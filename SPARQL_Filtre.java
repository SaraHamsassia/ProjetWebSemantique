package projet;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;


public class SPARQL_Filtre {
	public static final String NL = System.getProperty("line.separator") ;
	public static void main(String[] args)
	{
		Model m = ModelFactory.createDefaultModel();
		String fil_tom = "football.n3";

		m.read(fil_tom);
		String animal_ns = m.getNsPrefixURI("football");
		String prolog1 = "PREFIX football: <"+animal_ns+">" ;
		String prolog2 = "PREFIX rdf: <"+RDF.getURI()+">" ;
		String prolog3 = "PREFIX rdfs: <"+RDFS.getURI()+">" ;

		String rdq = prolog1 + NL + prolog2 + NL  + prolog3 + NL  +
				  "SELECT ?s ?label WHERE { ?s rdf:type football:MilieuDeTerrain. " 
				+"?s rdfs:label ?label ."
					 +  " FILTER REGEX(?label, \"^C\") } " ;
	//renvoyer les milieux de terrain dont la premi�re lettre de leur pr�nom commence par C
		Query query = QueryFactory.create(rdq);
		QueryExecution qexec = QueryExecutionFactory.create(query, m);
		query.serialize(new IndentedWriter(System.out,true)) ;
		System.out.println();
		try {
			ResultSet rs = qexec.execSelect() ;
			ResultSetFormatter.out(System.out, rs, query);
	


		}
		finally {qexec.close();}
	}
}
