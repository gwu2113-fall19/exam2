package email;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;


/**
 * CS2113
 *
 * Simple "email" server.
 *
 * @author timwood@seas.gwu.edu
 *
 */

class Email  {
	String sender;
	String subject;
	String body;
	public Email(String send, String sub, String bod) {
		sender = send;
		subject = sub;
		body = bod;
	}
}

public class EmailServer {

	private Hashtable<String, Email[]> emails;

	private void init(){

		emails = new Hashtable<String, Email[]>();

		Email[] msgs = new Email[2];
		msgs[0] = new Email("bill@microsoft.com", "computer crash.", "Prof. Wood,\nThis odd blue screen keeps appearing on my computer, can you help?\n\n-Bill G.");
		msgs[1] = new Email("obama@whitehouse.gov", "Good work.", "Tim,\nGood game the other day... I need to practice my rebounding.\n\n-The Prez.");
		emails.put("tim:secret", msgs);

		msgs = new Email[3];
		msgs[0] = new Email("asdfd@fdsfds.com", "important message", "Are you interested in rolex watches?  What about unapproved pharamacueticals in shady packaging?  Email me for details!");
		msgs[1] = new Email("realperson@legitimatedomain.com", "Winner!", "You have won the lottery!  Send me your name, address, social security number, and credit card details to receive your prize.");
		msgs[2] = new Email("security@goooogle.com", "Account Warning", "Dear valued customer,\n\nOur records indicate that your account may have been hacked.  Please send us your password so we can fix it.  We promise we are the real security peeeple.");
		emails.put("jsmith:nospam", msgs);

	}

	public EmailServer(){
		ServerSocket serverSocket = null;
		boolean listening = true;

		init();

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

				// Read in user:pass
				String userpass = in.readUTF();

				// Check if user:pass is valid
				if(emails.containsKey(userpass)) {
					Email[] msgs = emails.get(userpass);
					int numEmails = msgs.length;
					// Send the number of emails for this user
					out.writeInt(numEmails);
					out.flush();
					// for each email, send the sender and subject
					for(Email e: msgs) {
						out.writeUTF(e.sender);
						out.writeUTF(e.subject);
						out.flush();
					}
					// Read in what email the client wants to see
					int selected = in.readInt();
					if(selected < numEmails && selected >= 0) {
						// Send the selected message's body
						out.writeUTF(msgs[selected].body);
					}
					else{
						out.writeUTF("ERROR -- no such message");
					}
					out.flush();

				}
				else{
					System.out.println("Received invalid user/password: " + userpass + ". \nTry using  tim:secret  or jsmith:nospam");
				}

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

		new EmailServer();

	}
}
