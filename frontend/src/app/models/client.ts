export interface Client{
  id: number;
  name: string;
  surname: string;
  phone: string;
  email: string;
  address: string | null;
  hasDrivingLicence: boolean;
  registrationDateTime: string | null;
  dateOfBirth: string;
}
