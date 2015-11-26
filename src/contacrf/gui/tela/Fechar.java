package contacrf.gui.tela;

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


public class Fechar implements EventHandler<ActionEvent>{

	public void handle(ActionEvent evento) {
		Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
		GridPane cena = new GridPane();
		cena.setPadding(new Insets(20,35,20,35));
		cena.setAlignment(Pos.TOP_CENTER);
		dialogoAviso.setTitle("Zathura Enterprise ™");
	    dialogoAviso.setHeaderText("Você tem certeza que deseja excluir todos os seus dados ?");

	    Buscar busca = new Buscar();
	    busca.handle(null);
	    ButtonType btSim = new ButtonType("Sim");
	    ButtonType btOk = new ButtonType("OK");
	    ButtonType btVoltar = new ButtonType("Voltar",ButtonData.CANCEL_CLOSE);
	    Separator separator = new Separator();
	    //separator.setPrefWidth(100);
	   	VBox vb = new VBox(10);
	   	vb.getChildren().addAll(new Label("Dados"), separator);
	    HBox hb1 = new HBox(10);
		hb1.getChildren().addAll(new Label(busca.getNome()));

		cena.add(vb, 0, 0);
		cena.add(hb1, 0, 1);
		dialogoAviso.getDialogPane().setContent(cena);
	    dialogoAviso.getButtonTypes().setAll(btSim,btVoltar);
	    dialogoAviso.showAndWait().ifPresent(ok -> {
	    if ( ok == btSim){
	    	// LIMPAR ARQUIVO
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
	    });
	}
}
