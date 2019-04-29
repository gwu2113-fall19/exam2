package samples.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


/**
 * CS2113 
 * Simple server for performing
 * coin flips.
 * 
 * @author timwood@gwu.edu
 *
 */
public class SampleServer {

	private boolean coinFlips[];
	
	/**
	 * Fill in the coinFlips array with random values.
	 * @param num
	 */
	private void generateCoinFlips(int num){
		coinFlips = new boolean[num];
		Random rand = new Random();
		for(int i = 0; i < num; i++) {
			coinFlips[i] = rand.nextBoolean();
		}
	}
	
	public SampleServer(){
		ServerSocket serverSocket = null;
		boolean listening = true;
		
		System.out.println("Waiting for connections...");
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(-1);
		}

		while (listening) {
			try {
				Socket sock = serverSocket.accept();
				// client is now connected
				System.out.println("Connected to: " + sock.getInetAddress());
				// setup output stream
				DataOutputStream out = new DataOutputStream(sock.getOutputStream());
				// setup input stream
				DataInputStream in = new DataInputStream(sock.getInputStream());
				
				// Read the number of flips to perform from the client
				int num = in.readInt();
				
				generateCoinFlips(num);
				System.out.println("Sending coin flips as individual booleans...");
				for(int i = 0; i < num; i++){
					out.writeBoolean(coinFlips[i]);
				}
				out.flush(); // make sure all data is sent
				System.out.println("Finished sending " + num + " flips");
				
				out.close();
				in.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		new SampleServer();

	}
}
