package contacrf.gui.tela;

import java.util.Optional;

import contacrf.controller.ContaCorrenteController;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.exception.ContaJaCadastradaException;
import contacrf.exception.ContaNãoCadastradaException;
import contacrf.exception.ValorNegativoException;
import contacrf.model.Agencia;
import contacrf.model.ContaCorrente;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Deposito implements EventHandler<ActionEvent>{
	private double valor;
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	public void handle(ActionEvent evento) {
		Agencia agencia = new Agencia();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		TextField txfN = new TextField();
		TextField txfV = new TextField();

		txfV.setPromptText("Valor para deposito");
		txfN.setPrefWidth(100);
		txfV.setPrefWidth(20);
		HBox hb = new HBox(10);
		hb.getChildren().addAll(new Label("Numero"),txfN);
		VBox vb = new VBox(10);
		vb.getChildren().addAll(new Label("Agencia " +agencia.getNumero()),hb,txfV);
		alert.getDialogPane().setContent(vb);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			
			if(txfN.getText().equals("") || txfV.getText().equals("")){
				Erro erro = new Erro("Todos o campos devem ser preenchido!");
				erro.handle(null);
			}else{
			
				ContaCorrenteController cc = new ContaCorrenteController();
				ContaCorrente conta = new ContaCorrente();
				String numero =txfN.getText();
				
				
				try {
					cc.existeConta(numero);
					float valor = Float.parseFloat(txfV.getText());
					conta = cc.exibir(numero);
					if (conta.getNumero().equals(numero) || valor > 0) {
						conta.deposito(conta, valor);
						InfoOk info = new InfoOk("Valor depositado ", valor);
						info.handle(null);
					}
				} catch (ConexaoException e) {
					Erro erro = new Erro(e.getMessage());
					erro.handle(null);
				} catch (ContaNãoCadastradaException e) {
					Erro erro = new Erro(e.getMessage());
					erro.handle(null);
				} catch (ContaJaCadastradaException e) {
					Erro erro = new Erro(e.getMessage());
					erro.handle(null);
				}catch (ValorNegativoException e) {
					Erro erro = new Erro(e.getMessage());
					erro.handle(null);
				}
			}
		}
	}
}