import java.util.Scanner;

public class StringLiteralReader {
	
	 static String Print(String x, String z, int j, String word) {
		 String y="";
		for(int i=0;i<x.length();i++) {	
    		System.out.println(word+x.charAt(i));
    		if(x.charAt(i)==z.charAt(j)) {
    			y+=x.charAt(i);
    			break;
    		}
		}
		return y;
	}
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String bigLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String smallLetter = "abcdefghijklmnopqrstuvwxyz";
    	String symbol = "!@#$%^&*()_+-={}[]:\"'<>?,./\\'`~ ";
    	String number = "1234567890";
    	String all = bigLetter+smallLetter+number+symbol;
    	String word="";
    	
    	System.out.println("[Instruction]\nInput anything and the code\nwill run to determine the string literals.");
    	String finish=sc.nextLine();

    	int x=0;
    	while(true) {
    		word+=Print(all, finish,x, word);
    		x++;
    		
    		if(word.equals(finish)) {
    			break;
    		}
    	}
    	sc.close();
    }
}
