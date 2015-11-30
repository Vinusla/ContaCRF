package contacrf.gui.tela;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import contacrf.controller.ContaCorrenteController;
import contacrf.controller.PessoaFisicaController;
import contacrf.exception.ConexaoException;
import contacrf.gui.Botoes;
import contacrf.model.Agencia;
import contacrf.model.Conta;
import contacrf.model.PessoaFisica;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.VBox;

public class Especial implements EventHandler<ActionEvent>{
	public void PreencherGrafico(LineChart graficoLinha, int[] array){

		graficoLinha.getData().clear(); // LIMPA
		XYChart.Series serie = new XYChart.Series();

		int s = 0;
		while(s < array.length){
			serie.getData().add(new XYChart.Data("DIA"+(s+1), array[s]));
			s++;
		}
		graficoLinha.getData().addAll(serie);
	}

	LineChart graficoLinha= new LineChart<>(new CategoryAxis(),new NumberAxis());
	List<String> valor = new ArrayList<String>();
	List<String> dia = new ArrayList<String>();
	List<String> historico = new ArrayList<String>();

	public void handle(ActionEvent evento) {
		Buscar busca = new Buscar();
		busca.handle(null);
		if (busca.isAcho()) {
			Botoes bot = new Botoes();
			Dialog<ButtonType> dialog = new Dialog<ButtonType>();
			GridPane cena = new GridPane();
			Agencia agencia = new Agencia();
			ContaCorrenteController cc = new ContaCorrenteController();
			Conta conta = new Conta();
			PessoaFisicaController pfc = new PessoaFisicaController();
			PessoaFisica pf = new PessoaFisica();
			// cena.setPadding(new Insets(20,35,20,35));
			cena.setHgap(20);
			cena.setVgap(20);
			cena.setAlignment(Pos.TOP_CENTER);
			dialog.setTitle("Zathura Enterprise ™");
			dialog.setWidth(400);
			dialog.setHeight(400);
			graficoLinha.setPrefSize(600, 320);

			try {
				pf = pfc.exibir(busca.getCPF());
				conta = cc.exibir(cc.getNumeroConta(pf.getCpf()));
			} catch (ConexaoException e) {
				Erro erro = new Erro("Cliente não existe no sistema!!");
				erro.handle(null);
			}

			int[] array = new int[30];
			for (int i = 0; i < 15; i++) { // TESTE
				array[i] = 5 + i;
			}
			for (int i = 15; i < 30; i++) { // TESTE
				array[i] = 10 - i;
			}

			bot.getSaldo().setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					// ARRAY RECEBE SALDO
					PreencherGrafico(graficoLinha, array);
				}
			});
			bot.getDeposito().setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					// ARRAY RECEBE DEPOSITO
					PreencherGrafico(graficoLinha, array);
				}
			});
			bot.getTransferencia().setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					// ARRAY RECEBE TRANSFERENCIA
					PreencherGrafico(graficoLinha, array);
				}
			});
			bot.getSaque().setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					// ARRAY RECEBE TRANSFERENCIA
					PreencherGrafico(graficoLinha, array);
				}
			});

			HBox hbox = new HBox(5);
			hbox.getChildren().addAll(bot.getSaldo(), bot.getSaque(), bot.getDeposito(), bot.getTransferencia());
			HBox hb = new HBox(5);
			hb.getChildren().addAll(new Label("Ultimos 30 dias"));

			valor.add("100");
			valor.add("200");
			valor.add("50");
			historico.add("SAQUE");
			historico.add("TRANSFERENCIA");
			historico.add("DEPOSITO");
			dia.add("18");
			dia.add("18");
			dia.add("18");
			Date hoje = new Date();// Data inteira formata
			SimpleDateFormat dataformatada = new SimpleDateFormat();
			String data = dataformatada.format(hoje);

			VBox vbD = new VBox(5);  // DIA
			VBox vbT = new VBox(5);  // HISTORICO
			VBox vbV = new VBox(5);  // VALOR
			HBox hbG = new HBox(10); // GERAL
			VBox hbS = new VBox(10); // SALDO
			VBox vbI = new VBox();   // INFO CONTA
			VBox vbN = new VBox();   // NOME DA EMPRESA E DATA
			vbN.getChildren().addAll(new Label("-----ZATHURA ENTERPRISE ™-----"), new Label("      " + data));
			vbD.getChildren().addAll(new Label("DIA"), new Label(Especial.this.toDia()));
			vbT.getChildren().addAll(new Label("HISTORICO"), new Label(Especial.this.toHistorico()));// CONTA.TIPO
			vbV.getChildren().addAll(new Label("VALOR"), new Label(Especial.this.toString()));
			hbG.getChildren().addAll(vbD, vbT, vbV);
			DecimalFormat df = new DecimalFormat("0.00");	// FORMATA SAIDA DE SALDO FLOAT
			String saldo = df.format(conta.getSaldo());
			hbS.getChildren().addAll(hbG,new Label("  SALDO " +saldo));
			vbI.getChildren().addAll(new Label("AGENCIA " + agencia.getNumero() + "  NUMERO " +conta.getNumero()),
			new Label(pf.getNome()));
			cena.add(hbox, 0, 1);
			cena.add(vbN, 1, 1);
			cena.add(hb, 0, 2);
			cena.add(graficoLinha, 0, 3);
			cena.add(vbI, 1, 2);
			cena.add(hbS, 1, 3);

			ButtonType buttonTypeOk = new ButtonType("Voltar", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
			dialog.getDialogPane().setContent(cena);
			dialog.show();
		}
	}
	public String toDia(){
		String d = "";
		for (String string : dia) {
			d += string +"\n";
		}
		return d + "\n";
	}
	public String toHistorico(){
		String h = "";
		for (String string : historico) {
			h += string +"\n";
		}
		return h + "\n";
	}
	public String toString() {
		String v = "";
		for ( String num : valor) {
			 v += num +"\n";
		}
		return v + "\n";
	}
}