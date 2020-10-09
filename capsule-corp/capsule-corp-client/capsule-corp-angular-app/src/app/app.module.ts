import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { UsersComponent } from './models/users/users.component';
import { HomeComponent } from './models/home/home.component';
import { ProjectsComponent } from './models/projects/projects.component';
import { InvoicesComponent } from './models/invoices/invoices.component';

//routes
import { APP_ROUTING } from "./app.routes";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    UsersComponent,
    HomeComponent,
    ProjectsComponent,
    InvoicesComponent
  ],
  imports: [
    BrowserModule,
    APP_ROUTING
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
