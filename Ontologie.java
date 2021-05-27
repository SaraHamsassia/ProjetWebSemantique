package football;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.jena.ontology.AllValuesFromRestriction;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.ontology.SomeValuesFromRestriction;
import org.apache.jena.ontology.SymmetricProperty;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;
public class Ontologie {
		
		static String football = new String(OntologieClass.getUri());
		public static void main(String args[]) {
			OntModel model = ModelFactory.createOntologyModel();
			model.setNsPrefix("football", OntologieClass.getUri());
			//creation de classes

OntClass Personne = model.createClass(OntologieClass.Personne.toString());
OntClass Footballeur = model.createClass( OntologieClass.Footballeur.toString());
OntClass Entraineur = model.createClass( OntologieClass . Entraineur.toString());
OntClass Arbitre = model.createClass(OntologieClass .Arbitre.toString());;
OntClass Equipe = model.createClass(OntologieClass .Equipe.toString());
OntClass EquipeNational =  model.createClass( OntologieClass .EquipeNational.toString());
OntClass EquipeClub = model.createClass(OntologieClass.EquipeClub.toString());
OntClass match = model.createClass(OntologieClass .match.toString());
OntClass matchCompetition =model.createClass(OntologieClass.matchCompetition.toString());
//
// définition des sousclasses et classes Disjoint/ equivalent:
 Footballeur.addSuperClass(Personne);
 Entraineur.addSuperClass(Personne);
 Arbitre.addSuperClass(Personne);
 EquipeNational.addSuperClass(Equipe);
 EquipeClub.addSuperClass(Equipe);
 matchCompetition.addSuperClass(match);
 //
 Footballeur.addDisjointWith(Entraineur);
 Footballeur.addDisjointWith(Arbitre);
 Entraineur.addDisjointWith(Arbitre);
 EquipeNational.addDisjointWith(EquipeClub);

 
 Footballeur.addEquivalentClass(Personne);
 Entraineur.addEquivalentClass(Personne);
 Arbitre.addEquivalentClass(Personne);
 
 EquipeNational.addEquivalentClass(Equipe);
 EquipeClub.addEquivalentClass(Equipe);

//
materialiser(model, Personne);
materialiser(model, Footballeur);
materialiser(model, Equipe);
materialiser(model, EquipeNational);
materialiser(model, EquipeClub);
materialiser(model, Entraineur);
materialiser(model, Arbitre);
materialiser(model, matchCompetition);
materialiser(model, match);
materialiser(model, matchCompetition);



//Ajout d'individus:
OntProperty InteragitAvec= model.createObjectProperty(OntologieClass.InteragitAvec.toString());
Individual HugoLoris = model.getIndividual(OntologieClass.HugoLoris.toString());
Individual DidierDechamps = model.getIndividual(OntologieClass.DidierDechamps.toString());
Individual NestorPitana  = model.getIndividual(OntologieClass.NestorPitana.toString());
HugoLoris.addProperty(InteragitAvec,Footballeur);
HugoLoris.addProperty(InteragitAvec,DidierDechamps);
HugoLoris.addProperty(InteragitAvec,NestorPitana);

OntProperty JoueContre= model.createObjectProperty(OntologieClass.JoueContre.toString());
Individual EquipeDeFrance = model.getIndividual(OntologieClass.EquipeDeFrance.toString());
Individual EquipeDeCroatie = model.getIndividual(OntologieClass.EquipeDeCroatie.toString());
EquipeDeFrance.addProperty(JoueContre,EquipeDeCroatie);

//
model.write(System.out, "N3");
try { FileOutputStream outStream = new FileOutputStream("src/football/footballOWLTest.n3");

model.write(outStream, "N3");
  outStream.close(); } 
  catch (FileNotFoundException e)
  {System.out.println("File not found");} 

catch (IOException e)
  {System.out.println("IO problem");
  } 
}

public static OntModel materialiser (OntModel om, OntClass oc)
{
	Individual Hugoloris = null;Individual NestorPitana = null ; Individual DidierDechamps = null; Individual FinaleDeLaCoupeDuMonde2018 = null; Individual EquipeDeFrance = null;
	Individual EquipeDeCroatie = null;
	if (oc.getLocalName().equals("Footballeur"))
	{ Hugoloris = om.createIndividual(OntologieClass.HugoLoris.toString(),oc);
	Hugoloris.addProperty(RDFS.label, om.createLiteral("HugoLoris","fr"));
	Hugoloris.addProperty(OntologieClass.carton_rouge ,om.createTypedLiteral("0",XSD.getURI() + "integer"));
	Hugoloris.addProperty(OntologieClass.carton_jaune, om.createTypedLiteral("1",XSD.getURI() + "integer"));
	Hugoloris.addProperty(OntologieClass.titulaire, om.createTypedLiteral("oui",XSD.getURI() + "string"));}
	if (oc.getLocalName().equals("Arbitre"))
	{	NestorPitana = om.createIndividual(OntologieClass.NestorPitana.toString(),oc);
	NestorPitana.addProperty(RDFS.label, om.createLiteral("NestorPitana","fr"));
	NestorPitana.addProperty(OntologieClass.Nationalite ,om.createTypedLiteral("Français",XSD.getURI() + "string"));
	}
	if (oc.getLocalName().equals("Entraineur"))
	{	DidierDechamps = om.createIndividual(OntologieClass.DidierDechamps.toString(),oc);
	DidierDechamps.addProperty(RDFS.label, om.createLiteral("Entraineur","fr"));
	DidierDechamps.addProperty(OntologieClass.Age, om.createTypedLiteral("36",XSD.getURI() + "integer"));
	DidierDechamps.addProperty(OntologieClass.Nationalite ,om.createTypedLiteral("Français",XSD.getURI() + "string"));}
	if (oc.getLocalName().equals("EquipeNational"))
	{ EquipeDeFrance = om.createIndividual(OntologieClass.EquipeDeFrance.toString(),oc);
	EquipeDeFrance.addProperty(RDFS.label, om.createLiteral("Equipe_de_France_de_football","fr"));
	EquipeDeFrance.addProperty(OntologieClass.Pays ,om.createTypedLiteral("France",XSD.getURI() + "integer"));
	EquipeDeFrance.addProperty(OntologieClass.Fautes, om.createTypedLiteral("8",XSD.getURI() + "integer"));
	EquipeDeFrance.addProperty(OntologieClass.Score, om.createTypedLiteral("4",XSD.getURI() + "integer"));}
	
	if (oc.getLocalName().equals("EquipeNational"))
	{ EquipeDeCroatie = om.createIndividual(OntologieClass.EquipeDeCroatie.toString(),oc);
	EquipeDeCroatie.addProperty(RDFS.label, om.createLiteral("Equipe_de_Croatie_de_football","fr"));
	EquipeDeCroatie.addProperty(OntologieClass.Pays ,om.createTypedLiteral("Croatie",XSD.getURI() + "String"));
	EquipeDeCroatie.addProperty(OntologieClass.Fautes, om.createTypedLiteral("12",XSD.getURI() + "integer"));
	EquipeDeCroatie.addProperty(OntologieClass.Score, om.createTypedLiteral("2",XSD.getURI() + "integer"));}
	
	if (oc.getLocalName().equals("MatchCompetition"))
	{ FinaleDeLaCoupeDuMonde2018 = om.createIndividual(OntologieClass.FinaleDeLaCoupeDuMonde2018.toString(),oc);
	FinaleDeLaCoupeDuMonde2018.addProperty(RDFS.label, om.createLiteral("FinaleDeLaCoupeDuMonde2018","fr"));
	FinaleDeLaCoupeDuMonde2018.addProperty(OntologieClass.DateDébut,om.createTypedLiteral("15 juillet2018",XSD.getURI() + "String"));
	FinaleDeLaCoupeDuMonde2018.addProperty(OntologieClass.Pays, om.createTypedLiteral("Russie",XSD.getURI() + "String"));
	FinaleDeLaCoupeDuMonde2018.addProperty(OntologieClass.lieuMatch, om.createTypedLiteral("Stade_Lojinki",XSD.getURI() + "String"));}
	return om;}
}