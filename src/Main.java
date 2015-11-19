import contacrf.DAO.EnderecoDAO;
import contacrf.DAO.PessoaFisicaDAO;
import contacrf.exception.ConexaoException;
import contacrf.gui.*;
import contacrf.gui.tela.*;
import contacrf.model.Endereco;
import contacrf.model.PessoaFisica;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) throws ConexaoException {

		PessoaFisicaDAO pedao = new PessoaFisicaDAO();
		Endereco end = new Endereco("jose v", "fun2", 64);
		
		PessoaFisica pf = new PessoaFisica("vinicius", "111", end );		
		
		
		pedao.save(pf);
		
		
		Application.launch(); // INICIA START

	}

	public void start(Stage stage) throws Exception {
		Botoes bot = new Botoes();
		Imagem img = new Imagem();

		// TELA PRINCIPAL
		VBox VBM = new VBox(20);
		HBox HB1 = new HBox(10);
		VBM.setTranslateY(10); // ESPAÇO DA TELA AO LADO
		VBM.setAlignment(Pos.TOP_CENTER);
		HB1.setAlignment(Pos.CENTER);
		VBox VB1 = new VBox(5);
		VBox VB2 = new VBox(5);
		VBox VB3 = new VBox(5);
		VBox VBN = new VBox(10);
		VB1.getChildren().addAll(img.getImgCad(), bot.getCad());
		VB2.getChildren().addAll(img.getImgRel(), bot.getRelatorio());
		VB3.getChildren().addAll(img.getImgCli(), bot.getCli());
		// VB1.getChildren().addAll(img.getImgCad(),bot.getCad());
		HB1.getChildren().addAll(VB1, VB2, VB3);
		VBN.setAlignment(Pos.CENTER);
		VBN.getChildren().addAll(new Label("Desenvolvido por"),
				new Label("Mateus Cordeiro"), new Label("Pedro Fontes"),
				new Label("Gusttavo Heinrich"), new Label("Vinicius Lopes"));
		VBM.getChildren().addAll(img.getImgLogo(), HB1, VBN); // ADD BOTOES
		Scene scene = new Scene(VBM, 300, 490);
		stage.setTitle("Zathura Enterprise ™");
		stage.setScene(scene);
		stage.show();
		// BUTTONS MENU
		bot.getCad().setOnAction(new Cadastro()); // BUTTON CADASTRAR
		bot.getCli().setOnAction(new Cliente()); // BUTTON CLIENTE
		bot.getRelatorio().setOnAction(new Relatorio());

	}
}
