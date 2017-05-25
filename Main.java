import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//VARIABLES
		Scanner scanner = new Scanner(System.in);
		int power;
		int diff;
		int ctr;
		String ans = "";
		int x;
		int y;
		String c = "",d = "", q = "";
		String a, b, p, oper;
		String product, modp;
		int[] aarray;
		int[] barray;
		String[] astringarray;
		String[] bstringarray;
		
		//INPUT
		System.out.println("input a(x)");
		a = scanner.nextLine();
		System.out.println("input b(x)");
		b = scanner.nextLine();
		System.out.println("input p(x)");
		p = scanner.nextLine();
		System.out.println("input operation + - * /");
		oper = scanner.next();
		System.out.println("asd" + oper + "asd");
		
		//SETTING UP NUMBERS
		if (a.length() > b.length())
			power = a.length() - a.replace(" ", "").length() + 1;
		else
			power = b.length() - b.replace(" ", "").length() + 1;
		
		astringarray = a.split(" ");
		bstringarray = b.split(" ");
		a = a.replace(" ", "");
		b = b.replace(" ", "");
		p = p.replace(" ", "");
		
		aarray = new int[a.length()];
		barray = new int[b.length()];
		for (ctr = 0; ctr < a.length(); ctr++) {
			aarray[ctr] = Integer.parseInt(astringarray[ctr])%2;
		}
		for (ctr = 0; ctr < b.length(); ctr++) {
			barray[ctr] = Integer.parseInt(bstringarray[ctr])%2;
		}
		
		for (ctr = 0; ctr < a.length(); ctr++) {
			c = c + String.valueOf(Integer.valueOf(a.charAt(ctr))%2);
		}
		for (ctr = 0; ctr < b.length(); ctr++) {
			d = d + String.valueOf(Integer.valueOf(b.charAt(ctr))%2);
		}
		for (ctr = 0; ctr < p.length(); ctr++) {
			q = q + String.valueOf(Integer.valueOf(p.charAt(ctr))%2);
		}
		a = c;
		b = d;
		p = q;
		
		if (a.length() > b.length()) {
			diff = a.length()-b.length();
			for (ctr = 0; ctr < diff; ctr++) {
				b = "0" + b;
			}
		} else if (a.length() < b.length()) {
			diff = b.length() - a.length();
			for (ctr = 0; ctr < diff; ctr++) {
				a = "0" + a;
			}
		}
		System.out.println();
		
		//CALCULATION
		//ADDITION
		if (oper.contains("+") || oper.contains("-")) {
			for (ctr = 0; ctr < power; ctr++) {
				x = Integer.valueOf(a.charAt(ctr));
				y = Integer.valueOf(b.charAt(ctr));
				ans = ans + ((x+y)%2);				
			}
			System.out.println("  " + a + "\n" + oper + " " + b + "\n" + "= " + ans);
		
		
		//MULTIPLICATION
		} else if (oper.contains("*")) { 
			product = Integer.toBinaryString(Integer.parseInt(a, 2) * Integer.parseInt(b, 2));			
			System.out.println("  " + a + "\n" + oper + " " + b + "\n" + "= " + product);
			
			String[] productarray = product.split("");
			String[] parray = p.split("");
			aarray = new int[product.length()];
			barray = new int[p.length()];
			for (ctr = 0; ctr < product.length(); ctr++) {
				aarray[ctr] = Integer.parseInt(productarray[ctr]);
			}
			for (ctr = 0; ctr < p.length(); ctr++) {
				barray[ctr] = Integer.parseInt(parray[ctr]);
			}
			
			System.out.println("\nproduct / p(x) = [quotient, remainder(ANSWER)]\n");
			System.out.printf("%s / %s = %s",
	                Arrays.toString(aarray),
	                Arrays.toString(barray),
	                Arrays.deepToString(extendedSyntheticDivision(aarray, barray)));
			
			
		//DIVISION
		} else if (oper.contains("/")) {
			System.out.println("numerator / divisor = [quotient, remainder]\n");
			if (Integer.parseInt(a, 2) < Integer.parseInt(b, 2)) {
				System.out.println("By inspection, result = 0 and remainder = the numerator.");
				
			} else {
				System.out.printf("%s / %s = %s",
		                Arrays.toString(aarray),
		                Arrays.toString(barray),
		                Arrays.deepToString(extendedSyntheticDivision(aarray, barray)));
			}	
		}		
	}
	
	static int[][] extendedSyntheticDivision(int[] dividend, int[] divisor) {
        int[] out = dividend.clone();
        int normalizer = divisor[0];
 
        for (int i = 0; i < dividend.length - (divisor.length - 1); i++) {
            out[i] /= normalizer;
 
            int coef = out[i];
            if (coef != 0) {
                for (int j = 1; j < divisor.length; j++)
                    out[i + j] += -divisor[j] * coef;
            }
        }
 
        int separator = out.length - (divisor.length - 1);
        
        for (int ctr = 0; ctr < out.length; ctr++) {
        	while (out[ctr] < 0) {
        		out[ctr] += 2;
        	}
        	out[ctr] = out[ctr]%2;
        }
 
        return new int[][]{
            Arrays.copyOfRange(out, 0, separator),
            Arrays.copyOfRange(out, separator, out.length)
        };
    }
}
