package br.com.financas.util;

public class Texto extends Util {
  public final String substituir(String Valor, String Procurar, String Novo) throws Exception {
      String resp = "";
      int pos = 1;
      resp = Valor;
      pos = this.localizarString(resp,Procurar,pos,true);
      while (pos > 0) {
          resp = resp.substring(0,pos-1) +
                 Novo +
                 resp.substring(pos+Procurar.length()-1);
          pos = this.localizarString(resp,Procurar,pos,true);
      }
      return resp;
  }

  public final int localizarString(String Origem, String Procurar, int Inicio, boolean SensivelCaso) throws Exception {
      int i = 0, pos = Procurar.length(), tam = Origem.length();
      String Aux1 = "";
      String AuxOrigem = "";
      String AuxProcurar = "";
      if (SensivelCaso) {
          AuxOrigem = Origem;
          AuxProcurar = Procurar;
      } else {
          AuxOrigem = Origem.toUpperCase();
          AuxProcurar = Procurar.toUpperCase();
      }
      pos += Inicio - 2;
      for (i = Inicio-1; i < tam && pos < tam && !Aux1.equals(AuxProcurar); i++) {
          pos += 1;
          Aux1 = AuxOrigem.substring(i,pos);
      }
      if (Aux1.equals(AuxProcurar))
          return i;
      else
          return 0;
  }

  public final int localizarString(String Origem, String Procurar, int Inicio) throws Exception {
      return this.localizarString(Origem, Procurar, Inicio, false);
  }

  public final int localizarString(String Origem, String Procurar) throws Exception {
      return this.localizarString(Origem, Procurar, 1, false);
  }

  public final String alinharCentro(String Valor, int Tamanho, String Caracter) throws Exception {
      String AuxValor = Valor;
      for (int i = Valor.length(); i < Tamanho; i++) {
          if ((AuxValor.length() % 2) == 0)
              AuxValor =  Caracter + AuxValor;
          else
              AuxValor = AuxValor + Caracter;
      }
      return AuxValor;
  }

  public final String alinharCentro(String Valor, int Tamanho) throws Exception {
      return this.alinharCentro(Valor,Tamanho," ");
  }

  public final String alinharDireita(String Valor, int Tamanho, String Caracter) throws Exception {
      String AuxValor = Valor;
      for (int i = Valor.length(); i < Tamanho; i++)
          AuxValor =  Caracter + AuxValor;
      return AuxValor;
  }

  public final String alinharDireita(String Valor, int Tamanho) throws Exception {
      return this.alinharDireita(Valor,Tamanho," ");
  }

  public final String alinharEsquerda(String Valor, int Tamanho, String Caracter) throws Exception {
      String AuxValor = Valor;
      for (int i = Valor.length(); i < Tamanho; i++)
          AuxValor = AuxValor + Caracter;
      return AuxValor;
  }

  public final String alinharEsquerda(String Valor, int Tamanho) throws Exception {
      return this.alinharEsquerda(Valor,Tamanho," ");
  }

  public final int contarCaracteres(String Valor, String Procurar) throws Exception {
      int resp = 0;
      int pos = 1;
      pos = this.localizarString(Valor,Procurar,pos,true);
      while (pos > 0) {
          resp ++;
          pos = this.localizarString(Valor,Procurar,pos+1,true);
      }
      return resp;
  }
  /* by Don */
  public final String tratarNull(String strOriginal, String strSubs) throws Exception {
    if(strOriginal == null) {
      return strSubs;
    } else {
      return strOriginal;
    }
  }
  public final String tratarNull(String strOriginal, String strSubs, boolean bRetTrim) throws Exception {
    if(strOriginal == null) {
      return strSubs;
    } else {
      if (bRetTrim) {
        return strOriginal.trim();
      } else {
        return strOriginal;
      }
    }
  }
  /* substitui o CRLF por uma tag <BR> */
  public final String trataEnterHTML(String strOriginal) throws Exception {
    StringBuffer SB = new StringBuffer();

    for (int i = 0;i < strOriginal.length();i++) {
      if ((int)strOriginal.charAt(i) == 10) {
        continue;
      }
      if ((int)strOriginal.charAt(i) == 13) {
        SB.append("<BR>");
      }
      else {
        SB.append(strOriginal.charAt(i));
      }
    }
    return SB.toString();
  }
 //
}
