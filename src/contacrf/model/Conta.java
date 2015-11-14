package contacrf.model;

import java.util.Date;
import java.util.List;


public class Conta implements Transacao{
  private String numero;
  private Correntista titular ;
  private List<Transacao> transacoes;
public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}
public Correntista getTitular() {
	return titular;
}
public void setTitular(Correntista titular) {
	this.titular = titular;
}
public List<Transacao> getTransacoes() {
	return transacoes;
}
public void setTransacoes(List<Transacao> transacoes) {
	this.transacoes = transacoes;
}
@Override
public String getID() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public String getDescricao() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Date getData() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public float getValor() {
	// TODO Auto-generated method stub
	return 0;
}

  
  
}
