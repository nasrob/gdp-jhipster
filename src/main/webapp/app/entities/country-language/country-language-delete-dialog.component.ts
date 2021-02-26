import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICountryLanguage } from 'app/shared/model/country-language.model';
import { CountryLanguageService } from './country-language.service';

@Component({
  templateUrl: './country-language-delete-dialog.component.html',
})
export class CountryLanguageDeleteDialogComponent {
  countryLanguage?: ICountryLanguage;

  constructor(
    protected countryLanguageService: CountryLanguageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.countryLanguageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('countryLanguageListModification');
      this.activeModal.close();
    });
  }
}
