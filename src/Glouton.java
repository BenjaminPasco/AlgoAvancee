import java.util.ArrayList;

/**
 * Classe qui determine la triangulation minimale d'un polygome convexe par un algorithme glouton
 */
public class Glouton {

    final double MAX = 100000000;       //longueur d'une corde impossible a atteindre
    private int n;                      //le nombre de sommets du polygone
    private ArrayList<Point> points;    //la liste des points du polygone, generee au debut
    private double result;

    /**
     * Simple constructeur, affiche la triangularisation minimale
     * @param n le nombre de sommets du polygone
     * @param points la liste des points du polynome
     */
    public Glouton(int n, ArrayList<Point> points){
        this.n = n;
        this.points = points;
        this.result = triangulationGloutonne();
    }

    /**
     * methode gloutonne pour trouver la triangulation minimale
     * @return la triangulation
     */
    public double triangulationGloutonne(){
        double res = 0;
        int nRestant = 0;
        ArrayList<Integer> indexUtilises = new ArrayList<Integer>();
        boolean plusDeCordeExt = false;
        while(!plusDeCordeExt) {
            int cptIndexPossibles = 0;
            double[] tabValCordesExt = new double[this.n];
            for (int m = 0; m < this.n; m++) {
                boolean indexDejaUtilise = false;
                for (Integer i : indexUtilises) {
                    if (m == i || m == i + 1) indexDejaUtilise = true;
                    if (m == 0 && i == this.n - 1) indexDejaUtilise = true;
                }
                if (!indexDejaUtilise) {
                    Corde c = null;
                    if (m + 2 < this.n) c = new Corde(this.points.get(m), this.points.get(m + 2));
                    else if (m + 2 == this.n) c = new Corde(this.points.get(m), this.points.get(0));
                    else c = new Corde(this.points.get(m), this.points.get(1));
                    tabValCordesExt[m] = c.getLongueur();
                    cptIndexPossibles++;
                }
            }
            if (cptIndexPossibles == 0) plusDeCordeExt = true;
            double valCordeMini = this.MAX;
            int indexMini = 0;
            for (int i = 0; i < this.n; i++) {
                valCordeMini = Math.min(valCordeMini, tabValCordesExt[i]);
                if (valCordeMini == tabValCordesExt[i]) indexMini = i;
            }
            indexUtilises.add(indexMini);
            res = res + valCordeMini;
        }
        nRestant = n - indexUtilises.size();
        ArrayList<Point> ptsRestants = new ArrayList<Point>(this.points);
        for (Integer i : indexUtilises){
            if (i == this.n - 1) ptsRestants.remove(0);
            else ptsRestants.remove(i + 1);
        }

        //On determine le reste des cordes par la strategie des essais sucessifs
        EssaisSuccessifs e = new EssaisSuccessifs(nRestant, ptsRestants);
        return res + e.getResult();
    }

    /**
     * @return le resultat de la triangulation minimale
     */
    public double getResult(){
        return this.result;
    }
}
