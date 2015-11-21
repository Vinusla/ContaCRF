package contacrf.model;

public class PessoaFisica implements Correntista {

	private String cpf;
	private String nome;
	private Endereco endereco;
	private String telefone;
	private String sexo;
	private String dataNasc;

	public PessoaFisica(String nome, String cpf, Endereco endereco, String telefone,
			String sexo, String dataNasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public String getCPF() {
		return this.cpf;
	}
	public String getNome() {
		return this.nome;
	}
	public Endereco getEndereco() {
		return this.endereco;
	}
}