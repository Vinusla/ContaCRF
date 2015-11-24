package contacrf.controller;

import contacrf.DAO.EnderecoDAO;
import contacrf.exception.ConexaoException;
import contacrf.model.Endereco;

public class EnderecoController {

	private EnderecoDAO endDAO;

	public EnderecoController() {

		this.endDAO = new EnderecoDAO();

	}

	public void gravar() {

	}

	
	
	public Endereco exibirEndereco(int id_end){
		
		Endereco end = null;
		
		try {
			end = this.endDAO.getByEndereco(id_end);
		} catch (ConexaoException e) {
			System.out.println(e);
		}
		
		return end;
	}
	
	public boolean remover(int id_end) {

		boolean status = false;

		try {
			status = this.endDAO.remove(id_end);
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		return status;
	}
	
	
	public boolean alterar(Endereco end){
		
		boolean status = false;
		
		
		try {
			this.endDAO.update(end);
		} catch (ConexaoException e) {
			System.out.println(e);
		}
		
		return status;
		
		
		
		
		
	}

}
