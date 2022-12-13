package saul.rodriguez.naranjo.practica.last.daw.utils.security;

import java.security.SecureRandom;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Esta clase nos permitira cifrar las contraseñas para guardarlas en la base de datos.
 * 
 * @author Saúl Rodríguez Naranjo
 */
public class PasswordHashing {

    private static final int BCRYPT_STRENGTH = 10;
    
    public static String cipherPassword(String password) {
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCRYPT_STRENGTH, new SecureRandom());
        
        return passwordEncoder.encode(password);
        
    }
    
    public static boolean matchesPassword(String candidatePassword, String password) {
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        return passwordEncoder.matches(candidatePassword, password);
        
    }
    
    public static void main(String[] args) {
        
        String testPassword = "Inquisitive123";
        
        String hashedPassword = PasswordHashing.cipherPassword(testPassword);
        
        System.out.println("Contraseña cifrada: " + hashedPassword);
        
        if(PasswordHashing.matchesPassword(testPassword, hashedPassword)) {
            System.out.println("Las contraseñas coinciden");
        }
        
    }
    
}
