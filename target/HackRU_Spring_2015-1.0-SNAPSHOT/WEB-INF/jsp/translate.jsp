<%-- 
    Document   : translate
    Created on : Apr 27, 2015, 9:20:46 PM
    Author     : rayedchan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Google Translate API</title>
    </head>
    <body>
    <div align="center">
        <h2>Translator</h2>
        <form:form action="translate/process" method="POST" commandName="userTranslateForm">
            <table>
                <tr>
                    <td><form:label path="content">Content</form:label></td>
                    <td><form:input path="content" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form:form>
    </div>
       
    </body>
</html>
