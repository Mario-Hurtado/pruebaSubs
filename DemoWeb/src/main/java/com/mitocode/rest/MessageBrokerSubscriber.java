package com.mitocode.rest;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import javax.validation.Payload;

import com.mitocode.DemoWebApplication;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.concurrent.*;

public class MessageBrokerSubscriber implements Callable<Void>{


	public IMqttClient subscriber;
	public String topic = "post";
	public String textoRecibir;
	
	
	
	public MessageBrokerSubscriber(IMqttClient client) 
	{
        this.subscriber = client;
    }
 
    @Override
    public Void call() throws Exception 
    {        
        if (!subscriber.isConnected()) 
        {
        	System.out.println("No hay conexión");
            return null;
        }           
     
        subscriber.subscribe(topic, 0);        
        return null;        
    }
 
    private void recibirMensaje() throws MqttException{                   
   
    	try {
    		CountDownLatch receivedSignal = new CountDownLatch(10);
        	subscriber.subscribe(topic, (topic, msg) -> {
        	    byte[] payload = msg.getPayload();
        	    // ... payload handling omitted
        	    receivedSignal.countDown();
        	    System.out.println(payload);
        	});    
        	receivedSignal.await(1, TimeUnit.MINUTES);
    	}
    	
    	catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
    	
    	
    }
    
    
}
