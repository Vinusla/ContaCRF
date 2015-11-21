package contacrf.gui.tela;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Erro implements EventHandler<ActionEvent>{
	private String erro;

	public Erro(String erro) {
		this.erro = erro;
	}
	public void handle(ActionEvent evento) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Zathura Enterprise ™");
    	alert.setHeaderText("ERRO!!");
    	alert.setContentText(erro);
    	alert.showAndWait();
	}
}