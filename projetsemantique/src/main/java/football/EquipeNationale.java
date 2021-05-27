package football;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;

public class EquipeNationale extends Equipe {

    public static ArrayList<EquipeNationale> equipeNalList = new ArrayList<>();

    public EquipeNationale() {
    }

    public EquipeNationale(String uri, String nomEquipe, String pays, String stade, Entraineur coach) {
        super(uri, nomEquipe, pays, stade, coach);

    }

    public static boolean containsURI(String uri) {

        boolean test = EquipeNationale.equipeNalList.stream().anyMatch(e -> uri.equals(e.getUri()));
        System.out.println(test);
        return test;

    };

    public static EquipeNationale getElemByURI(String uri) {

        return EquipeNationale.equipeNalList.stream().filter(a -> uri.equals(a.getUri())).findFirst().orElse(null);

    };

    public static void addElem(EquipeNationale e) {

        if (!EquipeNationale.containsURI(e.getUri())) {
            EquipeNationale.equipeNalList.add(e);
        } else {
            System.out.println("Equipe déjà ajouté");
        }
    };

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getNomEquipe();
    }

    public static void instanceConstructor(String req) {

        String sparqlService = "http://query.wikidata.org/sparql";

        Query query = QueryFactory.create(req);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlService, query);
        query.serialize(new IndentedWriter(System.out, true));
        System.out.println();

        try {
            // ResultSet rs = qexec.execSelect();
            // ResultSetFormatter.out(System.out, rs, query);
            Iterator<QuerySolution> result = qexec.execSelect();

            System.out.println(result.toString());

            RDFVisitor aVisitor = new Un_Visiteur();
            int count = 1;

            for (; result.hasNext();) {

                System.out.println(count);

                try {

                    QuerySolution sol = result.next();
                    RDFNode uri = sol.get("equipeNal");
                    RDFNode label = sol.get("lbl");
                    RDFNode stade = sol.get("stadeLbl");
                    RDFNode pays = sol.get("countryLbl");
                    RDFNode coach = sol.get("coach");
                    Entraineur entraineur;
                    EquipeNationale equipe;

                    if (!Entraineur.containsURI(coach.visitWith(aVisitor).toString())) {

                        entraineur = new Entraineur(coach.visitWith(aVisitor).toString());
                        Entraineur.addElem(entraineur);

                    } else {
                        entraineur = Entraineur.getElemByURI(coach.visitWith(aVisitor).toString());
                    }

                    if (!EquipeNationale.containsURI(uri.visitWith(aVisitor).toString())) {

                        if (stade == null) {
                            equipe = new EquipeNationale(uri.visitWith(aVisitor).toString(),
                                    label.visitWith(aVisitor).toString(), pays.visitWith(aVisitor).toString(), "N/A",
                                    entraineur);

                        } else {
                            equipe = new EquipeNationale(uri.visitWith(aVisitor).toString(),
                                    label.visitWith(aVisitor).toString(), pays.visitWith(aVisitor).toString(),
                                    stade.visitWith(aVisitor).toString(), entraineur);

                        }

                        EquipeNationale.addElem(equipe);
                    }

                } catch (Exception e) {
                    System.out.println("Error numero " + count + " " + e);
                }

                count++;

            }

            System.out.println(qexec.isClosed());
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
