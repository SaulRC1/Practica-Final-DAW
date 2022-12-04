<%-- 
    Document   : articulo-window-min
    Created on : 28 nov 2022, 19:29:57
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <!-- Cabezera indicando el usuario -->
    <div>
        <!-- Imagen del usuario -->
        <c:choose>
            <c:when test="${empty param.usuario.rutaImagen}">
                <img src="${pageContext.request.contextPath}/images/usuario/default.png">
            </c:when>    
            <c:otherwise>
                <img src="${param.usuario.rutaImagen}">
            </c:otherwise>
        </c:choose>

        <!-- Nombre del usuario -->
        <p>${param.usuario.nombre}</p>
    </div>

    <!-- Seccion Articulo -->
    <div>
        <!-- Imagen del articulo -->
        <img>

        <!-- Seccion Nombre y Precio -->
        <div>
            <!-- Nombre del articulo -->
            <div>
                <p>

                </p>
            </div>

            <!-- Precio del articulo -->
            <div>
                <p>

                </p>
            </div>

        </div>
    </div>
</div>
