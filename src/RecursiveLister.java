import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Locale;

import static java.nio.file.StandardOpenOption.CREATE;
public class RecursiveLister extends JFrame {

     JPanel mainPanel;
     JButton fileLoader;
     JButton quitBtn;

     public RecursiveLister() {
          mainPanel = new JPanel();
          fileLoader = new JButton("Load File");
          fileLoader.addActionListener(e -> {
               fileText.setText("");
               listFiles(loadFile());
          });
          mainPanel.add(fileLoader);
          quitBtn = new JButton("Quit");
          quitBtn.addActionListener(e -> {
               if (JOptionPane.showConfirmDialog(this, "Do you want to quit?") == JOptionPane.YES_OPTION) {
                    System.exit(0);
               }
          });
          mainPanel.add(quitBtn);
          createTextArea();
          mainPanel.add(TextArea);

          setTitle("File Lister");
          add(mainPanel);
          setSize(400, 600);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setVisible(true);
     }

     JPanel textArea;
     JTextArea fileText;
     JScrollPane fileShow;
     private void createTextArea(){
          textArea = new JPanel();
          fileText = new JTextArea(30, 30);
          fileShow = new JScrollPane(fileText);
          textArea.add(fileShow);
     }

     private File loadFile() {
          JFileChooser chooser = new JFileChooser();
          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          File selectedFile = new File("");

          try
          {
               File workingDirectory = new File(System.getProperty("user.dir"));

               chooser.setCurrentDirectory(workingDirectory);
               if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
               {
                    selectedFile = chooser.getSelectedFile();
               }
               return selectedFile;
          } catch (HeadlessException e) {
               e.printStackTrace();
          }
          return new File("");
     }

     private void listFiles(Files dir){
          for(File currentFile : dirlistFiles()) {
               if (currentFile.isDirectory()) {
                    fileText.append(currentFile.getName() + "\n");
                    listFiles(currentFile);
               }
               else {
                    fileText.append(currentFile.getName());
               }
          }

     }

}