package finalllll_project;

import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class majiang extends JFrame {
    public static void main(String[] args) {
        Background bg = new Background();//start, Instruction for Use
        
    }

}

class Background extends JFrame{
    private JLabel backgroundLabel;
    private JPanel buttonPanel;
    private JPanel newButtonPanel;
    private int newButtonCount = 0;
    private JFrame frame;
    private HashMap<String,Integer> time = new HashMap<>();
    final String PATH = "/image/";
    public Background(){//main windows
        super("majiang");//window name
        this.setSize(700,700);//size
        this.setVisible(true);
        this.setLayout(null);//set by myself
        this.addWindowListener(new wap());

        ImageIcon imageIcon = new ImageIcon("/image/start.jpg");
        backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, 700, 700);//(starting place,size)
        this.add(backgroundLabel);//show the starting image
        getContentPane().revalidate();
        getContentPane().repaint();

        Timer timer = new Timer(4000, (ActionEvent e) -> {
            getContentPane().remove(backgroundLabel);//remove starting image
            Color background = new Color(253,249,238);//set background color by RGB
            getContentPane().setBackground(background);
            getContentPane().revalidate();
            getContentPane().repaint();
            instruction();
        });
        timer.setRepeats(false);//only run one time
        timer.start();

    }

    public void instruction() {//Instruction for Use
        JDialog dialog = new JDialog(this, true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(new Color(193, 203, 205));//background color

        JLabel messageLabel = new JLabel("Instruction for Use", SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(messageLabel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        nextButton.setFocusPainted(false); // remove Dotted frame
        nextButton.setBorderPainted(false);//remove button frame
        nextButton.setBackground(new Color(193, 203, 205)); // button bg color
        nextButton.setContentAreaFilled(false); 
        nextButton.setOpaque(true); 
        nextButton.addActionListener(e -> dialog.dispose());
        contentPanel.add(nextButton, BorderLayout.SOUTH);

        dialog.setContentPane(contentPanel);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                createInitialButton();
                createNewButtonPanel();
                JButton addButton = new JButton("NEXT");
                addButton.setFont(new Font("Arial", Font.PLAIN, 12));
                addButton.setBounds(600, 435, 80, 30);
                //addButton.setBounds(645, 435, 40, 25);
                addButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if((newButtonCount%3)!=2){
                            JOptionPane.showMessageDialog(frame, "Nooooooooo!");
                        }
                        //
                        //
                        //////////
                    }
                });
                getContentPane().add(addButton);
            }
        });
    }

    public void createInitialButton(){
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLayout(new GridLayout(4, 9, 20, 1));
        buttonPanel.setBounds(90,47,520,243);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(buttonPanel);
        ArrayList<String> buttonName = new ArrayList();
        String all = "abcdefghiABCDEFGHI123456789";
        for(char c : all.toCharArray()){
            String a = Character.toString(c);
            buttonName.add(a);
        }
        
        buttonName.add("east");
        buttonName.add("west");
        buttonName.add("south");
        buttonName.add("north");
        buttonName.add("red");
        buttonName.add("fa");
        buttonName.add("white");
        for(int i = 0;i<buttonName.size();i++){
            JButton button = createButton(new ImageIcon(PATH+buttonName.get(i)+".jpg"), buttonName.get(i));
            buttonPanel.add(button);
            time.put(buttonName.get(i),0);
        }
    }

    public JButton createButton(ImageIcon icon, String name){
        
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(40, 60));
        button.setActionCommand(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(time.get(name)>=4){
                    JOptionPane.showMessageDialog(frame, "can't click over four time!");
                }
                else addNewButton(name);
            }
        });
        return button;
    }

    public void createNewButtonPanel() {
        newButtonPanel = new JPanel();
        newButtonPanel.setLayout(null);
        //newButtonPanel.setLayout(new GridLayout(1, 16));
        newButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
        newButtonPanel.setBounds(7, 370, 675, 60); 
        newButtonPanel.setBorder(BorderFactory.createLineBorder(Color.RED)); 
        getContentPane().add(newButtonPanel);

        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void addNewButton(String name) {
        if (newButtonCount < 16) {
            newButtonCount++;
            time.put(name,time.get(name)+1);
            JButton newButton = new JButton(new ImageIcon(PATH+name+".jpg"));
            newButton.setActionCommand("new "+name);
            newButton.setPreferredSize(new Dimension(40,60));
            newButtonPanel.add(newButton);
            newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(Background.this, "Do you want to delete? ",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        time.put(name,time.get(name)-1);
                        newButtonPanel.remove(newButton);
                        newButtonPanel.revalidate();
                        newButtonPanel.repaint();
                        newButtonCount--;
                    }
                }
            });
            newButtonPanel.revalidate();
            newButtonPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Maximum button limit reached (16 buttons).");
        }
    }

    class wap extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);//can close the window
        }
    }
}
