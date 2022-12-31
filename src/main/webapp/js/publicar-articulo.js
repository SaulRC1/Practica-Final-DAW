//############################### VARIABLES Y CONSTANTES #######################

const CONVERSION_RATE_KILOBYTES_TO_BYTES = 1024;

const CONVERSION_RATE_MEGABYTES_TO_BYTES = 1024 * 1024;

const MAXIMUM_IMAGE_SIZE_ACCEPTED_IN_MB = 200;

const PNG_MIME_TYPE = "image/png";

const JPG_JPEG_MIME_TYPE = "image/jpeg";

//############################### EVENTOS ######################################
//
//Click al boton para subir imagenes
document.getElementById("subir-imagen-articulo-button").addEventListener("click", () => {
    document.getElementById("imagen-articulo").click();
});

//El usuario sube una imagen
document.getElementById("imagen-articulo").addEventListener("change", () => {
    cambiaImagenUsuario();
});

document.getElementById("year-adquisicion").addEventListener("keyup", () => {
   
   let anioAdquisicionInput = document.getElementById("year-adquisicion");
   
   let anioAdquisicion = anioAdquisicionInput.value;
   
   if(!validaAnioAdquisicion(anioAdquisicion)) {
       
       anioAdquisicionInput.style.backgroundColor = "red";
       
   } else {
       
       anioAdquisicionInput.style.backgroundColor = "";
       
   }
   
});

//############################## FUNCIONES #####################################

function cambiaImagenUsuario() {

    let uploadedFile = document.getElementById("imagen-articulo").files[0];

    let image = new FileReader();

    image.readAsDataURL(uploadedFile);

    image.onload = function (oFREvent) {

        if (validaImagenDeArticulo()) {
            let imagenUsuario = document.getElementById("imagen-articulo-img").src
                    = oFREvent.target.result;
        } else {
            
            alert("El archivo o imagen subida no es valido, la imagen debe ser" +
                    ".png o .jpg/jpeg y no superar los 200 megabytes de tamaño");
            
        }
    };

}

function validaImagenDeArticulo() {

    let sizeImagenDeArticulo = getUploadedImageSize();

    if (sizeImagenDeArticulo > MAXIMUM_IMAGE_SIZE_ACCEPTED_IN_MB)
    {
        return false;
    }

    let imagenDeArticulo = document.getElementById("imagen-articulo").files[0];
    
    if(imagenDeArticulo.type !== PNG_MIME_TYPE && imagenDeArticulo.type !== JPG_JPEG_MIME_TYPE) {
        return false;
    }

    return true;

}

function getUploadedImageSize() {

    let uploadedFile = document.getElementById("imagen-articulo").files[0];

    let fileSizeInBytes = uploadedFile.size;

    let fileSizeInMegaBytes = fileSizeInBytes / (CONVERSION_RATE_MEGABYTES_TO_BYTES);

    return fileSizeInMegaBytes;

}

function validaAnioAdquisicion(year) {
    
    let anioAdquisicionRegex = /^\d{4}$/;
    
    if(year === "") {
        return true;
    }
    
    if(!year.match(anioAdquisicionRegex)) {
        return false;
    }
    
    return true;
}

function validaFormulario() {
    
    let form = document.getElementById("formulario-publicar-articulo");
    
    let anioAdquisicion = form.elements["year-adquisicion"].value;
    
    if(!validaAnioAdquisicion(anioAdquisicion)) {
        
        alert("El año de adquisicion debe ser un número, como por ejemplo 2018");
        
        return false;
    }
    
    let imagenArticulo = document.getElementById("imagen-articulo").files[0];
    
    if(typeof imagenArticulo === "undefined") {
        
        alert("Debe de subir una imagen para poder publicar el articulo");
        
        return false;
    }
    
    if(!validaImagenDeArticulo()) {
        
        alert("El archivo o imagen subida no es valido, la imagen debe ser" +
                    ".png o .jpg/jpeg y no superar los 200 megabytes de tamaño");
        
        return false;
    }
    
    return true;
}