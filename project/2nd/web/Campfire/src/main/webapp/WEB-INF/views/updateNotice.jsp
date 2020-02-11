<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Board</title>
<style>
body {
	font-family: monospace Verdana, Geneva, Tahoma, sans-serif;
	/* font-family: "Nunito Sans", Arial, sans-serif; */
	font-size: 16px;
	background: #fff;
	line-height: 1.8;
	font-weight: 400;
}

.btn {
	width: 20%;
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

.btn-sm {
	border: 1px #00001a solid;
	border-radius: 5px;
}

.btn-ls {
	float: right;
	margin-right: 0px;
}

.btn:hover {
	opacity: 0.7
}

.bg-light {
	background: #f8f9fa !important;
}

.pt-5 {
	padding-top: 3rem !important;
}

.pb-5 {
	padding-bottom: 3rem !important;
}

.pl-5 {
	padding-left: 3rem !important;
}

.pr-9 {
	padding-right: 5rem !important;
}

.mb-5 {
	margin-bottom: 3rem !important;
}

.content-form {
	clear: both;
	color: #666666;
	border-top: 1px;
	border-bottom: 1px;
}

h3 {
	font-size: 30px;
	margin-bottom: 0.5rem;
	font-family: inherit;
}

.content-form-div {
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

#content-text {
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
	white-space: pre-wrap;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://kit.fontawesome.com/0e6723bde2.js"
	crossorigin="anonymous"></script>
<script>
    $(function(){
        $("#btnUpdate").click(function(){
			alert('ok')
            var data = $("#F").serialize();
            $.post({ url:"/updateNotice", data:data, success:function(r){
                if( r == 2) {
                    alert('글이 수정되었습니다.')
                    location.href = "/notice";
                }
            }});
        });
    });
    </script>
</head>
<body>
	<div id="contentarea">
		<div id="innercontentarea"></div>

		<!-- 게시글 입력 양식 -->
		<div id="content-form" class="content-form">
			<h3 class="mb-5">수정하기</h3>
			<form action="insert" id="F" method="POST"
				class="pt-5 pb-5 pl-5 pr-9 bg-light">
				<div class="content-form-div">
					<label for="nickname">작성자 *</label> <input type="text"
						name="nickname" readonly="readonly" value="관리자"
						id="nickname" class="form-control">
				</div>
				<div class="content-form-div">
					<label for="title">제목 *</label> <input type="text" name="title"
						value="${vo.title }" id="title" class="form-control">
				</div>
				<div class="content-form-div">
					<label for="text">내용 *</label>
					<textarea name="text" id="text" cols="30" rows="50"
						class="form-control">${vo.text }</textarea>
				</div>
				
				<div class="content-form-div">
					<input type="button" id="btnUpdate" value="등록" class="btn btn-sm">
					<button type="button" id="btnList" class="btn btn-sm btn-ls"
						onclick="location.href='/notice'">목록</button>
				</div>
				<input type="hidden" name="bno" id='bno' value="${vo.bno }">
			</form>
		</div>
	</div>
</body>
</html>