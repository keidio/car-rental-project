import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BookingRecord} from "../../models/BookingRecord";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookingRecordService {

  constructor(
    private http: HttpClient
  ) { }

  findAllBookingRecords(): Observable<Array<BookingRecord>>{
    return this.http.get<Array<BookingRecord>>('/assets/bookingRecords.json')
  }
}
