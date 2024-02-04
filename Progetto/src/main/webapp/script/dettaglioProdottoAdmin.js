function noValidazione()
{
	document.getElementById("formDettagli").setAttribute("onsubmit", "return validazioneModificaAdmin()");
	return false;
}

function abilitaCampi()
{
	$("input, select, option", "#formDettagli").prop('disabled',false);
}

function cambiaImmagineAdmin(id,prodotto,quantità) 
{
	var imgElemento=document.getElementById(id);
	//Aggiunta per il modal
	var colore=imgElemento.getAttribute("title");
	$('#coloreProdottoAttivo').attr("value", colore);
	$('#myBtn').attr("value", colore);
	var carta=imgElemento.classList[1];
	var indice=carta.indexOf("-");
	var numero=carta.substring(indice+1);
	var img="#img-"+numero;
	document.querySelector(img).src = `./img/prodotti/${id}`;
	$('#immagineProdotto').attr("value", $(img).attr("src"));
	if(!imgElemento.classList.contains('active-color'))
	{
		imgElemento.classList.add('active-color');
		var carta="card-"+numero;
		var elemento=document.getElementById(carta);
		var colori=elemento.querySelectorAll('.color');
		for(var i=0; i<colori.length; i++)
		{
			if(colori[i].classList.contains('active-color'))
			{
				colori[i].classList.remove('active-color');
			}
			imgElemento.classList.add('active-color');
		}
	}
	var elementoPrezzoProdotto=document.getElementById("prezzo");
	$('#prezzoProdotto').attr("value", prodotto);
	var elementoQuantità=document.getElementById("quantità");
	if(quantità<=0)
	{
		elementoQuantità.innerHTML=elementoQuantità.innerHTML+'<option value=0>'+0+'</option>';
		$('#disponibilità').html("Prodotto non disponibile");
		$('#myBtn').prop("disabled", true);
	}
	else
	{
		$('#disponibilità').html("");
		$('#myBtn').prop("disabled", false);
		$('#quantitàProdotto').attr("value", quantità);
	}
}

function validazioneModificaAdmin()
{
	var fileOK, marcaOK, nomeOK, prezzoOK, quantitàOK, codiceProdottoOK, nomeColoreOK;
	
	var file= $('#etichettaSceltaFile').html().trim();
	if(file=="Inserisci il file")
	{
		$('#erroreFile').html("Devi inserire l'immagine del prodotto");
		fileOK=false;
	}
	else
	{
		$('#erroreFile').html("");
		fileOK=true;
	}
	
	if(marca()==true)
	{
		$("#marcaProdotto").removeClass("bordoErrore");
		$('#erroreMarca').html("");
		marcaOK=true;
	}
	else
	{
		$('#erroreMarca').html("Errore: la marca non può essere vuota");
		$("#marcaProdotto").addClass("bordoErrore");
		marcaOK=false;
	}
	
	if(nome()==true)
	{
		$("#nomeProdotto").removeClass("bordoErrore");
		$('#erroreNome').html("");
		nomeOK=true;
	}
	else
	{
		$('#erroreNome').html("Errore: il nome non può essere vuoto");
		$("#nomeProdotto").addClass("bordoErrore");
		nomeOK=false;
	}
	
	if(prezzo()==true)
	{
		$("#prezzoProdotto").removeClass("bordoErrore");
		$('#errorePrezzo').html("");
		prezzoOK=true;
	}
	else
	{
		$('#errorePrezzo').html("Errore: il prezzo non è valido");
		$("#prezzoProdotto").addClass("bordoErrore");
		prezzoOK=false;
	}
	
	if(quantità()==true)
	{
		$("#quantitàProdotto").removeClass("bordoErrore");
		$('#erroreQuantità').html("");
		quantitàOK=true;
	}
	else
	{
		$('#erroreQuantità').html("Errore: la quantità può avere solo numeri da 0 in poi e non può essere vuota.");
		$("#quantitàProdotto").addClass("bordoErrore");
		quantitàOK=false;
	}
	
	if(codiceProdotto()==true)
	{
		$("#codiceProdotto").removeClass("bordoErrore");
		$('#erroreCodice').html("");
		codiceProdottoOK=true;
	}
	else
	{
		$('#erroreCodice').html("Errore: il codice prodotto non può essere vuoto.");
		$("#codiceProdotto").addClass("bordoErrore");
		codiceProdottoOK=false;
	}
	
	if(nomeColore()==true)
	{
		$("#nomeColoreProdotto").removeClass("bordoErrore");
		$('#erroreColore').html("");
		nomeColoreOK=true;
	}
	else
	{
		$('#erroreColore').html("Errore: il nome del colore non può essere vuoto.");
		$("#nomeColoreProdotto").addClass("bordoErrore");
		nomeColoreOK=false;
	}
	
	if(fileOK==false && marcaOK==false && nomeOK==false && prezzoOK==false && quantitàOK==false && codiceProdottoOK==false && nomeColoreOK==false)
	{
		return false;
	}
	else if(fileOK==false || marcaOK==false || nomeOK==false || prezzoOK==false || quantitàOK==false || codiceProdottoOK==false || nomeColoreOK==false)
	{
		return false;
	}
	else
	{
		return true;
	}
}

function marca()
{
	var marcaForm= document.forms["formDettagli"]["marcaProdotto"].value.trim();
	if(marcaForm.length<=50)
	{
		var espressioneRegolareMarca= /^[A-Za-z0-9]+\-*[A-Za-z0-9]+$/;
		if(marcaForm.match(espressioneRegolareMarca))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

function nome()
{
	var nomeForm= document.forms["formDettagli"]["nomeProdotto"].value.trim();
	if(nomeForm.length<=30)
	{
		var espressioneRegolareNome= /^[A-Za-z0-9\s]+\-*[A-Za-z0-9\s]+$/;
		if(nomeForm.match(espressioneRegolareNome))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

function prezzo()
{
	var prezzoForm= document.forms["formDettagli"]["prezzoProdotto"].value.trim();
	if(prezzoForm.length<=10)
	{
		var espressioneRegolarePrezzo= /^[0-9]+[,|.]?[0-9]+$/;
		if(prezzoForm.match(espressioneRegolarePrezzo))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

function quantità()
{
	var quantitàForm= document.forms["formDettagli"]["quantitàProdotto"].value.trim();
	var espressioneRegolareQuantità= /^[0-9]+$/;
	if(quantitàForm.match(espressioneRegolareQuantità))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function codiceProdotto()
{
	var codiceProdottoForm= document.forms["formDettagli"]["codiceProdotto"].value.trim();
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

function nomeColore()
{
	var nomeColoreForm= document.forms["formDettagli"]["nomeColoreProdotto"].value.trim();
	var espressioneRegolareNomeColore= /^[A-Za-z\s\W]+$/;
	if(nomeColoreForm.match(espressioneRegolareNomeColore))
	{
		return true;
	}
	else
	{
		return false;
	}
}

$(document).ready(function () 
{
	$('#sceltaFile').change(function () 
	{
		var i = $(this).prev('label').clone();
		var file = $('#sceltaFile')[0].files[0].name;
		$('#etichettaSceltaFile').html(file);
		$('#erroreFile').html("");
	}); 
});

function colore()
{
	var colorePremuto = $('#coloreProdotto').find(":selected").val();
	var split= colorePremuto.split(',');
	var immagine= split[0];
	$('#immagineProdotto').attr("value", immagine);
	$('#etichettaSceltaFile').text(immagine);
	$('#sceltaFile').attr("value", "./img/prodotti/"+immagine);
	var prezzo= split[1];
	$('#prezzoProdotto').attr("value", prezzo);
	var nomeColore= split[2];
	$('#nomeColoreProdotto').attr("value", nomeColore);
	var quantità= split[3];
	$('#quantitàProdotto').attr("value", quantità);
	var codiceProdotto= split[4];
	$('#codiceProdotto').attr("value", codiceProdotto);
}

function aggiungiColore()
{
	var ultimaOption = $('#coloreProdotto option:last-child').html();
	var lunghezza= ultimaOption.length;
	var indice= ultimaOption.substring(ultimaOption.length-1);
	var nuovoIndice= Number(indice)+1;
	indice+=1;
	var selectColore= document.getElementById("coloreProdotto");
	var option= document.createElement("option");
	option.text= "colore"+nuovoIndice;
	selectColore.add(option);
	$('#coloreProdotto option:last-child').attr('selected','selected');
	$('#prezzoProdotto').prop('disabled',false);
	$('#prezzoProdotto').attr("value", "");
	$('#nomeColoreProdotto').prop('disabled',false);
	$('#nomeColoreProdotto').attr("value", "");
	$('#quantitàProdotto').prop('disabled',false);
	$('#quantitàProdotto').attr("value", "");
	$('#codiceProdotto').prop('disabled',false);
	$('#codiceProdotto').attr("value", "");
	document.getElementById("formDettagli").setAttribute("onsubmit", "return validazioneModificaAdmin()");
	$('#etichettaSceltaFile').prop('disabled',false);
	$('#etichettaSceltaFile').html("Inserisci il file");
	$('#bottoneColore').remove();
	$('#myBtn').html("Aggiungi colore");
	$('#myBtn').attr("value", "insert");
	$('#sceltaFile').prop('disabled',false);
}