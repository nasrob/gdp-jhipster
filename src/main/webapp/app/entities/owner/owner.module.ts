import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GdpJhipsterSharedModule } from 'app/shared/shared.module';
import { OwnerComponent } from './owner.component';
import { OwnerDetailComponent } from './owner-detail.component';
import { OwnerUpdateComponent } from './owner-update.component';
import { OwnerDeleteDialogComponent } from './owner-delete-dialog.component';
import { ownerRoute } from './owner.route';

@NgModule({
  imports: [GdpJhipsterSharedModule, RouterModule.forChild(ownerRoute)],
  declarations: [OwnerComponent, OwnerDetailComponent, OwnerUpdateComponent, OwnerDeleteDialogComponent],
  entryComponents: [OwnerDeleteDialogComponent],
})
export class GdpJhipsterOwnerModule {}
