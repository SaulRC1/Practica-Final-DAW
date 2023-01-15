<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="articulo-window-min flex-row flex-justify-content-start flex-align-items-center">
    <!-- Imagen del articulo -->
    <img loading="lazy" src="${param.rutaImagen}" class="articulo-window-min-img">

    <!-- Seccion Nombre y Precio -->
    <div class="articulo-window-min-data-section flex-column flex-justify-content-space-evenly">
        
        <input value="${param.idArticulo}" style="display: none;">
        <!-- Nombre del articulo -->
        <p class="roboto-condensed articulo-element">
            ${param.nombre}
        </p>

        <!-- Precio del articulo -->
        <p class="roboto-condensed articulo-element">
            ${param.precioVenta} €
        </p>

        <!-- Codigo Postal -->
        <div class="flex-row flex-align-items-center flex-justify-content-center">
            <img class="codigo-postal-div-img" src="${pageContext.request.contextPath}/images/components/data-tag/pin-white.png">
            <p class="roboto-condensed articulo-element">
                ${param.codigoPostal}
            </p>
        </div>

    </div>

    <div class="articulo-see-more-div flex-column flex-justify-content-center flex-align-items-center">
        <p class="roboto-condensed">
            Ver más
        </p>
        <img src="${pageContext.request.contextPath}/images/components/icons/right-arrow.png">
    </div>
</div>
