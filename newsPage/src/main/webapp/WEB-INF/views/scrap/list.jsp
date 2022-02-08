<%@page import="com.my.web.dto.Scrap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크랩리스트</title>
<script type="text/javascript">
	$(function() {
		//전체선택
		$('#allSelect').on('click', function() {
			//클릭여부
			//전체선택이 된경우 전체해제
			if ($("input:checkbox[name=delSnum]").prop("checked")) {
				$("input:checkbox[name=delSnum]").prop("checked", false);
			}
			//전체해제가 된경우 전체선택
			else {
				$("input:checkbox[name=delSnum]").prop("checked", true);
			}

		});

		//삭제
		$('#delBt1').on(
				'click',
				function() {
					//삭제여부유효성체크
					var result = $("input[name=delSnum]:checked");
					//console.log(result);
					if (result.length == 0) {
						alert('삭제할 스크랩을 선택해주세요.');
						return;
					}
					$('#frm').attr('action', '${path }/scrap/del').attr(
							'method', 'post').submit();
				});

		//리스트검색
		$('#selectBt1').on('click', function() {
			$('#frm').attr('action', '${path }/scrap/list').submit();
		});

		//페이지이동
		$('.alist')
				.on(
						'click',
						function(e) {
							e.preventDefault(); //기본이벤트 삭제
							var curpage = $(this).attr('href');
							var findkey = $('#findkey').val();
							var findvalue = $('#findvalue').val();
							location.href = '${path}/scrap/list?curpage='
									+ curpage + '&findkey=' + findkey
									+ '&findvalue=' + findvalue;
						});
	});
</script>
</head>
<body>
	<main>
		<div id="showScrapList" style="padding:30px">
			<form id="frm" class="form-inline" action="">
				<select name="findkey">
					<option value="title"
						<c:out value="${page.findkey == 'title'? 'selected':'' }" />>제목</option>
					<option value="description"
						<c:out value="${page.findkey == 'description'? 'selected':'' }" />>내용</option>
					<option value="titleAndDescription"
						<c:out value="${page.findkey == 'titleAndDescription'? 'selected':'' }" />>제목+내용</option>
				</select>
				<div class="form-group">
					<input type="text" name="findvalue" class="form-control"
						id="findvalue" value="${page.findvalue }">
					<button id="selectBt1">조회</button>
				</div>
				<br>
				<button type="button" id="allSelect">전체선택</button>
				<button type="button" id="delBt1">삭제</button>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>순번</th>
							<th>제목</th>
						</tr>
					</thead>
					<tbody class="table-striped">
						<c:forEach var="i" items="${slist }">
							<tr>
								<td><input type="checkbox" name="delSnum"
									value="${i.snum }"> ${i.snum }</td>
								<td><a href="${i.link }">${i.title }</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
		<!--페이징처리  -->
		<div id="showPaging">
			<c:if test="${page.startpage != 1 }">
				<a href="${page.startpage-1}" class="alist">&laquo;</a>
			</c:if>
			<c:forEach var="i" begin="${page.startpage}" end="${page.endpage }">
				<c:if test="${i==page.curpage}">
					<a href="${i}" class="alist active">${i}</a>
				</c:if>
				<c:if test="${i!=page.curpage}">
					<a href="${i}" class="alist">${i}</a>
				</c:if>
			</c:forEach>
			<c:if test="${page.totpage>page.endpage}">
				<a href="${page.endpage+1}" class="alist">&raquo;</a>
			</c:if>
		</div>
	</main>
</body>
</html>