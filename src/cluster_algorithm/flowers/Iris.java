package cluster_algorithm.flowers;

/**
 * Created by Adam on 21/03/17.
 */
public class Iris {

    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;

    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public double getPetalWidth() {
        return petalWidth;
    }


    /**
     * Find the eludian distance to the other Iris.
     * @param other
     * @return
     */
    public double distanceToOtherIris(Iris other){
        double sepalLengthDistance = Math.pow(sepalLength - other.sepalLength, 2);
        double sepalWidthDistance = Math.pow(sepalWidth - other.sepalWidth, 2);
        double petalLengthDistance = Math.pow(petalLength - other.petalLength, 2);
        double petalWidthDistance = Math.pow(petalWidth - other.petalWidth, 2);

        return Math.sqrt(sepalLengthDistance + sepalWidthDistance + petalLengthDistance + petalWidthDistance);
    }

    public void setSepalLength(double sepalLength) {
        this.sepalLength = sepalLength;
    }

    public void setSepalWidth(double sepalWidth) {
        this.sepalWidth = sepalWidth;
    }

    public void setPetalLength(double petalLength) {
        this.petalLength = petalLength;
    }

    public void setPetalWidth(double petalWidth) {
        this.petalWidth = petalWidth;
    }
}
