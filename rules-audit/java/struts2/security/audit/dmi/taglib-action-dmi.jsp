<!--
Copied from repo: https://github.com/apache/struts-examples.git
file: wildcard-regex/src/main/webapp/WEB-INF/form/Start.jsp
rev: heads/master-0-g371e480
-->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <title>Start</title>
</head>

<body>

<!-- ruleid: taglib-action-dmi -->
<s:form action="Store!store" namespace="/store">
  User name: <s:textfield name="userName"/>
  <s:submit/>
</s:form>

</body>
</html>
