function opzioni()
{
	var opzione = $('#opzione').find(":selected").val();
	if(opzione=="Data")
	{
		$('#data').removeClass("nascondi");
		$('#data').addClass("fields");
		$('#utente').addClass("nascondi");
	}
	else if(opzione=="Utente")
	{
		$('#utente').removeClass("nascondi");
		$('#utente').addClass("fields");
		$('#data').addClass("nascondi");
	}
	$('#bottone').removeClass("nascondi");
}

function validazioneOrdini()
{
	var opzioneOK, data1OK, data2OK, utenteOK;
	
	if(opzione()==true)
	{
		$("#opzione").removeClass("bordoErrore");
		$('#erroreOpzione').html("");
		opzioneOK=true;
	}
	else
	{
		$('#erroreOpzione').html("Errore: devi selezionare se vuoi visualizzare gli ordini per data o per utente");
		$("#opzione").addClass("bordoErrore");
		opzioneOK=false;
	}
	
	var opzioneSelezionata = $('#opzione').find(":selected").val();
	if(opzioneSelezionata=="Data")
	{
		if(data("data1")==true)
		{
			$("#data1").removeClass("bordoErrore");
			$('#erroreData1').html("");
			data1OK=true;
		}
		else
		{
			$('#erroreData1').html("Errore: devi selezionare da quale data visualizzare gli ordini");
			$("#data1").addClass("bordoErrore");
			data1OK=false;
		}
		if(data("data2")==true)
		{
			$("#data2").removeClass("bordoErrore");
			$('#erroreData2').html("");
			data2OK=true;
		}
		else
		{
			$('#erroreData2').html("Errore: devi selezionare da quale data visualizzare gli ordini");
			$("#data2").addClass("bordoErrore");
			data2OK=false;
		}
	}
	else if(opzioneSelezionata=="Utente")
	{
		if(utente()==true)
		{
			$("#utenteOption").removeClass("bordoErrore");
			$('#erroreUtente').html("");
			utenteOK=true;
		}	
		else
		{
			$('#erroreUtente').html("Errore: devi selezionare un utente");
			$("#utenteOption").addClass("bordoErrore");
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
	var utenteForm= document.forms["formOrdini"]["utenteOption"].value.trim();
	if(utenteForm!="Seleziona l'utente")
	{
		return true;
	}
	else
	{
		return false;
	}
}