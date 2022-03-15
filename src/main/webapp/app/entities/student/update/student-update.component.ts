import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IStudent, Student } from '../student.model';
import { StudentService } from '../service/student.service';

@Component({
  selector: 'jhi-student-update',
  templateUrl: './student-update.component.html',
})
export class StudentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    age: [],
    gander: [],
    studyGroup: [],
    nationalId: [],
    parentNumber: [],
    rate: [],
    address: [],
    remarks: [],
    joiningDate: [],
    price: [],
  });

  constructor(protected studentService: StudentService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ student }) => {
      this.updateForm(student);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const student = this.createFromForm();
    if (student.id !== undefined) {
      this.subscribeToSaveResponse(this.studentService.update(student));
    } else {
      this.subscribeToSaveResponse(this.studentService.create(student));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStudent>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(student: IStudent): void {
    this.editForm.patchValue({
      id: student.id,
      name: student.name,
      age: student.age,
      gander: student.gander,
      studyGroup: student.studyGroup,
      nationalId: student.nationalId,
      parentNumber: student.parentNumber,
      rate: student.rate,
      address: student.address,
      remarks: student.remarks,
      joiningDate: student.joiningDate,
      price: student.price,
    });
  }

  protected createFromForm(): IStudent {
    return {
      ...new Student(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      age: this.editForm.get(['age'])!.value,
      gander: this.editForm.get(['gander'])!.value,
      studyGroup: this.editForm.get(['studyGroup'])!.value,
      nationalId: this.editForm.get(['nationalId'])!.value,
      parentNumber: this.editForm.get(['parentNumber'])!.value,
      rate: this.editForm.get(['rate'])!.value,
      address: this.editForm.get(['address'])!.value,
      remarks: this.editForm.get(['remarks'])!.value,
      joiningDate: this.editForm.get(['joiningDate'])!.value,
      price: this.editForm.get(['price'])!.value,
    };
  }
}
