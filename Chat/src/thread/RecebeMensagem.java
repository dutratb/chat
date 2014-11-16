package thread;

import java.io.DataInputStream;
import java.io.IOException;

public class RecebeMensagem implements Runnable {
	private DataInputStream input;
	
	public RecebeMensagem(DataInputStream input) {
		this.input = input;
	}

	@Override
	public void run() {
		String mensagem;
		try {
			while (true) {
				mensagem = (String) input.readUTF();
				System.out.println(mensagem);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
