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

	private Connection conexao;

	public EnderecoDAO() {
		// Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}
	}

	// Inserir no banco de dados
	public int save(Endereco endereco) throws ConexaoException {

		PreparedStatement stmt;
		String sql = "insert into endereco (rua, numero, bairro,CEP,complemento,estado) values (?,?,?,?,?,?)";
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
			String msg = "Não foi possível preparar o banco para a inserção";
			Erro erro = new Erro(msg);
			erro.handle(null);
			throw new ConexaoException(msg);
		}
		return id;
	}

	// Retorna um objeto Endereço atraves do id passando por parâmetro
	public Endereco getByEndereco(final int id) {
		return null;
	}
}