import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Notepad extends JFrame {
    private JTextField fileNameTextField;
    private JTextArea textArea;
    private JButton saveButton, loadButton, clearButton;
    public Notepad() {
        fileNameTextField = new JTextField();
        textArea = new JTextArea();
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");
        clearButton = new JButton("Clear");
        NotepadButtonListener buttonListener = new NotepadButtonListener();
        saveButton.addActionListener(buttonListener);
        loadButton.addActionListener(buttonListener);
        clearButton.addActionListener(buttonListener);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(clearButton);
        this.setLayout(new BorderLayout());
        add(fileNameTextField, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        pack();
        setVisible(true);
    }
    private String getFileName() {
        return fileNameTextField.getText();
    }
    private void readFile(String fileName) {
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileReader(fileName));
            textArea.setText("");
            while (inFile.hasNextLine()) {
                textArea.append(inFile.nextLine());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("File not found");
        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }
    }
    private void writeFile(String fileName) {
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(new FileWriter(fileName));
            outFile.print(textArea.getText());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("File not found");
        } finally {
            if (outFile != null) {
                outFile.close();
            }
        }
    }
    class NotepadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
            if (sourceButton.equals(loadButton)) {
                System.out.println("Load button pressed");
                readFile(getFileName());
            } else if (sourceButton.equals(saveButton)) {
                System.out.println("Save button pressed");
                writeFile(getFileName());
            } else if (sourceButton.equals(clearButton)) {
                System.out.println("Clear button pressed");
                textArea.setText("");
            } else {
                System.out.println("Unknown button pressed");
            }
        }
    }
    public static void main(String[] args) {
        new Notepad();
    }
}