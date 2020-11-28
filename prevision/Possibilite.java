package prevision;

import java.util.ArrayList;

import beans.Plateau;

public class Possibilite {
	public static final int PROFONDEUR_MAX=2;
	private int m_profondeur;
	private int m_joueur;
	private int m_ligne;
	private int m_colonne;
	private int m_note;
	private ArrayList<Possibilite> m_suivant=null;
	private Plateau m_plateau;
	private Possibilite m_precedent=null;
	
	/**
	 * Génération du départ des possibilités
	 * @param plateau : Plateau intégralement copié dans le noeud départ
	 * <i>Génération de la collection de possibilités de profondeur suivante</i>
	 */
	public Possibilite(Plateau plateau) {
		m_profondeur = 0;
		// on change de joueur
		m_joueur = Plateau.autreJoueur(plateau.getJoueurCourant());
		m_ligne = m_colonne = -1;
		m_note = 0;
		m_suivant=new ArrayList<>();
		// on recopie en dur le plateau
		m_plateau=new Plateau(plateau);
		m_precedent=null;
	}
	
	/**
	 * Création d'une possibilité Enfant. On lui colle le plateau, puis le mouvement du joueur.
	 * 
	 * @param profondeur : la profondeur de la recherche
	 * @param joueur : qui joue ?
	 * @param ligne : ma soeur s'est faite mordre par un Elan
	 * @param colonne
	 * @param plateau : le plateau initial
	 * @param precedent : éventuellement, la possibilité parente
	 */
	public Possibilite(int profondeur, int joueur, int ligne, int colonne, Plateau plateau, Possibilite precedent) {
		m_profondeur = profondeur;
		m_joueur = joueur;
		m_ligne = ligne;
		m_colonne = colonne;
		if(profondeur<PROFONDEUR_MAX)
			m_suivant=new ArrayList<>();
		// on recopie en dur le plateau
		m_plateau=new Plateau(plateau);
		m_plateau.place(joueur, ligne, colonne);
		m_note = 0;
		m_precedent=precedent;
	}
	
	/**
	 * Calcul de la note.
	 * 
	 * @return<ul>
	 * <li>1 si le gagnant du plateau est le joueur de la possibilité</li>
	 * <li>-1 si le gagnant du plateau est l'adversaire</li>
	 */
	public int quiAGagne() {
		// dans la note : on met, pour le joueur eu cours :
		//   +1 si c'est celui-ci qui a gagné
		//	  0 si personne n'a gagné
		//   -1 si c'est l'autre qui a gagné
		/*for (Possibilite possibilite : m_suivant) {
			int trouve=possibilite.getPlateau().quiAGagne();
			if(trouve!=0) {
				if(trouve==m_joueur)
					m_note = 1;
				else
					m_note = -1;
				break;
			}
		}*/
		int trouve=m_plateau.quiAGagne();
		if(trouve!=0) {
			if(trouve==m_joueur)
				m_note = 1;
			else
				m_note = -1;
		}
		return m_note;
	}

	public int getProfondeur() {
		return m_profondeur;
	}

	public int getJoueur() {
		return m_joueur;
	}

	public int getLigne() {
		return m_ligne;
	}

	public int getColonne() {
		return m_colonne;
	}

	public int getNote() {
		return m_note;
	}

	public ArrayList<Possibilite> getSuivant() {
		return m_suivant;
	}

	public Plateau getPlateau() {
		return m_plateau;
	}
	
}
