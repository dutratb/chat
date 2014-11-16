package thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Map;

import util.Mensagem;

public class RecebeCliente implements Runnable {
	private ObjectInputStream input;
	private DataOutputStream output;

	public RecebeCliente(Socket socket) {
		try {
			this.input = new ObjectInputStream(socket.getInputStream());
			this.output = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Mensagem msg;
		while (true) {
			try {
				msg = (Mensagem) input.readObject();
				switch (msg.getAcao()) {
				case entrar:
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
