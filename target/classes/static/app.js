
connect()

$("#greetings").html("");

function connect() {

  socket = new SockJS('http://localhost:8095/socket/message-stream');

  socket.onheartbeat = function() {
    console.log("heartbeat");
  };

  stompClient = Stomp.over(self.socket);
  stompClient.connect({}, function(frame) {
    console.log("Connected: " + frame);
    stompClient.subscribe('/user/topic/message', function (message) {
      showMessage(JSON.parse(message.body).message);
    });
  });

  // here is important thing. We must get current onclose function for call it later.
  var stompClientOnclose = self.stompClient.ws.onclose;
  stompClient.ws.onclose = function() {
    console.log("Websocket connection closed and handled from our app.");
    stompClientOnclose();
    connect()
  };
}

function showMessage(message) {
  $("#greetings").append("<tr><td>" + message + "</td></tr>");
}