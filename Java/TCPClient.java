import java.io.*;
import java.net.*;

public class TCPClient{
	public static void main(String[] args) {
		try{
			String sentence;
			String answer;
		
			// Cria Stream de entrada
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

			// Cria socket Client, conecta ao servidor
			Socket clientSocket = new Socket("192.168.0.5", 6787);
			System.out.println("Conected to server!");

			// Cria stream de saida, ligado ao socket
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

			// Cria stream de entrada ligado ao socket
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			while(true){
				System.out.print("Client: ");
				sentence = inFromUser.readLine();

				// Envia linha para o servidor
				outToServer.writeBytes(sentence + '\n');
				answer = inFromServer.readLine();

				// Ler linha do servidor
				System.out.println("Server: " + answer);
				
				if(answer.equals("EXIT") || sentence.equals("EXIT")){
					clientSocket.close();
					System.out.println("Connection ended!");
					break;
				}
			}
			
		}catch(IOException IOE){
			System.out.println("Não foi possivel realizar conexao.");
		}
	}
}