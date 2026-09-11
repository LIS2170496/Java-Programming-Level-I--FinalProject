/*
   Name:  Lisette Allen
   Course Number: CIS163AA
   Section Number: 17930
   MEID: LIS2170496
   Date:  4/14/2026

   Thank you for a great semester!
*/





import java.util.Random;

public class Die {
    private int rollValue;
    String returnString;
    Random randomNum;

    public Die() {
        // Instantiate Random object and set roll 0
        randomNum = new Random();
        rollValue = 0;
    }

    public void roll() {
        // Generate random number (roll the dice)
        rollValue = (randomNum.nextInt(10)) + 1;
    }

    @Override
    public boolean equals(Object obj) {
        // Set equals() so that it references the rollValue instead of the Object 

        // I originally set the parameters as "Die die2" like in the project instructions
        // but VSCode displayed a problem.
        // Using "Object obj" resolved that problem.

        Die die2 = (Die) obj;
        return this.rollValue == die2.rollValue;
    }


    @Override
    public String toString() {
        // Override toString() so that it returns string version of rollValue instead of the Object
        switch (rollValue) {
            case 1: 
                returnString = "one"; 
                break;
            case 2:
                returnString = "two";
                break;
            case 3:
                returnString = "three";
                break;
            case 4:
                returnString = "four";
                break;
            case 5:
                returnString = "five";
                break;
            case 6:
                returnString = "six";
                break;
            case 7:
                returnString = "seven";
                break;
            case 8:
                returnString = "eight";
                break;
            case 9:
                returnString = "nine";
                break;
            case 10:
                returnString = "ten";
                break;
        }
        return returnString;
    }



    
}
