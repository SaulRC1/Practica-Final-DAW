<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : perfil-usuario
    Created on : 1 dic 2022, 21:47:52
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages/usuario/perfil/perfil-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexbox-classes.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/cabezera.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/miniatura-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/perfil-usuario/data-tag.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/perfil-usuario/anchor-tag.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/greek-font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/roboto-condensed.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/buttons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/articulo-window-min.css">
        
        <script src="${pageContext.request.contextPath}/js/components/articulo-window-min.js" defer></script>
        <script src="${pageContext.request.contextPath}/js/components/cabezera.js" defer></script>
        
        <title>Perfil | ${usuarioPublico.nombre}</title>
    </head>
    <body>
        <div class="main-div flex-column flex-justify-content-start">
            <jsp:include page="../../components/cabezera.jsp"></jsp:include>
            <jsp:include page="../../components/menu.jsp"></jsp:include>

                <div class="main-section flex-row flex-justify-content-start">

                    <!-- Seccion de datos del perfil -->
                    <div class="profile-data-div flex-column flex-justify-content-start flex-align-items-center">

                        <!-- Imagen de perfil y Nombre -->
                        <div class="image-div-profile-data-div flex-row flex-justify-content-start flex-align-items-center">
                        <c:choose>
                            <c:when test="${usuarioPublico.tieneImagenDePerfil == true}">
                                <div class="rounded-image-div flex-justify-content-start flex-align-items-center flex-row">
                                    <img class="profile-image" src="${pageContext.request.contextPath}/imagenes?usuario=${usuarioPublico.nombre}">
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="rounded-image-div flex-justify-content-start flex-align-items-center flex-row">
                                    <img class="profile-image" src="${usuarioPublico.rutaImagen}">
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <div class="first-data-section flex-column flex-justify-content-start">
                            <p class="username roboto-condensed">${usuarioPublico.nombre}</p>
                            <jsp:include page="../../components/perfil-usuario/data-tag.jsp">
                                <jsp:param name="icon" value="${pageContext.request.contextPath}/images/components/data-tag/envelope.png"/>
                                <jsp:param name="data" value="${usuarioPublico.correoElectronico}"/>
                            </jsp:include>
                            <jsp:include page="../../components/perfil-usuario/data-tag.jsp">
                                <jsp:param name="icon" value="${pageContext.request.contextPath}/images/components/data-tag/telephone-white.png"/>
                                <jsp:param name="data" value="${usuarioPublico.telefono}"/>
                            </jsp:include>  
                        </div>

                    </div>

                    <!--Datos Personales -->
                    <div class="second-data-section flex-column flex-justify-content-start">

                        <!-- Direccion y Codigo Postal -->
                        <c:choose>
                            <c:when test="${empty usuarioPublico.direccion}">
                                <jsp:include page="../../components/perfil-usuario/data-tag.jsp">
                                    <jsp:param name="icon" value="${pageContext.request.contextPath}/images/components/data-tag/pin-white.png"/>
                                    <jsp:param name="data" value="Codigo Postal: ${usuarioPublico.codigoPostal}"/>
                                </jsp:include>
                            </c:when>
                            <c:otherwise>
                                <jsp:include page="../../components/perfil-usuario/data-tag.jsp">
                                    <jsp:param name="icon" value="${pageContext.request.contextPath}/images/components/data-tag/pin-white.png"/>
                                    <jsp:param name="data" value="${usuarioPublico.direccion}, CP: ${usuarioPublico.codigoPostal}"/>
                                </jsp:include>
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${not empty usuarioPublico.facebook}">
                            <jsp:include page="../../components/perfil-usuario/anchor-tag.jsp">
                                <jsp:param name="icon" value="${pageContext.request.contextPath}/images/components/data-tag/facebook.png"/>
                                <jsp:param name="anchorLink" value="${usuarioPublico.facebook}"/>
                                <jsp:param name="anchorAbbreviation" value="Facebook de ${usuarioPublico.nombre}"/>
                            </jsp:include>
                        </c:if>

                        <c:if test="${not empty usuarioPublico.twitter}">
                            <jsp:include page="../../components/perfil-usuario/anchor-tag.jsp">
                                <jsp:param name="icon" value="${pageContext.request.contextPath}/images/components/data-tag/twitter.png"/>
                                <jsp:param name="anchorLink" value="${usuarioPublico.twitter}"/>
                                <jsp:param name="anchorAbbreviation" value="Twitter de ${usuarioPublico.nombre}"/>
                            </jsp:include>
                        </c:if>
                    </div>
                </div>

                <!-- Seccion de articulos -->
                <div class="article-section flex-row flex-justify-content-space-evenly">
                    <c:forEach items="${listaArticulos}" var="articulo">
                        <c:choose>
                            <c:when test="${articulo.tieneImagenDeArticulo == true}">
                                <jsp:include page="../../components/articulo/articulo-window-min.jsp">
                                    <jsp:param name="rutaImagen" value="${pageContext.request.contextPath}/imagen-articulo?id-articulo=${articulo.idArticulo}"/>
                                    <jsp:param name="nombre" value="${articulo.nombre}"/>
                                    <jsp:param name="precioVenta" value="${articulo.precioVenta}"/>
                                    <jsp:param name="codigoPostal" value="${articulo.usuario.codigoPostal}"/>
                                    <jsp:param name="idArticulo" value="${articulo.idArticulo}"/>
                                </jsp:include>    
                            </c:when>
                            <c:otherwise>
                                <jsp:include page="../../components/articulo/articulo-window-min.jsp">
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

            </div>

            <jsp:include page="../../components/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
