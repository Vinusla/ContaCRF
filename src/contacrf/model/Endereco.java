package contacrf.model;

public class Endereco {

	private int id;
	private String rua;
	private String bairro;
	private int numero;
	private String CEP;
	private String complemento;
	private String estado;
	private String cidade;

	public Endereco(String rua, String bairro, int numero, String CEP, String complemento, String estado,String cidade) {
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.CEP = CEP;
		this.complemento = complemento;
		this.estado = estado;
		this.cidade = cidade;
	}

	public Endereco() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}