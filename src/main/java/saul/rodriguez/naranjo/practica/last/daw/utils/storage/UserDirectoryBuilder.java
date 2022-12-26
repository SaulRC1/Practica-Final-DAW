package saul.rodriguez.naranjo.practica.last.daw.utils.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.Part;
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
     * Comprueba si existe el directorio (carpeta) de usuario dentro del
     * directorio raiz de la aplicación.
     *
     * @param directory El directorio a comprobar si existe
     * @return true - si existe el directorio false - si no existe el directorio
     */
    public boolean existsUserDirectory(String directory) {

        String absoluteDirectoryPath = rootDirectory + directory;

        Path path = Paths.get(absoluteDirectoryPath);

        return Files.exists(path);
    }

    /**
     * Crea un directorio de usuario dentro del directorio raiz de la
     * aplicacion.
     * <br><br>
     *
     * Si el directorio ya existe, no hara nada.
     *
     * @param directoryName El nombre del directorio a crear
     */
    public void createUserDirectory(String directoryName) {

        if (!existsUserDirectory(directoryName)) {

            String absoluteDirectoryPath = rootDirectory + directoryName;

            File userDirectory = new File(absoluteDirectoryPath);

            userDirectory.mkdir();
        }

    }

    public void storeUserProfilePicture(String path, Part profilePicture) throws FileNotFoundException, IOException {
        InputStream is = profilePicture.getInputStream();

        File profileImage = new File(path);

        try ( FileOutputStream outputStream = new FileOutputStream(profileImage, false)) {
            int read;
            byte[] bytes = new byte[8192];
            while ((read = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }

}
