function abilitaCampi()
{
	console.log("OK");
	$("input, select, option", "#formModifica").prop('disabled',false);	
	//document.myForm.setAttribute("onsubmit", "return validazione()");
	
	//return false;
	$("#password").prop("type", "text");
}

function noValidazione()
{
	document.getElementById("formModifica").setAttribute("onsubmit", "return validazioneModifica()");
	return false;
}

function validazioneModifica()
{
	var nomeOK, cognomeOK, codiceFiscaleOK, dataNascitaOK, emailOK, passwordOK, telefonoOK, indirizzoOK;
	
	if(nome()==true)
	{
		if($("#nome").val()==document.forms["form"]["nome"].value.trim())
		{
			$('#erroreNome').html("Errore: il nome non può contenere un numero");
		}
		nomeOK=true;
	}
	else
	{
		$('#erroreNome').html("Errore: il nome non può contenere un numero");
		$("#nome").css("border-color", "red");
		nomeOK=false;
	}
	
	if(cognome()==true)
	{
		cognomeOK=true;
	}	
	else
	{
		$('#erroreCognome').html("Errore: il cognome non può contenere un numero");
		$("#cognome").css("border-color", "red");
		cognomeOK=false;
	}
	
	if(codiceFiscale()==true)
	{
		cognomeOK=true;
	}
	else
	{
		$('#erroreCodiceFiscale').html("Errore: il codice fiscale è errato");
		$("#codiceFiscale").css("border-color", "red");
		cognomeOK=false;
	}
	
	if(dataNascita()==true)
	{
		dataNascitaOK=true;
	}
	else
	{
		$('#erroreDataNascita').html("Errore: la data di nascita non è corretta");
		$("#dataNascita").css("border-color", "red");
		dataNascitaOK=false;
	}
	
	if(email()==true)
	{
		var presenzaEmail=controllaEmail();
		if(presenzaEmail==true)
		{
			$('#erroreEmail').html("Errore: l'email è già inserita.");
			$("#email").css("border-color", "red");
			emailOK=false;
		}
		else
		{
			emailOK=true;
		}
	}
	else
	{
		$('#erroreEmail').html("Errore: l'email non è corretta");
		$("#email").css("border-color", "red");
		emailOK=false;
	}
	
	if(password()==true)
	{
		passwordOK=true;
	}
	else
	{
		$('#errorePassword').html("Errore: la password non rispetta i requisiti");
		$("#password").css("border-color", "red");
		passwordOK=false;
	}
	
	if(telefono()==true)
	{
		telefonoOK=true;
	}
	else
	{
		$('#erroreTelefono').html("Errore: il numero di telefono non è corretto");
		$("#telefono").css("border-color", "red");
		telefonoOK=false;
	}
	
	if(indirizzo()==true)
	{
		indirizzoOK=true;
	}
	else
	{
		$('#erroreIndirizzo').html("Errore: l'indirizzo non è corretto");
		$("#indirizzo").css("border-color", "red");
		indirizzoOK=false;
	}
	
	
	if(nomeOK==false && cognomeOK==false && codiceFiscaleOK==false && dataNascitaOK==false && emailOK==false && passwordOK==false && telefonoOK==false && indirizzoOK==false)
	{
		return false;
	}
	else if(nomeOK==false || cognomeOK==false || codiceFiscaleOK==false || dataNascitaOK==false || emailOK==false || passwordOK==false || telefonoOK==false || indirizzoOK==false)
	{
		return false;
	}
	else
	{		
		return true;
	}
}

function nome()
{
	var nomeForm= document.forms["form"]["nome"].value.trim();
	var espresioneRegolareNome= /^[A-Za-z]+$/;
	if(nomeForm.match(espresioneRegolareNome))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function cognome()
{
	var cognomeForm= document.forms["form"]["cognome"].value.trim();
	var espresioneRegolareCognome= /^[A-Za-z]+$/;
	if(cognomeForm.match(espresioneRegolareCognome))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function codiceFiscale()
{
	var codiceFiscaleForm= document.forms["form"]["codiceFiscale"].value.trim();
	var espressioneRegolareCodiceFiscale= /^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$/;
	if(codiceFiscaleForm.match(espressioneRegolareCodiceFiscale))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function dataNascita()
{
	var dataNascitaForm= document.forms["form"]["dataNascita"].value.trim();
	var espressioneRegolareDataNascita=/^[0-9]{4}\-[0-9]{1,2}\-[0-9]{1,2}$/;
	if(dataNascitaForm.match(espressioneRegolareDataNascita))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function email()
{
	var emailForm= document.forms["form"]["email"].value.trim();
	var espressioneRegolareEmail= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(emailForm.match(espressioneRegolareEmail))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function password()
{
	var passwordForm= document.forms["form"]["password"].value.trim();
	var espressioneRegolarePassword=/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	if(passwordForm.match(espressioneRegolarePassword))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function telefono()
{
	var telefonoForm= document.forms["form"]["telefono"].value.trim();
	var espressioneRegolareTelefono=/^[+]{1}[0-9]{2,4}\-[0-9]{10}$/;
	if(telefonoForm.match(espressioneRegolareTelefono))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function indirizzo()
{
	var indirizzoForm= document.forms["form"]["indirizzo"].value.trim();
	var espressioneRegolareIndirizzo=/^[a-z A-Z]+[0-9]+$/;
	if(indirizzoForm.match(espressioneRegolareIndirizzo))
	{
		return true;
	}
	else
	{
		return false;
	}
}

function regioni()
{
	var regione= $("#regione").val();
	var indiceRegione=regione.indexOf("/");
	var id=regione.substring(0,indiceRegione);
	$.ajax
	({
		data: {regione: id},
		url: 'ProvinceServlet',
		method: 'POST', 
		success: function (risultati)
		{
			var tagliaArray=risultati.province.length;
			var selectProvince= document.getElementById("province");
			rimuoviOption(selectProvince);
			for(var i=0; i<tagliaArray; i++)
			{
				var valore=risultati.id[i]+"/"+risultati.province[i];
				var option=document.createElement("option");
				option.text= risultati.province[i];
				option.setAttribute("value", valore);
				selectProvince.add(option);
			}
		}
	})
}

function provincia()
{
	var provincia= $("#province").val();
	var indiceProvince=provincia.indexOf("/");
	var id=provincia.substring(0,indiceProvince);
	$.ajax
	({
		data: {provincia: id},
		url: 'ComuniServlet',
		method: 'POST', 
		success: function (risultati)
		{
			var tagliaArray=risultati.comuni.length;
			var selectComuni= document.getElementById("comune");
			rimuoviOption(selectComuni);
			for(var i=0; i<tagliaArray; i++)
			{
				var option=document.createElement("option");
				option.text= risultati.comuni[i];
				selectComuni.add(option);
			}
		}
	})
}

function rimuoviOption(selectProvince)
{
	var tagliaSelect=selectProvince.length;
	if(tagliaSelect>1)
	{
		for(var j=0; j<tagliaSelect; j++)
		{
			selectProvince.remove(selectProvince.selectedIndex);
		}
	}
}

function controllaEmail()
{
	var emailForm= document.forms["form"]["email"].value.trim();
	var presente;
	var emailValore= $("#email").attr("value");
	console.log(emailForm, emailValore);
	if(emailValore!=emailForm)
	{
		$.ajax
		({
			data: {email: emailForm},
			url: 'EmailServlet',
			method: 'POST', 
			async: false,
			success: function (risultati)
			{
				presente=risultati.presenza;
			}
		});
	}
	return presente;
}

