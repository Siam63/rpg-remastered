package com.company;
public class Enemy {
    int health;
    int maxHealth;
    int attack;
    int goldDrop;
    int expDrop;

    public Enemy(int health, int maxHealth, int attack, int goldDrop, int expDrop){
        this.health = health;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.goldDrop = goldDrop;
        this.expDrop = expDrop;
    }
}
