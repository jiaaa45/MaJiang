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
    public static int check = 0;
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
    private static JPanel answerpanel;
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
                createAnswerButtonPanel();
                JButton addButton = new JButton("NEXT");
                addButton.setFont(new Font("Arial", Font.PLAIN, 12));
                addButton.setBounds(600, 435, 80, 30);
                //addButton.setBounds(645, 435, 40, 25);
                addButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        majiang.check++;
                        if((newButtonCount%3)!=1){
                            JOptionPane.showMessageDialog(frame, "Nooooooooo!");
                            majiang.check--;
                        }
                        else if(majiang.check == 1){
                            HashMap<String,List<Character>> map = getMap();
                            Inside.listenWhat(map);
                            //majiang.returnMap(map);
                        }
                        else if(majiang.check >= 5)JOptionPane.showMessageDialog(frame, "Calm down!!!!!!!");
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

    public void createAnswerButtonPanel() {
        answerpanel = new JPanel();
        answerpanel.setLayout(null);
        //newButtonPanel.setLayout(new GridLayout(1, 16));
        answerpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
        answerpanel.setBounds(230, 500, 230, 60); 
        answerpanel.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 
        getContentPane().add(answerpanel);

        getContentPane().revalidate();
        getContentPane().repaint();
    }
    public static void answer(List<Character> list){
        HashMap <Character,String> ans=new HashMap<>();
        //private JPanel answerpanel;
        String million="abcdefghi";
        String chain="mnopqrstu";
        String cookie="123456789";
        int i=1;
        for(char c:million.toCharArray()){
            if(list.contains(c))ans.put(c, "million"+i);
            i++;
        }
        i=1;
        for(char c:chain.toCharArray()){
            if(list.contains(c))ans.put(c, "chain"+i);
            i++;
        }
        i=1;
        for(char c:cookie.toCharArray()){
            if(list.contains(c))ans.put(c, "cookie"+i);
            i++;
        }
        if(list.contains("A"))ans.put("A", "east");
        if(list.contains("B"))ans.put("B", "west");
        if(list.contains("C"))ans.put("C", "south");
        if(list.contains("D"))ans.put("D", "north");
        if(list.contains("E"))ans.put("E", "red");
        if(list.contains("F"))ans.put("F", "fa");
        if(list.contains("G"))ans.put("G", "white");
        answerpanel.removeAll();
        for(Map.Entry<Character,String> entry : ans.entrySet()){
            ImageIcon icon = new ImageIcon("image\\"+entry.getValue()+".jpg");
            JLabel label = new JLabel(icon);
            answerpanel.add(label);
        }
        answerpanel.revalidate();
        answerpanel.repaint();
        majiang.check=0;
    }

    class wap extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);//can close the window
        }
    }
}

class Inside{

    public static void listenWhat( HashMap< String, List<Character>> input){
        List<Character>[] inputArray =new List[4]; //0:coolie  1:million  2:chain  3:BigWord
        List<Character> output = new ArrayList<>() ;
        List<Character> pairOf = new ArrayList<>();
        List<Character> possibility = new ArrayList<>() ;
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
            Collections.sort(inputArray[i]);
        }

        if(count > 2) Background.answer(output);
        

        for(int i = 0;i < 4;i++){
            if(threeOrNot[i] == 0){
                if(Determine.correct3n(inputArray[i]) == false) Background.answer(output);
                else continue;
            }
            else if(threeOrNot[i] == 1){
                pairOf = Determine.pairof(inputArray[i], pairOf);
                possibility = Determine.possibility(inputArray[i], possibility);
            }
            else continue;
         }

         Background.answer(output);
    }
}

class Determine{

    public static List<Character> pairof(List<Character> input, List<Character> pairof){
        List<Character> have = new ArrayList<>();

        for(char i : input){
            if(!have.contains(i))  have.add(i);
            else if(!pairof.contains(i)) pairof.add(i);
        }

        return pairof;
    }

    public static List<Character> possibility(List<Character> input, List<Character> possibility){
  
        
        return possibility;
    }

    public static Boolean correct3n(List<Character> input){
        Boolean answer = false;
        //adddddddddddd
        //@510

        return answer;
    }

}


