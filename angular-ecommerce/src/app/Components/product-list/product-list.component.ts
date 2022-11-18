import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit{
  products: Product[]=[];
  currentCategoryId: number=1;
  currentCategoryName!: string;
  searchMode!: boolean;

  constructor(private productService: ProductService,
      private route:ActivatedRoute){
  }

  listProducts() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');
    if(this.searchMode){
      this.handleSearchProducts();
    } else {
      this.handleListProduct();
    }
  }

  handleListProduct(){
    //check if "id" parammeter is available 
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
    this.currentCategoryName= this.route.snapshot.paramMap.get('name')!;

    if(hasCategoryId){
      this.currentCategoryId= +this.route.snapshot.paramMap.get('id')!;

      
    this.currentCategoryName= this.route.snapshot.paramMap.get('name')!;
    } else {
      this.currentCategoryId=1;
      this.currentCategoryName="books;"
    }
    this.productService.getProductList(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
      }
    )
  }

  handleSearchProducts() {
    const theKeyword: string = this.route.snapshot.paramMap.get('keyword')!;

    //search for the product using keyword
    this.productService.searchProducts(theKeyword).subscribe(
      data => {
        this.products = data;
      }
    )
  }

  ngOnInit(): void {
    this.listProducts();
    this.route.paramMap.subscribe(()=>{
      this.listProducts();
    });
  }
}
