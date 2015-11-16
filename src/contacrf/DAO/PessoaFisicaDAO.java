package contacrf.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import contacrf.model.PessoaFisica;

public class PessoaFisicaDAO {

	private static PessoaFisicaDAO instance;
	protected EntityManager entityManager;

	public static PessoaFisicaDAO getInstance() {
		if (instance == null) {
			instance = new PessoaFisicaDAO();
		}

		return instance;
	}

	private PessoaFisicaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("contacrf");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	// Retorna um objeto PessoaFisca passando o id
	public PessoaFisica getById(final int id) {
		
		PessoaFisica ps = new PessoaFisica();

		try {

			ps = entityManager.find(PessoaFisica.class, id);

		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			getEntityManager().close();
		}

		return ps;
	}

	// Inserir no banco de dados
	public void save(PessoaFisica pf) {

		try {
			entityManager.getTransaction().begin();// inicia a transação
			entityManager.persist(pf); // persiste o objeto no banco
			entityManager.getTransaction().commit(); // finaliza a transação			
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();

		} finally {
			getEntityManager().close();
		}

	}

}
