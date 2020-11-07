package com.tundersoft.survival.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.tundersoft.survival.bean.MapBean;
import com.tundersoft.survival.constant.SurvivalConstant;
import com.tundersoft.survival.interf.OnServerCallBack;

public class SocketManagerService {
	
	OnServerCallBack mOnServerCallBack;

	private Socket mSocket;
	private static SocketManagerService mInstance = null;
	
	MapBean mMapBean = new MapBean();

	private SocketManagerService() {
	}

	public static SocketManagerService getInstance() {
		if (mInstance == null) {
			synchronized (SocketManagerService.class) {
				if (mInstance == null) {
					mInstance = new SocketManagerService();
				}
			}
		}
		return mInstance;
	}
	
	public void setOnServerCallBackListener(OnServerCallBack call){
		mOnServerCallBack = call;
	}

	public void connect(String ip, int prot) {
		try {
			mSocket = new Socket(ip, prot);
			new ServerHandler().start();
			new PingHandler().start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void send(String msg) {
		if (null == msg || msg.isEmpty() || null == mSocket)
			return;

		try {
			mSocket.getOutputStream().write(msg.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMapBean(MapBean bean){
		mMapBean = bean;
	}
	
	public MapBean getMapBean(){
		return mMapBean;
	}
	
	/**
	 * up
	 */
	public void upAndAttack(){
		send(mMapBean.getToken() + SurvivalConstant.W + SurvivalConstant.V);
	}
	
	public void upAndNoAttack(){
		send(mMapBean.getToken() + SurvivalConstant.W + SurvivalConstant.NV);
	}
	
	/**
	 * left
	 */
	public void leftAndAttack(){
		send(mMapBean.getToken() + SurvivalConstant.A + SurvivalConstant.V);
	}
	
	public void leftAndNoAttack(){
		send(mMapBean.getToken() + SurvivalConstant.A + SurvivalConstant.NV);
	}
	
	/**
	 * down
	 */
	public void downAndAttack(){
		send(mMapBean.getToken() + SurvivalConstant.S + SurvivalConstant.V);
	}
	
	public void downNOAndAttack(){
		send(mMapBean.getToken() + SurvivalConstant.S + SurvivalConstant.NV);
	}
	
	/**
	 * right
	 */
	public void rightAndAttack(){
		send(mMapBean.getToken() + SurvivalConstant.D + SurvivalConstant.V);
	}
	
	public void rightAndNoAttack(){
		send(mMapBean.getToken() + SurvivalConstant.D + SurvivalConstant.NV);
	}

	// read server
	class ServerHandler extends Thread {
		public void run() {
			try {
				if (null == mSocket)
					return;

				while (true) {
					DataInputStream input = new DataInputStream(
							mSocket.getInputStream());
					byte[] buffer = new byte[input.available()];
					if (buffer.length != 0) {
						input.read(buffer);
						if(null != mOnServerCallBack){
							mOnServerCallBack.onServerCallBack(new String(buffer));
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// ping server
	class PingHandler extends Thread {
		public void run() {
			try {
				if (null == mSocket)
					return;
				while (true) {
					mSocket.getOutputStream().write(SurvivalConstant.H.getBytes("UTF-8"));
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
