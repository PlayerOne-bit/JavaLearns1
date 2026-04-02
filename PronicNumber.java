
public class PronicNumber {
	
	static String isPronic(int number) {
		int count=0;
		String result="Not Pronic";
		while(count<100) {
			if(number==count*(count+1)) {
				result= "Pronic";
				break;
			}
			System.out.println(count);
			count++;
			System.out.println(count+"\n");
		}
		return result;
	}
	
	public static void main(String[] args) {
		int z=2;
		System.out.println(isPronic(z)+": "+z);
	}

}