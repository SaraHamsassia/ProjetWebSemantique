package projet;

import java.util.Iterator;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;



public class SPARQL_Attaquants {

    public static final String NL = System.getProperty("line.separator");

    public static void main(String[] args) {

        Model m = ModelFactory.createDefaultModel();
        m.read("football.n3");

        String football_ns = m.getNsPrefixURI("football");
        String prolog1 = "PREFIX football: <" + football_ns + ">";
        String prolog2 = "PREFIX rdf: <" + RDF.getURI() + ">";
        String prolog3 = "PREFIX rdfs: <" + RDFS.getURI() + ">";

        String rdq = prolog1 + NL + prolog2 + NL + prolog3
                + "SELECT ?s ?name WHERE { ?s rdf:type football:Attaquant ; rdfs:label ?name }";
        //Renvoyer tout les attaquants
        Query query = QueryFactory.create(rdq);

        QueryExecution qexec = QueryExecutionFactory.create(query, m);
        query.serialize(new IndentedWriter(System.out, true));
        System.out.println();
        try {

            Iterator<QuerySolution> result = qexec.execSelect();
            RDFVisitor aVisitor = new Un_Visiteur();
            System.out.println("Attaquants:");
            System.out.println("----------");

            for (; result.hasNext();) {

                QuerySolution sol = result.next();
                //RDFNode s = sol.get("s");
                RDFNode name = sol.get("name");
                //System.out.println(s);
                System.out.println(name.visitWith(aVisitor) + " ");
            }

        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }

        finally {
            qexec.close();
        }

    }

}
