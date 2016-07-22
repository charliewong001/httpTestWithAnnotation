<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">

var xmlHttp;
function createXMLHttpRequest(){
    //Mozilla 浏览器（将XMLHttpRequest对象作为本地浏览器对象来创建）
    if(window.XMLHttpRequest){ //Mozilla 浏览器
        xmlHttp = new XMLHttpRequest();
    }else if(window.ActiveXObject) { //IE浏览器
    //IE浏览器（将XMLHttpRequest对象作为ActiveX对象来创建）
        try{
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }catch(e){
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }catch(e){}
        }
    }
    if(xmlHttp == null){
        alert("不能创建XMLHttpRequest对象");
        return false;
    }
}
//用于发出异步请求的方法
function sendAsynchronRequest(url,parameter,callback){
    createXMLHttpRequest();
    if(parameter == null){
        //设置一个事件处理器，当XMLHttp状态发生变化，就会出发该事件处理器，由他调用
        //callback指定的javascript函数
        xmlHttp.onreadystatechange = callback;
        //设置对拂去其调用的参数（提交的方式，请求的的url，请求的类型（异步请求））
        xmlHttp.open("GET",url,true);//true表示发出一个异步的请求。
        xmlHttp.send(null);
    }else{
        xmlHttp.onreadystatechange = callback;
        xmlHttp.open("POST",url,true);
        xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
        xmlHttp.send(parameter);
    }
}
//以上代码是通用的方法，接下来是调用以上的方法
function loadPros(url,params){
    // 调用异步请求方法
    sendAsynchronRequest(url,null,loadCallBack);
}
// 指定回调方法
function loadCallBack(){
  try
 {
    if (xmlHttp.readyState == 4) {
        if (xmlHttp.status == 200) {
            if(xmlHttp.responseText != null && xmlHttp.responseText != ""){
               var result = document.getElementById('result');
               var content = result.innerHTML;
               var append = xmlHttp.responseText;
               var server = '';
               if(!!append){
            	   if(append.indexOf('192.168.1.221') != -1){
            		   server = '221';
            	   }else if(append.indexOf('192.168.1.222') != -1){
            		   server = '222';
            	   }else if(append.indexOf('192.168.1.223') != -1){
            		   server = '223';
            	   }
               }
               var p = document.createElement('div');
               switch (server){
               case '221':
            	   p.style.backgroundColor = 'red';
            	   break;
               case '222':
            	   p.style.backgroundColor = 'yellow';
            	   break;
               case '223':
            	   p.style.backgroundColor = 'green';
            	   break;
               default:break;
               }
               p.innerHTML = append;
               var hr = document.createElement('hr');
               result.appendChild(p).appendChild(hr);
           }
        }
     }
     if (xmlHttp.readyState == 1)
     {
        //alert("正在加载连接对象......");
     }
     if (xmlHttp.readyState == 2)
     {
        //alert("连接对象加载完毕。");
     }
     if (xmlHttp.readyState == 3)
     {
        //alert("数据获取中......");
     }
  }
  catch (e)
  {
      //alert(e);
  }
}

    function callDemoService(){
    	var date = new Date().getTime();
    	var url = '${pageContext.request.contextPath}/consumer/demoService?id='+date;
    	loadPros(url,null);
    }
    function callDemoService02(){
        var date = new Date().getTime();
        var url = '${pageContext.request.contextPath}/consumer/demoService02?id='+date;
        loadPros(url,null);
    }
    function callDemoService03(){
        var date = new Date().getTime();
        var url = '${pageContext.request.contextPath}/consumer/demoService03?id='+date;
        loadPros(url,null);
    }
    function callDemoService04(){
        var date = new Date().getTime();
        var url = '${pageContext.request.contextPath}/consumer/demoService04?id='+date;
        loadPros(url,null);
    }
    function clearContent(){
    	var result = document.getElementById('result');
    	result.innerHTML = '';
    }
</script>
</head>
<body>
    </br>
    <div style="margin-bottom:10px;margin-top:10px;">
	    <button id="callDemoService" onclick="callDemoService();">callDemoService</button>
	    <button id="callDemoService02" onclick="callDemoService02();">callDemoService02</button>
	    <button id="callDemoService03" onclick="callDemoService03();">callDemoService03</button>
	    <!-- button id="callDemoService04" onclick="callDemoService04();">callDemoService04 1000 times</button-->
    </div>
    <button id="clear" onclick="clearContent();">clearContent</button>
    </br>
    <div id="result" style="background-color:grey;"></div>
</body>
</html>