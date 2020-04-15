import java.awt.*;
import java.util.ArrayList;
import java.math.*;

public class Main {


    public static void main(String[] args) {


        int n = Integer.parseInt(/*args[0]*/ "5");
        ArrayList<Point> points = ValtrAlgorithm.generateRandomConvexPolygon(n);
        for (Point p : points){
            System.out.println("point " + points.indexOf(p) + " : X = " + p.getX() + ", Y = " + p.getY());
        }

        EssaisSuccessifs e = new EssaisSuccessifs(n, points);
        System.out.println(e.getResult());

        new ProblemeDynamique(n, points);

        Glouton g = new Glouton(n, points);
        System.out.println(g.getResult());
    }
}
