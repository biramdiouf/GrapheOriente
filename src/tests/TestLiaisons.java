package tests;

import static org.junit.Assert.*;

import java.util.Date;

import liaisons.Aeroport;
import liaisons.Vol;

import org.junit.Before;
import org.junit.Test;

import tda.Liste;
import tp2.GrapheDynamique;
import util.ListeChaine;

public class TestLiaisons {
	
	private GrapheDynamique<Aeroport, Vol> liaisons;
	private Aeroport montreal;
	private Aeroport quebec;
	private Aeroport toronto;
	private Aeroport winnipeg;
	private Aeroport calgary;
	private Aeroport vancouver;
	private Liste<Aeroport> aeroports;
	private Vol vol0;
	private Vol vol1;
	private Vol vol2;

	@Before
	public void setUp() throws Exception {
		montreal = new Aeroport("YUL","Trudeau","Montreal");
		quebec = new Aeroport("YQB","Jean-Lesage","Quebec");
		toronto = new Aeroport("YYZ","Pearson","Toronto");
		winnipeg = new Aeroport("YWG","james Armstrong Richardson","Winnipeg");
		calgary = new Aeroport("YYC","Calgary International Airport","Calgary");
		vancouver = new Aeroport("YVR","Vancouver International Airport","Vancouver");
		aeroports = new ListeChaine<Aeroport>();
		aeroports.insererFin(montreal);
		aeroports.insererFin(quebec);
		aeroports.insererFin(toronto);
		aeroports.insererFin(winnipeg);
		aeroports.insererFin(calgary);
		aeroports.insererFin(vancouver);
		liaisons = new GrapheDynamique<Aeroport, Vol>(aeroports);
		vol0 = new Vol("v0","c1",new Date(), new Date());
		vol1 = new Vol("v1","c1",new Date(), new Date());
		vol2 = new Vol("v2","c2",new Date(), new Date());
	}

	@Test
	public void testLiaisons() {
		assertTrue((new GrapheDynamique<Aeroport, Vol>()).estVide());
	}

	@Test
	public void testLiaisonsListeAeroport() {
		assertFalse(liaisons.estVide());
		assertTrue(liaisons.estNonConnecte());
	}

	@Test
	public void testSontAdjacents() {
		liaisons.ajouter(montreal, quebec, vol0);
		assertFalse(liaisons.estNonConnecte());
		assertTrue(liaisons.sontAdjacents(montreal, quebec));
		assertFalse(liaisons.sontAdjacents(quebec, montreal));
	}

	@Test
	public void testLesVoisins() {
		liaisons.ajouter(montreal, quebec, vol0);
		liaisons.ajouter(montreal, toronto, vol1);
		Liste<Aeroport> v = liaisons.lesVoisins(montreal);
		assertEquals(2, v.longueur());
		assertTrue(v.contient(quebec));
		assertTrue(v.contient(toronto));
		assertFalse(v.contient(winnipeg));
		assertFalse(v.contient(calgary));
		assertFalse(v.contient(vancouver));
	}

	@Test
	public void testLesSommets() {
		assertTrue(aeroports.contientTous((new GrapheDynamique<Aeroport, Vol>(aeroports)).lesSommets().lesValeurs()));
		assertTrue((new GrapheDynamique<Aeroport, Vol>(aeroports)).lesSommets().contientTous(aeroports.lesValeurs()));
	}

	@Test
	public void testAjouterTTV() {
		liaisons.ajouter(vancouver, toronto, vol2);
		assertFalse(liaisons.estNonConnecte());
		assertEquals(vol2, liaisons.laValeur(vancouver, toronto));
	}

	@Test
	public void testSupprimerTT() {
		liaisons.ajouter(vancouver, toronto, vol2);
		liaisons.supprimer(vancouver, toronto);
		assertTrue(liaisons.estNonConnecte());
	}

	@Test
	public void testSupprimerT() {
		liaisons.supprimer(vancouver);
		liaisons.supprimer(winnipeg);
		liaisons.supprimer(calgary);
		liaisons.supprimer(quebec);
		liaisons.supprimer(toronto);
		assertFalse(liaisons.estVide());
		liaisons.supprimer(montreal);
		assertTrue(liaisons.estVide());
	}

	@Test
	public void testAjouterT() {
		Aeroport bidon = new Aeroport("ZZZ","Bidon","UneVille");
		liaisons.ajouter(bidon);
		assertEquals(7, liaisons.lesSommets().longueur());
		liaisons.ajouter(bidon, bidon, new Vol("","",null,null));
		assertEquals(bidon, liaisons.lesVoisins(bidon).elementDebut());
	}

	@Test
	public void testLaValeur() {
		Aeroport bidon = new Aeroport("ZZZ","Bidon","UneVille");
		liaisons.ajouter(bidon);
		Vol v = new Vol("","",null,null);
		liaisons.ajouter(bidon, bidon, v);
		liaisons.ajouter(vancouver, toronto, vol2);
		liaisons.ajouter(montreal, quebec, vol0);
		liaisons.ajouter(montreal, toronto, vol1);
		assertEquals(vol2, liaisons.laValeur(vancouver, toronto));
		assertEquals(vol1, liaisons.laValeur(montreal, toronto));
		assertEquals(vol0, liaisons.laValeur(montreal, quebec));
		assertEquals(v, liaisons.laValeur(bidon, bidon));
		assertNull(liaisons.laValeur(montreal, winnipeg));
	}

}
