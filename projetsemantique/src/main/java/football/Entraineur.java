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

public class Entraineur extends Personne {

    public static ArrayList<Entraineur> coachList = new ArrayList<>();

    public Entraineur() {
    }

    public Entraineur(String uri) {
        this.setUri(uri);
    }

    public Entraineur(String uri, String label, String dateNaissance, String nationalite) {
        super(uri, label, dateNaissance, nationalite);
    }

    public static boolean containsURI(String uri) {

        return Entraineur.coachList.stream().anyMatch(e -> uri.equals(e.getUri()));
    };

    public static Entraineur getElemByURI(String uri) {

        return Entraineur.coachList.stream().filter(a -> uri.equals(a.getUri())).findFirst().orElse(null);

    };

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getLabel();
    }

    public static void addElem(Entraineur e) {

        if (!Entraineur.containsURI(e.getUri())) {
            Entraineur.coachList.add(e);
        } else {
            System.out.println("Entraineur déjà ajouté");
        }
    };

    public static void instaceConstructor(String req, String uri) {

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
                RDFNode lbl = sol.get("lbl");
                RDFNode dob = sol.get("dob");
                RDFNode nalLbl = sol.get("nalLbl");

                Entraineur ent;

                ent = getElemByURI(uri);
                ent.setDateNaissance(dob.visitWith(aVisitor).toString());
                ent.setNationalite(nalLbl.visitWith(aVisitor).toString());
                ent.setLabel(lbl.visitWith(aVisitor).toString());

            }

            qexec.close();

        } catch (

        Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }
}
