package com.gflux.test.emulatorAPI;

public enum NetworkDelay {
	GPRS("gprs"),
	EDGE_EGPRS("edge"),
	UMTS_3G("umts"),
	NO_LATENCY("none");
	
	private String command;
	
	private NetworkDelay(String command){
		this.command = command;
	}
	
	public String toString(){
		return this.command;
	}

}
