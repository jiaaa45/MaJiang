import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

public class majiang extends JFrame {
    private static HashMap<String,List<Character>> map = new HashMap<>();
    public static void main(String[] args) {
        Background bg = new Background();//start, Instruction for Use

    }
    public static void returnMap(HashMap<String,List<Character>> mapp){
        map = mapp;
        for (String key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }
    }

}

class Background extends JFrame{
    private JLabel backgroundLabel;
    private JPanel buttonPanel;
    private JPanel newButtonPanel;
    private int newButtonCount = 0;
    private JFrame frame;
    private HashMap<Character,Integer> time = new HashMap<>();
    private HashMap<String,List<Character>> map = new HashMap<>();
    final String PATH = "image\\";
    public Background(){//main windows
        super("majiang");//window name
        this.setSize(700,700);//size
        this.setVisible(true);
        this.setLayout(null);//set by myself
        this.addWindowListener(new wap());

        ImageIcon imageIcon = new ImageIcon("image\\start.jpg");
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
                        if((newButtonCount%3)!=1){
                            JOptionPane.showMessageDialog(frame, "Nooooooooo!");
                        }
                        else {
                            HashMap<String,List<Character>> map = getMap();
                            majiang.returnMap(map);
                        }


                    }
                });
                JButton clearButton = new JButton("clear");
                clearButton.setFont(new Font("Arial", Font.PLAIN, 12));
                clearButton.setBounds(500, 435, 80, 30);
                clearButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newButtonPanel.removeAll();
                        newButtonPanel.revalidate();
                        newButtonPanel.repaint();
                        newButtonCount = 0;
                        for(String keyy : map.keySet()){
                            List<Character> value = new LinkedList();
                            map.put(keyy,value); 
                        }

                    }
                });
                getContentPane().add(addButton);
                getContentPane().add(clearButton);
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
        ArrayList<Character> chain = new ArrayList();
        String a = "chain";
        map.put(a,new LinkedList<>());
        String chainName = "123456789";
        for(char c : chainName.toCharArray()){
            chain.add(c);
        }
        for(int i = 0;i<chain.size();i++){
            JButton button = createButton(new ImageIcon(PATH+"chain"+(i+1)+".jpg"), chain.get(i),a);
            buttonPanel.add(button);
            time.put(chain.get(i),0);
        }
        ArrayList<Character> million = new ArrayList();
        a = "million";
        map.put(a,new LinkedList<>());
        String millionName = "abcdefghi";
        for(char c : millionName.toCharArray()){
            million.add(c);
        }
        for(int i = 0;i<million.size();i++){
            JButton button = createButton(new ImageIcon(PATH+"million"+(i+1)+".jpg"), million.get(i),a);
            buttonPanel.add(button);
            time.put(million.get(i),0);
        }
        ArrayList<Character> cookie = new ArrayList();
        a = "cookie";
        map.put(a,new LinkedList<>());
        String cookieName = "mnopqrstu";
        for(char c : cookieName.toCharArray()){
            cookie.add(c);
        }
        for(int i = 0;i<cookie.size();i++){
            JButton button = createButton(new ImageIcon(PATH+"cookie"+(i+1)+".jpg"), cookie.get(i),a);
            buttonPanel.add(button);
            time.put(cookie.get(i),0);
        }
        ArrayList<String> buttonName = new ArrayList();
        a = "BigWord";
        map.put(a,new LinkedList<>());
        String n = "ABCDEFG";
        ArrayList<Character> BigWord = new ArrayList();
        for(char c:n.toCharArray()){
            BigWord.add(c);
        }
        buttonName.add("east");
        buttonName.add("west");
        buttonName.add("south");
        buttonName.add("north");
        buttonName.add("red");
        buttonName.add("fa");
        buttonName.add("white");
        for(int i = 0;i<buttonName.size();i++){
            JButton button = createButton(new ImageIcon(PATH+buttonName.get(i)+".jpg"), BigWord.get(i), a);
            buttonPanel.add(button);
            time.put(BigWord.get(i),0);
        }

    }

    public JButton createButton(ImageIcon icon, Character name,String key){
        
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(40, 60));
        button.setActionCommand(name.toString());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(time.get(name)>=4){
                    JOptionPane.showMessageDialog(frame, "can't click over four time!");
                }
                else {
                    List<Character> a = map.get(key);
                    a.add(name);
                    map.put(key,a);
                    addNewButton(name,key,icon);
                }
                
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

    public void addNewButton(Character name,String key,ImageIcon icon) {
        if (newButtonCount < 16) {
            newButtonCount++;
            time.put(name,time.get(name)+1);
            JButton newButton = new JButton(icon);
            newButton.setActionCommand("new "+name);
            newButton.setPreferredSize(new Dimension(40,60));
            newButtonPanel.add(newButton);
            newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(Background.this, "Do you want to delete? ",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        time.put(name,time.get(name)-1);
                        List<Character> a  = map.get(key);
                        a.remove(Character.valueOf(name));
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

    public HashMap<String,List<Character>> getMap(){
        return map;
    }

    class wap extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);//can close the window
        }
    }
}

class inside{

    public List<Character> listenWhat( HashMap< String, List<Character>> input){
        List<Character>[] inputArray =new ArrayList[4]; //0:coolie  1:million  2:chain  3:BigWord
        List<Character> output = new ArrayList<>() ;
        inputArray[0] = input.get("cookie");
        inputArray[1] = input.get("million");
        inputArray[2] = input.get("chain");
        inputArray[3] = input.get("BigWord");
        int[] threeOrNot = new int[4]; //0:3n  1:!3n  2:null
        int count = 0;
        for(int i = 0;i < 4;i++){
            if(inputArray[i].isEmpty()) threeOrNot[i] = 2;
            else if(inputArray[i].size() % 3 !=0){
                count++;
                threeOrNot[i] = 1;
            }
        }
         if(count > 2) return output;
         for(int i = 0;i < 4;i++){
            if(threeOrNot[i] == 0){

            }
            else if(threeOrNot[i] == 1){

            }
            else continue;
         }

        return output;
    }

    public Boolean huOrNot(List<Character> input){
        Boolean answer = false;
        //adddddddddddd
        //@510

        return answer;
    }

}