import java.awt.*;
import java.util.*;

public class ColorDisplay {
  public static final Scanner CONSOLE = new Scanner(System.in);
  
  public static void main(String[] args) {
    System.out.println("This project written by Kishan Patel\n");
    Color c1 = readColor();
    String s1 = colorToString(c1);
    System.out.println("The first color is " + s1 + "\n");
    Color c2 = readColor();
    String s2 = colorToString(c2);
    System.out.println("The second color is " + s2 + "\n");
    Color c3 =readColor();
    String s3 = colorToString(c3);
    System.out.println("The third color is " + s3 + "\n");
    Color c4 =readColor();
    String s4 = colorToString(c4);
    System.out.println("The fourth color is " + s4 + "\n");
    DrawingPanel panel = new DrawingPanel(550,550);
    Graphics g = panel.getGraphics();
    ReplaceMe.displayColors(g, c1, c2, c3, c4);
  }
  
  public static Color readColor (){
   System.out.println("Enter a color (three values between 0 and 255) : ");
   int red = CONSOLE.nextInt();
   int green = CONSOLE.nextInt();
   int blue = CONSOLE.nextInt();
   Color mynew = new Color (red, green, blue);
   return mynew;
   
}
public static String colorToString(Color c) {
   int red = c.getRed();
   int green = c.getGreen();
   int blue = c.getBlue();
   String myString = "The second Color is [r="+red+",g="+green+",b="+blue+"]: ";
   
   
   return myString;
   
}

public static void displayColors (Graphics g, Color c1, Color c2, Color c3, Color c4) {
   int red = c1.getRed();
   int green = c1.getGreen();
   int blue = c1.getBlue();
   int r2= c2.getRed();
   int g2= c2.getGreen();
   int b2=c2.getBlue();
   int r3= c3.getRed();
   int g3= c3.getGreen();
   int b3= c3.getBlue();
   int r4= c4.getRed();
   int g4= c4.getGreen();
   int b4= c4.getBlue();
   
   
   
   
   for (int i=0; i<10; i++){
      for (int j=0; j<10; j++) {
         int q1=(10-i) * (10-j);
         int q2=(10-i) * j;
         int q3= i * j;
         int q4 =i *j; 
         
         
   int newRed= (q1 *red+q2*r2+q3*r3+q4*r4)/(q1+q2+q3+q4);
   int newGreen=(q1 *green+q2*g2+q3*g3+q4*g4)/(q1+q2+q3+q4);
   int newBlue= (q1 *blue+q2*b2+q3*b3+q4*b4)/(q1+q2+q3+q4);
   
   
   Color mynew = new Color (red, green, blue);
   g.setColor(mynew);
   g.fillRect(i*50,j*50,50,50);
   g.setColor(c2);
     g.fillRect(500,0,50,50);
     g.setColor(c3);
     g.fillRect(0,500,50,50);
      }
   }
}
     
}

    