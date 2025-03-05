import { Component } from '@angular/core';
import { CardGamesComponent } from '../card-games/card-games.component';

@Component({
  selector: 'app-content',
  imports: [CardGamesComponent,],
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent {

}
