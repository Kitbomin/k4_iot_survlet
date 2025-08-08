<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25. 8. 8.
  Time: 오전 10:09
  To change this template use File | Settings | File Templates.
--%>

<%--
  === JSP 페이지(HTML에 자바 코드를 삽입할 수 있는 서버용 페이지) ===
  : 동적 페이지 생성
  : .jsp 확장자 사용

  - MVC 패턴에서 View에 대한 역할을 담당 (출력)
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- '@' page 지시자: JSP 파일 속성을 지정함 (UTF-8 로 인코딩 지시) --%>

<html>
<head>
    <title>User Form(user-form.jsp)</title>
</head>
<body>
  <%-- 실제 브라우저 영역 --%>
  <%-- 출력문(자바표현식): <%= 표현식 %>  --%>
  <%  String name = "김보민"; %>
  <p>
    안녕하세요, <%= name %> 님 :)
  </p>

  <%--
      JSP에서 request 사용하기: 서블릿에서 데이터를 보낸 경우
      request.setAttribute("name", "데이터값");
      >> JSP 출력 - <%= request.getAttribute("name")%>

  --%>



  <h2>${user == null ? "New User" : "Edit User"}</h2>
  <form action="${user == null ? 'insert' : 'update'}" method="post">
    <input type="hidden" name="id" value="${user.id}" />

    <input type="text" name="name" value="${user.name}" />
    <br/>

    <input type="text" name="email" value="${user.email}" />
    <br/>

    <input type="text" name="country" value="${user.country}" />
    <br/>

    <input type="submit" value="Submit" />
  </form>

</body>
</html>
