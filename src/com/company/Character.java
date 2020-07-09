package com.company;
import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Character {
    ArrayList<String> weapons = new ArrayList<>();
    ArrayList<String> spells = new ArrayList<>();
    ArrayList<String> potions = new ArrayList<>();

    int level;
    int health;
    int attack;
    int armor;
    int gold;
    int exp;
    int expReq;
    int maxHealth;
    int potionCount;
    int maxArmor;
    String equipped;

    public Character(int level, int health, int attack, int armor, int gold, int exp, int expReq, int maxHealth, int potionCount, int maxArmor, String equipped){
        this.level = level;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        this.gold = gold;
        this.exp = exp;
        this.expReq = expReq;
        this.maxHealth = maxHealth;
        this.potionCount = potionCount;
        this.maxArmor = maxArmor;
        this.equipped = equipped;
        weapons.add(equipped);
    }

    public void inventory(Character character){
        System.out.println("------ Inventory ------");
        System.out.println("GOLD: " + this.gold);
        System.out.println("ARMOR: " + this.armor);
        System.out.println("POTIONS: " + this.potionCount);
        System.out.println("---------------");
        System.out.println("WEAPONS: ");
        for(String wep : character.weapons){
            System.out.println(wep);
        }
        System.out.println("---------------");
        System.out.println("SPELLS: ");
        for(String spell : character.spells){
            System.out.println(spell);
        }
    }

    public void stats(){
        System.out.println("------ Stats ------");
        System.out.println("TOTAL EXP: " + this.exp);
        System.out.println("LEVEL: " + this.level);
        System.out.println("HEALTH: " + this.health);
        System.out.println("ATTACK: " + this.attack);
    }
}
