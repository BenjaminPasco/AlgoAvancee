import java.awt.*;
import java.util.ArrayList;
import java.math.*;

public class Main {


    public static void main(String[] args) {


        int n = Integer.parseInt(/*args[0]*/ "4");
        ArrayList<Point> points = ValtrAlgorithm.generateRandomConvexPolygon(n);
        for (Point p : points){
            System.out.println("point " + points.indexOf(p) + " : X = " + p.getX() + ", Y = " + p.getY());
        }
        new EssaisSuccessifs(n, points);
        new ProblemeDynamique(n, points);

        //System.out.println(points.toString()+"\n");
    }
}
