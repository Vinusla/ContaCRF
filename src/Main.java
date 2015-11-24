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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		MenuItem subBuscar = new MenuItem("Buscar conta");
		MenuItem subFechar = new MenuItem("Fechar conta");
		MenuItem subExit = new MenuItem("Exit");
		subExit.setOnAction(actionEvent -> Platform.exit());
		menuInicio.getItems().addAll(subAbrir, subEditar, subBuscar, subFechar,
				new SeparatorMenuItem(), subExit); // ADD SUBMENU

		Menu menuCliente = new Menu("_Cliente"); // MENU
		MenuItem subRelatorio = new MenuItem("Gerar Relatorio");
		MenuItem subSaque = new MenuItem("Saque");
		MenuItem subSaldo = new MenuItem("Saldo");
		MenuItem subTransferencia = new MenuItem("Transferência");
		menuCliente.getItems().addAll(subSaque, subSaldo, subTransferencia,
				subRelatorio);

		Menu menuSobre = new Menu("_Ajuda");
		MenuItem subSobre = new MenuItem("Quem somos");
		menuSobre.getItems().addAll(subSobre);

		Menu menuEspecial = new Menu("_Especial");
		MenuItem subEspecial = new MenuItem("Especial");
		menuEspecial.getItems().addAll(subEspecial);

		menuBar.getMenus().addAll(menuInicio, menuCliente, menuEspecial,
				menuSobre);
		stage.setScene(scene);
		stage.show();

		
		
		//Bloco de Teste 
		
		Endereco end = new Endereco("peido", "geisel", 74, "00000", "jjajajal", "PE");
		PessoaFisica pf = new PessoaFisica("nos", "456", end, "8888", "femi", "10/10/10");		
		PessoaFisicaController pfc = new PessoaFisicaController();
		EnderecoController endC = new EnderecoController();
		
		
		String cpf = "456";

		
		//Inserindo
		
		String msg = "CPF JA EXISTE";
		Erro erro = new Erro("CPF JA ");
		if(!pfc.existeCPF(pf.getCPF())){ // se o cpf não existe ele cadastra no banco
			
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
		subBuscar.setOnAction(new Buscar());
		subFechar.setOnAction(new Fechar());
		subRelatorio.setOnAction(new Relatorio());
		subEspecial.setOnAction(new Especial());
	}
}
