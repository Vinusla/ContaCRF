package contacrf.gui.tela;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Deposito implements EventHandler<ActionEvent>{
	private double valor;
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	public void handle(ActionEvent evento) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		TextField txfN = new TextField();
		TextField txfV = new TextField();

		txfV.setPromptText("Valor para deposito");
		txfN.setPrefWidth(100);
		txfV.setPrefWidth(20);
		HBox hb = new HBox(10);
		hb.getChildren().addAll(new Label("Numero"),txfN);
		VBox vb = new VBox(10);
		vb.getChildren().addAll(new Label("Agencia X.XXX-X"),hb,txfV);
		alert.getDialogPane().setContent(vb);

		ButtonType buttonV = new ButtonType("Voltar", ButtonData.CANCEL_CLOSE);
		alert.getDialogPane().getButtonTypes().addAll(buttonV);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			String nome ="";
			nome = txfV.getText();
			this.valor = Double.parseDouble(nome); // CRIAR EXCEÇÃO
			InfoOk info = new InfoOk("Valor depositado ",20);
			info.handle(null);

			if( valor <= 0 ){ // valor <= 0 || cliente != numero
				Erro erro = new Erro("Cliente não encontrado!");
				erro.handle(null);
			}
		}

		/*		alert.showAndWait().ifPresent(ok->{
			if(ok != buttonV && valor > 0){
				String nome ="";
				nome = txfV.getText();
				this.valor = Double.parseDouble(nome); // CRIAR EXCEÇÃO
				InfoOk info = new InfoOk("Valor depositado ",20);
				info.handle(null);
			}
			else if(ok != buttonV ){
				Erro erro = new Erro("Cliente não encontrado!");
				erro.handle(null);
			}
		});
*/
	}
}