<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#wrap{
		width : 1000px;
		min-height : 400px;
		margin : auto;
		height : auto;
		border : 1px solid rgba(0,0,0,0.6);
		border-radius : 16px;
		background-color : rgba(16,16,16,0.8);
		padding : 30px;
		margin-top : 30px;
	}
	
	#wrap > div{
		width : 100%
	}
	#content{
		width : 90%;
		margin : auto;
		height : auto;
		min-height : 300px;
	}
	button{
		width:100%
	}
	
	.message{
		display : inline-block;
		width : 250px;
		height 300px;
		text-align : center;
	}
	
	
</style>



</head>
<body>
	<jsp:include page="../include/header.jsp"/>
	
	
	<div id="wrap">
	
		<div id="content">
		
		</div>
		<div>
			<button onclick="getMessage();" class="btn btn-outline=success">더보기</button>
		</div>
		

	
	</div>
	
	
	<script>
		$(function(){
			getMessage();
		});
		let pageNo=1;
		
		function getMessage(){
			$.ajax({
				url : `message?pageNo=\${pageNo}`,
				type : 'get',
				success : result =>{
					// console.log(result);
					const messages = result.body;
					/* message안의 요소를 아래내용으로 하나씩 꺼내서 바꾼다음 join으로
					모든 요소들을 더해서 그 문자열을 outputStr에 담음.
					*/
					const outputStr = messages.map(e=>
						`<div class="message">
							<h3 class="category">\${e.DST_SE_NM}</h3> 
							<p class="content">\${e.MSG_CN}</p> 
							<h6 class="region">\${e.RCPTN_RGN_NM}</h6> 
						</div>`).join('');
					$('#content').append(outputStr);
					pageNo++;
				}
				
			});
			}
	</script>
	
	
	
	
	
	
	
	<jsp:include page="../include/footer.jsp"/>
</body>
</html>