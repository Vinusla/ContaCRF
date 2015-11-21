package contacrf.controller;

import contacrf.DAO.PessoaFisicaDAO;
import contacrf.exception.ConexaoException;
import contacrf.model.PessoaFisica;

public class PessoaFisicaController {

	private PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
	private PessoaFisica pf = new PessoaFisica("Mateus C","1235",null,"555555","MASCULINO","22/33/55");

	public void gravar() {
		try {
			pfDAO.save(pf);
		} catch (ConexaoException e) {
		}
	}
}