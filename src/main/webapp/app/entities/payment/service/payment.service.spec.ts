import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IPayment, Payment } from '../payment.model';

import { PaymentService } from './payment.service';

describe('Service Tests', () => {
  describe('Payment Service', () => {
    let service: PaymentService;
    let httpMock: HttpTestingController;
    let elemDefault: IPayment;
    let expectedResult: IPayment | IPayment[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(PaymentService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        cost: 0,
        date: currentDate,
        month: 0,
        year: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Payment', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            date: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );

        service.create(new Payment()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Payment', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            cost: 1,
            date: currentDate.format(DATE_FORMAT),
            month: 1,
            year: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a Payment', () => {
        const patchObject = Object.assign(
          {
            month: 1,
            year: 1,
          },
          new Payment()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Payment', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            cost: 1,
            date: currentDate.format(DATE_FORMAT),
            month: 1,
            year: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Payment', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addPaymentToCollectionIfMissing', () => {
        it('should add a Payment to an empty array', () => {
          const payment: IPayment = { id: 123 };
          expectedResult = service.addPaymentToCollectionIfMissing([], payment);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(payment);
        });

        it('should not add a Payment to an array that contains it', () => {
          const payment: IPayment = { id: 123 };
          const paymentCollection: IPayment[] = [
            {
              ...payment,
            },
            { id: 456 },
          ];
          expectedResult = service.addPaymentToCollectionIfMissing(paymentCollection, payment);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a Payment to an array that doesn't contain it", () => {
          const payment: IPayment = { id: 123 };
          const paymentCollection: IPayment[] = [{ id: 456 }];
          expectedResult = service.addPaymentToCollectionIfMissing(paymentCollection, payment);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(payment);
        });

        it('should add only unique Payment to an array', () => {
          const paymentArray: IPayment[] = [{ id: 123 }, { id: 456 }, { id: 35742 }];
          const paymentCollection: IPayment[] = [{ id: 123 }];
          expectedResult = service.addPaymentToCollectionIfMissing(paymentCollection, ...paymentArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const payment: IPayment = { id: 123 };
          const payment2: IPayment = { id: 456 };
          expectedResult = service.addPaymentToCollectionIfMissing([], payment, payment2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(payment);
          expect(expectedResult).toContain(payment2);
        });

        it('should accept null and undefined values', () => {
          const payment: IPayment = { id: 123 };
          expectedResult = service.addPaymentToCollectionIfMissing([], null, payment, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(payment);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
