/**
 * 
 */
package util;

/**
 * @author lefebvre_b
 *
 */
public class Maillon<T> implements Cloneable {
	private T valeur;
	private Maillon<T> suivant;
	/**
	 * @param valeur, la valeur du maillon
	 * @param suivant, le maillon suivant
	 */
	public Maillon(T valeur, Maillon<T> suivant) {
		super();
		this.valeur = valeur;
		this.suivant = suivant;
	}
	/**
	 * @param valeur, la valeur du maillon
	 */
	public Maillon(T valeur) {
		this(valeur, null);
	}
	
	/**
	 * @param valeur, la valeur du maillon
	 */
	public void setValeur(T valeur) {
		this.valeur = valeur;
	}
	/**
	 * @return, retourne la valeur du maillon
	 */
	public T getValeur() {
		return valeur;
	}
	/**
	 * @return, retourne le maillon suivant
	 */
	public Maillon<T> getSuivant() {
		return suivant;
	}
	/**
	 * @param suivant, définit le maillon suivant
	 */
	public void setSuivant(Maillon<T> suivant) {
		this.suivant = suivant;
	}
}
