import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {BookingRecordService} from "../../services/booking-record/booking-record.service";
import {BookingRecord} from "../../models/BookingRecord";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit, AfterViewInit{
  displayedColumns: string[] = ['id', 'bookedCar{id}', 'client{id}', 'startDate', 'endDate', 'fullPriceInEuroCents'];
  dataSource: MatTableDataSource<BookingRecord>;
  bookingRecords!: Array<BookingRecord>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private bookingRecordService: BookingRecordService
  ) {
    this.dataSource = new MatTableDataSource(this.bookingRecords);
    console.log('inside bookingRecords component constructor');
  }

  ngOnInit() {

    this.bookingRecordService.findAllBookingRecords()
      .subscribe(bookingRecords => {
        this.bookingRecords = bookingRecords;
        this.dataSource.data =this.bookingRecords;
        console.log(`results: ${JSON.stringify(bookingRecords,null,2)}`)
      })
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
