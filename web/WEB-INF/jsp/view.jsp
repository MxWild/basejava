<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<hr/>
<section>

    <h2>${resume.fullName} <a href="resume?uuid=${resume.uuid}&action=edit">Edit</a> </h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry" type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <hr/>
    <table cellpadding="2">
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry" type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>

            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>

            <tr>
                <td><h3><a name="type.name">${type.title}</a></h3></td>
            </tr>
            <c:choose>
                <c:when test="${type == 'OBJECTIVE'}">
                    <tr>
                        <td>
                            <%=((TextSection) section).getDescription()%>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type == 'PERSONAL'}">
                    <tr>
                        <td>
                            <%=((TextSection) section).getDescription()%>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type == 'QUALIFICATIONS' || type == 'ACHIEVEMENT'}">
                    <td>
                        <ul>
                            <c:forEach var="item" items="<%=((ListSection) section).getList()%>">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </c:when>
                <c:when test="${type == 'EXPERIENCE' || type == 'EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getOrganizations()%>">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${empty org.homePage.url}">
                                        <h4>${org.homePage.url}</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <h4><a href="${org.homePage.url}">${org.homePage.name}</a></h4>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <c:forEach var="position" items="${org.positions}">
                            <jsp:useBean id="position" type="com.urise.webapp.model.Organization.Position"/>
                            <tr>
                                <td><%=HtmlUtil.formatDates(position)%></td>
                                <td><strong>${position.title}</strong><br/> ${position.description}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>

            <%--<strong><%=sectionEntry.getKey().getTitle()%></strong><br/>--%>
            <%--<%=sectionEntry.getValue()%><br/>--%>
        </c:forEach>
    </table>

    <button onclick="window.history.back()">OK</button>

</section>
<hr/>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
