<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기사내용</title>
<script type="text/javascript">
	$(function () {
		//제목 내용 링크 표시
		$('#showTitle').html("${result.title}");
		$('#showDescription').html("${result.description} <a target='detailIframe' id='showIframe'>자세히보기</a> <br> <button type='button' id='scrapBt1'>스크랩</button> <span id='showResult'></span>");
		
		//iframe표시
		$('#showDescription').on('click','#showIframe',function(){
			$('#showIframe').html("<iframe src='${result.link}' name='detailIframe' style=' width:150%;  height:600px;'>");
		});
		
		//스크랩버튼클릭시
		$('#showDescription').on('click','#scrapBt1',function(){
			var title = "${result.title}";
			var description = "${result.description}";
			var link = "${result.link}";
			$.ajax({
				url : '${path}/scrap/insert',
				type : 'get',
				data : {title,description,link},
				contentType: "application/x-www-form-urlencoded; charset=utf-8",
				dataType : 'text',
				success : function (data) {
					$('#showResult').html(data);
				},
				error : function(){
					$('#showResult').html('스크랩실패');
				}
			});
		});
	})
</script>
</head>
<body>
	<main>
		<h2 id="showTitle"></h2>
		<p id="showDescription"></p>
		<div id="showIfame"></div>
	</main>
</body>
</html>
<%@ include file="../footer.jsp"%>