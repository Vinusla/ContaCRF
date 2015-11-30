package contacrf.util;

public class TelefoneValidador {

	public static boolean isTelefone(String numeroTelefone) {
		return numeroTelefone.matches("\\(\\d{2}\\)\\d{4,5}-\\d{4}");
				
	}

}
