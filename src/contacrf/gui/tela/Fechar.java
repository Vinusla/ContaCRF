package contacrf.gui.tela;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import contacrf.DAO.ContaCorrenteDAO;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.model.Agencia;
import contacrf.model.Conta;
import contacrf.model.ContaCorrente;
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
			PessoaFisicaController pfc = new PessoaFisicaController();
			Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
			GridPane cena = new GridPane();
			cena.setPadding(new Insets(20, 35, 20, 35));
			cena.setAlignment(Pos.TOP_CENTER);
			dialogoAviso.setTitle("Zathura Enterprise ™");
			dialogoAviso.setHeaderText("Você tem certeza que deseja excluir todos os seus dados ?");
			Separator separator = new Separator();

			List<Conta> contaList = new ArrayList<Conta>();
			Conta aux = new ContaCorrente();
			Agencia agencia = new Agencia();
			for(int x = 0; x < contaList.size(); x++){
			    if(contaList.get(x).getCpfCliente() == busca.getNome()){
			       aux = contaList.get(x);
			    }
			}
			try {
				pf = pfc.exibir(busca.getNome());
			} catch (ConexaoException e) {
			}

			VBox vb = new VBox(10);
			vb.getChildren().addAll(new Label("Dados"), separator);
			HBox hb1 = new HBox(10);
			hb1.getChildren().addAll(new Label(pf.getNome()),new Label("CPF "+pf.getCpf()));
			HBox hb2 = new HBox(10);
			hb2.getChildren().addAll(new Label("Numero " +aux.getNumero()),new Label("Agencia " +agencia.getNumero()));
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
				Alert dialogoAviso2 = new Alert(Alert.AlertType.WARNING);
				dialogoAviso2.setTitle("Zathura Enterprise ™");

				// Excluindo
				ContaCorrenteDAO conta = new ContaCorrenteDAO();
				try {
					conta.bloquear(pf.getCpf());
					dialogoAviso2.setHeaderText("EXCLUIDO COM SUCESSO!");
					dialogoAviso2.setContentText("");
				} catch (ConexaoException e1) {
					dialogoAviso2.setHeaderText("Nao foi possivel!");
					dialogoAviso2.setContentText("");
				}

				dialogoAviso2.getButtonTypes().setAll(btOk);
				dialogoAviso2.show();
			}
		}
	}
}