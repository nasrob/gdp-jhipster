import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CountryLanguageService } from 'app/entities/country-language/country-language.service';
import { ICountryLanguage, CountryLanguage } from 'app/shared/model/country-language.model';
import { TrueFalse } from 'app/shared/model/enumerations/true-false.model';

describe('Service Tests', () => {
  describe('CountryLanguage Service', () => {
    let injector: TestBed;
    let service: CountryLanguageService;
    let httpMock: HttpTestingController;
    let elemDefault: ICountryLanguage;
    let expectedResult: ICountryLanguage | ICountryLanguage[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CountryLanguageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CountryLanguage(0, 'AAAAAAA', TrueFalse.T, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CountryLanguage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CountryLanguage()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CountryLanguage', () => {
        const returnedFromService = Object.assign(
          {
            language: 'BBBBBB',
            isOfficial: 'BBBBBB',
            percentage: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CountryLanguage', () => {
        const returnedFromService = Object.assign(
          {
            language: 'BBBBBB',
            isOfficial: 'BBBBBB',
            percentage: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CountryLanguage', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
