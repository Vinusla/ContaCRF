package contacrf.gui.tela;

import java.util.Optional;

import contacrf.controller.PessoaFisicaController;
import contacrf.model.Conta;
import contacrf.model.PessoaFisica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Fechar implements EventHandler<ActionEvent> {

	public void handle(ActionEvent evento) {
		Buscar busca = new Buscar();
		busca.handle(null);
		if (busca.isAcho()) {
			PessoaFisica pf = new PessoaFisica();
			Conta conta = new Conta(); 		// FALTA DIZER Q ESTA INATIVA A CONTA
			PessoaFisicaController pfc = new PessoaFisicaController();
			Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
			GridPane cena = new GridPane();
			cena.setPadding(new Insets(20, 35, 20, 35));
			cena.setAlignment(Pos.TOP_CENTER);
			dialogoAviso.setTitle("Zathura Enterprise ™");
			dialogoAviso.setHeaderText("Você tem certeza que deseja excluir todos os seus dados ?");

			Separator separator = new Separator();
			// separator.setPrefWidth(100);
			// Excluindo
			String cpf = "4444";
			pf = pfc.exibir(cpf);

			VBox vb = new VBox(10);
			vb.getChildren().addAll(new Label("Dados"), separator);
			HBox hb1 = new HBox(10);
			hb1.getChildren().addAll(new Label(pf.getNome()),new Label("CPF "+pf.getCpf()));
			HBox hb2 = new HBox(10);
			hb2.getChildren().addAll(new Label("Numero 4456-22"),new Label("Agencia 9658-X"));
			cena.add(vb, 0, 0);
			cena.add(hb1, 0, 1);
			cena.add(hb2, 0, 2);

			ButtonType btOk = new ButtonType("OK");
			ButtonType btSim = new ButtonType("Sim");
			ButtonType btVoltar = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
			dialogoAviso.getDialogPane().setContent(cena);
			dialogoAviso.getButtonTypes().setAll(btSim, btVoltar);
			Optional<ButtonType> result = dialogoAviso.showAndWait();
			if (result.get() == btSim) {
				// Excluindo
				pfc.excluir(pf);

				Alert dialogoAviso2 = new Alert(Alert.AlertType.WARNING);
				dialogoAviso2.setTitle("Zathura Enterprise ™");
				try {
					dialogoAviso2.setHeaderText("EXCLUIDO COM SUCESSO!");
					dialogoAviso2.setContentText("");
				} catch (Exception e) {
					dialogoAviso2.setHeaderText("Nao foi possivel!");
					dialogoAviso2.setContentText("");
				}
				dialogoAviso2.getButtonTypes().setAll(btOk);
				dialogoAviso2.show();
			}
		}
	}
}