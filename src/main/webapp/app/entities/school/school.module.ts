import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GdpJhipsterSharedModule } from 'app/shared/shared.module';
import { SchoolComponent } from './school.component';
import { SchoolDetailComponent } from './school-detail.component';
import { SchoolUpdateComponent } from './school-update.component';
import { SchoolDeleteDialogComponent } from './school-delete-dialog.component';
import { schoolRoute } from './school.route';

@NgModule({
  imports: [GdpJhipsterSharedModule, RouterModule.forChild(schoolRoute)],
  declarations: [SchoolComponent, SchoolDetailComponent, SchoolUpdateComponent, SchoolDeleteDialogComponent],
  entryComponents: [SchoolDeleteDialogComponent],
})
export class GdpJhipsterSchoolModule {}
