package contacrf.util;

public class CepValidador {
	
	public static boolean isCep(String cep) {
		return cep.matches("\\d{5}-\\d{3}");
				
	}
	

}
