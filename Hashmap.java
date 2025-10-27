import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacVerification {

    private static final String HMAC_ALGORITHM = "HmacSHA256";

    public static String generateHmac(byte[] keyBytes, byte[] messageBytes) 
            throws NoSuchAlgorithmException, InvalidKeyException {
        
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, HMAC_ALGORITHM);
        
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        
        mac.init(secretKeySpec);
        
        byte[] hmacBytes = mac.doFinal(messageBytes);
        
        return HexFormat.of().formatHex(hmacBytes);
    }

    public static void main(String[] args) {
        System.out.println("\n--- Shravani Sachin Patil , 24UAM317 ---\n");
        System.out.println("\nName = Shravani Sachin Patil");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter secret key: ");
        String keyString = scanner.nextLine();
        
        System.out.print("Enter message: ");
        String messageString = scanner.nextLine();
        
        scanner.close();

        byte[] key = keyString.getBytes();
        byte[] message = messageString.getBytes();
        byte[] tempMessage = "tampered text".getBytes();
        
        try {
            String hmacValue = generateHmac(key, message);
            
            System.out.println("\nOriginal Message: " + new String(message));
            System.out.println("Generated HMAC: " + hmacValue);

            String receiverHmac = generateHmac(key, tempMessage);
            
            if (hmacValue.equals(receiverHmac)) {
                System.out.println("Message verified successfully (untampered).");
            } else {
                System.out.println("Verification failed (message altered).");
            }

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
