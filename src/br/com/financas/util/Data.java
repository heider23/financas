package br.com.financas.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Data extends Util {
	
  public String formatar(Date Data, String Padrao) throws Exception {
    SimpleDateFormat formatD = new SimpleDateFormat(Padrao);
    return formatD.format(Data);
  }
  
  public final Date converter(String Data, String Padrao) throws Exception {
    Date DataAux = new Date();
    SimpleDateFormat FormataDT = new SimpleDateFormat(Padrao);
    try {
      DataAux = FormataDT.parse(Data);
    }
    catch (java.text.ParseException E) {
      throw new Exception(E.getMessage());
      //return null;
    }
    return DataAux;
  }

}
