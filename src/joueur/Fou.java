package joueur;

import saison.Choix;
import saison.Match;
import saison.Pari;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Fou extends Parieur {

    public Fou(){
        super();
    }

    @Override
    public Pari parier(Match match) {
        // 1 : t'as un rand : si il est pair -> il parie
        // 2 : meme rand : autre nombre : on multiplie deux rands par 1000 -> si la différence entre les deux est entre -500 et 500 alors c'est un nul; au dessus de 500 -> équipe 1, en dessous de -500, équipe 2

        ArrayList<Choix> listeChoix = new ArrayList();

        listeChoix.add(Choix.A);
        listeChoix.add(Choix.H);
        listeChoix.add(Choix.D);

        Collections.shuffle(listeChoix); // Collections.shuffle utilise l'aléatoire de rand (voir docu)

        if(bankroll - MISEMOYENNE < DECOUVERTAUTORISE){
            if(idJourFin.equals("")){
                idJourFin = match.getJournee();
            }
            /*System.out.println(this.getClass().getSimpleName() + " n'a pas pu parier :<(");
            System.out.println("Pas assez d'argent... Mise refusée");
            System.out.println(this.getClass().getSimpleName() + " est à sec depuis " + this.idJourFin);*/
            return null;
        }


        if(new Random().nextInt()%2 == 0) {
            if (listeChoix.get(0) == Choix.A) {
                setBankroll(MISEMOYENNE);
                nbEff++;
                return new Pari(this, match, Choix.A, match.getCotes().get(2), MISEMOYENNE);
            } else if (listeChoix.get(0) == Choix.D) {
                setBankroll(MISEMOYENNE);
                nbEff++;
                return new Pari(this, match, Choix.D, match.getCotes().get(1), MISEMOYENNE);
            } else if (listeChoix.get(0) == Choix.H) {
                setBankroll(MISEMOYENNE);
                nbEff++;
                return new Pari(this, match, Choix.H, match.getCotes().get(0), MISEMOYENNE);
            }
        }
        return null;
    }
}
