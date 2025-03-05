import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { NavegationbarComponent } from '../../components/navegationbar/navegationbar.component';
import { ContentComponent } from '../../components/content/content.component';

@Component({
  selector: 'app-initial',
  imports: [HeaderComponent, NavegationbarComponent, ContentComponent,],
  templateUrl: './initial.component.html',
  styleUrl: './initial.component.css'
})
export class InitialComponent {

}
