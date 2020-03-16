var stompClient = null;

connect()

$("#greetings").html("");

function connect() {
  var socket = new SockJS('http://localhost:8095/socket/message-stream');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/user/topic/message', function (message) {
      showMessage(JSON.parse(message.body).message);
    });
  });
}

function showMessage(message) {
  $("#greetings").append("<tr><td>" + message + "</td></tr>");
}