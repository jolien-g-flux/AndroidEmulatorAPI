package com.gflux.test.emulatorAPI;

public enum BatteryHealth {
	UNKNOWN("unknown"),
	GOOD("good"),
	OVERHEAT("overheat"),
	DEAD("dead"),
	OVERVOLTAGE("overvoltage"),
	FAILURE("failure");
	
	private String command;
	
	private BatteryHealth(String command){
		this.command = command;
	}
	
	public String toString(){
		return this.command;
	}

}
