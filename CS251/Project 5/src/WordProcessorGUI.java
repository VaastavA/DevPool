import javax.swing.*;
import java.awt.*;

public class WordProcessorGUI {

    public void initProcessorGUI () {

        JFrame frame = new JFrame();
        frame.setSize(500, 200);
        frame.setLayout(new GridLayout(1,3));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Word Processor");

        JPanel findPanel = new JPanel();
        JPanel col1 = new JPanel();
        JPanel col2 = new JPanel();
        JPanel col3 = new JPanel();

        JLabel findWord = new JLabel("Find Word");
        findWord.setVisible(true);
        col1.add(findWord);

        JTextField textField = new JTextField(null);
        textField.setPreferredSize(new Dimension( 150, 24 ));
        col2.add(textField);

        JButton findButton = new JButton("Find");
        findButton.setVisible(true);
        col3.add(findButton);

        findPanel.add(col1);
        findPanel.add(col2);
        findPanel.add(col3);
        findPanel.setVisible(true);

        frame.add(findPanel);
    }

    public static void main(String[] args) {
        WordProcessorGUI wpg = new WordProcessorGUI();
        wpg.initProcessorGUI();

    }
}
