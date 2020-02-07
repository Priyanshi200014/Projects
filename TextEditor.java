import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.io.*;
  
public class TextEditor extends JFrame implements ActionListener{ 

 JTextArea t1;
 JComboBox menu;
 TextEditor() 
{

    Container c=getContentPane();	
    c.setLayout(new FlowLayout());
     
	 t1=new JTextArea("",22,72);
	 t1.setLineWrap(true);
	 //t1.setPreferredSize(new Dimension(1325,600));
     t1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
	 
	 
     
	 menu=new JComboBox();
	 menu.setPreferredSize(new Dimension(1325,40));
	 menu.addItem("File");
	 menu.addItem("New");
	 menu.addItem("Save");
	 menu.addItem("Open");
	 menu.addItem("Close");
 
    JScrollPane sp = new JScrollPane(t1);
     sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Font f1=new Font("Sans-Serif",Font.PLAIN,21);
	    Font f2=new Font("Sans-Serif",Font.PLAIN,16);
		
        t1.setFont(f1);
	  menu.setFont(f2);
	
	 c.add(menu);
	 //c.add(t1);
	 c.add(sp);
	 
	 setSize(400,400);
	 setVisible(true);
	 setTitle("NotePad");
   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu.addActionListener(this);
       
} 
  
  public void actionPerformed(ActionEvent e)
   {
	   String str=(String)menu.getSelectedItem();
	   
		 if(str=="Save")
		 { 
		   create();
		 }
		 else if(str=="Open")
		 {  
	 
			open();
		 }
		 else if(str=="New")
		 {
			 setTitle("*New");
			 String txt=t1.getText();
			
			 if((txt.length()==0))
			 { 
			    t1.setText(" ");
			 }
			 else
			 {
				 int a=JOptionPane.showConfirmDialog(this,"Do you Want to Save File ?");
				 
				 if(JOptionPane.YES_OPTION==a)
				 {
					 create();
				 }
				 else if (JOptionPane.NO_OPTION==a)
				 {
					 t1.setText(" ");
				 }
				 else
				 {
					 t1.setText(txt);
				 }
				 
				 
			 }
		 }
		 else if(str=="Close")
		 {
			 System.exit(0);
		 }
	}
	
	public void create()
	{
		String name="Enter File Name ";
		String input =JOptionPane.showInputDialog(null,name);
		setTitle(input);
		try
		{
		FileOutputStream f=new FileOutputStream(input);
		String code=t1.getText();
		byte b[]=code.getBytes();
		
		for(int i=0;i<b.length;i++)
		{
		      f.write(b[i]); 
		}
		
		JOptionPane.showMessageDialog(this," Your Code is saved !!!");
		f.close();
		
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void open()
	{
		String name="Enter File Name ";
		String input =JOptionPane.showInputDialog(null,name);
		setTitle(input);
		String file="";
		try
		{
		FileInputStream f=new FileInputStream(input);
		int i=0;
		char y;
		String x;
		while((i=f.read())!=-1)
		{
			y=(char)i;
			x=Character.toString(y);
			file=file.concat(x);
			if(i==(-1))
			{
				t1.setText(Integer.toString(i));
			}
			
		}
		
		f.close();
		}
		catch(Exception e)
		{
			
		}
		t1.setText(file);
	}

   
    public static void main(String args[]) 
    { 
        new TextEditor(); 
    } 
} 

