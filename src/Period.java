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
}
