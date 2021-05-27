package football;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jena.ontology.EnumeratedClass;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

public class Football {

	static final String base_url = "https://www.wikidata.org/wiki/Q2736";
	static final String football = base_url + "Q2736";

	public static void main(String[] args) {

		Model m = ModelFactory.createDefaultModel();
		m.setNsPrefix("football", football);
		m.setNsPrefix("rdf", RDF.getURI());
		m.setNsPrefix("rdfs", RDFS.getURI());
		m.setNsPrefix("xsd", XSD.getURI());
		m.setNsPrefix("wd", "http://www.wikidata.org/entity/"); // Entity
		m.setNsPrefix("wdt", "http://www.wikidata.org/prop/direct/"); // Property

		// super-Class
		// Personnage
		Resource personnage = m.createResource(football + "Personnage");
		m.add(personnage, RDFS.subClassOf, RDFS.Class);
		// Match
		Resource match = m.createResource(football + "Match");
		m.add(match, RDFS.subClassOf, RDFS.Class);
		// Poste
		Resource poste = m.createResource(football + "Poste");
		m.add(poste, RDFS.subClassOf, RDFS.Class);
		// Equipe
		Resource equipe = m.createResource(football + "Equipe");
		m.add(equipe, RDFS.subClassOf, RDFS.Class);

		// class
		// Competition
		Resource competition = m.createResource(football + "Competition");
		m.add(competition, RDFS.subClassOf, RDFS.Class);
		// TituOuRemplacant
		Resource tituouremplacant = m.createResource(football + "TituOuRemplacant");
		m.add(tituouremplacant, RDFS.subClassOf, RDFS.Class);

		// Sous-class
		// Footballer
		// EnumeratedClass joueur = m
		Resource footballeur = m.createResource(football + "Footballeur");
		footballeur.addProperty(RDFS.subClassOf, personnage);
		// Arbitre
		Resource arbitre = m.createResource(football + "Arbitre");
		arbitre.addProperty(RDFS.subClassOf, personnage);
		// Entraineur
		Resource entraineur = m.createResource(football + "Entraineur");
		entraineur.addProperty(RDFS.subClassOf, personnage);
		// EquipeClub
		Resource equipeClub = m.createResource(football + "EquipeClub");
		equipeClub.addProperty(RDFS.subClassOf, equipe);
		// EquipeNational
		Resource equipeNational = m.createResource(football + "EquipeNational");
		equipeNational.addProperty(RDFS.subClassOf, equipe);
		// MatchAmical
		Resource matchAmical = m.createResource(football + "MatchAmical");
		matchAmical.addProperty(RDFS.subClassOf, match);
		// MatchCompetition
		Resource matchCompetition = m.createResource(football + "MatchCompetition");
		matchCompetition.addProperty(RDFS.subClassOf, match);
		// MilieuDeTerrain
		Resource milieuDeTerrain = m.createResource(football + "MilieuDeTerrain");
		milieuDeTerrain.addProperty(RDFS.subClassOf, poste);
		// Attaquant
		Resource attaquant = m.createResource(football + "Attaquant");
		attaquant.addProperty(RDFS.subClassOf, poste);
		// Defenseur
		Resource defenseur = m.createResource(football + "Defenseur");
		defenseur.addProperty(RDFS.subClassOf, poste);
		// GardienDeBut
		Resource gardienDeBut = m.createResource(football + "GardienDeBut");
		gardienDeBut.addProperty(RDFS.subClassOf, poste);

		// Propriete(attribut) (pas specifique a une class)
		// personnage
		Property nom = m.createProperty(football + "nom");
		Property prenom = m.createProperty(football + "prenom");
		Property age = m.createProperty(football + "age");
		Property nationalite = m.createProperty(football + "nationalite");
		// Equipe
		Property nomEquipe = m.createProperty(football + "nomEquipe");
		Property pays = m.createProperty(football + "pays");
		// EquipeClub
		Property paysECI = m.createProperty(football + "paysECI");

		// Match
		Property dateMatch = m.createProperty(football + "dateMatch");
		Property lieuMatch = m.createProperty(football + "lieuMatch");
		Property nomAdversaire = m.createProperty(football + "nomAdversaire");

		Property fautes = m.createProperty(football, "fautes");
		Property fautesEquipe = m.createProperty(football, "fauteEquipe");
		fautesEquipe.addProperty(RDFS.subPropertyOf, fautes);
		fautesEquipe.addProperty(RDFS.subPropertyOf, nomEquipe);
		Property fautesAdversaire = m.createProperty(football, "fauteAdversaire");
		fautesAdversaire.addProperty(RDFS.subPropertyOf, fautes);
		fautesAdversaire.addProperty(RDFS.subPropertyOf, nomAdversaire);

		Property nbCartonJaune = m.createProperty(football, "nombreCartonsJaunes");
		Property nbCartonJaune_Adversaire = m.createProperty(football, "nbCartonJaune_Adversaire");
		nbCartonJaune_Adversaire.addProperty(RDFS.subPropertyOf, nbCartonJaune);
		nbCartonJaune_Adversaire.addProperty(RDFS.subPropertyOf, nomAdversaire);
		Property nbCartonJaune_Equipe = m.createProperty(football, "nbCartonJaune_Equipe");
		nbCartonJaune_Equipe.addProperty(RDFS.subPropertyOf, nbCartonJaune);
		nbCartonJaune_Equipe.addProperty(RDFS.subPropertyOf, nomEquipe);

		Property nbCartonRouges = m.createProperty(football, "nombreCartonsRouges");
		Property nbCartonRouges_Adversaire = m.createProperty(football, "nbCartonRouges_Adversaire");
		nbCartonRouges_Adversaire.addProperty(RDFS.subPropertyOf, nbCartonRouges);
		nbCartonRouges_Adversaire.addProperty(RDFS.subPropertyOf, nomAdversaire);
		Property nbCartonRouges_Equipe = m.createProperty(football, "nbCartonRouges_Equipe");
		nbCartonRouges_Equipe.addProperty(RDFS.subPropertyOf, nbCartonRouges);
		nbCartonRouges_Equipe.addProperty(RDFS.subPropertyOf, nomEquipe);

		Property score = m.createProperty(football + "score");
		Property scoreEquipe = m.createProperty(football, "scoreEquipe");
		scoreEquipe.addProperty(RDFS.subPropertyOf, score);
		scoreEquipe.addProperty(RDFS.subPropertyOf, nomEquipe);
		Property scoreAdversaire = m.createProperty(football, "scoreAdversaire");
		scoreAdversaire.addProperty(RDFS.subPropertyOf, score);
		scoreAdversaire.addProperty(RDFS.subPropertyOf, nomAdversaire);

		// Competition
		Property dateDebut = m.createProperty(football + "dateDebut");
		Property dateFin = m.createProperty(football + "dateFin");
		Property nomC = m.createProperty(football + "nomC");
		// Footballer
		Property numeroMaillot = m.createProperty(football + "numeroMaillot");

		// TituOuRemplacant(boolean)
		Property titulaire_Advairse = m.createProperty(football + "titulaire_Adverse");
		titulaire_Advairse.addProperty(RDFS.subPropertyOf, equipe);
		Property titulaire_Equipe = m.createProperty(football + "titulaire_Equipe");
		titulaire_Equipe.addProperty(RDFS.subPropertyOf, nomEquipe);

		// Propriete(role)
		Property appartient = m.createProperty(football + "appartient");
		Property entrainerPar = m.createProperty(football + "entrainerPar");
		Property arbitrerPar = m.createProperty(football + "arbitrerPar");
		Property faitPartie = m.createProperty(football + "faitPartie");

		// Instance:

		// equipe
		Resource equipeDeFrance = m.createResource(football + "EquipeDeFrance");
		equipeDeFrance.addProperty(RDF.type, equipeNational);
		equipeDeFrance.addProperty(RDFS.label, "EquipeDeFrance");
		equipeDeFrance.addProperty(pays, m.createLiteral("France", "fr"));

		// equipeAdverse
		Resource equipeDeCroatie = m.createResource(football + "EquipeDeCroatie");
		equipeDeCroatie.addProperty(RDF.type, equipeNational);
		equipeDeCroatie.addProperty(RDFS.label, "EquipeDeCroatie");
		equipeDeCroatie.addProperty(pays, m.createLiteral("Croatie", "fr"));

		// footballer Equipe
		Resource hugo_Loris = m.createResource(football + "Hugo_Lloris");
		hugo_Loris.addProperty(RDF.type, footballeur);
		hugo_Loris.addProperty(RDFS.label, "Hugo_Loris");
		hugo_Loris.addProperty(prenom, m.createLiteral("Hugo"));
		hugo_Loris.addProperty(nom, m.createLiteral("Lloris"));
		hugo_Loris.addProperty(age, m.createTypedLiteral("34", XSD.getURI() + "int"));
		hugo_Loris.addProperty(nationalite, m.createLiteral("Français", "fr"));
		hugo_Loris.addProperty(numeroMaillot, m.createTypedLiteral("1", XSD.getURI() + "int"));
		hugo_Loris.addProperty(appartient, equipeDeFrance);

		Resource benj_pavard = m.createResource(football + "benjamin_pavard");
		benj_pavard.addProperty(RDF.type, defenseur);
		benj_pavard.addProperty(RDFS.label, "Benhamin Pavard");
		benj_pavard.addProperty(prenom, m.createLiteral("Pavard"));
		benj_pavard.addProperty(nom, m.createLiteral("Benajamin"));
		benj_pavard.addProperty(age, m.createTypedLiteral("24", XSD.getURI() + "int"));
		benj_pavard.addProperty(nationalite, m.createLiteral("Français", "fr"));
		benj_pavard.addProperty(numeroMaillot, m.createTypedLiteral("2", XSD.getURI() + "int"));
		benj_pavard.addProperty(appartient, equipeDeFrance);

		Resource pres_kimpembe = m.createResource(football + "presnel_kimpembe");
		pres_kimpembe.addProperty(RDF.type, defenseur);
		pres_kimpembe.addProperty(RDFS.label, "Presnel Kimpembe");
		pres_kimpembe.addProperty(prenom, m.createLiteral("Kimpembe"));
		pres_kimpembe.addProperty(nom, m.createLiteral("Presnel"));
		pres_kimpembe.addProperty(age, m.createTypedLiteral("25", XSD.getURI() + "int"));
		pres_kimpembe.addProperty(nationalite, m.createLiteral("Français", "fr"));
		pres_kimpembe.addProperty(numeroMaillot, m.createTypedLiteral("3", XSD.getURI() + "int"));
		pres_kimpembe.addProperty(appartient, equipeDeFrance);

		Resource griezmann = m.createResource(football + "Antoine_Griezmann");
		griezmann.addProperty(RDF.type, attaquant);
		griezmann.addProperty(RDFS.label, "Antoine Griezmann");
		griezmann.addProperty(prenom, m.createLiteral("Griezmann"));
		griezmann.addProperty(nom, m.createLiteral("Antoine"));
		griezmann.addProperty(age, m.createTypedLiteral("29", XSD.getURI() + "int"));
		griezmann.addProperty(nationalite, m.createLiteral("Français", "fr"));
		griezmann.addProperty(numeroMaillot, m.createTypedLiteral("7", XSD.getURI() + "int"));
		griezmann.addProperty(appartient, equipeDeFrance);

		Resource mbappe = m.createResource(football + "Miguel_Sansores");
		mbappe.addProperty(RDF.type, attaquant);
		mbappe.addProperty(RDFS.label, "Kylian Mbappé");
		mbappe.addProperty(prenom, m.createLiteral("Mbappé"));
		mbappe.addProperty(nom, m.createLiteral("Kylian"));
		mbappe.addProperty(age, m.createTypedLiteral("22", XSD.getURI() + "int"));
		mbappe.addProperty(nationalite, m.createLiteral("Français", "fr"));
		mbappe.addProperty(numeroMaillot, m.createTypedLiteral("10", XSD.getURI() + "int"));
		mbappe.addProperty(appartient, equipeDeFrance);

		Resource pogba = m.createResource(football + "paul_pogba");
		pogba.addProperty(RDF.type, milieuDeTerrain);
		pogba.addProperty(RDFS.label, "Paul Pogba");
		pogba.addProperty(prenom, m.createLiteral("Pogba"));
		pogba.addProperty(nom, m.createLiteral("Paul"));
		pogba.addProperty(age, m.createTypedLiteral("30", XSD.getURI() + "int"));
		pogba.addProperty(nationalite, m.createLiteral("Français", "fr"));
		pogba.addProperty(numeroMaillot, m.createTypedLiteral("6", XSD.getURI() + "int"));
		pogba.addProperty(appartient, equipeDeFrance);

		Resource varane = m.createResource(football + "raphael_varane");
		varane.addProperty(RDF.type, defenseur);
		varane.addProperty(RDFS.label, "Raphaël Varane");
		varane.addProperty(prenom, m.createLiteral("Varane"));
		varane.addProperty(nom, m.createLiteral("Raphaël"));
		varane.addProperty(age, m.createTypedLiteral("30", XSD.getURI() + "int"));
		varane.addProperty(nationalite, m.createLiteral("Français", "fr"));
		varane.addProperty(numeroMaillot, m.createTypedLiteral("4", XSD.getURI() + "int"));
		varane.addProperty(appartient, equipeDeFrance);

		Resource mandanda = m.createResource(football + "steve_mandanda");
		mandanda.addProperty(RDF.type, gardienDeBut);
		mandanda.addProperty(RDFS.label, "Steve Mandanda");
		mandanda.addProperty(prenom, m.createLiteral("Steve"));
		mandanda.addProperty(nom, m.createLiteral("Mandanda"));
		mandanda.addProperty(age, m.createTypedLiteral("35", XSD.getURI() + "int"));
		mandanda.addProperty(nationalite, m.createLiteral("Français", "fr"));
		mandanda.addProperty(numeroMaillot, m.createTypedLiteral("16", XSD.getURI() + "int"));
		mandanda.addProperty(appartient, equipeDeFrance);

		Resource zouma = m.createResource(football + "kurt_zouma");
		zouma.addProperty(RDF.type, defenseur);
		zouma.addProperty(RDFS.label, "Kurt Zouma");
		zouma.addProperty(prenom, m.createLiteral("Kurt"));
		zouma.addProperty(nom, m.createLiteral("Zouma"));
		zouma.addProperty(age, m.createTypedLiteral("26", XSD.getURI() + "int"));
		zouma.addProperty(nationalite, m.createLiteral("Français", "fr"));
		zouma.addProperty(numeroMaillot, m.createTypedLiteral("13", XSD.getURI() + "int"));
		zouma.addProperty(appartient, equipeDeFrance);

		Resource digne = m.createResource(football + "lucas_digne");
		digne.addProperty(RDF.type, milieuDeTerrain);
		digne.addProperty(RDFS.label, "Lucas Digne");
		digne.addProperty(prenom, m.createLiteral("Lucas"));
		digne.addProperty(nom, m.createLiteral("Digne"));
		digne.addProperty(age, m.createTypedLiteral("27", XSD.getURI() + "int"));
		digne.addProperty(nationalite, m.createLiteral("Français", "fr"));
		digne.addProperty(numeroMaillot, m.createTypedLiteral("19", XSD.getURI() + "int"));
		digne.addProperty(appartient, equipeDeFrance);

		Resource hernandez = m.createResource(football + "lucas_hernandez");
		hernandez.addProperty(RDF.type, milieuDeTerrain);
		hernandez.addProperty(RDFS.label, "Lucas Hernandez");
		hernandez.addProperty(prenom, m.createLiteral("Lucas"));
		hernandez.addProperty(nom, m.createLiteral("Hernadez"));
		hernandez.addProperty(age, m.createTypedLiteral("25", XSD.getURI() + "int"));
		hernandez.addProperty(nationalite, m.createLiteral("Français", "fr"));
		hernandez.addProperty(numeroMaillot, m.createTypedLiteral("21", XSD.getURI() + "int"));
		hernandez.addProperty(appartient, equipeDeFrance);

		Resource sissoko = m.createResource(football + "Moussa_Sissoko");
		sissoko.addProperty(RDF.type, defenseur);
		sissoko.addProperty(RDFS.label, "Moussa Sissoko");
		sissoko.addProperty(prenom, m.createLiteral("Moussa"));
		sissoko.addProperty(nom, m.createLiteral("Issoko"));
		sissoko.addProperty(age, m.createTypedLiteral("31", XSD.getURI() + "int"));
		sissoko.addProperty(nationalite, m.createLiteral("Français", "fr"));
		sissoko.addProperty(numeroMaillot, m.createTypedLiteral("17", XSD.getURI() + "int"));
		sissoko.addProperty(appartient, equipeDeFrance);

		Resource kante = m.createResource(football + "ngolo_kante");
		kante.addProperty(RDF.type, milieuDeTerrain);
		kante.addProperty(RDFS.label, "N'golo Kanté");
		kante.addProperty(prenom, m.createLiteral("N'golo"));
		kante.addProperty(nom, m.createLiteral("Kanté"));
		kante.addProperty(age, m.createTypedLiteral("29", XSD.getURI() + "int"));
		kante.addProperty(nationalite, m.createLiteral("Français", "fr"));
		kante.addProperty(numeroMaillot, m.createTypedLiteral("4", XSD.getURI() + "int"));
		kante.addProperty(appartient, equipeDeFrance);

		Resource tolisso = m.createResource(football + "corentin_tolisso");
		tolisso.addProperty(RDF.type, milieuDeTerrain);
		tolisso.addProperty(RDFS.label, "Corentin Tolisso");
		tolisso.addProperty(prenom, m.createLiteral("Corentin"));
		tolisso.addProperty(nom, m.createLiteral("Tolisso"));
		tolisso.addProperty(age, m.createTypedLiteral("26", XSD.getURI() + "int"));
		tolisso.addProperty(nationalite, m.createLiteral("Français", "fr"));
		tolisso.addProperty(numeroMaillot, m.createTypedLiteral("12", XSD.getURI() + "int"));
		tolisso.addProperty(appartient, equipeDeFrance);

		Resource nzonzi = m.createResource(football + "steven_nzonzi");
		nzonzi.addProperty(RDF.type, milieuDeTerrain);
		nzonzi.addProperty(RDFS.label, "Steven Nzonzi");
		nzonzi.addProperty(prenom, m.createLiteral("Steven"));
		nzonzi.addProperty(nom, m.createLiteral("Nzonzi"));
		nzonzi.addProperty(age, m.createTypedLiteral("32", XSD.getURI() + "int"));
		nzonzi.addProperty(nationalite, m.createLiteral("Français", "fr"));
		nzonzi.addProperty(numeroMaillot, m.createTypedLiteral("15", XSD.getURI() + "int"));
		nzonzi.addProperty(appartient, equipeDeFrance);

		Resource rabiot = m.createResource(football + "adrien_rabiot");
		rabiot.addProperty(RDF.type, milieuDeTerrain);
		rabiot.addProperty(RDFS.label, "Adrien Rabiot");
		rabiot.addProperty(prenom, m.createLiteral("Adrien"));
		rabiot.addProperty(nom, m.createLiteral("Rabiot"));
		rabiot.addProperty(age, m.createTypedLiteral("25", XSD.getURI() + "int"));
		rabiot.addProperty(nationalite, m.createLiteral("Français", "fr"));
		rabiot.addProperty(numeroMaillot, m.createTypedLiteral("14", XSD.getURI() + "int"));
		rabiot.addProperty(appartient, equipeDeFrance);

		Resource coman = m.createResource(football + "kingsley_coman");
		coman.addProperty(RDF.type, attaquant);
		coman.addProperty(RDFS.label, "Kingsley Coman");
		coman.addProperty(prenom, m.createLiteral("Kingsley"));
		coman.addProperty(nom, m.createLiteral("Coman"));
		coman.addProperty(age, m.createTypedLiteral("24", XSD.getURI() + "int"));
		coman.addProperty(nationalite, m.createLiteral("Français", "fr"));
		coman.addProperty(numeroMaillot, m.createTypedLiteral("11", XSD.getURI() + "int"));
		coman.addProperty(appartient, equipeDeFrance);

		Resource martial = m.createResource(football + "anthony_martial");
		martial.addProperty(RDF.type, attaquant);
		martial.addProperty(RDFS.label, "Anthony Martial");
		martial.addProperty(prenom, m.createLiteral("Anthony"));
		martial.addProperty(nom, m.createLiteral("Martial"));
		// pas d'age volontairement pour tester une requete
		martial.addProperty(nationalite, m.createLiteral("Français", "fr"));
		martial.addProperty(numeroMaillot, m.createTypedLiteral("18", XSD.getURI() + "int"));
		martial.addProperty(appartient, equipeDeFrance);

		Resource thuram = m.createResource(football + "marcus_thuram");
		thuram.addProperty(RDF.type, attaquant);
		thuram.addProperty(RDFS.label, "Marcus Thuram");
		thuram.addProperty(prenom, m.createLiteral("Marcus"));
		thuram.addProperty(nom, m.createLiteral("Thuram"));
		thuram.addProperty(age, m.createTypedLiteral("23", XSD.getURI() + "int"));
		thuram.addProperty(nationalite, m.createLiteral("Français", "fr"));
		thuram.addProperty(numeroMaillot, m.createTypedLiteral("23", XSD.getURI() + "int"));
		thuram.addProperty(appartient, equipeDeFrance);

		Resource giroud = m.createResource(football + "olivier_giroud");
		giroud.addProperty(RDF.type, attaquant);
		giroud.addProperty(RDFS.label, "Olivier Giroud");
		giroud.addProperty(prenom, m.createLiteral("Olivier"));
		giroud.addProperty(nom, m.createLiteral("Giroud"));
		giroud.addProperty(age, m.createTypedLiteral("34", XSD.getURI() + "int"));
		giroud.addProperty(nationalite, m.createLiteral("Français", "fr"));
		giroud.addProperty(numeroMaillot, m.createTypedLiteral("9", XSD.getURI() + "int"));
		giroud.addProperty(appartient, equipeDeFrance);

		// footballer adverse
		Resource danijel_Subasic = m.createResource(football + "Danijel_Subasic");
		danijel_Subasic.addProperty(RDF.type, gardienDeBut);
		danijel_Subasic.addProperty(RDFS.label, "Didier_Deschamps");
		equipeDeCroatie.addProperty(appartient, equipeDeCroatie);

		Resource dejan_Lovren = m.createResource(football + "dejan_lovren");
		dejan_Lovren.addProperty(RDF.type, defenseur);
		dejan_Lovren.addProperty(RDFS.label, "Dejan Lovren");
		equipeDeCroatie.addProperty(appartient, equipeDeCroatie);

		// entraineur

		Resource didier_Deschamps = m.createResource(football + "Didier_Deschamps");
		didier_Deschamps.addProperty(RDF.type, entraineur);
		didier_Deschamps.addProperty(RDFS.label, "Didier_Deschamps");
		equipeDeFrance.addProperty(entrainerPar, didier_Deschamps);

		// arbitre

		Resource pierluigi_Collina = m.createResource(football + "Pierluigi_Collina");
		pierluigi_Collina.addProperty(RDF.type, arbitre);
		pierluigi_Collina.addProperty(RDFS.label, "Pierluigi_Collina");

		// match

		Resource finalCoupeDuMonde2018 = m.createResource(football + "FinalCoupeDuMonde2018");
		finalCoupeDuMonde2018.addProperty(RDF.type, matchCompetition);
		finalCoupeDuMonde2018.addProperty(RDFS.label, "FinalCoupeDuMonde2018");
		finalCoupeDuMonde2018.addProperty(arbitrerPar, pierluigi_Collina);
		finalCoupeDuMonde2018.addProperty(dateMatch, m.createTypedLiteral("2018-07-15", XSD.getURI() + "date"));
		finalCoupeDuMonde2018.addProperty(lieuMatch, m.createLiteral("Moscou", "fr"));

		finalCoupeDuMonde2018.addProperty(nomAdversaire, equipeDeCroatie);
		finalCoupeDuMonde2018.addProperty(nomEquipe, equipeDeFrance);

		finalCoupeDuMonde2018.addProperty(scoreEquipe, m.createTypedLiteral("4", XSD.getURI() + "int"));
		finalCoupeDuMonde2018.addProperty(scoreAdversaire, m.createTypedLiteral("2", XSD.getURI() + "int"));

		finalCoupeDuMonde2018.addProperty(fautesEquipe, m.createTypedLiteral("14", XSD.getURI() + "int"));
		finalCoupeDuMonde2018.addProperty(fautesAdversaire, m.createTypedLiteral("13", XSD.getURI() + "int"));

		finalCoupeDuMonde2018.addProperty(nbCartonRouges_Adversaire, m.createTypedLiteral("0", XSD.getURI() + "int"));
		finalCoupeDuMonde2018.addProperty(nbCartonRouges_Equipe, m.createTypedLiteral("0", XSD.getURI() + "int"));

		finalCoupeDuMonde2018.addProperty(nbCartonJaune_Adversaire, m.createTypedLiteral("1", XSD.getURI() + "int"));
		finalCoupeDuMonde2018.addProperty(nbCartonJaune_Equipe, m.createTypedLiteral("2", XSD.getURI() + "int"));

		finalCoupeDuMonde2018.addProperty(titulaire_Equipe, giroud);
		finalCoupeDuMonde2018.addProperty(titulaire_Equipe, hugo_Loris);

		finalCoupeDuMonde2018.addProperty(titulaire_Advairse, danijel_Subasic);
		finalCoupeDuMonde2018.addProperty(titulaire_Advairse, dejan_Lovren);

		// competition

		Resource coupeDuMonde2018 = m.createResource(football + "CoupeDuMonde2018");
		coupeDuMonde2018.addProperty(RDF.type, competition);
		coupeDuMonde2018.addProperty(RDFS.label, "CoupeDuMonde2018");
		finalCoupeDuMonde2018.addProperty(faitPartie, coupeDuMonde2018);
		coupeDuMonde2018.addProperty(dateDebut, m.createTypedLiteral("2018-06-14", XSD.getURI() + "date"));
		coupeDuMonde2018.addProperty(dateFin, m.createTypedLiteral("2018-07-15", XSD.getURI() + "date"));
		coupeDuMonde2018.addProperty(nomC, m.createLiteral("CoupeDuMondeRussie2018", "fr"));

		// affichage des triplets
		// N3 (ou TURTLE), N-TRIPLE, RDF/XML, JSON-LD
		m.write(System.out, "TURTLE");

		try {
			FileOutputStream outStream = new FileOutputStream("football.n3"); //
			m.write(outStream, "N3");
			outStream.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");

		} catch (IOException e) {
			System.out.println("IO problem");
		}

	}

}
