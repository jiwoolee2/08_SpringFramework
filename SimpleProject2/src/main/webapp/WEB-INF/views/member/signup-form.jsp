<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <style> 
        .content { 
            background-color:rgb(247, 245, 245);
            width:80%; 
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }
    </style>
</head>
<body>
    
	

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="sign-up" method="post">
                <div class="form-group">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" placeholder="Please Enter ID" name="memberId" required> <br>
					<!-- name속성은 DTO의 필드명과 동일하게 하는 것이 좋음
						왜?????????????????????
						-> 실수를 덜함. 생산성 향상 
					 -->
					
					
                    <label for="userPwd">* Password : </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="Please Enter Password" name="memberPw" required> <br>

                    <label for="checkPwd">* Password Check : </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" placeholder="Please Enter Name" name="memberName" required> <br>

                    <label for="userName">* Residence :</label>
                    <input type="text" class="form-control" id="residence" placeholder="Please Enter Residence" name="residence" required> <br>
					
                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="userEmail" placeholder="Please Enter Email" name="memberEmail"> <br>

                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary">회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
            
            
            
            
        
<%--             <c:if test="${msg != null }">
	            <script>
			
					alert(`${msg}`);
			
		       </script>
           		<c:remove var="msg" scope="mv"/>
            </c:if> --%>
            
        </div>
        <br><br>

    </div>

 

</body>
</html>