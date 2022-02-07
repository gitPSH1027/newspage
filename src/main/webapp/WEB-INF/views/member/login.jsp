<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script type="text/javascript">
	$(function() {
		$('#join').on('click', function() {
			location = '${path }/member/join';
		});

		//로그인유효성체크
		$('#loginChk').on('click', function() {
			var email = $('#email').val();
			var passwd = $('#passwd').val();

			if (email == '') {
				alert('이메일을 입력해주세요.');
				$('#email').focus();
			} else if (passwd == '') {
				alert('패스워드를 입력해주세요.');
				$('#passwd').focus();
			} else {
				$('#frmLog').attr('action', '${path}/log/login').submit();
			}
		});
	});
</script>
</head>
<body>
	<main>
		<form class="frmLog" id="frmLog" action="" method="post">
			<h2>로그인</h2>
			<div class="textForm">
				<input type="email" class="id" name="email" id="email"
					placeholder="이메일">
			</div>
			<div class="textForm">
				<input type="password" class="id" name="passwd" id="passwd"
					placeholder="패스워드">
			</div>
			<button type="button" id="loginChk" class="btn btn-default">로그인</button>
			<button type="button" id="join" class="btn btn-default">회원가입</button>
			<button type="button" onclick="location='${apiURL }'"
				class="btn btn-default" style="background-color: #54BD54;">N
				간편가입</button>
		</form>
	</main>
</body>
</html>