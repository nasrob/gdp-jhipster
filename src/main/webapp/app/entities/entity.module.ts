import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'owner',
        loadChildren: () => import('./owner/owner.module').then(m => m.GdpJhipsterOwnerModule),
      },
      {
        path: 'car',
        loadChildren: () => import('./car/car.module').then(m => m.GdpJhipsterCarModule),
      },
      {
        path: 'school',
        loadChildren: () => import('./school/school.module').then(m => m.GdpJhipsterSchoolModule),
      },
      {
        path: 'teacher',
        loadChildren: () => import('./teacher/teacher.module').then(m => m.GdpJhipsterTeacherModule),
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.GdpJhipsterCountryModule),
      },
      {
        path: 'city',
        loadChildren: () => import('./city/city.module').then(m => m.GdpJhipsterCityModule),
      },
      {
        path: 'country-language',
        loadChildren: () => import('./country-language/country-language.module').then(m => m.GdpJhipsterCountryLanguageModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GdpJhipsterEntityModule {}
