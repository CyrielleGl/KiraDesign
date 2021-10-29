import { Component, OnInit } from '@angular/core';
import * as FileSaver from 'file-saver';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'jhi-patterns',
  templateUrl: './patterns.component.html',
  styleUrls: ['./patterns.component.scss'],
})
export class PatternsComponent implements OnInit {
  patterns: any;

  constructor(private httpClient: HttpClient) {}

  ngOnInit(): void {
    this.httpClient.get<any>('content/data/patterns.json').subscribe((data: any) => {
      this.patterns = data.patterns;
    });
  }

  downloadPdf(): void {
    let pdfUrl = '';
    let pdfName = '';
    this.patterns.map((pattern: any) => {
      pdfUrl = pattern.linkPdf;
      pdfName = pattern.pdfName;
    });
    FileSaver.saveAs(pdfUrl, pdfName);
  }
}
