<%-- 
    Document   : iniciar-sesion
    Created on : 25 dic 2022, 23:57:00
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/omega.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexbox-classes.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages/iniciar-sesion.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/roboto-condensed.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/greek-font.css">
    </head>
    <body>
        <div class="main-div flex-column flex-justify-content-start">
            <div class="main-section flex-column flex-justify-content-center flex-align-items-center">

                <div class="login-div flex-column flex-justify-content-start">
                    <div class="logo-div flex-row flex-justify-content-center flex-align-items-center">
                        <img src="${pageContext.request.contextPath}/images/omega.png">
                        <p class="greek-font">DÉFTEROS MARKET</p>
                    </div>

                    <div class="main-login-div flex-row flex-align-items-center flex-justify-content-start">
                        <div class="login-image-div flex-column flex-align-items-center flex-justify-content-center">
                            <p class="roboto-condensed">¡Hola de nuevo!</p>
                            <img src="${pageContext.request.contextPath}/images/happy-girl.png">
                        </div>

                        <div class="login-user-div flex-column flex-justify-content-start">

                            <p class="login-user-div-title roboto-condensed">Iniciar sesión</p>

                            <form class="flex-column flex-justify-content-start" 
                                  action="${pageContext.request.contextPath}/iniciar-sesion"
                                  method="post" id="formulario-iniciar-sesion">

                                <label class="login-user-div-p roboto-condensed" for="email">Correo Electrónico</label>
                                <input class="login-user-div-p roboto-condensed" id="email" name="email" type="text" required>

                                <label class="login-user-div-p roboto-condensed" for="password">Contraseña</label>
                                <input class="login-user-div-p roboto-condensed" id="clave" name="clave" type="password" required>

                                <input class="login-user-div-button roboto-condensed" type="submit" value="Iniciar sesión">

                            </form>

                            <c:if test="${not empty error}">
                                <p class="login-user-div-error roboto-condensed">${error}</p>
                            </c:if>


                            <a href="${pageContext.request.contextPath}/alta-usuario" class="roboto-condensed"
                               target="_blank" rel="noopener noreferrer">
                                ¿Aún no estas registrado? Entra aquí para comenzar a vender
                            </a>

                        </div>
                    </div>
                </div>

            </div>
            <jsp:include page="components/footer.jsp"/>
        </div>

    </body>
</html>
