package contacrf.controller;

import contacrf.DAO.PessoaFisicaDAO;
import contacrf.exception.ConexaoException;
import contacrf.model.PessoaFisica;

public class PessoaFisicaController {

	private PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
	private PessoaFisica pf = new PessoaFisica("vinvi", "000", null);

	
	
	
	public void gravar() {

		try {
			pfDAO.save(pf);
		} catch (ConexaoException e) {
			
		}

	}

}
