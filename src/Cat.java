package src;
import java.util.*;
import static src.Constants.CatConstants.*;

class Cat extends Pet{
    private SimulatorAnimation catAnimation;
    private SimulatorUI catUI;

    public Cat(String name, SimulatorAnimation catAnimation, SimulatorUI catUI) {
        super(name);
        this.catAnimation = catAnimation;
        this.catUI = catUI;
        catUI.updateCatStatus(mood, hunger, energy);
    }

    //handles commands and generates cat response
    @Override
    public void talkToPet(String command) {
        System.out.println("\nYou: " + command);

        // Simulate whether the cat listens or not

        // Perform the detected action
        switch (command) 
        {
            case "eat_command":
                eat();
                break;
            case "sleep_command":
                sleep();
                break;
            case "play_command":

                if (!shouldListen()) 
                {
                    System.out.println(name + " ignores you... 😾");
                    return;
                }
                play();
                break;
            case "sit_command":
                
                if (!shouldListen()) 
                {
                    System.out.println(name + " ignores you... 😾");
                    return;
                }
                sit();
                break;
            default:
                System.out.println(" Meaowww?? 🤨 (Unrecognized command)");
                catAnimation.setThought(" Meaowww?? 🤨", 10);
                break;
        }
    }


    private void eat() {
        if (hunger > 20) 
        {
            hunger -= 30; // Big meal
            hunger = Math.max(0, hunger);

            mood += 15;
            mood = Math.min(100, mood);

            energy -= 5;
            energy = Math.max(0, energy);
            //System.out.println(name + " eats happily! 😸 (Hunger: " + hunger + ", Mood: " + mood + ")");

            catAnimation.setAnimation(EAT);
            catAnimation.resetAnimationTimer(10);
            catAnimation.setThought("Nom nom num 🍖🍖🍖", 10);

            catUI.updateCatStatus(mood, hunger, energy);
        } else {
            System.out.println(name + " is full and refuses to eat more. 😼");
            catAnimation.setThought(name + " is full and refuses to eat more. 😼", 10);
        }
    }

    private void sleep() {
        if (energy < 80) 
        {
            energy += 30;
            energy = Math.min(100, energy);

            mood += 10;
            mood = Math.min(100, mood);

            hunger += 20;
            hunger = Math.min(100, hunger);
            //System.out.println(name + " takes a cozy nap. 💤 (Hunger: " + hunger + ", Mood: " + mood + ")");
            catUI.updateCatStatus(mood, hunger, energy);
            catAnimation.setAnimation(SLEEP);
            catAnimation.resetAnimationTimer(20);
            catAnimation.setThought(name + " takes a cozy nap. 💤", 10);
        } else {
            System.out.println(name + " is not tired and ignores you. 🙀");
            catAnimation.setAnimation(SIT);
            catAnimation.setThought(name + " is not tired and ignores you. 🙀", 10);
        }
    }

    private void play() {
        if (hunger < 70 && energy > 20 && shouldListen()) 
        {
            mood += 15;
            mood = Math.min(100, mood);

            hunger += 15;
            hunger = Math.min(100, hunger);

            energy -= 20;
            energy = Math.max(0, energy);

            //System.out.println(name + " chases a toy mouse! 🐭 (Hunger: " + hunger + ", Mood: " + mood + ")");
            catUI.updateCatStatus(mood, hunger, energy);
            catAnimation.setAnimation(DANCE);
            catAnimation.resetAnimationTimer(7);
            catAnimation.setThought("😻😻",10);
        } else {
            System.out.println(name + " is too tired or hungry to play. 😿");
            catAnimation.setAnimation(SAD);
            catAnimation.resetAnimationTimer(100);
            catAnimation.setThought(name + " is too tired or hungry to play. 😿", 5);
        }
    }

    private void sit() {

        if (mood > 10 && hunger < 90 && energy > 10 && shouldListen()) 
        {
            mood -= 5;
            hunger += 5;
            energy -= 5;

            mood = Math.max(0, mood);
            hunger = Math.min(100, hunger);
            energy = Math.max(0, energy);
            //System.out.println(name + " sits patiently (Hunger: " + hunger + ", Mood: " + mood + ")");

            catUI.updateCatStatus(mood, hunger, energy);
            catAnimation.setAnimation(SIT);
            catAnimation.resetAnimationTimer(10);
            catAnimation.setThought("......",10);
        } else {
            System.out.println(name + " is too tired or hungry to listen to you. 😿");
            catAnimation.setThought(name + " Ignores You!", 5);
        }
    }
}