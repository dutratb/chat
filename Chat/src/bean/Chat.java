package bean;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

import util.Mensagem;

public class Chat {
	private Map<String, DataOutputStream> clientes;

	public Chat(Map<String, DataOutputStream> clientes) {
		this.clientes = clientes;
	}

	public Map<String, DataOutputStream> getClientes() {
		return clientes;
	}

	public void entrar(Mensagem mensagem, DataOutputStream output) throws IOException {
		Usuario usuario = new Usuario(mensagem.getTexto());
		if (clientes.containsKey(usuario.getNome()))
			enviar(output, "Chat:> " + usuario.getNome() + " já está no grupo!");
		else {
		clientes.put(usuario.getNome(), new DataOutputStream(output));
		enviarTodos("Chat:>" + usuario.getNome() + " entrou no grupo!");
		}
	}

	private void enviarTodos(String texto) throws IOException {
		for (Map.Entry<String, DataOutputStream> map : clientes.entrySet()) {
			map.getValue().writeUTF(texto);
		}
	}

	private void enviar(DataOutputStream output, String texto) throws IOException {
		output.writeUTF(texto);
	}
}
