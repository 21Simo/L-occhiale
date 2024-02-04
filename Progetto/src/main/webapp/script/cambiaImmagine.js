function cambiaImmagine(id,prodotto) 
{
	var imgElemento=document.getElementById(id);
	var carta=imgElemento.classList[1];
	var indice=carta.indexOf("-");
	var numero=carta.substring(indice+1);
	var img="#img-"+numero;
	document.querySelector(img).src = `./img/prodotti/${id}`;
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
	var bottone=document.getElementById("bottone-"+numero);
	var valore=bottone.getAttribute("value");
	var indiceSlash=valore.indexOf("/");
	var id=valore.substring(0,indiceSlash);
	var valoreBottone=id+"/"+imgElemento.getAttribute("title");
	bottone.setAttribute("value", valoreBottone);
	var elementoPrezzoProdotto=document.getElementById("prezzoProdotto-"+numero);
	elementoPrezzoProdotto.innerHTML=prodotto+" €";
}

function animazione(immagine)
{
	$("#"+immagine).animate({zoom: '150%'}, "slow");
}

function fuori(immagine)
{
	$("#"+immagine).animate({zoom: '100%'}, "slow");
}