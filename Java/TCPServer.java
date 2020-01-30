import java.io.*;
import java.net.*;

public class TCPServer {
	public static void main(String[] args) {
		try {
			String clientSentence;
			String sentence;

			// Cria socket de aceitacao da porta
			ServerSocket welcomeSocket = new ServerSocket(6787);
		
			// Cria Stream de entrada
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));

			// Espera no Socket de Aceitacao por contato do cliente
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Connected to client!");
			// Cria Stream de entrada ligado ao socket
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			// Cria stream de saida ligado ao socket
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			while(true){

				// ler linha do socket
				clientSentence = inFromClient.readLine();
				System.out.println("Client: " + clientSentence);

				System.out.print("Server: ");
				sentence = inFromServer.readLine();

				// Escreve linha para o socket

				outToClient.writeBytes(sentence + "\n");

				if(clientSentence.equals("EXIT") || sentence.equals("EXIT")){
					connectionSocket.close();
					System.out.println("Connection ended!");
					break;
				}
			} // Fim do loop, retorna e espera por outra conexao do cliente
		}catch(IOException IOE){
			System.out.println("Não foi possivel abrir conexao.");
		}
	}
}