document.getElementsByClassName("miniatura-usuario")[0].addEventListener("click", () => {
   
    let miniMenu = document.getElementById("user-mini-menu");
    
    if(miniMenu.style.display === "none" || miniMenu.style.display === "") {
        miniMenu.style.display = "flex";
        miniMenu.style.width = document.getElementsByClassName("miniatura-usuario")[0].offsetWidth + "px";
    } else {
        miniMenu.style.display = "none";
    }
    
});


