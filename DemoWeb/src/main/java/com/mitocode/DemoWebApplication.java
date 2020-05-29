package com.mitocode;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscriber;
import com.mitocode.model.*;
import com.mitocode.repo.IProductoChiperRepo;
import com.mitocode.repo.IVentaRepo;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoWebApplication implements MqttCallback {

	@Autowired
	private IProductoChiperRepo repo;
	
	public static void main(String[] args) {
		try{
			DemoWebApplication hola = new DemoWebApplication();
		String publisherId = UUID.randomUUID().toString();
		IMqttClient suscriber = new MqttClient("tcp://52.91.152.101:1883", publisherId);
		suscriber.setCallback(hola);
		MqttConnectOptions options = new MqttConnectOptions();
		options.setAutomaticReconnect(true);
		options.setCleanSession(true);
		options.setConnectionTimeout(10);
		suscriber.connect(options);
		suscriber.subscribe("post");
		}catch (Exception e ){
			e.printStackTrace();
		}
		

		

		SpringApplication.run(DemoWebApplication.class, args);
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("message is : " + message);
		ProductoChiper pc = new ProductoChiper();
		String[] arreglo = message.toString().split("/");
		if(arreglo[0].equals("POST")) {
			pc.setId(arreglo[1]);
			pc.setNombre(arreglo[2]);
			pc.setCategoria(arreglo[3]);
			pc.setDescripcion(arreglo[4]);
			pc.setPrecio(Integer.parseInt(arreglo[5]));
			repo.save(pc);	
		}
		else {
			repo.deleteById(arreglo[1]);
		}
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

}