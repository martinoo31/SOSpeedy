function callback( theXhr ) {

	if ( theXhr.readyState === 2 ) {
	    	
	}
	else if ( theXhr.readyState === 3 ) {
    	
	}
	else if ( theXhr.readyState === 4 ) {
		
		if ( theXhr.status === 200 ) {
			
			//INSERIRE IL RISULTATO DELLA RICHIESTA AJAX
			
				// Parsing con Json della risposta
				//
				//			var risultato = JSON.parse(theXhr.responseText);
				
				// Inserimento del risulato nel documente
				//
				//			document.getElementById(" inserire_id_elemento_dove_visualizzare_risposta ").value=risultato;
			
		}

		else {
			 alert("Impossibile effettuare l'operazione richiesta.");
	    }
	}
} 

function azioneAjax( theXhr /*, aggiungi qua i dati passati */){
	
	theXhr.onreadystatechange=function (){
		callback(theXhr);
	}
	
		//METODO GET

			//	try {
			//		theXhr.open("get", "inserire_nome_servlet?inserire_lista_parametri_per_la_get" , true);
			//	}
			//	catch(e) {
			//		alert(e);
			//	}
			//	theXhr.send(null);
		
		//METODO POST
		
			
			// var body=" scrivi_il_body "
			//	try {
			//		theXhr.open("post", "Prenota", true);
			//	}
			//	catch(e) {
			//		alert(e);
			//	}
			//	theXhr.send(body);
}


function azione(){
	
	//estrazione dei dati dal document necessari oppure passati come paramtero alla funzione
		
		// estrazione dal document usando getElementById, getElementByName, getElementsById
	
	//eventuali controlli sui dati passati o nel document 
	
		//possibile struttura di controllo
		//				if(){
		//					alert("messaggio di errore");
		//					return;
		//				}
	
	var xhr=new XMLHttpRequest();
	
	if(xhr){
		azioneAjax(xhr /*, aggiungi qua i dati da passare */); 
	}
	else {
		alert("Ajax non è supportato"); 
	}
}