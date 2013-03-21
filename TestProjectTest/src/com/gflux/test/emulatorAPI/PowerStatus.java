package com.gflux.test.emulatorAPI;

public enum PowerStatus {
	UNKNOWN("unknown"),
	CHARGING("charging"),
	DISCHARGING("discharging"),
	NOT_CHARGING("not-charging"),
	FULL("full");
	
	private String command;
	
	private PowerStatus(String command){
		this.command = command;
	}
	
	public String toString(){
		return this.command;
	}

}
