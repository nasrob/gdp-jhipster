import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICountryLanguage } from 'app/shared/model/country-language.model';

type EntityResponseType = HttpResponse<ICountryLanguage>;
type EntityArrayResponseType = HttpResponse<ICountryLanguage[]>;

@Injectable({ providedIn: 'root' })
export class CountryLanguageService {
  public resourceUrl = SERVER_API_URL + 'api/country-languages';

  constructor(protected http: HttpClient) {}

  create(countryLanguage: ICountryLanguage): Observable<EntityResponseType> {
    return this.http.post<ICountryLanguage>(this.resourceUrl, countryLanguage, { observe: 'response' });
  }

  update(countryLanguage: ICountryLanguage): Observable<EntityResponseType> {
    return this.http.put<ICountryLanguage>(this.resourceUrl, countryLanguage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICountryLanguage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICountryLanguage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
