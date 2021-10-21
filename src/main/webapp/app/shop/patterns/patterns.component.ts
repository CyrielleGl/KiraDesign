import { Component, OnInit } from '@angular/core';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'jhi-patterns',
  templateUrl: './patterns.component.html',
  styleUrls: ['./patterns.component.scss'],
})
export class PatternsComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  downloadPdf(): void {
    const pdfUrl = './content/patterns/kd-pattern-jules.pdf';
    const pdfName = 'kd_pattern_jules';
    FileSaver.saveAs(pdfUrl, pdfName);
  }
}
