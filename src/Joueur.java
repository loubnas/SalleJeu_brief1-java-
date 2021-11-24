import Helpers.ConsoleForeground;

import static Helpers.ConsoleHelper.*;

public class Joueur {
    private static int __ID;
    private int ID;
    private String NomComplet;

    // Constructeur :
    public Joueur(String NomComplet){
        Joueur.__ID++;
        this.ID=Joueur.__ID;
        this.NomComplet=NomComplet;
    }

    public static Joueur NouveauJoueur(){
        Print("");
        Print("------- Information du joueur -------", ConsoleForeground.PURPLE);
        do{

            String nomComplet=ReadString("Donner le nom complet du joueur : ");
        if(nomComplet.trim().length()>=3 && nomComplet.trim().length()<=10) {
            return new Joueur(nomComplet);
        }else{
            Print("Le nom de joueur ne respect pas les regles.", ConsoleForeground.RED);
        }
            //return null;
        }
        while(true);


    }

    @Override
    public String toString() {
        return "CODE-"+ID;
    }
}
