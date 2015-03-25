package liaisons;

import java.util.Date;

public class Vol {
	
	private String nom;
	private String compagnie;
	private Date depart;
	private Date arrivee;
	
	public String getNom() {
		return nom;
	}

	public void leNom(String nom) {
		this.nom = nom;
	}

	public String laCompagnie() {
		return compagnie;
	}

	public void definirCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}

	public Date leDepart() {
		return depart;
	}

	public void definirDepart(Date depart) {
		this.depart = depart;
	}

	public Date lArrivee() {
		return arrivee;
	}

	public void definirArrivee(Date arrivee) {
		this.arrivee = arrivee;
	}

	public Vol(String nom, String compagnie, Date depart, Date arrivee) {
		super();
		this.nom = nom;
		this.compagnie = compagnie;
		this.depart = depart;
		this.arrivee = arrivee;
	}

	@Override
	public String toString() {
		return "Vol [nom=" + nom + ", compagnie=" + compagnie + ", depart="
				+ depart + ", arrivee=" + arrivee + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vol other = (Vol) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
