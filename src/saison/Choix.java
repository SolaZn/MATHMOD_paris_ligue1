package saison;

public enum Choix {
    H,D,A;  /*Home : 1, Draw : N, Away :2*/

    static Choix bind(String caractere){
        if(caractere.equals("H")){
            return H;
        }else if(caractere.equals("D")){
            return D;
        }else if(caractere.equals("A")){
            return A;
        }
        throw new RuntimeException("euh");
    }
}
