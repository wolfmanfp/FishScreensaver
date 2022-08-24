package hu.wolfman.fishscreensaver.util;

public class MathUtil {
    
    public static int random (int lowerLimit, int upperLimit) {
        return (int) (Math.random() * (upperLimit - lowerLimit) + lowerLimit);
    }
    
    public static int random (int limit) {
        return (int) MathUtil.random(0, limit);
    }
    
    public static long random (long lowerLimit, long upperLimit) {
        return (long) (Math.random() * (upperLimit - lowerLimit) + lowerLimit);
    }
    
    public static long random (long limit) {
        return (long) (MathUtil.random(0, limit));
    }
    
    public static double distance (int x1, int x2, int y1, int y2) {
        return Math.sqrt(square(x1 - x2) + square(y1 - y2));
    }
    
    public static double square (double x) {
        return Math.pow(x, 2);
    }
    
}
