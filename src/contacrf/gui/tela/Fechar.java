package contacrf.gui.tela;

import java.util.Optional;

import contacrf.DAO.PessoaFisicaDAO;
import contacrf.controller.ContaCorrenteController;
import contacrf.exception.ConexaoException;
import contacrf.exception.ContaJaCadastradaException;
import contacrf.exception.ContaNãoCadastradaException;
import contacrf.exception.EnderecoNullPointerException;
import contacrf.exception.PessoaFisicaNullPointerException;
import contacrf.model.Agencia;
import contacrf.model.PessoaFisica;
import contacrf.util.MascaraDeFormatacao;
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
			PessoaFisicaDAO pfd = new PessoaFisicaDAO();
			Agencia agencia = new Agencia();
			Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
			GridPane cena = new GridPane();
			cena.setPadding(new Insets(20, 35, 20, 35));
			cena.setAlignment(Pos.TOP_CENTER);
			dialogoAviso.setTitle("Zathura Enterprise ™");
			dialogoAviso.setHeaderText("Você tem certeza que deseja bloquear a conta?");
			Separator separator = new Separator();

			ContaCorrenteController cc = new ContaCorrenteController();
			
			try {
				pf = pfd.getByCpf(busca.getCPF()); // CONTEM CPF
			} catch (ConexaoException e) {
				Erro erro = new Erro(e.getMessage());
				erro.handle(null);
			} catch (PessoaFisicaNullPointerException e) {
				Erro erro = new Erro(e.getMessage());
				erro.handle(null);
			} catch (EnderecoNullPointerException e) {
				Erro erro = new Erro(e.getMessage());
				erro.handle(null);
			}

			VBox vb = new VBox(10);
			vb.getChildren().addAll(new Label("Dados"), separator);
			HBox hb1 = new HBox(10);
			hb1.getChildren().addAll(new Label(pf.getNome()),new Label("CPF "+pf.getCpf()));
			HBox hb2 = new HBox(10);
			String numContaFormatado = MascaraDeFormatacao.formatar("#####-#", cc.getNumeroConta(pf.getCpf()));
			hb2.getChildren().addAll(new Label("Numero " + numContaFormatado),new Label("Agencia " +agencia.getNumero()));
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

 
				try {
					cc.bloquearConta(cc.getNumeroConta(pf.getCpf()));
					dialogoAviso2.setHeaderText("BLOQUEADO COM SUCESSO!");
					dialogoAviso2.setContentText("");
				} catch (ConexaoException e) {
					dialogoAviso2.setHeaderText(e.getMessage());
					dialogoAviso2.setContentText("");
				} catch (ContaNãoCadastradaException e) {
					dialogoAviso2.setHeaderText(e.getMessage());
					dialogoAviso2.setContentText("");
				} catch (ContaJaCadastradaException e) {
					dialogoAviso2.setHeaderText(e.getMessage());
					dialogoAviso2.setContentText("");
				}
				dialogoAviso2.getButtonTypes().setAll(btOk);
				dialogoAviso2.show();
			}
		}
	}
}