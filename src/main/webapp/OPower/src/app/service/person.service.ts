import { Person } from '../person';
import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class PersonService {
  private baseUrl = 'http://localhost:8080/jaxrs';

  constructor(private http: Http) {
  }

  get(id: number): Observable<Person> {
    let person$ = this.http
      .get(`${this.baseUrl}/Person/${id}`, {headers: this.getHeaders()})
      .map(mapPerson);
      return person$;
  }


  getAll(): Observable<Person[]> {
     let person$ = this.http
      .get(`${this.baseUrl}/Person`, {headers: this.getHeaders()})
      .map(mapPersons);
    return person$;
  }

  isEmpty(): boolean{
    let count$ = this.http.get(`${this.baseUrl}/count`, {headers: this.getHeaders()})
      .map(toNumber);
    count$.subscribe(val => console.log(val));
    return true;
  }

  save(person: Person): Observable<Response> {
    console.log('Saving component ' + JSON.stringify(person));
    return this.http.put(`${this.baseUrl}/Person/create`, JSON.stringify(person), {headers: this.getHeaders()});
  }

  saveEdit(person: Person): Observable<Response> {
    console.log('Saving edit component' + JSON.stringify(person));
    return this.http.put(`${this.baseUrl}/Person/edit/${person.id}`, JSON.stringify(person), {headers: this.getHeaders()});
  }

  private getHeaders() {
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    headers.append('Content-Type', 'application/json');
    return headers;
  }
}

function mapPersons(response: Response): Person[] {
  return response.json().map(toPerson);
}

function mapPerson(response: Response): Person {
  return toPerson(response.json());
}

function toPerson(r: any): Person {
  return r;
}

function toNumber(response: Response): number {
  const text = response.text;
  return +text;
}
