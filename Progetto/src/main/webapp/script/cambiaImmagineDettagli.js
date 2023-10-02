function cambiaImmagine(id,prodotto,quantità) 
{
	console.log("onclick");
	console.log("Id: "+id);
	var prova=document.getElementById(id);
	console.log("elemento cliccato: ");
	console.log(prova);
	/*
	console.log("Valore nome: ");
	console.log(prova.attributes[0].value);
	var bottone=document.getElementsByName("bottone");
	console.log("Bottone: ");
	console.log(bottone);
	*/
	var carta=prova.classList[1];
	console.log("Carta: "+carta);
	var indiceProva=carta.indexOf("-");
	//console.log("Indice dell'id: "+indiceProva);
	var provaNumero=carta.substring(indiceProva+1);
	console.log("Numero dell'id: "+provaNumero);
	var img="#img-"+provaNumero;
	console.log("Immagine: "+img);
	document.querySelector(img).src = `./img/prodotti/${id}`;
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
	//console.log(prova.attributes[1].value);
	console.log(prova.getAttribute("name"));	
	console.log("Valore title: ");
	console.log(prova.getAttribute("title"));
	console.log("Indice: "+provaNumero);
	
	/*
	var bottone=document.getElementById("bottone-"+provaNumero);
	console.log("Bottone");
	console.log(bottone);
	var valore=bottone.getAttribute("value");
	console.log(valore);
	console.log("Length: "+valore.indexOf("/"));
	var id=valore.charAt(0);
	console.log("Id: "+id);
	var valoreBottone=id+"/"+prova.getAttribute("title")
	//var valoreBottone=id+"/"+prova.attributes[0].value;
	bottone.setAttribute("value", valoreBottone);
	*/
	console.log(prodotto);
	var elementoPrezzoProdotto=document.getElementById("prezzo");
	console.log(elementoPrezzoProdotto);
	elementoPrezzoProdotto.innerHTML=prodotto+" €";
	var elementoQuantità=document.getElementById("quantità");
	console.log("Elemento quantità");
	console.log(elementoQuantità);
	//console.log("InnerHTML");
	//elementoQuantità.innerHTML=elementoQuantità.innerHTML+'<option value="audi">Fiat</option>';
	console.log(elementoQuantità.innerHTML);
	console.log("Parametro quantità");
	console.log(quantità);
	elementoQuantità.innerHTML="";
	console.log("Inizializziamo l'innerHTML");
	console.log(elementoQuantità.innerHTML);
	for (let i=1; i<=quantità; i++)
	{
		console.log(i);
		elementoQuantità.innerHTML=elementoQuantità.innerHTML+'<option value='+i+'>'+i+'</option>';
	}
}

function gradazione() 
{
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
/*
var input = document.getElementById('toggleswitch');
var outputtext = document.getElementById('status');

input.addEventListener('change',function()
{
	if(this.checked)
	{
		document.getElementById("choose-file-label").style.display="inline-block";
		document.getElementById("ergebnis").style.paddingBottom="10px";
	}
	else
	{
		document.getElementById("choose-file-label").style.display="none";
		document.getElementById("ergebnis").style.removeProperty('padding-bottom');
	}
});
*/
//Nuovo toogle 
$(document).ready(function() {
    $("#modificaLenti").on("change", function () {
        colorModePreview(this);
    })
});

function colorModePreview(ele) {
    if($(ele).prop("checked") == true){
        //$('body').addClass('dark-preview');
        //$('body').removeClass('white-preview');
        //document.getElementById("etichettaSceltaFile").style.display="inline-block";
        $('#etichettaSceltaFile').removeClass('elementoNascosto');
        $('#etichettaSceltaFile').addClass('elementoVisibile');
        //document.getElementById("toogle").style.paddingBottom="10px";
        $('#toogle').addClass('paddingToogle');
		/*document.getElementById("ergebnis").style.paddingBottom="10px";*/ 
		document.getElementById("modificaLenti").setAttribute('required', '');       
    }
    else if($(ele).prop("checked") == false){
        //$('body').addClass('white-preview');
        //$('body').removeClass('dark-preview');
        //document.getElementById("etichettaSceltaFile").style.display="none";
        $('#etichettaSceltaFile').removeClass('elementoVisibile');
        $('#etichettaSceltaFile').addClass('elementoNascosto');
        //document.getElementById("toogle").style.removeProperty('padding-bottom');
        $('#toogle').removeClass('paddingToogle');
		/*document.getElementById("ergebnis").style.removeProperty('padding-bottom');*/
		document.getElementById("modificaLenti").removeAttribute('required');
    }
}