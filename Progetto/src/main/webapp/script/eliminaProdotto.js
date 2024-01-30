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
	console.log(valoreOption);
	if(valoreOption!=-1)
	{
		var valoriColore= $("#colore"+valoreOption).val();
		var informazioni= valoriColore.split(',');
		console.log(informazioni);
		$("#immagine").html("Immagine: "+informazioni[0]);
		$("#prezzo").html("Prezzo: "+informazioni[1]+" €");
		$("#nomeColore").html("Nome colore: "+informazioni[2]);
		$("#quantità").html("Quantità: "+informazioni[3]);
		$("#codiceProdotto").html("Codice prodotto: "+informazioni[4]);
	}
	else
	{
		var colori= $("#colore-1").val();
		console.log(colori);
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
		for(var i=1; i<=colori; i++)
		{
			var valoriColore= $("#colore"+i).val();
			var informazioni= valoriColore.split(',');
			console.log(informazioni);
			immagini[i-1]= informazioni[0];
			console.log(immagini);
			$("#immagine").append(informazioni[0]+"<br>");
			prezzi[i-1]= informazioni[1];
			console.log(prezzi);
			$("#prezzo").append(informazioni[1]+" € ")
			nomiColore[i-1]= informazioni[2];
			console.log(nomiColore);
			$("#nomeColore").append(informazioni[2]+" ");
			quantità[i-1]= informazioni[3];
			console.log(quantità);
			$("#quantità").append(informazioni[3]+" ");
			codiciProdotto[i-1]= informazioni[4];
			console.log(codiciProdotto);
			$("#codiceProdotto").append(informazioni[4]+" ");
		}
	}
}