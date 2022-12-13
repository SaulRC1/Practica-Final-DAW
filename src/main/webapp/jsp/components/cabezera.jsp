<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="cabezera-div flex-row flex-justify-content-start flex-align-items-center">
    <div class="cabezera-first-section flex-row flex-justify-content-start flex-align-items-center">
        <img id="defteros-logo" src="${pageContext.request.contextPath}/images/omega.png">
        <p class="greek-font defteros-font-style">DÉFTEROS MARKET</p>
    </div>

    <div class="cabezera-second-section flex-row flex-justify-content-end flex-align-items-center">
        <div class="cabezera-user-section flex-row flex-justify-content-center flex-align-items-center">
            <jsp:include page="miniatura-usuario.jsp">
                <jsp:param name="imagenUsuario" value="${usuarioPublico.rutaImagen}"/>
                <jsp:param name="nombreDeUsuario" value="${usuarioPublico.nombre}"/>
            </jsp:include>
        </div>
    </div>

</div>
