import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ICountry } from '../../shared/model/country.model';
import { GdpService } from '../gdp.service';
import { ITEMS_PER_PAGE } from '../../shared/constants/pagination.constants';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-search-country',
  templateUrl: './search-country.component.html',
  styleUrls: ['./search-country.component.scss'],
})
export class SearchCountryComponent implements OnInit {
  countries: ICountry[];
  routeData: any;
  totalItems: any;
  queryCount: any;
  itemsPerPage: number;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  nameFilter: string;
  continentFilter: string;

  constructor(private countryGDPService: GdpService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  ngOnInit(): void {
    this.continentFilter = '';
    this.nameFilter = '';
    this.loadAll();
  }

  loadAll() {
    this.countryGDPService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
        'name.contains': this.nameFilter,
        'continent.equals': this.continentFilter,
      })
      .subscribe((res: HttpResponse<ICountry[]>) => this.paginateCountries(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/countries'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc'),
      },
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/countries',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc'),
      },
    ]);
    this.loadAll();
  }

  searchCountires() {
    this.clear();
  }

  trackId(index: number, item: ICountry) {
    return item.id;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    return result;
  }

  private paginateCountries(data: ICountry[], headers: HttpHeaders) {
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.queryCount = this.totalItems;
    this.countries = data;
  }
}
