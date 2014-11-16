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
		while (true) {
			try {
				mensagem = (Mensagem) input.readObject();
				switch (mensagem.getAcao()) {
				case entrar:
					this.usuario = this.chat.entrar(mensagem, output);
					break;
				case sair:
					if (usuario != null) {
						this.chat.sair(usuario);
						usuario = null;
						break;
					} 
					else
						this.chat.enviar("Chat:> É necessário entrar no chat.", this.output);
					break;
				default:
					break;
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
