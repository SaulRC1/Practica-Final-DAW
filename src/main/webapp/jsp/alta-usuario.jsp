<%-- 
    Document   : alta-usuario
    Created on : 30 oct 2022, 1:42:43
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Défteros Market | Alta Usuario</title>

        <link rel="icon" href="${pageContext.request.contextPath}/images/omega.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pages/alta-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexbox-classes.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/buttons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/cabezera.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/menu.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/miniatura-usuario.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/components/alta-usuario/prefijo-movil-tag.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/greek-font.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fonts/custom/roboto-condensed.css">
        <script src="${pageContext.request.contextPath}/js/alta-usuario.js" defer></script>
    </head>
    <body>
        <div class="main-div flex-column flex-justify-content-start">
            <jsp:include page="components/cabezera.jsp"></jsp:include>
            <jsp:include page="components/menu.jsp"></jsp:include>

                <div class="main-section flex-row flex-justify-content-start">

                    <div class="image-div flex-column flex-align-items-center flex-justify-content-start">
                        <p class="roboto-condensed image-title">¡Registrate en Défteros Market para comenzar a vender!</p>

                        <div class="carroussel-image-div flex-column flex-align-items-center">
                            <img src="${pageContext.request.contextPath}/images/alta_usuario/register1.png">
                    </div>
                </div>

                <div class="form-div flex-column flex-align-items-center flex-justify-content-start">
                    <form class="registro-form flex-column flex-justify-content-start" 
                          action="${pageContext.request.contextPath}/alta-usuario"
                          method="post" onsubmit="return validaAltaUsuario()"
                          id="formulario-registro"
                          enctype="multipart/form-data">

                        <div class="upload-user-image-div flex-row flex-justify-content-start flex-align-items-center">

                            <div class="image-container flex-column flex-align-items-center flex-justify-content-center">
                                <img id="img-perfil" src="${pageContext.request.contextPath}/images/usuarios/default.png">
                            </div>

                            <input type="file" accept=".png,.jpg,.jpeg" name="imagenDeUsuario" id="imagenDeUsuario">

                            <button class="defteros-button roboto-condensed" id="upload-image-button" type="button">Seleccione una imagen de usuario</button>

                        </div>
                            
                        <!-- Error la imagen de perfil -->
                        <c:if test="${not empty errorImagen}">
                            <div class="form-element flex-row flex-justify-content-start flex-align-items-center">  
                                <p class="form-error roboto-condensed">${errorImagen}</p>
                            </div>
                        </c:if>

                        <!-- Correo Electronico -->
                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="correoElectronico">Correo Electrónico:</label>
                            <input class="roboto-condensed form-input" type="text" id="correoElectronico" name="correoElectronico" required>
                        </div>

                        <!-- Error en el correo electronico -->
                        <c:if test="${not empty errorCorreoElectronico}">
                            <div class="form-element flex-row flex-justify-content-start flex-align-items-center">  
                                <p class="form-error roboto-condensed">${errorCorreoElectronico}</p>
                            </div>
                        </c:if>

                        <!-- Contraseña -->
                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="clave">Contraseña:</label>
                            <input class="roboto-condensed form-input" type="text" id="clave" name="clave" required>
                        </div>

                        <!-- Error en la contraseña -->
                        <c:if test="${not empty errorPassword}">
                            <div class="form-element flex-row flex-justify-content-start flex-align-items-center">  
                                <p class="roboto-condensed form-error">${errorPassword}</p>
                            </div>
                        </c:if>


                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="repetirClave">Repetir Contraseña:</label>
                            <input class="roboto-condensed form-input" type="text" id="repetirClave" name="repetirClave" required>
                        </div>

                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="nombre">Nombre:</label>
                            <input class="roboto-condensed form-input" type="text" id="nombre" name="nombre" required>
                        </div>
                        
                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="direccion">Dirección:</label>
                            <input class="roboto-condensed form-input" type="text" id="direccion" name="direccion">
                        </div>

                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="codigoPostal">Código Postal:</label>
                            <input class="roboto-condensed form-input" type="text" id="codigoPostal" name="codigoPostal" required>
                        </div>

                        <!-- Error en el codigo postal -->
                        <c:if test="${not empty errorCodigoPostal}">
                            <div class="form-element flex-row flex-justify-content-start flex-align-items-center">  
                                <p class="roboto-condensed form-error">${errorCodigoPostal}</p>
                            </div>
                        </c:if>

                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="facebook">Facebook:</label>
                            <input class="roboto-condensed form-input" type="text" id="facebook" name="facebook">
                        </div>

                        <!-- Error en el facebook -->
                        <c:if test="${not empty errorFacebook}">
                            <div class="form-element flex-row flex-justify-content-start flex-align-items-center">  
                                <p class="roboto-condensed form-error">${errorFacebook}</p>
                            </div>
                        </c:if>

                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="twitter">Twitter:</label>
                            <input class="roboto-condensed form-input" type="text" id="twitter" name="twitter">
                        </div>

                        <!-- Error en el twitter -->
                        <c:if test="${not empty errorTwitter}">
                            <div class="form-element flex-row flex-justify-content-start flex-align-items-center">  
                                <p class="roboto-condensed form-error">${errorTwitter}</p>
                            </div>
                        </c:if>

                        <input id="prefijo-movil" name="prefijo-movil" type="text" class="hidden-element" value="+34">

                        <div class="form-element flex-row flex-justify-content-start flex-align-items-center">
                            <label class="roboto-condensed form-label" for="telefono">Teléfono De Contacto:</label>
                            <div id="prefijos-movil" class="prefijo-movil-dropdown-list flex-column flex-justify-content-start">
                                <div class="prefijo-movil-dropdown-list-main flex-column flex-justify-content-start">
                                    <div class="prefijo-movil-tag flex-row flex-justify-content-start flex-align-items-center">
                                        <img id="prefijo-movil-list-main-img" src="${pageContext.request.contextPath}/images/alta_usuario/banderas/spain.png">
                                        <p id="prefijo-movil-list-main-p" class="roboto-condensed">+34</p>
                                    </div>
                                </div>

                                <div class="prefijo-movil-dropdown-list-body flex-column flex-justify-content-start hidden-element">
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="34"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/spain.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="355"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/albania.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="49"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/alemania.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="376"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/andorra.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="374"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/armenia.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="43"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/austria.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="994"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/azerbaiyan.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="32"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/belgica.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="358"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/finlandia.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="33"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/francia.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="30"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/grecia.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="39"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/italia.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="351"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/portugal.png"/>
                                    </jsp:include>
                                    <jsp:include page="components/alta-usuario/prefijo-movil-tag.jsp">
                                        <jsp:param name="prefijo" value="44"/>
                                        <jsp:param name="bandera" value="${pageContext.request.contextPath}/images/alta_usuario/banderas/reino-unido.png"/>
                                    </jsp:include>
                                </div>

                            </div>

                            <button type="button" id="prefijo-movil-list-button" class="prefijo-movil-dropdown-list-button flex-column flex-justify-content-center flex-align-items-center">
                                <img src="${pageContext.request.contextPath}/images/components/icons/arrow-down.png">
                            </button>
                            <input class="roboto-condensed form-input" type="text" id="telefono" name="telefono" placeholder="6573868498" required>
                        </div>

                        <!-- Error en telefono de contacto -->
                        <c:if test="${not empty errorTelefono}">
                            <div class="form-element flex-row flex-justify-content-start flex-align-items-center">  
                                <p class="roboto-condensed form-error">${errorTelefono}</p>
                            </div>
                        </c:if>

                        <input class="roboto-condensed form-submit-button" type="submit" value="¡Registrame!">
                    </form> 
                </div>
            </div>

            <jsp:include page="components/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
