package contacrf.controller;

import java.util.ArrayList;
import java.util.List;

import contacrf.DAO.PessoaFisicaDAO;
import contacrf.exception.ConexaoException;
import contacrf.exception.EnderecoNullPointerException;
import contacrf.gui.tela.Erro;
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

	public void gravar(PessoaFisica pf) throws ConexaoException, EnderecoNullPointerException {

		try {
			if(!existeCPF(pf.getCpf()))
				pfDAO.save(pf);
			else
				erro.handle(null);
		} catch (ConexaoException e) {
			throw new ConexaoException("N�o foi poss�vel preparar o banco para a inser��o do registro na tabela pessoafisica");
		} catch (EnderecoNullPointerException e) {
			throw new EnderecoNullPointerException("Objeto do tipo Endere�o n�o pode ser criado");
		}
	}

	public PessoaFisica exibir(String cpf) throws ConexaoException{
		PessoaFisica pf = null;

		try {
			pf = this.pfDAO.getByCpf(cpf);
		} catch (ConexaoException e) {
			throw new ConexaoException("N�o foi poss�vel buscar Pessoa f�sica");
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

	public boolean existeCPF(String cpf) throws ConexaoException {
		boolean existe = false;

		try {
			existe = pfDAO.verificaCpf(cpf);
		} catch (ConexaoException e) {
			throw new ConexaoException(	"N�o foi poss�vel preparar o banco para a a busca de dados pelo cpf na tabela pessoafisica");
		}
		return existe;
	}
}