import java.util.Objects;

public class Jeu {
    private CategorieJeu Categorie;
    private String NomJeu;

    // Constructeur :
    public Jeu(CategorieJeu Cat,String Nom){
        this.Categorie=Cat;
        this.NomJeu=Nom;
    }

    public String getNomJeu(){

        return this.NomJeu;
    }


    public boolean equals(Jeu o) {
        return Categorie == o.Categorie && Objects.equals(NomJeu, o.NomJeu);
    }


    @Override
    public String toString() {
        return "Jeu{" +
                "Categorie=" + Categorie +
                ", NomJeu='" + NomJeu + '\'' +
                '}';
    }
}
