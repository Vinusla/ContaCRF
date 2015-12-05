package contacrf.gui.tela;

import java.text.DecimalFormat;
import java.util.Optional;

import contacrf.controller.ContaCorrenteController;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.exception.ContaJaCadastradaException;
import contacrf.exception.ContaNãoCadastradaException;
import contacrf.exception.EnderecoNullPointerException;
import contacrf.exception.PessoaFisicaNullPointerException;
import contacrf.model.Agencia;
import contacrf.model.Conta;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Saldo implements EventHandler<ActionEvent>{

	public void handle(ActionEvent event) {
		Buscar busca = new Buscar();
		busca.handle(null);
		if (busca.isAcho()) {
			PessoaFisicaController pfc = new PessoaFisicaController();
			ContaCorrenteController cc = new ContaCorrenteController();
			Conta conta = new Conta();
			PessoaFisica pf = new PessoaFisica();
			Agencia agencia = new Agencia();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			GridPane cena = new GridPane();
			alert.setTitle("Zathura Enterprise ™");
			alert.setHeaderText("Verifique os dados");
			cena.setPadding(new Insets(20, 35, 20, 35));
			Dialog<ButtonType> dialog = new Dialog<>();
			TextField txt1 = new TextField();
			dialog.setTitle("Zathura Enterprise ™");
			dialog.setHeaderText("Informe os dados");
			txt1.setPrefWidth(80);
			txt1.setPromptText("XXX.XXX-X");

			try {
				pf = pfc.exibir(busca.getCPF());				
				conta = cc.exibir(cc.getNumeroConta(busca.getCPF()));
			} catch (ConexaoException e) {
				Erro erro = new Erro(e.getMessage());
				erro.handle(null);
			} catch (PessoaFisicaNullPointerException e) {
				Erro erro = new Erro(e.getMessage());
				erro.handle(null);
			} catch (EnderecoNullPointerException e) {
				Erro erro = new Erro(e.getMessage());
				erro.handle(null);
			} catch (ContaNãoCadastradaException e) {
				Erro erro = new Erro(e.getMessage());
				erro.handle(null);
			} catch (ContaJaCadastradaException e) {
				Erro erro = new Erro(e.getMessage());
				erro.handle(null);				
			}
			HBox hb = new HBox(10);
			hb.getChildren().addAll(new Label("Numero " + conta.getNumero()),new Label("Agencia "+ agencia.getNumero()));

			VBox vb = new VBox(10);
			DecimalFormat df = new DecimalFormat("0.00");	// FORMATA SAIDA DE SALDO FLOAT
			String saldo = df.format(conta.getSaldo());
			vb.getChildren().addAll(hb,new Label(pf.getNome()),new Label("Saldo " + saldo));
			dialog.getDialogPane().setContent(vb);

			ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().addAll(buttonTypeV);
			dialog.showAndWait();
		}
	}
}