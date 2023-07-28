export interface BookingRecord{
  id: number;
  bookedCarId: number;
  clientId: number;
  startDate: string;
  endDate: string;
  fullPriceInEuroCents: number;
}
