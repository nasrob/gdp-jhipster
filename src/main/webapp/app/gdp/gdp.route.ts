import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, scheduled } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Country, ICountry } from '../shared/model/country.model';
import { CountryGDPComponent } from './country-gdp/country-gdp.component';
import { GdpService } from './gdp.service';
import { SearchCountryComponent } from './search-country/search-country.component';

@Injectable({ providedIn: 'root' })
export class CountryGDPResolve implements Resolve<ICountry> {
  constructor(private service: GdpService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICountry> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Country>) => response.ok),
        map((country: HttpResponse<Country>) => country.body)
      );
    }
    return of(new Country());
  }
}

export const countryGDPRoutes: Routes = [
  {
    path: 'countries',
    component: SearchCountryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      defaultSort: 'name.asc',
      pageTitle: 'gdpApp.country.home.title',
    },
  },
  {
    path: 'showGDP/:id',
    component: CountryGDPComponent,
    resolve: {
      country: CountryGDPResolve,
    },
  },
];
