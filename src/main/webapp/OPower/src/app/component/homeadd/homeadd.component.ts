import { Component, OnInit } from '@angular/core';
import {Home, IntelligentDevice} from "../../person";
import {HomeService} from "../../service/home.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-homeadd',
  templateUrl: './homeadd.component.html',
  styleUrls: ['./homeadd.component.css']
})
export class HomeaddComponent implements OnInit {

  homes: Home[];
  home: Home;
  response: any;
  errorMessage: string;
  saved = false;
  ajoutDevice = false;
  device: any;
  heaterB = false;
  elecB = false;
  constructor(private homeService: HomeService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.homeService.getAll().subscribe(p => this.homes = p);
    this.home = {
      id: 0,
      adresse: '',
      ville: '',
      rooms: 0,
      surface: 0,
      devices: []};
    this.device = {
      id: 0,
      reference: '',
      consommationAvg: 0,
      type: 'sans'
    };
  }

  addHome() {
    this.homeService.save(this.home).subscribe(p => {
          this.response = p;
          console.log('réponse', this.response);
        },
        e => this.errorMessage = e._body,
        () => {
          this.saved = true;
          this.goToList();
        }
    );
  }

  goToList() {
    this.router.navigateByUrl('people');
  }

  ajoutDeviceTrue () {
    this.ajoutDevice = true;
  }

  heater() {
    this.device = {
      id: this.device.id,
      reference: this.device.reference,
      consommationAvg: this.device.consommationAvg,
      source: '',
      type: 'heater'
    };
    this.elecB = false;
    this.heaterB = true;
  }

  elec() {
    this.device = {
      id: this.device.id,
      reference: this.device.reference,
      consommationAvg: this.device.consommationAvg,
      name: '',
      type: 'elec'
    };
    this.heaterB = false;
    this.elecB = true;
  }
  addDevice() {
    this.home.devices.push(this.device);
  }
}
