package contacrf.gui.tela;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import contacrf.gui.Botoes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Relatorio implements EventHandler<ActionEvent>{
	private final Set arrayData = new TreeSet<>();
	public void handle(ActionEvent evento) {
		Arrays.asList(arrayData);
		Botoes bot = new Botoes();
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Selecione o cliente");
		HBox hb = new HBox(10);
		hb.getChildren().add(bot.getListaCli());
		dialog.getDialogPane().setContent(hb);
		ButtonType buttonTypeR = new ButtonType("Relatorio", ButtonData.OK_DONE);
		ButtonType buttonTypeC = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeR,buttonTypeC);
		dialog.showAndWait().ifPresent(ok->{
			if(ok == buttonTypeR){		// DEVE GERAR RESULTADO
				String test;
				test = bot.getListaCli().getValue();

				GridPane cena = new GridPane();
				cena.add(new Label ("Nome " + test), 0, 1);
				dialog.setHeaderText("Relatorio do cliente");
				dialog.getDialogPane().setContent(cena);
				ButtonType buttonTypeOK = new ButtonType("OK", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().clear();
				dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOK);
				dialog.showAndWait();
			}
		});
	}

}