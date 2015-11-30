package contacrf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataValidador {
	
	
	static SimpleDateFormat datFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public static boolean isData(String dataNasc) {
		
		try {
			  Date data = datFormat.parse(dataNasc);			  
			  return true;
			} catch(ParseException e) {
			  // se cair aqui, a data � inv�lida
			  //System.err.println("Data inv�lida");
			}
		
		
		return false;
				
	}
	
	
	
	
	
	

	
	

}
