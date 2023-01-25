<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="menu-div flex-row flex-justify-content-start flex-align-items-center">

    <a class="menu-option roboto-condensed defteros-anchor defteros-anchor-style" href="${pageContext.request.contextPath}/inicio">
        HOME
    </a>

    <a class="menu-option roboto-condensed defteros-anchor defteros-anchor-style"
       href="${pageContext.request.contextPath}/ver-articulos">
        VER ARTÍCULOS
    </a>

    <c:if test="${not empty usuario}">
        <a class="menu-option roboto-condensed defteros-anchor defteros-anchor-style"
           href="${pageContext.request.contextPath}/articulos-interes">
            MIS ARTÍCULOS DE INTERES
        </a>
    </c:if>

    <c:if test="${not empty usuario}">
        <a class="menu-option roboto-condensed defteros-anchor defteros-anchor-style" 
           href="${pageContext.request.contextPath}/publicar-articulo">
            PUBLICAR ARTICULO
        </a>
    </c:if>

    <c:if test="${empty usuario}">
        <a class="menu-option roboto-condensed defteros-anchor defteros-anchor-style" 
           href="${pageContext.request.contextPath}/alta-usuario">
            ALTA
        </a>
    </c:if>


</div>
