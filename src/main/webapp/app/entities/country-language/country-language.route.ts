import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICountryLanguage, CountryLanguage } from 'app/shared/model/country-language.model';
import { CountryLanguageService } from './country-language.service';
import { CountryLanguageComponent } from './country-language.component';
import { CountryLanguageDetailComponent } from './country-language-detail.component';
import { CountryLanguageUpdateComponent } from './country-language-update.component';

@Injectable({ providedIn: 'root' })
export class CountryLanguageResolve implements Resolve<ICountryLanguage> {
  constructor(private service: CountryLanguageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICountryLanguage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((countryLanguage: HttpResponse<CountryLanguage>) => {
          if (countryLanguage.body) {
            return of(countryLanguage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CountryLanguage());
  }
}

export const countryLanguageRoute: Routes = [
  {
    path: '',
    component: CountryLanguageComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gdpJhipsterApp.countryLanguage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CountryLanguageDetailComponent,
    resolve: {
      countryLanguage: CountryLanguageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gdpJhipsterApp.countryLanguage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CountryLanguageUpdateComponent,
    resolve: {
      countryLanguage: CountryLanguageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gdpJhipsterApp.countryLanguage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CountryLanguageUpdateComponent,
    resolve: {
      countryLanguage: CountryLanguageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gdpJhipsterApp.countryLanguage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
