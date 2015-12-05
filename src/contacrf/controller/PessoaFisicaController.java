package contacrf.controller;

import java.util.ArrayList;
import java.util.List;

import contacrf.DAO.PessoaFisicaDAO;
import contacrf.exception.ConexaoException;
import contacrf.exception.CpfJaCadastradaException;
import contacrf.exception.EnderecoNullPointerException;
import contacrf.exception.PessoaFisicaNullPointerException;
import contacrf.gui.tela.Erro;
import contacrf.model.PessoaFisica;

public class PessoaFisicaController {

	private PessoaFisicaDAO pfDAO;
	private EnderecoController endC;	
	
	public PessoaFisicaController(){
		this.pfDAO =  new PessoaFisicaDAO();
		this.endC = new EnderecoController();
		
	}

	public void gravar(PessoaFisica pf) throws ConexaoException, EnderecoNullPointerException, CpfJaCadastradaException {

		try {
			existeCPF(pf.getCpf());
			pfDAO.save(pf);			
		} catch (ConexaoException e) {
			throw new ConexaoException(e.getMessage());
		} catch (EnderecoNullPointerException e) {
			throw new EnderecoNullPointerException(e.getMessage());
		} catch (CpfJaCadastradaException e) {
			throw new CpfJaCadastradaException(e.getMessage());
		}
	}

	public PessoaFisica exibir(String cpf) throws ConexaoException, PessoaFisicaNullPointerException, EnderecoNullPointerException{
		PessoaFisica pf = null;

		try {
			pf = this.pfDAO.getByCpf(cpf);
		} catch (ConexaoException e) {
			throw new ConexaoException(e.getMessage());
		} catch (PessoaFisicaNullPointerException e) {
			throw new PessoaFisicaNullPointerException(e.getMessage());
		} catch (EnderecoNullPointerException e) {
			throw new EnderecoNullPointerException(e.getMessage());
		}
		return pf;
	}

	public List<PessoaFisica> listaPessoaFisica() throws ConexaoException{
		List<PessoaFisica> pessoas = new ArrayList<PessoaFisica>();

		try {
			pessoas = pfDAO.listAll();
		} catch (ConexaoException e) {
			throw new ConexaoException(	"N�o foi poss�vel para buscar as pessoas");
		}
		return pessoas;
	}

	public boolean alterar(PessoaFisica pf) throws ConexaoException{
		boolean status = false;

		try {
			status = this.pfDAO.update(pf);
			this.endC.alterar(pf.getEndereco());
		} catch (ConexaoException e) {
			Erro erro = new Erro("N�o foi poss�vel realizar a altera��o objeto pessoaFisica");
			erro.handle(null);
		}

		// criar uma condi��o para que verifique se retorno de update de pfDAO e alterar de endC se eh true, caso contrario status � false
		return status;
	}

	public boolean existeCPF(String cpf) throws ConexaoException, CpfJaCadastradaException {
		boolean existe = false;
		
		existe = pfDAO.verificaCpf(cpf);		
		
		
		return existe;
	}
}