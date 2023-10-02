function cambiaImmagine(id,prodotto) 
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
	var bottone=document.getElementById("bottone-"+provaNumero);
	console.log("Bottone: ");
	console.log(bottone);
	var valore=bottone.getAttribute("value");
	console.log("Valore bottone: ");
	console.log(valore);
	var indiceSlash=valore.indexOf("/");
	console.log("Length slash: "+indiceSlash);
	//var id=valore.charAt(0);
	var id=valore.substring(0,indiceSlash);
	console.log("Id: "+id);
	var valoreBottone=id+"/"+prova.getAttribute("title")
	//var valoreBottone=id+"/"+prova.attributes[0].value;
	bottone.setAttribute("value", valoreBottone);
	console.log(prodotto);
	var elementoPrezzoProdotto=document.getElementById("prezzoProdotto-"+provaNumero);
	console.log(elementoPrezzoProdotto);
	elementoPrezzoProdotto.innerHTML=prodotto+" €";
}