<%-- 
    Document   : detalles-articulo
    Created on : 14 ene 2023, 16:08:31
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>${articulo.nombre} | Detalles</title>
        
        <link rel="icon" href="${pageContext.request.contextPath}/images/omega.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexbox-classes.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/cabezera.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/miniatura-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/buttons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/roboto-condensed.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/perfil-usuario/data-tag.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/greek-font.css">
        
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages/detalles-articulo.css">
    </head>
    <body>

        <div class="main-div flex-column flex-justify-content-start">
            <jsp:include page="components/cabezera.jsp"></jsp:include>
            <jsp:include page="components/menu.jsp"></jsp:include>

            <div class="article-details flex-row flex-justify-content-start flex-align-items-center">
                <c:choose>
                    <c:when test="${articulo.tieneImagenDeArticulo == true}">
                        <img class="article-details-img" src="${pageContext.request.contextPath}/imagen-articulo?id-articulo=${articulo.idArticulo}">
                    </c:when>
                    <c:otherwise>
                        <img class="article-details-img" src="${articulo.rutaImagen}">    
                    </c:otherwise>
                </c:choose>
                            
                <div class="article-and-user-data flex-column flex-justify-content-start">
                    
                    <div class="article-and-user-shared-div flex-row flex-justify-content-start flex-align-items-center">
                        
                        <div class="article-data roboto-condensed flex-column flex-justify-content-space-evenly ">
                            
                            <p class="title-style">${articulo.nombre}</p>
                            
                            <p>Estado: ${articulo.estado}</p>
                            
                            <p>Año de adquisición: ${articulo.anioAdquisicion}</p>
                            
                            <p>Precio: ${articulo.precioVenta} €</p>
                            
                            <p>Descripcion:</p>
                            
                            <div style="overflow: auto; text-align: justify; width: 100%; height: 100px;" class="flex-column">
                                <p style="width: 80%; overflow-wrap: break-word;">${articulo.descripcion}</p>
                            </div>
                        </div>
                        
                        <div class="user-data roboto-condensed flex-column flex-justify-content-space-evenly">
                            
                            <p class="title-style">Detalles del vendedor</p>
                            
                            <div class="user-data-brief flex-row flex-justify-content-start flex-align-items-center">
                                
                                <c:choose>
                                    <c:when test="${articulo.usuario.tieneImagenDePerfil == true}">
                                        <img src="${pageContext.request.contextPath}/imagenes?usuario=${articulo.usuario.nombre}">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${articulo.usuario.rutaImagen}">
                                    </c:otherwise>
                                </c:choose>
                                
                                <div class="user-data-brief-contact flex-column flex-justify-content-space-evenly">
                                    
                                    <p style="font-size: 16px; margin-left: 0;">${articulo.usuario.nombre}</p>
                                    
                                    <p style="font-size: 16px; margin-left: 0;">${articulo.usuario.correoElectronico}</p>
                                    
                                    <p style="font-size: 16px; margin-left: 0;">${articulo.usuario.telefono}</p>
                                </div>
                                
                            </div>
                               
                            <div class="flex-row flex-justify-content-space-evenly flex-align-items-center">
                                <jsp:include page="components/perfil-usuario/data-tag.jsp">
                                    <jsp:param name="icon" value="${pageContext.request.contextPath}/images/components/data-tag/pin.png"/>
                                    <jsp:param name="data" value="${articulo.usuario.codigoPostal}"/>
                                </jsp:include>
                                
                                <a class="go-to-profile-button" href="${pageContext.request.contextPath}/usuario/publico/${articulo.usuario.nombre}">Visitar perfil</a>
                            </div>
                            
                            
                        </div>
                    </div>
                    
                    <div class="comments-section flex-column flex-justify-content-start">
                        
                    </div>
                </div>
            </div>
            
            <jsp:include page="components/footer.jsp"></jsp:include>
        </div>

    </body>
</html>
