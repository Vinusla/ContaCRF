package contacrf.gui.tela;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import contacrf.DAO.EnderecoDAO;
import contacrf.DAO.PessoaFisicaDAO;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.gui.Botoes;
import contacrf.model.Conta;
import contacrf.model.Endereco;
import contacrf.model.PessoaFisica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Editar implements EventHandler<ActionEvent>{
	List<String> list = new ArrayList<String>(); // TEST

	public void handle(ActionEvent evento) {
		Buscar busca = new Buscar();
		busca.handle(null);
		if (busca.isAcho()) {
			Endereco end = new Endereco();
			EnderecoDAO endd = new EnderecoDAO();
			PessoaFisica pf = new PessoaFisica();
			PessoaFisicaDAO pfd = new PessoaFisicaDAO();
			PessoaFisicaController pfc = new PessoaFisicaController();
			Botoes bot = new Botoes();
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setTitle("Zathura Enterprise ™");
			dialog.setHeaderText("Selecione o cliente");
			GridPane cena = new GridPane();
			cena.setAlignment(Pos.TOP_CENTER);
			cena.setHgap(10);
			cena.setVgap(10);
			cena.setPadding(new Insets(20,35,20,35));
			HBox hb1 = new HBox(10);
			hb1.getChildren().addAll(new Label("Nome"),bot.getTf1(),new Label("RG"),bot.getTf11());
			HBox hb2 = new HBox(10);
			hb2.getChildren().addAll(new Label("Sexo"),bot.getCbsexo(),new Label("Data nascimento"),bot.getTf2());
			HBox hb3 = new HBox(10);
			hb3.getChildren().addAll(new Label("CPF"),bot.getTf3(),new Label("Fone"),bot.getTf9());
			HBox hb4 = new HBox(10);
			hb4.getChildren().addAll(new Label("\nEndereço"));
			Separator separadorHorizontal = new Separator();
			Separator separadorHorizontal1 = new Separator();
			HBox hb5 = new HBox(10);
			hb5.getChildren().addAll(new Label("Rua"),bot.getTf4(),new Label("Cidade"),bot.getTf10());
			HBox hb6 = new HBox(10);
			hb6.getChildren().addAll(new Label("Complemento"),bot.getTf6(),new Label("Bairro"),bot.getTf7());
			HBox hb7 = new HBox(10);
			hb7.getChildren().addAll(new Label("Estado"),bot.getCbest(),new Label("CEP"),bot.getTf8(),new Label("Num"),bot.getTf5());
			cena.add(hb1, 0, 0);
			cena.add(hb2, 0, 1);
			cena.add(hb3, 0, 2);
			cena.add(hb4, 0, 3);
			cena.add(separadorHorizontal, 0, 4);
			cena.add(hb5, 0, 5);
			cena.add(hb6, 0, 6);
			cena.add(hb7, 0, 7);
			cena.add(separadorHorizontal1, 0, 8);
			try {
				pf = pfd.getByCpf(busca.getNome());
				end = endd.getByEndereco(pf.getId_end());
			} catch (ConexaoException e) {
				Erro erro = new Erro("Cliente não existe no sistema!!");
				erro.handle(null);
			}
			bot.getTf1().setText(pf.getNome());	//	NOME
			bot.getTf2().setText(pf.getDataNasc());	//	DATA NASCIMENTO
			bot.getTf3().setText(pf.getCpf());	//	CPF
			bot.getTf4().setText(end.getRua());	//	RUA
			bot.getTf5().setText(Integer.toString(end.getNumero()));	//	NUM
			bot.getTf6().setText(end.getComplemento());	//	COMPLEMENTO
			bot.getTf7().setText(end.getBairro());	//	BAIRRO
			bot.getTf8().setText(end.getCEP());	//	CEP
			bot.getTf9().setText(pf.getTelefone());	//	TELEFONE
			bot.getTf10().setText(end.getCidade());	//	CIDADE
			bot.getTf11().setText(pf.getRg());	//	RG
			bot.setCbsexo(pf.getSexo());
			bot.setCbest(end.getEstado());	// ESTADO
			bot.getPassword();

			dialog.getDialogPane().setContent(cena);
			ButtonType buttonTypeA = new ButtonType("Atualizar", ButtonData.OK_DONE);
			ButtonType buttonTypeV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().addAll(buttonTypeA, buttonTypeV);
			Optional<ButtonType> result = dialog.showAndWait();
			if (result.get() == buttonTypeA) {
				String scan;
				scan = bot.getTf1().getText(); 		// NOME
				pf.setNome(scan);
				scan = bot.getTf11().getText();		// RG
				pf.setRg(scan);
				scan = bot.getCbsexo().getValue();	// SEXO
				pf.setSexo(scan);
				scan = bot.getTf9().getText();		// TELEFONE
				pf.setTelefone(scan);
				scan = bot.getTf2().getText();		// DATA
				pf.setDataNasc(scan);
				scan = bot.getTf3().getText();		// CPF
				pf.setCpf(scan);
				scan = bot.getTf4().getText();		// RUA
				end.setRua(scan);
				scan = bot.getTf5().getText();		// NUMERO
				end.setNumero(Integer.parseInt(scan));
				scan = bot.getCbest().getValue();	// ESTADO
				end.setEstado(scan);
				scan = bot.getTf6().getText();		// COMPLEMENTO
				end.setComplemento(scan);
				scan = bot.getTf7().getText();		// BAIRRO
				end.setBairro(scan);
				scan = bot.getTf8().getText();		//CEP
				end.setCEP(scan);
				scan = bot.getTf10().getText();		// CIDADE
				end.setCidade(scan);

				pf.setEndereco(end);
				try {
					pfc.alterar(pf);
				} catch (ConexaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				InfoOk info = new InfoOk();
				info.handle(null);
			}
		}
	}
}