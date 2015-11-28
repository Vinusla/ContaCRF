package contacrf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import contacrf.conexao.ConnectionFactory;
import contacrf.exception.ConexaoException;
import contacrf.gui.tela.Erro;
import contacrf.model.Endereco;

public class EnderecoDAO {

	private Connection conexao = null;
	private Endereco endereco;

	// Inserir no banco de dados
	//obs: não abro e nem fecho uma conexão pela razão do metodo save de pessoaFisicaDAO chamar esse metodo, logo o save de pessoaFisicaDAO já abre e fecha uma conexão
	public int save(Endereco endereco, Connection conexao) throws ConexaoException {

		PreparedStatement stmt;
		String sql = "insert into endereco (rua, numero, bairro,CEP,complemento,estado,cidade) values (?,?,?,?,?,?,?)";
		int id = 0;

		try {
			// Preparando para inserção dos dados no banco
			stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// setando os valores
			stmt.setString(1, endereco.getRua());
			stmt.setInt(2, endereco.getNumero());
			stmt.setString(3, endereco.getBairro());
			stmt.setString(4, endereco.getCEP());
			stmt.setString(5, endereco.getComplemento());
			stmt.setString(6, endereco.getEstado());
			stmt.setString(7, endereco.getCidade());
			// executando
			stmt.execute();

			// pesquisa os ids
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getInt(1); // retorna o ultimo id que foi inserido
			}

			// fechando
			stmt.close();

		} catch (SQLException e) {
			String msg = "Não foi possível preparar o banco para a inserção do registro na tabela endereco";
			Erro erro = new Erro(msg);
			erro.handle(null);
			throw new ConexaoException(msg);
		}

		return id;
	}

	// Retorna um objeto Endereço atraves do id
	public Endereco getByEndereco(final int id_end) throws ConexaoException {

		//Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			throw new ConexaoException("Não foi possível abrir a conexão com o banco");
		}

		endereco = new Endereco();

		PreparedStatement stmt = null;
		String sql = "select * from endereco where id = " + id_end;

		try {
			stmt = conexao.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				endereco.setId(rs.getInt("id"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getInt("numero"));
				endereco.setBairro(rs.getString("bairro"));
				endereco.setCEP(rs.getString("cep"));
				endereco.setComplemento(rs.getString("complemento"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setCidade(rs.getString("cidade"));
			}
		} catch (SQLException e) {
			throw new ConexaoException("Não foi possível realizar a busca do endereço");
		}finally{

			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("Não foi possível fechar a busca do endereço");
			}

			//Fechando a conexão com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"Não foi possível fechar a conexão com o banco");
			}
		}
		return endereco;

	}

	// remover um objeto Endereço atraves do id
	public boolean remove(final int id_end) throws ConexaoException{

		//Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			throw new ConexaoException("Não foi possível abrir a conexão com o banco");
		}

		boolean status = false;
		PreparedStatement stmt = null;
		String sql = "delete from endereco where id = ?";

		try {
			stmt = this.conexao.prepareStatement(sql);
			stmt.setInt(1, id_end);
			stmt.execute();
			status = true;
		} catch (SQLException e) {
			throw new ConexaoException("não foi possível remover o endereço");
		}finally{

			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("Não foi possível fechar remoção de endereco");
			}

			//Fechando a conexão com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"Não foi possível fechar a conexão com o banco");
			}
		}
		return status;
	}

	public boolean update(Endereco end) throws ConexaoException{

		//Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			throw new ConexaoException("Não foi possível abrir a conexão com o banco");
		}

		boolean status = false;
		PreparedStatement stmt = null;
		String sql = "update endereco set rua = ?, numero = ?, bairro = ?, cep = ?, complemento = ? , estado = ? where id = ?";

		try {
			stmt = this.conexao.prepareStatement(sql);

			stmt.setString(1, end.getRua());
			stmt.setInt(2, end.getNumero());
			stmt.setString(3, end.getBairro());
			stmt.setString(4, end.getCEP());
			stmt.setString(5, end.getComplemento());
			stmt.setString(6, end.getEstado());
			stmt.setInt(7, end.getId());

			stmt.execute();
			status = true;

		} catch (SQLException e) {
			throw new ConexaoException("Não foi possível alterar o endereço");
		}finally{

			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("Não foi possível fechar a ateração do endereço");
			}

			//Fechando a conexão com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException("Não foi possível fechar a conexão com o banco");
			}
		}

		return status;

	}
}