/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vectortypes;

/**
 *
 * @author frank
 */
public class DVector {

    public double x;
    public double y;
    public double z;
    private static boolean string2D = false;
    private static final DVector zeroVector = new DVector(0, 0, 0);

    public DVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public DVector(double x, double y) {
        this(x, y, 0);
    }

    public DVector(double x) {
        this(x, 0, 0);
    }

    public DVector() {
        this(0, 0, 0);
    }

    public DVector(DVector v) {
        this(v.x, v.y, v.z);
    }

    // Add given DVector to this DVector object
    public void add(DVector v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    // Return a new DVector object that is the sum of the given DVector objects
    public static DVector getSum(DVector v1, DVector v2) {
        return new DVector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    // subtract given DVector to this DVector object
    public void subtract(DVector v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }

    public void sub(DVector v) {
        subtract(v);
    }

    // Return a new DVector object that is the difference of the given DVector objects
    public static DVector getDifference(DVector v1, DVector v2) {
        return new DVector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static DVector getDiff(DVector v1, DVector v2) {
        return getDifference(v1, v2);
    }

    // Scalar multiplication
    public void multiply(double n) {
        x *= n;
        y *= n;
        z *= n;
    }

    public void mult(double n) {
        multiply(n);
    }

    // Return a new DVector object that is the scalar product of the given DVector object and a double scalar
    public static DVector getProduct(DVector v, double n) {
        return new DVector(v.x * n, v.y * n, v.z * n);
    }

    public static DVector getProd(DVector v, double n) {
        return getProduct(v, n);
    }

    // Scalar division
    public void divide(double n) throws IllegalArgumentException {
        if (n == 0.0) {
            throw new IllegalArgumentException();
        }
        x /= n;
        y /= n;
        z /= n;
    }

    public void div(double n) throws IllegalArgumentException {
        if (n == 0.0) {
            throw new IllegalArgumentException();
        }
        divide(n);
    }

    // Return new DVector object that is the result of scalar division
    public static DVector getDivision(DVector v, double n) throws IllegalArgumentException {
        if (n == 0.0) {
            throw new IllegalArgumentException();
        }
        return new DVector(v.x / n, v.y / n, v.z / n);
    }

    public static DVector getDiv(DVector v, double n) throws IllegalArgumentException {
        if (n == 0.0) {
            throw new IllegalArgumentException();
        }
        return getDivision(v, n);
    }

    public void setMagnitude(double len) {
        normalize();
        mult(len);
    }

    public void setMag(double len) {
        setMagnitude(len);
    }

    // Return magnitude of this DVector
    public double getMagnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double getMag() {
        return getMagnitude();
    }

    // Return magnitude of the given DVector
    public static double getMagnitude(DVector v) {
        return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }

    public static double getMag(DVector v) {
        return getMagnitude(v);
    }

    public static double getDistance(DVector v1, DVector v2) {
        DVector distanceVector = DVector.getDiff(v2, v1);
        return distanceVector.getMag();
    }

    public static double getDist(DVector v1, DVector v2) {
        return getDistance(v1, v2);
    }

    // Normalize this vector
    public void normalize() throws ArithmeticException {
        double magnitude = getMag();
        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
        if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z)) {
            throw new ArithmeticException();
        }
    }

    public void norm() {
        normalize();
    }

    public static DVector getNormalize(DVector v) {
        double magnitude = getMag(v);
        return new DVector(v.x /= magnitude, v.y /= magnitude, v.z /= magnitude);
    }

    public static DVector getNorm(DVector v) {
        return getNormalize(v);
    }

    public void limit(double max) {
        if (getMag() > max) {
            normalize();
            mult(max);
        }
    }

    public double getHeading2D() {
        double angle = (double) Math.atan2(-y, x);
        return -1 * angle;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(double x, double y) {
        set(x, y, 0.0);
    }

    public void set(double x) {
        set(x, 0.0, 0.0);
    }

    public void set(DVector v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public static void setString2D(boolean state) {
        string2D = state;
    }

    @Override
    public DVector clone() {
        return new DVector(x, y, z);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(x);
        sb.append(",");
        sb.append(y);
        if (!string2D) {
            sb.append(",");
            sb.append(z);
        }
        sb.append(")");

        return sb.toString();
    }
}
