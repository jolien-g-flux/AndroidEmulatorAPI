package com.gflux.test.emulatorAPI;

public enum GSMVoiceState {
	UNREGISTERED("unregistered"),
	LOCAL("home"),
	ROAMING("roaming"),
	SEARCHING("searching"),
	EMERGENCY_ONLY("denied"),
	OFF("off"),
	ON("on");
	
	private String command;
	
	private GSMVoiceState(String command){
		this.command = command;
	}
	
	public String toString(){
		return this.command;
	}

}
