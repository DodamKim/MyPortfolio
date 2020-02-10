<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - board control</title>
<link
	href="https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,600,700,800,900&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap&subset=korean"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff"
	rel="stylesheet">
<style>
/* 유토이미지고딕R */
@font-face {
	font-family: 'BBTreeGR';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeGR.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

/* 유토이미지고딕B */
@font-face {
	font-family: 'BBTreeGR', sans-serif;
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeGB.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	font-size: 16px;
}

select {
	width: 15%;
	height: 51px;
	padding: .8em .5em;
	margin-right: 5px;
	border: 1px solid #555;
	font-size: 16px;
	font-family: inherit;
	background: url('selectbox.jpg') no-repeat 95% 50%;
	border-radius: 0px;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}

select::-ms-expand {
	display: none;
}

#keyword {
	width: 42%;
	height: 24px;
	padding: .8em .5em;
	margin-right: 3px;
	border: 1px solid #555;
	font-size: 16px;
	font-family: inherit;
	border-radius: 0px;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}

.btn {
	background-color: #00001a;
	border: none;
	color: white;
	padding: 16px 32px;
	text-align: center;
	font-weight: 600;
	font-size: 16px;
	margin: 4px 2px;
	opacity: 0.9;
	transition: 0.3s;
	cursor: pointer;
}

.btn-new {
	border: 1px #00001a solid;
	border-radius: 5px;
}

.btn:hover {
	opacity: 0.7
}

table {
	margin-top: 15px;
	border-collapse: collapse;
	width: 100%;
	/* color: #564b47;; */
}

thead tr {
	background-color: #f8f8f8;
	border-top: 1px solid #dbdbdb;
	font-size: small;
}

th, td {
	padding: 25px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

th {
	width: 25%;
}

tr:hover {
	background-color: #f5f5f5;
}

.title {
	padding: 30px;
	text-align: left;
	font-size: larger;
	font-weight: 550;
	cursor: pointer;
	/* text-decoration: none; */
}

.title-rp {
	padding: 30px;
	text-align: center;
	font-size: larger;
	font-weight: 40S0;
	color: #e60000;
	text-decoration: line-through;
	cursor: default;
}

#innercontentarea-new {
	padding-top: 10px;;
	text-align: right;
}

#innercontentarea-page {
	text-align: center;
	color: #00001a;
	font-size: 20px;
}

#btnNew:hover {
	opacity: 0.7
}

.btnPage {
	font-weight: 600;
	color: #00001a;
	text-decoration: none;
}

#crown {
	color: gold;
}

.pt-5 {
	padding-top: 3rem !important;
}

.sel {
	background-color: lightgray;
}

#btnSP {
	padding-left: 55%;
}
</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
	$(function() {

		var seltr;
		var mno = '';
		var bno = '';

		$.getJSON('/listAdminBoard', function(arr) {
			$('#innercontentarea-tb').empty();

			$.each(arr, function(idx, data) {
				// <th>글제목</th><th>작성자</th><th>작성일</th><th>파일명</th><th>태그</th>

				var tr = $('<tr></tr>').attr('bno', data.bno)
				//var a = $('<a href="#" class="btnPage"></a>').addClass('title')
				
				/* $(a).click(function() {
					if ($('#mno').val() != 'null' && $('#mno').val() != '' && $('#mno').val() != null) {
						location.href = '/boardDetail?bno='+ data.bno
					}
				} */

				var title = $('<td></td>').html(data.title)
				/* var p = $('<p></p>').html(data.title);
				$(a).append(p)
				$(title).append(a) */

				var td = $('<td></td>')
				var img = $('<img class="img"></img>').attr({
					src : 'mimg/' + data.img,
					width : 30,
					height : 30
				})
				var nickname = $('<p class="img"></p>').html(data.nickname);
				$(td).append(img, "&nbsp;&nbsp;", nickname)

				var bdate = $('<td></td>').html(data.bdate);
				var report = $('<td></td>')
						.html('<font color="red">신고글</font>');
				/* var rc = $('<td></td>').html('<i id="i-rc" class="far fa-thumbs-up i-cmd"></i>&nbsp;'+ data.rc); */

				$(tr).append(title, td, bdate, report)
				$('#innercontentarea-tb').append(tr);

				/* 선택된 tr 알기위한 이벤트 처리 */
				$(tr).click(function(){
					
					if(seltr != $(this)){
						$('tr').removeClass('sel')
					}
					
					$(this).addClass('sel')
					seltr = $(this)
					//mno = data.mno
					bno = $(this).attr('bno')
					//console.log('클릭한 회원번호 : '+ mno)
					console.log('클릭한 글번호 : '+ bno)
					
				})
			});
		});

		$('#btnDetail').click(function(){
			localStorage.setItem('admin','admin')
			location.href = '/boardDetail?bno='+ bno
		})
		
		$('#btnDelete').click(function(){
			var result = confirm('정말로 삭제하시겠습니까?');
			//alert(result)
			if (result) {
				$.ajax({
					url : '/deleteBoard?bno=' + bno,
					success : function(r) {
						if (r == 1) {
							alert('삭제되었습니다.')
							location.href = '/adminBoard';
						}
					}
				})
			}
		})
		
		$('#btnReset').click(function(){
			var result = confirm('블라인드를 해제하시겠습니까?');
			//alert(result)
			if (result) {
				$.ajax({
					url : '/resetReport?bno=' + bno,
					success : function(r) {
						if (r == 1) {
							alert('블라인드가 해제되었습니다.')
							location.href = '/adminBoard';
						}
					}
				})
			}
		})

	})
</script>
</head>
<body>
	<div id="contentarea">
		<div id="innercontentarea-search" class="pt-5">
			<h2 style="display: inline">신고글 관리</h2>
			<!--		<div id="innercontentarea-search" class="pt-5" style="display: inline">-->
			<!--			<form>-->
			<!--<input type='hidden' name='mno' id='mno' value='${mno}'> 
				<input type='hidden' name='nickname' id='nickname' value='${nickname}'>-->
			<span id="btnSP">
				<button id="btnDetail" class="btn">글보기</button>
				<button id="btnDelete" class="btn">글삭제</button>
				<button id="btnReset" class="btn">신고해제</button>
			</span>
			<!--			</form>-->
		</div>
		<hr>
		<div id="innercontentarea">
			<table id="innercontentarea-tb">
				<thead id="board-thead">
					<tr>
						<th>글제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>구분</th>
					</tr>
				</thead>
				<tbody id="innercontentarea-tb"></tbody>
			</table>
		</div>

	</div>
</body>
</html>