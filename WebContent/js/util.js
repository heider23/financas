/**
 * Exemplos:
 * Nome : <input name="nome" id="nome" type="text" size="30" onkeypress="return formatar(this,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',event);"><br>
 * Fone : <input name="fone" id="fone" type="text" size="14" onkeypress="return formatar(this,'(XXX)XXXX-XXXX',event);"><br>
 * Sala : <input name="sal" id="sal" type="text" size="14" onkeypress="return formatarNumero(this,'999.999,99',event);"><br>
 * Obs  : <input name="obs" id="obs" type="text" size="40" onkeypress="return formatar(this,'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX',event);"><br>
 * Hora : <input name="hora" id="hora" type="text" size="10" onkeypress="return formatar(this,'99:99',event);"><br>
 * Data : <input name="data" id="data" type="text" size="10" onkeypress="return formatar(this,'99/99/9999',event);"><br>
 * Sup  : <input name="sup" id="sup" type="text" size="5" onkeypress="return formatar(this,'S9999',event);"><br>
 * Reg  : <input name="reg" id="reg" type="text" size="5" onkeypress="return formatar(this,'G9999',event);"><br>
 * Dis  : <input name="dis" id="dis" type="text" size="5" onkeypress="return formatar(this,'T9999',event);"><br>
 **/

/**
 * Variável que define o tipo de dado: tipo = 'num' : Numérico tipo = '' : Pode
 * ser alfabético ou alfanumérico dependendo da mascara.
 */
var tipo = '';
/**
 * Formatar entradas numéricas.
 * 
 */
function formatarNumero(objCampo, cMascara, oEvent) {
	tipo = 'num';
	return Formatar(objCampo, cMascara, oEvent);
}
/**
 * Formatar entradas alfabéticas e alfanuméricas.
 **/
function formatar(objCampo, cMascara, oEvent) {
	tipo = '';
	if (cMascara.length == 1) {
		if (objCampo.size == 0) {
			cMascara = caracteres(cMascara, 20);
		} else {
			cMascara = caracteres(cMascara, objCampo.size);
		}
	}
	return Formatar(objCampo, cMascara, oEvent);
}
/**
 * Função principal que formata as entradas.
 **/
function Formatar(objCampo, cMascara, oEvent) {
	/**
	 * Testa se o Browser é o Internet Explorer para poder pegar o código da tecla:
	 **/
	var IE = navigator.userAgent.indexOf('MSIE') > 1;
	var tecla;
	if (IE) {
		tecla = oEvent.keyCode; // Captura o evento
	} else {
		tecla = oEvent.which; // Captura o evento
	}
	/**
	 * Constantes:
	 **/
	var TECLA_ZERO = 0;
	var TECLA_HOME = 36;
	var TECLA_END = 35;
	var TECLA_SETADIR = 39;
	var TECLA_SETAESQ = 37;
	var TECLA_CTRL = 17;
	var TECLA_SHIFT = 16;
	var TECLA_DEL = 46;
	var TECLA_INS = 45;
	var TECLA_TAB = 9;
	var TECLA_BACKSPACE = 8;
	var LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz ";
	var LETRAS_MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	var NUMEROS = "0123456789";

	/**
	 * Variáveis:
	 */
	var campoOrigem = objCampo.value;
	var acerto = 0;
	/**
	 * Caracteristicas do campo:
	 */
	objCampo.maxlength = cMascara.length;
	/**
	 * Não processar estas teclas:
	 */
	if (tecla == TECLA_ZERO || tecla == TECLA_HOME || tecla == TECLA_END
			|| tecla == TECLA_SETADIR || tecla == TECLA_SETAESQ
			|| tecla == TECLA_CTRL || tecla == TECLA_SHIFT
			|| tecla == TECLA_DEL || tecla == TECLA_INS || tecla == TECLA_TAB
			|| tecla == TECLA_BACKSPACE) {
		return true;
	}
	/**  
	 * Se o campo foi preenchido:
	 **/
	if (objCampo.value.length >= 0) {
		/**
		 * Alfa e Alfanuméricos
		 **/
		if (tipo != 'num') {
			/**
			 * Testa se o tamanho máximo do campo ja foi preenchido:
			 **/
			if (objCampo.value.length >= cMascara.length) {
				return false; // Retorna sem aceitar a tecla
			}
			// Ler o campo:
			for ( var c = 0; c <= objCampo.value.length; c++) {
				// Verifica o limite da mascara:
				if (c <= cMascara.length) {
					// Numérico:
					if (cMascara.charAt(c) == '9') {
						// Não aceitar teclas que não sejam de números
						if (NUMEROS.indexOf(String.fromCharCode(tecla)) < 0) {
							return false; // Retorna sem aceitar a tecla
						}
					} else {
						// Alfabético (A...Z,a...z):
						if (cMascara.charAt(c) == 'A') {
							// Não aceitas teclas que não sejam de letras						
							if ((LETRAS_MAIUSCULAS.indexOf(String
									.fromCharCode(tecla)) < 0)
									&& (LETRAS_MINUSCULAS.indexOf(String
											.fromCharCode(tecla)) < 0)) {
								return false; // Retorna sem aceitar a tecla
							}
						} else {
							// Alfanuméricos:
							// Quando a mascara nao for numerica ou alfabetica
							// assumir como alfanumerico e aceitar todos os
							// caracteres.
							if (cMascara.charAt(c) == 'X') {
								return true;
							} else {
								if (c >= objCampo.value.length) {
									objCampo.value = campoOrigem
											+ cMascara.charAt(c);
								}
							}
						}
					}
				}
			}
			return true; // Retorna e aceita a tecla
		} else {
			/**
			 * Numérico
			 **/
			/**
			 * Não aceitar teclas que não sejam de números
			 */
			if ((NUMEROS.indexOf(String.fromCharCode(tecla)) < 0)
					|| (String.fromCharCode(tecla) == ",")) {
				// Aceita somente uma virgula.
				if (String.fromCharCode(tecla) == ",") {
					if (objCampo.value.search(/\,/) != -1) {
						return false;
					} else {
						return true;
					}
				}
				return false; // Retorna sem aceitar a tecla
			}
			/**
			 * Testa se o tamanho máximo do campo ja foi preenchido:
			 **/
			if (objCampo.value.length >= cMascara.length)
				return false; // Retorna sem aceitar a tecla
			/**
			 * Retira o ponto (.) e a virgula(,) do campo e guarda na variável
			 * valor.
			 */
			var valor = objCampo.value.replace(/\.|\,/g, '');
			/**
			 * Faz acerto do código ascii quando for teclas do teclado numérico.
			 */
			if (tecla >= 96 && tecla <= 105)
				acerto = 48;
			else
				acerto = 0;
			/**
			 * Acrescenta o caracter na variável valor.
			 */
			valor = valor + String.fromCharCode(tecla - acerto);
			/**
			 * Calcula a quantidade de colunas na variável valor.
			 */
			cols = valor.length - 1;
			/**
			 * Cria um Array para armazenar os caracteres ja digitados
			 */
			destino = new Array();
			/**
			 * Preenche o Array com os caracteres digitados formantando segundo
			 * a mascara definida.
			 */
			for ( var a = cMascara.length - 1; a >= 0; a--) {
				if (cMascara.charAt(a) == '9') {
					destino.push(valor.charAt(cols));
					cols--;
					if (cols < 0)
						break;
				} else {
					destino.push(cMascara.charAt(a));
				}
			}
			/**
			 * Inverte a array preenchido e retira os caracteres desnecessários:
			 **/
			destino.reverse();
			var temp = '';
			for ( var b = 0; b < destino.length; b++) {
				if (destino[b] != '')
					temp += destino[b];
			}
			objCampo.value = temp;
		}
	}
	return false;
}
function caracteres(c, n) {
	var r = '';
	for (var i = 0; i < n; i++) {
		r = r + c;
	}
	return r;
}
