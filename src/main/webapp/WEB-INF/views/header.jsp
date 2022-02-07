<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./include/includefile.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	if ('${msg}' != '')
		alert('${msg}');

	$(function() {
		//간편가입 일반가입 여부에 따른 회원정보 및 스크랩 분류
		if ('${simplejoin}' == '0') //일반가입
		{
			if ('${cert}' == '1') //인증성공 1
			{
				$('#detail').attr('href', '${path}/member/detail');
				$('#scrapList').attr('href', '${path}/scrap/list');
				return;
			}
			$('#detail').attr('href', '${path}/member/pwchk?route=0');
			$('#scrapList').attr('href', '${path}/member/pwchk?route=1');
			return;
		}
		if ('${simplejoin}' == '1') //네이버간편가입
		{
			$('#detail').attr('href', '${path}/member/detail');
			$('#scrapList').attr('href', '${path}/scrap/list');
			return;
		}

	});
</script>
</head>
<header>
	<div class="menu">
		<nav class="clearfix">
			<ul class="clearfix">
				<li><a href="${path }/">Home</a></li>
				<li><a href="${path }/statistics/graph">통계</a></li>
				<li><a href="${path }/place">오시는길</a></li>
				<c:if test="${email == null }">
					<li><a id="login" href="${path }/member/login">로그인</a></li>
				</c:if>
				<c:if test="${email != null }">
					<li><a href="${path }/newspage/">뉴스검색</a></li>	
				</c:if>
			</ul>
			<c:if test="${email != null }">
				<div style="float: right; background-color:white; " >
					<table border="1">
						<tr>
							<td><img alt="" src="${path}/saveImges/${profilename }"
								width="50px"> 
							</td>
							<td>${email} 님</td>
						</tr>
						<tr>
							<td colspan="3" align="center"><a href="${path }/log/logout">로그아웃</a> <a
								href="" id="detail">회원정보</a> <a href="" id="scrapList">스크랩정보</a></td>
						</tr>
					</table>
				</div>
			</c:if>
		</nav>
	</div>

</header>
</body>
</html>