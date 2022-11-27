import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrderHistory } from '../common/order-history';

@Injectable({
  providedIn: 'root',
})
export class OrderHistoryService {
  private orderUrl = environment.luv2shopApiUrl + '/orders';

  constructor(private httpclient: HttpClient) {}

  getOrderHistory(theEmail: string): Observable<GetTesponseOrderHistory> {
    // need to build URL based on the customer email
    const orderHistoryUrl = `${this.orderUrl}/search/findByCustomerEmailOrderByDateCreatedDesc?email=${theEmail}`;

    return this.httpclient.get<GetTesponseOrderHistory>(orderHistoryUrl);
  }
}

interface GetTesponseOrderHistory {
  _embedded: {
    orders: OrderHistory[];
  };
}
