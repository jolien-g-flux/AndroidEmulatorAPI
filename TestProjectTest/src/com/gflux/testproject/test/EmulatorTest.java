package com.gflux.testproject.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gflux.test.emulatorAPI.Emulator;
import com.gflux.testproject.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

public class EmulatorTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public EmulatorTest() {
		super(com.gflux.testproject.MainActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	public void testConnection(){
		Emulator emulator = new Emulator(5554);
		assertTrue("Failed to connect",emulator.setUpConnection());
		assertTrue("Failed to disconnect",emulator.tearDownConnection());
	}
	
	public void testConsoleOutputStream(){
		Emulator emulator = new Emulator(5554);
		assertTrue("Failed to connect",emulator.setUpConnection());
		BufferedReader reader = new BufferedReader(new InputStreamReader(emulator.getConsoleOutput()));
		String text = "";
		try {
			text += reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("EmulatorAPI: Console output started.", text);
		assertTrue("Failed to disconnect",emulator.tearDownConnection());
	}
	
	public void testConsoleOutputStream2(){
		Emulator emulator = new Emulator(5554);
		assertTrue("Failed to connect",emulator.setUpConnection());
		BufferedReader reader = new BufferedReader(new InputStreamReader(emulator.getConsoleOutput()));
		String text = "";
		try {
			text += reader.readLine();
			text += "\n" + reader.readLine();
			text += "\n" + reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("EmulatorAPI: Console output started.\nAndroid Console: type 'help' for a list of commands\nOK", text);
		assertTrue("Failed to disconnect",emulator.tearDownConnection());
	}
	
	public void testChargingOff(){
		Emulator emulator = new Emulator(5554);
		emulator.setUpConnection();
		emulator.chargingOff();
		emulator.tearDownConnection();
	}
	
	public void testChargingOff2(){
		Emulator emulator = new Emulator(5554);
		emulator.setUpConnection();
		emulator.setRemaingingBatteryPower(25);
		emulator.tearDownConnection();
	}
	
	public void testDisplayDevicePower(){
		Emulator emulator = new Emulator(5554);
		emulator.setUpConnection();
		emulator.displayDevicePower();
		emulator.tearDownConnection();
	}

}
