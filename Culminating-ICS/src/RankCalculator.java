import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RankCalculator {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Rank Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new GridLayout(7, 2));

        // Create and add components
        JLabel firstScoreLabel = new JLabel("Enter the score of the first game:");
        JTextField firstScoreField = new JTextField();
        JLabel secondScoreLabel = new JLabel("Enter the score of the second game:");
        JTextField secondScoreField = new JTextField();
        JLabel thirdScoreLabel = new JLabel("Enter the score of the third game:");
        JTextField thirdScoreField = new JTextField();
        JLabel timePerGameLabel = new JLabel("Enter the time per game (in minutes):");
        JTextField timePerGameField = new JTextField();
        JLabel targetRankLabel = new JLabel("Enter the target rank (1-8):");
        JTextField targetRankField = new JTextField();
        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel();

        frame.add(firstScoreLabel);
        frame.add(firstScoreField);
        frame.add(secondScoreLabel);
        frame.add(secondScoreField);
        frame.add(thirdScoreLabel);
        frame.add(thirdScoreField);
        frame.add(timePerGameLabel);
        frame.add(timePerGameField);
        frame.add(targetRankLabel);
        frame.add(targetRankField);
        frame.add(new JLabel()); // Empty cell
        frame.add(calculateButton);
        frame.add(resultLabel);

        // Add action listener to the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the scores
                    double firstScore = Double.parseDouble(firstScoreField.getText());
                    double secondScore = Double.parseDouble(secondScoreField.getText());
                    double thirdScore = Double.parseDouble(thirdScoreField.getText());

                    // Get the time per game
                    double timePerGame = Double.parseDouble(timePerGameField.getText());

                    // Calculate the average score
                    double averageScore = (firstScore + secondScore + thirdScore) / 3;

                    // Define rank thresholds
                    double[] rankThresholds = {35400, 112000, 112000, 350000, 1039000, 1701000, 3382000, 6157000, 7567000};

                    // Get the target rank
                    int targetRank = Integer.parseInt(targetRankField.getText());

                    // Validate the target rank
                    if (targetRank < 1 || targetRank > 8) {
                        resultLabel.setText("Invalid rank. Please enter a rank between 1 and 8.");
                    } else {
                        // Get the threshold score for the target rank
                        double targetScore = rankThresholds[targetRank - 1];

                        // Calculate the number of games needed
                        double requiredGames = Math.ceil(targetScore / averageScore);

                        // Calculate the total time required
                        double totalTime = requiredGames * timePerGame;

                        // Display the result
                        resultLabel.setText("<html>You need to play " + (int)requiredGames + " games to reach rank " + targetRank 
                                            + ".<br>Total time required: " + totalTime + " minutes.</html>");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter valid numbers.");
                }
            }
        });

        // Set the frame to be visible
        frame.setVisible(true);
    }
}