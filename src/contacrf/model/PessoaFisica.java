package contacrf.model;
public class PessoaFisica implements Correntista{

	private String CPF;
	private String nome;
	private String endereco;
	
	
	
	
	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCPF() {
		return CPF;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}



}
