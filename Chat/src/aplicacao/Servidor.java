package aplicacao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import thread.RecebeCliente;

public class Servidor {
	private static final int PORT = 12345;
	
	public Servidor() {
		System.out.println("Inicializando o servidor...");
		try {
			ServerSocket servidor = new ServerSocket(PORT);
			System.out.println("Servidor rodando na porta "
					+ PORT);
			while (true) {
				Socket socketCliente = servidor.accept();
				System.out.println("Nova conexão com o cliente "
						+ socketCliente.getInetAddress().getHostAddress());
				new Thread(new RecebeCliente(socketCliente)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Servidor();
	}

}
