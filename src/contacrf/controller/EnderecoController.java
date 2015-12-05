package contacrf.controller;

import contacrf.DAO.EnderecoDAO;
import contacrf.exception.ConexaoException;
import contacrf.exception.EnderecoNullPointerException;
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
		} catch (EnderecoNullPointerException e) {
			System.out.println(e);
		}
		return end;
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