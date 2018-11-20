import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt. *;
import java.awt. event.*;
import java.sql.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class QuestionSeries{
    static String  userName;
     static int ID;
    public void name(String nmae, int id){
    userName = nmae;
    ID = id;
    
    
    
}
    static String info ="Java Online Test Week  \n \nINSTRUCTIONS:\nThere are 25 questions in this test and 10 minutes to complete them all.\nThe finish button is highlighted in green when you reach the end of the test. \nClicking the finish button will display the results with the correct answers marked in light red.  \n \nThe timecounter  begins when you click on the 'start' button \n \nBest of luck!\n";
int i = 0;
int j = 0;

 static String []question = new String[20];

 static String [][]answers  =  new String[20][4];
 static int []n = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
static String []choice= new String[20];
static int tally = choice.length;
static String testtitle;//="GK QUIZ WITH LOGIN FACILITY";
static int timeLimit =10;
static int passMark = 33;
PreparedStatement ps;


   
    public  void jdbc(int Choice){
    try {
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/doltzquiz";
String pass = "root";
String user = "root";
Connection con=(Connection) DriverManager.getConnection(url,pass,user);
switch(Choice){
         case 1: {ps = con.prepareStatement("Select * from gkqna");
                        testtitle =    "GK QUIZ ";}
                        break;
         case 2: {ps = con.prepareStatement("Select * from sportsqna");
                   testtitle =    "Science QUIZ";}
                        break;
         case 3: {ps = con.prepareStatement("Select * from scienceqna");
                   testtitle =    "Sports QUIZ";}
                        break;
         case 4: {ps = con.prepareStatement("Select * from moviesqna");
                   testtitle =    "Movies QUIZ";}
                        break;
                        
         
}
  ResultSet rs  = ps.executeQuery();
while(rs.next()){
    
    
question[i] = rs.getString(2);
System.out.println("Question:" + question[i]);
int a = 3;
for(j=0;j<4;j++)
{
answers [i][j] =rs.getString(a);
a++;
}

choice[i] = rs.getString(7);
i++;

}

}
catch (ClassNotFoundException | SQLException e){
e.printStackTrace();
}
    }
    
   

    

}



public class OnlineTest extends JFrame{
    static String studentname ="";
    static int TOTAL=0;

    
int Numoftests;
int testpass;



	static {
	try{
	TOTAL = QuestionSeries.tally;		
        start st = new start();
        
	
	studentname =  QuestionSeries.userName;
        System.out.println(studentname);
	if(studentname.length() < 1) studentname = JOptionPane.showInputDialog("Enter your name first ");
	else studentname = studentname.trim() + " ";
	}catch(NullPointerException e){ System.exit(0); }
	}	

    	int seconds, minutes;
    	int quesnum, itemCheck, mark; 
	final String TESTTITLE = QuestionSeries.testtitle;
    	final int TIMELIMIT = QuestionSeries.timeLimit;
    	final int PASS = QuestionSeries.passMark;
    	String []answers = new String[TOTAL];
	JButton []choice_button = new JButton[6];
	JTextArea answerboxes[] = new JTextArea[4];
	JCheckBox []boxes = new JCheckBox[4];
    	JTextPane pane = new JTextPane();
	JLabel student, choose, message, timecounter, testresult;
    	boolean start_test, check_answer, allowRestart, finishtest;
	Northwindow panelNorth = new Northwindow();
	Southwindow panelSouth = new Southwindow();
	Centerwindow panelCenter = new Centerwindow();


	protected OnlineTest(){
        for (int i=0; i<TOTAL; i++) answers[i] ="";
		getContentPane().setLayout(new BorderLayout() );
		getContentPane().add("North", panelNorth);
		getContentPane().add("South", panelSouth);
		getContentPane().add("Center", panelCenter);
		int width = 0, height=0; 
	        if(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()<799){width=		640; 		height=460; }
	        else {width=720; height=540; }
		setSize(width,height);
		Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width-width)/2, (dim.height-height)/2);
	}



class Northwindow extends JPanel{


    public Northwindow(){
		setLayout(new GridLayout(2,2));
		setBackground(new Color(230, 230, 255));
		student = new JLabel("\t Welcome : "+studentname+" to the GK QUIZ");
		student.setFont(new Font("",Font.BOLD,16) );
		message = new JLabel();
		message.setForeground(Color.blue);
		add(student);
		add(message);
		add(new JLabel("               ") );
		add(new JLabel("               ") );
        setBorder(BorderFactory.createEtchedBorder() );
	}
}


class Southwindow extends JPanel{
	public Southwindow(){
		String []key = {"","start:","next:","finish:","check next:","check previous:"};
			for(int i=0; i<choice_button.length; i++)
                   {
				choice_button[i] = new JButton(key[i]);
				choice_button[i].addActionListener(new ActionHandler() );
				if(i !=0)add(choice_button[i]);
			  }
        setBorder(BorderFactory.createEtchedBorder() );
	}
}


class Centerwindow extends JPanel{

	public Centerwindow(){
		setLayout(new GridLayout(1,2) );
		JScrollPane west = new JScrollPane(pane);       
		pane.setForeground(Color.red);	       
   		pane.setFont(new Font ("monospaced",0,12) );
		pane.setText(QuestionSeries.info);
		pane.setEditable(false);
		JPanel east = new JPanel();
		east.setLayout(new BorderLayout() );	
		JPanel northEast = new JPanel();
		northEast.setBackground(new Color(230, 230, 255) ); 
		east.add("North", northEast);
		JPanel westEast = new JPanel();
		westEast.setLayout(new GridLayout(6,1) );
		east.add("West", westEast);
		JPanel centerEast = new JPanel();
		centerEast.setLayout(new GridLayout(6,1) );
		centerEast.setBackground(new Color(255,255,200));
		east.add("Center", centerEast);       
			timecounter = new JLabel("     There are "+TOTAL+" questions in total");
			timecounter.setFont(new Font ("Arial",Font.BOLD,16) );
			timecounter.setForeground(new Color(0,90,20) );
			northEast.add(timecounter);
			westEast.add(new JLabel(" "));
        String []boxs = {" A ", " B ", " C ", " D "};
			for(int i=0; i<boxes.length; i++) { 
				boxes[i] = new JCheckBox(boxs[i]);
				boxes[i].addItemListener(new ItemHandler() );
				westEast.add(boxes[i]);
			}
        westEast.add(new JLabel() );
		choose = new JLabel("    CHOOSE CORRECT ANSWERS");
		choose.setBorder(BorderFactory.createEtchedBorder() );
		centerEast.add(choose);
        JScrollPane panes[] = new JScrollPane[4];
			for(int i=0; i<answerboxes.length; i++){
				answerboxes[i] = new JTextArea();
			    answerboxes[i].setBorder(BorderFactory.createEtchedBorder() );
				answerboxes[i].setEditable(false);
				answerboxes[i].setBackground(Color.white);
				answerboxes[i].setFont(new Font("",0,12) );
	            answerboxes[i].setLineWrap(true);      
                answerboxes[i].setWrapStyleWord(true);
                panes[i] = new JScrollPane(answerboxes[i]);
			    centerEast.add(panes[i]);
			}
		if(TIMELIMIT >0)testresult = new JLabel(studentname+",   You have only : "+TIMELIMIT+" minutes to complete.");   
		else testresult = new JLabel("     There is no time limit for this test");
		testresult.setBorder(BorderFactory.createEtchedBorder() );
		centerEast.add(testresult);
	add(west);
	add(east);
	}
}


class ActionHandler implements ActionListener{


 public  void jdbc(int Choice){
    try {
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/doltzquiz";
String pass = "root";
String user = "root";
Connection con=(Connection) DriverManager.getConnection(url,pass,user);

PreparedStatement ps = con.prepareStatement("Select *  From test Where id  = ?");
ps.setInt(1, QuestionSeries.ID);
  ResultSet rs  = ps.executeQuery();
  Numoftests  = rs.getInt(2);
testpass  = rs.getInt(3);
    
    }


catch (ClassNotFoundException | SQLException e){
e.printStackTrace();
}
    }

	public void actionPerformed(ActionEvent evt){
	  String source = evt.getActionCommand();
		if(source.equals("start:")){
			choice_button[1].setVisible(false);
			start_test=true;
			allowRestart=true;
            if(TIMELIMIT >0)new Timer(); 
			panelSouth.remove(choice_button[1]); 
            displayquestion();            
        }
	if(start_test){
		if(source.equals("previous:"))  {            			
			recordanswer();
			quesnum--;
    		if(quesnum ==  -1) quesnum=TOTAL-1;
			checkteststatus();
            displayquestion(); 
		}
		if(source.equals("next:")) {
			recordanswer();
			quesnum++;            
			if(quesnum ==  TOTAL-1) finishtest=true;
			if(quesnum ==  TOTAL) quesnum=0;
            checkteststatus();
            displayquestion(); 
		}
		if(source.equals("finish:")) {
			if (finishtest){
				recordanswer();
				quesnum = 0;

				choice_button[4].setBackground(Color.lightGray);
				timecounter.setForeground(Color.blue);
				timecounter.setFont(new Font ("Arial",0,14) );
				start_test=false; 
				check_answer=true;
				panelSouth.add(choice_button[0]);
				mark_ques();
				displayquestion();
				checkteststatus();
				calculateResult();
			}
			else  JOptionPane.showMessageDialog(null,"Cycle through all questions before pressing finish",
																 "User Message",JOptionPane.INFORMATION_MESSAGE);
		}
	} 

	if (check_answer){
		if(source.equals("check next:")) {
			quesnum++;
			if(quesnum ==  TOTAL) quesnum=0;
			mark_ques();
			displayquestion();
			checkteststatus();
		}
		if(source.equals("check previous:")) {
			quesnum--;
			if(quesnum ==  -1) quesnum=TOTAL-1;
			mark_ques();
			displayquestion();
			checkteststatus();
		}
	}
	validate();        
	}



class Timer extends Thread implements Runnable{
	public Timer(){
		new Thread(this).start();
	}

    public void run() {
		while(start_test){
            try {
               Thread.sleep(1000);
               seconds++;
				if(seconds % 60 == 0 && seconds != 0){
                    seconds -= 60;
                    minutes++;
				}
				timecounter.setText("    Time Counter:  "+minutes+" mins : "+seconds+" secs ");
				if(minutes==TIMELIMIT){
					start_test=false;
					endTest();
                }
		    }
            catch(InterruptedException ex)  { System.out.print(ex); }
		}
    }
}



		public void checkteststatus(){
		if((quesnum ==  TOTAL-1)&&(start_test))choice_button[3].setBackground(Color.green);
		else choice_button[4].setBackground(Color.lightGray);
      	  if(answers[quesnum].length() >0){ 
			for(int i=0; i<answers[quesnum].length(); i++)
			boxes[Integer.parseInt(answers[quesnum].substring(i,i+1) )-1].setSelected			(true);
		}
		else for(int i=0; i<boxes.length; i++)boxes[i].setSelected(false);
		}



	public void displayquestion(){
        int j = quesnum+1;
		pane.setText(QuestionSeries.question[quesnum]);
		if(start_test)message.setText("Question "+j+" out of "+TOTAL);
        for (int i=0; i<4; i++)answerboxes[i].setText(QuestionSeries.answers[quesnum][i]);
		if(start_test){
            String temp="";
			if(QuestionSeries.n[quesnum]==1) temp="<html>&nbsp;&nbsp;&nbsp;Choose only 		<b>ONE</b>  Option</html>";
			else if(QuestionSeries.n[quesnum]==2) temp="<html>&nbsp;&nbsp;Choose <b>TWO		</b> Options</html>";
		else if(QuestionSeries.n[quesnum]==3) temp="<html>&nbsp;&nbsp;Choose <b>THREE</b> 		Options</html>";
            else temp="<html>&nbsp;&nbsp;<b>ALL are true</b> true</html>";
		choose.setText(temp);
		}
		else {
		timecounter.setText("    Your choices are shown in the boxes");
            choose.setText("    Correct answers are marked in light red.");
		}
	}



	public void recordanswer(){
		String tmp = "";
		for(int i=0; i<boxes.length; i++) if(boxes[i].isSelected() ) tmp +=i+1;
        answers[quesnum] = tmp;
    }



	public void endTest(){
		message.setText("TIME OVER: please press 'finish'");
        choice_button[2].setEnabled(false); 
        choice_button[3].setEnabled(false); 
        choice_button[4].setEnabled(true); 
	}



	public void mark_ques(){
		for(int i=0; i<answerboxes.length; i++) answerboxes[i].setBackground(Color.white);
		for(int i=0; i<QuestionSeries.choice[quesnum].length(); i++)
			answerboxes[Integer.parseInt(QuestionSeries.choice[quesnum].substring(i,i+1))-1].setBackground(Color.red);
		if(QuestionSeries.choice[quesnum].equals(answers[quesnum])) message.setText("Answer correct, well done!");
		else message.setText("Sorry, you got this one wrong.");
	}


	public void calculateResult(){
            
              
		mark=0;
		double temp=0.0;
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.#");
		for(int i=0; i<TOTAL; i++)if(QuestionSeries.choice[i].equals(answers[i]))mark++;
		temp=(double)mark;
                
                Numoftests++;
		
                if(temp/TOTAL*100 >=PASS) {
                    testpass++;
                    testresult.setText("  Well done "+studentname.substring(0,studentname.indexOf(' ') )+", you passed");
                
                
                }
                else testresult.setText("  Better luck next time "+studentname.substring(0,studentname.indexOf(' ') ) );
		student.setText(" Final score for "+studentname+":  "+mark+" out of "+TOTAL+":  "+df.format(temp/TOTAL*100)+"%");
		
                new Resultwindow().show();
                try {
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/doltzquiz";
String pass = "root";
String user = "root";
Connection con=(Connection) DriverManager.getConnection(url,pass,user);

PreparedStatement ps = con.prepareStatement("update test set total_Test_Taken  = ?, tests_passed = ? Where id = ? ");
ps.setInt(1, Numoftests);
ps.setInt(2, testpass);
ps.setInt(3, QuestionSeries.ID);
  ps.execute();
  
    
    }


catch (ClassNotFoundException | SQLException e){
e.printStackTrace();
}
	}
}



class Resultwindow extends JFrame{
		Resultwindow() {
      	super( studentname+" results: " +(mark*100/TOTAL >=PASS?"PASS":"FAIL") );
  		Container cont = getContentPane();
		cont.setLayout(new GridLayout(TOTAL/2+3,5,2,5) );
		cont.setBackground(new Color(255,220,255) );
		cont.add(new JLabel("  "+"Marks:    "+mark+"/"+TOTAL+": "+"Percentage:  "+(mark*100/TOTAL)+"%") );
           for(int i=0; i<3; i++)cont.add(new JLabel() );
      	  String temp[] = new String[TOTAL];
			for(int i=0; i<TOTAL; i++){
				if(QuestionSeries.choice[i].equals(answers[i])) temp[i]="correct";
				else temp[i]="wrong";
			}
			for(int i=0; i<TOTAL; i++) cont.add(new JLabel("  Question "+(i+1)+":  "+temp		[i]) );
		pack();
		setLocation(200,200);
	}
}



class ItemHandler implements ItemListener{
	public void itemStateChanged(ItemEvent evt){
	  if(start_test){
		for(int i=0; i<boxes.length; i++) if(boxes[i].isSelected() ) itemCheck++; 
		if(itemCheck > QuestionSeries.n[quesnum]){
			java.awt.Toolkit.getDefaultToolkit().beep();
            if(QuestionSeries.n[quesnum]==1)	JOptionPane.showMessageDialog(null,"<html><font 		size='4' color='00308a'><center>"+
      	"There is only "+QuestionSeries.n[quesnum]+" possible<br> answer to question "+(quesnum+1)+
                    "<html>","User Information Message",JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(null,"<html><font size='4' color='00308a'><center>"+
                    "There are only "+QuestionSeries.n[quesnum]+" possible<br> answers to question "+(quesnum+1)+
                    "<html>","User Information Message",JOptionPane.INFORMATION_MESSAGE);
		}
		itemCheck=0;
	  }	  
	}
}


	public static void main(String [] args){
           
		OnlineTest frame = new OnlineTest();
		frame.setTitle("    "+QuestionSeries.testtitle);
		frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
		frame.setVisible(true);
    }
}