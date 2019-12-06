package main.day06;

import java.util.ArrayList;
import java.util.List;

public class OrbitMap {
    String center;
    List<OrbitMap> orbits = new ArrayList<>();

    int orbitSteps = 0;

    public OrbitMap(String input, int orbitSteps) {
        this.center = input;
        this.orbitSteps = orbitSteps;
    }

    public boolean addOrbiter(String mass, String orbiter) {
        if (center.equals(mass)) {
            orbits.add(new OrbitMap(orbiter, this.orbitSteps+1));
            return true;
        } else {
            for (OrbitMap orbit : orbits) {
                if (orbit.addOrbiter(mass, orbiter)) {
                    return true;
                }
            }
            return false;
        }
    }

    public int cntOrbits() {
        return this.orbitSteps + orbits.stream().map(OrbitMap::cntOrbits).mapToInt(Integer::intValue).sum();
    }

    public boolean getPathTo(List<String> path, String destination) {
        if (this.center.equals(destination)) {
            path.add(center);
            return true;
        } else {
            for (OrbitMap orbit : orbits) {
                if (orbit.getPathTo(path, destination)) {
                    path.add(center);
                    return true;
                }
            }
            return false;
        }
    }

}
