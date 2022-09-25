<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

Query employees: name: <input type="text" id="queryName"> id: <input type="text" id="queryId">
<br />

endpoint Type:

<select id="endpoint-type">
    <option value="jdbc">jdbc-source</option>
    <option value="jpa">jpa-method</option>
</select>
<br/>
<button onclick="requestEmployees()">search</button>

<script>
    function requestEmployees(){
        const xmlhttp = new XMLHttpRequest();
        var endpointType = document.getElementById("endpoint-type").value;
        const url='/jakarta-jdbc-1.0-SNAPSHOT/api/' + endpointType;
        xmlhttp.open("POST", url);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(JSON.stringify({ "queryName":  document.getElementById("queryName").value, "queryId":  parseInt(document.getElementById("queryId").value) }));

        xmlhttp.send();

        xmlhttp.onreadystatechange = (e) => {
            console.log(Http.responseText)
            console.log(e);
        }
    }
</script>
</body>
</html>