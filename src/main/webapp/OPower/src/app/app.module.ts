import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing } from './app.routes';

import { AppComponent } from './app.component';
import { PersonlistComponent } from './component/personlist.component';
import { PersoneditComponent } from './component/personedit.component';
import { PersondetailComponent } from './component/persondetail.component';
import { PersonService } from './service/person.service';
import { PersonaddComponent } from './component/personadd.component';
import {HomeService} from './service/home.service';
import { HomeaddComponent } from './component/homeadd/homeadd.component';
import { NavbarComponent } from './component/navbar/navbar.component';
import { HomedetailComponent } from './component/homedetail/homedetail.component';

@NgModule({
  declarations: [
    AppComponent,
    PersonlistComponent,
    PersoneditComponent,
    PersondetailComponent,
    PersonaddComponent,
    HomeaddComponent,
    NavbarComponent,
    HomedetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [PersonService, HomeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
