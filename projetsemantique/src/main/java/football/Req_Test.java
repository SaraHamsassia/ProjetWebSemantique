package football;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

public class Req_Test {

    static final String football_ns = "football";
    static final String football_uri = "https://www.wikidata.org/wiki/Q2736";

    public static final String NL = System.getProperty("line.separator");

    public static void main(String[] args) {

        Model m = ModelFactory.createDefaultModel();
        String sparqlService = "http://query.wikidata.org/sparql";

        m.setNsPrefix(football_ns, football_uri);
        m.setNsPrefix("rdf", RDF.getURI());
        m.setNsPrefix("rdfs", RDFS.getURI());
        m.setNsPrefix("xsd", XSD.getURI());
        m.setNsPrefix("wd", "http://www.wikidata.org/entity/"); // Entity
        m.setNsPrefix("wdt", "http://www.wikidata.org/prop/direct/"); // Property
        m.setNsPrefix("p", "http://www.wikidata.org/prop/"); // Property
        m.setNsPrefix("pq", "http://www.wikidata.org/prop/qualifier/"); // Property Qualifier
        m.setNsPrefix("ps", "http://www.wikidata.org/prop/statement/"); // Property Statement

        Resource personne = m.createResource(football_uri + "Personne");
        m.add(personne, RDFS.subClassOf, RDFS.Class);

        Resource footballeur = m.createResource(football_uri + "Footballeur");
        m.add(footballeur, RDFS.subClassOf, personne);

        Property nom = m.createProperty(football_uri + "nom");
        Property prenom = m.createProperty(football_uri + "prenom");
        Property dateNaissance = m.createProperty(football_uri + "dateBirth");
        Property nationalite = m.createProperty(football_uri + "nationalite");
        Property poste = m.createProperty(football_uri + "poste");
        Property numeroMaillot = m.createProperty(football_uri + "numeroMaillot");

        Property appartient = m.createProperty(football_uri + "appartient");

        String prolog1 = "PREFIX " + football_ns + ": <" + football_uri + ">";
        String prolog2 = "PREFIX rdf: <" + RDF.getURI() + ">";
        String prolog3 = "PREFIX rdfs: <" + RDFS.getURI() + ">";
        String prolog4 = "PREFIX wd: <" + m.getNsPrefixURI("wd") + ">";
        String prolog5 = "PREFIX wdt: <" + m.getNsPrefixURI("wdt") + ">";
        String prolog6 = "PREFIX p: <" + m.getNsPrefixURI("p") + ">";
        String prolog7 = "PREFIX ps: <" + m.getNsPrefixURI("ps") + ">";
        String prolog8 = "PREFIX pq: <" + m.getNsPrefixURI("pq") + ">";

        // Requetes

        // Competitions

        String uriCoupeMonde2018 = "Q170645";

        String reqCoupeMonde2018 = prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6 + NL + prolog7
                + NL + prolog8
                + "SELECT ?countryLbl ?country ?year ?from ?until ?finalMatch ?compName ?winner WHERE { wd:Q170645 wdt:P17 ?country ;"
                + "rdfs:label ?compName ; wdt:P585 ?year ; wdt:P1346 ?winner ; wdt:P580 ?from ; wdt:P582 ?until ; wdt:P3967 ?finalMatch ."
                + "?country rdfs:label ?countryLbl ; FILTER(lang(?countryLbl) = 'fr') . FILTER(lang(?compName) = 'fr')  }";

        // Equipes

        String equipesWC2018 = prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6 + NL + prolog7 + NL
                + prolog8

                + "SELECT DISTINCT ?equipeNal ?lbl ?coach ?countryLbl ?stadeLbl WHERE { wd:Q170645 wdt:P1923 ?uriWC2018 . "
                + "?uriWC2018 wdt:P1532 ?country . ?equipeNal wdt:P31 wd:Q6979593 ; wdt:P1532 ?country ; wdt:P2094 wd:Q31930761 ; wdt:P286 ?coach ;"
                + "rdfs:label ?lbl . ?country rdfs:label ?countryLbl . FILTER (lang(?lbl) = 'en') . FILTER(lang(?countryLbl)= 'en') . "
                + "OPTIONAL { ?equipeNal wdt:P115 ?stade . ?stade rdfs:label ?stadeLbl . FILTER(lang(?stadeLbl) = 'en') }}";

        // Matchs

        String reqMatchsWC2018 = prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6 + NL + prolog7 + NL
                + prolog8
                + "SELECT ?match ?matchLbl ?stadiumLbl ?date ?referee WHERE { VALUES ?uriCup { wd:Q43214603 wd:Q170645 wd:Q31189405 wd:Q31189406 "
                + "wd:Q31189411 wd:Q31189412 wd:Q31189413 wd:Q43214797 wd:Q43214817 wd:Q43214843 } ?match wdt:P361 ?uriCup ; wdt:P31 wd:Q16466010 ; "
                + "wdt:P276 ?stadium ; wdt:P585 ?date ; wdt:P1652 ?referee ; rdfs:label ?matchLbl . ?stadium rdfs:label ?stadiumLbl ."
                + "FILTER(lang(?matchLbl) = 'en') . FILTER(lang(?stadiumLbl) = 'en') }";

        EquipeNationale.instanceConstructor(equipesWC2018);
        Competition.instaceConstructor(reqCoupeMonde2018, uriCoupeMonde2018);
        Match.instanceConstructor(reqMatchsWC2018, uriCoupeMonde2018);
        System.out.println(EquipeNationale.equipeNalList.size());
        System.out.println(Competition.compList.size());
        System.out.println(Match.matchList.size());

        // Footballeurs

        String reqFootballeursEquipeCroatie = prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6 + NL
                + prolog7 + NL + prolog8
                + "SELECT ?player ?name ?posteLbl ?dob ?nationaliteLbl ?numMaillot WHERE { ?player wdt:P54 wd:Q134479 ;"
                + "wdt:P569 ?dob ; wdt:P27 ?nationalite ; wdt:P413 ?poste ; wdt:P1618 ?numMaillot ; rdfs:label ?name ; p:P54 ?statement . ?statement ps:P54 wd:Q134479;"
                + "FILTER (lang(?name) = 'fr') . ?poste rdfs:label ?posteLbl . FILTER(lang(?posteLbl) = 'fr') ."
                + "?nationalite rdfs:label ?nationaliteLbl . FILTER(lang(?nationaliteLbl) = 'fr') ."
                + "OPTIONAL { ?statement pq:P582 ?until } . FILTER(!( BOUND( ?until) )) } ORDER BY ?name";

        String reqFootballeursEquipeFrance = prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6 + NL
                + prolog7 + NL + prolog8
                + "SELECT ?player ?name ?posteLbl ?dob ?nationaliteLbl ?numMaillot WHERE { ?player wdt:P54 wd:Q47774 ;"
                + "wdt:P569 ?dob ; wdt:P27 ?nationalite ; wdt:P413 ?poste ; wdt:P1618 ?numMaillot ; rdfs:label ?name ; p:P54 ?statement . ?statement ps:P54 wd:Q47774;"
                + "FILTER (lang(?name) = 'fr') . ?poste rdfs:label ?posteLbl . FILTER(lang(?posteLbl) = 'fr') ."
                + "?nationalite rdfs:label ?nationaliteLbl . FILTER(lang(?nationaliteLbl) = 'fr') ."
                + "OPTIONAL { ?statement pq:P582 ?until } . FILTER(!( BOUND( ?until) )) } ORDER BY ?name";

        // Footballeur.instanceConstructor(reqFootballeursEquipeCroatie);
        // Footballeur.instanceConstructor(reqFootballeursEquipeFrance);

        for (EquipeNationale e : EquipeNationale.equipeNalList) {

            System.out.println(e.getUri());
        }

        for (EquipeNationale e : EquipeNationale.equipeNalList) {

            System.out.println("entre aqui");
            String equipeURI = e.getUri();
            System.out.println(equipeURI);

            String req = prolog2 + NL + prolog3 + NL + prolog4 + NL + prolog5 + NL + prolog6 + NL + prolog7 + NL
                    + prolog8
                    + "SELECT ?player ?name ?posteLbl ?dob ?nationaliteLbl ?numMaillot WHERE { ?player wdt:P54 wd:"
                    + equipeURI + " ;"
                    + "wdt:P569 ?dob ; wdt:P27 ?nationalite ; wdt:P413 ?poste ; wdt:P1618 ?numMaillot ; rdfs:label ?name ; p:P54 ?statement . ?statement ps:P54 wd:"
                    + equipeURI + " ; "
                    + "FILTER (lang(?name) = 'fr') . ?poste rdfs:label ?posteLbl . FILTER(lang(?posteLbl) = 'fr') ."
                    + "?nationalite rdfs:label ?nationaliteLbl . FILTER(lang(?nationaliteLbl) = 'fr') ."
                    + "OPTIONAL { ?statement pq:P582 ?until } . FILTER(!( BOUND( ?until) )) } ORDER BY ?name";

            Footballeur.instanceConstructor(req);

            System.out.println(Footballeur.listPlayers.size());
        }

        for (Footballeur f : Footballeur.listPlayers) {

            Resource player = m.createResource(football_uri + f.getUri());
            player.addProperty(RDF.type, footballeur);
            player.addProperty(RDFS.label, f.getLabel());
            player.addProperty(nationalite, m.createLiteral(f.getNationalite(), "fr"));
            player.addProperty(numeroMaillot, m.createTypedLiteral(f.getNumMaillot(), XSD.getURI() + "int"));
        }

        // m.write(System.out, "TURTLE");

        try {
            FileOutputStream outStream = new FileOutputStream("football_test.n3"); //
            m.write(outStream, "N3");
            outStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            System.out.println("IO problem");
        }

        // System.out.println(Footballeur.listPlayers.size());

        // Footballeur.listPlayers.stream().forEach(d ->
        // System.out.println(d.toString()));

    }

}
