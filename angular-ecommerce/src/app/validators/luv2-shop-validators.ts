import { FormControl, ValidationErrors } from '@angular/forms';

export class Luv2ShopValidators {
  // white space validation
  static notOnlyWhiteSpace(control: FormControl): ValidationErrors {
    if (control.value != null && control.value.trim().length === 0) {
      //Invalid
      return { notOnlyWhiteSpace: true };
    } else {
      //Valid
      return null;
    }
  }
}
