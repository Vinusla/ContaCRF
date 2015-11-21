package contacrf.gui.tela;

import contacrf.gui.Botoes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Especial implements EventHandler<ActionEvent>{
	public void PreencherGrafico(LineChart graficoLinha, int[] array){

		graficoLinha.getData().clear(); // LIMPA
		XYChart.Series serie = new XYChart.Series();

		int s = 0;
		while(s < array.length){
			serie.getData().add(new XYChart.Data("Sem"+(s+1), array[s]));
			s++;
		}
		graficoLinha.getData().addAll(serie);
	}

	LineChart graficoLinha= new LineChart<>(new CategoryAxis(),new NumberAxis());

	public void handle(ActionEvent evento) {
		Botoes bot = new Botoes();
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		GridPane cena = new GridPane();
		//cena.setPadding(new Insets(20,35,20,35));
		cena.setHgap(20);
		cena.setVgap(20);
		cena.setAlignment(Pos.TOP_CENTER);
		dialog.setTitle("Zathura Enterprise ™");
		dialog.setWidth(400);
		dialog.setHeight(400);
		graficoLinha.setPrefSize(500, 320);
		int[] array = new int[30];

		for (int i = 0; i < 15; i++) {	// TESTE
			array[i] = 5+i;
		}
		for (int i = 15; i < 30; i++) {	// TESTE
			array[i] = 10-i;
		}

		bot.getSaldo().setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				//ARRAY RECEBE SALDO
				PreencherGrafico(graficoLinha, array);
			}
		});
		bot.getDeposito().setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				//ARRAY RECEBE DEPOSITO
				PreencherGrafico(graficoLinha, array);
			}
		});
		bot.getTransferencia().setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				//ARRAY RECEBE TRANSFERENCIA
				PreencherGrafico(graficoLinha, array);
			}
		});

		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(bot.getSaldo(),bot.getDeposito(),bot.getTransferencia());
		HBox hb = new HBox(5);
		hb.getChildren().addAll(new Label("Ultimos 30 dias"));
		cena.add(hbox,0,1);
		cena.add(hb,0, 2);
		cena.add(graficoLinha, 0, 3);

		ButtonType buttonTypeOk = new ButtonType("Voltar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOk );
		dialog.getDialogPane().setContent(cena);
		dialog.show();
	}
}