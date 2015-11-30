package contacrf.model;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import contacrf.controller.ContaCorrenteController;
import contacrf.exception.ConexaoException;
import contacrf.exception.SaldoInsuficienteException;
import contacrf.exception.ValorNegativoException;

public class Conta {

	private String numero;
	private String cpfCliente;
	private float saldo;
	private char ativo;
	private String senha;

	private List<Transacao> transacoes;

	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	public Conta(String numero, String cpfCliente) {
		this.numero = numero;
		this.setCpfCliente(cpfCliente);
		this.saldo = 0.0f;
		this.ativo = '1';
		this.transacoes = new LinkedList<Transacao>();
	}

	public Conta() {
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public double setSaldo(double d) {
		return this.saldo = (float) d;
	}


	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}


	public void sacar(ContaCorrente conta,float valor) throws SaldoInsuficienteException, ValorNegativoException, ConexaoException {
		ContaCorrenteController cc = new ContaCorrenteController();
		if (valor < 0 ) {
			throw new ValorNegativoException("Valor não pode ser negativo");
		} else if (this.getSaldo() - valor < 0) {
			throw new SaldoInsuficienteException("Saldo Insuficiente");
		}
		conta.setSaldo(conta.getSaldo()-valor);
		cc.alterar(conta);
		//Transacao novaTransacao = new Transacao(new Date(), valor, "SAQUE");
		//this.movimentacao(novaTransacao);

	}

	public void tranferencia(ContaCorrente contaOrigem,ContaCorrente contaDestino, float valor) throws SaldoInsuficienteException, ValorNegativoException, ConexaoException {
		ContaCorrenteController cc = new ContaCorrenteController();
		if (valor < 0 ) {
			throw new ValorNegativoException("Valor não pode ser negativo");
		} else if (this.getSaldo() - valor < 0) {
			throw new SaldoInsuficienteException("Saldo Insuficiente");
		}
		contaDestino.setSaldo(contaDestino.getSaldo() + valor);
		cc.alterar(contaDestino);
		contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
		cc.alterar(contaOrigem);
		//Transacao novaTransacao = new Transacao(new Date(), valor, "TRANSFERÊNCIA");
		//this.movimentacao(novaTransacao);
	}

	public void deposito(ContaCorrente conta,float valor) throws ValorNegativoException, ConexaoException {
		ContaCorrenteController cc = new ContaCorrenteController();

		if (valor <= 0 ) {
			throw new ValorNegativoException("Valor não pode ser negativo");
		}
		conta.setSaldo(conta.getSaldo() + valor);
		cc.alterar(conta);

		//Transacao novaTransacao = new Transacao(new Date(), valor, "Deposito");
		//this.movimentacao(novaTransacao);
	}

	public void extratoConta(int OP) {


		if(OP == 001){
			System.out.print("Extrato da conta Corrente");
		}else if(OP == 51){
			System.out.print("Extrato da conta Poupanca");
		}

		System.out.println("\nData\t\tvalor\t\ttipo\n");

		for (Transacao t : this.transacoes) {
			System.out.println(df.format(t.getData()) + "\t" + t.getValor()
					+ "\t\t" + t.getDescricao());
		}

		System.out.println("\nSaldo Atual: " + this.getSaldo() + "\n");

	}
}