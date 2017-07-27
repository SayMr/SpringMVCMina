/**
 * 消息推送脚本
 */

/**
 * 将消息推送到客户端
 */
function push() {
	var url = CONTEXT_PATH + "/helloMina/push";
	var params = $("#pushForm").serialize();
	$.post(url, params, function(data) {
		alert(data);
	});
}