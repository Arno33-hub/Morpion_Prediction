package beans;

public class Case {
	private EtatCase m_etat;

	public Case() {
		m_etat=EtatCase.VIDE;
	}
	
	public Case(Case c) {
		m_etat=c.m_etat;
	}

	public EtatCase getEtat() {
		return m_etat;
	}

	public void setEtat(EtatCase etat) {
		if(m_etat==EtatCase.VIDE)
			m_etat = etat;
	}
	public String toString() {
		switch(m_etat) {
		case JOUEUR1:
			return "1";
		case JOUEUR2:
			return "2";
		default:
			return " ";
		}
	}
	public boolean estVide() {
		return m_etat==EtatCase.VIDE;
	}
}
