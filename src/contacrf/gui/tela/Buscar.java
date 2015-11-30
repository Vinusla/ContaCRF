package contacrf.gui.tela;

import java.util.ArrayList;
import java.util.List;

import contacrf.controller.ContaCorrenteController;
import contacrf.exception.ConexaoException;
import contacrf.model.AutoCompleteComboBoxListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;

public class Buscar implements EventHandler<ActionEvent>{
	private String CPF ; 			// TEST
	private boolean acho = false;
	public String getCPF() {
		return CPF;
	}
	public boolean isAcho() {
		return acho;
	}

	public List<String> contaAtivas() throws ConexaoException {
		List<String> contas = new ArrayList<String>();
		ContaCorrenteController cc = new ContaCorrenteController();
		for (int i = 0; i < cc.listarContas().size(); i++) {
			if('1' == cc.listarContas().get(i).getAtivo())
				contas.add(cc.listarContas().get(i).getCpfCliente());
		}
		return contas;
	}

	public void handle(ActionEvent evento) {
		Dialog<ButtonType> dialog = new Dialog<>();
		ComboBox<String> combobox = new ComboBox<>();
		List<String> listC = new ArrayList<String>();				// LISTA DE CPF

		try {
			listC.addAll(contaAtivas());
		} catch (ConexaoException e) {
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
							CPF = combobox.getValue();
							System.out.println(CPF);
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