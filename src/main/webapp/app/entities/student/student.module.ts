import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { StudentComponent } from './list/student.component';
import { StudentDetailComponent } from './detail/student-detail.component';
import { StudentUpdateComponent } from './update/student-update.component';
import { StudentDeleteDialogComponent } from './delete/student-delete-dialog.component';
import { StudentRoutingModule } from './route/student-routing.module';
import { LatedPaymentComponent } from './latedlist/lated-payment.component';

@NgModule({
  imports: [SharedModule, StudentRoutingModule],
  declarations: [StudentComponent, StudentDetailComponent, StudentUpdateComponent, StudentDeleteDialogComponent,LatedPaymentComponent],
  entryComponents: [StudentDeleteDialogComponent],
})
export class StudentModule {}
