package contacrf.gui.tela;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InfoOk implements EventHandler<ActionEvent>{	// TELA PARA CONFIRMAÇÃO
	public void handle(ActionEvent evento) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(" Enterprise ™");
		alert.setHeaderText("Realizado com sucesso!");
		alert.setContentText("");
		alert.show();
	}
}