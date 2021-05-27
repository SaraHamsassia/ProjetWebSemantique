package football;

import java.util.ArrayList;

public class Match {

    public static ArrayList<Match> matchList = new ArrayList<>();

    private String uri;
    private String label;
    private String lieuMatch;
    private String dateMatch;
    private Equipe equipeLocal;
    private Equipe equipeVisitant;
    private Arbitre arbitre;

    public Match() {
    }

    public Match(String uri) {
        this.setUri(uri);
    }

    public Match(String uri, String label, String dateMatch, Arbitre arbitre, String lieuMatch) {
        this.setArbitre(arbitre);
        this.setDateMatch(dateMatch);
        this.setLieuMatch(lieuMatch);
        this.setUri(uri);
        this.setLabel(label);
    }

    public Match(String lieuMatch, String dateMatch, Equipe equipeLocal, Equipe equipeVisitant) {

        this.setDateMatch(dateMatch);
        this.setEquipeLocal(equipeLocal);
        this.setEquipeVisitant(equipeVisitant);
        this.setLieuMatch(lieuMatch);

    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setArbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public Arbitre getArbitre() {
        return arbitre;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }

    public void setEquipeLocal(Equipe equipeLocal) {
        this.equipeLocal = equipeLocal;
    }

    public void setEquipeVisitant(Equipe equipeVisitant) {
        this.equipeVisitant = equipeVisitant;
    }

    public void setLieuMatch(String lieuMatch) {
        this.lieuMatch = lieuMatch;
    }

    public String getDateMatch() {
        return dateMatch;
    }

    public Equipe getEquipeLocal() {
        return equipeLocal;
    }

    public Equipe getEquipeVisitant() {
        return equipeVisitant;
    }

    public String getLieuMatch() {
        return lieuMatch;
    }

    public static boolean containsURI(String uri) {

        return Match.matchList.stream().anyMatch(e -> uri.equals(e.getUri()));
    };

    public static Match getElemByURI(String uri) {

        return Match.matchList.stream().filter(a -> uri.equals(a.getUri())).findFirst().orElse(null);

    };

    public static void addElem(Match e) {

        if (!Match.containsURI(e.getUri())) {
            Match.matchList.add(e);
        } else {
            System.out.println("Match déjà ajouté");
        }
    };
}
