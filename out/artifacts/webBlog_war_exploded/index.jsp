
<!DOCTYPE html>
<html>
<%@ page import="pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: Neco Yang
  Date: 2018/10/4
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <head>
      <title>The Hungry Traveller</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
      <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
            id="bootstrap-css">
      <link rel="stylesheet" type="text/css" href="Homepage.css">

    <script>
        var modal = document.getElementById('id02');
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }


    </script>
      <link rel="shortcut icon" type="image/png" href="images/favicon.ico"/>

  </head>


  <!----------------------------------------------Navbar header------------------------------------------->
<body>
  <% HttpSession userSession = request.getSession();
    User author = (User) userSession.getAttribute("userInfo");%>
<nav class="navbar navbar-expand-md navbar-dark" id="navbar">
    <a class="navbar-brand" href="index.jsp"><img src="images/loogoo.png" style="height:50px;"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="nav navbar-nav flex-fill w-100 flex-nowrap">
                    <li class="nav-item">
                <a class="nav-link" href="/getAllArticles">Article Gallery</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/gallery">Media Gallery</a>
            </li>

        </ul>
        <ul class="navbar-nav ml-auto w-100 justify-content-center">
                <li>
                    <form class="form-inline" action="SearchArticle" id="searchField">
                        <div class="input-group" id="searchbox">
                            <input type="text" name="info" class="form-control" placeholder="Search articles">
                        </div>
                    </form>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto w-100 justify-content-end">

            <% if(author==null){%>
            <li class="nav-item">
                <a href = "RegistrationForm.jsp" class="nav-link"> Sign Up</a>
            </li>
            <li class="nav-item">

                <a class="nav-link" onclick="document.getElementById('id02').style.display='block'"> Login</a>
            </li>

                <%} else{%>
            <li class="nav-item">
                <a class="nav-link" href="personalpage.jsp"><%=author.getRealName()%></a>
            </li>
            <% if(author.getId()== 62){%>
            <li class="nav-item"><a class="nav-link" href = "adminPage.jsp" class= pointer> Admin Page</a></li>
            <%}%>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Sign Out</a>
            </li><%}%>
        </ul>

    </div>
</nav>

  <!------------------------------------------------------------------------------------------------------>
  <!----------------------------------------------Jumbotron title----------------------------------------->
  <div class="container">
    <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12 post-title-block">
        <h1 class="text-center"><img src="images/titleLogo.png" style="width:100%"></h1>
      </div>
    </div>
  </div>
  <!------------------------------------------------------------------------------------------------------>
  <!----------------------------------------------Title and Blog Name------------------------------------->
  <div>
    <section>
      <ul>
        <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
        <li><a href="/getAllArticles"><span class="glyphicon glyphicon-send"></span> Posts</a></li>
        <li><a href="/gallery"><span class="glyphicon glyphicon-picture"></span> Photos</a></li>
      </ul>
    </section>
  </div>

  <!------------------------------------------------------------------------------------------------------>
  <!----------------------------------------------Blog Content-------------------------------------------->
    <!----------------------------------------------Blog Content-------------------------------------------->
    <section id="blog">
      <div class="container">

        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 post-title-block">
                <h2 class="text-center">3-star Michelin food destinations</h2>
                <p class="text-center">Michelin stars are a rating system used by the red Michelin Guide to grade restaurants on their quality.  </p>
            </div>
        </div>


        <div class="row">
          <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
            <div class="card text-center">
              <img class="card-img-top" src="./images/RyuGinTokyo.jpg" alt="" style="width:100%">
              <div class="card-block">
                <h4 class="card-title">A Perfect Plate at This Restaurant in Tokyo</h4>
                <p class="card-text">This being Roppongi, one of Tokyo’s glitziest
                  nightlife centers, the clientele is strongly weighted in favor
                  of high-rollers, both local and expat.</p>
                <br>
                <%--Add  ?param1= to href allows us to passs parameter into servlet--%>
                <a class="btn btn-default"  href="/getSingle?param1=67" value="67">Read More</a>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
            <div class="card text-center">
              <img class="card-img-top" src="./images/Le_Calandre.jpg" alt="" style="width:100%">
              <div class="card-block">
                <h4 class="card-title">Le Calandre: in the village of Sarmeola di Rubano</h4>
                <p class="card-text">Le Calandre has been described as one of Italy's most cutting-edge
                  restaurants, that is consistently judged by major critics as one of the best
                  in the country. </p>
                <%--Add  ?param1= to href allows us to passs parameter into servlet--%>
                <a class="btn btn-default" href="/getSingle?param1=11" value="11">Read More</a>
              </div>
            </div>
          </div>



          <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
            <div class="card text-center">
              <img class="card-img-top" src="./images/olive.jpg" alt="" style="width:100%">
              <div class="card-block">
                <h4 class="card-title">El Celler de Can Roca: Traditional Catalan</h4>
                <p class="card-text">Dishes served include those based on perfumes, and with unusual
                  presentations such as caramelised olives served on a bonsai tree..</p>
                <br>
                <%--Add  ?param1= to href allows us to passs parameter into servlet--%>
                <a class="btn btn-default" href="/getSingle?param1=52" value="52">Read More</a>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
            <div class="card text-center">
              <img class="card-img-top" src="./images/Carte_Printemps.jpg" alt="" style="width:100%">
              <div class="card-block">
                <h4 class="card-title">Hotel de la Paix: one of most prestigious addresses</h4>
                <p class="card-text">One of the most luxurious hotels in Geneva.
                  Whilst retaining its soul has been redecorated with the most
                  contemporary elegance revealing Italian architecture.</p>
                  <br>
                <%--Add  ?param1= to href allows us to passs parameter into servlet--%>
                <a class="btn btn-default" href="/getSingle?param1=12" value="12">Read More</a>
              </div>
            </div>
          </div>
        </div> <!-- row -->

      </div>
    </section>
    <!------------------------------------------------------------------------------------------------------>
    <!----------------------------------------------Blog Content-------------------------------------------->
    <br>
    <br>
 <!---------------------------------------------blog footer---------------------------------------------->
<div class="jumbotron text-center" id="jumbo">
    <p><a href="#">About</a></p>
    <p><a href="#">FAQ</a></p>
    <p><a href="#">Contact us</a></p>
    <br>
    <br>
    <p>Copyright © All Rights Reserved 2020 | Template Design & Development by Team-Two</p>
</div>
<!------------------------------------------------------------------------------------------------------>
<!----------------------------------------------Login Modal--------------------------------------------->
<div id="id02" class="modal">
      <div class="container">
        <div class="login-form">
          <div class="main-div">
            <div class="imgContainer">
              <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
            </div>
            <div class="panel">
              <h2>User Login</h2>
              <p>Please enter your username and password</p>
            </div>
            <form id="Login" action="/login" method="post">
              <div class="group">
                <input type="text" name="login" class="form-control" id="inputUsername" placeholder="Username">
              </div>

              <div class="group">
                <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
              </div>
              <div class="forgot">
                <a href=forgotPassword.jsp>Forgot password?</a>
              </div>
              <button type="submit" class="btn btn-primary">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>
<!------------------------------------------------------------------------------------------------------>
<!----------------------------------------------Admin Login Modal--------------------------------------->
<div id="id03" class="modal">
    <div class="container">
        <div class="login-form">
            <div class="main-div">
                <div class="imgContainer">
                    <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
                </div>
                <div class="panel">
                    <h2>Admin Login</h2>
                    <p>Please enter your username and password</p>
                </div>
                <form id="loginAdmin" action="/login" method="post">
                    <div class="group">
                        <input type="text" name="login" class="form-control" id=Username" placeholder="Username">
                    </div>

                    <div class="group">
                        <input type="password" name="password" class="form-control" id="Password" placeholder="Password">
                    </div>
                    <div class="forgot">
                        <a href=forgotPassword.jsp>Forgot password?</a>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!------------------------------------------------------------------------------------------------------>
  </body>
</html>

