package contacrf.gui.tela;

import contacrf.DAO.EnderecoDAO;
import contacrf.DAO.Per;
import contacrf.DAO.PessoaFisicaDAO;
import contacrf.exception.ConexaoException;
import contacrf.exception.EnderecoNullPointerException;
import contacrf.exception.PessoaFisicaNullPointerException;
import contacrf.gui.Botoes;
import contacrf.model.PessoaFisica;
import contacrf.model.Tabela;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

public class Relatorio extends Tabela implements EventHandler<ActionEvent> {

	
	
	
	public void handle(ActionEvent evento) {
		
		
		
		
		Per per = new Per();
		Buscar busca = new Buscar();
		PessoaFisica pf = new PessoaFisica();
		PessoaFisicaDAO pfd = new PessoaFisicaDAO();
		EnderecoDAO endd = new EnderecoDAO();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Zathura Enterprise ™");
		alert.setHeaderText("Relatiorio realizado com sucesso");

		busca.handle(null);
		if (busca.isAcho()) {
			try {
				pf = pfd.getByCpf(busca.getCPF()); // VAI CONTER CPF
				pf.setEndereco(endd.getByEndereco(pf.getId_end()));
				per.createCsvFile(pf);
				// per.createCsvFile(); TEM Q ALTERAR PARA RECEBER OS DADOS
				// CERTOS
				// SALDO DA CONTA
				// NUMERO DA CONTA
				alert.showAndWait();
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
		}
	}
}