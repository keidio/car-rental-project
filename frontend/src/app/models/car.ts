export interface Car{
  id: number;
  brand: string;
  model: string;
  productionYear: string;
  color: string;
  available: boolean;
  pricePerDayInEuroCents: string;
}

export enum Color{
  BLACK= 'BLACK',
  BLUE='BLUE',
  PINK='PINK',
  SILVER='SILVER'
}
