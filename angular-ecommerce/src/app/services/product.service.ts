import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../common/product';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/products';

  private baseCategoryUrl = 'http://localhost:8080/api/product-category';

  private searchUrl='http://localhost:8080/api/product'

  constructor(private httpClient: HttpClient) { }

  public getProductList(theCategoryId: number): Observable<Product[]> {
    const searchUrl= `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`;

    return this.getProducts(searchUrl);
  }
  private getProducts(searchUrl: string): Observable<Product[]> {
    return this.httpClient.get<GetResponseProducts>(searchUrl).pipe(
      map(response => response._embedded.products)
    );
  }

  public getProductCategories(): Observable<ProductCategory[]> {

    return this.httpClient.get<GetResponseCategory>(this.baseCategoryUrl).pipe(
      map(response => response._embedded.productCategory)
    );
  }
  public searchProducts(keyword: string){
    const searchUrl= `${this.baseUrl}/search/findByNameContaining?name=${keyword}`;

    return this.getProducts(searchUrl);
  }
  
  getProduct(theProductId: number): Observable<Product> {
    const productUrl = `${this.baseUrl}/${theProductId}`;

    return this.httpClient.get<Product>(productUrl);
  }
}


interface GetResponseProducts {
  _embedded: {
    products: Product[];
  }
}
interface GetResponseCategory {
  _embedded: {
    productCategory: ProductCategory[];
  }
}
