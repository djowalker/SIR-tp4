import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeaddComponent } from './homeadd.component';

describe('HomeaddComponent', () => {
  let component: HomeaddComponent;
  let fixture: ComponentFixture<HomeaddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeaddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeaddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
