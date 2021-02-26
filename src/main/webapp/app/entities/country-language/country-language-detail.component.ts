import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICountryLanguage } from 'app/shared/model/country-language.model';

@Component({
  selector: 'jhi-country-language-detail',
  templateUrl: './country-language-detail.component.html',
})
export class CountryLanguageDetailComponent implements OnInit {
  countryLanguage: ICountryLanguage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ countryLanguage }) => (this.countryLanguage = countryLanguage));
  }

  previousState(): void {
    window.history.back();
  }
}
