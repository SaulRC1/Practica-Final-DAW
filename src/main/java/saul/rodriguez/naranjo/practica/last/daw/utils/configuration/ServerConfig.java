package saul.rodriguez.naranjo.practica.last.daw.utils.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import saul.rodriguez.naranjo.practica.last.daw.utils.storage.UserDirectoryBuilder;

/**
 * Esta clase obtendrá toda la configuración proporcionada al servidor, dentro
 * del archivo server-config.properties
 * 
 * @author Saúl Rodríguez Naranjo
 */
public class ServerConfig {
    
    private static final ServerConfig serverConfig = new ServerConfig();

    public static final String SERVER_CONFIG_FILE = "server-config.properties";
    
    private Properties serverProperties;
    
    private ServerConfig() {
        
        try {
            
            serverProperties = new Properties();
            
            InputStream serverConfigInputStream = getClass().getClassLoader()
                    .getResourceAsStream(SERVER_CONFIG_FILE);
            
            serverProperties.load(serverConfigInputStream);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRootDirectory() {
        return serverProperties.getProperty("root-directory");
    }
    
    public static ServerConfig getServerConfig() {
        return serverConfig;
    }
    
    
    public static void main(String[] args) {
        
        ServerConfig serverConfig = ServerConfig.getServerConfig();
        
        System.out.println("Root Directory: " + serverConfig.getRootDirectory());
        
        UserDirectoryBuilder userDirectoryBuilder = new UserDirectoryBuilder();
        
        if(userDirectoryBuilder.existsUserDirectory("Saul PC")) {
            
            System.out.println("Existe");
            
        } else {
            
            System.out.println("No existe");
            
        }
        
        userDirectoryBuilder.createUserDirectory("Wolfgang Doppelganger");
    }
    
}
