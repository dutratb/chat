package bean;

import java.io.DataOutputStream;
import java.util.Map;

public class Chat {
	private Map<String, DataOutputStream> clientes;

	public Chat(Map<String, DataOutputStream> clientes) {
		this.clientes = clientes;
	}

	public Map<String, DataOutputStream> getClientes() {
		return clientes;
	}
}
