package com.mitocode.rest;
import com.mitocode.DemoWebApplication;
import java.util.List;
import java.util.UUID;
import com.mitocode.model.*;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mitocode.repo.*;
import com.mitocode.DemoWebApplication;
import com.mitocode.model.ProductoChiper;
import com.mitocode.repo.IProductoChiperRepo;

@RestController
@RequestMapping("/ventas")
public class RestCatalogueController 
{
	@Autowired
	private IVentaRepo repo;



	@GetMapping
	public List<Venta> listar(){
		return repo.findAll();
	}

	@PostMapping
	public void insertar(@RequestBody Venta venta){
		repo.save(venta);
		
	}

	@PutMapping
	public void modificar(@RequestBody Venta venta){
		repo.save(venta);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") String id) {
		repo.deleteById(id);
		
	}

}
