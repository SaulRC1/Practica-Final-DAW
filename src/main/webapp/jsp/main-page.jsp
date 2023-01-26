<%-- 
    Document   : main-page
    Created on : 28 oct 2022, 13:46:36
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <link rel="icon" href="${pageContext.request.contextPath}/images/omega.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages/main-page.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexbox-classes.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/cabezera.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/miniatura-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/buttons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/greek-font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/roboto-condensed.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/articulo-window-min.css">
        
        <script src="${pageContext.request.contextPath}/js/components/articulo-window-min.js" defer></script>
        <script src="${pageContext.request.contextPath}/js/components/cabezera.js" defer></script>

        <title>Défteros Market | Inicio</title>
    </head>
    <body>
        <jsp:include page="components/cabezera.jsp"></jsp:include>
        <jsp:include page="components/menu.jsp"></jsp:include>
        
        <div class="main-div flex-row flex-justify-content-start flex-align-items-center">
            
            <div class="main-section flex-row flex-justify-content-space-evenly">
                <c:forEach items="${listaArticulos}" var="articulo">
                    <c:choose>
                        <c:when test="${articulo.tieneImagenDeArticulo == true}">
                            <jsp:include page="components/articulo/articulo-window-min.jsp">
                                <jsp:param name="rutaImagen" value="${pageContext.request.contextPath}/imagen-articulo?id-articulo=${articulo.idArticulo}"/>
                                <jsp:param name="nombre" value="${articulo.nombre}"/>
                                <jsp:param name="precioVenta" value="${articulo.precioVenta}"/>
                                <jsp:param name="codigoPostal" value="${articulo.usuario.codigoPostal}"/>
                                <jsp:param name="idArticulo" value="${articulo.idArticulo}"/>
                            </jsp:include>    
                        </c:when>
                        <c:otherwise>
                            <jsp:include page="components/articulo/articulo-window-min.jsp">
                                <jsp:param name="rutaImagen" value="${articulo.rutaImagen}"/>
                                <jsp:param name="nombre" value="${articulo.nombre}"/>
                                <jsp:param name="precioVenta" value="${articulo.precioVenta}"/>
                                <jsp:param name="codigoPostal" value="${articulo.usuario.codigoPostal}"/>
                                <jsp:param name="idArticulo" value="${articulo.idArticulo}"/>
                            </jsp:include>
                        </c:otherwise>
                    </c:choose>
                </c:forEach> 
            </div>
            
            <div class="banner-section roboto-condensed flex-column flex-justify-content-space-evenly flex-align-items-center">

                <p>¡Echa un vistazo a lo último!</p>
                
                <img src="${pageContext.request.contextPath}/images/surprised.png">

            </div>
        </div>
        
        <jsp:include page="components/footer.jsp"></jsp:include>
    </body>
</html>
