<!--
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin - Member List</title>
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
</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
		$(function() {

			var sc = $('#sc').val();
			var key = $('#key').val();

			if (sc != '') {
				$('#search').val(sc);
				$('#keyword').val(key);

			} else {
				$('#search').val('mno');
			}

			$('#keyword').click(function() {
				$('#keyword').val('')
			})

			$('#search').change(function() {
				$('#keyword').val('')
			})

			var search = null
			var keyword = null
			var seltr;
			var mno = '';
			var nowPage = 1;


			var listMember = function(search, keyword, nowPage){
				
				$.getJSON('/listMember?search='+search+'&keyword='+keyword+'&nowPage='+nowPage, function(arr) {
					$('#innercontentarea-tb').empty();

					$.each(arr, (idx, data) => {

						var tr = $('<tr></tr>')
						var t_mno = $('<td></td>').html(data.mno)

						var id = $('<td></td>').html(data.id)

						var img = $('<img></img>').attr({
							src: "mimg/" + data.img,
							width: '50px',
							height: '50px'
						})
						var name = $('<p></p>').html(data.nickname)
						var nickname = $('<td></td>').append(img, name)

						var mlv = $('<td></td>')

						if (data.mlv != 1) {
							mlv.html('<font color="red">정지회원</font>')

						} else {
							mlv.html('')
						}

						$(tr).append(t_mno, id, nickname, mlv)

						$('#innercontentarea-tb').append(tr)

						/* 선택된 tr 알기위한 이벤트 처리 */
						$(tr).click(function(){
							
							if(seltr != $(this)){
								$('tr').removeClass('sel')
							}
							
							$(this).addClass('sel')
							seltr = $(this)
							mno = data.mno
							console.log('클릭한 회원번호 : '+ mno)
							
						})
					})
				})
			}

			listMember(search, keyword, nowPage)
			
			$('.nowPage').click(function(e) {
				
				e.preventDefault();
				nowPage = $(this).html();
				console.log(nowPage)
				//nowPage = Number(nowPage);
				//alert(nowPage);

				listMember(search, keyword, nowPage)

			});
			
			
			$('#btnStop').click(function(){

				var check = confirm('해당 회원의 권한을 정지하시겠습니까?')
				console.log('전달 할 회원번호 : '+mno)
				if(check){
					$.ajax({url:'/stopMember?mno='+mno, success:function(r){
						if(r == 1){
							alert('해당 회원의 회원 권한이 정지되었습니다.')
						}
						listMember(search, keyword, nowPage)
					}})
				}
			})
			
			$('#btnReset').click(function(){

				var check = confirm('해당 회원의 권한을 복구하시겠습니까?')

				if(check){
					$.ajax({url:'/resetMember?mno='+mno, success:function(r){
						if(r == 1){
							alert('해당 회원의 회원 권한이 복구되었습니다.')
						}
						listMember(search, keyword, nowPage)
					}})
				}
			})
			
			$('#btnDelete').click(function(){

				var check = confirm('해당 회원을 삭제하시겠습니까?')

				if(check){
					$.ajax({url:'/deleteMember?mno='+mno, success:function(r){
						if(r == 1){
							alert('해당 회원이 삭제되었습니다.')
						}
						listMember(search, keyword, nowPage)
					}})
				}
			})

			$('#btnSearch').click(function(){
				
				search = $('#search').val();
				keyword = $('#keyword').val();
				console.log(search)
				console.log(keyword)

				// 특수문자
				var specialC = /[~!@\#$%^&*\()\-=+_'\;<>0-9\/.\`:\"\\,\[\]?|{}]/gi;
				// 미완성된 한글
				var nonKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
				// 키워드 내 공백 제거
				keyword = keyword.replace(/(\s*)/g, "");
				if (keyword.match(specialC)) {
					var str = "특수문자는 입력하실 수 없습니다.\r\nex) ~!@\#$%^&*\()\-=+_'\;<>0-9\/.\`:\"\\,\[\]?|{}";
					alert(str);
					return;

				} else if (keyword.match(nonKorean)) {
					var str = "완성된 한글로 정확하게 입력해주세요.";
					alert(str);
				}
				listMember(search, keyword, nowPage)
				
			})
			
		})
	</script>
</head>

<body>
	<div id="contentarea">

		<div id="innercontentarea-search" class="pt-5">
			<form>
				<input type='hidden' id='sc' name='sc' value='${search }'> <input
					type='hidden' id='key' name='key' value='${keyword }'> <span
					id="selSP"> <select name="search" id="search">
						<option value="mno">고유번호</option>
						<option value="id">아이디</option>
						<option value="nickname">닉네임</option>
				</select>
				</span> <span id="inputSP"> <input type="text" name="keyword"
					id="keyword">
				</span> <span id="btnSP">
					<button type="submit" id="btnSearch" class="btn">검색하기</button>
				</span> <span id="btnSP">
					<button type="submit" id="btnStop" class="btn">정지</button>
				</span> <span id="btnSP">
					<button type="submit" id="btnReset" class="btn">해제</button>
				</span> <span id="btnSP">
					<button type="submit" id="btnDelete" class="btn">삭제</button>
				</span>
			</form>
		</div>

		<div id="innercontentarea">
			<table id="innercontentarea-th">
				<thead id="board-thead">
					<tr>
						<th>고유번호</th>
						<th>아이디</th>
						<th>닉네임</th>
						<th>활동</th>
					</tr>
				</thead>
				<tbody id="innercontentarea-tb"></tbody>
			</table>
		</div>

		<div id="innercontentarea-page">
			<i class="fas fa-angle-double-left" id='first'></i> 
			&nbsp;&nbsp; 
			<i class="fas fa-angle-left" id='fore'></i>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<span id="btnPageSpan">
				<c:forEach begin="1" end="5" var="i">
					<a href="#" class="nowPage btnPage">${i }</a>&nbsp;&nbsp;
				</c:forEach>
			</span>
			&nbsp;&nbsp; 
			<i class="fas fa-angle-right" id='next'></i>
			&nbsp;&nbsp; 
			<i class="fas fa-angle-double-right" id='end'></i>
		</div>

	</div>
</body>
</html>