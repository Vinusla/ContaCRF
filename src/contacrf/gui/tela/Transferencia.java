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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Transferencia implements EventHandler<ActionEvent>{

	public void handle(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		GridPane cena = new GridPane();
		alert.setTitle("Zathura Enterprise ™");
		alert.setHeaderText("Verifique os dados");
		cena.setPadding(new Insets(20,35,20,35));

		Dialog<ButtonType> dialog = new Dialog<>();
		TextField txt1= new TextField();
		TextField txt2= new TextField();
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setHeaderText("Informe os dados");
		txt1.setPrefWidth(80);
		txt1.setPromptText("XXX.XXX-X");
		txt2.setPrefWidth(80);
		txt2.setPromptText("XXX.XXX-X");
		HBox hb = new HBox(10);
		hb.getChildren().addAll(new Label("Numero do titular"),txt1);
		Separator separador = new Separator();
		Separator separador1 = new Separator();
		HBox hb2 = new HBox(10);
		hb2.getChildren().addAll(new Label("Numero de destinatorio"),txt2);
		HBox hbS = new HBox(10);
		PasswordField password = new PasswordField();
		password.setPrefWidth(80);
		password.setPromptText("6 DIGITOS");
		hbS.getChildren().addAll(new Label("Senha"),password);
		VBox vb = new VBox(10);
		vb.getChildren().addAll(hb,new Label("Agencia   6585-X"),separador,hb2,new Label("Agencia   6585-X"),separador1,hbS);
		dialog.getDialogPane().setContent(vb);

		ButtonType buttonTypeT = new ButtonType("Transferir", ButtonData.OK_DONE);
		ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeT,buttonTypeV);
		dialog.showAndWait().ifPresent(ok->{
			if(ok == buttonTypeT){
				String scan;
				double valor=11;
				scan = password.getText();
				System.out.println("Senha "+scan);

				// if( conta.password.equals(scan)){
					PessoaFisica pfT = new PessoaFisica();
					PessoaFisica pfD = new PessoaFisica();
					PessoaFisicaDAO pfd = new PessoaFisicaDAO();
					PessoaFisicaController pfc = new PessoaFisicaController();
		/*			try {
						pfT = pfd.getByCpf("4444");
						pfD = pfd.getByCpf("4444");
					} catch (ConexaoException e) {
						e.printStackTrace();
					}
		 */
					HBox hb3 = new HBox(10);
					hb3.getChildren().addAll(new Label("Numero do titular 4651-X"), new Label("Agencia 6585-X"));
					HBox hb4 = new HBox(10);
					//hb4.getChildren().addAll(new Label(pfT.getNome()));
					HBox hb5 = new HBox(10);
					hb5.getChildren().addAll(new Label("Numero de destinatorio 8885-X"), new Label("Agencia 6585-X"));
					HBox hb6 = new HBox(40);
					//hb6.getChildren().addAll(new Label(pfD.getNome()));

					HBox hbs = new HBox(40);
					hbs.getChildren().addAll(new Label("Saldo " + valor));
					cena.add(hb3, 0, 0);
					cena.add(hb4, 0, 1);
					cena.add(separador, 0, 2);
					cena.add(hb5, 0, 3);
					cena.add(hb6, 0, 4);
					cena.add(hbs, 1, 5);
					alert.getDialogPane().setContent(cena);

					ButtonType buttonV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
					alert.getDialogPane().getButtonTypes().addAll(buttonV);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						//if( pfTsaldo >= valor){				// REALIZAR OPERAÇAO
						InfoOk info = new InfoOk("Saldo", 20);	// conta.getSaldo
						info.handle(null);
					} else { //if( pfTsaldo < valor){
						Erro erro = new Erro("Saldo insuficiente!");
						erro.handle(null);
					}
				//}else {
					Erro erro = new Erro("Senha invalida");
					erro.handle(null);
			}
		});;
	}
}