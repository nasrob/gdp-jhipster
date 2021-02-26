export interface ICity {
  id?: number;
  name?: string;
  district?: string;
  population?: number;
  countryName?: string;
  countryId?: number;
}

export class City implements ICity {
  constructor(
    public id?: number,
    public name?: string,
    public district?: string,
    public population?: number,
    public countryName?: string,
    public countryId?: number
  ) {}
}
