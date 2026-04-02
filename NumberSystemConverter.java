import java.util.Scanner;

public class NumberSystemConverter {
	
	public String binaryConvert(int dec) {
		String bin="";
		while(dec>0) {
			bin+=Integer.toString(dec%2);
			
			dec/=2;
			}
		String binary="";
		for(int reverse=bin.length()-1; reverse>=0; reverse--) {
			binary+=bin.charAt(reverse);
		}
		return binary;
	}
	
	public int octadecimalConvert(int dec) {
		int octa=0;
		int next=10;
		while(dec>0) {
			octa+=dec%8 *next;
			next*=10;
			dec/=8;
		}
		return octa/10;
	}
	
	public String hexadecimalConvert(int dec) {
		String hex="";
		int hexa;
		while(dec>0) {
			hexa=dec%16;
			dec/=16;
			for(int test=0;test<16;test++) {
				if(hexa%16==test && test<10) {
					hex+=Integer.toString(test);
					break;
				}else if(hexa%16==test && test>9) {
					if(test==10) {
						hex+="A";
					}else if(test==11) {
						hex+="B";
					}else if(test==12) {
						hex+="C";
					}else if(test==13) {
						hex+="D";
					}else if(test==14) {
						hex+="E";
					}else if(test==15) {
						hex+="F";
					}
					break;
				}
			}
			
			
		}
		String hexadecimal="";
		for(int reverse=hex.length()-1; reverse>=0; reverse--) {
			hexadecimal+=hex.charAt(reverse);
		}
		
		return hexadecimal;
	}
	
	public static void main(String[] args) {
		NumberSystemConverter convert = new NumberSystemConverter();
		Scanner sc = new Scanner(System.in);
		System.out.print("Decimal: ");
		int num=sc.nextInt();
		
		System.out.println("Binary: "+convert.binaryConvert(num));
		System.out.println("Octadecimal: "+convert.octadecimalConvert(num));
		System.out.println("Hexadecimal: "+convert.hexadecimalConvert(num));
		
		
	}
}
