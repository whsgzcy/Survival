package com.tundersoft.survival.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
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
			System.out.println("will read 1");
			InputStream input = mSocket.getInputStream();
			InputStreamReader isr = new InputStreamReader(input, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			while (true) {
				System.out.println("will read");
				String str = br.readLine();
				System.out.println("·þÎñ¶Ë»Ø¸´£º" + str);
			}
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
							String three = new String(buffer);
							System.out.println("server re " + three);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		// read server
		class PingHandler extends Thread {
			public void run() {
				try {
					if (null == mSocket)
						return;
					while (true) {
						System.out.println("will ping");
						mSocket.getOutputStream().write("ping".getBytes("UTF-8"));
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}



}
