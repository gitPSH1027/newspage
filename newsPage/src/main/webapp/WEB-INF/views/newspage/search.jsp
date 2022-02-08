<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스검색</title>
<script type="text/x-handlebars-template" id="searchList">
		<table class="table table-striped"  style="text-align:center;">
		{{#each .}}
		<tr>
			<th>
				<form id="frmDe" action="${path}/newspage/detail" method="post">
					<span name="title" value="{{title}}">{{{title}}}</span> <button id="showDetail">기사보기</button>
					<input type="hidden" name="title" value="{{title}}">
					<input type="hidden" name="description" value="{{description}}">
					<input type="hidden" name="link" value="{{link}}">
				</form>	
			</th>
		</tr>
		{{/each}}
		</table>
</script>
<script type="text/javascript">
	$(function () {
		$('#showNews').on('click','#showDetail',function(){
			$('#frmDe').attr('atcion','').attr('method','post').submit();
		});
	})


	//검색버튼 클릭시
	function enterSearch(){
		if (window.event.keyCode == 13){
			var findvalue = $('#searchFindvalue').val();
			console.log(findvalue);
			//검색어 유효성
			if(findvalue == '')
			{
				alert('검색어를 입력해주세요.');
				$('#findvalue').focus();
				return;
			}
			$.ajax({
				url : '${path}/newspage/searchlist',
				type : 'get',
				data : {findvalue},
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				dataType : 'json',
				success : function(data){
					console.log(data);
					var source = $('#searchList').html();
					var template = Handlebars.compile(source);
					$('#showNews').html(template(data));
					alert('검색완료');
				},
				error : function(){
					alert('잘못된접근입니다. 로그인후 검색하세요.');
				}
			});
		}
			
	}
</script>
</head>
<body>
	<main>
		<h2 id="searchH2">뉴스키워드검색</h2>
		<div class="search">
			<input type="text" name="findvalue" id="searchFindvalue"
				placeholder="검색어 입력" onkeyup="enterSearch()"> 
			<img id="imgSearch" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
		</div>
		<hr>
		<div id="showNews"></div>
	</main>
</body>
</html>
<%@ include file="../footer.jsp"%>