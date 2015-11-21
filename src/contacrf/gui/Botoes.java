package contacrf.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class Botoes {
	// BUTTON's MENU
	private final Button Deposito = new Button(" Deposito ");
	private final Button Saldo = new Button(" Saldo ");
	private final Button Transferencia = new Button(" Transferencia ");
	private final ComboBox<String> cbsexo = new ComboBox<>(); // 1
	private final ComboBox<String> cbest = new ComboBox<>(); // 1
	private final String sexo[] = { "MASCULINO", "FEMININO" };
	private final String estado[] = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
			"PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };
	private ComboBox<String> listaCli = new ComboBox<>(); // 1
	// BUTTON's ESPECIAL
	// private final Button

	// CAIXAS
	private TextField tf1 = new TextField(); // NOME
	private TextField tf2 = new TextField(); //
	private TextField tf3 = new TextField(); // ENDEREÇO
	private TextField tf4 = new TextField(); // BAIRRO
	private TextField tf5 = new TextField(); // COMPLEMENTO
	private TextField tf6 = new TextField(); // NUMERO
	private TextField tf7 = new TextField(); //
	private TextField tf8 = new TextField(); //
	private TextField tf9 = new TextField(); //
	// DATA NASCIMENTO
	// CONTA
	// AGENCIA

	// GET'S e SET's
	public Button getDeposito() {
		return Deposito;
	}
	public Button getSaldo() {
		return Saldo;
	}
	public Button getTransferencia() {
		return Transferencia;
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
	public TextField getTf9() {
		return tf9;
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

	public Botoes() {

		// CONFIG COMBOX
		this.cbsexo.getItems().addAll(this.sexo);
		this.cbest.getItems().addAll(this.estado);
		this.listaCli.getItems().addAll("Mateus", "Gustavo", "Pedro", "Vinicius");
		// CONFIG FONTE
		this.getTransferencia().setFont(Font.font(14));
		this.getDeposito().setFont(Font.font(14));
		this.getSaldo().setFont(Font.font(14));
		// TAMANHO DO TextField
		this.tf1.setPrefWidth(290); // NOME
		this.tf2.setPrefWidth(95); // DATA NASCIMENTO
		this.tf2.setPromptText("DIA/MES/ANO");
		this.tf3.setPrefWidth(100); // CPF
		this.tf4.setPrefWidth(170); // ENDEREÇO
		this.tf5.setPrefWidth(60); // NUMERO
		this.tf6.setPrefWidth(120); // COMPLEMENTO
		this.tf7.setPrefWidth(80); // BAIRRO
		this.tf8.setPrefWidth(90); // CEP
		this.tf9.setPrefWidth(117); // TELEFONE
		this.tf9.setPromptText("(DDD)9____-____");
	}
}