package com.company;
import java.util.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BattlePhase {
    public void start(Character character){
        Random rand = new Random();
        String[] enemies = {"Skeleton", "Zombie", "Ghoul", "Fiend"};

        String enemy = enemies[rand.nextInt(enemies.length)]; // Picks a random item from the enemies array

        if(enemy.equals("Skeleton")){
            Skeleton skeleton = new Skeleton(20,20 ,10,150, 30);
            System.out.println("You are now in battle with a " + enemy);
            fight(skeleton, character);
        }else if(enemy.equals("Ghoul")){
            Ghoul ghoul = new Ghoul(15, 15,12,200, 45);
            System.out.println("You are now in battle with a " + enemy);
            fight(ghoul, character);
        }else if(enemy.equals("Zombie")){
            Zombie zombie = new Zombie(30,30,8,250, 55);
            System.out.println("You are now in battle with a " + enemy);
            fight(zombie, character);
        }else if(enemy.equals("Fiend")){
            Ghoul ghoul = new Ghoul(35,35,20,400, 90);
            System.out.println("You are now in battle with a " + enemy);
            fight(ghoul, character);
        }
        System.out.println("You are now in battle!");
    }

    public void fight(Enemy enemy, Character character){
        boolean running = true;
        boolean valid = true;
        Scanner scanner = new Scanner(System.in);
        int damageDealt;
        int damageTaken;

        while(running){
            System.out.printf("Your Armor: %d/%d | Your HP: %d/%d \t Enemy HP: %d/%d\n", character.armor, character.maxArmor, character.health,character.maxHealth,enemy.health,enemy.maxHealth);
            System.out.println("1. ATTACK");
            System.out.println("2. FLEE");
            System.out.println("3. POTION");
            System.out.println("4. SPELLS");
            System.out.print("> ");
            String input = scanner.nextLine();

            if(input.equals("1")){
                damageDealt = getValue(1, character.attack);
                enemy.health -= damageDealt;
                System.out.println("You have dealt " + damageDealt + " damage to the enemy!");
                System.out.println("-----------------");

                if(character.armor <= 0) {
                    // If the user does not have armor
                    damageTaken = getValue(1, enemy.attack);
                    character.health -= damageTaken;
                    System.out.println("The enemy has dealt " + damageTaken + " damage to you!");
                }else{
                    // If the user has armor
                    damageTaken = getValue(1, enemy.attack);
                    character.armor -= damageTaken;
                    System.out.println("The enemy has dealt " + damageTaken + " damage to you!");
                }
            }else if(input.equals("2")){
                System.out.println("You have fled the battle!");
                running = false;
            }else if(input.equals("3")){
                if(character.potionCount > 0){
                    System.out.println("You have used a potion! It increased your health by 50 HP!");
                    character.health += 50;
                    character.potionCount -= 1;
                }else{
                    System.out.println("You don't have any potions!");
                }
            }else if(input.equals("4")){
                if(character.spells.size() == 0){
                    System.out.println("You have no spells!");
                }else {
                    System.out.println("You have the following spells: ");
                    for(String s : character.spells){
                        System.out.println(s);
                    }
                    System.out.println("Which one would you like to use?");
                    System.out.print("> ");
                    String choice = scanner.nextLine();

                    while(valid) {
                        if (character.spells.contains(choice)) {
                            System.out.println("You have used a(n) " + choice + "!");
                            if (choice.equals("Fire Blast")) {
                                System.out.println("You dealt 80 damage to the enemy!");
                                enemy.health -= 80;
                                character.spells.remove("Fire Blast");
                                valid = false;
                            } else if (choice.equals("Ice Storm")) {
                                System.out.println("You dealt 85 damage to the enemy!");
                                enemy.health -= 85;
                                character.spells.remove("Ice Storm");
                                valid = false;
                            } else if (choice.equals("Earthquake")) {
                                System.out.println("You dealt 120 damage to the enemy!");
                                enemy.health -= 120;
                                character.spells.remove("Earthquake");
                                valid = false;
                            } else {
                                System.out.println("Come again?");
                            }
                        }
                    }
                }
            }

            if(enemy.health <= 0){
                System.out.println("You have won the battle!");
                getRewards(enemy, character);
                enemy.health = enemy.maxHealth;
                running = false;
            }

            if(character.health <= 0){
                System.out.println("You have died...");
                running = false;
            }
        }

        Game game = new Game();
        game.mainMenu(character);
    }

    public int getValue(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max-min) + 1) + min;       // Gets a number between 1 and max
    }

    public void getRewards(Enemy enemy, Character character){
        int goldDropped = getValue(50, enemy.goldDrop);
        character.gold += goldDropped;

        int expDropped = getValue(30, enemy.expDrop);
        character.exp += expDropped;
        levelCheck(character.exp, character, enemy);
    }

    public void levelCheck(int totalExp, Character character, Enemy enemy){
        // Level up buffs
        if(totalExp >= character.expReq){
            character.level += 1;
            System.out.println("Level Up! You are now level: " + character.level);
            character.expReq += 100;
            character.maxHealth += 10;
            character.attack += 15;

            // Enemy buffs
            enemy.maxHealth += 15;
            enemy.expDrop += 40;
            enemy.goldDrop += 80;
            enemy.attack += 10;
        }

        // Level stats
        System.out.println("Current Level: " + character.level);
        System.out.println("EXP Needed: " + character.exp + "/" + character.expReq);
    }

}
