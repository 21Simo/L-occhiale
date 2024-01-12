function opzioni()
{
	var opzione = $('#opzione').find(":selected").val();
	console.log(opzione);
	if(opzione=="Data")
	{
		$('#data').removeClass("nascondi");
		$('#data').addClass("fields");
	}
	else if(opzione=="Utente")
	{
		$('#utente').removeClass("nascondi");
		$('#utente').addClass("fields");
	}
	$('#bottone').removeClass("nascondi");
}

function validazioneOrdini()
{
	var opzioneOK, data1OK, data2OK, utenteOK;
	
	if(opzione()==true)
	{
		$('#erroreOpzione').html("");
		opzioneOK=true;
	}
	else
	{
		$('#erroreOpzione').html("Errore: devi selezionare se vuoi visualizzare gli ordini per data o per utente");
		$("#opzione").css("border-color", "red");
		opzioneOK=false;
	}
	
	var opzioneSelezionata = $('#opzione').find(":selected").val();
	console.log(opzioneSelezionata);
	if(opzioneSelezionata=="Data")
	{
		if(data("data1")==true)
		{
			$('#erroreData1').html("");
			data1OK=true;
		}
		else
		{
			$('#erroreData1').html("Errore: devi selezionare da quale data visualizzare gli ordini");
			$("#data1").css("border-color", "red");
			data2OK=false;
		}
		if(data("data2")==true)
		{
			$('#erroreData2').html("");
			data2OK=true;
		}
		else
		{
			$('#erroreData2').html("Errore: devi selezionare da quale data visualizzare gli ordini");
			$("#data2").css("border-color", "red");
			data2OK=false;
		}
	}
	else if(opzioneSelezionata=="Utente")
	{
		if(utente()==true)
		{
			$('#erroreUtente').html("");
			utenteOK=true;
		}	
		else
		{
			$('#erroreUtente').html("Errore: devi selezionare un utente");
			$("#utenteOption").css("border-color", "red");
			utenteOK=false;
		}
	}
	
	
	if(opzioneOK==false && data1OK==false && data2OK==false && utenteOK==false)
	{
		return false;
	}
	else if(opzioneOK==false || data1OK==false || data2OK==false || utenteOK==false)
	{
		return false;
	}
	else
	{
		return true;
	}
}

function opzione()
{
	var opzioneForm= document.forms["formOrdini"]["opzione"].value.trim();
	if(opzioneForm!="Seleziona se vuoi visualizzare gli ordini per data o per utente")
	{
		return true;
	}
	else
	{
		return false;
	}
}

function data(data)
{
	var dataForm= document.forms["formOrdini"][data].value.trim();
	var espressioneRegolareData= /^[0-9]{4}\-[0-9]{1,2}\-[0-9]{1,2}$/;
	if(dataForm.match(espressioneRegolareData))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function utente()
{
	var utenteForm= document.forms["formOrdini"]["utente"].value.trim();
	if(utenteForm!="Seleziona l'utente")
	{
		return true;
	}
	else
	{
		return false;
	}
}