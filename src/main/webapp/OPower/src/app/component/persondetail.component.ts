import {Friendship, Home, Person} from '../person';
import { PersonService } from '../service/person.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-persondetail',
  templateUrl: './persondetail.component.html',
  styles: []
})
export class PersondetailComponent implements OnInit, OnDestroy {
  person: Person;
  friendsList: Person[];
  homeList: Home[];
  fList: any[];
  sub: any;
  friend: any;
  errorMessage = '';

  constructor(private personService: PersonService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.fList = [];
    this.sub = this.route.params.subscribe(params => {
      const id = Number.parseInt(params['id']);
      this.personService
        .get(id)
        .subscribe(p => { this.person = p;
        if (this.person !== null) {this.fList = this.person.friends; }
        else { this.fList = []; } ;
        this.fillFriendList(); });
    });
  }
  fillFriendList() {
    while (!this.fList) {console.log('1'); }
    if (this.fList === null) { return;}
    this.friendsList = [];
    for (const f of this.fList){
      let ft: Person;
      this.personService.get(f.friendId).subscribe(p => {ft = p;  this.friendsList.push(ft); } );
    }
    console.log('friend list ', this.friendsList);
    return;
  }
  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigateByUrl('people');
  }

}
