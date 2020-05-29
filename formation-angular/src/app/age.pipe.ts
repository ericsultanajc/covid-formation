import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'age'
})
export class AgePipe implements PipeTransform {

  transform(value: string, ...args: any[]): any {
    let dtValue: Date = new Date(value);

    let dtJour: Date = new Date();

    let age = dtJour.getFullYear() - dtValue.getFullYear();

    return age;
  }

}
