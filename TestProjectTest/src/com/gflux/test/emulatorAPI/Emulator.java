package com.gflux.test.emulatorAPI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;


public class Emulator {
	
	private int portNumber;
	private String hostname = "10.0.2.2";
	private TelnetClient client;
	private BufferedWriter consoleSend;
	private BufferedReader consoleReceive;
	private PipedInputStream emulatorOutputInputStream;
	private BufferedWriter emulatorOutput;
	private boolean running;
	
	public Emulator(int portNumber) {
		super();
		this.portNumber = portNumber;
		this.client = new TelnetClient();
	}

	public boolean setUpConnection(){
		try {
			client.connect(hostname,portNumber);
			
			/** Organize output */
			consoleSend = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			consoleReceive = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			emulatorOutputInputStream = new PipedInputStream();
			emulatorOutput = new BufferedWriter(new OutputStreamWriter(new PipedOutputStream(emulatorOutputInputStream)));
			
			running = true;
			writeToConsoleOutputStream("EmulatorAPI: Console output started.");
			transferConsoleOutputToCorrectStream();
			
			return true;
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean tearDownConnection(){
		try {
			running = false;
			
			emulatorOutput.close();
			emulatorOutputInputStream.close();
			consoleSend.close();
			consoleReceive.close();
			client.disconnect();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public InputStream getConsoleOutput(){
		return emulatorOutputInputStream;
	}
	
	public void displayDevicePower(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		sendCommand("power display");
		String result = "";
		try {
			String text = reader.readLine();
			result = text;
			while(!text.equals("") || !text.equals(null)){
				text = reader.readLine();
				result += text;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result.equals("")){
			
		}
		
	}
	
	public void chargingOn(){
		sendCommand("power ac on");
	}
	
	public void chargingOff(){
		sendCommand("power ac off");
	}
	
	public void setBatteryStatus(PowerStatus status){
		sendCommand("power status "+status.toString());
	}
	
	public void setBatteryHealth(BatteryHealth health){
		sendCommand("power health "+health.toString());
	}
	
	public void setRemaingingBatteryPower(int percentage){
		if(percentage >= 0 && percentage <= 100 ){
			sendCommand("power capacity "+percentage);
		}
	}
	
	public void setNetworkDelay(NetworkDelay delay){
		sendCommand("network delay "+ delay.toString());
	}
	
	public void setNetworkSpeed(NetworkSpeed speed){
		sendCommand("network speed "+ speed.toString());
	}

	private void sendCommand(String text){
		try {
			consoleSend.write(text+"\n");
			consoleSend.flush();
			writeToConsoleOutputStream(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeToConsoleOutputStream(String text){
		final String finalText = text;
		//In new thread since PipedOutputStream requires this
		new Thread(
			    new Runnable(){
			      public void run(){
			        try {
			        	String text;
			        	if(!stringEndsWithNewLine(finalText)){
			        		text = finalText + "\n";
			        	}else{
			        		text = finalText;
			        	}
						emulatorOutput.write(text);
						emulatorOutput.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      }
			    }
			  ).start();
	}
	
	private void transferConsoleOutputToCorrectStream(){
		new Thread(
			    new Runnable(){
			      public void run(){
			        while(running){
						try {
							String text = consoleReceive.readLine();
				        	if(!text.equals(null) && !text.equals("")){
				        		writeToConsoleOutputStream(text);
				        	}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			      }
			    }
			  ).start();
	}
	
	private boolean stringEndsWithNewLine(String text){
		return new String("" + text.charAt(text.length()-1)).equals("\n");
	}

}
