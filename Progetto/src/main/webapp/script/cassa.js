function validazionePagamento()
{
	var nomeOk, numeroCartaOk, scadenzaOk, cvvOk;
	
	if(nome()==true)
	{
		$("#nome").removeClass("bordoErrore");
		$('#erroreNome').html("");
		nomeOk=true;
	}
	else
	{
		$('#erroreNome').html("Errore: il nome non può contenere un numero");
		$("#nome").addClass("bordoErrore");
		nomeOk=false;
	}
	
	if(numeroCarta()==true)
	{
		$("#numeroCarta").removeClass("bordoErrore");
		$('#erroreNumeroCarta').html("");
		numeroCartaOk=true;
	}
	else
	{
		$('#erroreNumeroCarta').html("Errore: il numero della carta non è corretto. Non può essere più lungo di 16 caratteri.");
		$("#numeroCarta").addClass("bordoErrore");
		numeroCartaOk=false;
	}
	
	if(scadenza()==true)
	{
		$("#scadenza").removeClass("bordoErrore");
		$('#erroreScadenza').html("");
		scadenzaOk=true;
	}
	else
	{
		$('#erroreScadenza').html("Errore: la scadenza non è corretta");
		$("#scadenza").addClass("bordoErrore");
		scadenzaOk=false;
	}
	
	if(cvv()==true)
	{
		$("#cvv").removeClass("bordoErrore");
		$('#erroreCVV').html("");
		cvvOk=true;
	}
	else
	{
		$('#erroreCVV').html("Errore: il CVV non è corretto. Non può essere più lungo di 4 caratteri.");
		$("#cvv").addClass("bordoErrore");
		cvvOk=false;
	}
	
	if(nomeOk==false && numeroCartaOk==false && scadenzaOk==false && cvvOk==false)
	{
		return false;
	}
	else if(nomeOk==false || numeroCartaOk==false || scadenzaOk==false || cvvOk==false)
	{
		return false;
	}
	else
	{		
		return true;
	}
}

function nome()
{
	var nomeForm= document.forms["form"]["nome"].value.trim();
	var espresioneRegolareNome= /^[A-Z a-z]+$/;
	if(nomeForm.match(espresioneRegolareNome))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function numeroCarta()
{
	var numeroCartaForm= document.forms["form"]["numeroCarta"].value.trim();
	var espressioneRegolareNumeroCarta= /^[0-9]{16}$/;
	if(numeroCartaForm.match(espressioneRegolareNumeroCarta))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function scadenza()
{
	var scadenzaForm= document.forms["form"]["scadenza"].value.trim();
	var espressioneRegolareScadenza= /^[0-9]{4}\-[0-9]{2}$/;
	if(scadenzaForm.match(espressioneRegolareScadenza))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function cvv()
{
	var cvvForm= document.forms["form"]["cvv"].value.trim();
	var espressioneRegolareCVV= /^[0-9]{3,4}$/;
	if(cvvForm.match(espressioneRegolareCVV))
	{
		return true;
	}
	else
	{
		return false;
	}
}