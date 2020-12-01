package tests;

import beans.EtatCase;
import beans.Plateau;
import prevision.Possibilite;

public class TestPlateau {

	public static void main(String[] args) {
		Plateau p=new Plateau(1);	
		p.init2();
		System.out.println("=== Départ ===");
		System.out.println(p);
		
		// test des possibilités de niveau 1
		Possibilite poss=new Possibilite(p);
		// on change de joueur
		int joueur=Plateau.autreJoueur(poss.getJoueur());
		int trouve=0;
		for(int l=0;trouve==0 && l<Plateau.NB_CASES;l++) {
			for(int c=0;trouve==0 && c<Plateau.NB_CASES;c++) {
				// System.out.format("Ligne %d, Colonne %d\n",l,c);
				if(poss.getPlateau().getPlateau()[l][c].estVide()) {
					Possibilite np=new Possibilite(1, joueur, l, c, poss.getPlateau(), null);
					poss.getSuivant().add(np);
					System.out.println(np.getPlateau());
					trouve=np.getPlateau().quiAGagne();
					//System.out.println("\t\tTaille "+poss.getSuivant().size());
					System.out.format("Ligne %d, Colonne %d : %d\n",l,c,trouve);
				}
			}
		}
		
		System.out.println("Restitution des résultats :");
		System.out.println("===========================");
		if(trouve!=0) {
			System.out.println(EtatCase.values()[trouve]+" a gagné !");
		}
		for(Possibilite ps:poss.getSuivant()) {
			int qag = ps.getPlateau().quiAGagne();
			System.out.println(ps.getPlateau()+String.format(" -> %s (Note=%d)\n", qag==0?"NON !":EtatCase.values()[qag],ps.quiAGagne()));
		}
	}

}
