package com.tundersoft.survival.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.tundersoft.survival.constant.SurvivalConstant;
import com.tundersoft.survival.interf.OnServerCallBack;

public class SocketManagerService {
	
	OnServerCallBack mOnServerCallBack;

	private Socket mSocket;
	private static SocketManagerService mInstance = null;

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
