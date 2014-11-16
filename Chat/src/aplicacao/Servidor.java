package aplicacao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private static final int PORT = 12345;
	
	public Servidor() {
		System.out.println("Inicializando o servidor...");
		try {
			ServerSocket servidor = new ServerSocket(PORT);
			System.out.println("Servidor rodando na porta "
					+ PORT);
			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Nova conexão com o cliente "
						+ cliente.getInetAddress().getHostAddress());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Servidor();
	}

}
