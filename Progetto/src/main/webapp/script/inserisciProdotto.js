function aggiungiCampi()
{
	var numeroColori=$('#idColore').val();
	console.log(numeroColori);
	var espressioneRegolareQuantità= /^[0-9]+$/;
	if(numeroColori.match(espressioneRegolareQuantità) && numeroColori>0)
	{
		$('#erroreColore').html("");
		$('#idColore').removeClass("bordoErrore");
		$('#idColore').prop('disabled',true);
		$('#idColore').attr("value", numeroColori);
		$('#bottoneColore').remove();
		for(i=numeroColori; i>=1; i--)
		{
			$('#colori').after('<div id="colore'+i+'"class="details personal"><span class="title">Colore'+i+'</span><div class="fields"><div class="input-field"><label>Immagine: </label><input type="text" id="immagineProdotto" name="immagineProdotto'+i+'" value="" hidden="true"><input name="immagine'+i+'" type="file" id="sceltaFile'+i+'" accept="image/*" value=""/><span class="errore" id="erroreFile'+i+'"></span></div><div class="input-field"><label>Prezzo: </label><input type="text" id="prezzoProdotto'+i+'" name="prezzoProdotto'+i+'" value="" placeholder="Inserisci il prezzo"><p class="errore" id="errorePrezzo'+i+'"></p></div><div class="input-field"><label>Nome colore: </label><input type="text" id="nomeColoreProdotto'+i+'" name="nomeColoreProdotto'+i+'" value="" placeholder="Inserisci il colore"><p class="errore" id="erroreColore'+i+'"></p></div><div class="input-field"><label for="quantità" class="labelQuantita"> Quantità: </label><input type="text" id="quantitàProdotto'+i+'" name="quantitaProdotto'+i+'" value="" placeholder="Inserisci la quantità"><p class="errore" id="erroreQuantità'+i+'"></p></div><div class="input-field"><label>Codice prodotto: </label><input type="text" id="codiceProdotto'+i+'" name="codiceProdotto'+i+'" value="" placeholder="Inserisci il codice del prodotto"><p class="errore" id="erroreCodice'+i+'"></p></div></div></div>');
		}
		$('#colore'+numeroColori).after('<button type="submit" class="bottoneDettagli" name="bottoneInserisci" value="" id="bottoneInserisci" onclick="validazioneInserisci()">Inserisci il prodotto</button>');
	}
	else
	{
		$('#erroreColore').html("Devi inserire un numero e deve essere maggiore di 0.");
		$('#idColore').addClass("bordoErrore");
	}
}

function validazioneInserisci()
{
	var fileOK, nomeProdottoOK, marcaProdottoOK, genereProdottoOK, prezzoProdottoOK, nomeColoreProdottoOK, quantitàProdottoOK, codiceProdottoOK;
	
	var numeroColori=$('#idColore').val();
	const filee= [];
	for(i=1; i<=numeroColori; i++)
	{
		console.log(i);
		filee[i]= $('#sceltaFile'+i).val();
		if(filee[i]!="")
		{
			$('#erroreFile'+i).removeClass("bordoErrore");
			$('#erroreFile'+i).html("");
			fileOK=true;
		}
		else
		{
			$('#erroreFile'+i).html("Devi inserire l'immagine del prodotto");
			$('#erroreFile'+i).addClass("bordoErrore");
			fileOK=false;
		}
		
		if(prezzoProdotto(i)==true)
		{
			$('#errorePrezzo'+i).html("");
			$('#prezzoProdotto'+i).removeClass("bordoErrore");
			prezzoProdottoOK=true;
		}
		else
		{
			$('#errorePrezzo'+i).html("Errore: il prezzo non può essere vuoto");
			$("#prezzoProdotto"+i).addClass("bordoErrore");
			prezzoProdottoOK=false;
		}
		
		if(nomeColoreProdotto(i)==true)
		{
			$('#erroreColore'+i).html("");
			$('#nomeColoreProdotto'+i).removeClass("bordoErrore");
			nomeColoreProdottoOK=true;
		}
		else
		{
			$('#erroreColore'+i).html("Errore: il nome del colore non può essere vuoto");
			$("#nomeColoreProdotto"+i).addClass("bordoErrore");
			nomeColoreProdottoOK=false;
		}
		
		if(quantitàProdotto(i)==true)
		{
			$('#erroreQuantità'+i).html("");
			$('#quantitàProdotto'+i).removeClass("bordoErrore");
			quantitàProdottoOK=true;
		}
		else
		{
			$('#erroreQuantità'+i).html("Errore: il nome del colore non può essere vuoto");
			$("#quantitàProdotto"+i).addClass("bordoErrore");
			quantitàProdottoOK=false;
		}
		
		if(codiceProdotto(i)==true)
		{
			$('#erroreCodice'+i).html("");
			$('#codiceProdotto'+i).removeClass("bordoErrore");
			codiceProdottoOK=true;
		}
		else
		{
			$('#erroreCodice'+i).html("Errore: il codice del prodotto non può essere vuoto");
			$("#codiceProdotto"+i).addClass("bordoErrore");
			codiceProdottoOK=false;
		}
	}
	console.log(filee);
	console.log(fileOK);
	
	if(nomeProdotto()==true)
	{
		$('#erroreNome').html("");
		$("#nomeProdotto").removeClass("bordoErrore");
		nomeProdottoOK=true;
	}
	else
	{
		$('#erroreNome').html("Errore: il nome non può essere vuoto");
		$("#nomeProdotto").addClass("bordoErrore");
		nomeProdottoOK=false;
	}
	
	if(marcaProdotto()==true)
	{
		$('#erroreMarca').html("");
		$("#marcaProdotto").removeClass("bordoErrore");
		marcaProdottoOK=true;
	}
	else
	{
		$('#erroreMarca').html("Errore: la marca non può essere vuota");
		$("#marcaProdotto").addClass("bordoErrore");
		marcaProdottoOK=false;
	}
	
	if(genereProdotto()==true)
	{
		$('#erroreGenere').html("");
		$("#genereProdotto").removeClass("bordoErrore");
		genereProdottoOK=true;
	}
	else
	{
		$('#erroreGenere').html("Errore: devi selezionare il genere del prodotto");
		$("#genereProdotto").addClass("bordoErrore");
		genereProdottoOK=false;
	}
	
	
	if(fileOK==false && nomeProdottoOK==false && marcaProdottoOK==false && genereProdottoOK==false && prezzoProdottoOK==false && nomeColoreProdottoOK==false && quantitàProdottoOK==false && codiceProdottoOK==false)
	{
		return false;
	}
	else if(fileOK==false || nomeProdottoOK==false || marcaProdottoOK==false || genereProdottoOK==false || prezzoProdottoOK==false || nomeColoreProdottoOK==false || quantitàProdottoOK==false || codiceProdottoOK==false)
	{
		return false;
	}
	else
	{
		$('#idColore').prop('disabled',false);
		return true;
	}
}

function nomeProdotto()
{
	var nomeProdottoForm= document.forms["formInserisci"]["nomeProdotto"].value.trim();
	var espressioneRegolareNomeProdotto= /^[A-Za-z0-9\s]+\-*[A-Za-z0-9\s]+$/;
	if(nomeProdottoForm.match(espressioneRegolareNomeProdotto))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function marcaProdotto()
{
	var marcaProdottoForm= document.forms["formInserisci"]["marcaProdotto"].value.trim();
	var espressioneRegolareMarcaProdotto= /^[A-Za-z0-9]+\-*[A-Za-z0-9]+$/;
	if(marcaProdottoForm.match(espressioneRegolareMarcaProdotto))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function genereProdotto()
{
	var genereProdottoForm= document.forms["formInserisci"]["genereProdotto"].value.trim();
	console.log(genereProdottoForm);
	if(genereProdottoForm=="Uomo" || genereProdottoForm=="Donna")
	{
		return true;
	}
	else
	{
		return false;
	}
}

function prezzoProdotto(indice)
{
	var prezzoProdottoForm= document.forms["formInserisci"]["prezzoProdotto"+indice].value.trim();
	console.log(prezzoProdottoForm);
	var espressioneRegolarePrezzoProdotto= /^[0-9]+[,|.]?[0-9]+$/;
	if(prezzoProdottoForm.match(espressioneRegolarePrezzoProdotto))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function nomeColoreProdotto(indice)
{
	var nomeColoreProdottoForm= document.forms["formInserisci"]["nomeColoreProdotto"+indice].value.trim();
	var espressioneRegolareNomeColoreProdotto= /^[A-Za-z\s\W]+$/;
	if(nomeColoreProdottoForm.match(espressioneRegolareNomeColoreProdotto))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function quantitàProdotto(indice)
{
	var quantitàProdottoForm= document.forms["formInserisci"]["quantitàProdotto"+indice].value.trim();
	var espressioneRegolareQuantitàProdotto= /^[0-9]+$/;
	if(quantitàProdottoForm.match(espressioneRegolareQuantitàProdotto))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function codiceProdotto(indice)
{
	var codiceProdottoForm= document.forms["formInserisci"]["codiceProdotto"+indice].value.trim();
	var espressioneRegolareCodiceProdotto= /^[A-Za-z0-9\s\W]+$/;
	if(codiceProdottoForm.match(espressioneRegolareCodiceProdotto))
	{
		return true;
	}
	else
	{
		return false;
	}
}