/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

import java.util.Random;

/**
 *
 * @author frank
 */
public class PTools {

    private static Random randomGen = new Random();

    public static int random(int n) {
        return randomGen.nextInt(n);
    }

    public static double random(double n) {
        return randomGen.nextDouble() * n;
    }

    public static float random(float n) {
        return randomGen.nextFloat() * n;
    }

    public static double random(double first, double last) {
        return first + (last - first) * randomGen.nextDouble();
    }

    public static float random(float first, float last) {
        return first + (last - first) * randomGen.nextFloat();
    }

    public static double map(double value1, double start1, double stop1, double start2, double stop2) {
        return ((value1 - start1) * (stop2 - start2) / (stop1 - start1) - start2);
    }

    public static float map(float value1, float start1, float stop1, float start2, float stop2) {
        return ((value1 - start1) * (stop2 - start2) / (stop1 - start1) - start2);
    }

    public static double constrain(double value, double minValue, double maxValue) {
        if (value < minValue) {
            return minValue;
        } else if (value > maxValue) {
            return maxValue;
        } else {
            return value;
        }
    }

    public static float constrain(float value, float minValue, float maxValue) {
        if (value < minValue) {
            return minValue;
        } else if (value > maxValue) {
            return maxValue;
        } else {
            return value;
        }
    }
}
