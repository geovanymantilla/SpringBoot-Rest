package com.gretrozh.springbootbackendapirest.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="pregunta_frecuente")
public class PreguntaFrecuente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_pregunta_frecuente")
    private int idPreguntaFrecuente;

    private String pregunta;

    private String respuesta;

    public int getIdPreguntaFrecuente() {
        return idPreguntaFrecuente;
    }

    public void setIdPreguntaFrecuente(int idPreguntaFrecuente) {
        this.idPreguntaFrecuente = idPreguntaFrecuente;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
