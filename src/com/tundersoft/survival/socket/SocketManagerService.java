package com.tundersoft.survival.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketManagerService {

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

	public void connect(String ip, int prot) {
		try {
			mSocket = new Socket(ip, prot);
			mSocket.setSoTimeout(5000);

			ping();
			read();

			new ServerHandler().run();
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

	public void ping() {

		try {
			if (null == mSocket)
				return;
			mSocket.getOutputStream().write("ping".getBytes("UTF-8"));
			Thread.sleep(1000);
			ping();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void read() {
		if (null == mSocket)
			return;

		try {
			InputStream input = mSocket.getInputStream();
			InputStreamReader isr = new InputStreamReader(input, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			while (true) {
				String str = br.readLine();
				System.out.println("服务端回复：" + str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// read server
	class ServerHandler implements Runnable {
		public void run() {
			try {
				if (null == mSocket)
					return;

				InputStream input = mSocket.getInputStream();
				InputStreamReader isr = new InputStreamReader(input, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				while (true) {
					String str = br.readLine();
					System.out.println("服务端回复：" + str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
