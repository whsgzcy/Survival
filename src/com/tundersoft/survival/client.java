package com.tundersoft.survival;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tundersoft.survival.main.Survival;

public class client {
	
	public static void main(String[] args) {
		
		Survival survival = new Survival("","180.97.81.180", 58190);
		survival.init();
		survival.connect();
		
		try {
			new BufferedReader(new InputStreamReader(System.in)).readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
