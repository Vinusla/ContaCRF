package contacrf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "numero")
	private int numero;
	
	public Endereco(String rua, String bairro, int numero){ //mudar tipo de numero para menor
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		
	}
	

}
