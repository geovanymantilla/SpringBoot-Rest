package com.web.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.entities.Foto;
import com.web.entities.Noticia;
import com.web.entities.Usuario;
import com.web.repository.services.FotoService;
import com.web.repository.services.IUploadFileService;
import com.web.repository.services.NoticiaService;
import com.web.repository.services.UsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/noticia")
public class NoticiaController {
 //Error al insertar dependecia de noticias
	@Autowired
	private NoticiaService noticiaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	private IUploadFileService uploadService;
	
	@Autowired
	private FotoService fotoService;
	
	@GetMapping("")
	public ResponseEntity<?> listarNoticias(){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Noticia> noticias = noticiaService.findAll();
			if(noticias.isEmpty()) {
				map.put("mensaje", "no existen noticias en la bd");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Noticia>>(noticias, HttpStatus.OK);
		} catch (DataAccessException | InternalError e) {
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured({"ROLE_ADMIN","ROLE_COMUNICADOR"})
	@PostMapping("/save/{idSubCategoria}")
	public ResponseEntity<?> guardar(@ModelAttribute Noticia noticia, Principal principal,
			@PathVariable int idSubCategoria, @RequestParam("files") List<MultipartFile> files) throws IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Usuario user = usuarioService.findByEmail(principal.getName());
			if(user == null) {
				map.put("mensaje", "no existe el usuario en la bd");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			
			if(files.isEmpty()) {	
				System.out.println("ENTRO ACA");
				System.out.println(files.size());
				map.put("mensaje", "no se envio ninguna imagen");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			System.out.println(files.toString());
			
			noticia.setUsuario(user);
			noticia.setAlcaldia(user.getAlcaldia());
			noticiaService.save(noticia);
			
			List<Foto> fotos = new ArrayList<>();
			for (int i = 0; i < files.size(); i++) {
				Foto f = new Foto();
				f.setNoticia(noticia);
				f.setDescripcion(uploadService.copy(files.get(i)));
				f.setUsuario(user);	
				System.out.println("GUARDO FOTO");
				fotos.add(f);
				fotoService.save(f);
			}
			noticia.setFotos(fotos);
			return new ResponseEntity<Noticia>(noticia, HttpStatus.OK);

		} catch (DataAccessException | InternalError e) {
			System.out.print(e);
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{idNoticia}")
	public ResponseEntity<?> listarPorNoticia(@PathVariable int idNoticia){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Noticia noticia = noticiaService.findById(idNoticia);
			if(noticia == null) {
				map.put("mensaje", "no existe la noticia");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Noticia>(noticia, HttpStatus.OK);
		} catch (DataAccessException | InternalError e) {
			System.out.print(e);
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
	}
	
	@Secured({"ROLE_ADMIN","ROLE_COMUNICADOR"})
	@DeleteMapping("/{idNoticia}")
	public ResponseEntity<?> eliminar(@PathVariable int idNoticia){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Noticia noticia = noticiaService.findById(idNoticia);
			if(noticia == null) {
				map.put("mensaje", "no se pudo eliminar");
				return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			}
			
			for(int i = 0; i < noticia.getFotos().size(); i++) {
				 Foto f =noticia.getFotos().get(i);
				 Noticia n= null;
				    f.setNoticia(n);
				uploadService.delete(noticia.getFotos().get(i).getDescripcion());
			   
			}
			noticiaService.remove(idNoticia);
			map.put("mensaje", "Noticia eliminada");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (DataAccessException | InternalError e) {
			map.put("mensaje", "Equipo no pudo ser eliminado!");
			map.put("error", e.getCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
	}
}
