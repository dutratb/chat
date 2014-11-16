package util;

import java.io.Serializable;

public class Mensagem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Acao acao;
	private String texto;

	public Mensagem(Acao acao, String texto) {
		this.acao = acao;
		this.texto = texto;
	}

	public Mensagem(Acao acao) {
		this.acao = acao;
		this.texto = null;
	}

	public Acao getAcao() {
		return acao;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
