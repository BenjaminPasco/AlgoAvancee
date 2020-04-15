import java.util.ArrayList;

/**
 *  * Classe qui determine la triangulation minimale d'un polygome convexe par un algorithme glouton
 */
public class Glouton {

    private int n;                      //le nombre de sommets du polygone
    private ArrayList<Point> points;    //la liste des points du polygone, generee au debut

    /*
     * Simple constructeur, affiche la triangularisation minimale
     * @param n le nombre de sommets du polygone
     * @param points la liste des points du polynome
     */
    public Glouton(int n, ArrayList<Point> points){
        this.n = n;
        this.points = points;
        double result = triangulationGloutonne();
        System.out.println(result);
    }

    /*
     * methode recursive pour trouver la triangulation minimale
     * @param pts les points de la triangulation a calculer
     * @return la triangulation
     */
    public double triangulationGloutonne(){
        double res = 0;

        return res;
    }

}
