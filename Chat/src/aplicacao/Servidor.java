package aplicacao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import thread.RecebeCliente;

public class Servidor {
	private static final int PORT = 12345;
	private Map<String, DataOutputStream> clientes;
	
	public Servidor() {
		this.clientes = new HashMap<String, DataOutputStream>();
		System.out.println("Inicializando o servidor...");
		try {
			ServerSocket servidor = new ServerSocket(PORT);
			System.out.println("Servidor rodando na porta "
					+ PORT);
			while (true) {
				Socket socketCliente = servidor.accept();
				System.out.println("Nova conexão com o cliente "
						+ socketCliente.getInetAddress().getHostAddress());
				new Thread(new RecebeCliente(socketCliente, clientes)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Servidor();
	}
}
