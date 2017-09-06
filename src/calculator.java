import java.awt.*; 
import java.awt.event.*; 
import java.applet.*; 
public class calculator extends Frame implements ActionListener{
	Checkbox inv;			// TO find inversen angle(i.e. sin inverse, cos inverse
	Checkbox cb[]=new Checkbox[2]; 	//Radio buttons (to Select between Degree or Radian)
	CheckboxGroup cbg; 		//checkbox group for above radio buttons
	Button sim[] = new Button[42];	//Actual calculator buttons
	Label mst=new Label("");	//Label to check the status of memory variable
	Boolean btnflag=false;		/*flag to check whether the last button pressed was a number(0-9,.) or an operation (+,-,*...etc.)*/
	double angle;			/*variable used for calculations of sin,cos,tan and their inverse*/
	String bfrdt="",mem="0";	/*bfrdt-save first operand of binary operation
					mem- save the memory variable */
	Label res = new Label("0",Label.RIGHT);	//Display of the calculator.
	int j=0;			//stores code for last binary operator.
	String r="0";			//used to store the result of the calculation
	int ky;				//variable to get key code for keyboard operations.
	public calculator() { 		//constructor to initialize variables.
		Panel main = new Panel();	//Main Panel to hold overall elements of the calculator.
        	main.setLayout(new FlowLayout());	//setting the layout(arrangements of elements)
		res.setBounds(0,15,355,30);	/*setting the boundries of the Display.
						left(x)=0	top(y)=15
						width=355	height=30*/
		res.setFont(new Font("Helvetica", Font.PLAIN, 20));	/*setting the font used in the Display.*/
		res.setForeground(Color.black);	//setting the foreground color(color of text)
		res.setBackground(Color.white);	//setting the background color(color behind text)
		Panel cal = new Panel();	//panel to hold display and memory status label
        	cal.setLayout(new BorderLayout(0,15));	//setting the layout.
        	cal.add("North", res);	/*adding diapay label into the panel 'cal' at top location*/
		//main.
		add("North",cal);	//adding cal panel into main panel at center location
		cal.add("North",mst);	/*adding memory status label into the panel 'cal' at top location*/
		Panel rkpan = new Panel();	//panel to store calculator button.
		rkpan.setLayout(new GridLayout(10,5,5,5));	/*setting layout to gridlayout
								for Grid view*/
		add("South",rkpan);	
		for(int i = 0; i < 10; i++)
			sim[i]=new Button(""+i);
		inv = new Checkbox("Inverse"); 
     		cbg= new CheckboxGroup();
     		cb[0]=new Checkbox("Degrees",cbg,true);
     		cb[1]=new Checkbox("Radians",cbg,false);
		sim[10]=new Button("Back");
     		sim[11]=new Button("Clear");
		sim[12]=new Button("MC");
		sim[13]=new Button("MR");
		sim[14]=new Button("MS");
		sim[15]=new Button("M+");
		sim[16]=new Button("sin");
		sim[17]=new Button("cos");
		sim[18]=new Button("tan");
		sim[19]=new Button("x^3");
		sim[20]=new Button("x^2");
		sim[21]=new Button("+/-");
		sim[22]=new Button("sqrt");
		sim[23]=new Button("%");
		sim[24]=new Button("1/x");
		sim[25]=new Button(".");
		sim[26]=new Button("Exp");
		sim[27]=new Button("log");
		sim[28]=new Button("n!");
		sim[29]=new Button("pi");
		sim[30]=new Button("Not");
		sim[31]=new Button("+");
		sim[32]=new Button("-");
		sim[33]=new Button("*");
		sim[34]=new Button("/");
		sim[35]=new Button("x^y");
		sim[36]=new Button("Mod");
		sim[37]=new Button("And");
		sim[38]=new Button("Or");
		sim[39]=new Button("Xor");
		sim[40]=new Button("=");
		sim[41]=new Button("Int");
		for(int i = 0; i < 42; i++) { 
			rkpan.add(sim[i]);
			sim[i].addActionListener(this);
			sim[i].addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent ke){
				ky=(int)ke.getKeyChar();
				for(int i=48,j=0;i<58;i++,j++)
					if(ky==i){
						calcu(j);
						return;
					}				
				switch(ky){
				case 43:calcu(31);
					break;	
				case 45:calcu(32);	
					break;
				case 42:calcu(33);	
					break;
				case 47:calcu(34);	
					break;
				case 38:calcu(37);	
					break;
				case 33:calcu(28);	
					break;
				case 37:calcu(23);	
					break;
				case 61:	
				case 10:
					calcu(40);	
					break;
				case 126:calcu(30);	
					break;
				case 94:calcu(35);	
					break;
				case 8:calcu(10);	
					break;
				case 27:calcu(11);	
					break;
				case 46:calcu(25);	
					break;
				}
			}
			});
		}

		for(int i = 0; i < 2; i++)
			rkpan.add(cb[i]);
		rkpan.add(inv); 
		//cal.add("South",rkpan);
		//setLayout(new BorderLayout(0, 0));
	        //add("North", main);
		addWindowListener(new WindowAdapter(){ 
		public void windowClosing(WindowEvent we) { 
		System.exit(0); 
		} 
		}); 
	} 


public void actionPerformed(ActionEvent ae) { 
	int i;
	for(i=0;i<42;i++) 
		if(ae.getSource() == sim[i])
			break;
	calcu(i);				
} 

public void calcu(int i){
	String txt=res.getText();
	try{
	switch(i){	
	case 0: 
        case 1: 
        case 2: 
        case 3: 
        case 4: 
	case 5: 
        case 6: 
        case 7: 
        case 8: 
        case 9:
		if((txt.equals("0"))||(btnflag))
			r=""+i;
		else
			r=txt+i;
		btnflag=false;
		
		break;
		
	case 25:if(btnflag)
			r="0.";
		else if(txt.indexOf('.')==-1)
			r=txt+".";
		btnflag=false;

		break;

	default:
		btnflag=true;
		switch(i){

	case 10:if(txt.length()==1)
			r="0";
		else
			r=txt.substring(0,txt.length()-1);
		break;

	case 11:r="0";
		j=0;
		break;

	case 12:mem="0";
		mst.setText("");
		break;

	case 13:r=""+mem;
		break;

	case 14:if(txt!="0"){
			mem=txt;
			mst.setText("M");
		}
		break;

	case 15:if(txt!="0"){		
			mem=""+(Double.parseDouble(mem)+Double.parseDouble(txt));	
			mst.setText("M");
		}
		break;

	case 16:if(!inv.getState()){

			if(cbg.getSelectedCheckbox()==cb[0])	
				angle=(Math.PI*(Double.parseDouble(txt))/180);
			else
				angle=Double.parseDouble(txt);
			r=""+Math.sin(angle);
		}
		else{

			if(cbg.getSelectedCheckbox()==cb[0])
				r=""+(Math.asin(Double.parseDouble(txt))*180/Math.PI);
			else
				r=""+Math.asin(Double.parseDouble(txt));
			inv.setState(false);
		}
		break;

	case 17:if(!inv.getState()){

			if(cbg.getSelectedCheckbox()==cb[0])	
				angle=(Math.PI*(Double.parseDouble(txt))/180);
			else
				angle=Double.parseDouble(txt);
			r=""+Math.cos(angle);
		}
		else{

			if(cbg.getSelectedCheckbox()==cb[0])
				r=""+(Math.acos(Double.parseDouble(txt))*180/Math.PI);
			else
				r=""+Math.acos(Double.parseDouble(txt));
			inv.setState(false);
		}
		break;

	case 18:if(!inv.getState()){

			if(cbg.getSelectedCheckbox()==cb[0])	
				angle=(Math.PI*(Double.parseDouble(txt))/180);
			else
				angle=Double.parseDouble(txt);
			r=""+Math.tan(angle);
		}
		else{

			if(cbg.getSelectedCheckbox()==cb[0])
				r=""+(Math.atan(Double.parseDouble(txt))*180/Math.PI);
			else
				r=""+Math.atan(Double.parseDouble(txt));
			inv.setState(false);
		}
		break;

	case 19:r=""+Math.pow((Double.parseDouble(txt)),3);
		break;

	case 20:r=""+Math.pow((Double.parseDouble(txt)),2);
		break;

	case 21:r=""+(0-Double.parseDouble(txt));
		break;

	case 22:r=""+Math.sqrt(Double.parseDouble(txt));
		break;

	case 23:r=""+Float.parseFloat(txt)/100;
		break;

	case 24:r=""+1/Float.parseFloat(txt);
		break;

	case 26:r=""+Math.exp(Double.parseDouble(txt));
		break;

	case 27:r=""+Math.log(Double.parseDouble(txt));
		break;

	case 28:r=""+fact(Integer.parseInt(txt));	
		break;

	

	case 29:r=""+Math.PI;
		break;

	case 30:r=""+(~Integer.parseInt(txt));
		break;
	
	case 41:r=""+Math.rint(Double.parseDouble(txt));
		break;

	case 31:
	case 32:
	case 33:
	case 34:
	case 35:
	case 36:
	case 37:
	case 38:
	case 39:
	case 40:
		switch(j){
		case 31:r=""+(Double.parseDouble(txt)+Double.parseDouble(bfrdt));
			break;

		case 32:r=""+(Double.parseDouble(bfrdt)-Double.parseDouble(txt));
			break;

		case 33:r=""+(Double.parseDouble(bfrdt)*Double.parseDouble(txt));
			break;

		case 34:r=""+(Double.parseDouble(bfrdt)/Double.parseDouble(txt));
			break;

		case 35:r=""+Math.pow(Double.parseDouble(bfrdt),Double.parseDouble(txt));
			break;

		case 36:r=""+(Double.parseDouble(bfrdt)%Double.parseDouble(txt));
			break;

		
		case 37:r=""+(Integer.parseInt(bfrdt)&Integer.parseInt(txt));
			break;

		case 38:r=""+(Integer.parseInt(bfrdt)|Integer.parseInt(txt));
			break;

		case 39:r=""+(Integer.parseInt(bfrdt)^Integer.parseInt(txt));
			break;
		}
		j=i;
		bfrdt=r;
		break;
	}
}
	
	if(r.equals("NaN"))
		r="Invalid input provided for the function";
	int l=r.length();
	if((l>1)&&(r.charAt(l-1)=='0')&&(r.charAt(l-2)=='.'))
		r=r.substring(0,l-2);
	res.setText(r);
	}catch(Exception e){
	bfrdt="0";
	j=0;
	res.setText("Invalid input provided for the function");	
	}
}

double fact(int n)
{
	if(n==0)
		return(1);
	else
		return(n*fact(n-1));
}
  

public static void main(String args[]) { 
	calculator cal = new calculator();  
	cal.setSize(370,350); 
	cal.setTitle("Calculator By Sumit Beniwal");
	cal.setFont(new Font("SansSerif", Font.BOLD, 12));
	cal.setBackground(Color.gray);
	cal.setVisible(true);
	cal.setResizable(false);
	} 
}