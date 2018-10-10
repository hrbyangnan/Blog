<%--
  Created by IntelliJ IDEA.
  User: Neco Yang
  Date: 2018/10/4
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>blog</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="Homepage.css">

    <script>
        var modal = document.getElementById('id02');
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
  </head>

  <body>
  <!----------------------------------------------Navbar header------------------------------------------->
  <nav class="navbar navbar-inverse">
    <div class="container">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Blog Name</a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li><a href = "Homepage.html" class= pointer><span class="glyphicon glyphicon-home"></span> Home</a></li>

          <li><a href="/getAllArticles"><span class="glyphicon glyphicon-th-large"></span> Archives</a></li>

          <li><a href="personalPage.html"><span class="glyphicon glyphicon-user"></span> Author Page</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <form class="navbar-form navbar-left">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="Search">
              <div class="input-group-btn">
                <button class="btn btn-default" type="submit">
                  <i class="glyphicon glyphicon-search"></i>
                </button>
              </div>
            </div>
          </form>
          <li><a href = "RegistrationForm.html" class= pointer><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
          <li><a class = pointer onclick="document.getElementById('id02').style.display='block'"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <!------------------------------------------------------------------------------------------------------>
  <!----------------------------------------------Jumbotron title----------------------------------------->
  <div class="container">

    <div class="row">
      <div class="col-lg-12 col-md-12 col-sm-12 post-title-block">
        <h1 class="text-center">post Title goes here</h1>
        <ul class="list-inline text-center">
          <li>Author |</li>
          <li>Category |</li>
          <li>Date |</li>
        </ul>
      </div>
    </div>
  </div>
  <!------------------------------------------------------------------------------------------------------>
  <!----------------------------------------------Title and Blog Name------------------------------------->
  <div>
    <section>
      <ul>
        <li><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-send"></span> Posts</a></li>
        <li><a href="#">Features</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-picture"></span> Photos</a></li>
      </ul>
    </section>
  </div>
  <a href="displayArticle.jsp">Display Article</a>
  <!------------------------------------------------------------------------------------------------------>
  <!----------------------------------------------Blog Content-------------------------------------------->
  <section id="blog">
    <div class="container">

      <div class="row">
        <div class="col-lg-6 col-lg-offset-3 text-center">
          <h2>
            <span class="minus"></span>Blog Posts<span class="minus"></span>
          </h2>
          <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
            Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus </p>
          <br>
        </div>
      </div>

      <div class="row">
        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
          <div class="card text-center">
            <img class="card-img-top" src="./images/AucklandCity.jpg" alt="" width="100%">
            <div class="card-block">
              <h4 class="card-title">Post Title</h4>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
                Aenean commodo ligula eget dolor. Aenean massa.</p>
              <form action="getSingle" method="get">
              <%--<a class="btn btn-default" name="13"  href="#">Read More</a>--%>
                <button type="submit" name="id" class="btn btn-primary">Read More</button>
              </form>
            </div>
          </div>
        </div>

        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
          <div class="card text-center">
            <img class="card-img-top" src="./images/AucklandCityNight.jpg" alt="" width="100%">
            <div class="card-block">
              <h4 class="card-title">Post Title</h4>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
                Aenean commodo ligula eget dolor. Aenean massa.</p>
              <a class="btn btn-default" href="#">Read More</a>
            </div>
          </div>
        </div>

        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
          <div class="card text-center">
            <img class="card-img-top" src="./images/AucklandCityNightpic.jpg" alt="" width="100%">
            <div class="card-block">
              <h4 class="card-title">Post Title</h4>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
                Aenean commodo ligula eget dolor. Aenean massa.</p>
              <a class="btn btn-default" href="#">Read More</a>
            </div>
          </div>
        </div>

        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
          <div class="card text-center">
            <img class="card-img-top" src="./images/Rangitoto.jpg" alt="" width="100%">
            <div class="card-block">
              <h4 class="card-title">Post Title</h4>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
                Aenean commodo ligula eget dolor. Aenean massa.</p>
              <a class="btn btn-default" href="#">Read More</a>
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
  <section id="blogContent">
    <div class="container">

      <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

          <h1 class="my-4">Michael Joseph Savage Memorial Park
            <small>mbuga za wanyama</small>
          </h1>

          <!-- Blog Post -->
          <div class="card mb-4">
            <img class="card-img-top" src="./images/MichaelSalvage%20%20.jpg" alt="Card image cap">
            <div class="card-body">
              <h2 class="card-title">Fersjabd</h2>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta
                expedita corporis animi vero voluptate voluptatibus possimus, veniam magni quis!</p>
              <a class="btn btn-default" href="#">Read More</a>
            </div>
            <div class="card-footer text-muted">
              Posted on January 1, 2017 by
              <a href="#">Alice</a>
            </div>
          </div>
          <br>
          <br>

          <!-- Blog Post -->
          <div class="card mb-4">
            <img class="card-img-top" src="./images/AucklandCity.jpg">
            <div class="card-body">
              <h2 class="card-title">Fjsdfhlnal</h2>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta
                expedita corporis animi vero voluptate voluptatibus possimus, veniam magni quis!</p>
              <a class="btn btn-default" href="#">Read More</a>
            </div>
            <div class="card-footer text-muted">
              Posted on January 1, 2017 by
              <a href="#">Stuart</a>
            </div>
          </div>
          <br>
          <br>

          <!-- Blog Post -->
          <div class="card mb-4">
            <img class="card-img-top" src="./images/AucklandCity.jpg" alt="Card image cap">
            <div class="card-body">
              <h2 class="card-title">Koiusdk</h2>
              <p class="card-text">Lorem ipsum dolor sit amet,
                consectetur adipisicing elit. Reiciendis aliquid
                atque, nulla? Quos cum ex quis soluta, a laboriosam.
                Dicta expedita corporis animi vero voluptate voluptatibus
                possimus, veniam magni quis!</p>
              <a class="btn btn-default" href="#">Read More</a>
            </div>
            <div class="card-footer text-muted">
              Posted on January 1, 2017 by
              <a href="#">Sara</a>
            </div>
          </div>
          <br>
          <br>
          <!-- Pagination -->
          <ul class="pagination justify-content-center mb-4">
            <li class="page-item">
              <a class="page-link" href="#">← Older</a>
            </li>
            <li class="page-item disabled">
              <a class="page-link" href="#">Newer →</a>
            </li>
          </ul>
        </div>
        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">
          <!-- Search Widget -->
          <div class="card my-1">
            <h5 class="card-header">Search</h5>
            <div class="card-body">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <button class="btn btn-secondary" type="button">Go!</button>
                </span>
              </div>
            </div>
          </div>
          <br>
          <br>
          <!-- Categories Widget -->
          <div class="card my-2">
            <h5 class="card-header">Categories</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">Hidso</a>
                    </li>
                    <li>
                      <a href="#">Jodsa</a>
                    </li>
                    <li>
                      <a href="#">Jsap</a>
                    </li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">Opand</a>
                    </li>
                    <li>
                      <a href="#">Kiki</a>
                    </li>
                    <li>
                      <a href="#">Fuisoa</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <br>
          <br>
          <!-- Side Widget -->
          <div class="card my-3">
            <h5 class="card-header">Kosjas</h5>
            <div class="card-body">
              consectetur adipisicing elit. Reiciendis aliquid
              atque, nulla? Quos cum ex quis soluta,
            </div>
          </div>
          <br>
          <br>
          <!-- Side Widget -->
          <div class="card my-4">
            <h5 class="card-header">banner</h5>
            <div class="card-body">
              <img class="card-img-top" id="banner" src="./images/AucklandCity.jpg" alt="Card image cap">
            </div>
          </div>
          <br>
          <br>
        </div>
      </div>
    </div>
  </section>
  <!------------------------------------------------------------------------------------------------------>
  <!----------------------------------------------Blog Footer--------------------------------------------->
  <section id="blogFooter">
    <footer class="area">
      <div class="container">
        <div class="row">
          <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 col-6">
            <div class="footer-title"><strong>Team</strong></div>
            <br>
            <p><a href="#">About</a></p>
            <p><a href="#">FAQ</a></p>
            <p><a href="#">Legal & Privacy</a></p>
          </div>
          <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6 col-6">
            <div class="footer-title"><strong>Quick Links</strong></div>
            <br>
            <p><a href="#">News</a></p>
            <p><a href="#">Contact us</a></p>
          </div>
        </div>
        <div class="row">
          <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 text-center">
            <br>
            <p>Copyright © All Rights Reserved 2020 | Template Design & Development by Team-Two</p>
          </div>
        </div>
      </div>
    </footer>
  </section>
  <!----------------------------------------------Login Modal--------------------------------------------->
  <div id="id02" class="modal">
    <div class="container">
      <div class="login-form">
        <div class="main-div">
          <div class="imgContainer">
            <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
          </div>
          <div class="panel">
            <h2>Admin Login</h2>
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
              <a href=#>Forgot password?</a>
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

