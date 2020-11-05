package com.tundersoft.survival.main;

import com.tundersoft.survival.constant.SurvivalConstant;
import com.tundersoft.survival.interf.OnServerCallBack;
import com.tundersoft.survival.socket.SocketManagerService;

public class Survival implements OnServerCallBack{
	
	String mIP;
	String mKey;
	int mPort;
	
	SocketManagerService mSocketManagerService;
	
	public Survival(){
		
	}
	
	public Survival(String key, String ip, int prot){
		mKey = key;
		mIP = ip;
		mPort = prot;
		mSocketManagerService = SocketManagerService.getInstance();
	}
	
	public void init(){
		
		mSocketManagerService.setOnServerCallBackListener(this);
	}
	
	/**
	 * connect server
	 * @param key
	 * @param ip
	 * @param prot
	 */ 
	public void connect(){
		mSocketManagerService.connect(mIP, mPort);
		mSocketManagerService.send(SurvivalConstant.KEY);
	}
	

	@Override
	public void onServerCallBack(String call) {
		
		
		
		
	}

}
