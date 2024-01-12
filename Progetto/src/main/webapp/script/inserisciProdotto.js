function aggiungiCampi()
{
	var numeroColori=$('#idColore').val();
	console.log(numeroColori);
	var espressioneRegolareQuantità= /^[0-9]+$/;
	if(numeroColori.match(espressioneRegolareQuantità) && numeroColori>0)
	{
		$('#erroreColore').html("");
		$('#idColore').prop('disabled',true);
		$('#idColore').attr("value", numeroColori);
		$('#bottoneColore').remove();
		for(i=numeroColori; i>=1; i--)
		{
			$('#colori').after('<div id="colore'+i+'"class="details personal"><span class="title">Colore'+i+'</span><div class="fields"><div class="input-field"><label>Immagine: </label><input type="text" id="immagineProdotto" name="immagineProdotto'+i+'" value="" hidden="true"><input name="immagine'+i+'" type="file" id="sceltaFile'+i+'" accept="image/*" value=""/><span id="erroreFile'+i+'"></span></div><div class="input-field"><label>Prezzo: </label><input type="text" id="prezzoProdotto'+i+'" name="prezzoProdotto'+i+'" value="" placeholder="Inserisci il prezzo"><p id="errorePrezzo'+i+'"></p></div><div class="input-field"><label>Nome colore: </label><input type="text" id="nomeColoreProdotto'+i+'" name="nomeColoreProdotto'+i+'" value="" placeholder="Inserisci il colore"><p id="erroreColore'+i+'"></p></div><div class="input-field"><label for="quantità" class="labelQuantita"> Quantità: </label><input type="text" id="quantitàProdotto'+i+'" name="quantitaProdotto'+i+'" value="" placeholder="Inserisci la quantità"><p id="erroreQuantità'+i+'"></p></div><div class="input-field"><label>Codice prodotto: </label><input type="text" id="codiceProdotto'+i+'" name="codiceProdotto'+i+'" value="" placeholder="Inserisci il codice del prodotto"><p id="erroreCodice'+i+'"></p></div></div></div>');
		}
		$('#colore'+numeroColori).after('<button type="submit" class="bottoneDettagli" name="bottoneInserisci" value="" id="bottoneInserisci" onclick="validazioneInserisci()">Inserisci il prodotto</button>');
	}
	else
	{
		$('#erroreColore').html("Devi inserire un numero e deve essere maggiore di 0.");
		$('#idColore').css("border-color", "red");
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
			fileOK=true;
		}
		else
		{
			$('#erroreFile'+i).html("Devi inserire l'immagine del prodotto");
			$('#erroreFile'+i).css("color", "red");
			fileOK=false;
		}
		
		if(prezzoProdotto(i)==true)
		{
			$('#errorePrezzo'+i).html("");
			prezzoProdottoOK=true;
		}
		else
		{
			$('#errorePrezzo'+i).html("Errore: il prezzo non può essere vuoto");
			$("#prezzoProdotto"+i).css("border-color", "red");
			prezzoProdottoOK=false;
		}
		
		if(nomeColoreProdotto(i)==true)
		{
			$('#erroreColore'+i).html("");
			nomeColoreProdottoOK=true;
		}
		else
		{
			$('#erroreColore'+i).html("Errore: il nome del colore non può essere vuoto");
			$("#nomeColoreProdotto"+i).css("border-color", "red");
			nomeColoreProdottoOK=false;
		}
		
		if(quantitàProdotto(i)==true)
		{
			$('#erroreQuantità'+i).html("");
			quantitàProdottoOK=true;
		}
		else
		{
			$('#erroreQuantità'+i).html("Errore: il nome del colore non può essere vuoto");
			$("#quantitàProdotto"+i).css("border-color", "red");
			quantitàProdottoOK=false;
		}
		
		if(codiceProdotto(i)==true)
		{
			$('#erroreCodice'+i).html("");
			codiceProdottoOK=true;
		}
		else
		{
			$('#erroreCodice'+i).html("Errore: il codice del prodotto non può essere vuoto");
			$("#codiceProdotto"+i).css("border-color", "red");
			codiceProdottoOK=false;
		}
	}
	console.log(filee);
	console.log(fileOK);
	
	if(nomeProdotto()==true)
	{
		$('#erroreNome').html("");
		nomeProdottoOK=true;
	}
	else
	{
		$('#erroreNome').html("Errore: il nome non può essere vuoto");
		$("#nomeProdotto").css("border-color", "red");
		nomeProdottoOK=false;
	}
	
	if(marcaProdotto()==true)
	{
		$('#erroreMarca').html("");
		marcaProdottoOK=true;
	}
	else
	{
		$('#erroreMarca').html("Errore: la marca non può essere vuota");
		$("#marcaProdotto").css("border-color", "red");
		marcaProdottoOK=false;
	}
	
	if(genereProdotto()==true)
	{
		$('#erroreGenere').html("");
		genereProdottoOK=true;
	}
	else
	{
		$('#erroreGenere').html("Errore: devi selezionare il genere del prodotto");
		$("#genereProdotto").css("border-color", "red");
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