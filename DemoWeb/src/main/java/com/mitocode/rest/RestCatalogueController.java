package com.mitocode.rest;

import java.util.List;
import java.util.UUID;

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

import com.mitocode.DemoWebApplication;
import com.mitocode.model.ProductoChiper;
import com.mitocode.repo.IProductoChiperRepo;

@RestController
@RequestMapping("/catalogo")
public class RestCatalogueController 
{
	@Autowired
	private IProductoChiperRepo repo;

	private MessageBrokerPublisher messageBroker = new MessageBrokerPublisher(DemoWebApplication.publisher);

	@GetMapping
	public List<ProductoChiper> listar(){
		return repo.findAll();
	}

	@PostMapping
	public void insertar(@RequestBody ProductoChiper prod){
		repo.save(prod);
		messageBroker.textoEnviar = "POST/" + prod.getId() + "/" + prod.getNombre() + "/" + prod.getCategoria() + "/" +
				prod.getDescripcion() + "/" + prod.getPrecio();
		try 
		{
			messageBroker.call();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@PutMapping
	public void modificar(@RequestBody ProductoChiper prod){
		repo.save(prod);
		messageBroker.textoEnviar = "UPDATE/" +  prod.getId() + "/" + prod.getNombre() + "/" + prod.getCategoria() + "/" +
				prod.getDescripcion() + "/" + prod.getPrecio();
		try 
		{
			messageBroker.call();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") String id) {
		repo.deleteById(id);
		messageBroker.textoEnviar = "DELETE/" + id;
		try 
		{
			messageBroker.call();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
