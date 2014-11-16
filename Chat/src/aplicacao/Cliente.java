package aplicacao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import thread.RecebeMensagem;
import util.Acao;
import util.Mensagem;

public class Cliente {
	private InetAddress servidorIP;
	private static final int SERVIDOR_PORTA = 12345;
	private Socket serverSocket;
	private DataInputStream input;
	private ObjectOutputStream output;

	public Cliente() {
		try {
			this.servidorIP = InetAddress.getLocalHost();
			this.serverSocket = new Socket(servidorIP, SERVIDOR_PORTA);
			System.out.println("Conexão com o Servidor estabelecida!");
			this.input = new DataInputStream(serverSocket.getInputStream());
			this.output = new ObjectOutputStream(serverSocket.getOutputStream());
			new Thread(new RecebeMensagem(input)).start();
			run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() throws IOException {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(
				System.in));
		String entrada = "";
		while (!entrada.equals(Acao.fechar.toString())) {
			try {
				entrada = teclado.readLine();
				if (entrada.contains(Acao.entrar.toString())) {
					// Enquanto não for informado o nome do usuário não aceita
					while (entrada.replace(Acao.entrar.toString(), "").equals(
							"")) {
						System.out.print("Chat:> Erro: Usuário em branco");
						entrada = teclado.readLine();
					}
					entrar(new Mensagem(Acao.entrar, entrada.replace(
							Acao.entrar.toString(), "").trim()));
				} else if (entrada.contains(Acao.enviar.toString())) 
					acoes(new Mensagem(Acao.enviar, entrada.replace(Acao.enviar.toString(), "").trim()));
				else if (entrada.equals(Acao.listar.toString().trim()))
					listar();
				else if (entrada.equals(Acao.sair.toString())) {
					acoes(new Mensagem(Acao.sair, entrada.replace(
							Acao.sair.toString(), "").trim()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.serverSocket.close();
	}

	private void listar() throws IOException {
		enviarMensagem(new Mensagem(Acao.listar));
	}

	private void acoes(Mensagem mensagem) throws IOException {
		if (!mensagem.getTexto().equals("")
				|| !mensagem.getAcao().equals(Acao.sair.toString())) {
			enviarMensagem(mensagem);
		}
	}

	private void entrar(Mensagem mensagem) throws IOException {
		enviarMensagem(mensagem);
	}

	private void enviarMensagem(Mensagem mensagem) throws IOException {
		this.output.writeObject(mensagem);
	}

	public static void main(String[] args) {
		new Cliente();
	}

}
