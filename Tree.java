/*************************************************************************
 * 
 * Description: Drawing a tree with green leaves. Inspired by many
 * recursive trees pictures online. It would take a bit more time if n is
 * over 12. 
 *************************************************************************/

public class Tree {
  // Drawing the Tree Trunk
  private static void trunk(double x0, double y0, double length, 
                            double width, double angle) { 
    StdDraw.setPenColor(165, 42, 42); // color brown 
    double[] x = new double [4];
    double[] y = new double [4];
    x[0] =  x0 - width/2*Math.cos(angle);
    x[1] =  x0 + width/2*Math.cos(angle);
    x[2] =  x0 + width/2*Math.cos(angle) - length*Math.sin(angle);
    x[3] =  x0 - width/2*Math.cos(angle) - length*Math.sin(angle); 
    y[0] =  y0 - width/2*Math.sin(angle);
    y[1] =  y0 + width/2*Math.sin(angle);
    y[2] =  y0 + width/2*Math.sin(angle) + length*Math.cos(angle);
    y[3] =  y0 - width/2*Math.sin(angle) + length*Math.cos(angle);
    StdDraw.filledPolygon(x, y);
   }  
  // Drawing a leaf
   private static void leaf(double x0, double y0, double length, 
                            double width, double angle) { 
     // The first central part of the leaf
      double  tx1 =  x0 - width/2*Math.cos(angle) - length*Math.sin(angle);
      double  tx3 =  x0 + width/2*Math.cos(angle) - length*Math.sin(angle);
      double  xt = (tx1 + tx3)/2;
      double  ty1 =  y0 - width/2*Math.sin(angle) + length*Math.cos(angle);
      double  ty3 =  y0 + width/2*Math.sin(angle) + length*Math.cos(angle);
      double  yt = (ty1 + ty3)/2;
     double[] ox = new double [4];
     double[] oy = new double [4];
     ox[0] = xt; 
     ox[1] = xt - 2*length*Math.sin(angle - Math.PI/6)/(2*Math.cos(Math.PI/6));
     ox[2] = xt - 2*length*Math.sin(angle);
     ox[3] = ox[2] + ox[0] - ox[1];
     oy[0] = yt;
     oy[1] = yt + 2*length*Math.cos(angle - Math.PI/6)/(2*Math.cos(Math.PI/6));
     oy[2] = yt + 2*length*Math.cos(angle);
     oy[3] = oy[2] + oy[0] - oy[1];  
      StdDraw.setPenColor(0, 160, 90); // Color green
      StdDraw.filledPolygon(ox, oy);
      double[] cx = Transform2D.copy(ox);
      double[] cy = Transform2D.copy(oy);
    // The second and third part of the leaf using Transform2D
       Transform2D.rotate(cx, cy, -30.0);
       Transform2D.scale(cx, cy, 0.8);
       double deltax1 = ox[0] - cx[0];
       double deltay1 = oy[0] - cy[0];
       Transform2D.translate(cx, cy, deltax1, deltay1);
       StdDraw.filledPolygon(cx, cy);
       cx = Transform2D.copy(ox);
       cy = Transform2D.copy(oy);
       Transform2D.rotate(cx, cy, +30.0);
       Transform2D.scale(cx, cy, 0.8);
       double deltax2 = ox[0] - cx[0];
       double deltay2 = oy[0] - cy[0];
       Transform2D.translate(cx, cy, deltax2, deltay2);
       StdDraw.filledPolygon(cx, cy);   
  }  
  
   // Drawing trunks recursively and leaves when n = 1
    private static void drawTrunk(int n, double x0, double y0, double length,
                                  double width, double angle) {
      if (n == 0)  return; 
      if (n == 1) leaf(x0, y0, length, width, angle); // draw leaves
      trunk(x0, y0, length, width, angle);
      double  tx1 =  x0 - width/2*Math.cos(angle) - length*Math.sin(angle);
      double  tx3 =  x0 + width/2*Math.cos(angle) - length*Math.sin(angle);
      double  xt = (tx1 + tx3)/2;
      double  ty1 =  y0 - width/2*Math.sin(angle) + length*Math.cos(angle);
      double  ty3 =  y0 + width/2*Math.sin(angle) + length*Math.cos(angle);
      double  yt = (ty1 + ty3)/2;
      // Recursively draw trunks 
    drawTrunk(n-1, xt, yt, 3/4.0 * length, 3/4.0 * width, angle + Math.PI/4);
    drawTrunk(n-1, xt, yt, 2/3.0 * length, 2/3.0 * width, angle - Math.PI/3);
    }
    
    public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);    
    double x0     = 0.5;
    double y0     = 0.05; 
    double length = 0.18;
    double width  = 0.03;
    double angle  = 0.0;
    drawTrunk(n, x0,  y0,  length,  width,  angle); 
    }
}
