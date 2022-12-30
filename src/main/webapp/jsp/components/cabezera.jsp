<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="cabezera-div flex-row flex-justify-content-start flex-align-items-center">
    <div class="cabezera-first-section flex-row flex-justify-content-start flex-align-items-center">
        <img id="defteros-logo" src="${pageContext.request.contextPath}/images/omega.png">
        <p class="greek-font defteros-font-style">DÉFTEROS MARKET</p>
    </div>

    <div class="cabezera-second-section flex-row flex-justify-content-end flex-align-items-center">
        <div class="cabezera-user-section flex-row flex-justify-content-center flex-align-items-center">
            <c:if test="${not empty usuario}">
                <c:choose>
                    <c:when test="${usuario.tieneImagenDePerfil == true}">
                        <jsp:include page="miniatura-usuario.jsp">
                            <jsp:param name="imagenUsuario" value="${pageContext.request.contextPath}/imagenes?usuario=${usuario.nombre}"/>
                            <jsp:param name="nombreDeUsuario" value="${usuario.nombre}"/>
                        </jsp:include>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="miniatura-usuario.jsp">
                            <jsp:param name="imagenUsuario" value="${usuario.rutaImagen}"/>
                            <jsp:param name="nombreDeUsuario" value="${usuario.nombre}"/>
                        </jsp:include>
                    </c:otherwise>
                </c:choose>
            </c:if>

            <c:if test="${empty usuario}">
                <a class="roboto-condensed" href="${pageContext.request.contextPath}/iniciar-sesion">Iniciar sesión</a>
            </c:if>    
        </div>
    </div>

</div>
