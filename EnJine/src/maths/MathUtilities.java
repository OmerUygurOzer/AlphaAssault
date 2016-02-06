package maths;

/**
 * Created by Omer on 2/5/2016.
 */
public class MathUtilities {
    public static int getDistance(float x1,float y1,float x2,float y2){
        return (int)(Math.sqrt(MathUtilities.square(x1-x2)+MathUtilities.square(y1-y2)));
    }
    public static double square(double x){return x*x;}
}
