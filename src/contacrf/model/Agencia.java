package contacrf.model;

import java.util.Map;

public class Agencia {

	private Map<String, Conta> contas;
	private String numero;

	public Agencia(String numero) {
		super();
		this.numero = numero;
	}

	public Conta criarConta(String numero) {
		return null;
	}

	public Conta getConta(String numero) {
		return null;
	}
}