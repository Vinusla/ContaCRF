package contacrf.gui.tela;

import java.util.Optional;

import contacrf.DAO.PessoaFisicaDAO;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
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

public class Saque implements EventHandler<ActionEvent>{
	double valor = 1000.00;
	public void handle(ActionEvent evento) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		GridPane cena = new GridPane();
		alert.setTitle("Zathura Enterprise ™");
		alert.setHeaderText("Verifique os dados");
		cena.setPadding(new Insets(20,35,20,35));

		Dialog<ButtonType> dialog = new Dialog<>();
		TextField txt1= new TextField();
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Informe os dados");
		txt1.setPrefWidth(80);
		txt1.setPromptText("XXX.XXX-X");
		HBox hb = new HBox(10);
		hb.getChildren().addAll(new Label("Numero"),txt1);
		HBox hb1 = new HBox(10);
		hb1.getChildren().addAll(new Label("Agencia   6585-X"));
		VBox vb = new VBox(10);
		vb.getChildren().addAll(hb,hb1);
		dialog.getDialogPane().setContent(vb);

		ButtonType buttonTypeB = new ButtonType("Buscar", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeB,buttonTypeV);
		dialog.showAndWait().ifPresent(ok->{
			if (ok == buttonTypeB) {
				String scan;
				scan = txt1.getText();

				// if( conta.password.equals(scan)){
					PessoaFisica pf = new PessoaFisica();
					PessoaFisicaDAO pfd = new PessoaFisicaDAO();
					PessoaFisicaController pfc = new PessoaFisicaController();
					try {
						pf = pfd.getByCpf("4444");
					} catch (ConexaoException e) {
						e.printStackTrace();
					}

					HBox hb2 = new HBox(10);
					hb2.getChildren().addAll(new Label("Numero de conta 4651-X"), new Label("Agencia 45613-X"));
					HBox hb3 = new HBox(10);
					hb3.getChildren().addAll(new Label(pf.getNome()));
					HBox hbs = new HBox(40);
					hbs.getChildren().addAll(new Label("Saldo " + valor));
					cena.add(hb2, 0, 0);
					cena.add(hb3, 0, 1);
					cena.add(hbs, 1, 2);
					alert.getDialogPane().setContent(cena);

					ButtonType buttonV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
					alert.getDialogPane().getButtonTypes().addAll(buttonV);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						//if( saldo >= saque){				// REALIZAR OPERAÇAO
						InfoOk info = new InfoOk("Saldo", 20); // conta.getSaldo
						info.handle(null);
					} else {
						Erro erro = new Erro("Saldo insuficiente!");
						erro.handle(null);
					}
				//}else {
					Erro erro = new Erro("Senha invalida");
					erro.handle(null);
			}
		});
	}
}