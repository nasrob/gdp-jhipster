import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { SERVER_API_URL } from '../app.constants';
import { Observable } from 'rxjs';
import { ICountry } from '../shared/model/country.model';
import { createRequestOption } from '../shared/util/request-util';

type EntityResponseType = HttpResponse<ICountry>;
type EntityArrayResponseType = HttpResponse<ICountry[]>;

@Injectable({
  providedIn: 'root',
})
export class GdpService {
  public searchCountryUrl: string = SERVER_API_URL + 'api/open/search-countries';
  public showGDPUrl: string = SERVER_API_URL + 'api/open/show-gdp';

  constructor(private http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICountry>(`${this.showGDPUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICountry[]>(this.searchCountryUrl, { params: options, observe: 'response' });
  }
}
