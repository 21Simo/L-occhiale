function validazioneDettagli()
{
	var file= $('#etichettaSceltaFile').html().trim();
	console.log(file);
	var graduati= $('#modificaLenti').val();
	console.log(graduati);
	if(graduati=="Graduati")
	{
		if(file=="Carica la prescrizione medica")
		{
			$('#erroreFile').html("Devi inserire il file della prescrizione medica");
			$('#erroreFile').css("color", "red");
			return false;
		}
	}
	else
	{
		return true;
	}
}