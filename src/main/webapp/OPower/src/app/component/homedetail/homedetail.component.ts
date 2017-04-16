import { Component, OnInit } from '@angular/core';
import {Home} from "../../person";
import {HomeService} from "../../service/home.service";
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-homedetail',
  templateUrl: './homedetail.component.html',
  styleUrls: ['./homedetail.component.css']
})
export class HomedetailComponent implements OnInit {

  home: Home;
  sub: any;
  response: any;
  constructor(private homeService: HomeService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.home = {
      id: 0,
      adresse: '',
      ville: '',
      rooms: 0,
      surface: 0,
      devices: []};
    this.sub = this.route.params.subscribe(params => {
      const id = Number.parseInt(params['id']);
      this.homeService
          .get(id)
          .subscribe(p => { this.home = p; console.log(this.home); });
    });
  }

  gotoList() {
    this.router.navigateByUrl('people');
  }

}
