package com.mitocode.model;

import javax.persistence.*;


@Entity
public class Tienda {
	
	@Id
	private String id;
	
	@Column (name = "nombre", length = 100)
	private String nombre;
	
	@Column (name = "ingresos")
	private int ingresos;
	
	@Column (name = "direccion", length = 100)
	private String direccion;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Zona_id", referencedColumnName = "id")
	private Zona zona;
	
	@Column (name = "ciudad", length = 100)
	private String ciudad;
	
	@Column (name = "pais", length = 100)
	private String pais;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIngresos() {
		return ingresos;
	}

	public void setIngresos(int ingresos) {
		this.ingresos = ingresos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
}
