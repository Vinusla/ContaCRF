package contacrf.gui.tela;

import java.util.ArrayList;
import java.util.List;

import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.model.AutoCompleteComboBoxListener;
import contacrf.model.PessoaFisica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
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
	List<PessoaFisica> listP = new ArrayList<PessoaFisica>();	// LISTA DE PESSOAS
	List<String> listC = new ArrayList<String>();				// LISTA DE CPF
	public void handle(ActionEvent evento) {
		Dialog<ButtonType> dialog = new Dialog<>();
		ComboBox<String> combobox = new ComboBox<>();
		PessoaFisicaController pfC = new PessoaFisicaController();
		try {
			listP = pfC.listaPessoaFisica();
		} catch (ConexaoException e) {
		}
		for (int i = 0; i < listP.size(); i++) {
			listC.add(listP.get(i).getCpf());
		}
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Informe o CPF");
		combobox.getItems().addAll(listC);
		new AutoCompleteComboBoxListener<>(combobox);
		HBox hb = new HBox(10);
		hb.getChildren().addAll(combobox);
		dialog.getDialogPane().setContent(hb);

		ButtonType buttonTypeB = new ButtonType("Buscar", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeB,buttonTypeV);

		dialog.showAndWait().ifPresent(ok->{
			if(ok == buttonTypeB ){
				Erro erro = new Erro("Cliente não encontrado!");
				if (combobox.getValue() != null){
					for (String string : listC) {
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