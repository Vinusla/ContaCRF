package contacrf.controller;


import java.util.ArrayList;
import java.util.List;

import contacrf.DAO.ContaCorrenteDAO;
import contacrf.exception.ConexaoException;
import contacrf.model.Conta;
import contacrf.model.ContaCorrente;

public class ContaCorrenteController {

	private ContaCorrenteDAO ccdao;

	public ContaCorrenteController(){
		this.ccdao = new ContaCorrenteDAO();
	}

	//abri uma nova conta
	public void AbrirConta(String numero, String cpfCliente, String senha) throws ConexaoException {
		try {
			this.ccdao.abrir(numero, cpfCliente, senha);
		} catch (ConexaoException e) {
			throw new ConexaoException("Não foi possível preparar abrir uma nova conta");
		}
	}

	//Retorna todas as contas
	public List<ContaCorrente> listarContas() throws ConexaoException{

		List contas = new ArrayList<ContaCorrente>();

		try {
			contas =  ccdao.listALL();
		} catch (ConexaoException e) {
			throw new ConexaoException(	"Não foi possível para buscar as contas");
		}
		return contas;
	}

	//Retorna uma conta
	public Conta exibir(String numConta) throws ConexaoException{
		ContaCorrente cc = null;

		try {
			if(existeConta(numConta))
				cc = ccdao.getByConta(numConta);
		} catch (ConexaoException e) {
			throw new ConexaoException("não foi possível encontrar a conta");
		}

		return cc;
	}

	//Bloquea uma conta
	public boolean bloquearConta(String numConta) throws ConexaoException{
		boolean status = false;

		try {
			if(existeConta(numConta))
				status = ccdao.bloquear(numConta);

		} catch (ConexaoException e) {
			throw new ConexaoException("não foi possível bloquear a conta");
		}
		return status;
	}

	//Altera a conta
	public boolean alterar(ContaCorrente cc) throws ConexaoException{

		boolean status = false;
		try {
			if(existeConta(cc.getNumero()))
				status = ccdao.update(cc);
		} catch (ConexaoException e) {
			throw new ConexaoException("Não foi possível realizar a alteração objeto Conta Corrente");
		}
		return status;
	}

	//verifica se a conta existe
	public boolean existeConta(String numConta) throws ConexaoException {
		boolean existe = false;

		try {
			existe = ccdao.verificaConta(numConta);
		} catch (ConexaoException e) {
			throw new ConexaoException(	"Não foi possível preparar o banco para a  busca de dados pelo numero da conta na tabela conta");
		}
		return existe;
	}
}