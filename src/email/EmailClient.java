package email;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * CS2113
 *
 * Simple "email" client.
 *
 * @author timwood@seas.gwu.edu
 *
 */
public class EmailClient {

	/**
	 * Asks the user to enter a number.  Returns the value entered.
	 * Don't change this code.
	 *
	 * Sample use:
	 *   int selectedMsg = enterNnumber();
	 */
	private int enterNumber() {
		System.out.print("Enter a number: ");
		int val = 0;
		String line = "";
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			line = is.readLine();
			val = Integer.parseInt(line);
		} catch (NumberFormatException ex) {
			System.err.println("Not a valid number: " + line);
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}

		return val;
	}

	public EmailClient() {

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


			// YOUR CODE HERE!

			out.writeUTF("tim:secret");
			int numMsgs;
			numMsgs = in.readInt();
			System.out.println("I have " + numMsgs + " messages.");

			for(int i = 0; i < numMsgs; i++ ) {
				String subject;
				String sender;

				sender = in.readUTF();
				subject = in.readUTF();
				System.out.println( i + " " + sender + " " + subject);
			}

			int m = enterNumber();
			System.out.println("Getting message number " + m + ".");

			out.writeInt(m); // send the mesesage number we want

			String message = in.readUTF(); // read in the actual message

			System.out.println(message);


			// clean up
			out.close();
			in.close();
			sock.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		new EmailClient();
	}

}
