package main.objects.objectOnMap;

import main.engine.Engine;
import main.objects.ListObjectOnMap;

import java.util.HashSet;
import java.util.Random;

public class Coin extends Object{
    private int numberOfCoin = 20;

    public Coin (ListObjectOnMap listObjectOnMap) {
        this.charOnMap = '$';
        this.numberOfRowsMap = Engine.getEngine().numberOfRowsMap;
        this.numberOfColumnsMap = Engine.getEngine().numberOfColumnsMap;
        this.locationList = new HashSet<int[]>();
        this.listObjectOnMap = listObjectOnMap;
    }
    public void addOnMap() {
        Random random = new Random();
        for (int i=0; i<numberOfCoin; i++) {
            int[] newLocation;
            do {
                newLocation = new int[] {random.nextInt(numberOfRowsMap), random.nextInt(numberOfColumnsMap)};
            } while (listObjectOnMap.hasObjectAtLocation(newLocation) != '.');
            locationList.add(newLocation);
            listObjectOnMap.addObjectToList(charOnMap, locationList);
        }
    }
}
