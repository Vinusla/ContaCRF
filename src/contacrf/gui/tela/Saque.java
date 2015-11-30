package contacrf.gui.tela;

import java.text.DecimalFormat;
import java.util.Optional;

import contacrf.DAO.PessoaFisicaDAO;
import contacrf.controller.ContaCorrenteController;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Saque implements EventHandler<ActionEvent>{

	public void handle(ActionEvent evento) {
		Agencia agencia = new Agencia();
		Alert alert = new Alert(AlertType.INFORMATION);
		GridPane cena = new GridPane();
		alert.setTitle("Zathura Enterprise ™");
		alert.setHeaderText("Verifique os dados");
		cena.setPadding(new Insets(20,35,20,35));

		Dialog<ButtonType> dialog = new Dialog<>();
		TextField txt1= new TextField();
		TextField txtV= new TextField();
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Informe os dados");
		txt1.setPrefWidth(80);
		txt1.setPromptText("XXXXX-X");
		txtV.setPrefWidth(80);
		txtV.setPromptText("Valor");
		HBox hb = new HBox(10);
		hb.getChildren().addAll(new Label("Numero"),txt1);
		HBox hb1 = new HBox(10);
		hb1.getChildren().addAll(new Label("Agencia "+ agencia.getNumero()));

		HBox hbS = new HBox(10);
		PasswordField password = new PasswordField();
		password.setPrefWidth(80);
		password.setPromptText("6 DIGITOS");
		hbS.getChildren().addAll(new Label("Senha"), password);
		HBox hbV = new HBox(10);
		hbV.getChildren().addAll(new Label("Valor"),txtV);

		VBox vb = new VBox(10);
		vb.getChildren().addAll(hb,hb1,hbV,hbS);
		dialog.getDialogPane().setContent(vb);



		ButtonType buttonTypeB = new ButtonType("Buscar", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeB,buttonTypeV);
		dialog.showAndWait().ifPresent(ok->{
			if (ok == buttonTypeB) {
				ContaCorrenteController cc = new ContaCorrenteController();
				ContaCorrente conta = new ContaCorrente();
				ContaCorrente contaAux = new ContaCorrente();
				PessoaFisica pf = new PessoaFisica();
				PessoaFisicaController pfc = new PessoaFisicaController();
				float valor = Float.parseFloat(txtV.getText());
				if(valor<0){
					Erro erro = new Erro("Valor de transferencia invalido!");
					erro.handle(null);
					return;
				}
				try {
					contaAux = cc.exibir(txt1.getText());
					if ('1' == contaAux.getAtivo()){
						conta = contaAux;
						pf = pfc.exibir(conta.getCpfCliente());
					}else{
						Erro erro = new Erro("Conta bloqueada!");
						erro.handle(null);
						return;
					}
				} catch (Exception e1) {
					Erro erro = new Erro("Conta nao existe!");
					erro.handle(null);
					return;
				}
				String scan;
				scan = password.getText();
				if( conta.getSenha().equals(scan)){
					HBox hb2 = new HBox(10);
					hb2.getChildren().addAll(new Label("Numero "+conta.getNumero()), new Label("Agencia "+agencia.getNumero()));
					HBox hb3 = new HBox(10);
					hb3.getChildren().addAll(new Label(pf.getNome()));
					HBox hbs = new HBox(40);
					DecimalFormat df = new DecimalFormat("0.00");	// FORMATA SAIDA DE SALDO FLOAT
					String saldo = df.format(conta.getSaldo());
					hbs.getChildren().addAll(new Label("Saldo " + saldo));
					cena.add(hb2, 0, 0);
					cena.add(hb3, 0, 1);
					cena.add(hbs, 1, 2);
					alert.getDialogPane().setContent(cena);

					ButtonType buttonV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
					alert.getDialogPane().getButtonTypes().addAll(buttonV);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() != buttonV) {
						if( conta.getSaldo() >= valor){				// REALIZAR OPERAÇAO
							try {
								conta.sacar(conta, valor);
							} catch (Exception e) {
								Erro erro = new Erro("Transação não realizada!");
								erro.handle(null);
							}
							InfoOk info = new InfoOk();
							info.handle(null);
						}else {
							Erro erro = new Erro("Saldo insuficiente!");
							erro.handle(null);
						}
					}
				}else {
					Erro erro = new Erro("Senha invalida");
					erro.handle(null);
				}
			}
		});
	}
}