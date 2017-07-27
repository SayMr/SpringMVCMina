<!doctype html>
<html>
<head>
<#include "/head.ftl"/>
<script type="text/javascript" src="${rc.contextPath}/public/js/helloMina/push.js"></script>
</head>
<body> 
  <div class="header">
   <p><span>向客户端推送消息</span></p>
   </div>
  </div>
  <div id="mainContentDiv">
  	<form class="form-inline" id="pushForm">
	  <div class="form-group">
	    <label for="exampleInputName2">Title</label>
	    <input type="text" class="form-control" id="title" name="title" placeholder="Message">
	  </div>
	  <div class="form-group">
	    <label for="exampleInputEmail2">Content</label>
	    <input type="text" class="form-control" id="content" name="content" placeholder="One Message">
	  </div>
	  <button type="submit" class="btn btn-default" onclick="push()">推送</button>
	</form>
  </div>
 </body>
</html>