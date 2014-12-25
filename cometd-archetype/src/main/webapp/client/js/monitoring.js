var _cometd = null;
var _connected = false;
var _subscription = null;
var _channel = "";
var _messagediv = "";
var _processMethod = null;
var _channelDesc = null;

function conntect(remoteUrl, channel, channelDesc, messagediv, securityData, processMethod) {
	$.cometd.disconnect();
	_cometd = $.cometd;
	_channel = channel;
	_channelDesc = channelDesc;
	_messagediv = messagediv;
	_processMethod = processMethod;
	_cometd.addListener('/meta/connect', metaConnect);
	_cometd.addListener('/meta/handshake', metaHandshake);
	_cometd.addListener('/meta/subscribe', metaSubscribe);

	showMessage("<font style=\"color:#E56700\">正在连接服务器...</font>");
	// init 同时执行了 cometd.configer 和 conetd.handshake 不用再执行cometd.handshake
	_cometd.configure({
		url : remoteUrl,
		logLevel : 'debug'
	});
	_cometd.handshake(securityData);
}

function disconnect(processMethod){
	 $.cometd.disconnect(processMethod);
}

// 连接事件
function metaConnect(message) {
	_connected = message.successful === true;
	if (_connected) {
		showMessage("<font style=\"color:green\">服务器连接正常</font>");
	} else {
		showMessage("<font style=\"color:#FF0000\">服务器连接失败</font>");
	}
}

// 握手事件
function metaHandshake(message) {
	if (message.successful) {
		_subscription = _cometd.subscribe(_channel, receive);
	} else {
		showMessage("<font style=\"color:#FF0000\">与服务器连接失败</font>");
	}
}

// 订阅事件
function metaSubscribe(message) {
	if (message.successful) {
		showMessage("<font style=\"color:#E56700\">开始接收" + _channelDesc + "信息</font>");
	} else {
		showMessage("<font style=\"color:#FF0000\">接收" + _channelDesc + "信息失败</font>");
	}
}

// 接受订阅频道消息
function receive(message) {
	_processMethod(message);
}

// 显示连接信息
function showMessage(message) {
	$("#" + _messagediv).html(message);
}
