package br.com.financas.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Numero extends Util {

	
	public double StrToVal(String numero) {
		String temp = new String("");
		double valor = 0;
		temp = ((numero != null) && (numero.indexOf(".") != -1))? numero.replaceAll("\\.", ""):numero;
		temp = ((temp != null) && (temp.indexOf(",") != -1))? temp.replaceAll(",", "\\."):temp;
		if (temp != null && !temp.equals("")) {
			valor = Double.valueOf(temp);
		}
		return valor;
	}
	
	public String ValToStrFmt(double Valor, int Decimais) {
	      DecimalFormat Formatador = null;
	      String Padrao = "";
	      for (int i = 1; i<=Decimais; i++) {
	          Padrao = Padrao + "0";
	      }
	      if (Decimais > 0) {
	         Padrao = "." + Padrao;
	      }
	      try {
	         Formatador = (DecimalFormat)NumberFormat.getInstance(Locale.GERMAN);
	      } catch (ClassCastException e) {
	    	  return null;
	      }
	      Formatador.applyPattern("###,###,###,###,##0" + Padrao);
	      return (Formatador.format(Valor));
	  }

}
