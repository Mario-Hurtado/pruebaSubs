package com.mitocode.model;
import javax.persistence.*;

@Entity
public class Zona {
	
	@Id
	private String id;
	
	@Column (name = "nombre", length = 100)
	private String nombre;
	
	@Column (name = "descripcion", length = 100)
	private String descripcion;
}
