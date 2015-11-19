package contacrf.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import contacrf.exception.ConexaoException;

public class ConnectionFactory {

	private static ConnectionFactory connectionFactory;
	private static Connection conexao = null;

	private ConnectionFactory() {}

	public static ConnectionFactory getInstance() {

		if (connectionFactory == null)
			connectionFactory = new ConnectionFactory();

		return connectionFactory;

	}

	public Connection getConnection() throws ConexaoException {

		if (conexao == null) {
			try {				
				conexao = DriverManager.getConnection("jdbc:mysql://localhost/contacrf", "root", "");
				System.out.println("Abrindo Conex�o");
			} catch (SQLException e) {
				throw new ConexaoException("N�o foi poss�vel abrir a conex�o com o banco");
			}
		}
		return conexao;
	}

	public void fecharConexao() throws ConexaoException {
		try {			
			conexao.close();
			System.out.println("Fechando Conex�o");
		} catch (SQLException e) {
			throw new ConexaoException(	"N�o foi poss�vel fechar a conex�o com o banco");
		}
	}
}
