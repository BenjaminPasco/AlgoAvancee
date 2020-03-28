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
    public EssaisSuccessifs(int n, ArrayList<Point> points){
        this.n = n;
        this.points = points;
        this.cordes = new ArrayList<Corde>();
        double result = triangulation(this.points);
        System.out.println(result);
    }

    /*
     * methode recursive pour trouver la triangulation minimale
     * @return la triangularisation
     */
    public double triangulation(ArrayList<Point> pts){

        int i = 0;
        int j = pts.size();

        if(j <= i + 3) return 0;

        //le resultat initial est suppose infini
        double res = MAX;

        for (int k = i + 2; k < j; k++){
            if(valideCorde(i, k)){
                Corde c = new Corde(pts.get(i), pts.get(k));
                this.cordes.add(c);
                ArrayList<Point> ptsTriang1 = new ArrayList<Point>(pts);
                ArrayList<Point> ptsTriang2 = new ArrayList<Point>(pts);
                for (int l = k + 1; l < j; l++){
                    ptsTriang1.remove(k + 1);
                }
                for (int l = i + 1; l < k; l++){
                    ptsTriang2.remove(i + 1);
                }
                res = Math.min(res, triangulation(ptsTriang1) + triangulation(ptsTriang2) + c.getLongueur());
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
