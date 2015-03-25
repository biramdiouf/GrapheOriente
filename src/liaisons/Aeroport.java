package liaisons;

public class Aeroport {
	private String nom;
	private String sigle;
	private String ville;

	public Aeroport(String sigle, String nom, String ville) {
		super();
		this.nom = nom;
		this.ville = ville;
		this.sigle = sigle;
	}

	public String leSigle() {
		return sigle;
	}

	public String leNom() {
		return nom;
	}

	public String laVille() {
		return ville;
	}

	@Override
	public String toString() {
		return "Aeroport [sigle=" + sigle + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeroport other = (Aeroport) obj;
		if (sigle == null) {
			if (other.sigle != null)
				return false;
		} else if (!sigle.equals(other.sigle))
			return false;
		return true;
	}
	
	

}
