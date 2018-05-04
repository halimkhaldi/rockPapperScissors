package com.company;

import com.game.Game;
import com.game.Item;
import com.player.Player;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class Main extends processing.core.PApplet{
    int computerChoice;
    int time = 60;
    int itemsIndex = 0;
    int iteration = 0;
    PImage computerImg;
    PImage multiImg;
    Item rockImg;
    Item paperImg;
    Item scissorsImg;
    Game game;
    ArrayList<Item> choices;
    boolean gameStarted = false;
    Player player;
    boolean playerChoised = false;
    PImage temp;
    String message;



    public static void main(String[] args)   {
            PApplet.main("com.company.Main",args);
        }
    public void settings(){
        // window size
        size(1280, 720);

    }
    public void setup(){
        computerImg = loadImage("user.png");
        multiImg = loadImage("multiple.png");
        rockImg = new Item(loadImage("rock.png"),"rock");
        paperImg = new Item(loadImage("paper.png"),"paper");
        scissorsImg = new Item (loadImage("scissors.png"),"scissors");
        choices = new ArrayList();
        choices.add(rockImg);
        choices.add(paperImg);
        choices.add(scissorsImg);
        temp = rockImg.getAction();
        fill(0);
        textSize(40);
    }
    public void draw(){
        if(!gameStarted) {
            background(255);
            image(computerImg, 300, 200, 200, 200);
            image(multiImg, 720, 200, 200, 200);
            text("play vs Computer", 250, 450);
            text("play vs Player", 680, 450);
            System.out.println((int )(Math.random() * 3));
        }
        else {
            background(255);
                        if(playerChoised) {

                            if (time  % 5 == 0) {
                                temp = Item.getPimage(choices.get(itemsIndex));
                                itemsIndex++;
                                if (itemsIndex > 2) {
                                    itemsIndex = 0;
                                }
                            }
                          iteration++;
                            if(iteration > computerChoice-120){
                                playerChoised=false;
                                iteration = 0;
                                time = 60;
                                temp = Item.getPimage(choices.get(computerChoice - 200));
                                game.getWinner(player.getChoice(),Item.toString(choices.get(computerChoice - 200)));
                            }
                            time++;
                        }

            image(temp, 550, 100, 200, 200);
            image(Item.getPimage(choices.get(0)), 250, 350, 200, 200);
            image(Item.getPimage(choices.get(1)), 550, 350, 200, 200);
            image(Item.getPimage(choices.get(2)), 850, 350, 200, 200);
            text(game.getMessage(), 520, 640);
        }

    }
    public void mousePressed(){
        if(mouseX >= 300 && mouseX <=500 && mouseY >=200 && mouseY <=400 && !gameStarted){
            gameStarted=true;
            game=new Game(0,"Game Started!");
            player = new Player("Halim");
        }
        else if(mouseX >= 250 && mouseX <=450 && mouseY >=350 && mouseY <=550 && gameStarted && !playerChoised){
            playerChoised = true;
            computerChoice = (int )(Math.random() * 3 + 200);
            System.out.println(computerChoice);
            player.setChoice(Item.toString(choices.get(0)));

        }
        else if (mouseX >= 550 && mouseX <=750 && mouseY >=350 && mouseY <=550 && gameStarted && !playerChoised){
            playerChoised = true;
            computerChoice = (int )(Math.random() * 3 + 200);
            System.out.println(computerChoice);
            player.setChoice(Item.toString(choices.get(1)));
        }
        else if(mouseX >= 850 && mouseX <=1050 && mouseY >=350 && mouseY <=550 && gameStarted && !playerChoised){
            playerChoised = true;
            computerChoice = (int )(Math.random() * 3 + 200);
            System.out.println(computerChoice);
            player.setChoice(Item.toString(choices.get(2)));

        }
    }
}
