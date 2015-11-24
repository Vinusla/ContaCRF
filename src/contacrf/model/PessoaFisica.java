package contacrf.model;

public class PessoaFisica implements Correntista {

	private String cpf;
	private String nome;
	private Endereco endereco;
	private String telefone;
	private String sexo;
	private String dataNasc;
	private int id_end;

	public PessoaFisica(String nome, String cpf, Endereco endereco, String telefone, String sexo, String dataNasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
	}

	public PessoaFisica() {
 
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

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public int getId_end() {
		return id_end;
	}

	public void setId_end(int id_end) {
		this.id_end = id_end;
	}
}