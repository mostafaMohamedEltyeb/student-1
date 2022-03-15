import * as dayjs from 'dayjs';

export interface IStudent {
  id?: number;
  name?: string | null;
  age?: number | null;
  gander?: number | null;
  studyGroup?: string | null;
  nationalId?: string | null;
  parentNumber?: string | null;
  rate?: string | null;
  address?: string | null;
  remarks?: string | null;
  joiningDate?: dayjs.Dayjs | null;
  price?: number | null;
}

export class Student implements IStudent {
  constructor(
    public id?: number,
    public name?: string | null,
    public age?: number | null,
    public gander?: number | null,
    public studyGroup?: string | null,
    public nationalId?: string | null,
    public parentNumber?: string | null,
    public rate?: string | null,
    public address?: string | null,
    public remarks?: string | null,
    public joiningDate?: dayjs.Dayjs | null,
    public price?: number | null
  ) {}
}

export function getStudentIdentifier(student: IStudent): number | undefined {
  return student.id;
}
