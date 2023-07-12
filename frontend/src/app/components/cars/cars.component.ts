import { Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {CarService} from "../../services/car.service";
import {Car} from "../../models/car";


@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})

  export class CarsComponent implements OnInit {
  displayedColumns: Array<string> = ['id', 'brand', 'model', 'productionYear', 'color', 'available', 'price'];
  dataSource: MatTableDataSource<Car>;
  cars!: Array<Car>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private carService: CarService
  ) {

    // Assign the data to the data source for the table to render
    this.dataSource = new MatTableDataSource(this.cars);
    console.log('inside cars component constructor')
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.carService.getAllCars()
      .subscribe(cars => {
        this.cars=cars;
        this.dataSource.data = this.cars;
        console.log(`results: ${JSON.stringify(cars, null, 2)}`);
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}


