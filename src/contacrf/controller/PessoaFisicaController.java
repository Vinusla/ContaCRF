package contacrf.controller;

import contacrf.DAO.PessoaFisicaDAO;
import contacrf.exception.ConexaoException;
import contacrf.exception.EnderecoNullPointerException;
import contacrf.gui.tela.Erro;
import contacrf.model.Endereco;
import contacrf.model.PessoaFisica;

public class PessoaFisicaController {

	private PessoaFisicaDAO pfDAO;
	private EnderecoController endC;
	private Erro erro;
	private String msg = "CPF JA EXISTE";
	
	public PessoaFisicaController(){		
	
		this.pfDAO =  new PessoaFisicaDAO();
		this.endC = new EnderecoController();
		this.erro = new Erro(msg);
	}
	

	public void gravar(PessoaFisica pf) {
		
		try {
			if(!existeCPF(pf.getCPF()))
				pfDAO.save(pf);
			else
				erro.handle(null);
		} catch (ConexaoException e) {
			System.out.println(e);
		} catch (EnderecoNullPointerException e) {
			System.out.println(e);
		}
	}
	
	public PessoaFisica exibir(String cpf){
		
		PessoaFisica pf = null;
		
		try {
			pf = this.pfDAO.getByCpf(cpf);
		} catch (ConexaoException e) {
			System.out.println(e);
		}
		
		return pf;
		
	}
	
	
	public boolean excluir(PessoaFisica pf){
		
		boolean status = false;
		
		try {
			status = this.pfDAO.remove(pf);
			this.endC.remover(pf.getId_end());
		} catch (ConexaoException e) {
			System.out.println(e);
		}
		
		
		// criar uma condição para que verifique se retorno de remove de pfDAO e remover de endC se eh true, caso contrario status é false	
		
		
		return status;
	}
	
	
	public boolean alterar(PessoaFisica pf){
		
		boolean status = false;		
		
		try {
			status = this.pfDAO.update(pf);			
			this.endC.alterar(pf.getEndereco());
		} catch (ConexaoException e) {
			System.out.println(e);
		}
		
		
		// criar uma condição para que verifique se retorno de update de pfDAO e alterar de endC se eh true, caso contrario status é false
		
		return status;
		
	}
	
	
	public boolean existeCPF(String cpf) {
		
		boolean existe = false;
		
		try {
			existe = pfDAO.verificaCpf(cpf);
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		return existe;
	}
	
}