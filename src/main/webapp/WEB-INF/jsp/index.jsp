<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored ="false" %>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
</head>
<body>
    <table>
        <tr>
            <td id="id1"></td>
        </tr>
    </table>
</body>
<script type="text/javascript">
	    var jsOne=${jsOne};
	    var requestTime=jsOne.requestTime;
	    var sourceip=jsOne.sourceip;
	    var tagId=jsOne.tagId;
	    var targetIp=returnCitySN["cip"];
	    //tagId不传参时，无法调用后台写入记事本中
/* 	    if(typeof(sourceip)=="undefined"){
	    	sourceip=null;
	    }
	    if(typeof(tagId)=="undefined"){
	    	tagId=0;
	    } */
	    document.getElementById("id1").innerHTML="{\"sourceip\":\""+sourceip+"\",\"tagId\":"+tagId+",\"targetIp\":\""+targetIp+"\",\"requestTime\":\""+requestTime+"\"}";
	    var xmlHttp=new XMLHttpRequest();
		xmlHttp.onreadystatechange=function(){
			if(xmlHttp.readyState==4&&xmlHttp.status==200){
				var jn=xmlHttp.responseText;
			    var json=eval("("+jn+")");
		    }    
		};
		xmlHttp.open("GET","writer.action?requestTime="+requestTime+"&sourceip="+sourceip
				+"&tagId="+tagId+"&targetIp="+targetIp,false);
		xmlHttp.send();
	    
   
</script>
</html>