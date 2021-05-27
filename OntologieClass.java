package football;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.shared.PrefixMapping;

public class OntologieClass {
	
    // define models
static final String football_ns = "http://www.ex.fr/football#";
private static Model core_model = ModelFactory.createDefaultModel();

public static String getUri()
{return football_ns;}


static {
      
        core_model.setNsPrefixes(PrefixMapping.Standard);
        core_model.setNsPrefix("football", football_ns);
    }

public static final Resource
// entities
Personne =  core_model.createResource( football_ns+ "Personne"),
Footballeur = core_model.createResource( football_ns+ "Footballeur"),
Entraineur =  core_model.createResource( football_ns +"Entraineur"),
Arbitre =  core_model.createResource(football_ns + "Arbitre"),
Equipe =  core_model.createResource(football_ns +"Equipe"),
EquipeNational =   core_model.createResource( football_ns +"EquipeNational"),
EquipeClub = core_model.createResource(football_ns + "EquipeClub"),
match =  core_model.createResource(football_ns +"match"),
matchCompetition = core_model.createResource(football_ns+ "matchCompetition"),
Competition =  core_model.createResource(football_ns+"Competition"),
HugoLoris =  core_model.createResource(football_ns+"HugoLoris"),
NestorPitana =core_model.createResource(football_ns+"NestorPitana"),
DidierDechamps =  core_model.createResource(football_ns+"DidierDechamps"),
FinaleDeLaCoupeDuMonde2018 =  core_model.createResource(football_ns+"FinaleDeLaCoupeDuMonde2018"),
EquipeDeFrance =  core_model.createResource(football_ns+"EquipeDeFrance"),
EquipeDeCroatie =  core_model.createResource(football_ns+"EquipeDeCroatie");
public static final Property

// properties
Nom = core_model.createProperty(football_ns, "Nom"),
Prenom= core_model.createProperty(football_ns, "Prenom"),
Nationalite = core_model.createProperty(football_ns, "Nationalite"),
Age = core_model.createProperty(football_ns, "Age"),
NumeroMaillot = core_model.createProperty(football_ns, "NumeroMaillot"),
NumeroEquipe = core_model.createProperty(football_ns, "NumeroEquipe"),
Pays = core_model.createProperty(football_ns, "Pays"),
DateMatch = core_model.createProperty(football_ns, "DateMatch"),
lieuMatch = core_model.createProperty(football_ns, "lieuMatch "),
NomAdversaire = core_model.createProperty(football_ns, "NomAdversaire"),
DateDébut = core_model.createProperty(football_ns, "DateDébut "),
DateFin = core_model.createProperty(football_ns, "DateFin"),
NomC= core_model.createProperty(football_ns, "NomC"), 
Score= core_model.createProperty(football_ns, "Score"),
Fautes = core_model.createProperty(football_ns, "Fautes"),
carton_rouge=core_model.createProperty(football_ns, "carton_rouge"),
carton_jaune=core_model.createProperty(football_ns, "carton_jaune"),
titulaire =core_model.createProperty(football_ns, "titulaire"),


InteragitAvec = core_model.createProperty(football_ns, "InteragitAvec") ,
JoueContre= core_model.createProperty(football_ns, "JoueContre") ;

}