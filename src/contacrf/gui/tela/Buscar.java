package contacrf.gui.tela;

import contacrf.model.AutoCompleteComboBoxListener;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;

public class Buscar implements EventHandler<ActionEvent>{
	private String nome ;
	private boolean acho = false;
	public String getNome() {
		return nome;
	}
	public boolean isAcho() {
		return acho;
	}
	List<String> list = new ArrayList<String>();
	public void handle(ActionEvent evento) {
		Dialog<ButtonType> dialog = new Dialog<>();
		ComboBox<String> combobox = new ComboBox<>();;
		list.add("Fulano");
        list.add("Ciclano");
        list.add("Beltrano");
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Informe o CPF");
		combobox.getItems().addAll(list);
		new AutoCompleteComboBoxListener<>(combobox);
		HBox hb = new HBox(10);
		hb.getChildren().addAll(combobox);
		dialog.getDialogPane().setContent(hb);

		ButtonType buttonTypeB = new ButtonType("Buscar", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeB,buttonTypeV);

		dialog.showAndWait().ifPresent(ok->{
			if(ok == buttonTypeB ){
				if (combobox.getValue() != null){
					nome = combobox.getValue();
					System.out.println(nome);
					this.acho = true;
				}
				else {
					Erro erro = new Erro("Cliente não encontrado!");
					erro.handle(null);
					this.acho = false;
				}
			}
		});
	}
}