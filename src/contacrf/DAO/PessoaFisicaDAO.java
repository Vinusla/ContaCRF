package contacrf.DAO;

import contacrf.gui.tela.Erro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import contacrf.conexao.ConnectionFactory;
import contacrf.exception.ConexaoException;
import contacrf.exception.EnderecoNullPointerException;
import contacrf.model.PessoaFisica;

public class PessoaFisicaDAO {

	private Connection conexao = null;
	EnderecoDAO endDAO;

	public PessoaFisicaDAO() {

		endDAO = new EnderecoDAO();
	}

	// Inserir no banco de dados
	public void save(PessoaFisica pf) throws ConexaoException,	EnderecoNullPointerException {

		// Abrindo a conex�o com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			throw new ConexaoException(
					"N�o foi poss�vel abrir a conex�o com o banco");
		}

		PreparedStatement stmt;
		int idEnd = 0;

		String sql = "insert into pessoafisica (cpf, nome,telefone,sexo,dataNasc,id_end) values (?,?,?,?,?,?)";

		try {

			if (pf.getEndereco() != null) {

				// vai salvar endereco no banco e retornar o id do mesmo
				idEnd = endDAO.save(pf.getEndereco(), this.conexao);

				// Preparando para inser��o dos dados no banco
				stmt = conexao.prepareStatement(sql);

				// setando os valores
				stmt.setString(1, pf.getCPF());
				stmt.setString(2, pf.getNome());
				stmt.setString(3, pf.getTelefone());
				stmt.setString(4, pf.getSexo());
				stmt.setString(5, pf.getDataNasc());
				stmt.setInt(6, idEnd);

				// executando
				stmt.execute();
				stmt.close();
			}
		} catch (SQLException e) {
			String msg = "Conta j� existe no sistema!";
			Erro erro = new Erro(msg);
			erro.handle(null);
			throw new ConexaoException(
					"N�o foi poss�vel preparar o banco para a inser��o do registro na tabela pessoafisica");
		} catch (NullPointerException e) {
			throw new EnderecoNullPointerException(
					"Objeto do tipo Endere�o n�o pode ser criado");

		} finally {

			// Fechando a conex�o com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(
						"N�o foi poss�vel fechar a conex�o com o banco");
			}
		}
	}

	// Retorna um objeto PessoaFisca atraves do CPF
	public PessoaFisica getByCpf(String cpf) throws ConexaoException {

		// Abrindo a conex�o com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		PessoaFisica pf = null;		
		PreparedStatement stmt = null;
		String sql = "select * from pessoafisica where cpf = " + cpf;

		try {

			// preparando a busca dentro do banco de dados
			stmt = this.conexao.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				pf = new PessoaFisica();
				pf.setCpf(rs.getString("cpf"));
				pf.setNome(rs.getString("nome"));
				pf.setTelefone(rs.getString("telefone"));
				pf.setSexo(rs.getString("sexo"));
				pf.setDataNasc(rs.getString("dataNasc"));
				pf.setId_end(rs.getInt("id_end"));
			}

			

		} catch (SQLException e) {
			throw new ConexaoException(	"N�o foi poss�vel preparar o banco para a a busca de dados pelo cpf na tabela pessoafisica");
		} finally {
			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("N�o foi poss�vel fechar a busco de pessoa fisica por cpf");		
			}	

			// Fechando a conex�o com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"N�o foi poss�vel fechar a conex�o com o banco");
			}
		}

		// retorna o endere�o e seta em pessoaFisica
		try {
			pf.setEndereco(this.endDAO.getByEndereco(pf.getId_end()));
		} catch (ConexaoException e) {
			throw new ConexaoException("N�o foi poss�vel procurar o endereco");
		}

		return pf;
	}

	//remover o objeto pessoaFisica do banco de dados
	public boolean remove(PessoaFisica pf) throws ConexaoException {

		// Abrindo a conex�o com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		boolean status = false;
		PreparedStatement stmt = null;
		String sql = "delete from pessoafisica where cpf = ?";

		try {
			stmt = this.conexao.prepareStatement(sql);
			stmt.setString(1, pf.getCPF());
			stmt.execute();
			stmt.close();
			status = true;
		} catch (SQLException e) {
			throw new ConexaoException("N�o foi poss�vel remover o objeto pessoa fisica");
		} finally {

			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("N�o foi poss�vel fechar a remo��o de pessoa fisica");		
			}	
			
			// Fechando a conex�o com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"N�o foi poss�vel fechar a conex�o com o banco");
			}
		}
		
		return status;

	}
	
	
	
	
	public boolean update(PessoaFisica pf) throws ConexaoException{
		
		// Abrindo a conex�o com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}
		
		PreparedStatement stmt = null;
		boolean status = false;
		String sql = "update pessoafisica set cpf = ?, nome = ?, telefone = ?, sexo = ?, dataNasc = ?  where cpf = ?";
		
		
		try {
			stmt = this.conexao.prepareStatement(sql);
			
			
			stmt.setString(1, pf.getCPF());
			stmt.setString(2, pf.getNome());
			stmt.setString(3, pf.getTelefone());
			stmt.setString(4, pf.getSexo());
			stmt.setString(5, pf.getDataNasc());			
			stmt.setString(6, pf.getCPF());
			
			stmt.execute();
			
			
		} catch (SQLException e) {
			throw new ConexaoException("N�o foi poss�vel realizar a altera��o objeto pessoaFisica");			
		}finally{
			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("N�o foi poss�vel fechar a atera��o de pessoa fisica");			}			
			
			
			// Fechando a conex�o com o banco
				try {
					if (conexao != null)
						ConnectionFactory.getInstance().fecharConexao();
				} catch (ConexaoException e) {
					throw new ConexaoException(	"N�o foi poss�vel fechar a conex�o com o banco");
				}
			
		}
		
		return status;
		
	}

	// verifica se o cpf j� existe no banco de dados
	public boolean verificaCpf(String cpf) throws ConexaoException {

		// Abrindo a conex�o com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		PreparedStatement stmt = null;
		boolean existe = false;
		String sql = "select cpf from pessoafisica";

		try {

			// preparando a busca dentro do banco de dados
			stmt = this.conexao.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (cpf.equals(rs.getString("cpf")))
					existe = true;
			}

			stmt.close();

		} catch (SQLException e) {
			throw new ConexaoException(	"N�o foi poss�vel preparar o banco para a a busca de dados pelo cpf na tabela pessoafisica");
		} finally {
			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("N�o foi poss�vel fechar a verificao se pessoa fisica existe usando cpf");		
			}	

			// Fechando a conex�o com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"N�o foi poss�vel fechar a conex�o com o banco");
			}
		}

		return existe;

	}

}