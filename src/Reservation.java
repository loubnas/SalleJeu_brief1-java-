import java.util.Date;

public class Reservation {
    private Joueur joueur;
    private Period period;
    private Jeu jeu;
    private Date date;
    private Poste poste;

    // Constructeur :
    public Reservation(Joueur joueur,Period period,Jeu jeu){
        this.joueur=joueur;
        this.period=period;
        this.jeu=jeu;
        this.date=new Date();
        this.poste=null;
    }
    public Period getPeriod(){

        return this.period;
    }

    public  void setPoste(Poste poste){

        this.poste=poste;
    }

    public Poste getPoste(){

        return this.poste;
    }


}
