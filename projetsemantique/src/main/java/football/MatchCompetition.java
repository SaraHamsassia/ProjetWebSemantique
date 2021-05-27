package football;

import java.util.Iterator;
import java.util.regex.Matcher;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;

public class MatchCompetition extends Match {

    private Competition competition;

    public MatchCompetition() {
    }

    public MatchCompetition(String uri, String label, String dateMatch, Competition competition, Arbitre arbitre,
            String lieuMatch) {

        super(uri, label, dateMatch, arbitre, lieuMatch);
        this.setCompetition(competition);

    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Competition getCompetition() {
        return competition;
    }

    public static void instanceConstructor(String req, String compURI) {

        String sparqlService = "http://query.wikidata.org/sparql";

        Query query = QueryFactory.create(req);
        QueryExecution qexec = QueryExecutionFactory.sparqlService(sparqlService, query);
        query.serialize(new IndentedWriter(System.out, true));
        System.out.println();

        try {
            // ResultSet rs = qexec.execSelect();
            // ResultSetFormatter.out(System.out, rs, query);
            Iterator<QuerySolution> result = qexec.execSelect();

            RDFVisitor aVisitor = new Un_Visiteur();

            for (; result.hasNext();) {

                QuerySolution sol = result.next();
                RDFNode uri = sol.get("match");
                RDFNode label = sol.get("matchLbl");
                RDFNode arbitre = sol.get("referee");
                RDFNode stade = sol.get("stadiumLbl");
                RDFNode date = sol.get("date");
                Competition comp = Competition.getElemByURI(compURI);
                Arbitre referee;

                if (Arbitre.containsURI(arbitre.visitWith(aVisitor).toString())) {

                    referee = Arbitre.getElemByURI(arbitre.visitWith(aVisitor).toString());

                } else {

                    referee = new Arbitre(arbitre.visitWith(aVisitor).toString());
                    Arbitre.addReferee(referee);

                }

                MatchCompetition match = new MatchCompetition(uri.visitWith(aVisitor).toString(),
                        date.visitWith(aVisitor).toString(), label.visitWith(aVisitor).toString(), comp, referee,
                        stade.visitWith(aVisitor).toString());

                Match.addElem(match);

            }

            qexec.close();

        } catch (

        Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

}
