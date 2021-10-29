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
      console.warn(this.patterns);
    });
  }

  downloadPdf(pattern: any): void {
    const pdfUrl = pattern.linkPdf;
    const pdfName = pattern.pdfName;
    FileSaver.saveAs(pdfUrl, pdfName);
  }
}
