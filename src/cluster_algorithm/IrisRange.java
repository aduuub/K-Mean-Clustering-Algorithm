package cluster_algorithm;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This stores the ranges for a collection of Iris. It has a range for each of its fields which was calculated using
 * max - min.
 *
 * @author Adam
 */
public class IrisRange {

    private double minSl, maxSl, minSw, maxSw, minPl, maxPl, minPw, maxPw;

    public IrisRange(double minSl, double maxSl, double minSw, double maxSw, double minPl, double maxPl, double minPw, double maxPw) {
        this.minSl = minSl;
        this.maxSl = maxSl;
        this.minSw = minSw;
        this.maxSw = maxSw;
        this.minPl = minPl;
        this.maxPl = maxPl;
        this.minPw = minPw;
        this.maxPw = maxPw;
    }

    public double getRandomSepalLength(){
        return ThreadLocalRandom.current().nextInt((int)minSl, (int)maxSl + 1);
    }

    public double getRandomSepalWidth(){
        return ThreadLocalRandom.current().nextInt((int)minSw, (int)maxSw + 1);
    }

    public double getRandomPetalLength(){
        return ThreadLocalRandom.current().nextInt((int)minPl, (int)maxPl + 1);
    }

    public double getRandomPetalWidth(){
        return ThreadLocalRandom.current().nextInt((int)minPw, (int)maxPw + 1);
    }
}
