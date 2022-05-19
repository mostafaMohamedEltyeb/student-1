import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IStudent } from '../student.model';
import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { StudentService } from '../service/student.service';
import { StudentDeleteDialogComponent } from '../delete/student-delete-dialog.component';
import { ExcelService } from './../../../excel.service';

@Component({
  selector: 'jhi-student',
  templateUrl: './student.component.html',
})
export class StudentComponent implements OnInit {
  students?: IStudent[];
  isLoading = false;
  totalItems = 0;

  studentName?: string;
  studentName1?: string;
  studentGander?: number;
  studentGander1?: number;
  ageTo?: number;
  ageFrom?: number;
  ageTo1?: number;
  ageFrom1?: number;
  studentGroup?: string;
  studentGroup1?: string;
  disconnected?: false;

  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected excelService : ExcelService ,
    protected studentService: StudentService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal,
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

     if (this.studentName === undefined || this.studentName === '') {
     this.studentName1 = 'zzNull'
     }
     else {
      this.studentName1 = this.studentName
      }

     if (this.studentGander === undefined) {
     this.studentGander1 = 3
     }
     else {
     this.studentGander1 = this.studentGander
     }
     if (!this.ageTo) {
       this.ageTo1 = 0
       }
       else {
       this.ageTo1 = this.ageTo
        }
        if (!this.ageFrom) {
          this.ageFrom1 = 0
          }
          else {
          this.ageFrom1 = this.ageFrom
           }
           if (!this.studentGroup)  {
             this.studentGroup1 = 'zzNull'
             }
             else {
             this.studentGroup1 = this.studentGroup
              }
      if (this.disconnected === undefined) {
          this.disconnected = false
          }



    this.studentService
      .filter({
        studentName : this.studentName1,
        studentGander : this.studentGander1,
        ageTo : this.ageTo1,
        ageFrom : this.ageFrom1,
        studentGroup : this.studentGroup1,
        disconnected : this.disconnected,
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IStudent[]>) => {
          this.isLoading = false;
          this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate);
        },
        () => {
          this.isLoading = false;
          this.onError();
        }
      );
  }

  ngOnInit(): void {
    this.handleNavigation();
  }

  exportStores () {
    return this.excelService.exportExcel ({
     data : this.students ,
     fileName : 'data',
     sheetName : "data" ,
     header : []
   })
 }
 
  trackId(index: number, item: IStudent): number {
    return item.id!;
  }

  delete(student: IStudent): void {
    const modalRef = this.modalService.open(StudentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.student = student;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadPage();
      }
    });
  }

  protected sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected handleNavigation(): void {
    combineLatest([this.activatedRoute.data, this.activatedRoute.queryParamMap]).subscribe(([data, params]) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    });
  }

  protected onSuccess(data: IStudent[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/student'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.students = data ?? [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page ?? 1;
  }


}
