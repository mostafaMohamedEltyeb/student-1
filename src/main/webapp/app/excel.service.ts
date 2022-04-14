import { Injectable } from '@angular/core';
import { utils as XLSXUtils, writeFile } from 'xlsx';
import { WorkBook, WorkSheet } from 'xlsx';

export interface IEExportAsExcelProps {
  readonly data: any[];
  readonly fileName: string;
  readonly sheetName?: string;
  readonly header?: string[];
  readonly table?: HTMLElement;
}

@Injectable({
  providedIn: 'root',
})
export class ExcelService {
  fileExtension = '.xlsx';

  public exportExcel({ data, fileName, sheetName, header = [], table }: IEExportAsExcelProps): void {
    let wb: WorkBook;

    if (table) {
      wb = XLSXUtils.table_to_book(table);
    } else {
      const ws: WorkSheet = XLSXUtils.json_to_sheet(data, { header });
      wb = XLSXUtils.book_new();

      XLSXUtils.book_append_sheet(wb, ws, sheetName);
    }
    writeFile(wb, `${fileName}${this.fileExtension}`);
  }
}

//  exportStores () {
//    this.exportExcelService.exportExcel ({
//     data : this.data ,
//     fileName : 'data',
//     sheetName : "data" ,
//     header : []
//   })
// }
