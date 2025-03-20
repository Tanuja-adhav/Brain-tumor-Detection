import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MriReportCheckComponent } from './mri-report-check.component';

describe('MriReportCheckComponent', () => {
  let component: MriReportCheckComponent;
  let fixture: ComponentFixture<MriReportCheckComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MriReportCheckComponent]
    });
    fixture = TestBed.createComponent(MriReportCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
