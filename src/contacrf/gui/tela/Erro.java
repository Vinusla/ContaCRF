package contacrf.gui.tela;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Erro implements EventHandler<ActionEvent>{
	private boolean erro;
	// true - JA EXISTE
	// false - NAO ENCONTRADO
	public Erro(boolean erro) {
		this.erro = erro;
	}
	public void handle(ActionEvent evento) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Zathura Enterprise ™");
    	alert.setHeaderText("ERRO!!");
    	if(erro)
    		alert.setContentText("Conta já existe no sistema!");
    	else alert.setContentText("Conta não existe no sistema!");
    	alert.showAndWait();
	}
}