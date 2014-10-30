/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vectortypes;

/**
 *
 * @author frank
 */
public class FVector {

    public float x;
    public float y;
    public float z;
    private static boolean string2D = false;
    private static final FVector zeroVector = new FVector(0, 0, 0);

    public FVector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public FVector(float x, float y) {
        this(x, y, 0);
    }

    public FVector(float x) {
        this(x, 0, 0);
    }

    public FVector() {
        this(0, 0, 0);
    }

    public FVector(FVector v) {
        this(v.x, v.y, v.z);
    }

    // Add given FVector to this FVector object
    public void add(FVector v) {
        x += v.x;
        y += v.y;
        z += v.z;
    }

    // Return a new FVector object that is the sum of the given FVector objects
    public static FVector getSum(FVector v1, FVector v2) {
        return new FVector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    // subtract given FVector to this FVector object
    public void subtract(FVector v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
    }

    public void sub(FVector v) {
        subtract(v);
    }

    // Return a new FVector object that is the difference of the given FVector objects
    public static FVector getDifference(FVector v1, FVector v2) {
        return new FVector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static FVector getDiff(FVector v1, FVector v2) {
        return getDifference(v1, v2);
    }

    // Scalar multiplication
    public void multiply(float n) {
        x *= n;
        y *= n;
        z *= n;
    }

    public void mult(float n) {
        multiply(n);
    }

    // Return a new FVector object that is the scalar product of the given FVector object and a float scalar
    public static DVector getProduct(FVector v, float n) {
        return new DVector(v.x * n, v.y * n, v.z * n);
    }

    public static DVector getProd(FVector v, float n) {
        return getProduct(v, n);
    }

    // Scalar division
    public void divide(float n) throws IllegalArgumentException {
        if (n == 0.0) {
            throw new IllegalArgumentException();
        }
        x /= n;
        y /= n;
        z /= n;
    }

    public void div(float n) throws IllegalArgumentException {
        if (n == 0.0) {
            throw new IllegalArgumentException();
        }
        divide(n);
    }

    // Return new FVector object that is the result of scalar division
    public static FVector getDivision(FVector v, float n) throws IllegalArgumentException {
        if (n == 0.0) {
            throw new IllegalArgumentException();
        }
        return new FVector(v.x / n, v.y / n, v.z / n);
    }

    public static FVector getDiv(FVector v, float n) throws IllegalArgumentException {
        if (n == 0.0) {
            throw new IllegalArgumentException();
        }
        return getDivision(v, n);
    }

    public void setMagnitude(float len) {
        normalize();
        mult(len);
    }

    public void setMag(float len) {
        setMagnitude(len);
    }

    // Return magnitude of this FVector
    public float getMagnitude() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float getMag() {
        return getMagnitude();
    }

    // Return magnitude of the given FVector
    public static float getMagnitude(FVector v) {
        return (float) Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }

    public static float getMag(FVector v) {
        return getMagnitude(v);
    }

    // Normalize this vector
    public void normalize() throws ArithmeticException {
        float magnitude = getMag();
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

    public static FVector getNormalize(FVector v) {
        float magnitude = getMag(v);
        return new FVector(v.x /= magnitude, v.y /= magnitude, v.z /= magnitude);
    }

    public static FVector getNorm(FVector v) {
        return getNormalize(v);
    }

    public void limit(float max) {
        if (getMag() > max) {
            normalize();
            mult(max);
        }
    }

    public float getHeading2D() {
        float angle = (float) Math.atan2(-y, x);
        return -1 * angle;
    }
    // Set the value of this vector

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(float x, float y) {
        set(x, y, 0.0f);
    }

    public void set(float x) {
        set(x, 0.0f, 0.0f);
    }

    public void set(FVector v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public static void setString2D(boolean state) {
        string2D = state;
    }

    @Override
    public FVector clone() {
        return new FVector(x, y, z);
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
