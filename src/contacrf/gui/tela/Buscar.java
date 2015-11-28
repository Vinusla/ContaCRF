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
	private String nome ; 			// TEST
	private boolean acho = false;
	public String getNome() {
		return nome;
	}
	public boolean isAcho() {
		return acho;
	}
	List<String> list = new ArrayList<String>();
	public void handle(ActionEvent evento) {			// RECEBER LISTA DE PESSOA
		Dialog<ButtonType> dialog = new Dialog<>();
		ComboBox<String> combobox = new ComboBox<>();;
		list.add("12345678911");

		dialog.setTitle("Zathura Enterprise �");
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
				Erro erro = new Erro("Cliente n�o encontrado!");
				if (combobox.getValue() != null){
					for (String string : list) {
						if(string == combobox.getValue()){
							nome = combobox.getValue();
							System.out.println(nome);
							this.acho = true;
						}
					}
					if(!acho){
						erro.handle(null);
						this.acho = false;
					}
				}
				else {
					erro.handle(null);
					this.acho = false;
				}
			}
		});
	}
}