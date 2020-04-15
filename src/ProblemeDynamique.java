import java.util.ArrayList;
import java.math.*;


public class ProblemeDynamique {

    public int n;
    public double[][] matriceProbleme;
    public ArrayList<Point> points;
    public double[][] distancesPoints;

    public ProblemeDynamique(int n, ArrayList<Point> points){
        this.points = points;
        this.matriceProbleme = new double[n][n];
        this.distancesPoints = new double[n][n];

        for(int i = 0; i<n;i++){
            for(int j = 0; j<n;j++ ){
                matriceProbleme[i][j]=-1;
                distancesPoints[i][j]=-1;
                if(Math.abs(i-j) <= 1){
                    distancesPoints[i][j]=0;
                }
            }
        }
        matriceToString(distancesPoints);
        double result = triangularisation(0, n-1);
        System.out.println(result);
    }

    public String distancesPointsToString(){
        String s = "";
        for(int i=0; i<n; i++){
            System.out.println(distancesPoints[0][0]);
            for (int j=0; j<n; j++){
                //s = s + String.valueOf(distancesPoints[i][j])+", ";

            }
            s = s +"\n";

        }
        return  s;
    }

    public double calculPoid(int point1, int point2, int point3) {

        double corde1;
        double corde2;
        double corde3;

        if(this.distancesPoints[point1][point2] == -1){

            corde1 = Math.sqrt(
                    Math.pow(this.points.get(point1).getX()-this.points.get(point2).getX(),2)
                    + Math.pow(this.points.get(point1).getY()-this.points.get(point2).getY(),2));
            this.distancesPoints[point1][point2] = corde1;
        } else {
            corde1 = this.distancesPoints[point1][point2];
        }
        if(this.distancesPoints[point2][point3] == -1){

            corde2 = Math.sqrt(
                    Math.pow(this.points.get(point2).getX()-this.points.get(point3).getX(),2)
                            + Math.pow(this.points.get(point2).getY()-this.points.get(point3).getY(),2));
            this.distancesPoints[point2][point3] = corde2;
        } else {
            corde2 = this.distancesPoints[point2][point3];
        }
        if(this.distancesPoints[point3][point1] == -1){

            corde3 = Math.sqrt(
                    Math.pow(this.points.get(point3).getX()-this.points.get(point1).getX(),2)
                            + Math.pow(this.points.get(point3).getY()-this.points.get(point1).getY(),2));
            this.distancesPoints[point3][point1] = corde3;
        } else {
            corde3 = this.distancesPoints[point3][point1];
        }
        double poid = corde1 + corde2 + corde3;
        return poid;
    }

    public double triangularisation(int debutSetPoints, int finSetPoints){
        int i = debutSetPoints;
        int t = finSetPoints;
        Double poidMin = 0.0;

        if(this.matriceProbleme[i][t]!=-1){
            return this.matriceProbleme[i][t];
        }
        else{
            System.out.println("triangularisation:"+debutSetPoints+","+finSetPoints);
            //matriceToString(matriceProbleme);
            if(t == i+2){
                this.matriceProbleme[i][t] = 0;
                return this.matriceProbleme[i][t];
            }
            else{
                for(int k = i+2 ; k < t; k++){
                    double poid = triangularisation(i,k) + triangularisation(k,i) + distancesPoints[i][k];
                    if(k == i+2){
                        poidMin = poid;
                    }else{
                        poidMin = Math.min(poid, poidMin);
                    }
                }
                matriceProbleme[i][t]= poidMin;
                return this.matriceProbleme[i][t];
            }
        }
    }

    public void matriceToString(double[][] matrice){
        String chaine = "|";
        for(int i=0;i< matrice.length;i++){
            for( int j=0;j<matrice.length;j++){
                chaine = chaine +" "+ matrice[i][j]+ " |";
            }
            chaine = chaine +"\n";
        }
        System.out.println(chaine);
    }
}
