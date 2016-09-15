import { Component } from '@angular/core';


@Component({
    selector: 'my-app',
	 moduleId: module.id,
    templateUrl: 'templates/main.html'
})
export class AppComponent { 
    
    
 
 
 
     cars: any[]=[{"brand": "mycar", "year": 2012, "color": "Orange", "vin": "dsad231ff"},{"brand": "VW", "year": 2012, "color": "Orange", "vin": "dsad231ff"}];
    
    cols: any[]=[{"field":"brand","header":"branddfsdf"},"year","color","vin"];
    
handleChange(e:any) {
  console.log(e);
    
}

 
 
 
count() {
    
    
   
      let  ws:WebSocket = new WebSocket("wss://localhost:8443/livemon/ws/receiver");

                ws.onopen = function (event:any) {
                    console.log("Socket has been opened!");
                }
               ws.onmessage= function (event:any) {
                console.log("empfangen "+event);
                    
                   this.cars.push({"brand": "neu dazu", "year": 2002, "color": "rot", "vin": "dsad231ff"});
                }.bind(this);
   
}

 
}
