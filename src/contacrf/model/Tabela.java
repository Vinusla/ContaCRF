package contacrf.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




import contacrf.controller.ContaCorrenteController;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.exception.EnderecoNullPointerException;
import contacrf.exception.PessoaFisicaNullPointerException;
import contacrf.gui.tela.Erro;
import contacrf.util.MascaraDeFormatacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


public class Tabela implements EventHandler<ActionEvent> {

	TableView<Pessoa> tabela = new TableView<Pessoa>();
	TableViewSelectionModel<Pessoa> tsm = tabela.getSelectionModel();
	//ObservableList<Integer> list = tsm.getSelectedIndices();
	ObservableList<Pessoa> list = tsm.getSelectedItems();
	TableColumn colunaNome = new TableColumn<>("CPF");
	TableColumn colunaIdade = new TableColumn<>("Nome");
	TableColumn colunaEmail = new TableColumn<>("Saldo");

	List<String> nomes = new ArrayList<String>();

	
	ContaCorrenteController cc = new ContaCorrenteController();
	PessoaFisicaController pfc = new PessoaFisicaController();
	PessoaFisica pf = new PessoaFisica();
	
	List<String> pfN = new ArrayList<String>();
	List<String> pfC = new ArrayList<String>();
	List<String> lc= new ArrayList<String>();
	
	
	public void handle(ActionEvent arg0) {

				
		
		try {
			for (int i = 0; i < cc.listarContas().size(); i++) {
				if ('1' == cc.listarContas().get(i).getAtivo()) {
					lc.add(MascaraDeFormatacao.formatar("#####-#", cc.listarContas().get(i).getNumero()));
					pf = pfc.exibir(cc.listarContas().get(i).getCpfCliente());
					pfN.add(pf.getNome());
					pfC.add( MascaraDeFormatacao.formatar("###.###.###-##", pf.getCpf()));
				}
			}			
			
			
		} catch (ConexaoException e) {
			Erro erro = new Erro (e.getMessage());
			erro.handle(null);
		} catch (PessoaFisicaNullPointerException e) {
			Erro erro = new Erro (e.getMessage());
			erro.handle(null);
		} catch (EnderecoNullPointerException e) {
			Erro erro = new Erro (e.getMessage());
			erro.handle(null);
		}
		
		
		
		
		List pessoas = Arrays.asList(new Pessoa("William", 32,
				"william@email.com"),
				new Pessoa("Luana", 17, "luana@email.com"), new Pessoa("Maria",
						12, "maria@email.com"), new Pessoa("João", 15,
						"joao@email.com"), new Pessoa("Antônio", 28,
						"antonio@email.com"), new Pessoa("Teles", 17,
						"teles@email.com"), new Pessoa("Eduan", 30,
						"eduan@email.com"), new Pessoa("Gabu", 22,
						"gabu@email.com"));
		
		tabela.setItems(FXCollections.observableArrayList(pessoas));


        VBox vBox = new VBox(10);

        vBox.getChildren().addAll(tabela);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Tabelas no JavaFX");
        dialog.getDialogPane().setContent(vBox);
        ButtonType buttonType = new ButtonType("New Record", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType);
		dialog.showAndWait().ifPresent(ok -> {
			if (ok == buttonType) {
				if (tsm.getSelectedItems().isEmpty()) {
					System.out.println("duhudhduh");
				} else {
					nomes.add(tabela.getSelectionModel().getSelectedItem().getNome());
				}

				for (Pessoa n : list) {
					System.out.println(n.getNome());
				}
				nomes.clear();
			}
		});
	}
	public Tabela() {
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
		colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tabela.getColumns().addAll(colunaNome, colunaIdade, colunaEmail);
		tabela.setPrefHeight(300);
		tabela.setPrefWidth(400);
	}
}