package contacrf.conexao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JDBCConexao {
	
	private String usuario;
	private String senha;
	private String url;
	private String database;
	
	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	
	public void getPropriedades (){
	    Properties props = new Properties();

	    	    
	    try {
	        //Setamos o arquivo que ser� lido
	        FileInputStream file = new FileInputStream("jdbc.properties");
	        //m�todo load faz a leitura atrav�s do objeto fis
	        props.load(file);
	    } catch (IOException e) {
	        System.out.println(e.getMessage());
	    }
	    //Captura o valor da propriedade, atrav�s do nome da propriedade(Key)
	    this.usuario = props.getProperty("jdbc.user");
	    this.senha = props.getProperty("jdbc.passwd");
	    this.url = props.getProperty("jdbc.url");
	    this.database = props.getProperty("jdbc.dataBase");
	    
	    
	}
	
	

}
