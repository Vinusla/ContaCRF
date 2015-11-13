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
		HBox hb2 = new HBox(10);
		hb1.getChildren().addAll(new Label("Nome"),bot.getTf1(),new Label("Sexo"),bot.getCbsexo());
		hb2.getChildren().addAll(new Label("Endereço"));
		cena.add(hb1, 0, 0);
		cena.add(hb2, 0, 1);
		dialog.getDialogPane().setContent(cena);

		//CONFIG BOTOES DO CANTO
			ButtonType buttonTypeOk = new ButtonType("Cadastrar", ButtonData.OK_DONE);
			ButtonType buttonTypeC = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk,buttonTypeC);
		dialog.showAndWait().ifPresent(ok->{// EXIBE TELA E RECEBE VALORES DAS CAIXAS

		});
	}
}