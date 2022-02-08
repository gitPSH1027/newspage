<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>본인확인</title>
<script type="text/javascript">
	$(function () {
		$('#passChk').on('click',function(){
			var route = '${route}'; //회원정보 or 스크랩정보 경로 접근여부
			var passwd = $('#passwd').val();
			
			$.ajax({
				url : '${path}/pwchk/',
				data : {passwd},
				type : 'post',
				dataType : 'json',
				success : function(data){
					//console.log(data.msg);
					//console.log(data.check);
					var check = data.check;
					if(!check) //본인확인실패
					{
						alert(data.msg);
						$('#passwd').val('');
						$('#passwd').focus();
						return;
					}
					//성공
					alert(data.msg);
					if(route == '0')//회원정보이동
					{
						$('#certForm').attr('action','${path}/member/detail').submit();
						return;
					}
					if(route == '1') //스크랩정보이동
					{
						$('#certForm').attr('action','${path}/scrap/list').submit();
						return;
					}
				},
				error : function(){
					"Error";	
				}
			});
		});
	})
</script>
</head>
<body>
	<form class="certForm" id="certForm" action="">
		<h2>본인확인</h2>
		<div class="textForm" >
			<input type="password" class="id" name="passwd" id="passwd" placeholder="비밀번호확인">
			<button type="button" id="passChk" >확인</button>
		</div>
	</form>
</body>
</html>