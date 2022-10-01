<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Jakarta EE Database access</title>
</head>
<body>
<br/>
<a href="hello-servlet">go to other servlet</a>
<br />
<br/>
<div>
Query employees: name: <input type="text" id="queryName"> id: <input type="text" id="queryId">
</div>
    <br />
<div>
Access Type:

<select id="endpoint-type">
    <option value="jdbc">jdbc-source</option>
    <option value="jpa">jpa-method</option>
</select>
</div>
<br/>
<button onclick="requestEmployees()">search</button>
<div>
    <h3>Response Result:</h3><br/>
    <p style="border: 2px;" id="resultParagraph">

    </p>
</div>

<script>
    function requestEmployees(){
        const xmlhttp = new XMLHttpRequest();
        var endpointType = document.getElementById("endpoint-type").value;
        const url='/jakarta-jdbc-1.0-SNAPSHOT/api/' + endpointType;
        xmlhttp.open("POST", url);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(JSON.stringify({ "queryName":  document.getElementById("queryName").value, "queryId":  parseInt(document.getElementById("queryId").value) }));

        xmlhttp.onreadystatechange = (e) => {
            console.log("readyStateChanged");

            document.getElementById("resultParagraph").innerHTML = xmlhttp.responseText;
            console.log(xmlhttp.responseText)
            console.log(e);
        }
    }
</script>
</body>
</html>