package contacrf.gui.tela;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InfoOk implements EventHandler<ActionEvent>{	// TELA PARA CONFIRMA��O
	private String msg = " ";

	public InfoOk(String msg,double saldo){ 	// SAQUE
		this.msg = msg +" "+ saldo;
	}

	public InfoOk(){

	}
	public void handle(ActionEvent evento) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Zathura Enterprise �");
		alert.setHeaderText("Realizado com sucesso!");
		alert.setContentText(msg);
		alert.show();
	}
}