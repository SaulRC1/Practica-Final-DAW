package saul.rodriguez.naranjo.practica.last.daw.models.error.processing;

/**
 *
 * @author SaulRC1
 */
public class RegisterFormError {
    
    private String errorCorreoElectronico;
    
    private String errorClave;
    
    private String errorClaveRepetir;
    
    private String errorNombre;
    
    private String errorDireccion;
    
    private String errorCodigoPostal;
    
    private String errorTelefonoDeContacto;

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
