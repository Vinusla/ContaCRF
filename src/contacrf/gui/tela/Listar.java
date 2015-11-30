package contacrf.gui.tela;

import java.util.ArrayList;
import java.util.List;

import contacrf.controller.ContaCorrenteController;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.model.PessoaFisica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Listar implements EventHandler<ActionEvent>{
	List<String> pfN = new ArrayList<String>();
	List<String> pfC = new ArrayList<String>();
	List<String> lc= new ArrayList<String>();

	public String toNome(){
		String n = "";
		pfN.add("Nome");
		for (String string : pfN) {
			n += string +"\n";
		}
		return n + "\n";
	}
	public String toCPF(){
		String c = "";
		for (String string : pfC) {
			c += string +"\n";
		}
		return c + "\n";
	}
	public String toConta() {
		String v = "";
		for ( String num : lc) {
			 v += num +"\n";
		}
		return v + "\n";
	}

	public void handle(ActionEvent event) {
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane, 550, 300, Color.WHITE);
		Stage stage = new Stage();
		stage.setTitle("Zathura Enterprise ™");
		pane.setPadding(new Insets(20,20,20,20));
		ContaCorrenteController cc = new ContaCorrenteController();
		PessoaFisicaController pfc = new PessoaFisicaController();
		PessoaFisica pf = new PessoaFisica();

		try {
			for (int i = 0; i < cc.listarContas().size(); i++) {
				if ('1' == cc.listarContas().get(i).getAtivo()) {
					lc.add(cc.listarContas().get(i).getNumero());
					pf = pfc.exibir(cc.listarContas().get(i).getCpfCliente());
					pfN.add(pf.getNome());
					pfC.add(pf.getCpf());
				}
			}
		} catch (ConexaoException e) {
			Erro erro = new Erro ("Carregamento de dados não concluido");
			erro.handle(null);
		}
		ObservableList<String> data = FXCollections.observableArrayList();
		ListView<String> listN = new ListView<String>(data); // LISTA NOME
	    listN.setPrefSize(200, 250);
	    data.addAll(pfN);
	    listN.setItems(data);

	    ObservableList<String> data1 = FXCollections.observableArrayList();
	    ListView<String> listCPF = new ListView<String>(data); // LISTA CPF
	    listCPF.setPrefSize(100, 250);
	    data1.addAll(pfC);
	    listCPF.setItems(data1);

	    ObservableList<String> data2 = FXCollections.observableArrayList();
	    ListView<String> listC = new ListView<String>(data);	//LISTA CONTA
	    listC.setPrefSize(100, 250);
	    data2.addAll(lc);
	    listC.setItems(data2);

	    VBox vbC = new VBox(10);
	    Label nome = new Label("Conta");
	    nome.setFont(Font.font("Courier New", FontWeight.THIN, 14));
	    vbC.getChildren().addAll(nome,listC);

	    VBox vbN = new VBox(10);
	    Label nome2 = new Label("Nome Completo");
	    nome2.setFont(Font.font("Courier New", FontWeight.THIN, 14));
	    vbN.getChildren().addAll(nome2,listN);

	    VBox vbCPF = new VBox(10);
	    Label nome3 = new Label("CPF");
	    nome3.setFont(Font.font("Courier New", FontWeight.THIN, 14));
	    vbCPF.getChildren().addAll(nome3,listCPF);

	    pane.setLeft(vbC); // CONTA
		pane.setCenter(vbN); // NOME
		pane.setRight(vbCPF); // CPF
		stage.setScene(scene);
		stage.showAndWait();
	}
}