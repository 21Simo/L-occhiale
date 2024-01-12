function validazioneElimina()
{
	var coloreOK;
	
	if(coloreProdotto()==true)
	{
		$('#erroreColore').html("");
		coloreOK=true;
	}
	else
	{
		$('#erroreColore').html("Errore: devi selezionare il genere del prodotto");
		$("#coloreProdotto").css("border-color", "red");
		coloreOK=false;
	}
	
	if(coloreOK==false)
	{
		return false;
	}
	else
	{
		return true;
	}
}

function coloreProdotto()
{
	var coloreProdottoForm= document.forms["formElimina"]["coloreProdotto"].value.trim();
	if(coloreProdottoForm!="Inserisci il colore da eliminare o tutto il prodotto")
	{
		return true;
	}
	else
	{
		return false;
	}
}