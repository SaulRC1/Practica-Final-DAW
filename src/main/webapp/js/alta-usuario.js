//############################### EVENTOS ######################################

document.getElementById("prefijo-movil-list-button").addEventListener("click", () => {prefijoMovilListButtonClick();});

//Click a elemento dentro de lista de prefijos
let elementosPrefijosMoviles = document.getElementsByClassName("prefijo-movil-tag");

for(let i = 0; i < elementosPrefijosMoviles.length; i++) {
    
    elementosPrefijosMoviles[i].addEventListener("click", () => {prefijoMovilListElementClick(event);});
    
}

//Click al boton para subir imagenes
document.getElementById("upload-image-button").addEventListener("click", () => {
    document.getElementById("imagenDeUsuario").click();
});

//El usuario sube una imagen
document.getElementById("imagenDeUsuario").addEventListener("change", () => {
    
    cambiaImagenUsuario();
    
});

//############################## FUNCIONES #####################################

function prefijoMovilListButtonClick() {
    let botonListaPrefijosMoviles = document.getElementsByClassName("prefijo-movil-dropdown-list-body");
    
    if(botonListaPrefijosMoviles[0].style.display === "flex") {
        
        botonListaPrefijosMoviles[0].style.display = "none";
        
    } else {
        
        botonListaPrefijosMoviles[0].style.display = "flex";
        
    }
}

function prefijoMovilListElementClick(event) {
    alert(event.target.childNodes[0]);
    console.log(event.target.childNodes[3].innerHTML.textContent);
}

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

function cambiaImagenUsuario() {
   
    let uploadedFile = document.getElementById("imagenDeUsuario").files[0];
    
    let image = new FileReader();
    
    image.readAsDataURL(uploadedFile);
    
    image.onload = function(oFREvent) {
        
        let imagenUsuario = document.getElementById("img-perfil").src 
                = oFREvent.target.result;
        
    };
   
}


