package main.objects;

import main.engine.Engine;
import main.objects.objectOnMap.ObjectOnMap;

import java.util.HashMap;

public class Map <T extends ObjectOnMap>  {
    private char [][] map;
    private int numberOfRowsMap;
    private int numberOfColumnsMap;
    private ListLocationAndObjectOnMap listLocationAndObjectOnMap;
    public Map(ListLocationAndObjectOnMap listLocationAndObjectOnMap) {
        this.numberOfRowsMap = Engine.getEngine().numberOfRowsMap;
        this.numberOfColumnsMap = Engine.getEngine().numberOfColumnsMap;
        this.listLocationAndObjectOnMap = listLocationAndObjectOnMap;
        this.map = new char [numberOfRowsMap][numberOfColumnsMap];
    }
    public void generateMap () {

        for (int i=0; i<numberOfRowsMap; i++) {
            for (int j=0; j<numberOfColumnsMap; j++) {
                T object = (T) listLocationAndObjectOnMap.hasObjectAtLocation(new int [] {i,j});
                if (object == null) {
                    map[i][j] = '.';
                } else {
                    map[i][j] = object.charOnMap;
                }
            }
        }
    }
    //if all be bad =/
    public void renderMap () {
        for (int i=0; i<numberOfRowsMap; i++) {
            for (int j=0; j<numberOfColumnsMap; j++) {
                System.out.print(map[i][j]);
                if (j != numberOfColumnsMap-1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public char[][] getMap() {
        return map;
    }
}
