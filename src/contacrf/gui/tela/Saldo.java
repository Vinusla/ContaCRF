package contacrf.gui.tela;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Saldo implements EventHandler<ActionEvent>{

	public void handle(ActionEvent event) {
		Buscar busca = new Buscar();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		GridPane cena = new GridPane();
		alert.setTitle("Zathura Enterprise ™");
		alert.setHeaderText("Verifique os dados");
		cena.setPadding(new Insets(20,35,20,35));

		Dialog<ButtonType> dialog = new Dialog<>();
		TextField txt1= new TextField();
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Informe os dados");
		txt1.setPrefWidth(80);
		txt1.setPromptText("XXX.XXX-X");
		HBox hb = new HBox(10);
		hb.getChildren().addAll(new Label("Numero"),txt1);
		HBox hb1 = new HBox(10);
		hb1.getChildren().addAll(new Label("Agencia   6585-X"));	// TROCAR PELO O REAL NUMERO
		VBox vb = new VBox(10);
		vb.getChildren().addAll(hb,hb1);
		dialog.getDialogPane().setContent(vb);

		ButtonType buttonTypeB = new ButtonType("Buscar", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeB,buttonTypeV);
		dialog.showAndWait().ifPresent(ok->{
			if(ok == buttonTypeB){

			}
		});
	}
}