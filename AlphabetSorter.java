
public class AlphabetSorter {

	public static void main(String[] args) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz",
				word="nigga", sorted="";
		
		for(int i=0; i<alphabet.length(); i++) {
			for(int j=0; j<word.length(); j++) {
				if(alphabet.charAt(i)==word.charAt(j)) {
					sorted+=word.charAt(j);
				}
			}
		}
		System.out.println(sorted);
	}

}
