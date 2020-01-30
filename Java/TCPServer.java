import java.io.*;
import java.net.*;

public class TCPServer {
	public static void main(String[] args) {
		try {
			String clientSentence;
			String sentence;

			// Create port acceptation socket
			ServerSocket welcomeSocket = new ServerSocket(6787);
		
			// Create input Stream
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));

			// Wait for client to make contact
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Connected to client!");

			// Create input stream bonded to socket
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			// Create output stream bonded to socket
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			while(true){

				// Read line from socket
				clientSentence = inFromClient.readLine();
				System.out.println("Client: " + clientSentence);

				System.out.print("Server: ");
				sentence = inFromServer.readLine();

				// Write line to socket

				outToClient.writeBytes(sentence + "\n");

				if(clientSentence.equals("EXIT") || sentence.equals("EXIT")){
					connectionSocket.close();
					System.out.println("Connection ended!");
					break;
				}
			} // End of loop, return and awaits for another client connection
		}catch(IOException IOE){
			System.out.println("Impossible to stablish connection.");
		}
	}
}