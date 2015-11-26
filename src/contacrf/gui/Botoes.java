package contacrf.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
	private TextField tf2 = new TextField(); // DATA NASCIMENTO
	private TextField tf3 = new TextField(); // CPF
	private TextField tf4 = new TextField(); // ENDEREÇO
	private TextField tf5 = new TextField(); // NUMERO
	private TextField tf6 = new TextField(); // COMPLEMENTO
	private TextField tf7 = new TextField(); // BAIRRO
	private TextField tf8 = new TextField(); // CEP
	private TextField tf9 = new TextField(); //	TELEFONE
	private TextField tf10 = new TextField(); // CIDADE
	private TextField tf11 = new TextField(); // RG
	//
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
	public TextField getTf10() {
		return tf10;
	}
	public TextField getTf11() {
		return tf11;
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
		this.tf1.setPrefWidth(185); // NOME
		this.tf2.setPrefWidth(95); // DATA NASCIMENTO
		this.tf2.setPromptText("DIA/MES/ANO");
		this.tf2.setTooltip(new Tooltip("DIA/MES/ANO"));
		this.tf3.setPrefWidth(110); // CPF
		this.tf3.setPromptText("XXX-XXX-XXX-XX");
		this.tf3.setTooltip(new Tooltip("XXX-XXX-XXX-XX"));
		this.tf4.setPrefWidth(180); // ENDEREÇO
		this.tf5.setPrefWidth(60); // NUMERO
		this.tf6.setPrefWidth(120); // COMPLEMENTO
		this.tf7.setPrefWidth(100); // BAIRRO
		this.tf8.setPrefWidth(100); // CEP
		this.tf8.setPromptText("XXXXX-XXX");
		this.tf8.setTooltip(new Tooltip("XXXXX-XXX"));
		this.tf9.setPrefWidth(127); // TELEFONE
		this.tf9.setPromptText("(DDD)9____-____");
		this.tf9.setTooltip(new Tooltip("(DDD)9____-____"));
		this.tf10.setPrefWidth(90);	// CIDADE
		this.tf11.setPrefWidth(90); // RG
		this.tf11.setPromptText("XXX-XXX-XXX");
		this.tf11.setTooltip(new Tooltip("XXX-XXX-XXX"));
	}
}