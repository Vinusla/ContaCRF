package contacrf.gui.tela;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;

public class Editar implements EventHandler<ActionEvent>{
	public void handle(ActionEvent evento) {
		TextField txf = new TextField();
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Zathura Enterprise �");
		dialog.setHeaderText("Selecione o cliente");
		txf.setPrefWidth(200);
		txf.setPromptText("CPF");
		HBox hb = new HBox(10);
		hb.getChildren().addAll(txf);
		dialog.getDialogPane().setContent(hb);

		ButtonType buttonTypeB = new ButtonType("Buscar", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeB,buttonTypeV);

		dialog.showAndWait().ifPresent(ok->{
			if(ok == buttonTypeB){
				String scan;
				scan = txf.getText();
				System.out.println(scan);
			}
		});
	}
}