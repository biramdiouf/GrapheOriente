/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tda.Liste;

/**
 * @author lefebvre_b
 *
 */
public class ListeChaine<T> implements Liste<T> {

	protected Maillon<T> tete;
	
	/* (non-Javadoc)
	 * @see tda.Liste#estVide()
	 */
	@Override
	public boolean estVide() {
		return tete == null;
	}

	/* (non-Javadoc)
	 * @see tda.Liste#longueur()
	 */
	@Override
	public int longueur() {
		int nbElements = 0;
		Maillon<T> elem = tete;
		while (elem != null) {
			nbElements++;
			elem = elem.getSuivant();
		}
		return nbElements;
	}

	/* (non-Javadoc)
	 * @see tda.Liste#elementDebut()
	 */
	@Override
	public T elementDebut() {
		return this.elementPosition(0);
	}

	/* (non-Javadoc)
	 * @see tda.Liste#elementFin()
	 */
	@Override
	public T elementFin() {
		return this.elementPosition(this.longueur() - 1);
	}
	
	/* (non-Javadoc)
	 * @see tda.Liste#elementPosition(int)
	 */
	@Override
	public T elementPosition(int pos) {
		if (pos < 0 || pos >= this.longueur()) throw new NoSuchElementException();
		Maillon<T> elem = tete;
		for (int i = 0; i < pos; i++)
			elem = elem.getSuivant();
		return elem.getValeur();
	}

	/* (non-Javadoc)
	 * @see tda.Liste#insererDebut(java.lang.Object)
	 */
	@Override
	public void insererDebut(T element) {
		this.insererPosition(element, 0);
	}

	/* (non-Javadoc)
	 * @see tda.Liste#insererFin(java.lang.Object)
	 */
	@Override
	public void insererFin(T element) {
		this.insererPosition(element, this.longueur());
	}
	
	/* (non-Javadoc)
	 * @see tda.Liste#insererPosition(java.lang.Object, int)
	 */
	@Override
	public void insererPosition(T element, int pos) {
		if (pos < 0 || pos > this.longueur()) throw new NoSuchElementException();
		Maillon<T> nouveau = new Maillon<T>(element);
		if (pos == 0) {
			nouveau.setSuivant(tete);
			tete = nouveau;
		} else {
			Maillon<T> elem = tete;
			for (int i = 0; i < pos - 1; i++)
				elem = elem.getSuivant();
			nouveau.setSuivant(elem.getSuivant());
			elem.setSuivant(nouveau);
		}
	}

	/* (non-Javadoc)
	 * @see tda.Liste#retirerDebut()
	 */
	@Override
	public T retirerDebut() {
		return this.retirerPosition(0);
	}

	/* (non-Javadoc)
	 * @see tda.Liste#retirerFin()
	 */
	@Override
	public T retirerFin() {
		return this.retirerPosition(this.longueur() - 1);
	}

	/* (non-Javadoc)
	 * @see tda.Liste#retirerPosition(int)
	 */
	@Override
	public T retirerPosition(int pos) {
		if (pos < 0 || pos >= this.longueur()) throw new NoSuchElementException();
		if (pos == 0) {
			T val = tete.getValeur();
			tete = tete.getSuivant();
			return val;
		}
		Maillon<T> elem = tete;
		for (int i = 0; i < pos - 1; i++)
			elem = elem.getSuivant();
		Maillon<T> sup = elem.getSuivant();
		elem.setSuivant(sup.getSuivant());
		return sup.getValeur();
	}

	@Override
	public Collection<T> lesValeurs() {
		Collection<T> valeurs = new ArrayList<T>();
		for (T elem : this) {
			valeurs.add(elem);
		}
		return valeurs;
	}

	@Override
	public boolean contient(T elem) {
		boolean trouve = false;
		for (Iterator<T> iter = this.iterator(); ! trouve && iter.hasNext();) {
			trouve = ((T) iter.next()).equals(elem);
		}
		return trouve;
	}

	@Override
	public boolean contientTous(Collection<T> elems) {
		return this.lesValeurs().containsAll(elems);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			protected Maillon<T> courant = (Maillon<T>) tete;
			
			@Override
			public boolean hasNext() {
				return courant != null;
			}

			@Override
			public T next() {
				Maillon<T> prec = courant;
				courant = courant.getSuivant();
				return prec.getValeur();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}

}
