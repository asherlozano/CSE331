/*
 * Copyright (C) 2022 Kevin Zatloukal and James Wilcox.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Autumn Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;
import pathfinder.datastructures.Point;
import pathfinder.datastructures.Path;
import graph.*;

public class CampusMap implements ModelAPI {
    Map<String, String> buildingName;
    Graph<Point, Double> campusMap;
    List<CampusBuilding> campusBuildings;
    List<CampusPath> campusPaths;

    public CampusMap(){
        campusMap = new Graph<>();
        buildingName = new HashMap<>();
        campusBuildings = CampusPathsParser.parseCampusBuildings("campus_buildings.csv");
        campusPaths = CampusPathsParser.parseCampusPaths("campus_paths.csv");
        for(CampusBuilding b : campusBuildings){
            buildingName.put(b.getShortName(), b.getLongName());
        }

    }

    @Override
    public boolean shortNameExists(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        return buildingName.containsKey(shortName);
    }

    @Override
    public String longNameForShort(String shortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        return buildingName.get(shortName);
    }

    @Override
    public Map<String, String> buildingNames() {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        return buildingName;
    }

    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        // TODO: Implement this method exactly as it is specified in ModelAPI
        double x1 = 0.0;
        double x2 = 0.0;
        double y1 = 0.0;
        double y2 = 0.0;
        if(shortNameExists(startShortName) && shortNameExists(endShortName)){
            for(CampusBuilding build : campusBuildings){
                if(build.getShortName().equals(startShortName)){
                    x1 = build.getX();
                    y1 = build.getY();
                }
                if(build.getShortName().equals(endShortName)){
                    x2 = build.getX();
                    y2 = build.getY();
                }
                Point a = new Point(x1, y1);
                Point b = new Point(x2, y2);
                return Dijkstra.findPath(campusMap, a, b);
            }
        }
        return null;
    }

}
