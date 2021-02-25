export interface ITeacher {
  id?: number;
  name?: string;
  age?: number;
  schoolName?: string;
  schoolId?: number;
}

export class Teacher implements ITeacher {
  constructor(public id?: number, public name?: string, public age?: number, public schoolName?: string, public schoolId?: number) {}
}
