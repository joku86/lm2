import {Injectable}  from '@angular/core';
import {Http}  from '@angular/http';
import {Observable}  from 'rxjs/Observable';
import 'rxjs/Rx';

@Injectable()
export class HttpService{
    constructor(private _http:Http){

    }
    getServerConfig():Observable<any>{
        return this._http.get('http://127.0.0.1:8080/livemon/api/serverconfig').map(res=>res.json().settings);

    }
  
}