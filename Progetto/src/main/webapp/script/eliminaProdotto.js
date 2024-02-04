function validazioneElimina()
{
	var coloreOK;
	
	if(coloreProdotto()==true)
	{
		$("#coloreProdotto").removeClass("bordoErrore");
		$('#erroreColore').html("");
		coloreOK=true;
	}
	else
	{
		$('#erroreColore').html("Errore: devi selezionare il genere del prodotto");
		$("#coloreProdotto").addClass("bordoErrore");
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

function aggiorna(oggettoSelezionato)
{
	var valoreOption= oggettoSelezionato.value;
	if(valoreOption!=-1)
	{
		var valoriColore= $("#colore"+valoreOption).val();
		var informazioni= valoriColore.split(',');
		$("#immagine").html("Immagine: "+informazioni[0]);
		$("#prezzo").html("Prezzo: "+informazioni[1]+" €");
		$("#nomeColore").html("Nome colore: "+informazioni[2]);
		$("#quantità").html("Quantità: "+informazioni[3]);
		$("#codiceProdotto").html("Codice prodotto: "+informazioni[4]);
	}
	else
	{
		var colori= $("#colore-1").val();
		var variId= colori.split("/");
		const immagini=[];
		const prezzi=[];
		const nomiColore=[];
		const quantità=[];
		const codiciProdotto=[];
		$("#immagine").html("Immagine: ");
		$("#prezzo").html("Prezzo: ");
		$("#nomeColore").html("Nome colore: ");
		$("#quantità").html("Quantità: ");
		$("#codiceProdotto").html("Codice prodotto: ");
		for(var i=1; i<=variId[0]; i++)
		{
			var valoriColore= $("#colore"+variId[i]).val();
			var informazioni= valoriColore.split(',');
			immagini[i-1]= informazioni[0];
			$("#immagine").append(informazioni[0]+"<br>");
			prezzi[i-1]= informazioni[1];
			$("#prezzo").append(informazioni[1]+" € ")
			nomiColore[i-1]= informazioni[2];
			$("#nomeColore").append(informazioni[2]+" ");
			quantità[i-1]= informazioni[3];
			$("#quantità").append(informazioni[3]+" ");
			codiciProdotto[i-1]= informazioni[4];
			$("#codiceProdotto").append(informazioni[4]+" ");
		}
	}
}