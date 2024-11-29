import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class RecursiveListerFrame extends JFrame {


    JPanel mainPnl;
    JPanel displayPnl;
    JPanel controlPnl;
    JPanel titlePnl;

    JButton readButton;
    JTextArea displayTA;
    JScrollPane scroller;


    JLabel titleLabel;
    JButton quitBtn;



    public RecursiveListerFrame() {


        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());


        createButtonPanel();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(810, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createButtonPanel() {

        titlePnl = new JPanel();
        titlePnl.setLayout(new BorderLayout());

        titleLabel = new JLabel("File Lister");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePnl.add(titleLabel, BorderLayout.CENTER);




    }


    private void createDisplayPanel() {

        displayPnl = new JPanel();
        displayTA = new JTextArea(30, 65);
        displayTA.setFont(new Font("Georgia", Font.PLAIN, 14));
        displayTA.setEditable(false);
        scroller = new JScrollPane(displayTA);
        displayPnl.add(scroller);
    }


    private void createControlPanel() {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2));

        readButton = new JButton("List Directory");

        readButton.addActionListener((ActionEvent ae) -> {


                    File selectedDirectory = fileChooser();
                    readDirectory(selectedDirectory);

                }
        );


        quitBtn = new JButton("Quit!");
        quitBtn.setFont(new Font("Verdana", Font.PLAIN, 20));
        quitBtn.addActionListener((ActionEvent ae) -> {
                    System.exit(0);
                }
        );

        controlPnl.add(readButton);
        controlPnl.add(quitBtn);

    }


    public File fileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a directory");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            displayTA.setText("");
            return selectedDirectory;
        } else {
            displayTA.setText("No directory selected");
            return null;
        }

    }


    public void readDirectory(File selectedDirectory) {


        File[] list = selectedDirectory.listFiles();

        if (list != null) {
            for (File f : list) {
                if (f.isDirectory()) {
                    displayTA.append("Folder: " + f.getAbsolutePath() + "\n");
                    readDirectory(f);
                } else {
                    displayTA.append("File: " + f.getAbsolutePath() + "\n");
                }
            }
        }





    }


}
