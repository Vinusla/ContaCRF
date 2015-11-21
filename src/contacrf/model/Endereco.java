package contacrf.model;

public class Endereco {

	private int id;
	private String rua;
	private String bairro;
	private int numero;
	private String CEP;
	private String complemento;
	private String estado;

	public Endereco(String rua, String bairro, int numero, String CEP,
			String complemento, String estado) {
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.CEP = CEP;
		this.complemento = complemento;
		this.estado = estado;
	}

	public String getCEP() {
		return CEP;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getEstado() {
		return estado;
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