import java.util.ArrayList;
import java.awt.geom.Line2D;

/**
 * Classe qui determine la triangulation minimale d'un polygome convexe par essais successifs
 */
public class EssaisSuccessifs {

    final double MAX = 100000000;       //longueur d'une corde impossible a atteindre
    private int n;                      //le nombre de sommets du polygone
    private ArrayList<Point> points;    //la liste des points du polygone, generee au debut
    private ArrayList<Corde> cordes;    //la liste des cordes, remplie au cours de l'algorithme
    private double result;

    /**
     * Simple constructeur, affiche la triangularisation minimale
     * @param n le nombre de sommets du polygone
     * @param points la liste des points du polynome
     */
    public EssaisSuccessifs(int n, ArrayList<Point> points){
        this.n = n;
        this.points = points;
        this.cordes = new ArrayList<Corde>();
        this.result = triangulation(this.points);
    }

    /**
     * methode recursive pour trouver la triangulation minimale
     * @param pts les points de la triangulation a calculer
     * @return la triangulation
     */
    public double triangulation(ArrayList<Point> pts){

        int i = 0;
        int j = pts.size();

        if(j <= i + 3) return 0;

        //le resultat initial est suppose infini
        double res = this.MAX;
        for (int m = i; m < j; m++) {
            for (int k = m + 2; k < j; k++) {
                if (valideCorde(m, k, pts)) {
                    Corde c = new Corde(pts.get(m), pts.get(k));
                    this.cordes.add(c);
                    ArrayList<Point> ptsTriang1 = new ArrayList<Point>(pts);
                    ArrayList<Point> ptsTriang2 = new ArrayList<Point>(pts);
                    for (int l = k + 1; l < j + m; l++) {
                        if (l < j) ptsTriang1.remove(k + 1);
                        else ptsTriang1.remove(0);
                    }
                    for (int l = m + 1; l < k; l++) {
                        ptsTriang2.remove(m + 1);
                    }
                    res = Math.min(res, triangulation(ptsTriang1) + triangulation(ptsTriang2) + c.getLongueur());
                }
            }
        }
        return res;
    }

    /**
     * @param i le premier point de la corde
     * @param j le deuxieme point de la corde
     * @param pts les points de la triangulation en cours de calcul
     * @return false si la corde testee est deja tracee, ou si les 2 points sont identiques,
     * ou si les 2 points sont consecutifs
     * les intersections entre cordes sont rendus impossibles par la conception de la methode triangulation
     */
    public boolean valideCorde(int i, int j, ArrayList<Point> pts){
        boolean ret = true;
        //si j < i, on inverse les valeurs
        if (j < i){
            int k = j;
            j = i;
            i = k;
        }

        //non valide si on a pris 2 points identiques
        if (i == j) ret = false;

        //non valide si on a pris 2 points consécutifs
        if(j == i + 1 || (i == 0 && j == pts.size() - 1)) ret = false;

        for (Corde c : cordes){
            //non valide si la corde est déjà dans la liste
            if (c.getP1().equals(pts.get(i)) && c.getP2().equals(pts.get(j))) ret = false;

        }
        return ret;
    }

    /**
     * @return le resultat de la triangulation minimale
     */
    public double getResult(){
        return this.result;
    }
}
