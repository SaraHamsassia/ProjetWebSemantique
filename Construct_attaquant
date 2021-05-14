package projet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class Construct_attaquant {
	
    public static final String NL = System.getProperty("line.separator");
/* voir http://downlode.org/Code/RDF/ISO-3166/countries */
/* */
    public static void main(String[] args) {
        Model m = ModelFactory.createDefaultModel();
        String fil_tom = "football.n3";
        m.read(fil_tom);
        
        String prolog1 = "PREFIX rdf: <" + RDF.getURI() + ">";
		String animal_ns = m.getNsPrefixURI("football");
		String prolog2 = "PREFIX football: <"+animal_ns+">" ;
	      String prolog3 = "PREFIX rdfs: <" + RDFS.getURI() + ">";
        // Query string.
        String rdq = prolog1
                + NL
                + prolog2
                + NL
                + prolog3
                + NL
            + "CONSTRUCT WHERE { ?i rdf:type football:Attaquant ." +
                " ?i rdfs:label ?label ."+
                " ?i football:age ?age .}";
        //tout les individus attaquant dont l'age est renseign�

        Query query = QueryFactory.create(rdq);
        QueryExecution qexec = QueryExecutionFactory.create(query, m);
		query.serialize(new IndentedWriter(System.out,true)) ;
        Model results = qexec.execConstruct();
        //System.out.println(results.size());
        results.write(System.out, "N3");
        try {
            FileOutputStream ost = new FileOutputStream("attaquantConstruct.n3");
            results.write(ost, "N3" ); 
        	System.out.println("pas de Antoine Maricial car pas d'�ge");}
            catch (FileNotFoundException e) {
                System.out.println("pb de fichier");
            }
        qexec.close();
    }
}
