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
		return this.clientes;
	}

	public Usuario entrar(Mensagem mensagem, DataOutputStream output) throws IOException {
		Usuario usuario = new Usuario(mensagem.getTexto());
		if (this.clientes.containsKey(usuario.getNome())) {
			enviar("Chat:> " + usuario.getNome() + " já está no grupo!", output);
			return null;
		}
		this.clientes.put(usuario.getNome(), new DataOutputStream(output));
		enviarTodos("Chat:> " + usuario.getNome() + " entrou no grupo!");
		return usuario;
	}

	private void enviarTodos(String texto) throws IOException {
		for (Map.Entry<String, DataOutputStream> map : this.clientes.entrySet()) {
			map.getValue().writeUTF(texto);
		}
	}

	public void enviar(String texto, DataOutputStream output) throws IOException {
		output.writeUTF(texto);
	}

	public void sair(Usuario usuario) throws IOException {
		enviarTodos("Chat:>" + usuario.getNome() + " saiu!");
		this.clientes.remove(usuario.getNome());
	}

	public void listar(DataOutputStream output) throws IOException {
		String lista = "Chat:> ";
		for (Map.Entry<String, DataOutputStream> map : clientes.entrySet()) {
			lista = lista + " [" + map.getKey() + "] ";
		}
		enviar(lista, output);
		
	}
	
}
