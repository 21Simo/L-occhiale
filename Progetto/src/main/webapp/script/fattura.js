function generaPDF()
{
	var pdfObject = jsPDFInvoiceTemplate.default(props);
	
	console.log("Oggetto creato", pdfObject);
	console.log(telefonoHtml);
	console.log(indiceTelefono);
	console.log(telefono);
}

var righe= document.getElementById("dettagliOrdini").rows.length-1;

var nomeCognomeHtml= $("#nomecognome").html();
var indiceNomeCognome= nomeCognomeHtml.indexOf(":");
var nomeCognome= nomeCognomeHtml.substring(indiceNomeCognome+2);

var indirizzoHtml= $("#indirizzo").html();
var indiceIndirizzo= indirizzoHtml.indexOf(":");
var indirizzo= indirizzoHtml.substring(indiceIndirizzo+2);

var telefonoHtml= $("#telefono").html();
var indiceTelefono= telefonoHtml.indexOf(":");
var telefono= telefonoHtml.substring(indiceIndirizzo+1);

var numeroOrdineHtml= $("#numeroOrdine").html();
var indiceNumeroOrdine= numeroOrdineHtml.indexOf(":");
var numeroOrdine= numeroOrdineHtml.substring(indiceNumeroOrdine+2);

var dataOrdineHtml= $("#dataOrdine").html();
var indiceDataOrdine= dataOrdineHtml.indexOf(":");
var dataOrdine= dataOrdineHtml.substring(indiceDataOrdine+2);

var totaleHtml= $("#totale").html();
var indiceTotaleHtml= totaleHtml.indexOf(":");
var totale= totaleHtml.substring(indiceTotaleHtml+2);


var props = {
    outputType: jsPDFInvoiceTemplate.OutputType.Save,
    returnJsPDFDocObject: true,
    fileName: "Fattura ordine "+numeroOrdine,
    orientationLandscape: false,
    compress: true,
    logo: {
        src: "./img/LogoAdobe.png",
        type: 'PNG', //optional, when src= data:uri (nodejs case)
        width: 55, //aspect ratio = width/height
        height: 55,
        margin: {
            top: -8, //negative or positive num, from the current position
            left: -10 //negative or positive num, from the current position
        }
    },
    business: {
        name: "L'occhiale",
        address: "Albania, Tirane ish-Dogana, Durres 2001",
        phone: "(+39) 3311007122",
        email: "s.civale2@studenti.unisa.it",
        email_1: "info@example.al",
        website: "www.l'occhiale.it",
    },
    contact: {
        label: "Fattura per:",
        name: nomeCognome,
        address: indirizzo,
        phone: telefono,
    },
    invoice: {
        label: "Fattura #: ",
        num: numeroOrdine,
        invDate: "Data: "+dataOrdine,
        headerBorder: false,
        tableBodyBorder: false,
        header: [
          {
            title: "#", 
            style: { 
              width: 10
            } 
          }, 
          { 
            title: "Nome",
            style: {
              width: 50
            } 
          }, 
          { 
            title: "Colore",
            style: {
              width: 30
            } 
          }, 
          { 
			title: "Gradazione",
			style: {
              width: 30
              }
		  },
          { title: "Prezzo"},
          { title: "Quantità"}
        ],
        table: Array.from(Array(righe), (item, index)=>([
            index + 1,
            document.getElementById("dettagliOrdini").rows[index+1].cells[1].innerHTML,
            document.getElementById("dettagliOrdini").rows[index+1].cells[2].innerHTML,
            document.getElementById("dettagliOrdini").rows[index+1].cells[3].innerHTML,
            document.getElementById("dettagliOrdini").rows[index+1].cells[4].innerHTML,
            document.getElementById("dettagliOrdini").rows[index+1].cells[5].innerHTML
        ])),
        additionalRows: [{
            col1: 'Totale:',
            col2: totale,
            style: {
                fontSize: 14 //optional, default 12
            }
        },
        {
            col1: 'IVA:',
            col2: '22%',
            style: {
                fontSize: 10 //optional, default 12
            }
        }]},
    footer: {
        text: "La fattura viene creata al computer ed è valida anche senza firma e timbro.",
    },
    pageEnable: true,
    pageLabel: "Pagina ",
};