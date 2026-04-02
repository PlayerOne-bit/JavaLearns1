
public class SwitchChars {
	    public static String doubleCharacterSwap(String str, char c1, char c2) {
	        String str1 = "";
	        for(int i=0;i<str.length(); i++){
	            if(str.charAt(i)==c2){
	                str1+=c1;
	            }else
	            if(str.charAt(i)==c1){
	                str1+=c2;
	         	}else {
	         		str1+=str.charAt(i);
	         	}
	        }
	        	
	        return str1;
	    }
	    public static void main(String[] args) {
	    	System.out.println(doubleCharacterSwap("abcdefghijklmnopqrstuvwxyz",'z','s'));}
	
}
