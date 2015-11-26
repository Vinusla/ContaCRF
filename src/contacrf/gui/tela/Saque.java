package contacrf.gui.tela;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Saque implements EventHandler<ActionEvent>{
	double valor = 1000.00;
	public void handle(ActionEvent evento) {
		Buscar busca = new Buscar();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		GridPane cena = new GridPane();
		alert.setTitle("Zathura Enterprise ™");
		alert.setHeaderText("Verifique os dados");
		cena.setPadding(new Insets(20,35,20,35));

		busca.handle(null);
		if (busca.isAcho()) {

			HBox hb = new HBox(10);
			hb.getChildren().addAll(new Label("Numero de conta 4651-X"), new Label("Agencia 45613-X"));

			HBox hb1 = new HBox(10);
			hb1.getChildren().addAll(new Label(busca.getNome()));

			HBox hbs = new HBox(40);
			hbs.getChildren().addAll(new Label("Saldo " + valor));
			cena.add(hb, 0, 0);
			cena.add(hb1, 0, 1);
			cena.add(hbs, 1, 2);
			alert.getDialogPane().setContent(cena);

			ButtonType buttonV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
			alert.getDialogPane().getButtonTypes().addAll(buttonV);
			alert.showAndWait().ifPresent(ok -> {
				if (ok != buttonV) {
					InfoOk info = new InfoOk("Saldo", 20);
					info.handle(null);
				} else {
					Erro erro = new Erro("Saldo insuficiente!");
					erro.handle(null);
				}
			});
		}
	}
}