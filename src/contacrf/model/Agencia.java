package contacrf.model;

import java.util.Map;

public class Agencia {

	private Map<String, ContaCorrente> contas;
	private final String numero = "6585-X";

	public String getNumero() {
		return numero;
	}

	public ContaCorrente criarConta(String numero) {
		return null;
	}

	public ContaCorrente getConta(String numero) {
		return null;
	}
}