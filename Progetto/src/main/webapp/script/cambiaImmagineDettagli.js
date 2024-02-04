function cambiaImmagine(id,prodotto,quantità) 
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
	elementoPrezzoProdotto.innerHTML=prodotto+" €";
	var elementoQuantità=document.getElementById("quantità");
	elementoQuantità.innerHTML="";
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
		elementoQuantità.innerHTML=elementoQuantità.innerHTML+'<option value='+i+'>'+i+'</option>';
	}
}

function quantita()
{
	var quantità= $("#quantità").val();
	$('#quantitàProdotto').attr("value", quantità);
}

//Toogle 
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
		var file = $('#sceltaFile')[0].files[0].name;
		$('#etichettaSceltaFile').html(file);
		$('#erroreFile').html("");
	}); 
});