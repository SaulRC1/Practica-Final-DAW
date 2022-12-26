<%-- 
    Document   : iniciar-sesion
    Created on : 25 dic 2022, 23:57:00
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    </div>
                </div>

            </div>
            <jsp:include page="components/footer.jsp"/>
        </div>

    </body>
</html>
