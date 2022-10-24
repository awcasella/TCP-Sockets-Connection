import java.io.*;
import java.net.*;

public class TCPClient{
	public static void main(String[] args) {
		try{
			String sentence;
			String answer;
		
			// Create input Stream
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			
			// Getting server address
			String serverIpAddress = "192.168.15.6"; // Modify with the ip address of the server
			Integer serverPort = 6787; // Doesn't need to be modified

			// Create Client socket, conects to the server
			Socket clientSocket = new Socket(serverIpAddress, serverPort);
			System.out.println("Conected to server!");

			// Cria output stream, bonded to socket
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			// Cria input stream, bonded to socket
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			while(true){
				System.out.print("Client: ");
				sentence = inFromUser.readLine();

				// Write line to the server
				outToServer.writeBytes(sentence + '\n');
				answer = inFromServer.readLine();

				// Read line from servidor
				System.out.println("Server: " + answer);
				
				if(answer.equals("EXIT") || sentence.equals("EXIT")){
					clientSocket.close();
					System.out.println("Connection ended!");
					break;
				}
			}
			
		}catch(IOException IOE){
			System.out.println("Impossible to stablish connection.");
		}
	}
}
