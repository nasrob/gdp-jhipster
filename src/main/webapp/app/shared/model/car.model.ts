import { Moment } from 'moment';

export interface ICar {
  id?: number;
  name?: string;
  model?: string;
  manufactureYear?: Moment;
  ownerName?: string;
  ownerId?: number;
}

export class Car implements ICar {
  constructor(
    public id?: number,
    public name?: string,
    public model?: string,
    public manufactureYear?: Moment,
    public ownerName?: string,
    public ownerId?: number
  ) {}
}
