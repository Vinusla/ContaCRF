import contacrf.DAO.EnderecoDAO;
import contacrf.DAO.PessoaFisicaDAO;
import contacrf.controller.EnderecoController;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.gui.*;
import contacrf.gui.tela.*;
import contacrf.model.Endereco;
import contacrf.model.PessoaFisica;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws ConexaoException {
		Application.launch(); // INICIA START
	}

	public void start(Stage stage) throws ConexaoException {
		Imagem img = new Imagem();
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane, 550, 550, Color.WHITE);
		MenuBar menuBar = new MenuBar();
		stage.setTitle("Zathura Enterprise ™");
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		pane.setTop(menuBar);
		pane.setCenter(img.getImgLogo()); // ADD LOGO

		Menu menuInicio = new Menu("_Conta"); // MENU
		MenuItem subAbrir = new MenuItem("Abrir conta"); // SUBMENU
		MenuItem subEditar = new MenuItem("Editar conta"); // SUBMENU
		MenuItem subExibir = new MenuItem("Exibir contas");
		MenuItem subFechar = new MenuItem("Fechar conta");
		MenuItem subExit = new MenuItem("Exit");
		subExit.setOnAction(actionEvent -> Platform.exit());
		menuInicio.getItems().addAll(subAbrir, subEditar, subExibir, subFechar,
				new SeparatorMenuItem(), subExit); // ADD SUBMENU

		Menu menuOperacoes = new Menu("_Operaçoes"); // MENU
		MenuItem subRelatorio = new MenuItem("Gerar Relatorio");
		MenuItem subSaque = new MenuItem("Saque");
		MenuItem subDeposito = new MenuItem("Deposito");
		MenuItem subSaldo = new MenuItem("Saldo");
		MenuItem subTransferencia = new MenuItem("Transferência");
		menuOperacoes.getItems().addAll(subSaque,subDeposito, subSaldo, subTransferencia,
				subRelatorio);

		Menu menuAjuda = new Menu("_Ajuda");
		MenuItem subSobre = new MenuItem("Quem somos");
		menuAjuda.getItems().addAll(subSobre);

		Menu menuEspecial = new Menu("_Especial");
		MenuItem subEspecial = new MenuItem("Especial");
		menuEspecial.getItems().addAll(subEspecial);

		menuBar.getMenus().addAll(menuInicio, menuOperacoes, menuEspecial,
				menuAjuda);
		stage.setScene(scene);
		stage.show();

		//Bloco de Teste
/*
		Endereco end = new Endereco("peido", "geisel", 74, "00000", "jjajajal", "PE");
		PessoaFisica pf = new PessoaFisica("nos", "456", end, "8888", "femi", "10/10/10");
		PessoaFisicaController pfc = new PessoaFisicaController();
		EnderecoController endC = new EnderecoController();

		String cpf = "456";

		//Inserindo

		Erro erro = new Erro("CPF JA EXISTE");
		if(!pfc.existeCPF(pf.getCpf())){ // se o cpf não existe ele cadastra no banco

			pfc.gravar(pf);
		}else
			erro.handle(null);


		//fim do Inserindo

		/*
		//Excluindo
		String cpf = "1235";

		if (!pfc.existeCPF(cpf))
			System.out.println("usuario  não encontrado");
		else {
			pf = pfc.exibir(cpf);
			pfc.excluir(pf);

		}
		//Fim do excluindo
		 *
		 */

		/*
		 *

		//Alterando

		PessoaFisica pf = null;
		Endereco end = null;

		pf = pfc.exibir(cpf);

		pf.setNome("elelelel");
		pf.setTelefone("7777");
		pf.setSexo("gay");
		pf.setDataNasc("31/31/31");

		//end = endC.exibirEndereco(pf.getId_end());

		end.setRua("lalalala");
		end.setBairro("geisel");
		end.setNumero(45);
		end.setCEP("4545");
		end.setComplemento("hdhdhdhd");

		pf.setEndereco(end);

		pfc.alterar(pf);

		*/
		//Fim do Alterando



		//Fim do Testando


		// BUTTONS MENU
		subAbrir.setOnAction(new Cadastro());
		subEditar.setOnAction(new Editar());
		subExibir.setOnAction(new Buscar());
		subFechar.setOnAction(new Fechar());

		subRelatorio.setOnAction(new Relatorio());
		subSaque.setOnAction(new Saque());
		subDeposito.setOnAction(new Deposito());

		subEspecial.setOnAction(new Especial());
	}
}
