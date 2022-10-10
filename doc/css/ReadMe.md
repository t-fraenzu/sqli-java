Prevent XSS, CLRF

Included in the JSF framework by default

Display user input automatically escaped on server side:

    <h2>Received Text:</h2> #{helloBean.name} <br />
    <h2>Bad implementation for text: </h2> <br />
    <h:outputText value="#{helloBean.name}" escape="false"></h:outputText>