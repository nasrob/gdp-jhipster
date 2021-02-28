import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { countryGDPRoutes } from './gdp.route';
import { GdpJhipsterSharedModule } from '../shared/shared.module';
import { RouterModule } from '@angular/router';
import { SearchCountryComponent } from './search-country/search-country.component';
import { CountryGDPComponent } from './country-gdp/country-gdp.component';

const ENTITY_STATES = [...countryGDPRoutes];

@NgModule({
  declarations: [SearchCountryComponent, CountryGDPComponent],
  imports: [GdpJhipsterSharedModule, RouterModule.forChild(ENTITY_STATES)],
  entryComponents: [SearchCountryComponent, CountryGDPComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class CountryGDPModule {}
