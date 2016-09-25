import { Component } from '@angular/core';

interface NumberOfConnection {
  label:string;
  value:number;
}
@Component({
    selector: 'configs',
	 moduleId: module.id,
    template: `<p-panel header="Nicht Konfiguriert" [collapsed]="true" [toggleable]="true" >
	<div>
		
 <p-dropdown [options]="numberOfConnection" [(ngModel)]="selectedCity" [style]="{'width':'50px'}" ></p-dropdown>
	</div>
</p-panel>`,
     styles: ['h1 { color: red; }']
})

export class ConfigComponent { 
    
    selectedQueueNumber: string;

 
 numberOfConnection: NumberOfConnection[];
  constructor() {
this.numberOfConnection = [];
      this.numberOfConnection.push({label: 'keine', value: null});
       for (var i = 1; i < 10; i++) {
      this.numberOfConnection.push({label: i.toString(), value: i});
    }
        
       
         
    }
 
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
