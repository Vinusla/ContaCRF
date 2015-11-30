package contacrf.gui.tela;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import contacrf.controller.ContaCorrenteController;
import contacrf.controller.PessoaFisicaController;
import contacrf.gui.Botoes;
import contacrf.model.Agencia;
import contacrf.model.Conta;
import contacrf.model.Endereco;
import contacrf.model.PessoaFisica;
import contacrf.util.CepValidador;
import contacrf.util.CpfValidador;
import contacrf.util.DataValidador;
import contacrf.util.MascaraDeFormatacao;
import contacrf.util.TelefoneValidador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Cadastro implements EventHandler<ActionEvent>{
	public void handle(ActionEvent evento) {
		Endereco end = new Endereco();
		PessoaFisica pf = new PessoaFisica();
		Conta conta = new Conta();
		Agencia agencia = new Agencia ();
		PessoaFisicaController pfc = new PessoaFisicaController();
		ContaCorrenteController cc = new ContaCorrenteController();
		Botoes bot = new Botoes();
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		GridPane cena = new GridPane();
		dialog.setTitle("Cadastrar");
		dialog.setHeaderText("Informe dados do cliente");
		dialog.setResizable(true);
		cena.setAlignment(Pos.TOP_CENTER);
		cena.setHgap(10);
		cena.setVgap(10);
		cena.setPadding(new Insets(20,35,20,35));
		bot.setCbsexo("MASCULINO");
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
		HBox hb8 = new HBox(10);
		
		Random random = new Random();		// GERAR NUMERO DA CONTA
		conta.setNumero(String.valueOf(random.nextInt(880000)+10000));
		String numContaFormatado = MascaraDeFormatacao.formatar("#####-#", conta.getNumero());
		hb8.getChildren().addAll(new Label("Password"),bot.getPassword(),new Label("Numero "+ numContaFormatado),new Label("Agencia "+agencia.getNumero()));
		
		cena.add(hb1, 0, 0);
		cena.add(hb2, 0, 1);
		cena.add(hb3, 0, 2);
		cena.add(hb4, 0, 3);
		cena.add(separadorHorizontal, 0, 4);
		cena.add(hb5, 0, 5);
		cena.add(hb6, 0, 6);
		cena.add(hb7, 0, 7);
		cena.add(separadorHorizontal1, 0, 8);
		cena.add(hb8, 0, 9);
		dialog.getDialogPane().setContent(cena);

		//CONFIG BOTOES DO CANTO
		ButtonType buttonTypeOk = new ButtonType("Cadastrar", ButtonData.OK_DONE);
		ButtonType buttonTypeC = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk,buttonTypeC);

		

		dialog.showAndWait().ifPresent(ok->{// EXIBE TELA E RECEBE VALORES DAS CAIXAS
			if(buttonTypeOk == ok){
				
				
				String r = "";

				
				
				
				
				
						
				if(bot.getTf1().getText().equals("") || bot.getTf2().getText().equals("") || bot.getTf3().getText().equals("")
						|| bot.getTf4().getText().equals("") || bot.getTf5().getText().equals("") || bot.getTf7().getText().equals("") 
						|| bot.getTf8().getText().equals("") || bot.getTf9().getText().equals("") || bot.getTf10().getText().equals("") 
						|| bot.getTf11().getText().equals("") || bot.getCbest().getValue() == null){
					
					Erro erro = new Erro("Todos os Campos são obrigatórios exceto o campo complemento");
					erro.handle(null);
					return;
				}else{
				
				
					String scan;					
					
					scan = bot.getTf1().getText(); 		// NOME
					pf.setNome(scan);
					
					
					scan = bot.getTf11().getText();		// RG
					pf.setRg(scan);
					
					
					scan = bot.getCbsexo().getValue();	// SEXO
					pf.setSexo(scan);
					
					
					
					//valida o telefone	
					if(!TelefoneValidador.isTelefone(scan = bot.getTf9().getText())){
					    Erro erro = new Erro("Telefone Inválido");
						erro.handle(null);
						return;
					}	
					
					//retira os parenteses e -				      
				    scan = scan.replaceAll("[\\D]", "");
					pf.setTelefone(scan);
					
					
					//valida a data	
					if(!DataValidador.isData(scan = bot.getTf2().getText())){
					    Erro erro = new Erro("Cpf Inválido");
						erro.handle(null);
						return;
					}
					
					//retira as barras				      
				    scan = scan.replaceAll("[\\D]", "");					
					pf.setDataNasc(scan);
	
					
					//valida o cpf	
					if(!CpfValidador.validaCpf(scan = bot.getTf3().getText())){
					    Erro erro = new Erro("Cpf Inválido");
						erro.handle(null);
						return;
					}
					
					//retira os ponto e espaços 
				    scan = bot.getTf3().getText();  
				    scan = scan.replaceAll("[\\D]", "");
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
					
					
					
					
					//valida o cep	
					if(!CepValidador.isCep(scan = bot.getTf8().getText())){
					    Erro erro = new Erro("Cep Inválido");
						erro.handle(null);
						return;
					}	
					
					//retira os parenteses e -				      
					scan = scan.replaceAll("[\\D]", "");					
					end.setCEP(scan);
					
					
					scan = bot.getTf10().getText();		// CIDADE
					end.setCidade(scan);
					scan = bot.getPassword().getText(); // PASSWORD
	
					if(scan.length()!=6){
						Erro erro = new Erro("Senha invalida");
						erro.handle(null);
						return;
					}
					conta.setSenha(bot.getPassword().getText());
					conta.setCpfCliente(pf.getCpf());
					pf.setEndereco(end);
	
					try {
						if(!pfc.existeCPF(pf.getCpf())){ // se o cpf não existe ele cadastra no banco
							pfc.gravar(pf);
							cc.AbrirConta(conta.getNumero(), conta.getCpfCliente(),conta.getSenha());
							// FALTA TRANSAÇOES
							InfoOk info = new InfoOk();
							info.handle(null);
						}else{
							Erro erro = new Erro("CPF JA EXISTE");
							erro.handle(null);
						}
					} catch (Exception e) {
					}
				}
			}
		});
	}
}