package contacrf.gui.tela;

import java.text.DecimalFormat;
import java.util.Optional;

import contacrf.controller.ContaCorrenteController;
import contacrf.controller.PessoaFisicaController;
import contacrf.model.Agencia;
import contacrf.model.ContaCorrente;
import contacrf.model.PessoaFisica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Transferencia implements EventHandler<ActionEvent> {

	public void handle(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		GridPane cena = new GridPane();
		alert.setTitle("Zathura Enterprise ™");
		alert.setHeaderText("Verifique os dados");
		cena.setPadding(new Insets(20, 35, 20, 35));

		Dialog<ButtonType> dialog = new Dialog<>();
		TextField txt1 = new TextField();
		TextField txt2 = new TextField();
		TextField txtV = new TextField();
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Informe os dados");
		txt1.setPrefWidth(80);
		txt1.setPromptText("XXX.XXX-X");
		txt2.setPrefWidth(80);
		txt2.setPromptText("XXX.XXX-X");
		txtV.setPrefWidth(80);
		txtV.setPromptText("Valor");
		HBox hb = new HBox(10);
		hb.getChildren().addAll(new Label("Numero do titular"), txt1);
		Separator separador = new Separator();
		Separator separador1 = new Separator();
		HBox hb2 = new HBox(10);
		hb2.getChildren().addAll(new Label("Numero de destinatorio"), txt2);
		HBox hbS = new HBox(10);
		PasswordField password = new PasswordField();
		password.setPrefWidth(80);
		password.setPromptText("6 DIGITOS");
		hbS.getChildren().addAll(new Label("Senha"), password);
		HBox hbV = new HBox(10);
		hbV.getChildren().addAll(new Label("Valor"),txtV);
		VBox vb = new VBox(10);
		vb.getChildren().addAll(hb, new Label("Agencia   6585-X"), separador, hb2, new Label("Agencia   6585-X"),
				separador1,hbV,hbS);
		dialog.getDialogPane().setContent(vb);

		ButtonType buttonTypeT = new ButtonType("Transferir", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeT, buttonTypeV);
		dialog.showAndWait().ifPresent(ok -> {
			if (ok == buttonTypeT) {
				
				if(txt1.getText().equals("") || txt2.getText().equals("") || txtV.getText().equals("") || password.getText().equals("")){
					Erro erro = new Erro("Todos os campos devem ser preenchidos!");
					erro.handle(null);
					return;
				}else{
				
				
					PessoaFisicaController pfc = new PessoaFisicaController();
					ContaCorrenteController cc = new ContaCorrenteController();
					ContaCorrente contaO = new ContaCorrente();
					ContaCorrente contaD = new ContaCorrente();
					ContaCorrente contaAux = new ContaCorrente();
					PessoaFisica pfO = new PessoaFisica();
					PessoaFisica pfD = new PessoaFisica();
					Agencia agencia = new Agencia();
					float valor = Float.parseFloat(txtV.getText());
					
					if(valor<0){
						Erro erro = new Erro("Valor de transferencia invalido!");
						erro.handle(null);
						return;
					}
					
					try {
						contaAux = cc.exibir(txt1.getText());
						if ('1' == contaAux.getAtivo()){
							contaO = contaAux;
							pfO = pfc.exibir(contaO.getCpfCliente());
						}else{
							Erro erro = new Erro("Conta origem bloqueada!");
							erro.handle(null);
							return;
						}
						contaAux = cc.exibir(txt2.getText());
						if ('1' == contaAux.getAtivo()){
							contaD = cc.exibir(txt2.getText());
							pfD = pfc.exibir(contaD.getCpfCliente());
						}else{
							Erro erro = new Erro("Conta destino bloqueada!");
							erro.handle(null);
							return;
						}
					} catch (Exception e) {
						Erro erro = new Erro("Conta nao existe!");
						erro.handle(null);
						return;
					}
					String scan;
					scan = password.getText();
	
					if (contaO.getSenha().equals(scan)) {
						HBox hb3 = new HBox(10);
						hb3.getChildren().addAll(new Label("Numero do titular "+contaO.getNumero()), new Label("Agencia " +agencia.getNumero()));
						HBox hb4 = new HBox(10);
						hb4.getChildren().addAll(new Label(pfO.getNome()));
						HBox hb5 = new HBox(10);
						hb5.getChildren().addAll(new Label("Numero de destinatorio "+contaD.getNumero()), new Label("Agencia " +agencia.getNumero()));
						HBox hb6 = new HBox(40);
						hb6.getChildren().addAll(new Label(pfD.getNome()));
	
						HBox hbs = new HBox(40);
						DecimalFormat df = new DecimalFormat("0.00");	// FORMATA SAIDA DE SALDO FLOAT
						String saldo = df.format(contaO.getSaldo());
						hbs.getChildren().addAll(new Label("Saldo " + saldo));
						cena.add(hb3, 0, 0);
						cena.add(hb4, 0, 1);
						cena.add(separador, 0, 2);
						cena.add(hb5, 0, 3);
						cena.add(hb6, 0, 4);
						cena.add(hbs, 1, 5);
						alert.getDialogPane().setContent(cena);
						ButtonType buttonTypeC = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
						alert.getDialogPane().getButtonTypes().add(buttonTypeC);
						Optional<ButtonType> result = alert.showAndWait();
						
						if (result.get() != buttonTypeC) {
							if( contaO.getSaldo() >= valor){ // REALIZAR OPERAÇAO
								try {
									contaO.tranferencia(contaO,contaD, valor);
								} catch (Exception e) {
									Erro erro = new Erro("Transação não realizada!");
									erro.handle(null);
								}
								InfoOk info = new InfoOk();
								info.handle(null);
							} else {
								Erro erro = new Erro("Saldo insuficiente!");
								erro.handle(null);
							}
						}
					}else{
						Erro erro = new Erro("Senha invalida");
						erro.handle(null);
					}
				}
			}
		});
	}
}