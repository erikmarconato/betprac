import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { NavegationbarComponent } from '../../components/navegationbar/navegationbar.component';

@Component({
  selector: 'app-initial',
  imports: [HeaderComponent, NavegationbarComponent, ],
  templateUrl: './initial.component.html',
  styleUrl: './initial.component.css'
})
export class InitialComponent {

}
