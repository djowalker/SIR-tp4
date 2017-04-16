
import {Home, Person} from '../person';
import { PersonService } from '../service/person.service';
import { Component, OnInit } from '@angular/core';
import {HomeService} from '../service/home.service';


@Component({
  selector: 'app-personlist',
  templateUrl: './personlist.component.html',
  styles: []
})

export class PersonlistComponent implements OnInit {
  people: Person[] = [];
  homes: Home[] = [];
  peopleEmpty: boolean;

  constructor(private personService: PersonService, private homeService: HomeService) {
    console.log('test');
  }

  ngOnInit() {
    this.personService.getAll().subscribe(p => this.people = p);
    this.homeService.getAll().subscribe( p => this.homes = p);
    console.log(this.people);
  }

}
