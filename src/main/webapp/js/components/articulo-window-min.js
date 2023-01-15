//########################### LISTENERS ########################################

let articulosComponents = document.getElementsByClassName("articulo-window-min");

for(let i = 0; i < articulosComponents.length; i++) {
    
    articulosComponents[i].addEventListener("click", (event) => {
        articuloComponentClickEvent(event);
    });
    
}

//######################### FUNCTIONS ##########################################

function articuloComponentClickEvent(event) {
    
    let articuloComponent = event.target.parentNode;
    
    let idArticulo = articuloComponent.getElementsByTagName("input")[0].value;
    
    window.location.href = buildArticleDetailsURL(idArticulo);
    
}

function buildArticleDetailsURL(idArticulo) {
    
    let serverContext = window.location.pathname.split("/")[1];
    
    let articleURL = window.location.origin + "/" + serverContext + "/articulo?idArticulo=" + idArticulo;
    
    return articleURL;
}

