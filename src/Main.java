import java.awt.*;
import java.util.ArrayList;
import java.math.*;

public class Main {


    public static void main(String[] args) {


        int n = Integer.parseInt(/*args[0]*/ "5");
        ArrayList<Point> points = ValtrAlgorithm.generateRandomConvexPolygon(n);
        for (Point p : points){
            System.out.println("(" + p.getX() + ", " + p.getY() + ")");
        }

        EssaisSuccessifs e = new EssaisSuccessifs(n, points);
        System.out.println("Resultat essais successifs :" + e.getResult());

        ProblemeDynamique p = new ProblemeDynamique(n, points);
        System.out.println("Resultat probleme dynamique :" + p.getResult());

        Glouton g = new Glouton(n, points);
        System.out.println("Resultat glouton :" + g.getResult());
    }
}
