<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		var kind = '${pager.kind}';
		$(".k").each(function() {
			if ($(this).val() == kind) {
				$(this).prop("selected", true);
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<h1>${board}ListPage</h1>
		<form action="./${board}List" class="form-inline">
			<div class="form-group col-xs-2">
				<select name="kind" class="form-control">
					<option value="1" class="k">Title</option>
					<option value="2" class="k">Writer</option>
					<option value="3" class="k">Contents</option>
				</select>
			</div>
			<div class="form-group col-xs-2">
				<input type="text" class="form-control" value="${pager.search}"
					name="search">
			</div>
			<div class="form-group col-xs-2">
				<button class="form-control">Search</button>
			</div>
		</form>
		<table class="table table-hover">
			<tr>
				<td>NUM</td>
				<td>TITLE</td>
				<td>WRITER</td>
				<td>DATE</td>
				<td>HIT</td>
			</tr>
			<c:forEach items="${list}" var="qnaVO">
				<tr>
					<td>${qnaVO.num}</td>
					<td>
						<c:catch>
							<c:forEach begin="1" end="${qnaVO.depth}" varStatus="i">
								&nbsp;&nbsp;
								<c:if test="${i.last}">└ </c:if>
							</c:forEach>
						</c:catch>
						<a href="${board}Select?num=${qnaVO.num}">${qnaVO.title}</a></td>
					<td>${qnaVO.writer}</td>
					<td>${qnaVO.reg_date}</td>
					<td>${qnaVO.hit}</td>
				</tr>
			</c:forEach>
		</table>
		<ul class="pagination">
			<c:if test="${pager.curBlock > 1}">
				<li><a
					href="./${board}List?curPage=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}">[이전]</a>
				</li>
			</c:if>
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<li><a
					href="./${board}List?curPage=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a>
				</li>
			</c:forEach>
			<c:if test="${pager.curBlock < pager.totalBlock}">
				<li><a
					href="./${board}List?curPage=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}">[다음]</a>
				</li>
			</c:if>
		</ul>
		<div>
			<a href="./${board}Write">Write</a>
		</div>
	</div>
</body>
</html>