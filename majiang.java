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
    private static JFrame frame;
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
        
        JButton nextButton = new JButton("next");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 12));
        nextButton.setBounds(600, 600, 80, 30);
        nextButton.setBackground(new Color(193, 203, 205));
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getContentPane().remove(backgroundLabel);//remove starting image
                getContentPane().remove(nextButton);
                Color background = new Color(253,249,238);//set background color by RGB
                getContentPane().setBackground(background);
                getContentPane().revalidate();
                getContentPane().repaint();
                instruction();

            }
        });
        this.add(backgroundLabel);//show the starting image
        this.add(nextButton);
        //this.setVisible(true);
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    public void instruction() {//Instruction for Use
        JDialog dialog = new JDialog(this, true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());
        createInitialButton();
        createNewButtonPanel();
        createAnswerButtonPanel();
        JButton addButton = new JButton("next");
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
                    List<Character> list=Inside.listenWhat(map);
                    answer(list);
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
                for(Character keyy : time.keySet()){
                    time.put(keyy,0); 
                }
                for(String keyy : map.keySet()){
                    List<Character> value = new LinkedList();
                    map.put(keyy,value); 
                }

            }
        });
        getContentPane().add(addButton);
        getContentPane().add(clearButton);
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
        answerpanel.setBounds(150, 500, 400, 60); 
        answerpanel.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 
        getContentPane().add(answerpanel);

        getContentPane().revalidate();
        getContentPane().repaint();
    }
    public static void answer(List<Character> list){
        HashMap <Character,String> ans=new HashMap<>();
        if(list.size()==0)JOptionPane.showMessageDialog(frame, "No listen!!!!!!");
        //private JPanel answerpanel;
        else{
            String million="abcdefghi";
            String chain="123456789";
            String cookie="mnopqrstu";
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
            if(list.contains('A'))ans.put('A', "east");
            if(list.contains('B'))ans.put('B', "west");
            if(list.contains('C'))ans.put('C', "south");
            if(list.contains('D'))ans.put('D', "north");
            if(list.contains('E'))ans.put('E', "red");
            if(list.contains('F'))ans.put('F', "fa");
            if(list.contains('G'))ans.put('G', "white");
            answerpanel.removeAll();
            for(Map.Entry<Character,String> entry : ans.entrySet()){
                ImageIcon icon = new ImageIcon("image\\"+entry.getValue()+".jpg");
                JLabel label = new JLabel(icon);
                answerpanel.add(label);
            }
            answerpanel.revalidate();
            answerpanel.repaint();
            }
        majiang.check=0;
    }

    class wap extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);//can close the window
        }
    }
}

class Inside{

    public static List<Character> listenWhat( HashMap< String, List<Character>> input){
        System.out.println(input);
        List<Character>[] inputArray =new List[4]; //0:coolie  1:million  2:chain  3:BigWord
        List<Character> output = new ArrayList<>() ;
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
            //System.out.println(threeOrNot[i]);
            //Collections.sort(inputArray[i]);
        }

        if(count > 2) return output;
        
        if(threeOrNot[3] == 1 ){
            HashMap<Character, Integer> times = Determine.timeHashMap(inputArray[3]);
            List <Character> time1Characters = new ArrayList<Character>() ;
            List <Character> time2Characters = new ArrayList<Character>() ;
            int time1 = 0;
            int time2 = 0;
            int time3 = 0;
            int time4 = 0;
            for(Map.Entry<Character, Integer> entry : times.entrySet()){
                if(entry.getValue() == 1) {
                    time1++;
                    time1Characters.add(entry.getKey());
                }
                else if(entry.getValue() == 2) {
                    time2++;
                    time2Characters.add(entry.getKey());
                }
                else if(entry.getValue() == 3) time3++;
                else if(entry.getValue() == 4) time4++;
            }
            if(count == 1){
                System.out.println("only BigWord");
                if(time4 > 0) return output;
                if(time1 == 1 && time2 == 0) output = time1Characters;
                else if(time1 == 0 && time2 == 2) output = time2Characters;
                else return output;
            }
            else if(count == 2){
                System.out.println("Bigword and else");
                if(time4 > 0 ||time1 > 0 || time2 > 1) return output;
                for(int i = 0;i < 3;i++){
                    if(threeOrNot[i] == 0){
                        if(Determine.correct3n(inputArray[i]) == false) return output;
                    }
                    else if(threeOrNot[i] == 1){
                        possibility = Determine.possibility(inputArray[i], possibility);
                        //a pair in bigword
                        for(char j : possibility){
                            List<Character> temp = new ArrayList<>();
                            temp.addAll(inputArray[i]); 
                            temp.add(j);
                            if(Determine.correct3n(temp) == true) output.add(j);
                        }
                        //a pair in else
                        List<Character> pairof = new ArrayList<>();
                        pairof = Determine.pairof(inputArray[i], pairof);
                        for(char j : pairof){
                            List<Character> temp = new ArrayList<>();
                            temp.addAll(inputArray[i]);
                            temp.remove(Character.valueOf(j));
                            temp.remove(Character.valueOf(j));
                            if(Determine.correct3n(temp) == true){
                                output.addAll(time2Characters);
                                break;
                            }
                        }
                    }
                }
                
                
            }
            

        }

        else{
            System.out.println("no bigword");
            List<Character> all = new ArrayList<>();
            for(int i = 0;i < 4;i++){
                if(threeOrNot[i] == 0){
                    if(i == 3){
                        if(Determine.correct3nForBigWord(inputArray[i]) == false) return output;
                        //break;
                    }
                    if(Determine.correct3n(inputArray[i]) == false) return output;
                    else continue;
                }
                else if(threeOrNot[i] == 1){
                    
                    all.addAll(inputArray[i]);
                    possibility = Determine.possibility(inputArray[i], possibility);
                }
                else continue;
            }
            output = Determine.finalAnswer(all, possibility);
        }
        System.out.println(output);
        Collections.sort(output);
        return output;
    }
}

class Determine{

    public static List<Character> finalAnswer(List<Character> all, List<Character> possibility){
        List<Character> output = new ArrayList<>();
        
        for(char i : possibility){
            List<Character> temp = new ArrayList<>();
            List<Character> pairof = new ArrayList<>();
            temp.addAll(all);
            temp.add(i);
            pairof = pairof(temp, pairof);
            for(char j : pairof){
                List<Character> tempAddPossibility = new ArrayList<>();
                tempAddPossibility.addAll(temp);
                tempAddPossibility.remove(Character.valueOf(j));
                tempAddPossibility.remove(Character.valueOf(j));
                if(correct3n(tempAddPossibility) == false) continue;
                else {
                    output.add(i);
                    break;
                }
                
            }
        }
        
        return output;
    }

    public static List<Character> pairof(List<Character> input, List<Character> pairof){
        List<Character> have = new ArrayList<>();

        for(char i : input){
            if(!have.contains(i))  have.add(i);
            else if(!pairof.contains(i)) pairof.add(i);
        }

        return pairof;
    }

   public static List<Character> possibility(List<Character> input, List<Character> possibility){
         Set<Character> set = new HashSet<>(input);
         HashMap<Character, Integer> times=timeHashMap(input);
         Set<Character> tempPos = new HashSet<>();
        for(char testCard:set){
            if(times.get(testCard)!=4) tempPos.add(testCard);
            if(testCard=='1'||testCard=='a'||testCard=='m'){
                char plus=(char)(testCard+1);
                if(times.get(plus)==null) tempPos.add(plus);
            }
            else if(testCard=='9'||testCard=='i'||testCard=='u'){
                char minus=(char)(testCard-1);
                if(times.get(minus)==null) tempPos.add(minus);
            }
            else{
                char plus=(char)(testCard+1);
                char minus=(char)(testCard-1);
                if(times.get(plus)==null) tempPos.add(plus);
                if(times.get(minus)==null)tempPos.add(minus);
            }
        }
        possibility.addAll(tempPos);
        //System.out.println(tempPos);
        return possibility;
    }

    public static Boolean correct3n(List<Character> input){
        Collections.sort(input);
        while (input.size()!=0) {
            if(input.size()<3)return false;
            char theFirstCard=input.get(0);
            if(input.get(2).equals(theFirstCard)){
                input.remove(0);
                input.remove(0);
                input.remove(0);
            }
            else if(input.contains((char)(theFirstCard+1))&&input.contains((char)(theFirstCard+2))){
                input.remove(0);
                for(int i=0;i<input.size();i++){
                    if(input.get(i)==theFirstCard+1){
                        input.remove(i);
                        break;
                    }
                }
                for(int i=0;i<input.size();i++){
                    if(input.get(i)==(theFirstCard+2)){
                        input.remove(i);
                        break;
                    }
                }

            }
            else{
                //System.out.println(input);
                return false;
            }
        }
        return true;
    }
    public static Boolean correct3nForBigWord(List<Character> input){
            Collections.sort(input);
            while (input.size()!=0) {
                if(input.size()<3)return false;
                char theFirstCard=input.get(0);
                if(input.get(2).equals(theFirstCard)){
                    input.remove(0);
                    input.remove(0);
                    input.remove(0);
                }
                else return false;
            }
        return true;
    }

    public static HashMap<Character, Integer> timeHashMap(List<Character> input){
        HashMap<Character, Integer> output = new HashMap<>();
        for(char i: input){
            if(output.get(i) ==null) output.put(i, 1);
            else output.put(i, output.get(i)+1);
        }

        return output;
    }

}
