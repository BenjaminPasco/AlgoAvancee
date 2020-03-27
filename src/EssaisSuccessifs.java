import java.util.ArrayList;
import java.awt.geom.Line2D;

/*
 * Classe qui determine la triangulation d'un polygome convexe par essais successifs
 */
public class EssaisSuccessifs {

    final double MAX = 100000000;
    private int n;
    private ArrayList<Point> points;    //la liste des points du polygone, generee au debut
    private ArrayList<Corde> cordes;    //la liste des cordes, remplie au cours de l'algorithme

    /*
     * Simple constructeur, affiche la triangularisation minimale
     * @param n le nombre de sommets du polygone
     */
    public EssaisSuccessifs(int n){
        this.n = n;
        this.points = ValtrAlgorithm.generateRandomConvexPolygon(n);
        this.cordes = new ArrayList<Corde>();
        double result = triangulation(0, n-1);
        System.out.println(result);
    }

    /*
     * @return la triangularisation minimale
     */
    public double triangulation(int i, int j){

        if(j <= i+ 2) return 0;

        //le resultat initial est suppose infini
        double res = MAX;

        for (int k = i + 2; k < j; k++){
            if(valideCorde(i, k)){
                Corde c = new Corde(this.points.get(i), this.points.get(k));
                this.cordes.add(c);
                res = Math.min(res, triangulation(i,k) + triangulation(k,j) + c.getLongueur());
            }
        }
        return res;
    }

    /*
     * @return false si la corde testee est deja tracee ou si elle coupe une corde deja tracee
     */
    public boolean valideCorde(int i, int j){
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
        if(j == i + 1 || (i == 0 && j == this.n - 1)) ret = false;

        for (Corde c : cordes){
            //non valide si la corde est déjà dans la liste
            if (c.getP1().equals(points.get(i)) && c.getP2().equals(points.get(j))) ret = false;

            //non valide si la corde coupe une autre corde de la liste
            if (ret)
                ret = !(Line2D.linesIntersect(
                        points.get(i).getX(),
                        points.get(i).getY(),
                        points.get(j).getX(),
                        points.get(j).getY(),

                        c.getP1().getX(),
                        c.getP1().getY(),
                        c.getP2().getX(),
                        c.getP2().getY()));
            if (!ret) break;
        }
        return ret;
    }
}
