package contacrf.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import contacrf.conexao.ConnectionFactory;
import contacrf.exception.ConexaoException;
import contacrf.model.ContaCorrente;

public class ContaCorrenteDAO {

	private Connection conexao = null;

	public void abrir(String numeroConta, String cpfCiente, String senha) throws ConexaoException{
		// Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			throw new ConexaoException(	"Não foi possível abrir a conexão com o banco");
		}

		PreparedStatement stmt;
		String sql = "insert into conta (num_conta, saldo, senha, cpf_pesF, ativo) values (?,?,?,?,?)";

		try {
			if(cpfCiente != ""){
				//Preparando para inserção no banco
				stmt = this.conexao.prepareStatement(sql);

				stmt.setString(1, numeroConta);
				stmt.setFloat(2, 0.0f);
				stmt.setString(3, senha);
				stmt.setString(4, cpfCiente);
				stmt.setString(5, String.valueOf('0'));	//converte char em String

				// executando
				stmt.execute();
				stmt.close();
			}

		} catch (SQLException e) {
			throw new ConexaoException("Não foi possível preparar o banco para a inserção do registro na tabela conta");
		} finally {
			// Fechando a conexão com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"Não foi possível fechar a conexão com o banco");
			}
		}
	}

	// Retorna um objeto ContaCorrente atraves do numero da conta
	public ContaCorrente getByConta(String numConta) throws ConexaoException {

		// Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		ContaCorrente cc = null;
		PreparedStatement stmt = null;
		String sql = "select * from conta where num_conta = " + numConta;

		try {
			// preparando a busca dentro do banco de dados
			stmt = this.conexao.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				cc = new ContaCorrente();
				cc.setNumero(rs.getString("num_conta"));
				cc.setSaldo(rs.getFloat("saldo"));
				cc.setSenha(rs.getString("senha"));
				cc.setCpfCliente(rs.getString("cpf_pesF"));
				cc.setAtivo(rs.getString("ativo").charAt(0));//converte String para char
			}
		} catch (SQLException e) {
			throw new ConexaoException(	"Não foi possível preparar o banco para a a busca de dados pelo numero da conta na tabela conta");
		} finally {

			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("Não foi possível fechar a busca de conta corrente pelo numero da conta");
			}
				// Fechando a conexão com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"Não foi possível fechar a conexão com o banco");
			}
		}
		return cc;
	}

	
	// Retorna o numero da conta de acordo com o cpf passado por parâmetro
		public String getByNumConta(String cpfCliente) throws ConexaoException {

			// Abrindo a conexão com o banco
			try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
			}catch (ConexaoException e) {
			System.out.println(e);
			}

			String numConta = null;
			PreparedStatement stmt = null;
			String sql = "select * from conta where cpf_pesF = " + cpfCliente;

			try {
				// preparando a busca dentro do banco de dados
				stmt = this.conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					if(rs.getString("cpf_pesF").equals(cpfCliente))
						numConta = rs.getString("num_conta");
				}
				
				} catch (SQLException e) {
					throw new ConexaoException(	"Não foi possível preparar o banco para a a busca de dados pelo numero do cpf na tabela conta");
				} finally {
		
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new ConexaoException("Não foi possível fechar a busca de conta corrente pelo numero do cpf");
				}
					// Fechando a conexão com o banco
				try {
					if (conexao != null)
						ConnectionFactory.getInstance().fecharConexao();
				} catch (ConexaoException e) {
					throw new ConexaoException(	"Não foi possível fechar a conexão com o banco");
				}
			}
				return numConta;
		}
	
	//Altera is dadis da conta do cliente
	public boolean update(ContaCorrente cc) throws ConexaoException{
		// Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		PreparedStatement stmt = null;
		boolean status = false;
		String sql = "update conta set num_conta = ?, saldo = ?, senha = ?, cpf_pesF = ?, ativo = ?  where num_conta = ?";

		try {
			stmt = this.conexao.prepareStatement(sql);

			stmt.setString(1, cc.getNumero());
			stmt.setFloat(2, cc.getSaldo());
			stmt.setString(3, cc.getSenha());
			stmt.setString(4, cc.getCpfCliente());
			stmt.setString(5, String.valueOf(cc.getAtivo()));//converte o tipo char em String
			stmt.setString(6, cc.getNumero());

			stmt.execute();

		} catch (SQLException e) {
			throw new ConexaoException("Não foi possível realizar a alteração objeto Conta Corrente");
		}finally{

			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("Não foi possível fechar a ateração de Conta Corrente");			}

			// Fechando a conexão com o banco
				try {
					if (conexao != null)
						ConnectionFactory.getInstance().fecharConexao();
				} catch (ConexaoException e) {
					throw new ConexaoException(	"Não foi possível fechar a conexão com o banco");
				}

		}
		return status;
	}

	public List<ContaCorrente> listALL() throws ConexaoException{

		// Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		List contas = new ArrayList<ContaCorrente>();

		PreparedStatement stmt = null;
		String sql = "select * from conta";

		try {
			// preparando a busca dentro do banco de dados
			stmt = this.conexao.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ContaCorrente cc = new ContaCorrente();

				cc.setNumero(rs.getString("num_conta"));
				cc.setSaldo(rs.getFloat("saldo"));
				cc.setSenha(rs.getString("senha"));
				cc.setCpfCliente(rs.getString("cpf_pesF"));
				cc.setAtivo(rs.getString("ativo").charAt(0));//converte String para char

				contas.add(cc);
			}
		} catch (SQLException e) {
			throw new ConexaoException(	"Não foi possível preparar o banco para buscar as contas");
		} finally {

			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("Não foi possível fechar a busca de contas");
			}
						// Fechando a conexão com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"Não foi possível fechar a conexão com o banco");
			}
		}
		return contas;
	}

	//Bloquea a conta do cliente inserinfo 0 com campo Ativo
	public boolean bloquear(String numConta) throws ConexaoException{

		ContaCorrente cc = null;
		boolean status = false;

		if(verificaConta(numConta)){
			cc = getByConta(numConta);
			cc.setAtivo('0');
			update(cc);
		}else
			throw new ConexaoException("Conta não encontrado");
		return status;
	}

	//Veirifica se a conta existe
	public boolean verificaConta(String numConta) throws ConexaoException{

		// Abrindo a conexão com o banco
		try {
			this.conexao = ConnectionFactory.getInstance().getConnection();
		} catch (ConexaoException e) {
			System.out.println(e);
		}

		PreparedStatement stmt = null;
		boolean existe = false;
		String sql = "select num_conta from conta";

		try {

			// preparando a busca dentro do banco de dados
			stmt = this.conexao.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				if (numConta.equals(rs.getString("num_conta")))
					existe = true;
			}

			stmt.close();

		} catch (SQLException e) {
			throw new ConexaoException(	"Não foi possível preparar o banco para a  busca de dados pelo numero da conta na tabela conta");
		} finally {

			try {
				stmt.close();
			} catch (SQLException e) {
				throw new ConexaoException("Não foi possível fechar a verificao se conta existe usando numero da conta");
			}

			// Fechando a conexão com o banco
			try {
				if (conexao != null)
					ConnectionFactory.getInstance().fecharConexao();
			} catch (ConexaoException e) {
				throw new ConexaoException(	"Não foi possível fechar a conexão com o banco");
			}
		}
		return existe;
	}
}