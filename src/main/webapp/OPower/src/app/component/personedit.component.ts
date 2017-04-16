import { Person } from '../person';
import { PersonService } from '../service/person.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-personedit',
  templateUrl: './personedit.component.html',
  styles: []
})
export class PersoneditComponent implements OnInit, OnDestroy {
  person: Person;
  sub: any;
  errorMessage = '';
  response: any;


  constructor(private personService: PersonService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = Number.parseInt(params['id']);
      console.log('getting person with id: ' + id);
      this.personService
        .get(id)
        .subscribe(p => this.person = p);
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  savePersonDetails() {
    this.personService.save(this.person).subscribe(p => this.response = p,
                                                        e => this.errorMessage = e._body,
                                                        () => this.router.navigate(['/persons/', this.person.id]));
  }
}
