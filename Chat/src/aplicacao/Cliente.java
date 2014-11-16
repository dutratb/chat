package aplicacao;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import thread.RecebeMensagem;

public class Cliente {
	private InetAddress servidorIP;
	private static final int SERVIDOR_PORTA = 12345;
	private Socket serverSocket;
	private DataInputStream input;
	
	public Cliente() {
		try {
			this.servidorIP = InetAddress.getLocalHost();
			this.serverSocket = new Socket(servidorIP, SERVIDOR_PORTA);
			System.out.println("Conexão com o Servidor estabelecida!");
			this.input = new DataInputStream(serverSocket.getInputStream());
			new Thread(new RecebeMensagem(input)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new Cliente();
	}

}
