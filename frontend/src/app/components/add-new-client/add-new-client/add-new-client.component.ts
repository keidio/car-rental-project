import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../../models/client";
import {ClientService} from "../../../services/client/client.service";

@Component({
  selector: 'app-add-new-client',
  templateUrl: './add-new-client.component.html',
  styleUrls: ['./add-new-client.component.css']
})
export class AddNewClientComponent {

  clientForm = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(2)]),
    surname: new FormControl('', [Validators.required, Validators.minLength(2)]),
    phone: new FormControl('', [Validators.required, Validators.minLength(7)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    address: new FormControl(''),
    hasDrivingLicence: new FormControl(false, Validators.required ),
    registrationDateTime: new FormControl(null),
    dateOfBirth: new FormControl('', Validators.required)
  });

  constructor(
    private clientService: ClientService
  ) {
  }

  get name() {
    return this.clientForm.controls.name;
  }

  get surname() {
    return this.clientForm.controls.surname;
  }

  get phone() {
    return this.clientForm.controls.phone;
  }

  get email() {
    return this.clientForm.controls.email;
  }

  get address(){
    return this.clientForm.controls.address;
  }

  get drivingLicence(){
    return this.clientForm.controls.hasDrivingLicence;
  }

  get dateOfBirth() {
    return this.clientForm.controls.dateOfBirth;
  }

  onSubmit() {
    console.log(`data to send: ${JSON.stringify(this.clientForm.value, null, 2)}`);
    this.clientService.createClient(this.clientForm.value as Client)
      .subscribe(value => {
        this.clientForm.reset();
        window.close();
      });
  }
}
