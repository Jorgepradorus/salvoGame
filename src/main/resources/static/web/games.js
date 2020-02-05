fetch("http://localhost:8080/api/games")
        .then(function(response)  {
         if (response.ok) {
            return response.json();
            }
        })
        .then(function(json){
            console.log(json)
            data = json
            console.log(data)

            ScoresPlayers()
        })


function ScoresPlayers(){

        let tableRow = [];

        for( let i= 0; i< data.length; i++){

             for (let j = 0; j< data[i].gamePlayer.length; j++){

              let dataTableRow = {
                                          emailPlayer: "",
                                          totalScore: 0,
                                          wins: 0,
                                          lost: 0,
                                          tie: 0,
                                          };

               dataTableRow.emailPlayer = data[i].gamePlayer[j].player.email

                tableRow.push(dataTableRow)

                for (let k = 0; k< data[i].gamePlayer[j].player.score.length; k++){

                    if(data[i].gamePlayer[j].player.score[k].scoreGame == "1"){
                    dataTableRow.wins = dataTableRow.wins + 1
                  dataTableRow.totalScore = dataTableRow.totalScore + 1
                       }

                    if(data[i].gamePlayer[j].player.score[k].scoreGame == "0"){
                      dataTableRow.lost = dataTableRow.lost + 1
                    dataTableRow.totalScore = dataTableRow.totalScore + 0
                       }

                    if ((data[i].gamePlayer[j].player.score[k].scoreGame == "0,5")) {
                       dataTableRow.tie = dataTableRow.tie + 1
                     dataTableRow.totalScore = dataTableRow.totalScore + 0,5
                       }
               }
            }

        }

       //Hago copia
         let newTableRow = tableRow.slice();
        //Ordeno la variable
          let tableRowSort = newTableRow.sort((a,b) => (a.emailPlayer > b.emailPlayer) ? 1 : ((b.emailPlayer > a.emailPlayer) ? -1 : 0));




        //Me quedo con los únicos
        let newTableRowSort=[];

     for (let i = 0; i< tableRowSort.length; i++){
         if(tableRowSort[i].emailPlayer == tableRowSort[i++].emailPlayer){
                      newTableRowSort.push(tableRowSort[i])
             }
                 }
        //Ordeno la variable por totalScore
        let newTableRowSortFinal = newTableRowSort.sort((a,b) => (a.totalScore < b.totalScore) ? 1 : ((b.totalScore < a.totalScore) ? -1 : 0));

        //Construyo la tabla

        let tableRowFinal="";
        for(let i=0; i < newTableRowSortFinal.length; i++){
        tableRowFinal += '<tr><td>' + newTableRowSortFinal[i].emailPlayer + '</td><td>' + newTableRowSortFinal[i].totalScore + '</td><td>' + newTableRowSortFinal[i].wins + '</td><td>' + newTableRowSortFinal[i].lost + '</td><td>' + newTableRowSortFinal[i].tie + '</td></tr>'
        }

      document.getElementById("outputTableRow").innerHTML= tableRowFinal;
    }
