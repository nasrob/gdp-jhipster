import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICountryLanguage, CountryLanguage } from 'app/shared/model/country-language.model';
import { CountryLanguageService } from './country-language.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country/country.service';

@Component({
  selector: 'jhi-country-language-update',
  templateUrl: './country-language-update.component.html',
})
export class CountryLanguageUpdateComponent implements OnInit {
  isSaving = false;
  countries: ICountry[] = [];

  editForm = this.fb.group({
    id: [],
    language: [null, [Validators.required]],
    isOfficial: [null, [Validators.required]],
    percentage: [null, [Validators.required]],
    countryId: [null, Validators.required],
  });

  constructor(
    protected countryLanguageService: CountryLanguageService,
    protected countryService: CountryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ countryLanguage }) => {
      this.updateForm(countryLanguage);

      this.countryService.query().subscribe((res: HttpResponse<ICountry[]>) => (this.countries = res.body || []));
    });
  }

  updateForm(countryLanguage: ICountryLanguage): void {
    this.editForm.patchValue({
      id: countryLanguage.id,
      language: countryLanguage.language,
      isOfficial: countryLanguage.isOfficial,
      percentage: countryLanguage.percentage,
      countryId: countryLanguage.countryId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const countryLanguage = this.createFromForm();
    if (countryLanguage.id !== undefined) {
      this.subscribeToSaveResponse(this.countryLanguageService.update(countryLanguage));
    } else {
      this.subscribeToSaveResponse(this.countryLanguageService.create(countryLanguage));
    }
  }

  private createFromForm(): ICountryLanguage {
    return {
      ...new CountryLanguage(),
      id: this.editForm.get(['id'])!.value,
      language: this.editForm.get(['language'])!.value,
      isOfficial: this.editForm.get(['isOfficial'])!.value,
      percentage: this.editForm.get(['percentage'])!.value,
      countryId: this.editForm.get(['countryId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICountryLanguage>>): void {
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

  trackById(index: number, item: ICountry): any {
    return item.id;
  }
}
