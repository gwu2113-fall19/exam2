package samples.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * CS2113 
 * Simple client that connects to a server
 * and requests it to perform coin flips
 * 
 * @author timwood@gwu.edu
 *
 */
public class SampleClient {

	public SampleClient() {
		
		String host = "127.0.0.1"; // local host
		int portnum = 4444;

		Socket sock;
		try {

			// create socket and output stream
			sock = new Socket(host, portnum);
			// setup output stream
			DataOutputStream out = new DataOutputStream(sock.getOutputStream());
			// setup input stream
			DataInputStream in = new DataInputStream(sock.getInputStream());

			int numFlips = 10;
			// send how many coin fips to generate
			out.writeInt(numFlips);
			
			// REMEMBER TO FLUSH the data stream!
			out.flush(); 
			
			System.out.println("Requested " + numFlips + " flips.");
			
			for(int i = 0; i < numFlips; i++) {
				// read in the coin flip
				boolean flip = in.readBoolean();
				if(flip) {
					System.out.println("Heads");
				}
				else {
					System.out.println("Tails");
				}
			}

			// clean up
			out.close();
			sock.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		new SampleClient();
	}

}
