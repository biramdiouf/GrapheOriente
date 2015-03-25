/**
 * 
 */
package tp2;


import tda.Graphe;
import tda.Liste;
import util.Couple;
import util.ListeChaine;
/**
 * Classe GrapheOriente
 * 
 * ****************   INF2120
 * ****************   Biram DIOUF
 * ****************   CODE PERMANENT : DIOB06127605
 * ****************   diouf.biram@courrier.uqam.ca
 * ****************   TP2 sur les Graphes orientés
 */
public class GrapheDynamique<T, V> implements Graphe<T, V> {
	
	private ListeChaine<ListeChaine<Couple<T, V>>> adjacence;
	
	public GrapheDynamique() {
		super();
		adjacence = new ListeChaine<ListeChaine<Couple<T, V>>>();
	}
 
	public GrapheDynamique(Liste<T> s) {
		this();
		for (int i = 0; i < s.longueur(); i++) {
			adjacence.insererPosition(new ListeChaine<Couple<T, V>>(), i);
			adjacence.elementPosition(i).insererDebut(new Couple<T,V>(s.elementPosition(i), null) );
			for (int j = 1; j < s.longueur(); j++) {
				adjacence.elementPosition(i).insererFin(new Couple<T,V>(s.elementPosition(j), null));
			}
		}
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#sontAdjacents(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean sontAdjacents(T x, T y) {
		
		for (ListeChaine<Couple<T, V>> listCh1 : adjacence) {
			if(listCh1.elementDebut().x().equals(x)){
				for (Couple<T, V> listCh2 : listCh1) {
					if (listCh2.x().equals(y)) return true;
				}
			}
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#lesVoisins(java.lang.Object)
	 */
	@Override
	public Liste<T> lesVoisins(T x) {
		
		Liste<T> voisins = new ListeChaine<T>();
		
		for (ListeChaine<Couple<T, V>> listCh1 : adjacence) {
			if(listCh1.elementDebut().x().equals(x)){
				for (Couple<T, V> listCh2 : listCh1) {
					if (listCh2.y() != null ) 
						voisins.insererFin(listCh2.x());
				}
			}else{
				for (Couple<T, V> listCh2 : listCh1) {
					if (listCh2.x().equals(x) && listCh2.y() != null   ) 
						voisins.insererFin(listCh1.elementDebut().x());
				}
			}
		}
		
		return voisins;
	}
	
	/* (non-Javadoc)
	 * @see tda.Graphe#lesSommets()
	 */
	@Override
	public Liste<T> lesSommets() {
		Liste<T> liste = new ListeChaine<T>();
		
		for (ListeChaine<Couple<T, V>> listCh1 : adjacence) {
			liste.insererFin(listCh1.elementDebut().x());
		}
		return liste;
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#ajouter(java.lang.Object)
	 */
	@Override
	public void ajouter(T x) {
		adjacence.insererFin(new ListeChaine<Couple<T, V>>());
		adjacence.elementFin().insererDebut(new Couple<T, V>(x, null));
		for (int i = 1; i < adjacence.longueur(); i++) {
			adjacence.elementFin().insererFin(new Couple<T,V>(adjacence.elementPosition(i).elementDebut().x(), null));
		}
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#laValeur(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V laValeur(T x, T y) {
		for (ListeChaine<Couple<T, V>> listCh1 : adjacence) {
			if(listCh1.elementDebut().x().equals(x)){
				for (Couple<T, V> listCh2 : listCh1) {
					if (listCh2.x().equals(y)){
						return listCh2.y();
					}
					
				}
			}
		}		
		return null;
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#ajouter(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void ajouter(T x, T y, V v) {
		boolean trouveX = false;
		boolean trouveY = false;
		for (int i = 0; i < adjacence.longueur() && !trouveX; i++) {
			if(adjacence.elementPosition(i).elementDebut().x().equals(x)){
				trouveX = true;
				for ( int j = 0; j < adjacence.elementPosition(i).longueur() && !trouveY; j++) {
					if (adjacence.elementPosition(i).elementPosition(j).x().equals(y)) {
						trouveY = true;
						adjacence.elementPosition(i).retirerPosition(j);
						adjacence.elementPosition(i).insererPosition(new Couple<T, V>(y, v), j);
					}
				}
			}
		}		
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#supprimer(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void supprimer(T x, T y) {
		ajouter(x, y, null);
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#supprimer(java.lang.Object)
	 */
	@Override
	public void supprimer(T x) {
		boolean trouve = false;
		for (int i = 0; i < adjacence.longueur(); i++) {
			if(adjacence.elementPosition(i).elementDebut().x().equals(x)){
				adjacence.retirerPosition(i);
			}else{
				for(int j = 0; j < adjacence.elementPosition(i).longueur() && !trouve; j++){
					if(adjacence.elementPosition(i).elementPosition(j).x().equals(x)){
						trouve = true;
						adjacence.elementPosition(i).retirerPosition(j);
					}
				}
			}
		}		
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#estVide()
	 */
	@Override
	public boolean estVide() {
		return ( lesSommets().longueur() == 0 );
	}

	/* (non-Javadoc)
	 * @see tda.Graphe#estNonConnecte()
	 */
	@Override
	public boolean estNonConnecte() {
		Liste<T> voisins = new ListeChaine<T>();
		if (estVide()) {
			return false;  // Cas où il n'y a pas de sommets dans le graphe
		} else {
			for (T sommet : lesSommets()) {
					voisins = lesVoisins(sommet);
					
					/* Teste la longueur de la liste des voisins du sommet courant */
					boolean connecte = (voisins.longueur() != 0);
					if (connecte)
						return false;
			}
		}
		return true;
	}

}
