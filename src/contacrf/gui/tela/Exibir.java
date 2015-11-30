package contacrf.gui.tela;

import contacrf.DAO.EnderecoDAO;
import contacrf.DAO.PessoaFisicaDAO;
import contacrf.controller.ContaCorrenteController;
import contacrf.exception.ConexaoException;
import contacrf.gui.Botoes;
import contacrf.gui.tela.Editar;
import contacrf.model.Agencia;
import contacrf.model.Endereco;
import contacrf.model.PessoaFisica;
import contacrf.util.MascaraDeFormatacao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Exibir implements EventHandler<ActionEvent> {

	public void handle(ActionEvent event) {
		Buscar busca = new Buscar();
		busca.handle(null);
		if (busca.isAcho()) {
			Endereco end = new Endereco();
			EnderecoDAO endd = new EnderecoDAO();
			PessoaFisica pf = new PessoaFisica();
			Agencia agencia = new Agencia();
			PessoaFisicaDAO pfd = new PessoaFisicaDAO();
			ContaCorrenteController cc = new ContaCorrenteController();

			try {
				pf = pfd.getByCpf(busca.getCPF()); // CONTEM CPF
				end = endd.getByEndereco(pf.getId_end());
			} catch (ConexaoException e) {
				Erro erro = new Erro("Cliente não existe no sistema!!");
				erro.handle(null);
			}
			Botoes bot = new Botoes();
			Dialog<ButtonType> dialog = new Dialog<ButtonType>();
			GridPane cena = new GridPane();
			dialog.setTitle("Dados");
			dialog.setHeaderText("Dados do cliente");
			cena.setAlignment(Pos.TOP_CENTER);
			cena.setHgap(10);
			cena.setVgap(10);
			cena.setPadding(new Insets(20, 35, 20, 35));
			HBox hb1 = new HBox(10);
			hb1.getChildren().addAll(new Label("Nome"), bot.getTf1(), new Label("RG"), bot.getTf11());
			HBox hb2 = new HBox(10);
			hb2.getChildren().addAll(new Label("Sexo"), bot.getCbsexo(), new Label("Data nascimento"), bot.getTf2());
			HBox hb3 = new HBox(10);
			hb3.getChildren().addAll(new Label("CPF"), bot.getTf3(), new Label("Fone"), bot.getTf9());
			HBox hb4 = new HBox(10);
			hb4.getChildren().addAll(new Label("\nEndereço"));
			Separator separadorHorizontal = new Separator();
			Separator separadorHorizontal1 = new Separator();
			HBox hb5 = new HBox(10);
			hb5.getChildren().addAll(new Label("Rua"), bot.getTf4(), new Label("Cidade"), bot.getTf10());
			HBox hb6 = new HBox(10);
			hb6.getChildren().addAll(new Label("Complemento"), bot.getTf6(), new Label("Bairro"), bot.getTf7());
			HBox hb7 = new HBox(10);
			hb7.getChildren().addAll(new Label("Estado"), bot.getCbest(), new Label("CEP"), bot.getTf8(),
					new Label("Num"), bot.getTf5());
			HBox hb8 = new HBox(10);
			String numContaFormatado = MascaraDeFormatacao.formatar("#####-#", cc.getNumeroConta(pf.getCpf()));
			hb8.getChildren().addAll(new Label("Numero "+ numContaFormatado),new Label("Agencia " + agencia.getNumero()));
			cena.add(hb1, 0, 0);
			cena.add(hb2, 0, 1);
			cena.add(hb3, 0, 2);
			cena.add(hb4, 0, 3);
			cena.add(separadorHorizontal, 0, 4);
			cena.add(hb5, 0, 5);
			cena.add(hb6, 0, 6);
			cena.add(hb7, 0, 7);
			cena.add(hb8, 0, 9);
			cena.add(separadorHorizontal1, 0, 8);

			bot.getTf1().setText(pf.getNome()); // NOME
			bot.getTf2().setText(pf.getDataNasc()); // DATA NASCIMENTO
			bot.getTf3().setText(pf.getCpf()); // CPF
			bot.getTf4().setText(end.getRua()); // RUA
			bot.getTf5().setText(Integer.toString(end.getNumero())); // NUM
			bot.getTf6().setText(end.getComplemento()); // COMPLEMENTO
			bot.getTf7().setText(end.getBairro()); // BAIRRO
			bot.getTf8().setText(end.getCEP()); // CEP
			bot.getTf9().setText(pf.getTelefone()); // TELEFONE
			bot.getTf10().setText(end.getCidade()); // CIDADE
			bot.getTf11().setText(pf.getRg()); // RG
			bot.setCbsexo(pf.getSexo());
			bot.setCbest(end.getEstado()); // ESTADO

			dialog.getDialogPane().setContent(cena);
			ButtonType buttonE = new ButtonType("Editar", ButtonData.OK_DONE);
			ButtonType buttonOK = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(buttonE,buttonOK);
			dialog.showAndWait().ifPresent(ok->{
				Editar editar = new Editar();
				if(ok == buttonE)
					editar.handle(null);
			});
		}
	}
}