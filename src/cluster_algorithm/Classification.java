package cluster_algorithm;


import cluster_algorithm.flowers.Centroid;
import cluster_algorithm.flowers.Iris;
import cluster_algorithm.flowers.TestIris;

import java.util.*;

/**
 * This classifies Iris's class based on previous irisList learnt.
 *
 * @author Adam
 */
public class Classification {

    public final int MAX_ITERATIONS = 1000;

    private List<TestIris> irisList;
    private List<Centroid> centroids;
    private IrisRange range;
    private int k;

    public Classification(int k) {
        this.k = k;
    }


    /**
     * Learn a list of Iris List, and set the new max/min range.
     *
     * @param irisToLearn
     */
    public void start(List<TestIris> irisToLearn) {
        this.irisList = irisToLearn;
        determineMaxAndMinRanges();
        createCentroids();
        centraliseCentroids();
    }


    /**
     * Create a list of K centroids, and add K centroids with random properties to it
     */
    private void createCentroids() {
        this.centroids = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            Centroid centroid = new Centroid(range.getRandomSepalLength(), range.getRandomSepalWidth(), range.getRandomPetalLength(),
                    range.getRandomPetalWidth());
            centroids.add(centroid);
        }
    }


    /**
     * Perform the algorithm!
     * For each Iris find the closest centroid, and add the Iris to the centroid. For all the Iris associated with the
     * centroid, calculate the mean properties and set the centroid to have those calculated properties. If no Iris associated
     * with the centroid then set it to random properties. Keep repeating until no change in centroids properties.
     */
    public void centraliseCentroids() {
        boolean changed;
        int count = 0;

        for (; count < MAX_ITERATIONS; count++) {
            changed = false;

            // Assign all iris to the closest centroid
            for (int i = 0; i < irisList.size(); i++) {
                TestIris iris = irisList.get(i);
                int centroidLocation = getClosestCentroid(iris);
                centroids.get(centroidLocation).addIris(iris);
            }

            // Find the average between all Iris belonging to the certain centroid
            for (Centroid centroid : centroids) {
                double totalSl = 0;
                double totalSw = 0;
                double totalPl = 0;
                double totalPw = 0;

                // Sum all the lengths
                List<TestIris> centroidIris = centroid.getAssignedIris();
                for (Iris iris : centroidIris) {
                    totalSl += iris.getSepalLength();
                    totalSw += iris.getSepalWidth();
                    totalPl += iris.getPetalLength();
                    totalPw += iris.getPetalWidth();
                }

                // Average all the lengths
                totalSl /= centroidIris.size();
                totalSw /= centroidIris.size();
                totalPl /= centroidIris.size();
                totalPw /= centroidIris.size();

                // Check if centroid has been moved
                if (centroid.getPetalLength() != totalPl || centroid.getSepalWidth() != totalSw ||
                        centroid.getPetalLength() != totalPl || centroid.getPetalWidth() != totalPw) {

                    changed = true;
                    centroid.setSepalLength(totalSl);
                    centroid.setSepalWidth(totalSw);
                    centroid.setPetalWidth(totalPw);
                    centroid.setPetalLength(totalPl);
                }
            }

            for (Centroid c : centroids) {
                int size = c.getAssignedIris().size();

                if (size == 0) {
                    c.setSepalLength(range.getRandomSepalLength());
                    c.setSepalWidth(range.getRandomSepalWidth());
                    c.setPetalLength(range.getRandomPetalLength());
                    c.setPetalWidth(range.getRandomPetalWidth());

                }
            }

            // Loop exit -> when converged
            if (!changed)
                break;

            // If not going to be the last iteration, clear the Centroids associated Iris
            if (count != MAX_ITERATIONS - 1)
                centroids.forEach(centroid -> centroid.clearList());
        }
        System.out.println("Finished after: " + count + " Iterations. \n");
        calculateCohesionAndPrintOutput();
    }


    /**
     * Calculates the cohesion of the Iris that were associated with each centroid.
     * Prints out the each centroid, its cohesion, most common class and all class types associated with it.
     */
    private void calculateCohesionAndPrintOutput() {
        int centroidCount = 1;
        for (Centroid c : centroids) {
            Map<String, Integer> votes = new HashMap<>(); // Class -> Frequency
            int sum = 0;

            // Loop over each iris associated with the centroid
            for (TestIris t : c.getAssignedIris()) {
                String label = t.getIrisType();
                sum++;

                // Add vote to the iris type
                if (votes.containsKey(label)) {
                    votes.put(label, votes.get(label) + 1);
                } else {
                    votes.put(label, 1);
                }
            }

            // Loop over votes and find max
            String maxType = "";
            Integer max = 0;
            for(String type : votes.keySet()){
                if(votes.get(type) > max){
                    maxType = type;
                    max = votes.get(type);
                }
            }

            double cohesion = max / (double) sum;
            System.out.println("\nCentroid " + centroidCount++ + " has " + Math.round(cohesion*100) + "% cohesion");
            System.out.println("The most common class is " + maxType);
            System.out.println("It contained: ");
            c.getAssignedIris().forEach(iris -> System.out.println(iris.getIrisType()));
        }
    }


    /**
     * Calculate the closest centroid to the Iris
     *
     * @param iris
     * @return
     */
    private int getClosestCentroid(Iris iris) {
        double minDistance = Double.MAX_VALUE;
        int closestIndex = -1;

        for (int i = 0; i < centroids.size(); i++) {
            double d = centroids.get(i).distanceToOtherIris(iris);

            if (d < minDistance) {
                // Found a distance is less than the minimum distance
                closestIndex = i;
                minDistance = d;
            }
        }
        return closestIndex;
    }


    /**
     * Determines the minimum and maximum ranges for the irises learnt. Sets the range
     */
    private void determineMaxAndMinRanges() {
        // Set min values
        double minSl = 0, minSw = 0, minPl = 0, minPw = 0;
        // Set max values
        double maxSl = 0, maxSw = 0, maxPl = 0, maxPw = 0;

        // Update values for each irisList
        for (Iris iris : this.irisList) {
            // Sepal length
            minSl = Math.min(iris.getSepalLength(), minSl);
            maxSl = Math.max(iris.getSepalLength(), maxSl);
            // Sepal width
            minSw = Math.min(iris.getSepalWidth(), minSw);
            maxSw = Math.max(iris.getSepalWidth(), maxSw);
            // Petal length
            minPl = Math.min(iris.getPetalLength(), minPl);
            maxPl = Math.max(iris.getPetalLength(), maxPl);
            // Petal width
            minPw = Math.min(iris.getPetalWidth(), minPw);
            maxPw = Math.max(iris.getPetalWidth(), maxPw);
        }

        range = new IrisRange(minSl, maxSl, minSw, maxSw, minPl, maxPl, minPw, maxPw);
    }
}
