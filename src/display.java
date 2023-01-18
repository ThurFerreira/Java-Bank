import javax.swing.*;
import java.awt.*;

public class display {
    private String name;
    private int age;
    private int birthYear;
    public display() {
        name = "Myself";
        age = 19;
        birthYear = 1994;
    }

    public void displayGUI() {
        JOptionPane.showMessageDialog(
                null, getPanel(), "Output : ",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        JLabel nameLabel = getLabel("Your Name : " + name);
        JLabel ageLabel = getLabel("Your Age : " + age);
        JLabel yearLabel = getLabel("Your Birth Year : " + birthYear);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(yearLabel);

        return panel;
    }

    private JLabel getLabel(String title) {
        return new JLabel(title);
    }
}
