import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./models/home/home.component";
import { ProjectsComponent } from "./models/projects/projects.component";
import { InvoicesComponent } from "./models/invoices/invoices.component";
import { UsersComponent } from "./models/users/users.component";

const APP_ROUTES: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'projects', component: ProjectsComponent },
    { path: 'invoices', component: InvoicesComponent },
    { path: 'users', component: UsersComponent },
    { path: '**', pathMatch: 'full', redirectTo:'home'}
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES);