package joueur;

import saison.Match;
import saison.Pari;

public class Fou extends Parieur {
    public Fou(){
        super();
    }

    @Override
    public Pari parier(Match match) {
        // 1 : t'as un rand : si il est pair -> il parie
        // 2 : meme rand : autre nombre : on multiplie deux rands par 1000 -> si la différence entre les deux est entre -500 et 500 alors c'est un nul; au dessus de 500 -> équipe 1, en dessous de -500, équipe 2

        return null;
    }
}
