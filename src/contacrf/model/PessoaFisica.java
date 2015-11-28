package contacrf.model;

public class PessoaFisica implements Correntista {

	private String cpf;
	private String nome;
	private Endereco endereco;
	private String telefone;
	private String sexo;
	private String dataNasc;
	private int id_end;
	private String rg;

	public PessoaFisica(String nome, String cpf, Endereco endereco, String telefone, String sexo, String dataNasc,String rg) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
		this.rg = rg;
	}

	public PessoaFisica() {
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDataNasc() {
		return dataNasc;
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
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "PessoaFisica CPF " + cpf + "\nNome " + nome + "\nEndereco " + endereco + "\nTelefone " + telefone
				+ "\nSexo " + sexo + "\nDataNasc=" + dataNasc + "\nID Endereço " + id_end + "\nRG " + rg;
	}
}