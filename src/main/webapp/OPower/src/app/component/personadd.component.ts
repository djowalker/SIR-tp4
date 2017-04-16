import {Person, Friend, Friendship, Home} from '../person';
import { PersonService } from '../service/person.service';
import {Component, OnInit, OnDestroy, OnChanges} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {HomeService} from '../service/home.service';

@Component({
  selector: 'app-personadd',
  templateUrl: './personadd.component.html',
  styles: []
})
export class PersonaddComponent implements OnInit, OnDestroy, OnChanges {
  person: Person;
  friend: Person;
  home: Home;
  test: any;
  code: any;
  errorMessage = '';
  response: any;
 people: Person[] = [];
 homes: Home[] = [];
 idSelect: number;
 idHomeSelect: number;
 saved = false;

  constructor(private personService: PersonService,
    private homeService: HomeService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.personService.getAll().subscribe(p => this.people = p);
    this.homeService.getAll().subscribe( p => this.homes = p);
    this.person = {id: 0, name: '', email: '', friends: [], homes: [], surname: ''};
    this.friend = {id: 0, name: '', email: '', friends: [], homes: [], surname: ''};
    this.home= {
          id: 0,
          adresse: '',
          ville: '',
          rooms: 0,
          surface: 0,
          devices: []};
  }

  ngOnDestroy() {
  }

  ngOnChanges() {
      console.log('change');
      this.person.id = +this.response._body;
  }

  savePerson() {
      if (!this.saved) {
          this.personService.save(this.person).subscribe(p => {
                  this.response = p;
                  console.log('réponse', this.response);
              },
              e => this.errorMessage = e._body,
              () => {
                  this.saved = true;
                  this.person.id = Number.parseInt(this.response._body);
              }
          );
      }
  }

  editPerson() {
      this.errorMessage = '';
      this.personService.saveEdit(this.person).subscribe(
          p => {this.response = p;  },
          e => this.errorMessage = e._body
      );
  }
  goToEdit() {
      this.editPerson();
      if (this.errorMessage !== '') {
          this.router.navigateByUrl('persons/' + this.person.id);
      }
      else {
          console.log('error');
          this.goToList();
      }
  }
  goToList() {
      this.router.navigateByUrl('people');
  }
  addFriend() {
    this.initFriend();
    console.log(this.person);
  }
  initFriend() {
      this.test = this.personService.get(this.idSelect).subscribe(f => { this.friend = f; this.addFriendCont(); } );
  }
  addFriendCont() {
    let friendShip: Friendship;
    friendShip = {id: 0, idMe: this.person.id, idFriend: this.friend.id};
    this.person.friends.push(friendShip);
  }

  addHome() {
      this.test = this.homeService.get(this.idHomeSelect).subscribe(f => { this.home = f; this.addHomeCont(); } );
  }

    addHomeCont() {
      this.person.homes.push(this.home);
    }
}
