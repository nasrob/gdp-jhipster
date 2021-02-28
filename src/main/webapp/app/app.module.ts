import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { GdpJhipsterSharedModule } from 'app/shared/shared.module';
import { GdpJhipsterCoreModule } from 'app/core/core.module';
import { GdpJhipsterAppRoutingModule } from './app-routing.module';
import { GdpJhipsterHomeModule } from './home/home.module';
import { GdpJhipsterEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { CountryGDPModule } from './gdp/gdp.module';

@NgModule({
  imports: [
    BrowserModule,
    GdpJhipsterSharedModule,
    GdpJhipsterCoreModule,
    GdpJhipsterHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    GdpJhipsterEntityModule,
    GdpJhipsterAppRoutingModule,
    CountryGDPModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class GdpJhipsterAppModule {}
