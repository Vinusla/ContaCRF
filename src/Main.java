import contacrf.DAO.EnderecoDAO;
import contacrf.DAO.PessoaFisicaDAO;
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

	public void start(Stage stage)throws ConexaoException {
		Imagem img = new Imagem();
		BorderPane pane = new BorderPane();
	    Scene scene = new Scene(pane, 550, 550, Color.WHITE);
	    MenuBar menuBar = new MenuBar();
	    stage.setTitle("Zathura Enterprise ™");
	    menuBar.prefWidthProperty().bind(stage.widthProperty());
	    pane.setTop(menuBar);
	    pane.setCenter(img.getImgLogo());						//ADD LOGO

		Menu menuInicio = new Menu("_Conta");					// MENU
		MenuItem subAbrir = new MenuItem("Abrir conta");		// SUBMENU
		MenuItem subEditar = new MenuItem("Editar conta"); 		// SUBMENU
	    MenuItem subBuscar = new MenuItem("Buscar conta");
	    MenuItem subFechar = new MenuItem("Fechar conta");
		MenuItem subExit = new MenuItem("Exit");
	    subExit.setOnAction(actionEvent -> Platform.exit());
	    menuInicio.getItems().addAll(subAbrir,subEditar,subBuscar,subFechar,new SeparatorMenuItem(),
	    		subExit);			//ADD SUBMENU

	    Menu menuCliente = new Menu("_Cliente");				 // MENU
	    MenuItem subRelatorio = new MenuItem("Gerar Relatorio");
	    MenuItem subSaque = new MenuItem("Saque");
	    MenuItem subSaldo = new MenuItem("Saldo");
	    MenuItem subTransferencia = new MenuItem("Transferência");
	    menuCliente.getItems().addAll(subSaque,subSaldo,subTransferencia,subRelatorio);

	    Menu menuSobre = new Menu("_Ajuda");
	    MenuItem subSobre = new MenuItem("Quem somos");
	    menuSobre.getItems().addAll(subSobre);

	    Menu menuEspecial = new Menu("_Especial");
	    MenuItem subEspecial = new MenuItem ("Especial");
	    menuEspecial.getItems().addAll(subEspecial);

	    menuBar.getMenus().addAll(menuInicio,menuCliente,menuEspecial,menuSobre);
		stage.setScene(scene);
		stage.show();
		/*// TESTE
		PessoaFisicaDAO pedao = new PessoaFisicaDAO();
		Endereco end = new Endereco("Rua Jose","Agua Fria",0,"58053-022","Proximo UNIPE","PB");
		PessoaFisica pf = new PessoaFisica("Mateus C","123.456.789-10",end,"083 1234-1234","MASCULINO","11/22/33");
		pedao.save(pf);
*/
		// BUTTONS MENU
		subAbrir.setOnAction(new Cadastro());
		subEditar.setOnAction(new Editar());
		subBuscar.setOnAction(new Buscar());
		subFechar.setOnAction(new Fechar());

		subRelatorio.setOnAction(new Relatorio());
		subEspecial.setOnAction(new Especial());
	}
}
