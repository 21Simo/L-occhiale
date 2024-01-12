function noValidazione()
{
	document.getElementById("formDettagli").setAttribute("onsubmit", "return validazioneModificaAdmin()");
	return false;
}

function abilitaCampi()
{
	console.log("OK");
	$("input, select, option", "#formDettagli").prop('disabled',false);
}

function cambiaImmagineAdmin(id,prodotto,quantità) 
{
	console.log("onclick");
	console.log("Id: "+id);
	var prova=document.getElementById(id);
	console.log("elemento cliccato: ");
	console.log(prova);
	//Aggiunta per il modal
	var colore=prova.getAttribute("title");
	console.log(colore);
	$('#coloreProdottoAttivo').attr("value", colore);
	$('#myBtn').attr("value", colore);
	var carta=prova.classList[1];
	console.log("Carta: "+carta);
	var indiceProva=carta.indexOf("-");
	//console.log("Indice dell'id: "+indiceProva);
	var provaNumero=carta.substring(indiceProva+1);
	console.log("Numero dell'id: "+provaNumero);
	var img="#img-"+provaNumero;
	console.log("Immagine: "+img);
	document.querySelector(img).src = `./img/prodotti/${id}`;
	console.log($(img).attr("src"));
	$('#immagineProdotto').attr("value", $(img).attr("src"));
	var elementoProva=document.getElementById(id);
	console.log("Elemento selezionato: ")
	console.log(elementoProva);
	console.log("Classi dell'elemento selezionato: ");
	console.log(elementoProva.classList);
	if(!elementoProva.classList.contains('active-color'))
	{
		console.log("No active color");
		elementoProva.classList.add('active-color');
		var carta="card-"+provaNumero;
		console.log("card + numero: "+carta);
		var elemento=document.getElementById(carta);
		console.log("Elemento selezionato: ");
		console.log(elemento);
		var colori=elemento.querySelectorAll('.color');
		console.log("Elemento con .color");
		console.log(colori);
		console.log("Lunghezza di elementi .color selezionati: "+colori.length);
		for(var i=0; i<colori.length; i++)
		{
			console.log("Classi degli elementi .color");
			console.log(colori[i].classList);
			if(colori[i].classList.contains('active-color'))
			{
				console.log("C'è la classe active-color");
				colori[i].classList.remove('active-color');
			}
			elementoProva.classList.add('active-color');
		}
	}
	console.log("Valore nome: ");
	console.log(prova.getAttribute("name"));	
	console.log("Valore title: ");
	console.log(prova.getAttribute("title"));
	console.log("Indice: "+provaNumero);
	console.log(prodotto);
	var elementoPrezzoProdotto=document.getElementById("prezzo");
	console.log(elementoPrezzoProdotto);
	$('#prezzoProdotto').attr("value", prodotto);
	//elementoPrezzoProdotto.innerHTML=prodotto+" €";
	var elementoQuantità=document.getElementById("quantità");
	console.log("Elemento quantità");
	console.log(elementoQuantità);
	//console.log(elementoQuantità.innerHTML);
	console.log("Parametro quantità");
	console.log(quantità);
	//elementoQuantità.innerHTML="";
	console.log("Inizializziamo l'innerHTML");
	//console.log(elementoQuantità.innerHTML);
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
	/*
	for (let i=1; i<=quantità; i++)
	{
		console.log(i);
		elementoQuantità.innerHTML=elementoQuantità.innerHTML+'<option value='+i+'>'+i+'</option>';
	}
	*/
}

function validazioneModificaAdmin()
{
	var fileOK, marcaOK, nomeOK, prezzoOK, quantitàOK, codiceProdottoOK, nomeColoreOK;
	
	var file= $('#etichettaSceltaFile').html().trim();
	if(file=="Inserisci il file")
	{
		$('#erroreFile').html("Devi inserire l'immagine del prodotto");
		$('#erroreFile').css("color", "red");
		fileOK=false;
	}
	else
	{
		fileOK=true;
	}
	
	if(marca()==true)
	{
		marcaOK=true;
	}
	else
	{
		$('#erroreMarca').html("Errore: la marca non può essere vuota");
		$("#marcaProdotto").css("border-color", "red");
		marcaOK=false;
	}
	
	if(nome()==true)
	{
		$('#erroreNome').html("");
		$("#nomeProdotto").css("border", "none");
		nomeOK=true;
	}
	else
	{
		$('#erroreNome').html("Errore: il nome non può essere vuoto");
		$("#nomeProdotto").css("border-color", "red");
		nomeOK=false;
	}
	
	if(prezzo()==true)
	{
		$('#errorePrezzo').html("");
		$("#prezzoProdotto").css("border", "none");
		prezzoOK=true;
	}
	else
	{
		$('#errorePrezzo').html("Errore: il prezzo non è valido");
		$("#prezzoProdotto").css("border-color", "red");
		prezzoOK=false;
	}
	
	if(quantità()==true)
	{
		$('#erroreQuantità').html("");
		$("#quantitàProdotto").css("border", "none");
		quantitàOK=true;
	}
	else
	{
		$('#erroreQuantità').html("Errore: la quantità può avere solo numeri da 0 in poi e non può essere vuota.");
		$("#quantitàProdotto").css("border-color", "red");
		quantitàOK=false;
	}
	
	if(codiceProdotto()==true)
	{
		$('#erroreCodice').html("");
		$("#codiceProdotto").css("border", "none");
		codiceProdottoOK=true;
	}
	else
	{
		$('#erroreCodice').html("Errore: il codice prodotto non può essere vuoto.");
		$("#codiceProdotto").css("border-color", "red");
		codiceProdottoOK=false;
	}
	
	if(nomeColore()==true)
	{
		$('#erroreColore').html("");
		$("#nomeColoreProdotto").css("border", "none");
		nomeColoreOK=true;
	}
	else
	{
		$('#erroreColore').html("Errore: il nome del colore non può essere vuoto.");
		$("#nomeColoreProdotto").css("border-color", "red");
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

function nome()
{
	var nomeForm= document.forms["formDettagli"]["nomeProdotto"].value.trim();
	console.log(nomeForm);
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

function prezzo()
{
	var prezzoForm= document.forms["formDettagli"]["prezzoProdotto"].value.trim();
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
		console.log("Cambia label: ");
		console.log(i);
		var file = $('#sceltaFile')[0].files[0].name;
		console.log("Cambia label: ");
		console.log(file);
		//console.log($(this).prev('label'));
		//$(this).prev('label').text(file);
		$('#etichettaSceltaFile').html(file);
		$('#erroreFile').html("");
	}); 
});

function colore()
{
	var colorePremuto = $('#coloreProdotto').find(":selected").val();
	console.log(colorePremuto);
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
	console.log(ultimaOption);
	var lunghezza= ultimaOption.length;
	console.log(lunghezza);
	var indice= ultimaOption.substring(ultimaOption.length-1);
	console.log(typeof Number(indice));
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
	//$('#bottoneColore').prop('disabled',true);
	$('#bottoneColore').remove();
	$('#myBtn').html("Aggiungi colore");
	$('#myBtn').attr("value", "insert");
	$('#sceltaFile').prop('disabled',false);
}