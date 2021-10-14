import { Component, Input } from '@angular/core';

@Component({
  selector: 'jhi-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
})
export class FooterComponent {
  @Input()
  yearNow: string | undefined;

  @Input()
  appTitle: string | undefined;
}
