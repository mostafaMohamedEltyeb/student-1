import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStudent } from '../student.model';

import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { StudentService } from '../service/student.service';

@Component({
  selector: 'jhi-lated-payment',
  templateUrl: './lated-payment.component.html',
})
export class LatedPaymentComponent implements OnInit {
  students?: IStudent[];
  searchByMonth?: number;
  searchByYear?: number ;
  isLoading = false;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page?: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  name : any
  month : any
  year : any
  constructor(
    protected studentService: StudentService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    this.isLoading = true;
    const pageToLoad: number = page ?? this.page ?? 1;

//      this.name = this.searchByName !== undefined ? this.searchByName : null
//       this.month = this.searchByMonth !== undefined ? this.searchByMonth : 0
//       this.year = this.searchByYear !== undefined ?  this.searchByYear : 0

     if (this.searchByMonth === undefined) {
     this.month = 0
     }
      else {
       this.month = this.searchByMonth
       }

     if (this.searchByYear === undefined) {
     this.year = 0
     }
     else {
     this.year = this.searchByYear
     }


    this.studentService
      .lated({
             month:this.month,
             year:this.year,


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

  trackId(index: number, item: IStudent): number {
    return item.id!;
  }

//   delete(payment: IStudent): void {
//     const modalRef = this.modalService.open(PaymentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
//     modalRef.componentInstance.payment = payment;
//     // unsubscribe not needed because closed completes on modal close
//     modalRef.closed.subscribe(reason => {
//       if (reason === 'deleted') {
//         this.loadPage();
//       }
//     });
//   }

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
        this.router.navigate(['/student/lated'], {
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
