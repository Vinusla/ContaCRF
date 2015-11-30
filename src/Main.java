
import contacrf.gui.Imagem;
import contacrf.gui.tela.Cadastro;
import contacrf.gui.tela.Deposito;
import contacrf.gui.tela.Editar;
import contacrf.gui.tela.Especial;
import contacrf.gui.tela.Exibir;
import contacrf.gui.tela.Fechar;
import contacrf.gui.tela.Listar;
import contacrf.gui.tela.Relatorio;
import contacrf.gui.tela.Saldo;
import contacrf.gui.tela.Saque;
import contacrf.gui.tela.Transferencia;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {
	private Imagem img = new Imagem();
	public static final String APPLICATION_ICON = "logo.png";
    public static final String SPLASH_IMAGE = "logoS.png";
    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private static final int SPLASH_WIDTH = 500;
    private static final int SPLASH_HEIGHT = 220;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(SPLASH_IMAGE ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Will find friends for peanuts . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                "-fx-background-color: cornsilk; " +
                "-fx-border-width:5; " +
                "-fx-border-color: " +
                    "linear-gradient(" +
                        "to bottom, " +
                        "chocolate, " +
                        "derive(chocolate, 50%)" +
                    ");"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundFriends =
                        FXCollections.<String>observableArrayList();
                ObservableList<String> availableFriends =
                        FXCollections.observableArrayList(
                        		 "Cadastro", "Clientes", "Banco de Dados", "Criando Conexão",
                                 "Relatorios","Graficos", "Menu", "Contas", "Arquivos",
                                 "Ajuda");
                updateMessage("Inicializando ...");
                for (int i = 0; i < availableFriends.size(); i++) {
                    Thread.sleep(100); // MUDAR PARA 800
                    updateProgress(i + 1, availableFriends.size());
                    String nextFriend = availableFriends.get(i);
                    foundFriends.add(nextFriend);
                    updateMessage("Inicializando . . . Carregando " + nextFriend);
                }
                Thread.sleep(400);
                updateMessage("Inicialização completada");

                return foundFriends;
            }
        };
        showSplash(
                initStage,
                friendTask,
                () -> showMainStage(friendTask.valueProperty())
        );
        new Thread(friendTask).start();
    }

    private void showMainStage(
            ReadOnlyObjectProperty<ObservableList<String>> friends
    ) {
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane, 550, 550, Color.WHITE);
		MenuBar menuBar = new MenuBar();
		Stage stage = new Stage();
		stage.setTitle("Zathura Enterprise ™");
		stage.getIcons().add(new Image(APPLICATION_ICON));
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		pane.setTop(menuBar);
		pane.setCenter(img.getImgLogo()); // ADD LOGO

		Menu menuInicio = new Menu("_Conta"); // MENU
		MenuItem subAbrir = new MenuItem("Abrir conta"); // SUBMENU
		MenuItem subEditar = new MenuItem("Editar conta"); // SUBMENU
		MenuItem subExibir = new MenuItem("Exibir contas");
		MenuItem subListar = new MenuItem("Listar contas");
		MenuItem subFechar = new MenuItem("Fechar conta");
		MenuItem subExit = new MenuItem("Exit");
		subExit.setOnAction(actionEvent -> Platform.exit());
		menuInicio.getItems().addAll(subAbrir, subEditar, subExibir,subListar,
				subFechar,new SeparatorMenuItem(), subExit); // ADD SUBMENU

		Menu menuOperacoes = new Menu("_Operações"); // MENU
		MenuItem subRelatorio = new MenuItem("Gerar Relatório");
		MenuItem subSaque = new MenuItem("Saque");
		MenuItem subDeposito = new MenuItem("Deposito");
		MenuItem subSaldo = new MenuItem("Saldo");
		MenuItem subTransferencia = new MenuItem("Transferência");
		menuOperacoes.getItems().addAll(subSaque,subDeposito, subSaldo, subTransferencia,
				subRelatorio);

		Menu menuEspecial = new Menu("_Especial");
		MenuItem subEspecial = new MenuItem("Especial");
		menuEspecial.getItems().addAll(subEspecial);

		menuBar.getMenus().addAll(menuInicio, menuOperacoes, menuEspecial);
		stage.setScene(scene);
		stage.show();

		// BUTTONS MENU
		subAbrir.setOnAction(new Cadastro());
		subEditar.setOnAction(new Editar());
		subExibir.setOnAction(new Exibir());
		subListar.setOnAction(new Listar());
		subFechar.setOnAction(new Fechar());

		subSaque.setOnAction(new Saque());
		subDeposito.setOnAction(new Deposito());
		subSaldo.setOnAction(new Saldo());
		subTransferencia.setOnAction(new Transferencia());
		subRelatorio.setOnAction(new Relatorio());

		subEspecial.setOnAction(new Especial());
    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });

        Scene splashScene = new Scene(splashLayout);
        initStage.initStyle(StageStyle.UNDECORATED);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.show();
    }

    public interface InitCompletionHandler {
        public void complete();
    }
}