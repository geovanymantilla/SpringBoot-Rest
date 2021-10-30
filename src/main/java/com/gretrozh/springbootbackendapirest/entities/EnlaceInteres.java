package com.gretrozh.springbootbackendapirest.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="enlace_interes")
public class EnlaceInteres implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_enlace_interes")
    private int idEnlaceInteres;


    private String url;

    public int getIdEnlaceInteres() {
        return idEnlaceInteres;
    }

    public void setIdEnlaceInteres(int idEnlaceInteres) {
        this.idEnlaceInteres = idEnlaceInteres;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	@Override
	public String toString() {
		return "EnlaceInteres [idEnlaceInteres=" + idEnlaceInteres + ", url=" + url + "]";
	}
    
}
