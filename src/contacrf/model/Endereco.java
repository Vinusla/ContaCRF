package contacrf.model;

public class Endereco {

	private int id;
	private String rua;
	private String bairro;
	private int numero;

	public Endereco(String rua, String bairro, int numero) {

		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;

	}

	public int getId() {
		return id;
	}

	public String getRua() {
		return rua;
	}

	public String getBairro() {
		return bairro;
	}

	public int getNumero() {
		return numero;
	}

}
