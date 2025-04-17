import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class
Notepad extends JFrame implements ActionListener{

    String text = "";
    private JTextArea area;
    private JScrollPane scpane;

    public Notepad(){
        super("Notepad Clone");

        setBounds(300,100,800,600);

        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                exitApplication();
            }
        });

        JMenuBar menuBar = new JMenuBar(); //menubar

        JMenu file = new JMenu("File"); //file menu

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        save.addActionListener ( this );

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem ("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        exit.addActionListener(this);

        JMenu edit = new JMenu("Edit");

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem selectall = new JMenuItem("Select All");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);

        JMenu view = new JMenu("View");

        JMenuItem zoomin = new JMenuItem("Zoom in");
        zoomin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,ActionEvent.CTRL_MASK));
        zoomin.addActionListener(this);

        JMenuItem zoomout = new JMenuItem("Zoom out");
        zoomout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,ActionEvent.CTRL_MASK));
        zoomout.addActionListener(this);


        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        scpane = new JScrollPane(area);
        scpane.setBorder(BorderFactory.createEmptyBorder());

        setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);

        view.add(zoomin);
        view.add(zoomout);


        add(scpane,BorderLayout.CENTER);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Notepad();
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("New")){
            area.setText("");

        } else if(ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser("D:/Java");
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");
            chooser.addChoosableFileFilter(restrict);

            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();

                try {
                    System.out.println("HEki");
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    area.read(br,null);
                    br.close();
                    area.requestFocus();
                } catch(Exception e){
                    System.out.print(e);
                }
            }
        } else if(ae.getActionCommand().equals("Save")){
            final JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
            int actionDialog = SaveAs.showOpenDialog(this);
            if (actionDialog != JFileChooser.APPROVE_OPTION){
                return;
            }

            File fileName = new File(SaveAs.getSelectedFile()+".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            } catch(IOException e){
                e.printStackTrace();
            }
        } else if(ae.getActionCommand().equals("Print")){
            try {
                area.print();
            }catch(Exception e){
            }
        }else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }else if(ae.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }else if(ae.getActionCommand().equals("Paste")){
            area.insert(text,area.getCaretPosition());
        }else if(ae.getActionCommand ( ).equals ("Cut")){
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(),area.getSelectionEnd());
        }else if(ae.getActionCommand ( ).equals( "Select All")){
            area.selectAll();
        }
        else if(ae.getActionCommand().equals("Zoom in")){
            Font currentFont = area.getFont();
            int newSize = currentFont.getSize() + 2;
            area.setFont(new Font(currentFont.getFontName(),currentFont.getStyle(),newSize));

        }else if(ae.getActionCommand().equals("Zoom out")){
            Font currentFont = area.getFont();
            int newSize = Math.max(currentFont.getSize() - 2, 10); // Ensure minimum size, adjust as needed
            area.setFont(new Font(currentFont.getFontName(),currentFont.getStyle(),newSize));
        }
    }

    private void exitApplication(){
        int result = JOptionPane.showConfirmDialog(
                this,
                "Do you really want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION );

        if (result == JOptionPane.YES_OPTION) {
            // Perform any cleanup or additional actions before exiting
            System.exit(0);
        }else{
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Set default close operation

            addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e){
                    exitApplication();
                }
            });
        }
    }
}