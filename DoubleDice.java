/*
   Name:  Lisette Allen
   Course Number: CIS163AA
   Section Number: 17930
   MEID: LIS2170496
   Date:  4/14/2026

   Thank you for a great semester!
*/





import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.text.NumberFormat;


public class DoubleDice extends JFrame implements ActionListener {
    // Give the player $200
    private double dollarAmount = 200.00;

    JLabel dollarLabel;
    JLabel resultLabel;
    private JFormattedTextField betAmount;
    JLabel winningsLabel;
    JLabel loseLabel;
    JLabel quitInstructions;

    // Give a pair of dice
    Die firstDie = new Die();
    Die secondDie = new Die();


    public DoubleDice() {
        setTitle("Double Dice");
        
        
        // Create label for current dollar amount
        dollarLabel = new JLabel(String.format("You have $%.2f", dollarAmount));



        // Create label and field for placing a bet
        JLabel betLabel = new JLabel("Place your bet:");
        betAmount = new JFormattedTextField(NumberFormat.getCurrencyInstance());
        betAmount.setEditable(true);
        betAmount.setValue(1);
        betAmount.setColumns(8);


        // Create labels for die roll result, winnings statement, closing statement
        resultLabel = new JLabel();
        winningsLabel = new JLabel();
        loseLabel = new JLabel();
        quitInstructions = new JLabel("To quit, enter $0.00 bet or click the X in the top right corner");
        
        

        // Set GridBagLayout and declare GridBagConstraints
        setLayout(new GridBagLayout());
        GridBagConstraints layoutConst = null;


        // Set GridBagConstrains and Insets then apply to current dollars label, bet amount label, bet amount field
        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.insets = new Insets(10, 10, 70, 10);
        layoutConst.gridwidth = 2;
        add(dollarLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        layoutConst.insets = new Insets(10, 87, 10, 4);
        layoutConst.anchor = GridBagConstraints.LINE_END;
        add(betLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        layoutConst.insets = new Insets(10, 1, 10, 10);
        layoutConst.anchor = GridBagConstraints.LINE_START;
        add(betAmount, layoutConst);

        



        // Create a button to roll the dice
        JButton rollButton = new JButton("Roll the Dice!");
        rollButton.addActionListener(this);



        // Set GridBagConstrains and Insets then apply to roll button
        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.gridwidth = 2;
        add(rollButton, layoutConst);

        // SetGridBagConstraints and Insets then apply to result label and winnings
        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        layoutConst.insets = new Insets(10, 0, 10, 10);
        layoutConst.gridwidth = 2;
        add(resultLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        layoutConst.insets = new Insets(10, 0, 10, 10);
        layoutConst.gridwidth = 2;
        add(winningsLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 5;
        layoutConst.insets = new Insets(10, 0, 10, 10);
        layoutConst.gridwidth = 2;
        add(loseLabel, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.gridx = 0;
        layoutConst.gridy = 8;
        layoutConst.insets = new Insets(100, 0, 10, 10);
        layoutConst.gridwidth = 2;
        add(quitInstructions, layoutConst);

    }

    

    @Override
    public void actionPerformed(ActionEvent event) {
        // Created listener for button press

        // Get the bet amount from the user input
        Number betNumber = (Number) betAmount.getValue();
        double betValue = Math.round( betNumber.doubleValue() * 100.0)  /  100.0  ;


        if (betValue == 0) {
            // 0 to exit - show goodbye message
            JOptionPane.showMessageDialog(this, "See you around, winner!");
            System.exit(0);

        }

        else if (dollarAmount <= 0.0001  ||  betValue <= 0.0001) {
            //Do nothing when out of money or $0 bet
        }

        
        // Set popup message for insufficient funds
        else if (betValue > dollarAmount) {
            JOptionPane.showMessageDialog(this, "You don't have enough money to cover this bet!");
        }


        // Roll the dice!
        else if (betValue > 0.0001) {
            firstDie.roll();
            secondDie.roll();

            // Pull String version of rollValue to create the result text 
            String resultOne = firstDie.toString();
            String resultTwo = secondDie.toString();
            String resultText = ("You rolled a " + resultOne + " and " + resultTwo);
            
            resultLabel.setText(resultText);


            //if (resultOne.equals(resultTwo)) {
            if (firstDie.equals(secondDie)) {
                //WINNER 
                //Winnings is 5 * initial bet. Set winningsLabel to reflect. Add winnings to total and update dollarLabel.
                double winningsAmount = betValue * 5.00;
                dollarAmount = dollarAmount + winningsAmount;
                winningsLabel.setText(String.format("You win $%.2f", winningsAmount));

                dollarLabel.setText(String.format("You have $%.2f", dollarAmount));
            }

            else {
                //LOSER
                //Deduct bet amount from total. Set winningsLabel to reflect loss. Update dollarLabel to reflect.
                dollarAmount = dollarAmount - betValue;
                winningsLabel.setText(String.format("You lose $%.2f", betValue));

                dollarLabel.setText(String.format("You have $%.2f", dollarAmount));


                // Set closing statement for when player loses all their money
                if (dollarAmount <= 0.0001) {
                    loseLabel.setText("You are out of money! \n Better luck next time");
                }
            }

            
        }

        
    }


    public static void main(String[] args) {
        DoubleDice newGame = new DoubleDice();

        // Generic config for exit, size, and setVisible
        newGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newGame.setSize(500, 500);
        newGame.setVisible(true);


    }

}
