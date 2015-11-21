package contacrf.gui.tela;

import contacrf.gui.Botoes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Cadastro implements EventHandler<ActionEvent>{
	public void handle(ActionEvent evento) {
		Botoes bot = new Botoes();

		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		GridPane cena = new GridPane();
		dialog.setTitle("Cadastrar");
		dialog.setHeaderText(null);
		dialog.setResizable(true);
		cena.setAlignment(Pos.TOP_CENTER);
		cena.setHgap(10);
		cena.setVgap(10);
		cena.setPadding(new Insets(20,35,20,35));
		bot.setCbsexo("MASCULINO");
		HBox hb1 = new HBox(10);
		hb1.getChildren().addAll(new Label("Nome"),bot.getTf1());
		HBox hb2 = new HBox(10);
		hb2.getChildren().addAll(new Label("Sexo"),bot.getCbsexo(),new Label("Telefone"),bot.getTf9());
		HBox hb3 = new HBox(10);
		hb3.getChildren().addAll(new Label("Data nascimento"),bot.getTf2(),new Label("CPF"),bot.getTf3());
		HBox hb4 = new HBox(10);
		hb4.getChildren().addAll(new Label("Endereço"),bot.getTf4(),new Label("Num"),bot.getTf5());
		HBox hb5 = new HBox(10);
		hb5.getChildren().addAll(new Label("Complemento"),bot.getTf6(),new Label("Bairro"),bot.getTf7());
		HBox hb6 = new HBox(10);
		hb6.getChildren().addAll(new Label("Estado"),bot.getCbest(),new Label("CEP"),bot.getTf8());
		cena.add(hb1, 0, 0);
		cena.add(hb2, 0, 1);
		cena.add(hb3, 0, 2);
		cena.add(hb4, 0, 3);
		cena.add(hb5, 0, 4);
		cena.add(hb6, 0, 5);
		dialog.getDialogPane().setContent(cena);

		//CONFIG BOTOES DO CANTO
			ButtonType buttonTypeOk = new ButtonType("Cadastrar", ButtonData.OK_DONE);
			ButtonType buttonTypeC = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk,buttonTypeC);
		dialog.showAndWait().ifPresent(ok->{// EXIBE TELA E RECEBE VALORES DAS CAIXAS
			if(buttonTypeOk == ok){
				String scan;

				scan = bot.getTf1().getText(); 		// NOME
				System.out.println(scan);
				scan = bot.getCbsexo().getValue();	// SEXO
				System.out.println(scan);
				scan = bot.getTf9().getText();		// TELEFONE
				System.out.println(scan);
				scan = bot.getTf2().getText();		// DATA
				System.out.println(scan);
				scan = bot.getTf3().getText();		// CPF
				System.out.println(scan);
				scan = bot.getTf4().getText();		// ENDEREÇO
				System.out.println(scan);
				scan = bot.getTf5().getText();		// NUMERO
				System.out.println(scan);
				scan = bot.getCbest().getValue();	// ESTADO
				System.out.println(scan);
				scan = bot.getTf6().getText();		// COMPLEMENTO
				System.out.println(scan);
				scan = bot.getTf7().getText();		// BAIRRO
				System.out.println(scan);
				scan = bot.getTf8().getText();		// CEP
				System.out.println(scan);
				/*_.setNome(scan);
				*/
			}
		});
	}
}