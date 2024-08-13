package org.example;

public class Values {
    Integer distance;
    String size;
    String fragility;
    Integer workload;

    public Integer getDistance() {
        return distance;
    }

    public String getSize() {
        return size;
    }

    public String getFragility() {
        return fragility;
    }

    public Integer getWorkload() {
        return workload;
    }

    public Values(){}

    public Values(Integer distance, String size, String fragility, Integer workload) {
        this.distance = distance;
        this.size = size;
        this.fragility = fragility;
        this.workload = workload;
    }

    @Override
    public String toString() {
        return "Values{" +
                "distance=" + distance +
                ", size='" + size + '\'' +
                ", fragility='" + fragility + '\'' +
                ", workload=" + workload +
                '}';
    }
}
