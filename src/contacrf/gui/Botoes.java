package contacrf.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class Botoes {
	// BUTTON's MENU
		private final Button Cad = new Button (" Cadastro ");
		private final Button Sim = new Button("SIM");
		private final Button Nao = new Button("NÃO");
		private final Button Saldo = new Button("   Saldo    ");
		private final Button Relatorio = new Button(" Relatorio");
		private ComboBox<String> cbsexo = new ComboBox<>(); // 1
		private String sexo[] = {"MASCULINO","FEMININO"};
		private ComboBox<String> listaCli = new ComboBox<>(); // 1
	//CAIXAS
		private TextField tf1 = new TextField();
		private TextField tf2 = new TextField();
		private TextField tf3 = new TextField();
		private TextField tf4 = new TextField();

    //GET'S e SET's
	public Button getSim() {
		return Sim;
	}
	public Button getNao() {
		return Nao;
	}
	public Button getCad() {
		return Cad;
	}
	public Button getSaldo() {
		return Saldo;
	}
	public Button getRelatorio() {
		return Relatorio;
	}
	public TextField getTf1() {
		return tf1;
	}
	public TextField getTf2() {
		return tf2;
	}
	public TextField getTf3() {
		return tf3;
	}
	public TextField getTf4() {
		return tf4;
	}
	public ComboBox<String> getCbsexo() {
		return cbsexo;
	}
	public void setCbsexo(String cbsexo) {
		this.cbsexo.setValue(cbsexo);
	}
	public ComboBox<String> getListaCli() {
		return listaCli;
	}
	public void setListaCli(ComboBox<String> listaCli) {
		this.listaCli = listaCli;
	}

	public Botoes(){

	// CONFIG COMBOX SEXO
		this.cbsexo.getItems().addAll(sexo);
		this.listaCli.getItems().addAll("Mateus","Gustavo","Pedro","Vinicius");
	// CONFIG FONTE
		this.getCad().setFont(Font.font(16));
		this.getSaldo().setFont(Font.font(16));
		this.getRelatorio().setFont(Font.font(16));
	// TAMANHO DO TextField
		this.tf1.setPrefWidth(220);
   }
}