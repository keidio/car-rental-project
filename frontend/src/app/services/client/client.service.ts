import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client} from "../../models/client";
import {Observable} from "rxjs";
import {allClients} from "../../models/links";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(
    private http: HttpClient
  ) { }

  getAllClients(): Observable<Array<Client>>{
    return this.http.get<Array<Client>>(allClients)
  }
}
