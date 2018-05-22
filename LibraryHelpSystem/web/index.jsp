<%-- 
    Document   : index
    Created on : 16.03.2016, 23:28:52
    Author     : Kondrat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/_styles.css">
        <link rel="stylesheet" type="text/css" href="styles/index.css">
        <title>Library help system</title>
    </head>
    <body>
        <div id="wrapper">
            <header id="header">
                <img width="250" height="100" src="images/logotip.png" alt="logo"/>
            </header>
            <main id="main1">
                <form method="POST" action="pages/auth.jsp?type=0">
                    <table class="input_panel" id="login">
                        <caption><h2>Вход</h2></caption>
                        <tbody>
                            <tr>
                                <td class="rightalign">
                                    Учетная запись:
                                </td>
                                <td>
                                    <input type="text" name="login" required pattern="^[a-zA-Z0-9]{4,20}" placeholder="Имя учетной записи">
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Пароль:
                                </td>
                                <td>
                                    <input type="password" name="password" required pattern="^[a-zA-Z0-9]{4,20}" placeholder="Пароль">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="button" id="switch1" value="Регистрация">
                                </td>
                                <td style="float: right">
                                    <input type="submit" name="login_button" value="Войти">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <form method="POST" action="pages/auth.jsp?type=1">
                    <table class="input_panel" id="register" style="display: none">
                        <caption><h2>Регистрация</h2></caption>
                        <tbody>
                            <tr>
                                <td class="rightalign">
                                    Учетная запись:
                                </td>
                                <td>
                                    <input type="text" name="login" autocomplete="off" required pattern="^[a-zA-Z0-9]{4,20}" placeholder="Имя учетной записи">
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Пароль:
                                </td>
                                <td>
                                    <input type="password" name="password" autocomplete="off" required pattern="^[a-zA-Z0-9]{4,20}" placeholder="Пароль">
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Имя:
                                </td>
                                <td>
                                    <input type="text" name="firstname" autocomplete="off" required placeholder="Ваше имя">
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Фамилия:
                                </td>
                                <td>
                                    <input type="text" name="lastname" autocomplete="off" required placeholder="Ваша фамилия">
                                </td>
                            </tr>
                            <tr>
                                <td class="rightalign">
                                    Отчество:
                                </td>
                                <td>
                                    <input type="text" name="surname" autocomplete="off" required placeholder="Ваше отчество">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="button" id="switch2" value="Вход">
                                </td>
                                <td style="float: right">
                                    <input type="submit" name="login_button" value="Зарегистрироваться">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <script>
                    switch1.onclick = function () {
                        login.style.display = 'none';
                        register.style.display = 'table';
                    }

                    switch2.onclick = function () {
                        register.style.display = 'none';
                        login.style.display = 'table';
                    }
                </script>
            </main>
        </div>
    </body>
</html>
