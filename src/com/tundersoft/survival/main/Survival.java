package com.tundersoft.survival.main;

import com.tundersoft.survival.bean.MapBean;
import com.tundersoft.survival.constant.SurvivalConstant;
import com.tundersoft.survival.interf.OnServerCallBack;
import com.tundersoft.survival.socket.SocketManagerService;
import com.tundersoft.survival.util.SurvivalHelper;

public class Survival implements OnServerCallBack{
	
	String mIP;
	String mKey;
	int mPort;
	
	MapBean mMapBean;
	
	SocketManagerService mSocketManagerService;
	
	public Survival(){
		
	}
	
	public Survival(String key, String ip, int prot){
		mKey = key;
		mIP = ip;
		mPort = prot;
		mSocketManagerService = SocketManagerService.getInstance();
		mMapBean = new MapBean();
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
		
		if(null == call) return;
		
		if(call.contains("START")){
			// set global role
			mMapBean.setStartInfo(call);
			mSocketManagerService.setMapBean(mMapBean);
			return;
		}
		
		if(call.contains("LOCATION")){
			mMapBean.setMaps(call);
			mSocketManagerService.setMapBean(mMapBean);
			return;
		}
		
		
	}

}
