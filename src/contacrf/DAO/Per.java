package contacrf.DAO;

//Para a execu��o das duas classes abaixo � importar algumas classes do Java.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import contacrf.model.PessoaFisica;

public class Per {
	public static void createCsvFile(PessoaFisica pf) {
		// A estrutura try-catch � usada pois o objeto BufferedWriter exige que
		// as excess�es sejam tratadas
		try {

			// Cria��o de um buffer para a escrita em uma stream
			BufferedWriter StrW = new BufferedWriter(new FileWriter(
					"Tabela.csv"));

			// Escrita dos dados da Tabela
			StrW.write("Nome;CPF;Telefone;Cidade;Rua;Numero;Bairro;Estado\n");
			StrW.write(pf.getNome()+ ";" +pf.getCpf()+ ";" +pf.getTelefone()+ ";" +pf.getEndereco().getCidade()+
				";" +pf.getEndereco().getRua()+ ";" +pf.getEndereco().getNumero()+ ";" +pf.getEndereco().getBairro()+
				";" +pf.getEndereco().getEstado()+ "\n");
			// Fechamos o buffer
			StrW.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readCsvFile() {

		// A estrutura try-catch � usada pois o objeto BufferedWriter exige que
		// as
		// excess�es sejam tratadas
		try {

			// Cria��o de um buffer para a ler de uma stream
			BufferedReader StrR = new BufferedReader(new FileReader(
					"Tabela.csv"));

			String Str;
			String[] TableLine;

			// Essa estrutura do looping while � cl�ssica para ler cada linha
			// do arquivo
			while ((Str = StrR.readLine()) != null) {
				// Aqui usamos o m�todo split que divide a linha lida em um
				// array de String passando como parametro o divisor ";".
				TableLine = Str.split(";");

				// O foreach � usadao para imprimir cada c�lula do array de String.
				for (String cell : TableLine) {
					System.out.print(cell + " ");
				}
				System.out.println("\n");
			}
			// Fechamos o buffer
			StrR.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}