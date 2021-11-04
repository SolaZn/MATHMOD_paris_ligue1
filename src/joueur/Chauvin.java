package joueur;

import saison.Match;
import saison.Pari;
import saison.Choix;

public class Chauvin extends Parieur {
    private String equipeFav;
    public int mise;

    public Chauvin(String nomEquipe){
        equipeFav = nomEquipe;
        mise = 10;
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

        if(equipe == Choix.H){
            coteChoisie = match.getCotes().get(0);
            setBankroll(mise);
            nombreParisEffectues++;
            return new Pari(match,mise,Choix.H,this, coteChoisie);
        } else if(equipe == Choix.A){
            coteChoisie = match.getCotes().get(2);
            setBankroll(mise);
            nombreParisEffectues++;
            return new Pari(match,mise,Choix.A,this,coteChoisie);
        }
        return null;
    }

}
