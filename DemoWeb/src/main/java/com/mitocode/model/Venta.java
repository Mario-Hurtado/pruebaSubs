package com.mitocode.model;
import java.sql.Date;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Venta {

	
	@Id
	private String id;
	
	@Column(name = "ganancia")	
	private int ganancia;

	@Column(name = "fecha")	
	private Date fecha;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Tienda_id", referencedColumnName = "id")
	private Tienda tienda;



	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getGanancia() {
		return ganancia;
	}


	public void setGanancia(int ganancia) {
		this.ganancia = ganancia;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public Tienda getTienda() {
		return tienda;
	}


	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	
	
}
