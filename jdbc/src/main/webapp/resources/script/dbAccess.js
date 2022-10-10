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
        } catch (exception) {
            document.getElementById("resultParagraph").innerHTML = xmlhttp.responseText;
        }

        console.log(e);
    }
}