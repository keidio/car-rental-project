import { Component } from '@angular/core';
import {homePageUrl, notFoundPageUrl} from "../../models/links";

@Component({
  selector: 'app-not-found-page',
  templateUrl: './not-found-page.component.html',
  styleUrls: ['./not-found-page.component.css']
})
export class NotFoundPageComponent {

  protected readonly notFoundPageUrl = notFoundPageUrl;
  protected readonly homePageUrl = homePageUrl;
}
