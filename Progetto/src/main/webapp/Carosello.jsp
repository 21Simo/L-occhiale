<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
      * {
        box-sizing: border-box}
      .slide1, .slide2 {
        display: none}
      img {
        vertical-align: middle;
      }
      .carosello {
        max-width: 1000px;
        position: relative;
        margin: auto;
      }
      .prec, .succ {
        cursor: pointer;
        user-select:none;
        position: absolute;
        top: 50%;
        width: 40px;
        height:40px;
        border:2px solid #DDDDDD;
        color: rgb(45,45,45);
        font-size: 18px;
        transition: 0.5s ease;
        border-radius:50%;
        text-align: center;
        line-height: 38px;
      }
      .succ {
        right: 0;
      }
      .prec:hover, .succ:hover {
        background-color: #DDDDDD;
        color: black;
      }
    </style>
  </head>
  <body>
    <p>Carosello 1:
    </p>
    <div class="carosello">
      <div class="slide1">
        <img src="img/Logo.png" style="width:100%">
      </div>
      <div class="slide1">
        <img src="img/LogoAdobe.png" style="width:100%">
      </div>
      <div class="slide1">
        <img src="img/Logo.png" style="width:100%">
      </div>
      <a class="prec" onclick="cambiaSlide(-1, 0)">&#10094;
      </a>
      <a class="succ" onclick="cambiaSlide(1, 0)">&#10095;
      </a>
    </div>
    <p>Carosello 2:
    </p>
    <div class="carosello">
      <div class="slide2">
        <img src="img/Logo.png" style="width:100%">
      </div>
      <div class="slide2">
        <img src="img/LogoAdobe.png" style="width:100%">
      </div>
      <div class="slide2">
        <img src="img/Logo.png" style="width:100%">
      </div>
      <a class="prec" onclick="cambiaSlide(-1, 1)">&#10094;
      </a>
      <a class="succ" onclick="cambiaSlide(1, 1)">&#10095;
      </a>
    </div>
    <script>
      var slideTab = [1,1];
      var slideId = []
      var slides = document.getElementsByClassName("carosello");
      for (var e = 1; e <= slides.length; e++){
        slideId.push("slide"+e);
      }
      creaSlide(1, 0);
      creaSlide(1, 1);
      function cambiaSlide(n, no) {
        creaSlide(slideTab[no] += n, no);
      }
      function creaSlide(n, no) {
        var i;
        var x = document.getElementsByClassName(slideId[no]);
        if (n > x.length) {
          slideTab[no] = 1}
        if (n < 1) {
          slideTab[no] = x.length}
        for (i = 0; i < x.length; i++) {
          x[i].style.display = "none";
        }
        x[slideTab[no]-1].style.display = "block";
      }
    </script>
  </body>
</html>