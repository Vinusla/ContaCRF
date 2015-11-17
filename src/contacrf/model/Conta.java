package contacrf.model;

import java.util.List;

public class Conta {

	private int id;
	private String numero;
	private Correntista titular;
	private float saldo;
	private List<Transacao> transacoes;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Correntista getTitular() {
		return titular;
	}

	public void setTitular(Correntista titular) {
		this.titular = titular;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

}
