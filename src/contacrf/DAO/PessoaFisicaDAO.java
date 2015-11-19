package contacrf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import contacrf.conexao.ConnectionFactory;
import contacrf.exception.ConexaoException;
import contacrf.model.Endereco;
import contacrf.model.PessoaFisica;

public class PessoaFisicaDAO {

	private Connection conexao = null;
	private EnderecoDAO endereco;
	EnderecoDAO endDAO;
		

	
	public PessoaFisicaDAO() {

		//Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}
		
		endDAO = new EnderecoDAO();

	}

	// Inserir no banco de dados
	public void save(PessoaFisica pf) throws ConexaoException {
		
		PreparedStatement stmt;
		int idEnd = 0;
		
		String sql = "insert into pessoafisica (cpf, nome, id_end) values (?,?,?)";
		
		
		try {
			
			//vai salvar endereco no banco e retornar o id do mesmo
			idEnd = endDAO.save(pf.getEndereco()); 
			
			
			//Preparando para inserção  dos dados no banco
			stmt = conexao.prepareStatement(sql);
			
			
			//setando os valores
			stmt.setString(1, pf.getCPF());
			stmt.setString(2, pf.getNome());
			stmt.setInt(3, idEnd);
			
			//executando 
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new ConexaoException("Não foi possível preparar o banco para a inserção");
		}
		
		//Fechando a conexão com o banco
		try {
			if (conexao != null)
				ConnectionFactory.getInstance().fecharConexao();
			;
		} catch (ConexaoException e) {
			System.out.println(e);
		}

	}

	// Retorna um objeto PessoaFisca atraves do id passando por parâmetro
	public PessoaFisica getById(final int id) {
		
		return null;
	}

}
