import { Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {Car, Color} from "../../models/car";
import {CarService} from "../../services/car/car.service";
import {FormControl, FormGroup} from "@angular/forms";


@Component({
  selector: 'app-cars',
  templateUrl: './cars.component.html',
  styleUrls: ['./cars.component.css']
})

  export class CarsComponent implements OnInit {
  displayedColumns: Array<string> = ['id', 'brand', 'model', 'productionYear', 'color', 'available', 'price'];
  dataSource: MatTableDataSource<Car>;
  cars!: Array<Car>;
  eColor = Color;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  carForm= new FormGroup({
    model: new FormControl(''),
    brand: new FormControl(''),
    productionYear: new FormControl(''),
    color: new FormControl(''),
    available: new FormControl(''),
    price: new FormControl('')
  });

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


