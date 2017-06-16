import java.awt.Robot;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
class Hack_typelab {
	// Main:
	public static void main(String[] args)  throws Exception
	{
		// String Es = args[0]; This get the name of exercise 
		String Es = args[0];
		// Initialize a frame, otherwise the program write the ex everywhere
		JTextField textField = new JTextField(10);
		JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    frame.add(textField);
	    frame.pack();
        frame.setLocationRelativeTo( null );
	    frame.setVisible( true );  
		// Open file xml to file String
		File file = new File("esercizi\\" + Es);
		// Create new object of class DocumentBuilderFactory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		.newInstance();
		// create new document
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		// Parse the file
		Document document = documentBuilder.parse(file);
		// get the text by the tag name 
		String text = document.getElementsByTagName("texte").item(0).getTextContent();
	    // create robot objects, This beacause the vk_enter don't work if i press another key
		Robot robot = new Robot();
		Robot enter = new Robot();
		Robot enter2 = new Robot();
		Robot robot2 = new Robot();
		// Read the text line by line 
		Scanner scanner = new Scanner(text);
		robot.delay(10000);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
		// Control if line is empty
			if(line.length()==0)
			{ 
		// delay of 20ms
				enter2.delay(20);
		    	enter2.keyPress(KeyEvent.VK_ENTER);
				enter2.keyRelease(KeyEvent.VK_ENTER);
				continue;
			}	
        // get characters of line 			
		    for (int i=0; i<line.length(); i++)
			{
				try{
					// convert characters in ASCII code
					int code = (int) line.charAt(i);
					// check if characters is uppercase
				    boolean uppercase = Character.isUpperCase(line.charAt(i));
					 // keycode only handlnes [A-Z] (which is ASCII decimal [65-90])
					if (code > 96 && code < 123) code = code - 32;
					// if uppercase is true then press Shift and code
					// robot.keypress(code) = code is ASCII code but don't support special
					// character
					if (uppercase)
					{
						robot2.delay(10);
						robot2.keyPress(KeyEvent.VK_SHIFT);
						robot2.keyPress(code);
						robot2.keyRelease(code);
						robot2.keyRelease(KeyEvent.VK_SHIFT);
					}
					else
					{
						robot.delay(10);
						robot.keyPress(code);
						robot.keyRelease(code); 
					}
					}
					// If the character is a special character like(è, à ...) i get an error.
					// Typelab doesn't support the ALT + NUMPAD(ASCII CODE)
				    catch(IllegalArgumentException e)
				    {
					// Press space if character is special character
				    	robot.delay(10);
			    	    robot.keyPress(KeyEvent.VK_SPACE);
				    	    	/*
				    	    	 if(line.charAt(i)=='à')
						    	    {
				    	    			robot.delay(40);
				    	    			robot.keyPress(KeyEvent.VK_ALT);
				    	    			robot.keyPress(KeyEvent.VK_NUMPAD1);
				    	    			robot.keyPress(KeyEvent.VK_NUMPAD3);
				    	    			robot.keyPress(KeyEvent.VK_NUMPAD3);
				    	    			robot.keyRelease(KeyEvent.VK_NUMPAD1);
				    	    			robot.keyRelease(KeyEvent.VK_NUMPAD3);
				    	    			robot.keyRelease(KeyEvent.VK_NUMPAD3);
				    	    			robot.keyRelease(KeyEvent.VK_ALT);
						    	    }
						    	    */
				    	    }
				    }
				  enter.keyPress(KeyEvent.VK_ENTER);
				  enter.keyRelease(KeyEvent.VK_ENTER); 
			}
			scanner.close();    	
	}
}
