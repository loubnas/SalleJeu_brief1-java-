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

}
