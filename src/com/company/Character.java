package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



    public class Character {
        String [] currentSkillSet;
        static Scanner input = new Scanner(System.in);
        int Str;
        int Dex ;
        int Con ;
        int Int ;                                //declare a variable
        int Wis ;
        int Cha ;
        int bonus;
        int con_Bonus;
        String selectedCharacter;
        int selectedDiceType;
        String[] charaters = { "barbarian", "bard", "cleric", "druid", "monk", "Rogue", "warlock", "fighter", "ranger",
                "paladin", "sorcerer", "wizard" };
        int[] diceTypes = { 12, 8, 8, 8, 8, 8, 8, 10, 10, 10, 6, 6 };
        int Level = 0;                //declare and initialise Level(variable)
        boolean isNumber;
        boolean gameOver = false;
        String userIn = "";

        public void printCharaters() {
            for (int i = 0; i < charaters.length; i++) {
                System.out.println(charaters[i]);
            }
        }

        public int findDiceType(String selectedCharacter) {
            int index=0;
            for (int i = 0; i < charaters.length ; i++) {
                if (charaters[i].equals(selectedCharacter)) {
                    index = i;
                }
            }
            return diceTypes[index];
        }

        public  int randomGenerator(int diceType) {
            int[] arr = new int[4];
            int total = 0;
            int min = 7;
            int output;
            for (int g = 0; g < arr.length; g++) {
                arr[g] = (int) (Math.random() * 1000 % diceType + 1);
                total = total + arr[g];
                if (arr[g] < min) {
                    min = arr[g];
                }
            }
            output = total - min;
            return output;
        }

        public  void display(int value, String variable) {
//		int bonus;
            if ((value == 10) || (value == 11)) {
                bonus = 0;
                System.out.println(variable + ":" + "[" + value + "]" + "[" + bonus + "]");
            } else if (((value % 2) == 0) && value > 10) {
                bonus = (value - 10) / 2; 												// Calculate the Str Bonus
                System.out.println(variable + ":" + "[" + value + "]" + "[+" + bonus + "]");
            } else if (((value % 2) == 1) && value > 11) {
                bonus = (value - 11) / 2;
                System.out.println(variable + ":" + "[" + value + "]" + "[+" + bonus + "]");
            } else if (((value % 2) == 0) && value < 10) {
                bonus = (value - 10) / 2;
                System.out.println(variable + ":" + "[" + value + "]" + "[" + bonus + "]");
            } else if (((value % 2) == 1) && value < 10) {
                bonus = (value - 11) / 2;
                System.out.println(variable + ":" + "[" + value + "]" + "[" + bonus + "]");
            }

        }

        public void game() {
            do {
                System.out.println("Enter the value of Level");             //prompt for Level
                if (input.hasNextInt()) {
                    Level = input.nextInt();                                //get inputs for Level
                    if (Level > 0) {
                    } else {
                        System.err.println("Invalid input. Try Again!.");
                    }
                    isNumber = true;
                } else {
                    System.err.println("Invalid Input. Try Again!. ");        //to the wrong input print"Invalid Input"
                    isNumber = false;
                    input.next();
                }

            } while (!(isNumber) || Level < 0);
            System.out.println("Select your Character :");
            while(!gameOver) {
                //System.out.println("Select your Character");
                printCharaters();
                selectedCharacter = input.next();
                selectedDiceType = findDiceType(selectedCharacter);
                System.out.println("Level:" + "[" + Level + "]");
                Str=randomGenerator(selectedDiceType);
                display(Str,"Str ");
                Dex=randomGenerator(selectedDiceType);
                display(Dex,"Dex ");
                Con=randomGenerator(selectedDiceType);
                display(Con,"Con ");
                con_Bonus=bonus;
                Int=randomGenerator(selectedDiceType);
                display(Int,"Int ");
                Wis=randomGenerator(selectedDiceType);
                display(Wis,"Wis ");
                Cha=randomGenerator(selectedDiceType);
                display(Cha,"Cha ");

                try  {
                    File file= new File("skills.txt");
                    FileReader fileReader = new FileReader(file) ;
                    BufferedReader bufferedReader=  new BufferedReader(fileReader);
                    ArrayList<String> read= new ArrayList<>();
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        read.add(line);
                        System.out.println(line);

                    }
                    currentSkillSet= new String[Level];
                    for (int i=0;i<Level;i++){
                        System.out.println("Enter Skill :");
                        currentSkillSet[i]= input.next();
                    }
                    System.out.println("Your Selected Skills are");
                    for(String i:currentSkillSet){
                        System.out.println(i);
                    }
                    System.out.println("Skill Point is :"+Level);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("If You accept this Type 1 to exit the game");
                System.out.println("If you want re-Select Character press any key");
                userIn = input.next();
                if(userIn.equals("1")) {
                    break;
                }

            }
            display(Str,"Str ");
            display(Dex,"Dex ");
            display(Con,"Con ");
            display(Int,"Int ");
            display(Wis,"Wis ");
            display(Cha,"Cha ");
            System.out.println("Your Selected Skills are :");
            for(String i:currentSkillSet){
                System.out.println(i);
            }
            System.out.println("Skill Point is :"+Level);

        }





}
