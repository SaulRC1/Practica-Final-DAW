package saul.rodriguez.naranjo.practica.last.daw.utils.storage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import saul.rodriguez.naranjo.practica.last.daw.utils.configuration.ServerConfig;

/**
 * Clase de utilidad para operar con los directorios de datos de los usuarios
 * registrados en la aplicacion.
 * 
 * @author Saúl Rodríguez Naranjo
 */
public class UserDirectoryBuilder {
    
    private String rootDirectory = ServerConfig.getServerConfig().getRootDirectory();

    public UserDirectoryBuilder() {
    }
    
    /**
     * Comprueba si existe el directorio (carpeta) de usuario dentro del directorio 
     * raiz de la aplicación.
     * 
     * @param directory El directorio a comprobar si existe
     * @return true - si existe el directorio
     *         false - si no existe el directorio
     */
    public boolean existsUserDirectory(String directory) {
        
        String absoluteDirectoryPath = rootDirectory + directory;
        
        Path path = Paths.get(absoluteDirectoryPath);
        
        return Files.exists(path);
    }
    
    /**
     * Crea un directorio de usuario dentro del directorio raiz de la aplicacion.
     * <br><br>
     * 
     * Si el directorio ya existe, no hara nada.
     * 
     * @param directoryName El nombre del directorio a crear
     */
    public void createUserDirectory(String directoryName) {
        
        if(!existsUserDirectory(directoryName)) {
            
            String absoluteDirectoryPath = rootDirectory + directoryName;
            
            File userDirectory = new File(absoluteDirectoryPath);
            
            userDirectory.mkdir();
        }
        
    }

}
