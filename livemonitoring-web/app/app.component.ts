import { Component } from '@angular/core';
import {HttpService} from './http.service';
import {TreeTableModule,TreeNode,SharedModule} from 'primeng/primeng';

interface NumberOfConnection {
  label:string;
  value:number;
}
 
@Component({
    selector: 'my-app',
	 moduleId: module.id,
    templateUrl: 'templates/main.html',
     styles: ['h1 { color: red; }'],
     providers:[HttpService]
})

export class AppComponent { 
    
    selectedQueueNumber: string;
 
 
 numberOfConnection: NumberOfConnection[];
  constructor(private _httpService:HttpService) {

  

this.numberOfConnection = [];
      this.numberOfConnection.push({label: 'keine', value: null});
       for (var i = 1; i < 10; i++) {
      this.numberOfConnection.push({label: i.toString(), value: i});
    }
        
       
         
    }
 
     cars: any[]=[{"plant": "testanlage1", "year": 2012, "plantType": "anltyp", "plantName": "dsad231ff"},{"plant": "UV", "year": 2012, "color": "Orange", "vin": "dsad231ff"}];
    
    cols: any[]=[{"field":"plant","header":"Anlage"},{"field":"plantName","header":"Anlagebezeichnung"},{"field":"plantType","header":"Anlagentyp"}];
    
handleChange(e:any) {
  console.log(e);
    
}

 response:any;
 logout(){
   this._httpService.getServerConfig().subscribe(
     response=>this.response=response.setting,
     error=>console.log(error))

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
