package saul.rodriguez.naranjo.practica.last.daw.models.error.processing;

/**
 *
 * @author SaulRC1
 */
public class RegisterFormError {
    
    private String errorCorreoElectronico = null;
    
    private String errorClave = null;
    
    private String errorClaveRepetir = null;
    
    private String errorNombre = null;
    
    private String errorDireccion = null;
    
    private String errorCodigoPostal = null;
    
    private String errorTelefonoDeContacto = null;

    public String getErrorCorreoElectronico() {
        return errorCorreoElectronico;
    }

    public void setErrorCorreoElectronico(String errorCorreoElectronico) {
        this.errorCorreoElectronico = errorCorreoElectronico;
    }

    public String getErrorClave() {
        return errorClave;
    }

    public void setErrorClave(String errorClave) {
        this.errorClave = errorClave;
    }

    public String getErrorClaveRepetir() {
        return errorClaveRepetir;
    }

    public void setErrorClaveRepetir(String errorClaveRepetir) {
        this.errorClaveRepetir = errorClaveRepetir;
    }

    public String getErrorNombre() {
        return errorNombre;
    }

    public void setErrorNombre(String errorNombre) {
        this.errorNombre = errorNombre;
    }

    public String getErrorDireccion() {
        return errorDireccion;
    }

    public void setErrorDireccion(String errorDireccion) {
        this.errorDireccion = errorDireccion;
    }

    public String getErrorCodigoPostal() {
        return errorCodigoPostal;
    }

    public void setErrorCodigoPostal(String errorCodigoPostal) {
        this.errorCodigoPostal = errorCodigoPostal;
    }

    public String getErrorTelefonoDeContacto() {
        return errorTelefonoDeContacto;
    }

    public void setErrorTelefonoDeContacto(String errorTelefonoDeContacto) {
        this.errorTelefonoDeContacto = errorTelefonoDeContacto;
    }
    
}
