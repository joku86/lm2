import { Component } from '@angular/core';



@Component({
    selector: 'configs',
    moduleId: module.id,
     templateUrl: 'templates/config.html',
    styles: [`.my-grid-row {margin-bottom: 4px;margin-top: 4px;}`]
})

export class ConfigComponent {

    selectedQueueNumber: string;
    configurated: string = "Nicht konfiguriert";
    selectedNumberOfConnections: InsertedConnectionDetails[] = [{ queueAdress: "",username: "",password: "" }];

    numberOfConnection: NumberOfConnection[];
    constructor() {
        this.numberOfConnection = [];
        for (var i = 1; i < 10; i++) {
            this.numberOfConnection.push({ label: i.toString(), value: i });
        }


    }

    submitConnections() {
        for (let entry of this.selectedNumberOfConnections) {
            console.log(entry);
        }


    }

    valueChanged(e: any) {

        if (e.value > this.selectedNumberOfConnections.length) {
            for (let i = this.selectedNumberOfConnections.length; i < e.value; i++) {
                this.selectedNumberOfConnections.push({ queueAdress: "", username: "", password: "" });
            }
        } else {
            let end = this.selectedNumberOfConnections.length - e.value
            for (let i = 0; i < end; i++) {
                this.selectedNumberOfConnections.pop();
            }
        }

        console.log(e.value);

    }

    count() {



        let ws: WebSocket = new WebSocket("wss://127.0.0.2:8443/livemon/ws/server");

        ws.onopen = function(event: any) {
            console.log("Socket has been opened!");
        }
        ws.onmessage = function(event: any) {
            console.log("empfangen " + event);

            this.cars.push({ "brand": "neu dazu", "year": 2002, "color": "rot", "vin": "dsad231ff" });
        }.bind(this);

    }


}
interface NumberOfConnection {
    label: string;
    value: number;
}
interface InsertedConnectionDetails {
    queueAdress: string;
    username: string;
    password: string;
}
