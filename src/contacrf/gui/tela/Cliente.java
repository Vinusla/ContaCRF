package contacrf.gui.tela;

import contacrf.gui.Botoes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;

public class Cliente implements EventHandler<ActionEvent>{
	public void handle(ActionEvent evento) {
		Botoes bot = new Botoes();
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Selecione o cliente");
		HBox hb = new HBox(10);
		hb.getChildren().add(bot.getListaCli());
		dialog.getDialogPane().setContent(hb);
		ButtonType buttonTypeE = new ButtonType("Editar", ButtonData.OK_DONE);
		ButtonType buttonTypeD = new ButtonType("Deletar", ButtonData.OK_DONE);
		ButtonType buttonTypeEx = new ButtonType("Exibir", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeE,buttonTypeD,buttonTypeEx,buttonTypeV);
		dialog.showAndWait().ifPresent(ok->{
			if(ok == buttonTypeE)		// DEVE GERAR RESULTADO

			if(ok == buttonTypeD)

			if(ok == buttonTypeEx){

			}
		});
	}
}
