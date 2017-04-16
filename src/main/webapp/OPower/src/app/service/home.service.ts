import { Home } from '../person';
import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class HomeService {
    private baseUrl = 'http://localhost:8080/jaxrs';

    constructor(private http: Http) {
    }

    get(id: number): Observable<Home> {
        let home$ = this.http
            .get(`${this.baseUrl}/Homes/${id}`, {headers: this.getHeaders()})
            .map(mapHome);
        return home$;
    }


    getAll(): Observable<Home[]> {
        let home$ = this.http
            .get(`${this.baseUrl}/Homes`, {headers: this.getHeaders()})
            .map(mapHomes);
        return home$;
    }

    isEmpty(): boolean{
        let count$ = this.http.get(`${this.baseUrl}/Homes/count`, {headers: this.getHeaders()})
            .map(toNumber);
        count$.subscribe(val => console.log(val));
        return true;
    }

    save(home: Home): Observable<Response> {
        console.log('Saving home ' + JSON.stringify(home));
        return this.http.put(`${this.baseUrl}/Homes/create`, JSON.stringify(home), {headers: this.getHeaders()});
    }

    saveEdit(home: Home): Observable<Response> {
        console.log('Saving edit home' + JSON.stringify(home));
        return this.http.put(`${this.baseUrl}/Homes/edit/${home.adresse}`, JSON.stringify(home), {headers: this.getHeaders()});
    }

    private getHeaders() {
        let headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append('Content-Type', 'application/json');
        return headers;
    }
}

function mapHomes(response: Response): Home[] {
    return response.json().map(toHome);
}

function mapHome(response: Response): Home {
    return toHome(response.json());
}

function toHome(r: any): Home {
    return r;
}

function toNumber(response: Response): number {
    const text = response.text;
    return +text;
}
