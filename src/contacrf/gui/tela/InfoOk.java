package contacrf.gui.tela;

import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InfoOk implements EventHandler<ActionEvent>{	// TELA PARA CONFIRMAÇÃO
	private String msg = " ";

	public InfoOk(String msg,float valor){ 	// OPERAÇOES
		DecimalFormat df = new DecimalFormat("0.00");	// FORMATA SAIDA DE SALDO FLOAT
		String saldo = df.format(valor);
		this.msg = msg +" " + saldo;
	}

	public InfoOk(){

	}
	public void handle(ActionEvent evento) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Zathura Enterprise ™");
		alert.setHeaderText("Realizado com sucesso!");
		alert.setContentText(msg);
		alert.show();
	}
}