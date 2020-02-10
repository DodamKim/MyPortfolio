<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
	font-family: monospace Verdana, Geneva, Tahoma, sans-serif;
	/* font-family: "Nunito Sans", Arial, sans-serif; */
	font-size: 16px;
	background: #fff;
	line-height: 1.8;
	font-weight: 400;
}

#keyword {
	width: 65%;
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

#innercontentarea {
	margin-top: 25px;
	width: 100%;
	font-size: 18px;
}

.div-title {
	background-color: #f8f8f8;
	padding-left: 30px;
	padding-top: 3px;
	padding-bottom: 0.1em;
	font-weight: 600;
	font-size: 24px;
	text-align: left;
}

.div-head {
	background-color: #f8f8f8;
	font-size: 20px;
	text-align: right;
	margin-top: 0px;
	padding-top: 0px;
	padding-bottom: 20px;
}

.sp {
	margin-top: 0px;
	margin-right: 50px;
}

#sp-writer, #sp-date {
	color: #595959;
}

.sp-rp {
	font-weight: 600;
	color: #e60000;
	cursor: pointer;
}

.i-cmd {
	font-size: 30px;
	font-weight: 500;
	color: #004080;
	text-transform: uppercase;
	cursor: pointer;
}

.i-rp {
	font-size: 30px;
	font-weight: 500;
	color: #e60000;
	text-transform: uppercase;
	/* cursor: pointer; */
}

.report {
	display: none;
}

#div-content {
	padding: 20px;
	border-bottom: 1px solid #dbdbdb;
	white-space: pre-wrap;
}

#innercontentarea-btn {
	text-align: right;
}

.btn {
	width: 16%;
	display: inline;
	background-color: #00001a;
	border: none;
	color: white;
	cursor: pointer;
	padding: 16px 32px;
	font-weight: 600;
	font-size: 16px;
	margin: 4px 2px;
	opacity: 0.9;
	transition: 0.3s;
}

.btn-cm {
	width: 20%;
	border: 1px #00001a solid;
	border-radius: 5px;
}

.btn:hover {
	opacity: 0.7
}

.bg-light {
	background: #f8f9fa !important;
}

.p-5 {
	padding: 3rem !important;
}

.pt-5 {
	padding-top: 3rem !important;
}

.p-cm-5 {
	padding-top: 3rem !important;
	padding-bottom: 3rem !important;
	padding-left: 3rem !important;
	padding-right: 5rem !important;
}

.mb-5 {
	margin-bottom: 3rem !important;
}

.comment-form, .comment-body {
	clear: both;
	color: #666666;
	border-top: 1px;
	border-bottom: 1px;
}

.comment-body p {
	white-space: pre-line;
}

#comment div {
	display: inline;
}

h3 {
	font-size: 30px;
	margin-bottom: 0.5rem;
	font-family: inherit;
}

#comment h3 {
	font-size: 22px;
}

.comment-list {
	padding: 0;
	margin: 0;
}

ul {
	display: block;
	list-style-type: disc;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	padding-inline-start: 40px;
}

.comment-list li {
	padding: 0;
	margin: 0 0 30px 0;
	float: left;
	width: 100%;
	clear: both;
	list-style: none;
}

li {
	display: list-item;
	text-align: -webkit-match-parent;
}

img {
	vertical-align: middle;
	border-style: none;
	border-radius: 50%;
	width: 50px;
	margin-right: 20px;
}

.comment-list li .comment-body {
	float: right;
	width: calc(100% - 80px);
}

.comment-list li .comment-body .meta {
	text-transform: uppercase;
	font-size: 13px;
	letter-spacing: .1em;
	color: #ccc;
}

p {
	margin-top: 0;
	margin-bottom: 1rem;
	display: block;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
}

/* 자유게시판에서만 필요 */
.comment-list li .comment-body .reply {
	padding: 5px 10px;
	background: #e6e6e6;
	color: #000000;
	text-transform: uppercase;
	font-size: 15px;
	letter-spacing: .1em;
	font-weight: 400;
	border-radius: 4px;
	text-decoration: none;
}

.comment-form-div {
	margin-bottom: 1rem;
}

label {
	font-size: 20px;
	display: inline-block;
	margin-bottom: 0.5rem;
	cursor: default;
}

.form-control {
	height: 52px !important;
	background: #fff !important;
	color: #000000 !important;
	font-size: 18px;
	border-radius: 5px;
	box-shadow: none !important;
	display: block;
	width: 100%;
	padding: 0.375rem 0.75rem;
	font-weight: 400;
	line-height: 1.5;
	color: #495057;
	border: 1px solid #ced4da;
	transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
		-webkit-box-shadow 0.15s ease-in-out;
}

#comment-text {
	margin-top: 0px;
	margin-bottom: 0px;
	height: 534px;
	overflow: auto;
	resize: vertical;
	margin: 0;
	font-family: inherit;
}

textarea.form-control {
	height: inherit !important;
}

/* 내가 쓴 css */
/* * {
	margin: 5px;
} 

#member-img {
	width: 30px;
	height: 30px;
	display: inline;
	width: 30;
	height: 30;
} */
#contents {
	padding: 1%;
	padding-top: 5%;
	width: 100%;
	height: 250px;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script type="text/javascript">
	$(function() {

		var mno = $('#mno').val()
		var bno = $('#bno').val()

		if (mno != 'c_1') {
			$('#btnUpdate').hide()
			$('#btnDelete').hide()
		} else{
			$('#btnUpdate').show()
			$('#btnDelete').show()
		}

		$('#btnDelete').click(function() {
			var result = confirm('정말로 삭제하시겠습니까?');
			 if(result == true){
				if( mno == 'c_1'){
					$.ajax({
						url : '/deleteNotice?bno='+bno,
						success : function(r) {
							if (r == 1) {
								alert('삭제되었습니다.')
								location.href = '/notice';
							}
						}
					})
				}
			} 
		});
		
	});
</script>
</head>
<body>
	<!-- bno, title, bdate, bimg, nickname, img, rc, dc, report, tag, text -->
	<!-- 자게 상세보기 -->
	<form>
		<input type='hidden' id='mno' value='${mno }'>
		<input type='hidden' id='bno' value='${vo.bno }'>
	</form>
	<div id="contentarea">
		<div id="innercontentarea">
			<!-- 제목 -->
			<div id="div-title" class="div-title">${vo.title }</div>
			<!-- 작성자 / 작성일 / 추천 / 비추천 -->
			<div class="div-head">
				<!-- 작성자 -->
				<span id="sp-writer" class="sp"> 관리자</span>
				<!-- 작성일 -->
				<span id="sp-date" class="sp">${vo.bdate }</span>

			</div>
			<hr>
			<div id='contents'>${vo.text }</div>
			<hr>
			<div id='info' align="right">
				<span id='tag'></span>
			</div>

			<!-- 글목록 / 수정 / 삭제  -->
			<div id="innercontentarea-btn">
				<button type="button" id="btnList" class="btn"
					onclick="location.href='/notice'">목록</button>
				<button type="button" id="btnUpdate" class="btn"
					onclick="location.href='/updateNotice?bno=${vo.bno }'">수정</button>
				<button type="button" id="btnDelete" class="btn">삭제</button>
			</div>
		</div>
	</div>
</body>
</html>
