package contacrf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PessoaFisica implements Correntista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "cpf")
	private String CPF;
	
	@Column(name = "nome")
	private String nome;
	
	@OneToOne 
	@JoinColumn(name = "id_end")
	private Endereco endereco;

	
	
	public void setCPF(String cPF) {
		CPF = cPF;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

	@Override
	public String getCPF() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getEndereco() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

	

}
