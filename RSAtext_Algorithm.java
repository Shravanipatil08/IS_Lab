import java.util.Scanner;

public class RSAtext_Algorithm 
{
    // gcd function
    static int gcd(int a, int b) 
    {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Find modular inverse of e under mod phi
    static int modInverse(int e, int phi) 
    {
        for (int d = 1; d < phi; d++) {
            if ((d * e) % phi == 1)
                return d;
        }
        return -1;
    }

    // Modular exponentiation: (base^exp) % mod
    static int power(int base, int exp, int mod) 
    {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result = (result * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) 
    {
        // Step 1: Take primes from user
        try (Scanner sc = new Scanner(System.in)) 
        {
            System.out.println("\n--- Trupti Rajgonda Zore , 24UAM316 ---\n");

            // Step 1: Take primes from user
            System.out.print("Enter prime number p = ");
            int p = sc.nextInt();
            System.out.print("Enter prime number q = ");
            int q = sc.nextInt();
            sc.nextLine(); 
            
            int n = p * q;
            int phi = (p - 1) * (q - 1);
            
            // Step 2: Choose e
            int e = 2;
            while (e < phi) 
            {
                if (gcd(e, phi) == 1) break;
                e++;
            }
            
            // Step 3: Find d
            int d = modInverse(e, phi);
            
            System.out.println("\nPublic Key (e, n) = (" + e + ", " + n + ")");
            System.out.println("Private Key (d, n) = (" + d + ", " + n + ")");
            
            // Step 4: Input text message
            System.out.print("\nEnter a text message = ");
            String message = sc.nextLine();
            
            // Step 5: Encrypt each character
            int[] encrypted = new int[message.length()];
            for (int i = 0; i < message.length(); i++) 
            {
                int m = (int) message.charAt(i);
                encrypted[i] = power(m, e, n);
            }
            
            System.out.print("Encrypted message = ");
            for (int c : encrypted) 
            {
                System.out.print(c + " ");
            }
            System.out.println();
            
            // Step 6: Decrypt each character
            StringBuilder decrypted = new StringBuilder();
            for (int c : encrypted) 
            {
                int m = power(c, d, n);
                decrypted.append((char) m);
            }
            
            System.out.println("Decrypted message = " + decrypted.toString());
            System.out.println();
        }
    }
}