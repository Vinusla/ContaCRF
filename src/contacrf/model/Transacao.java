package contacrf.model;

import java.util.Date;

public interface Transacao {
  public String getID();
  public String getDescricao();
  public Date getData();
  public float getValor();
  
  
}
