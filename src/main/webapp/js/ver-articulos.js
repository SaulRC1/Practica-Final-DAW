//############################ LISTENERS ########################################

document.getElementById("filter-button").addEventListener("click", () => {
   
    let codigoPostal = document.getElementById("filtro-codigo-postal").value;
    
    if(!validaCodigoPostal(codigoPostal)) {
        
        alert("El codigo postal introducido no es válido.");
        return;
    }
    
    let idCategoria = document.getElementById("filtro-categoria").value;
    
    let precioMinimo = document.getElementById("precio-min-input").value;
    
    let precioMaximo = document.getElementById("precio-max-input").value;
    
    let urlConFiltro = construirQuery(codigoPostal, idCategoria, precioMinimo, precioMaximo);
    
    window.location.href = urlConFiltro;
    
});

document.getElementById("precio-min-slider").addEventListener("input", () => {
   
    let inputPrecioMinimo = document.getElementById("precio-min-input");
    
    inputPrecioMinimo.value = document.getElementById("precio-min-slider").value;
    
    let precioMaximoSlider = document.getElementById("precio-max-slider");
    
    if(parseInt(precioMaximoSlider.value) < parseInt(document.getElementById("precio-min-slider").value)) {
        
        precioMaximoSlider.value = document.getElementById("precio-min-slider").value;
    
        precioMaximoSlider.dispatchEvent(new Event("input"));
    }
    
    
});

document.getElementById("precio-max-slider").addEventListener("input", () => {
   
    let inputPrecioMaximo = document.getElementById("precio-max-input");
    
    let inputPrecioMinimoValue = document.getElementById("precio-min-slider").value;
    
    if(parseInt(inputPrecioMinimoValue) <= parseInt(document.getElementById("precio-max-slider").value)) {
        
        inputPrecioMaximo.value = document.getElementById("precio-max-slider").value;
        
    } else if(parseInt(inputPrecioMinimoValue) > parseInt(document.getElementById("precio-max-slider").value)){
        
        document.getElementById("precio-max-slider").value = document.getElementById("precio-min-slider").value;
        document.getElementById("precio-max-slider").dispatchEvent(new Event("input"));
    }
    
    
});


//############################ FUNCTIONS #######################################
function validaCodigoPostal(codigoPostal) {

    let codigoPostalRegex = /^\d{5}$/;
    
    if(codigoPostal === "") {
        return true;
    }
    
    if(!codigoPostal.match(codigoPostalRegex)) {
        return false;
    }

    return true;
}

function construirQuery(codigoPostal, idCategoria, precioMinimo, precioMaximo) {
    
    let currentURL = window.location.origin;
    
    let queryURL = currentURL + "/Practica-Final-DAW/ver-articulos?";
    
    let codigoPostalQuery = "";
    
    if(codigoPostal !== "") {
        codigoPostalQuery = "codigoPostal=" + codigoPostal;
        
        queryURL += codigoPostalQuery + "&";
    }
    
    let categoriaQuery = "";
    
    if(idCategoria !== "default") {
        categoriaQuery = "idCategoria=" + idCategoria;
        queryURL += categoriaQuery + "&";
    }
    
    let precioMinimoQuery = "precioMinimo=" + precioMinimo;
    
    if(precioMinimo > 0) {
        queryURL += precioMinimoQuery + "&";
    }
    
    let precioMaximoQuery = "precioMaximo=" + precioMaximo;
    
    if(precioMaximo > 0) {
        queryURL += precioMaximoQuery;
    }
    
    
    return queryURL;
}