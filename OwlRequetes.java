package football;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;


public class OwlRequetes

{
	public static final String NL = System.getProperty("line.separator");
	static Model m = ModelFactory.createDefaultModel();
	
	//methode display qui affiche la requete+resultat 
	public static void display (String rdq)
	{
				Query query = QueryFactory.create(rdq);
				QueryExecution qexec = QueryExecutionFactory.create(query, m);
				try {
					ResultSet rs = qexec.execSelect();
					System.out.println("Resultat : \n");
					ResultSetFormatter.out(System.out, rs, query);
					System.out.println("\n");
					System.out.println("같같같같같같같같같같같같같같같");	
					
				} catch(Exception e){ 
					System.out.println("Something went wrong.");
				} finally{
					qexec.close();
				}
	}
	
	public static void main(String[] args) {
		
		String fil_football = "src/football/footballOWLTest.n3";
	    m.read(fil_football);
	    String football_ns = "http://www.ex.fr/football#";
	    String prolog = "PREFIX football: <"+football_ns+">" ;
		String prolog2 = "PREFIX rdfs: <" + RDFS.getURI() + ">";
		String prolog1 = "PREFIX schema: <http://schema.org/>";
		String prolog3 = "PREFIX rdf: <"+RDF.getURI()+">" ;
		String prolog4 = "PREFIX owl: <"+RDF.getURI()+">" ;
		
		String requete1 = prolog + NL +prolog1 + NL + prolog2 + NL + prolog3 + NL + prolog4 + NL  
				
		 	+"SELECT ?s ?name WHERE { ?s rdf:type football:Arbitre ; rdfs:label ?name }";
		
		
		




		
		Query query = QueryFactory.create(requete1);
		QueryExecution qexec = QueryExecutionFactory.create(query, m);
		query.serialize(new IndentedWriter(System.out,true));
		System.out.println();
		try {

			ResultSet rs = qexec.execSelect();
			ResultSetFormatter.out(System.out, rs, query);
		} finally {
			qexec.close();
		
			
	}
}
}
