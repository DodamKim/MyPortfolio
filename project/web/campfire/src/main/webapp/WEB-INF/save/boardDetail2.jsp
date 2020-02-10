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
		
		var bno = $('#bno').val()
		var level;
		
		/* 추천, 비추, 신고기능 자기 아이디와 다를때만 가능하게 하기 */
		/* 추천 클릭시 */
		$('#i-rc').click(function(){
			$.ajax({url:'/updateRC?bno='+bno, success: function(rc){
				console.log(rc)
				$('#sp-rc').html(rc)
			}})
		})
		
		/* 비추 클릭시 */
		$('#i-dc').click(function(){
			//alert(bno)
			$.ajax({url:'/updateDC?bno='+bno, success: function(dc){
				console.log(dc)
				$('#sp-dc').html(dc)
			}})
		})
		
		/* 신고 클릭시 */
		$('#i-rp').click(function(){
			confirm('해당글을 신고하시겠습니까?')
			$.ajax({url:'/updateRP?bno='+bno, success: function(r){
				alert('신고')
			}})
		})

		/* 새 댓글 등록 */
		$('#insertComment').click(function() {
			var data = $('#F').serialize()
			$.post('/insertComment', data, function(r) {
				console.log(r)
			});
		});

		/* 댓글수 출력 */
		$.getJSON('/getCommentNum?bno='+bno, function(num){
			$('#commentsNum').html(num)
		})

		/* 댓글 출력 */
		$.getJSON('/listComments?bno=' + bno, function(arr) {
			//alert(bno)
			//alert(arr)
			//$('#comment-list').empty()
			$.each(arr, function(idx, data) {

				var comment = $('<li id="comment" class="comment"></li>')
				
				/* 댓글 하나 묶음  */
				var wrap = $('<div></div>') // 전체 감싸기 [공백이미지 + 댓글]
				var div = $('<div></div>') // 댓글 한 묶음 감싸기
				
				/* 댓글 공백 처리 시도중 */
				var level = data.c_level
				level = Number(level)
				console.log(level) //레벨은 잘 출력됨 ㅠㅠ 이미지 안나옴 ㅎ
				var blank = $('<span></span>') // span이어야 나란히 출력될거 같아서..
				for(var i; i<=level; i++){
					var img = $('<img></img>').attr({src: 'mimg/1.png', width:'50px', height:'100px'})
					$(blank).append(img)
				}
				
				var vcard = $('<div class="vcard"></div>')				
				var img = $('<img alt="Image placeholder"></img>').attr({
					src : "mimg/" + data.img
				});
				$(vcard).append(img)
				
				var comment_body = $('<div class="comment-body"></div>')
				var nickname = $('<h3></h3>').html(data.nickname)
				$(nickname).prepend(img)
				var date = $('<div class="meta"></div>').html(data.cdate)
				var text = $('<p></p>').html(data.ctext)
				var reply = $('<p></p>')
				var a = $('<a href="#" class="reply"></a>').attr({cno: data.cno, level: data.c_level}).html("Reply")
						
				$(reply).append(a)
				$(comment_body).append(nickname, date, text, reply)

				$(div).append(vcard, comment_body)
				//$(wrap).append(blank ,div)
				$(comment).append(blank ,div)
				$('.comment-list').append(comment);

				
				/* reply버튼 누를경우 동적으로 입력창 생성 */
				$(a).click(function() {
					
					var cno = $(this).attr('cno')
					//alert('cno : ' + cno)
					//alert('bno : ' + bno)
					$('#re').remove();
					
					var div = $('<div id="re" class="comment-body"></div>')	
					var form = $("<form method='post' id='cf'></form>")
					var f_cno = $("<input type='hidden' name='cno' value="+cno+">")
					var f_bno = $("<input type='hidden' name='bno' value="+bno+">")
					/* 로그인한 회원의 닉네임으로 고정하기  */
					var f_nickname = $("<input type='text' name='nickname' size='50'>")
					var f_ctext = $("<textarea name='ctext' cols='70' rows='5'></textarea>")
					var f_submit = $("<input type='submit' value='등록'>")
					$(form).append(f_cno, f_bno, '<br>', '작성자 : ', f_nickname, '<br>', f_ctext, f_submit)
					
					$(div).append(form);
					$(comment).append(div);

					/* 댓글의 댓글 등록 */
					$(f_submit).click(function(){
						var data = $(form).serialize()
						$.getJSON('/insertComment', data, function(r){
							console.log('r : '+r);
						})
					})
				})
			});
		});

		/* 태그 칼럼 불러와서 단어별로 구분하여 그려주기 */
		var tags = $('#tags').val()
		var taglist = tags.split(',');
		var tag = '';
		$.each(taglist, function(i, data) {
			tag += '<i class="fas fa-hashtag"></i>' + data + '&nbsp;';
		});

		$('#tag').html(tag);

		$('#btnDelete').click(function() {
			/* 글쓴이와 로그인한 회원의 번호가 일치할때만 삭제되게 조건 추후에 추가! */
			var result = confirm('정말로 삭제하시겠습니까?');
			alert(result)
			if (result) {
				$.ajax({
					url : '/deleteBoard?bno=' + bno,
					success : function(r) {
						if (r == 1) {
							alert('삭제되었습니다.')
							location.href = '/board';
						}
					}
				})
			}
		});
	});
</script>
</head>
<body>
	<!-- bno, title, bdate, bimg, nickname, img, rc, dc, report, tag, text -->
	<!-- 자게 상세보기 -->
	<div id="contentarea">
		<div id="innercontentarea">
			<!-- 제목 -->
			<div id="div-title" class="div-title">${vo.title }</div>
			<!-- 작성자 / 작성일 / 추천 / 비추천 -->
			<div class="div-head">
				<!-- 작성자 -->
				<span id="sp-writer" class="sp"><img id='member-img'
					src='mimg/${vo.img }'>${vo.nickname }</span>
				<!-- 작성일 -->
				<span id="sp-date" class="sp">${vo.bdate }</span>
				<!-- 추천 아이콘 -->
				<i id="i-rc" class="far fa-thumbs-up i-cmd"></i>&nbsp;&nbsp;
				<!-- 추천수 -->
				<span id="sp-rc" class="sp">${vo.rc }</span>
				<!-- </span> -->
				<!-- 비추천 아이콘 -->
				<i id="i-dc" class="far fa-thumbs-down i-cmd"></i>&nbsp;&nbsp;
				<!-- 비추천수 -->
				<span id="sp-dc" class="sp">${vo.dc }</span>
				<!-- 신고 -->
				<span id="i-rp" class="sp sp-rp"> <i
					class="far fa-angry i-rp"></i>&nbsp;&nbsp;신고 <!-- <span id="report" style="display: none;"></span> -->
				</span>
			</div>
			<hr>
			<div id='contents'>${vo.text }</div>
			<form>
				<input type='hidden' id='tags' value='${vo.tag }'> 
			</form>
			<hr>
			<div id='info' align="right">
				<span id='tag'></span>
			</div>

			<!-- 글목록 / 수정 / 삭제  -->
			<div id="innercontentarea-btn">
				<button type="button" id="btnList" class="btn"
					onclick="location.href='/board'">목록</button>
				<button type="button" id="btnUpdate" class="btn"
					onclick="location.href='/updateBoard?bno=${vo.bno }'">수정</button>
				<button type="button" id="btnDelete" class="btn">삭제</button>
			</div>

			<!-- 댓글 -->
			<div id="comment-body" class="comment-body mt-5 pt-5">
				<h3 class="mb-5">
					<span id=commentsNum></span> Comment
				</h3>
				<!-- 댓글 리스트 -->
				<ul class="comment-list">
				</ul>
			</div>

			<!-- <h3>comments</h3> -->

			<!-- 댓글 입력 양식 -->
			<div id="comment-form" class="comment-form pt-5">
				<h3 class="mb-5">댓글쓰기</h3>
				<form id='F' action="#" class="p-5 bg-light">
					<div class="comment-form-div">
						<label for="nickname">작성자 *</label> <input type="text"
							name="nickname" id="nickname" placeholder="추후 닉네임 자동설정"
							class="form-control">
					</div>
					<div class="comment-form-div">
						<label for="ctext">내용</label>
						<textarea name="ctext" id="ctext" cols="30" rows="10"
							class="form-control"></textarea>
					</div>
					<div class="comment-form-div">
						<input type="hidden" id='bno' name='bno' value='${vo.bno }'>
						<input id='insertComment' type="submit" value="등록"
							class="btn btn-cm">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
