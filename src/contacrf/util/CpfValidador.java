package contacrf.util;

import java.util.regex.Pattern;


/**
 *  Valida CPF de uma maneira simples e precisa.
 * 
 * 
 * @author Hugo Prudente
 */
public class CpfValidador {

	private static char[] aCpf;

	/**
	 * Valida um CPF, através de uma string recebida;
	 * 
	 * @param cpf
	 * @return boolean
	 */
	public static boolean validaCpf(String cpf) {
		cpf = cpf.replaceAll(Pattern.compile("\\s").toString(), "");
		cpf = cpf.replaceAll(Pattern.compile("\\D").toString(), "");

		int soma = 0;

		if (cpf.length() != 11) {
			return false;
		}

		aCpf = cpf.toCharArray();

		// Verifica 1º digito
		for (int i = 0; i < 9; i++) {
			int j = (i + 1);
			int x = Integer.parseInt(Character.toString(aCpf[i]));
			soma += (j * x);
		}

		int d1 = (soma % 11);
		if (d1 == 10) {
			d1 = 0;
		}

		// Verifica o 2º digito
		soma = 0;
		int j = 0;
		for (int i = 9; i > 0; i--) {
			int x = Integer.parseInt(Character.toString(aCpf[j]));
			soma += (i * x);
			j++;
		}

		int d2 = (soma % 11);

		if (d2 == 10) {
			d2 = 0;
		}

		if (d1 == Integer.parseInt(Character.toString(aCpf[9]))
				&& d2 == Integer.parseInt(Character.toString(aCpf[10]))) {
			return true;
		} else {
			return false;
		}
	}

}
