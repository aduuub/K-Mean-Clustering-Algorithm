package cluster_algorithm;

import cluster_algorithm.flowers.TestIris;
import java.util.List;

/**
 * Entry point to the program. Running this reads in the files and starts the learning, then classification. This is a
 * controller for other classes.
 */
public class Main {

    public final int k;

    public Main(List<TestIris> iris, int k) {
        this.k = k;

        // Run classification
        Classification classifier = new Classification(k);
        classifier.start(iris);
}

    /**
     * Ruuuuuun the program
     * @param args
     */
    public static void main(String args[]){
        if(args.length != 2){
            System.out.println("Usage: Run with arguments: fileName kValue");
        }
        String fileName = args[0];
        int k = Integer.valueOf(args[1]);

        // Parse the data
        List<TestIris> testData = Parser.parseIris(fileName);

        // Start the program
        new Main(testData, k);
    }
}
