<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>AJAX</h1>
	
	<jsp:include page="../include/header.jsp"/>
	
	<pre>
		Asynchronous Javascript And XML
		서버로부터 데이터를 응답받아서 전체 페이지를 새롭게 렌더링하지 않고,
		일부만 새롭게 갱신할 수 있는 기법이다.
		
		참고로 기존에 우리가 a태그 / location.href / form태그를 이용한 요청 == 동기식 요청
		=? 응답 페이지 전체가 돌아와야만 화면을 새롭게 만들어 낼 수 있음
		
		* 동기식 / 비동기식 요청 차이
		- 동기식 : 요청 처리 후 응답 HTML데이터를 받아 화면에 렌더링 한 뒤에만 작업이 가능 
				 만약에 서버에서 응답데이터를 돌려주는 시간이 지연되면 무작정 기다림
				 전체 화면이 새로고침됨 -> 페이지가 깜빡
				 
		- 비동기식 : 현재 페이지는 그대로 유지하면서 중간중간 추가적인 요청을 보낼 수 있음
				  응답이 돌아온다고 해서 다른 페이지로 넘어가지 않음(페이지가 유지)  
				  요청을 보내놓고 응답이 올떄까지 다른 작업을 할 수 있음
		 사용예시 ) 아이디 중복체크 기능, 검색어 자동완성, 댓글
		 
		 ** 추가로 요즘엔 모든 요청 및 응답처리를 AJAX로 구현 **
				   
	</pre>
	
	
	<pre>
		
		 * 실전 압축 jQuery 사용법
		
		1. 요소선택
		document.querySelector('CSS 선택자');
		2. 이벤트
		.addEventListner('click',()=> {
			이벤트 발생 시 수행할 코드
		});
		3. Content값 변경
		.innerHTML = '안에 넣을 값'
		==
		$('CSS 선택자');
		4. style 변경
		.style.세부 스타일 속성 = '';
		.css('스타일속성','바꿀스타일')
	</pre>
	
	<h3 id="h3">하하호호 나에게 접근해라</h3>
	
	<script>
		// 현재 객체가 모두 로딩이 끝나고 난 후 수행하겠다는 뜻
		window.onload = function(){
			/*
			const h3El = document.querySelector('#h3');
			h3El.addEventListener('click',()=>{
				alert('호호호')
			})
			h3El.innerHTML = '<h1>으쯔라구</h1>';
			h3El.style.backgroundColor = 'red';
			*/
			
			const h3El = $('#h3');
			h3El.click(()=>{
				alert('호호호')
			});
			h3El.html('<h1>으쯔라구</h1>')
			h3El.css('background','red')
			console.log(h3El);
		}
		
	</script>
	 
	
	
	<pre>
	
		* jQery를 사용한 AJAX 통신
		
		[표현법]
		$.ajax({
			속성명 : 값,
			속성명 : 값,
			속성명 : 값
			...
		
		});
		
		* 주요 속성
		
		- url : 요청할 URL(필수작성) => form태그로 따지면 action속성
		
		- type : 요청 전송방식(GET/POST...PUT,DELETE)
					GET방식 : 조회요청(SELECT)
					POST방식 : 데이터 생성 요청(INSERT)
					PUT방식 : 데이터 갱신 요청(UPDATE)
					DELET방식 : 데이터 삭제 요청(DELETE)
		- success : AJAX 통신 성공 시 실행할 함수를 정의
		- data : 요청 시 전달값({키 : 밸류}) => form 태그의 input요소 입력값
	</pre>
	
	
	<hr>
	
	<h2>jQuery방식으로 AJAX로 요청을 보내고 응답을 받아서 화면상에 출력</h2>
	
	<h3>1. 버튼을 클릭 했을 떄 GET 방식으로 서버에 데이터를 전송하고 응답을 받아보기</h3>
	
	<div class="form-group">
		<div class="form-control">
			입력 : <input type="text" id="ajax-input">
		</div>
	
		<div class="form-control">
			<button class="btn btn-sm btn-primary" id="ajax-btn">AJAX로 요청보내기</button>
		</div>
	
	</div>	
	응답 : <label>현재 응답 없음</label>
	
	<!--  
		우리의 계획 :
		사용자가 입력이라고 써있는 input요소에 값을 입력해서
		AJAX로 요청보내기 버튼을 누르면
		
		요청을 받아 처리하는 RequestHandler가 값을 받아서 응답을 해주고
		라벨 요소의 Content영역에 응답받은 데이터를 출력할 것
	-->
	
	<script>
		$('#ajax-btn').click(()=>{  // 버튼을 눌렀을 때
			
			const inputValue = $('#ajax-input').val(); // input요소 값을 inputValue에 담음
			
			$.ajax({
				
				url : `bjax?input=\${inputValue}`, // 요청할 url주소
				type : 'get',					   // get방식	
				success : function(result){        // 다시 반환받을 때 수행할 함수, 반환값을 result변수에 담음
					
					//console.log(result);
					//$('#result').text(result); 
					// ==
					document.querySelector('#result').innerText = result;
				}
			});
		});
	
	</script>
	
	
	<hr>
	<h3>VO단일 객체를 조회해서 출력해보기</h3>
	
	<div>
		게시글 제목 : <p id="title"></p>
		게시글 작성자 :<p id="writer"></p>
		게시글 내용 : <p id="content"></p>
		게시글 작성일 : <p id="date"></p>
		
		<hr>
		
		<img id="board-img"/>
		<hr>
		<div id="reply-area"></div>
			
	
	</div>
	
	게시글 번호 : <input type="text" id="replyNo">
	<button onclick="selectReply()">게시글 보여주세용</button>
	
	
	<script>
	
         function selectReply(){
            
            const replyNo = document.getElementById('replyNo').value;
            $.ajax({
            	url : `study?replyNo=\${replyNo}`,
            	type : 'get',
            	success : result =>{
            		console.log(result);
            		
            		// 응답받은 데이터를 화면에 출력
            		$('#title').text(result.boardTitle); 
            		// == document.querySelector('#title').innerText = result.boardTitle;
            		$('#writer').text(result.boardWriter);
            		$('#content').text(result.boardContent);
            		$('#date').text(result.boardDate);
            		
            		const imgEl = document.querySelector('#board-img');
            		if(result.changeName){
            			imgEl.src = result.changeName;
            		} else{
            			imgEl.src = '';
            		}
            		
            		const reply = result.replyList;
					
            		const elements = reply.map(e => {
            			return(
            			`<div>
            				<label>댓글 작성자 : \${e.replyWriter}</label>
            				<label>댓글 내용 : \${e.replyContent}</label>
            				<label>작성글 : \${e.createDate}</label>
            			</div>`
            			)
            		}).join('');
            		 document.querySelector('#reply-area').innerHTML = elements;
            	}
            	
            })
           
         }
   </script>
	
</body>
</html>