import { Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {Car, Color} from "../../models/car";
import {CarService} from "../../services/car/car.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";


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
    brand: new FormControl('', [Validators.required, Validators.minLength(3)]),
    model: new FormControl('', [Validators.required, Validators.minLength(2)]),
    productionYear: new FormControl('YYYY-MM', [Validators.required, Validators.minLength(7)] ),
    color: new FormControl('', Validators.required),
    available: new FormControl('', Validators.required),
    price: new FormControl('', [Validators.required, Validators.minLength(4)])
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

  onSubmit() {
    console.log(`data to send: ${JSON.stringify(this.carForm.value, null, 2)}`);
  }
}


