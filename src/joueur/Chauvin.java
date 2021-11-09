package joueur;

import saison.Match;
import saison.Pari;
import saison.Choix;

public class Chauvin extends Parieur {
    private String equipeFav;

    public Chauvin(String nomEquipe){
        equipeFav = nomEquipe;
    }


    private Choix choixParEquipe(Match match){
        if(equipeFav.equals(match.getEquipes()[0])){
            return Choix.H;
        }
        if(equipeFav.equals(match.getEquipes()[1])){
            return Choix.A;
        }
        else{
            return null;
        }
    }


    @Override
    public Pari parier(Match match) {
        double coteChoisie;
        Choix equipe = choixParEquipe(match);

        if(bankroll - MISEMOYENNE < DECOUVERTAUTORISE){
            if(idJourFin.equals("")){
                idJourFin = match.getJournee();
            }
            /*System.out.println(this.getClass().getSimpleName() + " n'a pas pu parier :<(");
            System.out.println("Pas assez d'argent... Mise refusée");
            System.out.println(this.getClass().getSimpleName() + " est à sec " + this.idJourFin);*/
            return null;
        }

        if(equipe == Choix.H){
            coteChoisie = match.getCotes().get(0);
            setBankroll(MISEMOYENNE);
            nbEff++;
            return new Pari(this, match, Choix.H, coteChoisie, MISEMOYENNE);
        } else if(equipe == Choix.A){
            coteChoisie = match.getCotes().get(2);
            setBankroll(MISEMOYENNE);
            nbEff++;
            return new Pari(this, match, Choix.A, coteChoisie, MISEMOYENNE);
        }
        return null;
    }

}
