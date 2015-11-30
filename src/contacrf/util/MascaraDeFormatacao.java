package contacrf.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class MascaraDeFormatacao {


	public static String formatar(String padrao, Object valor) {
		 MaskFormatter mascaraF;

        try {
        	mascaraF = new MaskFormatter(padrao);
        	mascaraF.setValueContainsLiteralCharacters(false);
            return mascaraF.valueToString(valor);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}