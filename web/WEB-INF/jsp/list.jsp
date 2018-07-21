<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<hr/>
    <section>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Имя:</th>
                <th>Email:</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${resumes}" var="resume">
                <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
            <tr>
                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <%--<td>${resume.getContacts(ContactType.EMAIL)}</td>--%>
                <td><%=ContactType.EMAIL.toHtml(resume.getContacts(ContactType.EMAIL))%></td>
                <td><a href="resume?uuid=${resume.uuid}&action=edit">Edit</a></td>
                <td><a href="resume?uuid=${resume.uuid}&action=delete">Delete</a></td>
            </tr>
            </c:forEach>
        </table>
        <p>
            <a href="resume?action=add">Добавить</a>
        </p>
    </section>
<hr/>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
