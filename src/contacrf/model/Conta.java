package contacrf.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	
	
	@Column(name = "num_Conta")
	private String numero;
	
	@OneToOne (targetEntity = PessoaFisica.class)
	@JoinColumn(name = "id_pesF")
	private Correntista titular;

	@Column(name = "saldo")
	private float saldo;

	//@OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
	//private List<Transacao> transacoes;
	

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

	//public List<Transacao> getTransacoes() {
	//	return transacoes;
	//}

	//public void setTransacoes(List<Transacao> transacoes) {
	//	this.transacoes = transacoes;
	//}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	

}
