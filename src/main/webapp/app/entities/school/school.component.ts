import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISchool } from 'app/shared/model/school.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SchoolService } from './school.service';
import { SchoolDeleteDialogComponent } from './school-delete-dialog.component';

@Component({
  selector: 'jhi-school',
  templateUrl: './school.component.html',
})
export class SchoolComponent implements OnInit, OnDestroy {
  schools: ISchool[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected schoolService: SchoolService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.schools = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.schoolService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<ISchool[]>) => this.paginateSchools(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.schools = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSchools();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISchool): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSchools(): void {
    this.eventSubscriber = this.eventManager.subscribe('schoolListModification', () => this.reset());
  }

  delete(school: ISchool): void {
    const modalRef = this.modalService.open(SchoolDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.school = school;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSchools(data: ISchool[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.schools.push(data[i]);
      }
    }
  }
}
