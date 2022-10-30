<%-- 
    Document   : alta-usuario
    Created on : 30 oct 2022, 1:42:43
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Défteros Market | Alta Usuario</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/omega.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages/alta-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexbox-classes.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/cabezera.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/greek-font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/roboto-condensed.css">
    </head>
    <body>
        <jsp:include page="components/cabezera.jsp"></jsp:include>
        <jsp:include page="components/menu.jsp"></jsp:include>

            <div class="main-section flex-row flex-justify-content-start">

                <div>
                    <p class="roboto-condensed">Registrate en Défteros Market para comenzar a vender!</p>
                    
                    <div>
                        <img src="${pageContext.request.contextPath}/images/alta_usuario/register1.png">
                    </div>
                </div>

                <div>
                    <form>
                        <label for="correoElectronico">Correo Electrónico:</label><br>
                        <input type="text" id="correoElectronico" name="correoElectronico"><br>
                        <label for="clave">Contraseña:</label><br>
                        <input type="text" id="clave" name="clave"><br>
                        <label for="repetirClave">Repetir Contraseña:</label><br>
                        <input type="text" id="repetirClave" name="repetirClave"><br>
                        <label for="nombre">Nombre:</label><br>
                        <input type="text" id="nombre" name="nombre"><br>
                        <label for="codigoPostal">Código Postal:</label><br>
                        <input type="text" id="codigoPostal" name="codigoPostal"><br>
                        <label for="facebook">Facebook:</label><br>
                        <input type="text" id="facebook" name="facebook"><br>
                        <label for="twitter">Twitter:</label><br>
                        <input type="text" id="twitter" name="twitter"><br>
                        <label for="telefono">Teléfono De Contacto:</label><br>
                        <input type="text" id="telefono" name="telefono">
                    </form> 
                </div>
            </div>

        <jsp:include page="components/footer.jsp"></jsp:include>
    </body>
</html>
