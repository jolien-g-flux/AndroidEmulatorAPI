package com.gflux.test.emulatorAPI;

public enum NetworkSpeed {
	GSM_CSD("gsm"),
	HSCSD("hscsd"),
	GPRS("gprs"),
	EDGE_EGPRS("edge"),
	UMTS_3G("umts"),
	HSDPA("hsdpa"),
	NO_LIMIT("full");
	
	private String command;
	
	private NetworkSpeed(String command){
		this.command = command;
	}
	
	public String toString(){
		return this.command;
	}

}
