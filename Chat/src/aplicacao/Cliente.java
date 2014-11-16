package aplicacao;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
	private InetAddress servidorIP;
	private static final int SERVIDOR_PORTA = 12345;
	private Socket serverSocket;
	
	public Cliente() {
		try {
			this.servidorIP = InetAddress.getLocalHost();
			this.serverSocket = new Socket(servidorIP, SERVIDOR_PORTA);
			System.out.println("Conexão com o Servidor estabelecida!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		new Cliente();
	}

}
