package cluster_algorithm;

import cluster_algorithm.flowers.Iris;
import cluster_algorithm.flowers.TestIris;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 20/03/17.
 */
public class Parser {

    public static List<TestIris> parseIris(String fileName) {
        List<TestIris> flowers = new ArrayList<>();

        try {
            // Set up file reader
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // Read value
            String val = reader.readLine();

            // Create flower from data line
            while (!val.isEmpty()) {
                String[] vals = val.split(" ");
                double sepalLength = Double.valueOf(vals[0]);
                double sepalWidth = Double.valueOf(vals[2]);
                double petalLength = Double.valueOf(vals[4]);
                double petalWidth = Double.valueOf(vals[6]);
                String flowerType = vals[8];
                TestIris flower = new TestIris(sepalLength, sepalWidth, petalLength, petalWidth, flowerType);
                flowers.add(flower);
                val = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flowers;
    }
}