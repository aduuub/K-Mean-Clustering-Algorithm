package cluster_algorithm.flowers;

/**
 * Created by Adam on 5/04/17.
 */
public class TestIris extends Iris {
    private String irisType;


    public TestIris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String irisType) {
        super(sepalLength, sepalWidth, petalLength, petalWidth);
        this.irisType = irisType;
    }

    public String getIrisType() {
        return irisType;
    }
}
