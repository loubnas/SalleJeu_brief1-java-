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
        Print("\n------- Information du joueur -------");
        String nomComplet=ReadString("Donner le nom complet du joueur : ");
        if(nomComplet!="") {
            return new Joueur(nomComplet);
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return "CODE-"+ID;
    }
}
