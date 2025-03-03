import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { InitialComponent } from './pages/initial/initial.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, InitialComponent,],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'betprac';
}
