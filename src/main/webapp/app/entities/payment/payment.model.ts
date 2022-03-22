import * as dayjs from 'dayjs';
import { IStudent } from 'app/entities/student/student.model';

export interface IPayment {
  id?: number;
  cost?: number | null;
  date?: dayjs.Dayjs | null;
  month?: number | null;
  year?: number | null;
  studentId?: IStudent | null;
}

export class Payment implements IPayment {
  constructor(
    public id?: number,
    public cost?: number | null,
    public date?: dayjs.Dayjs | null,
    public month?: number | null,
    public year?: number | null,
    public studentId?: IStudent | null
  ) {}
}

export function getPaymentIdentifier(payment: IPayment): number | undefined {
  return payment.id;
}
