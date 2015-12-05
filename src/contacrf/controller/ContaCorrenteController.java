package contacrf.controller;


import java.util.ArrayList;
import java.util.List;

import contacrf.DAO.ContaCorrenteDAO;
import contacrf.exception.ConexaoException;
import contacrf.exception.ContaJaCadastradaException;
import contacrf.exception.ContaNãoCadastradaException;
import contacrf.model.ContaCorrente;

public class ContaCorrenteController {

	private ContaCorrenteDAO ccdao;

	public ContaCorrenteController(){
		this.ccdao = new ContaCorrenteDAO();
	}

	//abri uma nova conta
	public void AbrirConta(String numero, String cpfCliente, String senha) throws ConexaoException, ContaNãoCadastradaException {
		try {
			this.ccdao.abrir(numero, cpfCliente, senha);
		} catch (ConexaoException e) {
			throw new ConexaoException(e.getMessage());
		} catch (ContaNãoCadastradaException e) {
			throw new ContaNãoCadastradaException(e.getMessage());
		}
	}

	//Retorna todas as contas
	public List<ContaCorrente> listarContas() throws ConexaoException{

		List contas = new ArrayList<ContaCorrente>();

		try {
			contas = ccdao.listALL();
		} catch (ConexaoException e) {
			throw new ConexaoException(e.getMessage());
		}
		return contas;
	}

	//Retorna uma conta
	public ContaCorrente exibir(String numConta) throws ConexaoException, ContaNãoCadastradaException, ContaJaCadastradaException{
		ContaCorrente cc = null;

		try {
			if(existeConta(numConta))
				cc = ccdao.getByConta(numConta);
		} catch (ConexaoException e) {
			throw new ConexaoException(e.getMessage());
		} catch (ContaNãoCadastradaException e) {
			throw new ContaNãoCadastradaException(e.getMessage());
		} catch (ContaJaCadastradaException e) {
			throw new ContaJaCadastradaException(e.getMessage());
		}

		return cc;
	}

	// retorna o numero da conta atravs do cpf
	public String getNumeroConta(String cpfCliente) {

		String numConta = null;

		try {
			numConta = ccdao.getByNumConta(cpfCliente);
		} catch (ConexaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return numConta;

	}

	//Bloquea uma conta
	public boolean bloquearConta(String numConta) throws ConexaoException, ContaNãoCadastradaException, ContaJaCadastradaException{
		
		boolean status = false;

		try {
			if(existeConta(numConta))
				status = ccdao.bloquear(numConta);

		} catch (ConexaoException e) {
			throw new ConexaoException(e.getMessage());
		} catch (ContaNãoCadastradaException e) {
			throw new ContaNãoCadastradaException(e.getMessage());
		} catch (ContaJaCadastradaException e) {
			throw new ContaJaCadastradaException(e.getMessage());
		}
		return status;
	}
	
	
	//Altera a conta
	public boolean alterar(ContaCorrente cc) throws ConexaoException, ContaNãoCadastradaException, ContaJaCadastradaException{

		boolean status = false;
		try {
			if(existeConta(cc.getNumero()))
				status = ccdao.update(cc);
		} catch (ConexaoException e) {
			throw new ConexaoException(e.getMessage());
		} catch (ContaNãoCadastradaException e) {
			throw new ContaNãoCadastradaException(e.getMessage());
		} catch (ContaJaCadastradaException e) {
			throw new ContaJaCadastradaException(e.getMessage());
		}
		return status;
	}

	//verifica se a conta existe
	public boolean existeConta(String numConta) throws ConexaoException, ContaNãoCadastradaException, ContaJaCadastradaException {
		boolean existe = false;

		try {
			existe = ccdao.verificaConta(numConta);
		} catch (ConexaoException e) {
			throw new ConexaoException(e.getMessage());
		} catch (ContaNãoCadastradaException e) {
			throw new ContaNãoCadastradaException(e.getMessage());
		} catch (ContaJaCadastradaException e) {
			throw new ContaJaCadastradaException(e.getMessage());
		}
		return existe;
	}
}