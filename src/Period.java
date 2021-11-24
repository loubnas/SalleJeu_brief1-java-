import Helpers.ConsoleForeground;

import static Helpers.ConsoleHelper.Print;
import static Helpers.ConsoleHelper.ReadInt;

public enum Period {
    Min30("30min",5),
    Heure1("1Heure",10),
    Heure2("2Heures",18),
    Heure5("5Heures",40),
    Journee("Journee",65);

    private final int prix;
    private final String period;

    // Constructeur :
    Period(String period, int prix) {
        this.prix = prix;
        this.period = period;
    }

    //getters:
    public int getPrix() {
        return this.prix;
    }

    //getters:
    public String getPeriod() {
        return this.period;
    }

    public static Period SelectPeriod(){
        int choix=-1;
        do {
            Print("");
            Print("------- Selection de la periode -------",ConsoleForeground.PURPLE);
            Print("1. 30 Min.");
            Print("2. 01 Heure.");
            Print("3. 02 Heures.");
            Print("4. 05 Heures.");
            Print("5. Toute la journée.");
            Print("6. Quitter.");
            choix=ReadInt("Selectionner une periode : ");
        }while(choix<1 || choix >6);

        switch (choix){
            case 1: return Min30;
            case 2: return Heure1;
            case 3: return Heure2;
            case 4: return Heure5;
            case 5: return Journee;
            case 6: break;
            default:Print("Choix invalide.", ConsoleForeground.RED);break;
        }

        return null;
    }
}
