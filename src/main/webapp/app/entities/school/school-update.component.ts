import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISchool, School } from 'app/shared/model/school.model';
import { SchoolService } from './school.service';

@Component({
  selector: 'jhi-school-update',
  templateUrl: './school-update.component.html',
})
export class SchoolUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    eduType: [null, [Validators.required]],
    noOfRooms: [null, [Validators.required, Validators.min(5), Validators.max(99)]],
  });

  constructor(protected schoolService: SchoolService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ school }) => {
      this.updateForm(school);
    });
  }

  updateForm(school: ISchool): void {
    this.editForm.patchValue({
      id: school.id,
      name: school.name,
      eduType: school.eduType,
      noOfRooms: school.noOfRooms,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const school = this.createFromForm();
    if (school.id !== undefined) {
      this.subscribeToSaveResponse(this.schoolService.update(school));
    } else {
      this.subscribeToSaveResponse(this.schoolService.create(school));
    }
  }

  private createFromForm(): ISchool {
    return {
      ...new School(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      eduType: this.editForm.get(['eduType'])!.value,
      noOfRooms: this.editForm.get(['noOfRooms'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISchool>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
