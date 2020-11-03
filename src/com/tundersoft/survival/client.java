package com.tundersoft.survival;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tundersoft.survival.socket.SocketManagerService;

public class client {
	
	public static void main(String[] args) {
		
		SocketManagerService.getInstance().connect("180.97.81.180", 53705);
		SocketManagerService.getInstance().send("hello");
		
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
