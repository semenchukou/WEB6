<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat</title>
    <script>
        var websocket;
        var lang;

        function init() {
            websocket = new WebSocket('ws://localhost:8080/lab7_war_exploded/chat');
            websocket.onopen = function (event) {
                websocketOpen(event);
            };
            websocket.onmessage = function (event) {
                websocketMessage(event);
            };
            websocket.onerror = function (event) {
                websocketError(event);
            };
        }

        function websocketOpen(event) {
            console.log("webSocketOpen invoked");
            websocket.send("[web_socket_message]:=:" + document.getElementById('senderId').value);
        }

        function websocketMessage(event) {
            console.log("websocketMessage invoked");
            document.getElementById('chatWindow').value += '\n' + event.data;
        }

        function websocketError(event) {
            console.log("websocketError invoked");
        }

        function sendMessage() {
            var msg = document.getElementById('chatInput');
            if (msg.value === "") return;
            websocket.send(msg.value);
            if (lang === 'ru') {
                document.getElementById('chatWindow').value += '\n' + 'Я: ' + msg.value;
            } else {
                document.getElementById('chatWindow').value += '\n' + 'I: ' + msg.value;
            }

            msg.value = "";
        }

        function closeConnection() {
            websocket.close();
        }

        window.addEventListener("load", init);
        window.addEventListener("unload", closeConnection);
    </script>
</head>
<body>
<p style="color: red;">${errorString}</p>

<input type="hidden" id="senderId" value="${senderId}">
<div>
    <c:if test="${sessionScope['loginedUser'].getRole() == User.Role.ADMIN}">
        <p>
            To send message write [receiver name]:=:[message]
        </p>
    </c:if>
    <textArea id="chatWindow" rows="10" style="width: 44em;margin: 15px" readonly></textArea>
</div>
<div>
    <input type="text" id="chatInput" style="width: 40em;margin: 15px"/>
    <input id="sendBtn" type="button" class="btn btn-primary" value="Send"
           onclick="sendMessage()"/>
</div>

<a href="${pageContext.request.contextPath}/?command=home"><fmt:message key="bt.home"/></a>

<script>lang = "${userLocale.language}"</script>

</body>
</html>