package aplicacao;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {
	private static final int PORT = 12345;
	
	public Servidor() {
		System.out.println("Inicializando o servidor...");
		
		try {
			ServerSocket servidor = new ServerSocket(PORT);
			System.out.println("Servidor rodando na porta "
					+ PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Servidor();
	}

}
