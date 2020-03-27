import java.util.ArrayList;
import java.awt.geom.Line2D;

/*
 * Classe qui determine la triangulation d'un polygome convexe par essais successifs
 */
public class EssaisSuccessifs {

    private ArrayList<Point> points;    //la liste des points du polygone, generee au debut
    private ArrayList<Corde> cordes;    //la liste des cordes, remplie au cours de l'algorithme

    /*
     * Simple constructeur, affiche la triangularisation minimale
     * @param n le nombre de sommets du polygone
     */
    public EssaisSuccessifs(int n){
        this.points = ValtrAlgorithm.generateRandomConvexPolygon(n);
        double result = triangularisationMinimale();
        System.out.println(result);
    }

    /*
     * @return la triangularisation minimale
     */
    public double triangularisationMinimale(){
        return 0;
    }

    /*
     * @return true si la corde testee est deja tracee ou si elle coupe une corde deja tracee
     */
    public boolean valideCorde(Point p1, Point p2){
        boolean ret = false;
        for (Corde c : cordes){
            if (c.getP1().equals(p1) && c.getP2().equals(p2)) ret = true;
            if (c.getP1().equals(p2) && c.getP2().equals(p1)) ret = true;
            if (ret = false)
                ret = Line2D.linesIntersect(p1.getX(),p1.getY(),p2.getX(),p2.getY(),
                        c.getP1().getX(),c.getP1().getY(),c.getP2().getX(),c.getP2().getY());
            if (ret = true) break;
        }
        return ret;
    }
}
