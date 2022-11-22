import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Luv2shopFormService {
  constructor() {}
  getCreditCardMonths(startMonth: number): Observable<number[]> {
    let data: number[] = [];

    for (let tempMonth = startMonth; tempMonth <= 12; tempMonth++) {
      data.push(tempMonth);
    }
    return of(data);
  }
  getCreditCardYears(): Observable<number[]> {
    let data: number[] = [];

    let startYear: number = new Date().getFullYear();

    for (let tempYear = startYear; tempYear <= startYear + 10; tempYear++) {
      data.push(tempYear);
    }
    return of(data);
  }
}
