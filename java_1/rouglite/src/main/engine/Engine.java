package main.engine;

import main.objects.*;
import main.objects.objectOnMap.*;
import main.objects.objectOnMap.person.Enemy.PatrolEnemy;
import main.objects.objectOnMap.person.Enemy.PursuingEnemy;
import main.objects.objectOnMap.person.Hero;
import main.objects.objectOnMap.person.Spike;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Engine {
    private static Engine engine;
    //default settings map
    public int numberOfRowsMap = 30;
    public int numberOfColumnsMap = 50;
    Scanner scanner = new Scanner(System.in);
    public Random random = new Random();
    public char inputChar;
    public static boolean inputProcessed = false;
    public boolean inventoryVisible = false;
    private Engine() {
    }
    public static Engine getEngine() {
        if (engine == null) {
            engine = new Engine();
        }
        return engine;
    }
    public void run() {
        //start
        ListLocationAndObjectOnMap listLocationAndObjectOnMap = new ListLocationAndObjectOnMap();

        Map map = new Map(listLocationAndObjectOnMap);

        Inventory inventory = new Inventory();
        inventory.setVisible(false);

        GeneratorLoot generatorLoot = new GeneratorLoot(listLocationAndObjectOnMap);

        Hero hero = new Hero("Heroin", listLocationAndObjectOnMap, inventory, generatorLoot);
        hero.addOnMap();

        Wall wall = new Wall(listLocationAndObjectOnMap);
        wall.addOnMap();

        Coin coin = new Coin(listLocationAndObjectOnMap);
        coin.addOnMap();

        Teleport teleport = new Teleport(listLocationAndObjectOnMap);
        teleport.addOnMap();

        Spike spike = new Spike(listLocationAndObjectOnMap);
        spike.addOnMap();

        Chest chest = new Chest(listLocationAndObjectOnMap, generatorLoot, coin);
        chest.addOnMap();

        PatrolEnemy patrolEnemy1 = new PatrolEnemy(listLocationAndObjectOnMap);
        patrolEnemy1.addOnMap();
        PursuingEnemy pursuingEnemy1 = new PursuingEnemy(listLocationAndObjectOnMap);
        pursuingEnemy1.addOnMap();

        PatrolEnemy patrolEnemy2 = new PatrolEnemy(listLocationAndObjectOnMap);
        patrolEnemy2.addOnMap();
        PursuingEnemy pursuingEnemy2 = new PursuingEnemy(listLocationAndObjectOnMap);
        pursuingEnemy2.addOnMap();

        PatrolEnemy patrolEnemy3 = new PatrolEnemy(listLocationAndObjectOnMap);
        patrolEnemy3.addOnMap();
        PursuingEnemy pursuingEnemy3 = new PursuingEnemy(listLocationAndObjectOnMap);
        pursuingEnemy3.addOnMap();

        PatrolEnemy patrolEnemy4 = new PatrolEnemy(listLocationAndObjectOnMap);
        patrolEnemy4.addOnMap();
        PursuingEnemy pursuingEnemy4 = new PursuingEnemy(listLocationAndObjectOnMap);
        pursuingEnemy4.addOnMap();

        PatrolEnemy patrolEnemy5 = new PatrolEnemy(listLocationAndObjectOnMap);
        patrolEnemy5.addOnMap();
        PursuingEnemy pursuingEnemy5 = new PursuingEnemy(listLocationAndObjectOnMap);
        pursuingEnemy5.addOnMap();

        map.generateMap();

        JFrame frame = new JFrame("ArrowKeysExample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrowKeys arrowKeys = new ArrowKeys(map);
        frame.getContentPane().add(arrowKeys);
        HeroPanel heroPanel = new HeroPanel(hero);
        heroPanel.updateHeroPanel();
        frame.getContentPane().add(heroPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(inventory, BorderLayout.EAST);
        frame.pack();
        frame.setVisible(true);
        arrowKeys.requestFocusInWindow();

        //run
        heroPanel.updateHeroPanel();

        while (true) {
            map.generateMap();
            inputChar = '#';
            //wait inputChar
            while (!inputProcessed) {
                synchronized (Engine.getEngine()) {
                    try {
                        Engine.getEngine().wait(); // ��������, ���� inputProcessed �� ������ true
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            inputProcessed = false;

            hero.action(inputChar);
            map.generateMap();
            heroPanel.updateHeroPanel();

            if (inventoryVisible) {
                continue;
            }

            patrolEnemy1.action();
            pursuingEnemy1.action();

            patrolEnemy2.action();
            pursuingEnemy2.action();

            patrolEnemy3.action();
            pursuingEnemy3.action();

            patrolEnemy4.action();
            pursuingEnemy4.action();

            patrolEnemy5.action();
            pursuingEnemy5.action();

            map.generateMap();
        }
    }
}