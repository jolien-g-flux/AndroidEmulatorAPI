package com.gflux.test.emulatorAPI;

public enum GSMDataState {
	UNREGISTERED("unregistered"),
	LOCAL("home"),
	ROAMING("roaming"),
	SEARCHING("searching"),
	EMERGENCY_ONLY("denied"),
	OFF("off"),
	ON("on");
	
	private String command;
	
	private GSMDataState(String command){
		this.command = command;
	}
	
	public String toString(){
		return this.command;
	}

}
