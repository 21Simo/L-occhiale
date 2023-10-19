// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
/*
btn.onclick = function() {
  modal.style.display = "block";
}*/

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

function servlet() 
{
	var modal = document.getElementById("myModal");
	console.log(modal);
	modal.style.display = "block";
	console.log("ciao");
	var nome=document.getElementById("nome");
	console.log(nome.innerHTML);
	var immagine=document.getElementById("img-1").getAttribute("src");
	console.log("Immagine: ");
	console.log(immagine);
	console.log("Colore: ");
	var colore=$('.color-groups').find("img").filter(".active-color").attr("title");
	console.log(colore);
	console.log("Valore bottone");
	console.log($('#myBtn').attr("value", colore));
	var quantità=$('#quantità').val();
	var graduati=$('#modificaLenti').val();
	if(graduati=="Graduati")
	{
		var file=$('#etichettaSceltaFile').html();
	}
	$.ajax
	({
		data: {nome: nome.innerHTML, immagine: immagine, colore: colore, quantità: quantità, graduati: graduati, file: file},
		url: 'CarelloServlet',
		method: 'POST',
		success: function (risultati)
		{
			console.log("json: ");
			console.log(risultati.nome);
			console.log(risultati.immagine);
			console.log(risultati.colorazione);
			$('#immagineModal').attr("src", risultati.immagine);
			console.log($('#colorazione').html());
			$('#colorazione').html(risultati.colorazione);
			$('#prezzoModal').html(risultati.prezzo+" €");
			console.log(risultati.totaleCarello);
			$('#totaleCarello').html("Totale costo: "+risultati.totaleCarello+" €");
			$('#quantitàCarello').html("Totale prodotti nel carello: "+risultati.quantitàCarello);
			$('#contatoreCarello').addClass("opacita");
			$('#contatoreCarello').html(risultati.quantitàCarello);
		}
	});	
	console.log("Modal");
	console.log(document.getElementById("immagineModal"));
}