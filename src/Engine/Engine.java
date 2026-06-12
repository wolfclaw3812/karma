package Engine;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Engine {
    private long seed;
    private Random randomGen;
    private ArrayList<MapGenerator> maps = new ArrayList<MapGenerator>();
    private int currentMap = 0;

    public Engine(){
        this("");
    }

    public Engine(String seed){
        long longSeed;
        try {
            longSeed = Long.parseLong(seed);
        } catch (Exception ignored){
            longSeed = (long) (Math.random()*(Math.pow(10.0,16)));
        }
        this.randomGen = new Random(longSeed);
        this.seed = longSeed;
    }

    public void generateMap(){
        MapGenerator mg = new MapGenerator(randomGen);
        mg.generateNodes();
        maps.add(mg);
    }

    public Map getCurrentMap(){
        return maps.get(currentMap).getMap();
    }

    public ArrayList<MapGenerator> getMaps() {
        return maps;
    }

    public Random getRandomGen() {
        return randomGen;
    }

    public void setRandomGen(Random randomGen) {
        this.randomGen = randomGen;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }


}
