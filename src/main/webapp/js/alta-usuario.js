//############################### EVENTOS ######################################




//############################## FUNCIONES #####################################

function validaAltaUsuario() {
    
    //Obtenemos el formulario
    let registroForm = document.getElementById("formulario-registro");
    
    //Obtenemos correo electrónico
    let correoElectronico = registroForm.elements["correoElectronico"];
    
    if(!validaCorreoElectronico(correoElectronico)) {
        return false;
    }
    
    let clave = registroForm.elements["clave"];
    
    let repetirClave = registroForm.elements["repetirClave"];
    
    let nombre = registroForm.elements["nombre"];
    
    let codigoPostal = registroForm.elements["codigoPostal"];
    
    let facebook = registroForm.elements["facebook"];
    
    let twitter = registroForm.elements["twitter"];
    
    let telefono = registroForm.elements["telefono"];
    
    return true;
    
}

function validaCorreoElectronico(correoElectronico) {
    
}

function validaClave(clave) {
    
}

function validaNombre() {
    
}

function validaCodigoPostal(codigoPostal) {
    
}

function validaFacebook(facebook) {
    
}

function validaTwitter(twitter) {
    
}

function validaTelefono() {
    
}


