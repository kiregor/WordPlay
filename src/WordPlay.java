import java.awt.*;
import java.awt.event.*;

class WordPlay implements ActionListener{

	TextField t1, t2;
	Button b1, b2, b3, b4;

	public WordPlay(){
		t1 = new TextField(40);
		t2 = new TextField(40);
		b1 = new Button("Flip Words");
		b2 = new Button("Order Words");
		b3 = new Button("Remove Duplicates");
		b4 = new Button("Clear");
		
		Panel p1 = new Panel(), p2 = new Panel();

		Label l1 = new Label("Input Text:");
		Label l2 = new Label("Output Text:");
		Frame f = new Frame("WordPlay");

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		f.setLayout(new GridLayout(2,1));

		p1.add(l1);
		p1.add(t1);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p2.add(l2);
		p2.add(t2);
		p2.add(b4);

		f.add(p1, BorderLayout.NORTH);
		f.add(p2);
		
		f.setSize(350, 250);
		f.setVisible(true);
	}

	public static void main(String[] args){
		new WordPlay();
	}


	public void actionPerformed(ActionEvent e){

		Button btn = (Button)e.getSource();
		if(btn == b1){
			t2.setText(ReverseWords.wordReverse(t1.getText()));
		}
		if(btn == b2){
			t2.setText(OrderWords.wordOrder(t1.getText()));
		}
		if(btn == b3) {
			t2.setText(DuplicateMethods.removeDuplicates(t1.getText()));
		}
		if(btn == b4){
			t1.setText("");
			t2.setText("");
		}

	}
}

class ReverseWords{

	public static String mark;

	public static String wordReverse(String a){
		String output = "", tempString1, tempString2;
		int count = 0;
		boolean duplicate = false;

		for(int i = 0; i < a.length(); i++){
			if(wordEnd(a.substring(i,i+1))){

				output += reverse(a.substring(count,i), mark);

				count=i+1;
			}

			if(i == a.length()-1){

				output += reverse(a.substring(count,a.length()), mark);
			}
		}

		return output;
	}

	public static boolean wordEnd(String a){
		boolean result = false;

		switch(a){
			case " ":
				mark = " ";
				result = true;
				break;
			case ",":
				mark = " ,";
				result = true;
				break;
			case ".":
				mark = " .";
				result = true;
				break;
			case "!":
				mark = " !";
				result = true;
				break;
		}

		return result;
	}

	public static String reverse(String a, String punc){

		String result = "";

		if(!a.equals("")){
			result+=punc;

			for(int i = a.length(); i>0; i--){
				result+=a.substring(i-1,i);
			}

		}

		return result;
	}
}


class OrderWords{

	public static String mark;

	public static String wordOrder(String a){
		String output = "", tempString1, tempString2;
		int count = 0;
		boolean duplicate = false;

		for(int i = 0; i < a.length(); i++){
			if(wordEnd(a.substring(i,i+1))){

				output = order(a.substring(count,i), output);

				count=i+1;
			}

			if(i == a.length()-1){

				output = order(a.substring(count,a.length()), output);
			}
		}

		return output;
	}

	public static boolean wordEnd(String a){
		boolean result = false;

		switch(a){
			case " ":
				mark = " ";
				result = true;
				break;
			case ",":
				mark = ", ";
				result = true;
				break;
			case ".":
				mark = ". ";
				result = true;
				break;
			case "!":
				mark = "! ";
				result = true;
				break;
		}

		return result;
	}

	public static String order(String a, String output){

		int count = 0;

		if(output.equals("")){
			output+=a + " ";
		}
		else{
			if(!a.equals("")){
				for(int i = 0; i < output.length(); i++){
					if(wordEnd(output.substring(i,i+1))){

						if(output.substring(count, i).length()>=a.length()){
							output = output.substring(0,count) + a + " " + output.substring(count,output.length());
							break;
						}

						count = i+1;
					}

					if(i == output.length()-1){
						if(output.substring(count, output.length()).length()>=a.length()){
							output = output.substring(0,count) + a + " " + output.substring(count,output.length());
							break;
						}
						else{
							output+=a;
							break;
						}
					}
				}
			}
		}

		return output;
	}
}

class DuplicateMethods{

	public static String mark;

	public static String removeDuplicates(String a){
		String output = "";
		int count = 0;

		for(int i = 0; i < a.length(); i++){
			if(wordEnd(a.substring(i,i+1))){

				output = addition(a.substring(count,i), output, mark);

				count=i+1;
			}

			if(i == a.length()-1){

				output = addition(a.substring(count,a.length()), output, mark);
			}
		}

		return output;
	}

	public static boolean wordEnd(String a){
		boolean result = false;

		switch(a){
			case " ":
				mark = " ";
				result = true;
				break;
			case ",":
				mark = ", ";
				result = true;
				break;
			case ".":
				mark = ". ";
				result = true;
				break;
			case "!":
				mark = "! ";
				result = true;
				break;
		}

		return result;
	}

	public static String addition(String a, String output, String punc){
		boolean duplicate = false;
		int count = 0;

		if(!a.equals("")){
			for(int i = 0; i < output.length(); i++){
				if(wordEnd(output.substring(i,i+1))){
					if(output.substring(count, i).equals(a)){
						duplicate = true;
					}
					count = i+1;
				}
			}

			if(!duplicate){
				output+=a + punc;
			}
			else if(!punc.equals(" ")){
				output+=punc;
			}
		}

		return output;
	}

}