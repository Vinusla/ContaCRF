
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;




import contacrf.gui.*;
import contacrf.gui.tela.*;
import contacrf.model.Conta;
import contacrf.model.Endereco;
import contacrf.model.PessoaFisica;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application{
	public static void main(String[] args)throws Exception{
		Application.launch(); // INICIA START
	}

	public void start(Stage stage) throws Exception {
		Botoes bot = new Botoes();
		Imagem img = new Imagem();

		// TELA PRINCIPAL
		HBox VBM = new HBox(20);
		HBox HB1 = new HBox(10);
		VBM.setTranslateY(10); // ESPA�O DA TELA AO LADO
		VBM.setAlignment(Pos.TOP_LEFT);
		HB1.setAlignment(Pos.TOP_RIGHT);
		VBox VB1 = new VBox(5);
		VBox VB2 = new VBox(5);
		VBox VB3 = new VBox(5);
		VBox VB4 = new VBox(5);
		VB1.getChildren().addAll(img.getImgCad(),bot.getCad());
		VB2.getChildren().addAll(img.getImgRel(),bot.getRelatorio());
		VB3.getChildren().addAll(img.getImgCli(),bot.getCli());
		//VB1.getChildren().addAll(img.getImgCad(),bot.getCad());
		HB1.getChildren().addAll(VB1,VB2,VB3,VB4);
		VBM.getChildren().addAll(img.getImgLogo(),HB1); // ADD BOTOES
		Scene scene = new Scene(VBM, 550, 200);
		stage.setTitle("Zathura Enterprise �");
		stage.setScene(scene);
		stage.show();
		// BUTTONS MENU
		bot.getCad().setOnAction(new Cadastro()); // BUTTON CADASTRAR
		bot.getCli().setOnAction(new Cliente()); // BUTTON CLIENTE
		bot.getRelatorio().setOnAction(new Relatorio());
		
		
		
		
		/** EntityManagerFactory emf = Persistence.createEntityManagerFactory("contacrf");
	     EntityManager em = emf.createEntityManager();
	     
	     em.getTransaction().begin();
	     
		
	    Endereco end = new Endereco("ali", "jp", 64);
				
		PessoaFisica pe = new PessoaFisica();
		pe.setNome("Vinicius");
		pe.setCPF("58585858855");
		pe.setEndereco(end);
		
		
		Conta c = new Conta();
		c.setNumero("111");
		c.setSaldo(10.5f);
		c.setTitular(pe);
		
		em.persist(end);
		em.persist(pe);
		em.persist(c);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		**/
		
		
		
		
	}
}