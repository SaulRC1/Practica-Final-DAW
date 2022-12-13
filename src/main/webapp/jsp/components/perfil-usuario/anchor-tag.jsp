<%-- 
    Document   : anchor-tag
    Created on : 9 dic 2022, 13:23:49
    Author     : SaulRC1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="data-tag flex-row flex-justify-content-start flex-align-items-center">
    
    <img class="data-tag-icon" src="${param.icon}">
    
    <a href="${param.anchorLink}" class="data-tag-text roboto-condensed">${param.anchorAbbreviation}</a>
    
</div>
