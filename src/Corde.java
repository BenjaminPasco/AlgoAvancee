/**
 * Classe qui modelise une corde de 2 points, possibilite de retourner sa longueur
 */
public class Corde {

    private Point p1;
    private Point p2;
    private double longueur;

    public Corde(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
        this.longueur = Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX())
                + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()));
    }

    public Point getP1(){
        return this.p1;
    }
    public Point getP2(){
        return this.p2;
    }
    public double getLongueur(){
        return this.longueur;
    }
}
