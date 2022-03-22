import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IStudent, Student } from '../student.model';

import { StudentService } from './student.service';

describe('Service Tests', () => {
  describe('Student Service', () => {
    let service: StudentService;
    let httpMock: HttpTestingController;
    let elemDefault: IStudent;
    let expectedResult: IStudent | IStudent[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(StudentService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
        age: 0,
        gander: 0,
        studyGroup: 'AAAAAAA',
        nationalId: 'AAAAAAA',
        parentNumber: 'AAAAAAA',
        rate: 'AAAAAAA',
        address: 'AAAAAAA',
        remarks: 'AAAAAAA',
        joiningDate: currentDate,
        price: 0,
        currentSora: 'AAAAAAA',
        stage: 'AAAAAAA',
        grade: 'AAAAAAA',
        readAndWriteRate: 'AAAAAAA',
        lastTestRate: 'AAAAAAA',
        groupClass: 'AAAAAAA',
        disConnected: false,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            joiningDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Student', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            joiningDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            joiningDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Student()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Student', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            age: 1,
            gander: 1,
            studyGroup: 'BBBBBB',
            nationalId: 'BBBBBB',
            parentNumber: 'BBBBBB',
            rate: 'BBBBBB',
            address: 'BBBBBB',
            remarks: 'BBBBBB',
            joiningDate: currentDate.format(DATE_FORMAT),
            price: 1,
            currentSora: 'BBBBBB',
            stage: 'BBBBBB',
            grade: 'BBBBBB',
            readAndWriteRate: 'BBBBBB',
            lastTestRate: 'BBBBBB',
            groupClass: 'BBBBBB',
            disConnected: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            joiningDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a Student', () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
            nationalId: 'BBBBBB',
            rate: 'BBBBBB',
            address: 'BBBBBB',
            remarks: 'BBBBBB',
            currentSora: 'BBBBBB',
            stage: 'BBBBBB',
            grade: 'BBBBBB',
            disConnected: true,
          },
          new Student()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            joiningDate: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Student', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            age: 1,
            gander: 1,
            studyGroup: 'BBBBBB',
            nationalId: 'BBBBBB',
            parentNumber: 'BBBBBB',
            rate: 'BBBBBB',
            address: 'BBBBBB',
            remarks: 'BBBBBB',
            joiningDate: currentDate.format(DATE_FORMAT),
            price: 1,
            currentSora: 'BBBBBB',
            stage: 'BBBBBB',
            grade: 'BBBBBB',
            readAndWriteRate: 'BBBBBB',
            lastTestRate: 'BBBBBB',
            groupClass: 'BBBBBB',
            disConnected: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            joiningDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Student', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addStudentToCollectionIfMissing', () => {
        it('should add a Student to an empty array', () => {
          const student: IStudent = { id: 123 };
          expectedResult = service.addStudentToCollectionIfMissing([], student);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(student);
        });

        it('should not add a Student to an array that contains it', () => {
          const student: IStudent = { id: 123 };
          const studentCollection: IStudent[] = [
            {
              ...student,
            },
            { id: 456 },
          ];
          expectedResult = service.addStudentToCollectionIfMissing(studentCollection, student);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a Student to an array that doesn't contain it", () => {
          const student: IStudent = { id: 123 };
          const studentCollection: IStudent[] = [{ id: 456 }];
          expectedResult = service.addStudentToCollectionIfMissing(studentCollection, student);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(student);
        });

        it('should add only unique Student to an array', () => {
          const studentArray: IStudent[] = [{ id: 123 }, { id: 456 }, { id: 83886 }];
          const studentCollection: IStudent[] = [{ id: 123 }];
          expectedResult = service.addStudentToCollectionIfMissing(studentCollection, ...studentArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const student: IStudent = { id: 123 };
          const student2: IStudent = { id: 456 };
          expectedResult = service.addStudentToCollectionIfMissing([], student, student2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(student);
          expect(expectedResult).toContain(student2);
        });

        it('should accept null and undefined values', () => {
          const student: IStudent = { id: 123 };
          expectedResult = service.addStudentToCollectionIfMissing([], null, student, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(student);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
