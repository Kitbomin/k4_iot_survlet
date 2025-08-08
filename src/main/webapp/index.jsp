<%--
    jsp 파일: java Server Pages의 약자
        >> 동적인 웹 페이지를 만들기 위해 사용되는 파일 확장자임

--%>



<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>첫번째 서블릿 페이지 </title>
</head>
<body>
<h1><%= "첫 서블릿 페이지에용ㅇ " %>
</h1>
<br/>
<%-- a 링크 클릭 시: hello-servelt의 경로로 이동을 요청함 --%>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>