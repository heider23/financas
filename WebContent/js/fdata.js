function data(tipo) {
	hoje = new Date();
	var meses = new Array("Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro");
	dia = hoje.getDate();
	mes = hoje.getMonth() * 1 + 1;
	zero = (mes < 10) ? '0':'';
	ano = hoje.getYear() * 1;
	if (ano < 1000)
		ano = 1900 + ano;
	switch (tipo) {
		case 1:
			return (dia + " de " + meses[mes - 1] + " de " + ano);
		default:
			return (dia + '/' + zero + mes + '/' + ano);		
	}
}