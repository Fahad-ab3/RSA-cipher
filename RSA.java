package Project;

import java.math.BigInteger;
import java.util.Scanner;


public class RSA {


    	public BigInteger PublicKey;
    	public BigInteger n;
    	private BigInteger PrivateKey;
	
    	public RSA() {
		   
	}
	
	
	public void keyGeneration() {
	
	 Scanner in = new Scanner(System.in);

	 System.out.println("Enter the values of p and q respectively: ");

	 BigInteger p=BigInteger.valueOf(in.nextBigInteger().longValue()), q=BigInteger.valueOf(in.nextBigInteger().longValue());
	 n = BigInteger.valueOf(p.longValue() * q.longValue());
	 long phi = (p.longValue()-1)*(q.longValue()-1);
	 long e = 0;
	 int t = 0;
	 
	 BigInteger []choices = new BigInteger [(int)(phi/2)];
		    

	 for(long i = 2; i < phi; i++) {
	     if(gcd(i, phi) == 1){
		 choices [t++] = BigInteger.valueOf(i);
	     }
	 }
	 
	 printChoices(choices, t);
	 
	 e = in.nextLong();
	 if(e == 0) {
	     e = choices[(int)(Math.random()*(t-1))].longValue();
	 }
	 else if (e >=2 && e <phi && gcd(e, phi) == 1){
	     
	 } 
	 else
	     System.out.println("You entered an invalid choice.");
	
	 
	 PublicKey = BigInteger.valueOf(e);
	 PrivateKey = BigInteger.valueOf(findInverse(e, phi));
	 in.close();
	}

	

	
	public BigInteger encrypt(BigInteger M) {
	    
	    BigInteger C = BigInteger.valueOf(M.longValue()).modPow(PublicKey, n);
		    
	    return C;
	}
	
	public BigInteger decrypt(BigInteger C) {
	    
	    BigInteger M = BigInteger.valueOf(C.longValue()).modPow(PrivateKey, n);	    
	    
	    return M;
	}
	
	
	private long gcd(long x, long y) {
	    if(Math.floorMod(x, y) == 0)
		return (y > 0 ? y : - y);	
	    else
		return gcd(y, Math.floorMod(x, y));
	    }
	
		
	 private long findInverse(long a, long m){
	    for (long d = 1; d < m; d++)
		if (Math.floorMod((Math.floorMod(a, m) * Math.floorMod(d, m)), m) == 1)
		    return d;
	    return 1;
	    }
	 

	
	private void printChoices(BigInteger []arr, int t) {
	    System.out.println("Choose e from among the following list of options: ");
	    System.out.print("[");
	    for(int i=0; i<t;i++)
		if(i != t-1)
		    System.out.print(arr[i].longValue() + ",");
		else
		    System.out.println(arr[i].longValue() + "]");
	}
	
	
	public static void main(String[] args) {

	    RSA rsa = new RSA();
	    Scanner in = new Scanner(System.in);
	    

	    System.out.println("Enter your Message: ");
	    
	    BigInteger M = BigInteger.valueOf(in.nextLong()); 

	    System.out.println("The message is: "+ M);
	    rsa.keyGeneration();
	    
	    BigInteger C = rsa.encrypt(M);
	    
	    System.out.println("The cipher is: " + C);
	    
	    System.out.println("The deciphered message is: " + rsa.decrypt(C));
	    System.out.println("The public key is: (" + rsa.n +","+ rsa.PublicKey + ")");
	    System.out.println("The private key is: " + rsa.PrivateKey);
	    
	    in.close();
	
	}

}
