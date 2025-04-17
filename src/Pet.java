package src;
import java.util.Random;

abstract class Pet {
    protected String name;
    protected int hunger; // 0 (Full) - 10 (Very Hungry)
    protected int mood;   // 0 (Happy) - 10 (Grumpy)
    protected int energy = 5;
    protected Random random;

    public Pet(String name) {
        this.name = name;
        this.hunger = 30; // Neutral state
        this.mood = 50;   // Neutral mood
        this.energy = 80;
        this.random = new Random();
    }

    public abstract void talkToPet(String command);

    //randomized behavior of the cat depending on mood and hunger level of the cat
    //if the cat is well fed and had a good sleep, positive reactions are favoured
    protected boolean shouldListen() {
        // Scale values to weights (each 0–100)
        int obedienceScore = (100 - hunger) / 2       // lower hunger = better
                           + mood / 2                 // higher mood = better
                           + energy / 2;              // higher energy = better
    
        // Max score = 150 (50 + 50 + 50)
        return random.nextInt(150) < obedienceScore;
    }
}