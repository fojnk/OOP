package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WindowNotebookApp extends JFrame {
    public WindowNotebookApp(List<Note> list) {
        JFrame frame = new JFrame("Notes");
        frame.setBackground(Color.BLACK);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        for (var note : list) {
            JPanel wrapper = new JPanel();
            wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
            panel.setBackground(Color.GRAY);
            JLabel label1 = new JLabel(note.getTitle());
            JTextField date = new JTextField(note.getNoteDate());
            label1.setFont(new Font("Tahoma", Font.PLAIN, 20));
            date.setFont(new Font("Tahoma", Font.PLAIN, 14));
            date.setBackground(Color.LIGHT_GRAY);
            date.setEditable(false);
            panel.add(label1);
            panel.add(date);
            JTextArea text = new JTextArea(" " + note.getDescription());
            text.setFont(new Font("Tahoma", Font.PLAIN, 16));
            text.setEditable(false);
            wrapper.add(panel);
            wrapper.add(text);
            mainPanel.add(wrapper);
        }
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}