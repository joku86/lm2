import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpModule}    from '@angular/http';
import { AppComponent }  from './app.component';
import {InputTextModule,DataTableModule,ButtonModule,DialogModule,TabViewModule,SharedModule} from 'primeng/primeng';

@NgModule({
  imports:  [BrowserModule,FormsModule,HttpModule,InputTextModule,DataTableModule,ButtonModule,DialogModule,TabViewModule,SharedModule],
  declarations: [ AppComponent ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
