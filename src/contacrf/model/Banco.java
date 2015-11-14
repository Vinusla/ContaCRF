package contacrf.model;

import java.util.Map;

public class Banco {
private String numero ;
private String nome;
private Map<String,Agencia> agencias;
public Banco(String numero, String nome) {
	this.numero = numero;
	this.nome = nome;
}		
	
public Agencia criarAgencia(String codigo) {
	return null;
}
public Agencia getAgencia(String codigo) {
	return null;
}
}
