package tda;

import java.util.ArrayList;

import util.ListeChaine;


/**
 * @author Lefebvre Bernard
 *
 * @param <T>, le type des sommets du graphe
 * @param <V>, le type de la valeur attachée aux arcs
 */
public interface Graphe<T,V> {
	
	/**
	 * @param x : un sommet du graphe
	 * @param y : un sommet du graphe
	 * @return, retourne vrai s'il existe un arc entre le sommet x et le sommet y
	 * Si x ou y ne sont pas des sommets du graphe, retourne false;
	 */
	public boolean sontAdjacents(T x, T y);
	
	/**
	 * @param x : un sommet du graphe
	 * @return, retourne un tableau liste contenant des sommets adjacents à x
	 * Si x n'est pas un sommet du graphe, retourne un tableau liste vide;
	 */
	public Liste<T> lesVoisins(T x);
	
	/**
	 * @return, retourne un tableau liste contenant l'ensemble des sommets du graphe
	 */
	public Liste<T> lesSommets();
	
	/**
	 * @param x : le sommet du graphe à ajouter
	 * ajoute le sommet x au graphe
	 */
	public void ajouter(T x);
	
	/**
	 * @param x : un sommet du graphe
	 * @param y : un sommet du graphe
	 * @return, retourne la valeur associée à l'arc dont les sommets x et y sont adjacents
	 * Si x ou y ne sont pas des sommets du graphe, retourne null;
	 */
	public V laValeur(T x, T y);
	
	/**
	 * @param x : un sommet du graphe
	 * @param y : un sommet du graphe
	 * @param v : la valeur à associer à l'arc
	 * ajoute un arc entre les sommets x et y avec la valeur v
	 * Si x ou y ne sont pas des sommets du graphe, ajouter ne change pas le graphe;
	 */
	public void ajouter(T x, T y, V v);
	
	/**
	 * @param x : un sommet du graphe
	 * @param y : un sommet du graphe
	 * Supprime l'arc entre les sommets x et y si celui-ci existe
	 * Si x ou y ne sont pas des sommets du graphe, supprimer ne change pas le graphe;
	 */
	public void supprimer(T x, T y);
	
	/**
	 * @param x : un sommet du graphe
	 * supprime tous les arcs pour lesquels x est un sommet adjacent
	 * Si x n'est pas un sommet du graphe, supprimer ne change pas le graphe;
	 */
	public void supprimer(T x);
	
	/**
	 * @return, retourne vrai si et seulement si le graphe est vide (sans sommets)
	 */
	public boolean estVide();
	
	/**
	 * @return, retourne vrai si et seulement si le graphe possède des sommets mais pas d'arcs
	 */
	public boolean estNonConnecte();
	
}
