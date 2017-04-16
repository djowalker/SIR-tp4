import { Routes, RouterModule } from '@angular/router';
import { PersonlistComponent } from './component/personlist.component';
import { PersondetailComponent } from './component/persondetail.component';
import { PersoneditComponent } from './component/personedit.component';
import { PersonaddComponent } from './component/personadd.component';
import {HomeaddComponent} from 'app/component/homeadd/homeadd.component';
import {NavbarComponent} from './component/navbar/navbar.component';
import { HomedetailComponent } from './component/homedetail/homedetail.component';



const routes: Routes = [
  {
    path: 'root',
    component: NavbarComponent
  },
  {
    path: 'people',
    component: PersonlistComponent
  },
  {
    path: 'people/add',
    component: PersonaddComponent
  },
  {
    path: 'persons/:id',
    component: PersondetailComponent
  },
  {
    path: 'persons/edit/:id',
    component: PersoneditComponent
  },
  {
    path: 'homes/add',
    component: HomeaddComponent
  },
  {
    path: 'homes/:id',
    component: HomedetailComponent
  },
  {
    path: '',
    redirectTo: 'root',
    pathMatch: 'full'
  },
];

export const routing = RouterModule.forRoot(routes);