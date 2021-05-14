package projet;

import java.util.Iterator;
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

public class SPARQL_Footballeur {
    public static final String NL = System.getProperty("line.separator") ;
	  public static void main(String[] args)
	    {
		  Model m = ModelFactory.createDefaultModel();
		  String fil_tom = "football.n3";
					            
		  m.read(fil_tom);
		  String animal_ns = m.getNsPrefixURI("football");
		  String prolog1 = "PREFIX football: <"+animal_ns+">" ;
		  String prolog2 = "PREFIX rdf: <"+RDF.getURI()+">" ;
		  
		  String rdq = prolog1 + NL + prolog2 + NL  +
		  "SELECT ?s WHERE { ?s rdf:type football:Poste}" ;
		  
		  Query query = QueryFactory.create(rdq);
		  QueryExecution qexec = QueryExecutionFactory.create(query, m);
		  try {
			  Iterator<QuerySolution> results = qexec.execSelect();
			  RDFVisitor aVisitor = new Un_Visiteur();
			  System.out.println("les footballeurs");
			  for (;results.hasNext();)
			  {
				  QuerySolution sol = results.next();
				  RDFNode s = sol.get("s");
				  System.out.println(s.visitWith(aVisitor)+"    ");
			  }
		  }
		  finally {qexec.close();}
	    }
}
