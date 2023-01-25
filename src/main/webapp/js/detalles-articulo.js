//############################### LISTENERS ####################################

document.getElementsByClassName("add-favorite-button")[0].addEventListener("click", () => {
    
    let idArticuloFavorito = document.getElementById("id-articulo-favorito").value;
    
    let urlAddFavorite = window.location.origin + window.location.pathname;
    
    let articuloFavData = {
        idArticuloFavorito: idArticuloFavorito
    };
    
    $.ajax({
        url: urlAddFavorite,
        method: 'POST',
        data: articuloFavData,
        success: (data, textStatus, jqXHR) => {
            
            alert("Articulo guardado como favorito");
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            
        },
        async: false
    });
    
});


