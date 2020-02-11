<!--
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,600,700,800,900&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap&subset=korean"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff"
	rel="stylesheet">
<title>Record</title>
<style type="text/css">
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
	font-family: 'BBTreeGB';
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

tbody tr:hover {
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

#crown {
	color: gold;
}

.pt-5 {
	padding-top: 3rem !important;
}

h1 {
	text-align: center;
}

.ranker {
	font-weight: bold;
}

.ck {
	position: absolute;
	margin-top: 1%;
	cursor: pointer;
}

.ck:hover {
	color: pink;
}

#today {
	margin-left: 83%;
}

#total {
	margin-left: 90%;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script type="text/javascript">
		$(function() {

			function addList (arr) {

				$('#innercontentarea-tb').empty();
				$.each(arr, (idx, data) => {

					var tr = $('<tr></tr>')
					var rank = $('<td></td>')

					var img = $('<img></img>').attr({
						src: "mimg/" + data.img,
						width: '50px',
						height: '50px'
					})
					var name = $('<p></p>').html(data.nickname)
					var nickname = $('<td></td>').append(img, name)

					var rtime = $('<td></td>').html(data.rtime)
					var rdate = $('<td></td>').html(data.rdate)

					if (idx == 0) {
						var icon = ('<i class="fas fa-crown" style="color:#FFCC33; font-size:50px;"></i>')
						$(nickname).addClass('ranker')
						$(rtime).addClass('ranker')
						$(rdate).addClass('ranker')

					} else if (idx == 1) {
						var icon = ('<i class="fas fa-crown" style="color:#EAEAEA; font-size:30px;"></i>')
						$(nickname).addClass('ranker')
						$(rtime).addClass('ranker')
						$(rdate).addClass('ranker')

					} else if (idx == 2) {
						var icon = ('<i class="fas fa-crown" style="color:#bfa452; font-size:30px;"></i>')
						$(nickname).addClass('ranker')
						$(rtime).addClass('ranker')
						$(rdate).addClass('ranker')

					} else if (idx > 2) {
						$(rank).html(idx + 1)
						//console.log(idx+1)
					}

					$(rank).append(icon)

					$(tr).append(rank, nickname, rtime, rdate)

					$('#innercontentarea-tb').append(tr)

				})
			}

			function listTotal (){
				$.getJSON('/listRecord', function(arr) {
					addList(arr)
				})
			}

			listTotal();
			
			$('#total').click(function(){
				$('#tt').html('Total')
				listTotal();
			})
			
			$('#today').click(function(){
				$('#tt').html('Today')
				$.getJSON('/todayRecord', function(arr){
					addList(arr)
				})
			})
		})
	</script>
</head>

<body>
	<div class='ck' id='today'>today</div>
	<div class='ck' id='total'>total</div>
	<h1>
		<span id="tt">Total</span> Ranking <i class="fas fa-medal"></i> TOP 10
	</h1>
	<div id="contentarea">
		<div id="innercontentarea">
			<table id="innercontentarea-th">
				<thead id="board-thead">
					<tr>
						<th>순위</th>
						<th>닉네임</th>
						<th>시간(초)</th>
						<th>달성일</th>
					</tr>
				</thead>
				<tbody id="innercontentarea-tb"></tbody>
			</table>
		</div>
	</div>
</body>
</html>