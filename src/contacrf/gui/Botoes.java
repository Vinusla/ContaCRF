package contacrf.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class Botoes {
	// BUTTON's MENU
		private final Button Cad = new Button (" Cadastrar ");
		private final Button Cli = new Button ("  Clientes  ");
		private final Button Excluir = new Button(" Deletar ");
		private final Button Editar = new Button(" Editar ");
		private final Button Saldo = new Button("   Saldo    ");
		private final Button Relatorio = new Button(" Relatorio ");
		private final ComboBox<String> cbsexo = new ComboBox<>(); // 1
		private final ComboBox<String> cbest = new ComboBox<>(); // 1
		private final String sexo[] = {"MASCULINO","FEMININO"};
		private final String estado[] = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA"
		,"MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
		private ComboBox<String> listaCli = new ComboBox<>(); // 1
	//CAIXAS
		private TextField tf1 = new TextField();	//NOME
		private TextField tf2 = new TextField();	//ENDEREÇO
		private TextField tf3 = new TextField();	//BAIRRO
		private TextField tf4 = new TextField();	//COMPLEMENTO
		private TextField tf5 = new TextField();	//NUMERO
		private TextField tf6 = new TextField();	//
		private TextField tf7 = new TextField();	//
		private TextField tf8 = new TextField();	//
			//DATA NASCIMENTO
			//CONTA
			//AGENCIA

    //GET'S e SET's
	public Button getEditar() {
		return Editar;
	}
	public Button getExcluir() {
		return Excluir;
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
	public Button getCli() {

		return Cli;
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
	public TextField getTf5() {
		return tf5;
	}
	public TextField getTf6() {
		return tf6;
	}
	public TextField getTf7() {
		return tf7;
	}
	public TextField getTf8() {
		return tf8;
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
	public ComboBox<String> getCbest() {
		return cbest;
	}

	public Botoes(){

	// CONFIG COMBOX
		this.cbsexo.getItems().addAll(this.sexo);
		this.cbest.getItems().addAll(this.estado);
		this.listaCli.getItems().addAll("Mateus","Gustavo","Pedro","Vinicius");
	// CONFIG FONTE
		this.getCad().setFont(Font.font(14));
		this.getRelatorio().setFont(Font.font(14));
		this.getCli().setFont(Font.font(14));
		this.getEditar().setFont(Font.font(14));
	// TAMANHO DO TextField
		this.tf1.setPrefWidth(220);		//NOME
		this.tf2.setPrefWidth(95);		//DATA NASCIMENTO
		this.tf2.setPromptText("DIA/MES/ANO");
		this.tf3.setPrefWidth(100);		//CPF
		this.tf4.setPrefWidth(150);		//ENDEREÇO
		this.tf5.setPrefWidth(50);		//NUMERO
		this.tf6.setPrefWidth(120);		//COMPLEMENTO
		this.tf7.setPrefWidth(80);		//BAIRRO
		this.tf8.setPrefWidth(80);		//CEP
	}
}