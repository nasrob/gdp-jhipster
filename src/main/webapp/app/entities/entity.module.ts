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
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GdpJhipsterEntityModule {}
