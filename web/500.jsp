<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>500 - Server Error</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <style>
            .error-container { text-align: center; margin-top: 100px; font-family: Arial, sans-serif; }
            h1 { font-size: 5em; color: #ff4d4d; }
            p { font-size: 1.5em; color: #333; }
            a { text-decoration: none; color: #007bff; font-weight: bold; }
        </style>
    </head>
    <body>
        <div class="error-container">
            <h1>500</h1>
            <p>Sorry, our server encountered an internal error. Please try again later.</p>
            <a href="${pageContext.request.contextPath}/MainController">Return to Home</a>
        </div>
    </body>
</html>
