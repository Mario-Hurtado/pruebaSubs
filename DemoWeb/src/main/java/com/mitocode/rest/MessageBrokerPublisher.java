package com.mitocode.rest;

import java.util.concurrent.Callable;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageBrokerPublisher implements Callable<Void>
{
	public IMqttClient client;
	public String topic = "post";
	public String textoEnviar;
	
	public MessageBrokerPublisher(IMqttClient client) 
	{
        this.client = client;
    }
 
    @Override
    public Void call() throws Exception 
    {        
        if (!client.isConnected()) 
        {
        	System.out.println("No hay conexión");
            return null;
        }           
        MqttMessage msg = crearMensaje();
        msg.setQos(0);
        msg.setRetained(true);
        client.publish(topic,msg);        
        return null;        
    }
 
    private MqttMessage crearMensaje() 
    {                    
        byte[] payload = textoEnviar.getBytes();   
        System.out.println(textoEnviar);
        return new MqttMessage(payload);           
    }
}
