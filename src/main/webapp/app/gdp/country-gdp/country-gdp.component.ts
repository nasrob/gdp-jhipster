import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IGdpData } from '../../shared/model/chart.gdp.model';
import { ICountry } from '../../shared/model/country.model';
import * as Chart from 'chart.js';

@Component({
  selector: 'jhi-country-gdp',
  templateUrl: './country-gdp.component.html',
  styleUrls: ['./country-gdp.component.scss'],
})
export class CountryGDPComponent implements OnInit {
  currentCountry!: ICountry;
  data!: IGdpData[];
  preGDPUrl = 'http://api.worldbank.org/v2/countries/';
  postGDPUrl = '/indicators/NY.GDP.MKTP.CD?format=json&per_page=' + 10;
  year = [];
  gdp = [];
  chart: any;
  noDataAvailable: any;

  constructor(private activatedRoute: ActivatedRoute, private httpClient: HttpClient) {
    this.activatedRoute.data.subscribe(result => {
      this.currentCountry = result.country;
    });
  }

  ngOnInit(): void {
    const gdpUrl = this.preGDPUrl + this.currentCountry.code + this.postGDPUrl;
    this.httpClient.get(gdpUrl).subscribe(res => {
      this.noDataAvailable = true;
      const gdpDataArr = res[1];
      if (gdpDataArr) {
        this.noDataAvailable = false;
        gdpDataArr.forEach(item => {
          this.year.push(item.date);
          this.gdp.push(item.value);
        });

        this.year = this.year.reverse();
        this.gdp = this.gdp.reverse();

        this.chart = new Chart('canvas', {
          type: 'line',
          data: {
            labels: this.year,
            datasets: [
              {
                data: this.gdp,
                borderColor: '#3CBA9F',
                fill: true,
              },
            ],
          },
          options: {
            legend: {
              display: false,
            },
            scales: {
              xAxes: [{ display: true }],
              yAxes: [{ display: true }],
            },
          },
        });
      }
    });
  }
}
