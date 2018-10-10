<%--
  Created by IntelliJ IDEA.
  User: kfit706
  Date: 9/10/2018
  Time: 9:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!DOCTYPE html>
<head>
    <title>Sign Up</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="index.css">
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>


    <style>
        input[type=text], select, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        label {
            padding: 12px 12px 12px 0;
            display: inline-block;
        }

        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }


    </style>
</head>
<body>
<!------------------------------------------------------------------------------------------------------>
<div id="nav">
    <nav class="navbar navbar-default navbar-fixed-top" style="margin-bottom: 0px">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
                <li><a href="#">Gallery</a></li>
            </ul>
            <form class="navbar-form navbar-left" action="/action_page.php">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right" id="loggedout">
                <li><a href="#" >
                <%--<li><a href="#" onclick="showAndHideUl()"><span>--%>
                    <span class="glyphicon glyphicon-user"></span> Sign Up</a></li>

                <li><a href="#" onclick="document.getElementById('login').style.display='block'">
                <%--<li><a href="#" onclick="showAndHideUl()"><span--%>

                    <span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
            <%--<!--<ul class="nav navbar-nav navbar-right" id="loggedin">-->--%>
            <%--<!--<li><a href="#" onclick="showAndHideUl()"><span-->--%>
            <%--<!--class="glyphicon glyphicon-log-out"></span> Log Out</a></li>            </ul>-->--%>
        </div>
    </nav>
</div>
<!------------------------------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------------------------------>
<div class="container">
    <div class="row">
        <div class="col-md-3 ">

        </div>
        <div class="col-md-9">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <h4>Your Profile</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form action="/register" method="post">
                                <div class="form-group row">
                                    <label for="username" class="col-4 col-form-label">User Name*</label>
                                    <div class="col-8">
                                        <input id="username" name="username" placeholder="Username" required="required"
                                               type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="name" class="col-4 col-form-label">First Name</label>
                                    <div class="col-8">
                                        <input id="name" name="firstName" placeholder="First Name" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="lastname" class="col-4 col-form-label">Last Name</label>
                                    <div class="col-8">
                                        <input id="lastname" name="lastName" placeholder="Last Name" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-4 col-form-label">Country</label>
                                    <div class="col-8">
                                        <select id="country" name="country">
                                            <option value="" selected="selected"></option>
                                            <option value="Afghanistan">Afghanistan</option>
                                            <option value="Albania">Albania</option>
                                            <option value="Algeria">Algeria</option>
                                            <option value="Andorra">Andorra</option>
                                            <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                                            <option value="Argentina">Argentina</option>
                                            <option value="Armenia">Armenia</option>
                                            <option value="Australia">Australia</option>
                                            <option value="Austria">Austria</option>
                                            <option value="Azerbaijan">Azerbaijan</option>
                                            <option value="Bahamas">Bahamas</option>
                                            <option value="Bahrain">Bahrain</option>
                                            <option value="Bangladesh">Bangladesh</option>
                                            <option value="Barbados">Barbados</option>
                                            <option value="Belarus">Belarus</option>
                                            <option value="Belgium">Belgium</option>
                                            <option value="Belize">Belize</option>
                                            <option value="Benin">Benin</option>
                                            <option value="Bhutan">Bhutan</option>
                                            <option value="Bolivia">Bolivia</option>
                                            <option value="Bosnia and Herzegovina">Bosnia and Herzegovina</option>
                                            <option value="Botswana">Botswana</option>
                                            <option value="Brazil">Brazil</option>
                                            <option value="Brunei">Brunei</option>
                                            <option value="Bulgaria">Bulgaria</option>
                                            <option value="Burkina Faso">Burkina Faso</option>
                                            <option value="Burundi">Burundi</option>
                                            <option value="Cambodia">Cambodia</option>
                                            <option value="Cameroon">Cameroon</option>
                                            <option value="Canada">Canada</option>
                                            <option value="Cape Verde">Cape Verde</option>
                                            <option value="Central African Republic">Central African Republic</option>
                                            <option value="Chad">Chad</option>
                                            <option value="Chile">Chile</option>
                                            <option value="China">China</option>
                                            <option value="Colombia">Colombia</option>
                                            <option value="Comoros">Comoros</option>
                                            <option value="Congo">Congo</option>
                                            <option value="Costa Rica">Costa Rica</option>
                                            <option value="Cote d'Ivoire">Cote d'Ivoire</option>
                                            <option value="Croatia">Croatia</option>
                                            <option value="Cuba">Cuba</option>
                                            <option value="Cyprus">Cyprus</option>
                                            <option value="Czech Republic">Czech Republic</option>
                                            <option value="Denmark">Denmark</option>
                                            <option value="Djibouti">Djibouti</option>
                                            <option value="Dominica">Dominica</option>
                                            <option value="Dominican Republic">Dominican Republic</option>
                                            <option value="East Timor">East Timor</option>
                                            <option value="Ecuador">Ecuador</option>
                                            <option value="Egypt">Egypt</option>
                                            <option value="El Salvador">El Salvador</option>
                                            <option value="Equatorial Guinea">Equatorial Guinea</option>
                                            <option value="Eritrea">Eritrea</option>
                                            <option value="Estonia">Estonia</option>
                                            <option value="Ethiopia">Ethiopia</option>
                                            <option value="Fiji">Fiji</option>
                                            <option value="Finland">Finland</option>
                                            <option value="France">France</option>
                                            <option value="Gabon">Gabon</option>
                                            <option value="Gambia">Gambia</option>
                                            <option value="Georgia">Georgia</option>
                                            <option value="Germany">Germany</option>
                                            <option value="Ghana">Ghana</option>
                                            <option value="Greece">Greece</option>
                                            <option value="Grenada">Grenada</option>
                                            <option value="Guatemala">Guatemala</option>
                                            <option value="Guinea">Guinea</option>
                                            <option value="Guinea-Bissau">Guinea-Bissau</option>
                                            <option value="Guyana">Guyana</option>
                                            <option value="Haiti">Haiti</option>
                                            <option value="Honduras">Honduras</option>
                                            <option value="Hong Kong">Hong Kong</option>
                                            <option value="Hungary">Hungary</option>
                                            <option value="Iceland">Iceland</option>
                                            <option value="India">India</option>
                                            <option value="Indonesia">Indonesia</option>
                                            <option value="Iran">Iran</option>
                                            <option value="Iraq">Iraq</option>
                                            <option value="Ireland">Ireland</option>
                                            <option value="Israel">Israel</option>
                                            <option value="Italy">Italy</option>
                                            <option value="Jamaica">Jamaica</option>
                                            <option value="Japan">Japan</option>
                                            <option value="Jordan">Jordan</option>
                                            <option value="Kazakhstan">Kazakhstan</option>
                                            <option value="Kenya">Kenya</option>
                                            <option value="Kiribati">Kiribati</option>
                                            <option value="North Korea">North Korea</option>
                                            <option value="South Korea">South Korea</option>
                                            <option value="Kuwait">Kuwait</option>
                                            <option value="Kyrgyzstan">Kyrgyzstan</option>
                                            <option value="Laos">Laos</option>
                                            <option value="Latvia">Latvia</option>
                                            <option value="Lebanon">Lebanon</option>
                                            <option value="Lesotho">Lesotho</option>
                                            <option value="Liberia">Liberia</option>
                                            <option value="Libya">Libya</option>
                                            <option value="Liechtenstein">Liechtenstein</option>
                                            <option value="Lithuania">Lithuania</option>
                                            <option value="Luxembourg">Luxembourg</option>
                                            <option value="Macedonia">Macedonia</option>
                                            <option value="Madagascar">Madagascar</option>
                                            <option value="Malawi">Malawi</option>
                                            <option value="Malaysia">Malaysia</option>
                                            <option value="Maldives">Maldives</option>
                                            <option value="Mali">Mali</option>
                                            <option value="Malta">Malta</option>
                                            <option value="Marshall Islands">Marshall Islands</option>
                                            <option value="Mauritania">Mauritania</option>
                                            <option value="Mauritius">Mauritius</option>
                                            <option value="Mexico">Mexico</option>
                                            <option value="Micronesia">Micronesia</option>
                                            <option value="Moldova">Moldova</option>
                                            <option value="Monaco">Monaco</option>
                                            <option value="Mongolia">Mongolia</option>
                                            <option value="Montenegro">Montenegro</option>
                                            <option value="Morocco">Morocco</option>
                                            <option value="Mozambique">Mozambique</option>
                                            <option value="Myanmar">Myanmar</option>
                                            <option value="Namibia">Namibia</option>
                                            <option value="Nauru">Nauru</option>
                                            <option value="Nepal">Nepal</option>
                                            <option value="Netherlands">Netherlands</option>
                                            <option value="New Zealand">New Zealand</option>
                                            <option value="Nicaragua">Nicaragua</option>
                                            <option value="Niger">Niger</option>
                                            <option value="Nigeria">Nigeria</option>
                                            <option value="Norway">Norway</option>
                                            <option value="Oman">Oman</option>
                                            <option value="Pakistan">Pakistan</option>
                                            <option value="Palau">Palau</option>
                                            <option value="Panama">Panama</option>
                                            <option value="Papua New Guinea">Papua New Guinea</option>
                                            <option value="Paraguay">Paraguay</option>
                                            <option value="Peru">Peru</option>
                                            <option value="Philippines">Philippines</option>
                                            <option value="Poland">Poland</option>
                                            <option value="Portugal">Portugal</option>
                                            <option value="Puerto Rico">Puerto Rico</option>
                                            <option value="Qatar">Qatar</option>
                                            <option value="Romania">Romania</option>
                                            <option value="Russia">Russia</option>
                                            <option value="Rwanda">Rwanda</option>
                                            <option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
                                            <option value="Saint Lucia">Saint Lucia</option>
                                            <option value="Saint Vincent and the Grenadines">Saint Vincent and the
                                                Grenadines
                                            </option>
                                            <option value="Samoa">Samoa</option>
                                            <option value="San Marino">San Marino</option>
                                            <option value="Sao Tome and Principe">Sao Tome and Principe</option>
                                            <option value="Saudi Arabia">Saudi Arabia</option>
                                            <option value="Senegal">Senegal</option>
                                            <option value="Serbia and Montenegro">Serbia and Montenegro</option>
                                            <option value="Seychelles">Seychelles</option>
                                            <option value="Sierra Leone">Sierra Leone</option>
                                            <option value="Singapore">Singapore</option>
                                            <option value="Slovakia">Slovakia</option>
                                            <option value="Slovenia">Slovenia</option>
                                            <option value="Solomon Islands">Solomon Islands</option>
                                            <option value="Somalia">Somalia</option>
                                            <option value="South Africa">South Africa</option>
                                            <option value="Spain">Spain</option>
                                            <option value="Sri Lanka">Sri Lanka</option>
                                            <option value="Sudan">Sudan</option>
                                            <option value="Suriname">Suriname</option>
                                            <option value="Swaziland">Swaziland</option>
                                            <option value="Sweden">Sweden</option>
                                            <option value="Switzerland">Switzerland</option>
                                            <option value="Syria">Syria</option>
                                            <option value="Taiwan">Taiwan</option>
                                            <option value="Tajikistan">Tajikistan</option>
                                            <option value="Tanzania">Tanzania</option>
                                            <option value="Thailand">Thailand</option>
                                            <option value="Togo">Togo</option>
                                            <option value="Tonga">Tonga</option>
                                            <option value="Trinidad and Tobago">Trinidad and Tobago</option>
                                            <option value="Tunisia">Tunisia</option>
                                            <option value="Turkey">Turkey</option>
                                            <option value="Turkmenistan">Turkmenistan</option>
                                            <option value="Tuvalu">Tuvalu</option>
                                            <option value="Uganda">Uganda</option>
                                            <option value="Ukraine">Ukraine</option>
                                            <option value="United Arab Emirates">United Arab Emirates</option>
                                            <option value="United Kingdom">United Kingdom</option>
                                            <option value="United States">United States</option>
                                            <option value="Uruguay">Uruguay</option>
                                            <option value="Uzbekistan">Uzbekistan</option>
                                            <option value="Vanuatu">Vanuatu</option>
                                            <option value="Vatican City">Vatican City</option>
                                            <option value="Venezuela">Venezuela</option>
                                            <option value="Vietnam">Vietnam</option>
                                            <option value="Yemen">Yemen</option>
                                            <option value="Zambia">Zambia</option>
                                            <option value="Zimbabwe">Zimbabwe</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row" style="width: 200px">
                                    <label for="date" class="col-4 col-form-label">Birthday: </label>
                                    <div class="col-8">
                                        <input id="date" name="birthday" required="required" type="date">
                                    </div>
                                </div>
                                <div class="form-group row" id="selectAvatar" style="width: 400px">
                                    <label for="selectAvatar" class="col-4 col-form-label">Choose Avatar</label>
                                    <div class="col-8">
                                            <table>
                                                <tr>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/01.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="Images/Avatars/01.png">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/02.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="Images/Avatars/02.png">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/03.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="Images/Avatars/03.png">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/04.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="04">
                                                    </td>
                                                </tr>
                                                <tr><tr>
                                                <td style="text-align: center; padding:10px">
                                                    <img src="Images/Avatars/05.png" style="width: 50px"><br>
                                                    <input type="radio" name="avatar" value="05">
                                                </td>
                                                <td style="text-align: center; padding:10px">
                                                    <img src="Images/Avatars/06.png" style="width: 50px"><br>
                                                    <input type="radio" name="avatar" value="06">
                                                </td>
                                                <td style="text-align: center; padding:10px">
                                                    <img src="Images/Avatars/07.png" style="width: 50px"><br>
                                                    <input type="radio" name="avatar" value="07">
                                                </td>
                                                <td style="text-align: center; padding:10px">
                                                    <img src="Images/Avatars/08.png" style="width: 50px"><br>
                                                    <input type="radio" name="avatar" value="08">
                                                </td>
                                            </tr>
                                                <tr>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/09.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="09">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/10.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="10">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/11.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="11">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/12.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="12">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/13.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="13">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/14.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="14">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/15.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="15">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/16.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="16">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/17.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="17">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/18.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="18">
                                                    </td>
                                                    <td style="text-align: center; padding:10px">
                                                        <img src="Images/Avatars/19.png" style="width: 50px"><br>
                                                        <input type="radio" name="avatar" value="19">
                                                    </td>
                                                </tr>
                                            </table>
                                    </div>
                                </div>
                                <div class="form-group row" style="width: 200px">
                                    <label for="select" class="col-4 col-form-label"></label>
                                    <div class="col-8">
                                        <!--action="/Upload" method = "post"-->
                                        <!--<form method="post" enctype="multipart/form-data">
                                            <label for="avatar">Upload Avatar</label>
                                            <input type="file" id="avatar" name="avatar"
                                                   accept="image/png, image/jpeg"/>
                                        </form>-->
                                    </div>
                                </div>
                                <div class="form-group row" style="width: 875px">
                                    <label for="select" class="col-4 col-form-label"><input name=submit type="submit"
                                                                                            value="Submit"></label>
                                    <div class="col-8">

                                    </div>
                                </div>

                                <div class="form-group row" style="width: 875px">
                                    <label for="email" class="col-4 col-form-label">Email*</label>
                                    <div class="col-8">
                                        <input id="email" name="email" placeholder="Email" class="form-control here"
                                               required="required" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="publicinfo" class="col-4 col-form-label">Public Info</label>
                                    <div class="col-8">
                                        <textarea id="publicinfo" name="information" cols="40" rows="4"  ></textarea>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="newpass" class="col-4 col-form-label">New Password</label>
                                    <div class="col-8">
                                        <input id="newpass" name="password" placeholder="New Password" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="offset-4 col-8">
                                        <button name="submit" type="submit" class="btn btn-primary">Register My
                                            Profile
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<!------------------------------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------------------------------>
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
<!------------------------------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------------------------------>

</body>
</html>
