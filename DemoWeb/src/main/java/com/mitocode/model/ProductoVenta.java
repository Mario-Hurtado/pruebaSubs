package com.mitocode.model;
import javax.persistence.*;

@Entity
public class ProductoVenta {

	@Id
	private String id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ProductoChiper_id", referencedColumnName = "id")
	private ProductoChiper producto;
	
	@Column (name = "cantidad")
	private int cantidad;
	
	@Column (name = "subtotal")
	private int subtotal;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Venta_id", referencedColumnName = "id")
	private Venta venta;
}
