package com.company;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Game {
    public void mainMenu(Character character){
        // Actual game code
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running){
            try {
                System.out.println("------- Game Menu -------");
                System.out.println("1. Battle");
                System.out.println("2. Inventory");
                System.out.println("3. Stats");
                System.out.println("4. Exit Game");
                System.out.println("5. Heal");
                System.out.println("6. Potions / Armor Shop");
                System.out.println("7. Weapon Shop");
                System.out.println("8. Change Weapon");
                System.out.println("9. Spell Shop");
                System.out.println();
                System.out.print("> ");
                String input = scanner.nextLine();

                // Main Menu

                if (input.equals("1")) {
                    if(character.health <= 0){
                        System.out.println("You need to heal first!");
                    }else {
                        BattlePhase battlePhase = new BattlePhase();
                        battlePhase.start(character);
                    }
                } else if (input.equals("2")) {
                    character.inventory(character);
                } else if(input.equals("3")) {
                    character.stats();
                } else if(input.equals("4")) {
                    running = false;
                }else if(input.equals("5")) {
                    character.health = character.maxHealth;
                }else if(input.equals("6")) {
                    potionArmorShop(character);
                }else if(input.equals("7")) {
                    weaponShop(character);
                }else if(input.equals("8")) {
                    equipItem(character);
                }else if(input.equals("9")) {
                    spellShop(character);
                }else{
                    throw new IllegalArgumentException();
                }
            }catch(Exception e){
                System.out.println("Invalid input! Please try again.");
            }
        }
        System.out.println("Thanks for playing!");
    }

    public void potionArmorShop(Character character){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("------ Potion / Armor Shop ------");
        while(running){
            System.out.println("1. Potion         -- +50 HP    -- 350  Gold");
            System.out.println("2. Armor          -- +20 Armor -- 500  Gold");
            System.out.println("3. Extended Armor -- +60 Armor -- 1000 Gold");
            System.out.println("4. EXIT");
            System.out.println("--------------------");
            System.out.println("Current Gold: " + character.gold);
            System.out.println("Current Potions: " + character.potionCount);
            System.out.println("Current Armor: " + character.maxArmor);
            System.out.print("> ");
            String input = scanner.nextLine();

            if(input.equals("1")){
                if(character.gold >= 350){
                    character.potions.add("Potion");
                    character.gold -= 350;
                    character.potionCount += 1;
                }
            }else if(input.equals("2")){
                if(character.gold >= 500){
                    System.out.println("You have purchased armor");
                    character.potions.add("Armor");
                    character.gold -= 500;
                    character.armor += 20;
                    character.maxArmor += 20;
                }
            }else if(input.equals("3")){
                if(character.gold >= 1000){
                    System.out.println("You have purchased extended armor");
                    character.potions.add("Extended Armor");
                    character.gold -= 1000;
                    character.armor += 60;
                    character.maxArmor += 60;
                }
            }else if(input.equals("4")){
                System.out.println("Thanks for shopping!");
                running = false;
            }
        }
    }

    public void weaponShop(Character character){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("------ Weapon Shop ------");
        while(running){
            System.out.println("1. Dagger            -- +15 ATTACK -- 550  Gold");
            System.out.println("2. Long Sword        -- +25 ATTACK -- 800  Gold");
            System.out.println("3. Dual Blades       -- +50 ATTACK -- 1200 Gold");
            System.out.println("4. Behemoth Blade    -- +95 ATTACK -- 2200 Gold");
            System.out.println("5. EXIT");
            System.out.println("--------------------");
            System.out.println("Current Gold: " + character.gold);
            System.out.println("Current Weapon Equipped: " + character.equipped);
            System.out.print("> ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    if (character.gold >= 550) {
                        if (character.weapons.contains("Dagger")) {
                            System.out.println("You already own that!");
                        } else {
                            character.weapons.add("Dagger");
                            character.gold -= 550;
                            System.out.println("You have purchased the Dagger!");
                        }
                    }else{
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case "2":
                    if (character.gold >= 800) {
                        if (character.weapons.contains("Long Sword")) {
                            System.out.println("You already own that!");
                        } else {
                            character.weapons.add("Long Sword");
                            character.gold -= 800;
                            System.out.println("You have purchased the Long Sword!");
                        }
                    }else{
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case "3":
                    if (character.gold >= 1200) {
                        if (character.weapons.contains("Dual Blades")) {
                            System.out.println("You already own that!");
                        } else {
                            character.weapons.add("Dual Blades");
                            character.gold -= 1200;
                            System.out.println("You have purchased the Dual Blades!");
                        }
                    }else{
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case "4":
                    if (character.gold >= 2200) {
                        if (character.weapons.contains("Behemoth Blade")) {
                            System.out.println("You already own that!");
                        } else {
                            character.weapons.add("Behemoth Blade");
                            character.gold -= 2200;
                            System.out.println("You have purchased the Behemoth Blade!");
                        }
                    }else{
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case "5":
                    System.out.println("Thanks for shopping!");
                    running = false;
                    break;
                default:
                    System.out.println("Please enter a valid input!");
                    break;
            }
        }
    }

    public void equipItem(Character character){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println("Type out the item you would like to equip.");
            System.out.println("TYPE E TO EXIT");
            for (int i = 0; i < character.weapons.size(); i++) {
                System.out.println(i + " - " + character.weapons.get(i));
            }
            System.out.print("> ");
            String input = scanner.nextLine();

            if (character.weapons.contains(input)) {
                System.out.println("You have equipped the " + input);
                if (!input.equals("Wooden Sword")) {
                    damageBoost(input, character);
                }
                character.equipped = input;
                running = false;
            }else if(input.equalsIgnoreCase("e")){
                running = false;
            } else {
                System.out.println("Item does not exist!");
            }
        }
    }

    public void damageBoost(String weapon, Character character){
        if(weapon.equalsIgnoreCase("Dagger")){
            character.attack += 15;
        }else if(weapon.equalsIgnoreCase("Long Sword")){
            character.attack += 25;
        }else if(weapon.equalsIgnoreCase("Dual Blades")){
            character.attack += 50;
        }else if(weapon.equalsIgnoreCase("Behemoth Blade")){
            character.attack += 95;
        }
    }

    public void spellShop(Character character){
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("------ Spell Shop ------");
        while(running){
            System.out.println("1. Fire Blast (80 DAMAGE)  -- 400 GOLD");
            System.out.println("2. Ice Storm  (85 DAMAGE)  -- 420 GOLD");
            System.out.println("3. Earthquake (120 DAMAGE) -- 700 GOLD");
            System.out.println("4. EXIT");
            System.out.print("> ");
            String input = scanner.nextLine();
            System.out.println("Current Spells: ");

            for(String spells : character.spells){
                System.out.println(spells);
            }

            if(input.equals("1")){
                if(character.gold >= 400){
                    System.out.println("You have purchased a Fire Blast!");
                    character.spells.add("Fire Blast");
                    character.gold -= 400;
                }else{
                    System.out.println("Insufficient funds.");
                }
            }else if(input.equals("2")){
                if(character.gold >= 420){
                    System.out.println("You have purchased a Ice Storm!");
                    character.spells.add("Ice Storm");
                    character.gold -= 420;
                }else{
                    System.out.println("Insufficient funds.");
                }
            }else if(input.equals("3")){
                if(character.gold >= 700){
                    System.out.println("You have purchased an Earthquake!");
                    character.spells.add("Earthquake");
                    character.gold -= 700;
                }else{
                    System.out.println("Insufficient funds.");
                }
            }else if(input.equals("4")){
                System.out.println("Thanks for shopping!");
                running = false;
            } else{
                System.out.println("Please enter a valid input!");
            }
        }
    }
}
