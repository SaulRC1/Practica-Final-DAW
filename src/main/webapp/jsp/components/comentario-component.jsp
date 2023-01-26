<%-- 
    Document   : comentario-component
    Created on : 25 ene 2023, 23:05:37
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div class="comment-component flex-column flex-justify-content-start">
    
    <div class="flex-row flex-justify-content-start flex-align-items-center">
        <c:choose>
            <c:when test="${param.usuarioTieneImagenDePerfil == true}">
                <jsp:include page="miniatura-usuario-comment.jsp">
                    <jsp:param name="imagenUsuario" value="${pageContext.request.contextPath}/imagenes?usuario=${param.nombreUsuario}"/>
                    <jsp:param name="nombreDeUsuario" value="${param.nombreUsuario}"/>
                </jsp:include>
            </c:when>
            <c:otherwise>
                <jsp:include page="miniatura-usuario-comment.jsp">
                    <jsp:param name="imagenUsuario" value="${param.usuarioRutaImagen}"/>
                    <jsp:param name="nombreDeUsuario" value="${param.nombreUsuario}"/>
                </jsp:include>
            </c:otherwise>
        </c:choose>
        
        <p class="roboto-condensed">
            COMENTARIO ${param.visibilidad}
        </p>
    </div>
    
    <div class="comment-component-text">
        <p class="roboto-condensed">${param.comentario}</p>
    </div>

</div>
