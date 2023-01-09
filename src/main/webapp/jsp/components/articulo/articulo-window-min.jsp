<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="articulo-window-min flex-row flex-justify-content-start flex-align-items-center">
    <!-- Imagen del articulo -->
    <img src="${param.rutaImagen}" class="articulo-window-min-img">

    <!-- Seccion Nombre y Precio -->
    <div class="articulo-window-min-data-section flex-column flex-justify-content-space-evenly">
        <!-- Nombre del articulo -->
        <div>
            <p>
                ${param.nombre}
            </p>
        </div>

        <!-- Precio del articulo -->
        <div>
            <p>
                ${param.precioVenta}
            </p>
        </div>

    </div>
</div>
