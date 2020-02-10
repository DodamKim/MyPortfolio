<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Notice</title>
<style>
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

.btn {
	/* background-color: #f4511e; */
	/* background-color: #004080; */
	/* background-color: #564b47; */
	background-color: #00001a;
	width: 16%;
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

#btnSearch:hover {
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

/* #btnNew {
	background-color: #00001a;
	width: 16%;
	border: none;
	color: white;
	padding: 16px 32px;
	text-align: center;
	font-weight: 600;
	font-size: 16px;
	margin: 4px 2px;
	opacity: 0.9;
	transition: 0.3s;
} */
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

/* 추가한 css */
.img {
	display: inline;
}
</style>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script type="text/javascript">
	$(function() {
		var sc = $('#sc').val();
		var key = $('#key').val();

		if(sc != ''){
			$('#search').val(sc);
			$('#keyword').val(key);
			
		}else{
			$('#search').val('title');
		}
		
		$('#keyword').click(function(){
			$('#keyword').val('')
		})
		
		$('#search').change(function(){
			$('#keyword').val('')
		})

		/* 총 레코드 수 */
		var totalRecord;
		/* 한 페이지에 보여질 글의 수 */
		var pageSize = 10;
		/* 페이지에 노출될 시작 글번호 */
		var start = 1;
		/* 페이지에 노출될 마지막 글번호 */
		var end = start + pageSize - 1;
		/* 현재페이지 번호 */
		var nowPage = 1;
		/* 한 번에 보여질 페이지 숫자의 개수 */
		var page = 5;
		/* 페이지 그룹 [예: 1-5, 6-10, 11-15] */
		var pageGroup;
		/* 시작 페이지 번호 */
		var startPage;
		/* 끝 페이지 번호 */
		var endPage;
		
		var keyword = null;
		var search = null;
		
		$.getJSON('/getCnt_notice', function(data) {
			$('#btnPageSpan').empty();
			
			totalRecord = Number(data);
			var totalPage = parseInt(Math.ceil(totalRecord / pageSize));
			
			pageGroup = parseInt(Math.ceil(nowPage / page));
			startPage = (pageGroup - 1) * page + 1;
			endPage = startPage + page - 1;
			
			for (i = startPage; i <= endPage; i++) {
				$('#btnPageSpan')
				.append('<a href="#" class="nowPage btnPage">' + i + '</a>&nbsp;&nbsp;')
			}
			
			$('.nowPage').click(function(e) {
				e.preventDefault();
				nowPage = $(this).html();
				nowPage = Number(nowPage);
				//alert(nowPage);
				start = (nowPage - 1) * pageSize + 1;
				end = start + pageSize - 1;

				listBoard(start, end, search, keyword);
				
			});

		});
		
		var listBoard = function(start, end, search, keyword) {
			
			$.getJSON('/listNotice?start=' + start + '&end=' + end + '&search='
					+ search + '&keyword=' + keyword, function(arr) {
				$('#innercontentarea-tb').empty();
				$.each(arr, function(idx, data) {
							// <th>글제목</th><th>작성자</th><th>작성일</th><th>파일명</th><th>태그</th>
							
							var tr = $('<tr></tr>').attr('bno', data.bno);
							
							/* 신고글 블라인드 처리  */
							if(data.report >= 5){
								//alert(data.report)
								var report = $('<td colspan="8"></td>').html('신고 5회로 인하여 블라인드 처리된 글 입니다.').addClass('title-rp')
								$(tr).append(report)
								
							} else{
								var a = $('<a></a>').attr('href',
										'/noticeDetail?bno=' + data.bno).addClass('btnPage')
								var title = $('<td></td>')
								var p = $('<p></p>').html(data.title);
								$(a).append(p)
								$(title).append(a)
	
								var nickname = $('<td></td>').html(
										"관리자");
								
								var bdate = $('<td></td>').html(data.bdate);
								
								$(tr).append(title, nickname, bdate)
							}						
							$('#innercontentarea-tb').append(tr);
						});
			});
		}
		listBoard(start, end, search, keyword);

		/* 검색 */
		$('#btnSearch').click(function() {

			search = $('#search').val();
			keyword = $('#keyword').val();

			// 특수문자
            var specialC = /[~!@\#$%^&*\()\-=+_'\;<>0-9\/.\`:\"\\,\[\]?|{}]/gi;
            // 미완성된 한글
            var nonKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
            // 키워드 내 공백 제거
            keyword = keyword.replace(/(\s*)/g,"");
            if( keyword.match(specialC)){
                var str = "특수문자는 입력하실 수 없습니다.\r\nex) ~!@\#$%^&*\()\-=+_'\;<>0-9\/.\`:\"\\,\[\]?|{}";
                alert(str);
                return;
                
            } else if( keyword.match(nonKorean)){
                var str = "완성된 한글로 정확하게 입력해주세요.";
                alert(str);
            }
			

			/* $('#sc').val(search);
			$('#key').val(keyword); */

			listBoard(start, end, search, keyword);

			
		});
		
		var mno = $('#mno').val()

		if (mno != 'c_1') {
			$('#btnNew').hide()	
		} else{
			$('#btnNew').show()
		}

	});
</script>
</head>
<body>
<form>
	<input type='hidden' id='mno' value='${mno }'>
</form>
	<div id="contentarea">
		<div id="innercontentarea-search" class="pt-5">
			<form>
				<input type='hidden' id='sc' name='sc' value='${search }'> <input
					type='hidden' id='key' name='key' value='${keyword }'> <span
					id="selSP"> <select name="search" id="search">
						<option value="title">제목</option>
						<option value="nickname">작성자</option>
						<option value="text">내용</option>
				</select>
				</span> <span id="inputSP"> <input type="text" name="keyword"
					id="keyword">
				</span> <span id="btnSP">
					<button type="submit" id="btnSearch" class="btn">검색하기</button>
				</span>
			</form>
		</div>

		<div id="innercontentarea">
			<table id="innercontentarea-tb">
				<!-- <thead id="board-thead">
					<tr>
						<th>글제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>파일명</th>
						<th>태그</th>
						<th>내용</th>
					</tr>
				</thead> -->
				<tbody id="innercontentarea-tb"></tbody>
			</table>
		</div>

		<div id="innercontentarea-new">
			<button type="button" id="btnNew" class="btn btn-new"
				onclick="location.href='/insertNotice'">새글쓰기</button>
		</div>

	</div>

	<div id="innercontentarea-page">
		<i class="fas fa-angle-double-left" id='first'></i> &nbsp;&nbsp; <i
			class="fas fa-angle-left" id='fore'></i>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="btnPageSpan"></span>
		&nbsp;&nbsp; <i class="fas fa-angle-right" id='next'></i> &nbsp;&nbsp;
		<i class="fas fa-angle-double-right" id='end'></i>
	</div>
</body>
</html>