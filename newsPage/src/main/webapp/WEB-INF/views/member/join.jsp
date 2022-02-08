<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../header.jsp"%>
<%@ include file="../include/jusoincludefile.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>일반회원가입</title>
<script type="text/javascript">
	$(function() {
		//중복조회
		$('#dupCheck').on('click',function() {
			var email = $('#email').val();
			//유효성체크
			if (email == '') {
				$('#email').focus();
				alert('중복조회시 이메일을 입력해주세요.');
			} 
			else {
				$.ajax({
					url : '${path}/search/dupCheck?email='+ email,
					type : 'get',
					contentType : 'application/x-www-form-urlencoded; charset=utf8',
					dataType : 'json',
					success : function(data) {
						//"1":가입회원, "0":미가입회원
						if (data == '0') {
							alert('사용가능한 이메일입니다.');
							$('#passwd').focus();
						} 
						else if (data == '1') {
							alert('중복 이메일입니다.');
							$('#email').val('');
							$('#email').focus();
						}
					},
					error : function() {
						alert("잘못된접근입니다.");
					}
				});
			}
		});

		//회원가입시 유효성 체크
		$('#joinBt1').on(
				'click',
				function() {
					var email = $('#email').val();
					var passwd = $('#passwd').val();
					var zipcode = $('#zipcode').val();
					//주소 전체 readonly 우편검색후 전체 값 입력됨 
					//따라서 우편번호만 체크하면됨
					//var addr = $('#addr').val();
					//var addrdetail = $('#addrdetail').val();
					var birthyear = $('#birthyear').val();
					var gender = $(':radio[name="gender"]:checked').val();

					if (email == '') {
						alert('이메일을 입력해주세요.');
						$('#email').focus();
					} else if (passwd == '') {
						alert('패스워드를 입력해주세요.');
						$('#passwd').focus();
					} else if (zipcode == '') {
						alert('우편번호검색을 해주세요.');
						$('#zipcode').focus();
					} else if (birthyear == '') {
						alert('태어난년도를 입력해주세요.');
						$('#birthyear').focus();
					} else {
						$('#frmJoin').attr('action', '${path}/member/joinForm')
								.submit();
					}
				});

		//가입취소
		$('#celBt1').on('click', function() {
			location = '${path}/';
		});

		//프로필사진 추가시 파일명 변경
		$("#file").on('change', function() {
			var fileName = $("#file").val();
			$(".upload-name").val(fileName);
		});
	});

	/* //주소팝업
	function goPopup(){
		// 호출된 페이지(jusoPopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	    var pop = window.open("${path}/search/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	    
		// 모바일 웹인 경우, 호출된 페이지(jusoPopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
	    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}
	/** API 서비스 제공항목 확대 (2017.02) **/
	/* 	function jusoCallBack(roadAddrPart1,addrDetail,zipNo){
	 // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	 document.frmJoinAndModi.addr.value = roadAddrPart1; //도로명 주소
	 document.frmJoinAndModi.addrdetail.value = addrDetail;//상세주소
	 document.frmJoinAndModi.zipcode.value = zipNo; //우편번호
	 } */
</script>
</head>
<body>
	<main>
		<form class="joinForm" name="frmJoinAndModi" id="frmJoin" action=""
			method="post" enctype="multipart/form-data">
			<h2>회원가입</h2>
			<div class="textForm">
				<input type="email" class="id" name="email" id="email"
					placeholder="이메일">
				<button type="button" id="dupCheck">중복조회</button>
			</div>
			<div class="textForm">
				<input type="password" class="id" name="passwd" id="passwd"
					placeholder="패스워드">
			</div>
			<div class="textForm">
				<input type="text" class="id" name="zipcode" size="2" readonly
					id="zipcode" placeholder="우편번호">
				<button type="button" onclick="goPopup()" id="zipSearch">우편검색</button>
			</div>
			<div class="textForm">
				<input type="text" class="id" name="addr" size="25" readonly
					id="addr" placeholder="도로명주소">
			</div>
			<div class="textForm">
				<input type="text" class="id" name="addrdetail" readonly
					id="addrdetail" placeholder="상세주소">
			</div>
			<div class="textForm">
				<input type="number" class="id" name="birthyear" id="birthyear"
					placeholder="태어난년도">
			</div>
			<label class="test_obj"> <input type="radio" name="gender"
				value="M" checked> <span>남</span>
			</label> <label class="test_obj"> <input type="radio" name="gender"
				value="W"> <span>여</span>
			</label>
			<div class="filebox">
				<label for="file">파일찾기</label> <input class="upload-name"
					value="프로필사진" placeholder="프로필사진" readonly size="30"> <input
					type="file" id="file" name="profile">
			</div>
			<button type="button" id="joinBt1" class="btn btn-default">회원가입</button>
			<button type="reset" id="celBt1" class="btn btn-default">가입취소</button>
		</form>
	</main>
</body>
</html>