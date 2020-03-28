import java.awt.*;
import java.util.ArrayList;
import java.math.*;

public class Main {


    public static void main(String[] args) {


        int n = Integer.parseInt(/*args[0]*/ "5");
        ArrayList<Point> points = ValtrAlgorithm.generateRandomConvexPolygon(n);
        new EssaisSuccessifs(n, points);
        new ProblemeDynamique(n, points);

        //System.out.println(points.toString()+"\n");
    }
}
