package beans;

public class Plateau {
	public static final int NB_CASES=5;
	private Case[][] m_plateau=new Case[NB_CASES][NB_CASES];
	private int m_joueurCourant;
	
	/**
	 * Instanciation d'un plateau vide avec son joueur
	 * @param joueurCourant : le joueur courant, banane
	 */
	public Plateau(int joueurCourant) {
		m_joueurCourant = joueurCourant;
		for(int l=0;l<NB_CASES;l++) {
			for(int c=0;c<NB_CASES;c++) {
				m_plateau[l][c]=new Case();
			}
		}
	}
	
	/**
	 * Création d'un plateau à partir d'un autre avec recopie intégrale.
	 * @param p
	 */
	public Plateau(Plateau p) {
		for(int l=0;l<NB_CASES;l++) {
			for(int c=0;c<NB_CASES;c++) {
				m_plateau[l][c]=new Case(p.m_plateau[l][c]);
			}
		}
	}
	
	/**
	 * Pour savoir quel est l'indice de l'autre joueur.
	 * @param joueur
	 * @return
	 */
	public static int autreJoueur(int joueur) {
		return joueur==1?2:1;
	}
	/**
	* Pour basculer d'un joueur à l'autre
	*/
	public void changeJoueur() {
		m_joueurCourant=m_joueurCourant==1?2:1;
	}
	/**
	* Place, de la part d'un joueur donné, une marque aux coordonnées indiquées
	*/
	public void place(int joueur, int l, int c) {
		m_plateau[l][c].setEtat(EtatCase.values()[joueur]);
	}
	/**
	* Place, de la part du joueur courant, une marque aux coordonnées indiquées, puis change de joueur
	*/
	public void place(int l, int c) {
		m_plateau[l][c].setEtat(EtatCase.values()[m_joueurCourant]);
		changeJoueur();
	}
	/**
	* Afficher en mode console le plateau
	*/
	public String toString() {
		String res=new String(new char[NB_CASES]).replace("\0", "-");
		for(int l=0;l<NB_CASES;l++) {
			res+="\n";
			for(int c=0;c<NB_CASES;c++) {
				res+=m_plateau[l][c];
			}
		}
		return res;
	}

	public int getJoueurCourant() {
		return m_joueurCourant;
	}

	public Case[][] getPlateau() {
		return m_plateau;
	}	
	/**
	* Programme général pour savoir qui a gagné
	*/
	public int quiAGagne() {
		int l=ligne4Egale();
		int c = colonne4Egale();
		int dD = diagonaleD4Egale();
		int dG = diagonaleG4Egale();		
		if (l != 0)		return l;
		if(c!=0)		return c;
		if(dD!=0)		return dD;	
		if(dG!=0)		return dG;		
		return 0;
	}
	/**
	* Recherche de lignes contenant 4 identiques consécutives
	*/
	private int ligne4Egale() {
		for(int l=0;l<NB_CASES;l++) {
			for(int c=0;c<NB_CASES-3;c++) {
				if(! m_plateau[l][c].estVide() && 
						m_plateau[l][c].getEtat()==m_plateau[l][c+1].getEtat() && m_plateau[l][c].getEtat()==m_plateau[l][c+2].getEtat() && 
						m_plateau[l][c].getEtat()==m_plateau[l][c+3].getEtat()) {
					return m_plateau[l][c].getEtat().ordinal();
				}
			}
		}
		return 0;
	}
	
	private int colonne4Egale() {
		for(int c=0;c<NB_CASES;c++) {
			for(int l=0;l<NB_CASES-3;l++) {
				if(! m_plateau[l][c].estVide() && 
						m_plateau[l][c].getEtat()==m_plateau[l+1][c].getEtat() && m_plateau[l][c].getEtat()==m_plateau[l+2][c].getEtat() && 
						m_plateau[l][c].getEtat()==m_plateau[l+3][c].getEtat()) {
					return m_plateau[l][c].getEtat().ordinal();
				}
			}
		}
		return 0;
	}
	
	private int diagonaleD4Egale() {
		for(int c=0;c<NB_CASES-3;c++) {
			for(int l=0;l<NB_CASES-3;l++) {
				if(! m_plateau[l][c].estVide() && 
						m_plateau[l][c].getEtat()==m_plateau[l+1][c+1].getEtat() && m_plateau[l][c].getEtat()==m_plateau[l+2][c+2].getEtat() && 
						m_plateau[l][c].getEtat()==m_plateau[l+3][c+3].getEtat()) {
					return m_plateau[l][c].getEtat().ordinal();
				}
			}
		}
		return 0;
	}
	
	private int diagonaleG4Egale() {
		for(int c=3;c<NB_CASES;c++) {
			for(int l=0;l<NB_CASES-3;l++) {
				if(! m_plateau[l][c].estVide() && 
						m_plateau[l][c].getEtat()==m_plateau[l+1][c-1].getEtat() && m_plateau[l][c].getEtat()==m_plateau[l+2][c-2].getEtat() && 
						m_plateau[l][c].getEtat()==m_plateau[l+3][c-3].getEtat()) {
					return m_plateau[l][c].getEtat().ordinal();
				}
			}
		}
		return 0;
	}
}
