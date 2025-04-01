import { Component, Input } from '@angular/core';

interface Odds {
    betType: string;
    value: string;
    odd: number;
}

interface Match {
    league: string;
    homeTeam: string;
    awayTeam: string;
    imgHomeTeam: string;
    imgAwayTeam: string;
    oddsList: Odds[];
}

@Component({
    selector: 'app-card-games',
    templateUrl: './card-games.component.html',
    styleUrls: ['./card-games.component.css']
})
export class CardGamesComponent {
    @Input() match!: Match;

    getOddForType(oddsList: Odds[], value: string): string {
        const odd = oddsList.find(o => o.value === value);
        return odd ? odd.odd.toFixed(2) : '-';
    }

    placeBet(type: string, match: Match) {
        console.log(`Aposta no ${type} para o jogo ${match.homeTeam} vs ${match.awayTeam}`);
    }
}
