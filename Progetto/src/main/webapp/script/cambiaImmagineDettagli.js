function cambiaImmagine(id,prodotto,quantità) 
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
	elementoPrezzoProdotto.innerHTML=prodotto+" €";
	var elementoQuantità=document.getElementById("quantità");
	console.log("Elemento quantità");
	console.log(elementoQuantità);
	console.log(elementoQuantità.innerHTML);
	console.log("Parametro quantità");
	console.log(quantità);
	elementoQuantità.innerHTML="";
	console.log("Inizializziamo l'innerHTML");
	console.log(elementoQuantità.innerHTML);
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
	}
	for (let i=1; i<=quantità; i++)
	{
		console.log(i);
		elementoQuantità.innerHTML=elementoQuantità.innerHTML+'<option value='+i+'>'+i+'</option>';
	}
}

function quantita()
{
	var quantità= $("#quantità").val();
	console.log(quantità);
	$('#quantitàProdotto').attr("value", quantità);
}

/*
function gradazione() 
{
	console.log("Gradazione");
	var valore=document.getElementById("prescrizione").value;
	console.log(valore);
	if(valore=="Si")
	{
		document.getElementById("choose-file-label").style.display="inline-block";
	}
	else if(valore=="No")
	{
		document.getElementById("choose-file-label").style.display="none";
	}
}
*/

//Nuovo toogle 
$(document).ready(function() 
{
    $("#modificaLenti").on("change", function () 
    {
        colorModePreview(this);
    })
});

function colorModePreview(ele) 
{
    if($(ele).prop("checked") == true)
    {
        $('#etichettaSceltaFile').removeClass('elementoNascosto');
        $('#etichettaSceltaFile').addClass('elementoVisibile');
        $('#toogle').addClass('paddingToogle');
		document.getElementById("modificaLenti").setAttribute('required', ''); 
		$('#modificaLenti').val("Graduati"); 
		$('#gradazione').attr("value", "Graduati");
		$('#erroreFile').html("");     
    }
    else if($(ele).prop("checked") == false)
    {
        $('#etichettaSceltaFile').removeClass('elementoVisibile');
        $('#etichettaSceltaFile').addClass('elementoNascosto');
        $('#toogle').removeClass('paddingToogle');		
		document.getElementById("modificaLenti").removeAttribute('required');
		$('#modificaLenti').val("Non graduati");
		$('#gradazione').attr("value", "Non graduati");
		$('#erroreFile').html("");
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