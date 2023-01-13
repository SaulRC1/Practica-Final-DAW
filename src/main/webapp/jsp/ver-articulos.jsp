<%-- 
    Document   : ver-articulos
    Created on : 8 ene 2023, 17:39:41
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="${pageContext.request.contextPath}/images/omega.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages/ver-articulos.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexbox-classes.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/cabezera.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/articulo-window-min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/miniatura-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/buttons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/greek-font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/roboto-condensed.css">
        <title>Défteros Market | Ver Artículos</title>
    </head>
    <body>
        <jsp:include page="components/cabezera.jsp"></jsp:include>
        <jsp:include page="components/menu.jsp"></jsp:include>
        
        <div class="main-section flex-row flex-justify-content-start flex-align-items-center">
            
            <div class="filters-section flex-column flex-justify-content-space-evenly roboto-condensed">
                
                <p class="filters-section-title">Filtrar Artículos</p>
                
                <label class="filters-section-element" >Código Postal</label>
                <input class="filters-section-element" type="text">
                
                <label class="filters-section-element">Categoría</label>
                <select class="roboto-condensed filters-section-element">
                    <option value="default">Seleccione una categoría</option>
                </select>
                
                <label class="filters-section-element">Precio mínimo</label>
                <input class="filters-section-element" type="range" id="precio-min-slider" min="0" max="100000">
                <input class="filters-section-element" type="text">
                
                <label class="filters-section-element">Precio máximo</label>
                <input class="filters-section-element" type="range" id="precio-max-slider" min="0" max="100000">
                <input class="filters-section-element" type="text">
                
                <button type="button" class="filters-section-element">
                    Filtrar
                </button>
            </div>
            
            <div class="articulos-section flex-row flex-justify-content-space-evenly">
                <c:forEach items="${listaArticulos}" var="articulo">
                    <c:choose>
                        <c:when test="${articulo.tieneImagenDeArticulo == true}">
                            <jsp:include page="components/articulo/articulo-window-min.jsp">
                                <jsp:param name="rutaImagen" value="${pageContext.request.contextPath}/imagen-articulo?id-articulo=${articulo.idArticulo}"/>
                                <jsp:param name="nombre" value="${articulo.nombre}"/>
                                <jsp:param name="precioVenta" value="${articulo.precioVenta}"/>
                                <jsp:param name="codigoPostal" value="${articulo.usuario.codigoPostal}"/>
                            </jsp:include>    
                        </c:when>
                        <c:otherwise>
                            <jsp:include page="components/articulo/articulo-window-min.jsp">
                                <jsp:param name="rutaImagen" value="${articulo.rutaImagen}"/>
                                <jsp:param name="nombre" value="${articulo.nombre}"/>
                                <jsp:param name="precioVenta" value="${articulo.precioVenta}"/>
                                <jsp:param name="codigoPostal" value="${articulo.usuario.codigoPostal}"/>
                            </jsp:include>
                        </c:otherwise>
                    </c:choose>
                    
                </c:forEach> 
            </div>
        </div>
        
        <jsp:include page="components/footer.jsp"></jsp:include>
    </body>
</html>
