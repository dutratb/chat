package thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;

import bean.Chat;
import bean.Usuario;
import util.Mensagem;

public class RecebeCliente implements Runnable {
	private ObjectInputStream input;
	private DataOutputStream output;
	private Chat chat;
	private Usuario usuario;

	public RecebeCliente(Socket socket, Map<String, DataOutputStream> clientes) {
		try {
			this.input = new ObjectInputStream(socket.getInputStream());
			this.output = new DataOutputStream(socket.getOutputStream());
			this.chat = new Chat(clientes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Mensagem mensagem;
		try {
			while (!Thread.interrupted()) {
				mensagem = (Mensagem) input.readObject();
				switch (mensagem.getAcao()) {
				case entrar:
					this.usuario = this.chat.entrar(mensagem, this.output);
					break;
				case listar:
					if (this.usuario != null) {
						this.chat.listar(this.output);
						break;
					}
					else
						this.chat.enviar("Chat:> É necessario entrar no chat!", this.output);
					break;
				case sair:
					if (this.usuario != null) {
						this.chat.sair(this.usuario);
						this.usuario = null;
						break;
					} else
						this.chat.enviar("Chat:> É necessário entrar no chat.",
								this.output);
					break;
				default:
					break;
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			try {
				if (usuario != null)
					chat.sair(usuario);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
