//############################### VARIABLES Y CONSTANTES #######################

const CONVERSION_RATE_KILOBYTES_TO_BYTES = 1024;

const CONVERSION_RATE_MEGABYTES_TO_BYTES = 1024 * 2;

const MAXIMUM_IMAGE_SIZE_ACCEPTED_IN_MB = 200;

const PNG_MIME_TYPE = "image/png";

const JPG_JPEG_MIME_TYPE = "image/jpeg";


//############################### EVENTOS ######################################

document.getElementById("prefijo-movil-list-button").addEventListener("click", () => {
    prefijoMovilListButtonClick();
});

//Click a elemento dentro de lista de prefijos
let elementosPrefijosMoviles = document.getElementsByClassName("prefijo-movil-tag");

for (let i = 0; i < elementosPrefijosMoviles.length; i++) {

    elementosPrefijosMoviles[i].addEventListener("click", () => {
        prefijoMovilListElementClick(event);
    });

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

    if (botonListaPrefijosMoviles[0].style.display === "flex") {

        botonListaPrefijosMoviles[0].style.display = "none";

    } else {

        botonListaPrefijosMoviles[0].style.display = "flex";

    }
}

function prefijoMovilListElementClick(event) {
    
    let mainListElementImage = document.getElementById("prefijo-movil-list-main-img");
    
    let mainListElementPrefix = document.getElementById("prefijo-movil-list-main-p");
    
    let prefijoTelefonoFormulario = document.getElementById("prefijo-movil");
    
    if(event.target.parentNode.classList.contains("prefijo-movil-tag")) {
        //alert(event.target.parentNode.getElementsByTagName('p')[0].innerHTML);
        
        let ownImage = event.target.parentNode.getElementsByTagName('img')[0].src;
        
        mainListElementImage.src = ownImage;
                
        let ownPrefix = event.target.parentNode.getElementsByTagName('p')[0].innerHTML;
        
        mainListElementPrefix.innerText = ownPrefix.toString();
        
        prefijoTelefonoFormulario.value = ownPrefix.toString();
        
    } else {
        let ownImage = event.target.getElementsByTagName('img')[0].src;
        
        mainListElementImage.src = ownImage;
        
        let ownPrefix = event.target.getElementsByTagName('p')[0].innerHTML;
        
        mainListElementPrefix.innerText = ownPrefix.toString();
        
        prefijoTelefonoFormulario.value = ownPrefix.toString();
        //alert(event.target.getElementsByTagName('p')[0].innerHTML);
    }
    
    document.getElementById("prefijo-movil-list-button").click();
    //console.log(event.target.childNodes[3].innerHTML.textContent);
}

function validaAltaUsuario() {

    //Obtenemos el formulario
    let registroForm = document.getElementById("formulario-registro");

    //Obtenemos correo electrónico
    let correoElectronico = registroForm.elements["correoElectronico"].value;

    if (!validaCorreoElectronico(correoElectronico)) {
        alert("El correo electrónico no es válido, por favor, asegurese de que lo ha" +
               "introducido correctamente");
        return false;
    }

    let clave = registroForm.elements["clave"].value;

    let repetirClave = registroForm.elements["repetirClave"].value;
    
    if(!validaClave(clave, repetirClave)) {
        
        if(clave !== repetirClave) {
            alert("La contraseña y la contraseña repetida deben ser iguales");
        } else {
            alert("La contraseña debe contener máximo 40 caractéres y mínimo 10 para ser válida");
        }
        
        return false;
    }

    let nombre = registroForm.elements["nombre"].value;

    let codigoPostal = registroForm.elements["codigoPostal"].value;
    
    if(!validaCodigoPostal(codigoPostal)) {
        
        alert("El codigo postal introducido no es valido");
        
        return false;
    }

    let facebook = registroForm.elements["facebook"].value;
    
    if(facebook !== "" && !validaURL(facebook)) {
        
        alert("El enlace introducido de facebook no parece ser válido");
        
        return false;
    }

    let twitter = registroForm.elements["twitter"].value;
    
    if(twitter !== "" && !validaURL(twitter)) {
        
        alert("El enlace de twitter introducido no parece ser válido");
        
        return false;
    }

    let telefono = registroForm.elements["telefono"].value;
    
    if(!validaTelefono(telefono)) {
        
        alert("El telefono introducido no es válido");
        
        return false;
    }

    return true;

}

function validaCorreoElectronico(correoElectronico) {
    
    let emailRegex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    
    if(!correoElectronico.match(emailRegex)) {
        return false;
    }
    
    return true;
}

function validaClave(clave, repetirClave) {
    
    if(clave !== repetirClave) {
        return false;
    }
    
    let regexClave = /^[\w\W]{10,40}$/;
    
    if(!clave.match(regexClave)) {
        return false;
    }
    
    return true;
    
}

function validaNombre() {

}

function validaCodigoPostal(codigoPostal) {

    let codigoPostalRegex = /^\d{5}$/;
    
    if(!codigoPostal.match(codigoPostalRegex)) {
        return false;
    }

    return true;
}

function validaURL(url) {
    let regexURL = /^((https:\/\/)|(http:\/\/))[\w\W]+$/;
    
    if(!url.match(regexURL)) {
        return false;
    }
    
    return true;
}

function validaTelefono(telefono) {

    let regexTelefono = /^\d{9}$/;
    
    if(!telefono.match(regexTelefono)) {
        return false;
    }
    
    return true;
}   

function cambiaImagenUsuario() {

    let uploadedFile = document.getElementById("imagenDeUsuario").files[0];

    let image = new FileReader();

    image.readAsDataURL(uploadedFile);

    image.onload = function (oFREvent) {

        if (validaImagenDeUsuario()) {
            let imagenUsuario = document.getElementById("img-perfil").src
                    = oFREvent.target.result;
        } else {
            
            alert("El archivo o imagen subida no es valido, la imagen debe ser" +
                    ".png o .jpg/jpeg y no superar los 200 megabytes de tamaño");
            
        }
    };

}

function getUploadedImageSize() {

    let uploadedFile = document.getElementById("imagenDeUsuario").files[0];

    let fileSizeInBytes = uploadedFile.size;

    let fileSizeInMegaBytes = fileSizeInBytes / (CONVERSION_RATE_MEGABYTES_TO_BYTES);

    return fileSizeInMegaBytes;

}

function validaImagenDeUsuario() {

    let sizeImagenDeUsuario = getUploadedImageSize();

    if (sizeImagenDeUsuario > MAXIMUM_IMAGE_SIZE_ACCEPTED_IN_MB)
    {
        return false;
    }

    let imagenDeUsuario = document.getElementById("imagenDeUsuario").files[0];
    
    if(imagenDeUsuario.type !== PNG_MIME_TYPE && imagenDeUsuario.type !== JPG_JPEG_MIME_TYPE) {
        return false;
    }

    return true;

}


