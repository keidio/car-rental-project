import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {ClientService} from "../../services/client/client.service";
import {Client} from "../../models/client";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {addNewClientPageUrl} from "../../models/links";
@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})

export class ClientsComponent implements OnInit, AfterViewInit {
  displayedColumns: Array<string> = ['id', 'name', 'surname', 'phone', 'email', 'address', 'hasDrivingLicence', 'registrationDateTime', 'dateOfBirth'];
  dataSource: MatTableDataSource<Client>;
  clients: Array<Client> = [];


  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  constructor(
    private clientService: ClientService
  ) {
    // Assign the data to the data source for the table to render
    this.dataSource = new MatTableDataSource(this.clients);
    console.log('inside clients component constructor');
  }

  ngOnInit() {
    this.fetchClient();
  }

  public fetchClient() {
    this.clientService.getAllClients()
      .subscribe(clients => {
        this.clients = clients;
        this.dataSource.data = this.clients;
        console.log(`results: ${JSON.stringify(clients, null, 2)}`);
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  /*calculateFormProgress(form: FormGroup): number {

    const controlCount = Object.keys(form.controls).length;
    let validCount = 0;

    for( const [key, value] of Object.entries(form.controls)) {
      if (value.valid) validCount++;
    }

    return validCount / controlCount * 100;
  }*/

  openAddNewClientPage() {
    window.open(addNewClientPageUrl, '_blank');
  }
}



























