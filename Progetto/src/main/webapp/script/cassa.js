function validazionePagamento()
{
	var nomeOk, numeroCartaOk, scadenzaOk, cvvOk;
	
	if(nome()==true)
	{
		nomeOK=true;
	}
	else
	{
		$('#erroreNome').html("Errore: il nome non può contenere un numero");
		$("#nome").css("border-color", "red");
		nomeOK=false;
	}
	
	if(numeroCarta()==true)
	{
		numeroCartaOk=true;
	}
	else
	{
		$('#erroreNumeroCarta').html("Errore: il numero della carta non è corretto. Non può essere più lungo di 16 caratteri.");
		$("#numeroCarta").css("border-color", "red");
		numeroCartaOk=false;
	}
	
	if(scadenza()==true)
	{
		scadenzaOk=true;
	}
	else
	{
		$('#erroreScadenza').html("Errore: la scadenza non è corretta");
		$("#scadenza").css("border-color", "red");
		scadenzaOk=false;
	}
	
	if(cvv()==true)
	{
		cvvOk=true;
	}
	else
	{
		$('#erroreCVV').html("Errore: il CVV non è corretto. Non può essere più lungo di 4 caratteri.");
		$("#cvv").css("border-color", "red");
		cvvOk=false;
	}
	
	if(nomeOK==false && numeroCartaOk==false && scadenzaOk==false && cvvOk==false)
	{
		return false;
	}
	else if(nomeOK==false || numeroCartaOk==false || scadenza==false || cvvOk==false)
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