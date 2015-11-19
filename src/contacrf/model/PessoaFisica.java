package contacrf.model;

public class PessoaFisica implements Correntista {

	private String cpf;
	private String nome;
	private Endereco endereco;

	public PessoaFisica(String nome, String cpf, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;

	}

	@Override
	public String getCPF() {
		return this.cpf;
	}

	@Override
	public String getNome() {

		return this.nome;
	}

	@Override
	public Endereco getEndereco() {

		return this.endereco;
	}

}
