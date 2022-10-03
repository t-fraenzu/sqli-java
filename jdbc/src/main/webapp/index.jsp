<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Jakarta EE Database access</title>
</head>
<body>
<br/>
<a href="hello-servlet">go to other servlet</a>
<br/>
<br/>
<div>
    Query employees: name: <input type="text" id="queryName"> id: <input type="text" id="queryId">
</div>
<br/>
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
    <div>
        <div>[JDBC] Preparedstatement result</div>
        <table style="border: 1px;" id="responseTable-prepState">

        </table>
        <div>[JDBC] Plain sql result</div>
        <table style="border: 1px;" id="responseTable-statementResult">

        </table>
        <div>[JPA] criteria query result</div>
        <table style="border: 1px;" id="responseTable-criteriaQuery">

        </table>
        <div>[JPA] untyped query result</div>
        <table style="border: 1px;" id="responseTable-untypedQuery">

        </table>
        <div>
            <p style="border: 2px;" id="resultParagraph">

            </p>
        </div>
    </div>
</div>

<script>
    function requestEmployees() {
        const xmlhttp = new XMLHttpRequest();
        var endpointType = document.getElementById("endpoint-type").value;
        const url = '/jakarta-jdbc-1.0-SNAPSHOT/api/' + endpointType;
        xmlhttp.open("POST", url);
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(JSON.stringify({
            "queryName": document.getElementById("queryName").value,
            "queryId": parseInt(document.getElementById("queryId").value)
        }));

        xmlhttp.onreadystatechange = (e) => {
            console.log("readyStateChanged");

            function cleanElements() {
                document.getElementById("resultParagraph").innerHTML = "";
                document.getElementById("responseTable-prepState").innerHTML = "";
                document.getElementById("responseTable-criteriaQuery").innerHTML = "";
                document.getElementById("responseTable-untypedQuery").innerHTML = "";
                document.getElementById("responseTable-statementResult").innerHTML = "";
            }

            cleanElements();
            try {
                var searchResult = JSON.parse(xmlhttp.responseText);

                function addResultToTable(searchResult, tablename) {
                    var text = "<tr><th style='width: 35px;'>Id</th><th style='width: 35px;'>Name</th><th style='width: 35px;'>Salary</th><th style='width: 35px;'>workFunction</th></tr>";
                    for (let x in searchResult) {
                        text += "<tr><td>" + searchResult[x].eid + "</td>";
                        text += "<td>" + searchResult[x].ename + "</td>";
                        text += "<td>" + searchResult[x].salary + "</td>";
                        text += "<td>" + searchResult[x].workfunction + "</td>";
                        text += "</tr>"
                    }

                    document.getElementById(tablename).innerHTML = text;
                }

                if (searchResult != null) {
                    if (searchResult.prepStatementResult != null) {
                        addResultToTable(searchResult.prepStatementResult, "responseTable-prepState")
                    } else {
                        document.getElementById("responseTable-prepState").innerHTML = "";
                    }

                    if (searchResult.criteriaQuery != null) {
                        addResultToTable(searchResult.criteriaQuery, "responseTable-criteriaQuery")
                    } else {
                        document.getElementById("responseTable-criteriaQuery").innerHTML = "";
                    }

                    if (searchResult.untypedQuery != null) {
                        addResultToTable(searchResult.untypedQuery, "responseTable-untypedQuery")
                    } else {
                        document.getElementById("responseTable-untypedQuery").innerHTML = "";
                    }

                    if (searchResult.statementResult != null) {
                        addResultToTable(searchResult.statementResult, "responseTable-statementResult")
                    } else {
                        document.getElementById("responseTable-statementResult").innerHTML = "";
                    }
                } else {
                    document.getElementById("resultParagraph").innerHTML = xmlhttp.responseText;
                }
            } catch(exception) {
                document.getElementById("resultParagraph").innerHTML = xmlhttp.responseText;
            }

            console.log(e);
        }
    }
</script>
</body>
</html>