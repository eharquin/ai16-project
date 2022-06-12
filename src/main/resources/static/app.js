var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/channels/1', function (message) {
            messageBody = JSON.parse(message.body);
            // Instantiate the table with the existing HTML tbody
            // and the row with the template
            var tbody = document.querySelector("#messages");
            var template = document.querySelector('#messagetemplate');

            // Clone the new row and insert it into the table
            var clone = template.content.cloneNode(true);
            var username = clone.querySelector(".username");
            var contents = clone.querySelector(".contents");
            username.textContent = messageBody.username
            contents.textContent = messageBody.content

            tbody.appendChild(clone);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/channels/1/send", {}, JSON.stringify({'content': $("#message").val()}));
    $("#message").val('');
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $(document).ready(function(){
        connect();
    })
    $("#sendMessage").click(function () {
        sendMessage();
    });
    // $("#disconnect").click(function () {
    //     disconnect();
    // });
    // $("#send").click(function () {
    //     sendName();
    // });
});