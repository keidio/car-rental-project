import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Car} from "../models/car";

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(
    private http: HttpClient
  ) { }

  getAllClients(): Observable<Array<Car>>{
    return this.http.get<Array<Car>>('assets/cars.json')
  }
}
