<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>人民币冠字号码查询信息管理系统V3.0</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/blue/button.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/blue/query.css">
		<script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
		<script>
		function date2str(x,y) {
			 var z = {M:x.getMonth()+1,d:x.getDate(),h:x.getHours(),m:x.getMinutes(),s:x.getSeconds()};
			 y = y.replace(/(M+|d+|h+|m+|s+)/g,function(v) {return ((v.length>1?"0":"")+eval('z.'+v.slice(-1))).slice(-2)});
			 return y.replace(/(y+)/g,function(v) {return x.getFullYear().toString().slice(-v.length)});
		}
		
		//聚焦冠字号文本框
		window.onload = function(){
			document.getElementById("dealNo").focus();
			
		}
		function toUpp(){
			 document.getElementById("dealNo").value = document.getElementById("dealNo").value.toUpperCase(); 
		}
		
		function showInfo(){
	  	 	var info = document.getElementById("errorInfo");
	  	 	var dealNo = (document.getElementById("dealNo").value).replace(/\s+/g,""); 
			document.getElementById("dealNo").value = dealNo;
			info.innerHTML = "<font style='color:#999999; font-size:12px;'>请输入完整的冠字号！冠字号长度为10-12位,可以用‘*’代替冠字号中的字符</font>";
		}

		function searchResult(){
			var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
			var dealNo = (document.getElementById("dealNo").value).replace(/\s+/g,""); 
			document.getElementById("dealNo").value = dealNo;
	  	 	var errInfo = document.getElementById("errorInfo");
	  	 
			if(dealNo == ""){
				errInfo.innerHTML = "<font style='color:red; font-size:12px;'>冠字号码不能为空，请输入冠字号！</font>";
				document.getElementById("dealNo").focus();
				return false;
			}else if ((dealNo.split('*')).length-1 > 2) {
				errInfo.innerHTML = "<font style='color:red; font-size:12px;'>冠字号码不能包含两个以上的‘*’，请重新输入！</font>";
				document.getElementById("dealNo").focus();
				return false;
			}else if(reg.test(dealNo)){
				errInfo.innerHTML = "<font style='color:red; font-size:12px;'>冠字号码不能包含汉字，请重新输入！</font>";
				document.getElementById("dealNo").focus();
				return false;
			} else {
				errInfo.innerHTML = "";
				document.getElementById("search").disabled = true;
				document.getElementById("running").style.display = 'block';
		 	    var login= document.getElementById("login").value;
		 		$.ajax({                                             
		  	 	  type: "POST",                                 
		  	 	  url: "${pageContext.request.contextPath}/GZHQueryServlet?method=queryResult&login="+login,
		  	 	  data: "dealNo="+dealNo,
		  	 	  success: function(data){
	  	 			var resultDiv = document.getElementById("searchResult");
	  	 			var searchCountInfoDiv = document.getElementById("searchCountInfo");
		  	 	    if( data != "notfound" ){
			  	 	  	var resultStr = eval(data);
			  	 		var contentStr = "";
			  	 		var contentCountStr = "";
			  	 		var banknoList = "";
			  	 		var resultStrLength = resultStr.length;
			  	 		var infoListSize = 0;
			  	 		for( var i = 0;i< resultStr.length;i++ ){
			  	 			if(resultStr[i].bankno == undefined && resultStr[i].causeInfo != 0){
			  	 				infoListSize++;
			  	 			}
			  	 			if( resultStr[i].imagepath == undefined ){
			  	 				resultStr[i].imagepath = "-";
			  	 			}
			  	 			if(resultStr[i].fromSource == 1 ){
			  	 				resultStr[i].fromSource = "人行";
			  	 			}
			  	 			if(resultStr[i].fromSource == 2 ){
			  	 				resultStr[i].fromSource = "商行";
			  	 			}
			  	 			if( resultStr[i].trueflag == "1" ){
			  	 				resultStr[i].trueflag = "真";
			  	 			}else{
			  	 				resultStr[i].trueflag = "假";
			  	 			}
			  	 			
			  	 			if( resultStr[i].bankno == undefined ){
			  	 				resultStr[i].bankno = "-";
			  	 			}
			  	 			if( resultStr[i].bankname == undefined ){
			  	 				resultStr[i].bankname = "-";
			  	 			}
			  	 			if( resultStr[i].branchname == undefined ){
			  	 				resultStr[i].branchname = "-";
			  	 			}
			  	 			if( resultStr[i].agencyno == undefined ){
			  	 				resultStr[i].agencyno = "-";
			  	 			}
			  	 			if( resultStr[i].pertype == "00" ){
			  	 				resultStr[i].pertype = "点钞机";
			  	 			}
			  	 			if( resultStr[i].pertype == "01" ){
			  	 				resultStr[i].pertype = "ATM";
			  	 			}
			  	 			if( resultStr[i].pertype == "02" ){
			  	 				resultStr[i].pertype = "清分机";
			  	 			}
			  	 			if( resultStr[i].businesstype == "0" ){
			  	 				resultStr[i].businesstype = "清分";
			  	 			}
			  	 			if( resultStr[i].businesstype == "1" ){
			  	 				resultStr[i].businesstype = "存款";
			  	 			}
			  	 			if( resultStr[i].businesstype == "2" ){
			  	 				resultStr[i].businesstype = "取款";
			  	 			}
			  	 			if( resultStr[i].businesstype == "3" ){
			  	 				resultStr[i].businesstype = "加钞";
			  	 			}
			  	 			if( resultStr[i].businesstype == "4" ){
			  	 				resultStr[i].businesstype = "回收";
			  	 			}
			  	 			if( resultStr[i].businesstype == "5" ){
			  	 				resultStr[i].businesstype = "清点";
			  	 			}
			  	 			if( resultStr[i].businesstype == undefined ){
			  	 				resultStr[i].businesstype = "-";
			  	 			}
			  	 			if( resultStr[i].countNum == undefined ){
			  	 				resultStr[i].countNum = "-";
			  	 			}
			  	 			if( resultStr[i].causeInfo == undefined ){
			  	 				resultStr[i].causeInfo = "-";
			  	 			}
			  	 			if( resultStr[i].causeInfo == 1 ){
			  	 				resultStr[i].causeInfo = "网络连接异常";
			  	 			}
			  	 			if( resultStr[i].causeInfo == 2 ){
			  	 				resultStr[i].causeInfo = "连接超时";
			  	 			}
			  	 			if( resultStr[i].causeInfo == 3 ){
			  	 				resultStr[i].causeInfo = "未查询到相关记录";
			  	 			}
			  	 			
			  	 			if( resultStr[i].montype == "CNY" ){
			  	 				resultStr[i].montype = "人民币";
			  	 			}else{
			  	 				resultStr[i].montype = "其它";
			  	 			}
			  	 			
			  	 			if(resultStr[i].bankno != undefined && resultStr[i].countNum > 0){
				  	 			contentStr= contentStr + "<tr align='middle'>" 
				  	 					+"<td width='180px'><div><img  height='20' src='data:image/png;base64,"+resultStr[i].imagepath+"'></div></td>"
				  	 					+"<td width='80px'>"+resultStr[i].mon+"</td>"
				  	 					+"<td width='40px'>"+resultStr[i].monval+"</td>"
				  	 					+"<td width='40px'>"+resultStr[i].monver+"</td>"
				  	 					+"<td width='80px'>"+resultStr[i].operdate+"</td>"
				  	 					+"<td width='80px'>"+resultStr[i].percode+"</td>"
				  	 					+"<td width='80px'>"+resultStr[i].bankno+"</td>"
				  	 					+"<td width='130px'>"+resultStr[i].bankname+"</td>"
				  	 					+"<td width='80px'>"+resultStr[i].agencyno+"</td>"
				  	 					+"<td width='130px'>"+resultStr[i].branchname+"</td>"
				  	 					+"<td width='100px'>"+resultStr[i].businesstype+"</td>"
				  	 					+"<td width='40px'>"+resultStr[i].fromSource+"</td>"
				  	 					+"</tr>"
			  	 			}
			  	 			if(resultStr[i].countNum == 0){
			  	 				contentCountStr= contentCountStr + "<tr align='middle'>" 
		  	 					+"<td width='230px'>"+resultStr[i].localBankName+"</td>"
		  	 					+"<td width='200px'>"+resultStr[i].countNum+"</td>"
		  	 					+"<td width='200px'>"+resultStr[i].causeInfo+"</td>"
		  	 					+"</tr>"
		  	 					continue;
			  	 			}
		  	 				if(banknoList.indexOf(resultStr[i].localBankName)== -1){
		  	 					banknoList = banknoList + resultStr[i].localBankName;
		  	 					contentCountStr= contentCountStr + "<tr align='middle'>" 
		  	 					+"<td width='230px'>"+resultStr[i].localBankName+"</td>"
		  	 					+"<td width='200px'>"+resultStr[i].countNum+"</td>"
		  	 					+"<td width='200px'>"+resultStr[i].causeInfo+"</td>"
		  	 					+"</tr>"
		  	 				} else {
		  	 					continue;
		  	 				} 
			  	 			
			  	 		}
			  	 		
			  	 		var bannerDiv = document.getElementById("bannerDiv");
			  	 		var form =  document.getElementById("form");
			  	 		
			  	 		if(contentStr == ""){
			  	 			resultDiv.innerHTML = "<font style='color:#0000FF; font-size:25px;'>对不起，您要查找的冠字号码不存在！</font>";
			  	 			searchCountInfoDiv.innerHTML = "<div style='border:1px solid #9db3c5;width:90%;height:300px;overflow-x:hidden;overflow-y:scroll' id='contentDiv2'><table class='t2'  id='contentValue2'>"
								+"<thead>"
		  	 					+"<th>银行名称</th>"
		  	 					+"<th>冠字号合计</th>"
		  	 					+"<th>原因</th>"
		  	 					+"</thead>"
								+contentCountStr
								+"<tr>"
		  	 					+"<td colspan='12' align='center'>"
		  	 					+"</td>"
		  	 					+"</tr>"
								+"<table></div>";
							var contentDiv2 =  document.getElementById("contentDiv2");
							bannerDiv.style.height = "710px";
							contentDiv2.style.height = "200px";
			  	 		} else {
			  	 			resultDiv.innerHTML = "<div style='border:1px solid #9db3c5;width:90%;height:300px;overflow-x:hidden;overflow-y:scroll' id='contentDiv'><table class='t2'  id='contentValue'>"
		  	 					+"<thead>"
		  	 					+"<th>冠字号码图片</th>"
		  	 					+"<th>冠字号码</th>"
		  	 					+"<th>币值</th>"
		  	 					+"<th>版别</th>"
		  	 					+"<th>日期</th>"
		  	 					+"<th>设备编号</th>"
		  	 					+"<th>银行编号</th>"
		  	 					+"<th>银行名称</th>"
		  	 					+"<th>网点编号</th>"
		  	 					+"<th>网点名称</th>"
		  	 					+"<th>业务类型</th>"
		  	 					+"<th>来源</th>"
		  	 					+"</thead>"
		  	 					+contentStr
		  	 					+"<tr>"
		  	 					+"<td colspan='12' align='center'>"
		  	 					+"</td>"
		  	 					+"</tr>"
								+"<table></div>";
			  	 		  var table = document.getElementById("contentValue");//根据table的 id 属性值获得对象    
				  	      var rows = table.getElementsByTagName("tr");//获取table类型的tr元素的列表  
				  	      for(var i = 0; i < rows.length; i++){  
				  	           if(i % 2 == 0){  
				  	               rows[i].style.backgroundColor = "#e8f3fd";//偶数行时背景色为#e8f3fd  
				  	           }  
				  	           else{  
				  	               rows[i].style.backgroundColor = "White";//单数行时背景色为white  
				  	           }  
				  	       }  
				  	      
				  	    searchCountInfoDiv.innerHTML = "<div style='border:1px solid #9db3c5;width:90%;height:300px;overflow-x:hidden;overflow-y:scroll' id='contentDiv2'><table class='t2'  id='contentValue2'>"
							+"<thead>"
	  	 					+"<th>银行名称</th>"
	  	 					+"<th>冠字号码合计</th>"
	  	 					+"<th>原因</th>"
	  	 					+"</thead>"
							+contentCountStr
							+"<tr>"
	  	 					+"<td colspan='12' align='center'>"
	  	 					+"</td>"
	  	 					+"</tr>"
							+"<table></div>";
							var contentDiv2 = document.getElementById("contentDiv2");
							var contentDiv = document.getElementById("contentDiv");
							if(resultStrLength > 400){
								contentDiv.style.height = "500px";
								bannerDiv.style.height = "1000px";
								contentDiv2.style.height = "300px";
							}
							if(resultStrLength < 130){
								contentDiv.style.height = "300px";
								bannerDiv.style.height = "710px";
								contentDiv2.style.height = "200px";
							}
			  	 		}
							form.style.cssText = "padding-top: 50px;"
							resultDiv.style.display = 'block';
							searchCountInfoDiv.style.display = 'block';
							document.getElementById("search").disabled = false;
							document.getElementById("running").style.display = 'none';
							
							var countNumTmp = resultStrLength - infoListSize; 
							errInfo.innerHTML = "<font style='color:#999999; font-size:13px;'>系统帮您查找到</font>"+"<font style='color:#0000FF; font-size:20px;'>"+countNumTmp+"</font>"+"<font style='color:#999999; font-size:13px;'>条相关记录</font>";
						}else{
							resultDiv.innerHTML = "<font style='color:#0000FF; font-size:25px;'>对不起，您要查找的冠字号码不存在！</font>";
							resultDiv.style.display = 'block';
							document.getElementById("search").disabled = false;
							document.getElementById("running").style.display = 'none';
						}
					}
	  	 	 	}); 
			}
		}
		
		
 		$(document).keyup(function(event){
		  if(event.keyCode ==13){
			searchResult();
			document.getElementById("dealNo").blur();
		  }
		});  
		</script>
	</head>
	<body>
	<input type="hidden" value=${login }  id = "login" name="login"/>
			<div style="border: 1px solid graytext; background: url('${pageContext.request.contextPath}/style/blue/images/banner.jpg') repeat-x ; height: 550px;" id="bannerDiv" >
				<div  style="padding-top: 15px;">
					<h1 ><font size="6"  color="#FFFFFF" face="华文楷体">&nbsp;南宁中心支行&nbsp;</font><font size="4"  color="#FFFFFF"  face="华文楷体">人民币冠字号码查询信息管理系统欢迎您</font></h1>
				</div>
				
				<form action="" id="form" name="form" method="post" style="padding-top: 230px;">
					<div  style='width:100%' align = "center">
					<table style="height: 70px; " align="center" cellpadding="0" >
						<tr>
							<td  style="text-overflow:ellipsis;word-break:keep-all; white-space:nowrap; height: 30px;">
								<font size="4"><b>请输入冠字号码：</b></font>&nbsp;
								<input type="text" id="dealNo" name="dealNo" maxlength="12" style="border: 1px solid rgb(115, 155, 192);background:transparent; height:30px;" onblur="showInfo();toUpp();" size="30">&nbsp;
							</td>
							<td width="35px">
								<div id="running" style="display: none;">
									<img src="${pageContext.request.contextPath}/style/blue/images/loading.gif"></img>
								</div>
							</td>
							<td>
								<input type="button" id="search" class="btn26" value="查询" onClick="return searchResult();" />
							</td>
							<td>
								<input type="text" style="display: none" id="searchCount" name="searchCount">
							</td>
						</tr>
						<tr>
							<td>
								<div id="errorInfo" style="width: 280px;height: 40px;float: right;"></div>
							</td>
						</tr>
					</table>
					</div>
				</form>
				<h3></h3>
				<div id="searchResult" style="overflow: auto;width: auto;height: auto;display: none; margin-left: 5px;margin-right: 5px" align="center" >
				</div>
				<div id="searchCountInfo" style="overflow: auto;width: auto;height: auto;display: none; margin-left: 5px;margin-right: 5px" align="center" >
				</div>
			</div>
			<br />
			<div align="center">
				<hr style="border: 1px dashed;border-bottom: 1px solid graytext;">
				<span style="color:#999999; font-family:'宋体'; font-size: 13;">系统提供商:深圳宝嘉电子设备有限公司 (V3.0)
				<br /> Copyright (c) www.poka.com.cn All rigths reserved. 
				</span>
			</div>
	
	</body>
</html>
