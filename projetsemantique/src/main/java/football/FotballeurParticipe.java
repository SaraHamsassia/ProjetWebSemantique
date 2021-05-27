package football;

public class FotballeurParticipe {

    private Footballeur player;
    private Match match;
    private boolean titulaire;
    private int carton_rouge;
    private int carton_jaune;

    public FotballeurParticipe() {
    }

    public FotballeurParticipe(Footballeur player, Match match, boolean titulaire, int carton_rouge, int carton_jaune) {

    }

    public void setCarton_jaune(int carton_jaune) {
        this.carton_jaune = carton_jaune;
    }

    public int getCarton_jaune() {
        return carton_jaune;
    }

    public void setCarton_rouge(int carton_rouge) {
        this.carton_rouge = carton_rouge;
    }

    public int getCarton_rouge() {
        return carton_rouge;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public void setPlayer(Footballeur player) {
        this.player = player;
    }

    public Footballeur getPlayer() {
        return player;
    }

    public void setTitulaire(boolean titulaire) {
        this.titulaire = titulaire;
    }

    public boolean getTitulaire() {

        return this.titulaire;
    }

}
