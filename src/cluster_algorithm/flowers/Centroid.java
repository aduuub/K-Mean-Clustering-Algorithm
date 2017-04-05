package cluster_algorithm.flowers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 5/04/17.
 */
public class Centroid extends Iris{

    private List<TestIris> assignedIris;

    public Centroid(double sepalLength, double sepalWidth, double petalLength, double petalWidth) {
        super(sepalLength, sepalWidth, petalLength, petalWidth);
        assignedIris = new ArrayList<>();
    }

    public void addIris(TestIris iris){
        assignedIris.add(iris);
    }

    public void clearList(){
        assignedIris.clear();
    }

    public List<TestIris> getAssignedIris() {
        return assignedIris;
    }
}
