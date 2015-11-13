

import contacrf.gui.*;
import contacrf.gui.tela.*;
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
		HBox princ = new HBox(20);
		VBox VB = new VBox(10);
		princ.setTranslateY(10); // ESPAÇO DA TELA AO LADO
		princ.setAlignment(Pos.TOP_LEFT);
		VB.setAlignment(Pos.TOP_RIGHT);
		VB.getChildren().addAll(bot.getCad(),bot.getSaldo(),bot.getRelatorio());
		princ.getChildren().addAll(img.getImgLogo(),VB); // ADD BOTOES
		Scene scene = new Scene(princ, 350, 200);
		stage.setTitle(" Enterprise ™");
		stage.setScene(scene);
		stage.show();
		// BUTTONS MENU
		bot.getCad().setOnAction(new Cadastro()); // BUTTON CADASTRAR
		bot.getSaldo().setOnAction(new Erro(false)); // BUTTON SALDO TESTE
		bot.getRelatorio().setOnAction(new Relatorio());
	}
}