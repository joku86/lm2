import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpModule}    from '@angular/http';
import { AppComponent }  from './app.component';
import { ConfigComponent }  from './config.component';
import {DropdownModule,PanelModule,InputTextModule,DataTableModule,ButtonModule,DialogModule,TabViewModule,TreeTableModule,TreeNode,SharedModule} from 'primeng/primeng';

@NgModule({
  imports:  [DropdownModule,BrowserModule,FormsModule,HttpModule,InputTextModule,DataTableModule,PanelModule,ButtonModule,TreeTableModule,DialogModule,TabViewModule,SharedModule],
  declarations: [ AppComponent,ConfigComponent ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
