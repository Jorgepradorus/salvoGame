

var url_string = window.location.href
var url = new URL(url_string);
var c = url.searchParams.get("gp");
console.log(c);


fetch("http://localhost:8080/api/game_view/"+c)
        .then(function(response)  {
         if (response.ok) {
            return response.json();
            }
        })
        .then(function(json){
            console.log(json)
            data = json
            console.log(data)
            mailsOfPlayers();
          //  x()
          //  shotsRival()

                y()
        })

function mailsOfPlayers(){
let mail="";
let mailRival=""
for( let i = 0; i < data.length; i++){
        mail += "<ul>" + data[0].created + "<ol>"
        mailRival += "<ul>" + data[0].created + "<ol>"
         for(let j = 0 ; j < data[0].gameplayers.length; j++){
         if (data[i].gameplayers[j].id == c ==true){
                mail += "<ul>" + data[i].gameplayers[j].player.email + "</ul>"
               } else {
               mailRival += "<ul>" + data[i].gameplayers[j].player.email + "</ul>"
               }
            }
            mail += "</ol></ul>"
}
document.getElementById("output").innerHTML= mail;
document.getElementById("outputR").innerHTML= mailRival;
}

/*
function x(){
let myshipslocations="";

for( let i = 0; i < data[0].ships.length; i++){
        myshipslocations += "<li>" + data[0].ships[i].locations + "</li>";
        console.log(data[0].ships[i].locations)
        for(let j = 0; j < data[0].ships[i].locations.length; j++){
            let casillaSeleccionada = document.getElementById(data[0].ships[i].locations[j])
            casillaSeleccionada = casillaSeleccionada.classList.add('positionship')
            console.log(casillaSeleccionada)
        }
}
document.getElementById("output2").innerHTML= myshipslocations;
}

function shotsRival(){

    for( let i = 0; i < data[0].salvoes.length; i++){

        for ( let j = 0; j < data[0].salvoes[i].salvos.length; j++){

                    if (data[0].salvoes[i].playerId != c){
                             for( let k = 0; k < data[0].salvoes[i].salvos[j].locations.length; k++){


                    console.log(data[0].salvoes[i].salvos[j].locations[k])
                    let casillaSeleccionadaSalvoes = document.getElementById(data[0].salvoes[i].salvos[j].locations[k]);
                    console.log(casillaSeleccionadaSalvoes);
                            if(document.getElementById(data[0].salvoes[i].salvos[j].locations[k]) == document.getElementById(data[0].ships[i].locations[j])){
                                    casillaSeleccionadaSalvoes = casillaSeleccionadaSalvoes.classList.add('positionSalvosPlayer2Hit')
                                }
                            }
            } else if (data[0].salvoes[i].playerId == c) {
                for( let k = 0; k < data[0].salvoes[i].salvos[j].locations.length; k++){
                    let casillaSeleccionadaSalvoesAlRival = document.getElementById(data[0].salvoes[i].salvos[j].locations[k]);
                        casillaSeleccionadaSalvoesAlRival = casillaSeleccionadaSalvoesAlRival.classList.add('positionSalvosPlayer1');

                }
                }

            }
        }
    }*/

    function y(){

    for( let i = 0; i < data[0].ships.length; i++){


            for(let j = 0; j < data[0].ships[i].locations.length; j++){
                let casillaSeleccionada = document.getElementById(data[0].ships[i].locations[j])
                console.log(data[0].ships[i].locations[j])
                console.log(document.getElementById(data[0].ships[i].locations[j]))
                casillaSeleccionada = casillaSeleccionada.classList.add('positionship')
            }
    }

     for( let i = 0; i < data[0].salvoes.length; i++){
            for ( let j = 0; j < data[0].salvoes[i].salvos.length; j++){
                    if (data[0].salvoes[i].playerId != c){
                         for( let k = 0; k < data[0].salvoes[i].salvos[j].locations.length; k++){
                            let casillaSeleccionadaHits = document.getElementById(data[0].salvoes[i].salvos[j].locations[k]);
                            document.getElementById(data[0].salvoes[i].salvos[j].locations[k]).innerHTML = data[0].salvoes[i].salvos[j].turn_number

                            console.log(data[0].salvoes[i].salvos[j].locations[k])

                            if(casillaSeleccionadaHits.className == "grid-item"){
                                casillaSeleccionadaHits.className = "positionSalvosPlayer2Miss";
                                console.log("Casilla sin barco")
                            }else if(casillaSeleccionadaHits.className == "grid-item positionship"){
                                console.log("Casilla con barco")
                                casillaSeleccionadaHits.className = "positionSalvosPlayer2Hit";
                            }

                        }
                    } else if (data[0].salvoes[i].playerId == c){
                         for( let k = 0; k < data[0].salvoes[i].salvos[j].locations.length; k++){
                          let casillaSeleccionadaSalvoesAlRival = document.getElementById(data[0].salvoes[i].salvos[j].locations[k]);
                          document.getElementById(data[0].salvoes[i].salvos[j].locations[k]).innerHTML = data[0].salvoes[i].salvos[j].turn_number
                          casillaSeleccionadaSalvoesAlRival = casillaSeleccionadaSalvoesAlRival.classList.add("positionSalvosPlayer1");
                    }
                   }
            }
    }
}

