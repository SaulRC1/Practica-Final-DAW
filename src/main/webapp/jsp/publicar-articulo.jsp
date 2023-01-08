<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Défteros Market | Publicar Artículo</title>
        <link rel="icon" href="${pageContext.request.contextPath}/images/omega.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexbox-classes.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/cabezera.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/miniatura-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/buttons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages/publicar-articulo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/roboto-condensed.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/greek-font.css">
        <script src="${pageContext.request.contextPath}/js/publicar-articulo.js" defer></script>
    </head>
    <body>
        <div class="main-div flex-column flex-justify-content-start">
            <jsp:include page="components/cabezera.jsp"></jsp:include>
            <jsp:include page="components/menu.jsp"></jsp:include>

                <div class="main-section flex-row flex-justify-content-start flex-align-items-center">

                    <div class="article-img-section flex-column flex-justify-content-center flex-align-items-center">

                        <img id="imagen-articulo-img" src="${pageContext.request.contextPath}/images/publicar_articulo/upload.png">

                    <button id="subir-imagen-articulo-button" type="button" class="roboto-condensed">
                        Subir imagen
                    </button>
                    
                    <c:if test="${not empty errorImagen}">
                            <p class="form-error roboto-condensed">${errorImagen}</p>
                    </c:if>
                </div>

                <div class="article-data-section flex-column flex-justify-content-start">

                    <div class="article-data-section-title flex-column flex-justify-content-center flex-align-items-center">
                        <p class="roboto-condensed">¡Sube tu artículo!</p>
                    </div>


                    <form id="formulario-publicar-articulo" class="flex-column flex-justify-content-space-evenly" 
                          onsubmit="" method="post" action="${pageContext.request.contextPath}/publicar-articulo"
                          enctype="multipart/form-data">

                        <input style="display: none" type="file" name="imagen-articulo" id="imagen-articulo" accept=".png,.jpg,.jpeg">

                        <label class="roboto-condensed" for="nombre-articulo">Nombre del articulo:</label>
                        <input class="roboto-condensed" type="text" id="nombre-articulo" name="nombre-articulo" required>

                        <label class="roboto-condensed" for="year-adquisicion">Año de adquisicion:</label>
                        <input class="roboto-condensed" type="text" id="year-adquisicion" name="year-adquisicion" placeholder="2021">
                        
                        <c:if test="${not empty errorAnioAdquisicion}">
                            <p class="form-error roboto-condensed">${errorAnioAdquisicion}</p>
                        </c:if>
                        
                        <label for="estado" class="roboto-condensed">Estado:</label>
                        <select name="estado" id="estado">
                            <option value="NUEVO">Nuevo</option>
                            <option value="SEMINUEVO">Seminuevo</option>
                            <option value="DETERIORADO">Deteriorado</option>
                            <option value="ANTIGUO">Antiguo</option>
                        </select>

                        <label for="categoria" class="roboto-condensed">Categoria:</label>
                        <select name="categoria" id="categoria" required>
                            <c:forEach items="${listaCategorias}" var="categoria">
                                <option value="${categoria.idCategoria}">${categoria.nombreCategoria}</option>
                            </c:forEach>
                        </select>

                        <label class="roboto-condensed" for="precio-venta">Precio de venta (€):</label>
                        <input class="roboto-condensed" type="number" id="precio-venta" name="precio-venta"
                               placeholder="0000.00" min="0.00" max="999999.99" step="0.01"required>

                        <c:if test="${not empty errorPrecioVenta}">
                            <p class="form-error roboto-condensed">${errorPrecioVenta}</p>
                        </c:if>
                            
                        <label class="roboto-condensed" for="descripcion">Descripcion:</label>
                        <textarea class="roboto-condensed" id="descripcion" name="descripcion"></textarea>

                        <input class="article-data-section-submit-button" type="submit" value="Publicar Artículo">

                    </form>

                </div>

            </div>

            <jsp:include page="components/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
