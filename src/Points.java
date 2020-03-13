public class Points {
    Double x;
    Double y;
     public Points(Double x, Double y){
         this.x = x;
         this.y = y;
     }
     public void setCoord(Double x, Double y){
         this.x = x;
         this.y = y;
     }
     public Double getX(){
         return this.x;
     }
     public Double getY(){
         return this.y;
     }
     public String toString(){
         return "\n x ="+this.getX()+", y ="+this.getY();
     }
}
