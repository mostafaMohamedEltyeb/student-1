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
  currentSora?: string | null;
  stage?: string | null;
  grade?: string | null;
  readAndWriteRate?: string | null;
  lastTestRate?: string | null;
  groupClass?: string | null;
  disConnected?: boolean | null;
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
    public price?: number | null,
    public currentSora?: string | null,
    public stage?: string | null,
    public grade?: string | null,
    public readAndWriteRate?: string | null,
    public lastTestRate?: string | null,
    public groupClass?: string | null,
    public disConnected?: boolean | null
  ) {
    this.disConnected = this.disConnected ?? false;
  }
}

export function getStudentIdentifier(student: IStudent): number | undefined {
  return student.id;
}
