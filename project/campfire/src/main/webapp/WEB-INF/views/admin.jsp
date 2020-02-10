<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>캠프파이어 코리아 - 관리자 페이지</title>
<meta charset="utf-8">
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

<link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="css/animate.css">

<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet" href="css/magnific-popup.css">

<link rel="stylesheet" href="css/aos.css">

<link rel="stylesheet" href="css/ionicons.min.css">

<link rel="stylesheet" href="css/bootstrap-datepicker.css">
<link rel="stylesheet" href="css/jquery.timepicker.css">


<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/style.css">

<link rel="shortcut icon" href="/fire.ico">
<link rel="icon" href="/fire.ico">
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
	font-family: 'BBTreeGB';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_nine_@1.1/BBTreeGB.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	font-family: 'BBTreeGR', sans-serif;
	/* font-family: 'yg-jalnan', sans-serif; */
	/*font-family: 'Do Hyeon', sans-serif;
      font-family: 'Jua', sans-serif;
      font-weight: 100; */
}

#videobcg {
	padding-top: 135px;
	/* padding-bottom: 100px; */
	position: absolute;
	top: 90px;
	left: 0px;
	min-width: 800px;
	min-height: auto;
	width: 100%;
	height: auto;
	z-index: -1000;
	overflow: hidden;
	background-size: cover;
	background-color: #000;
}

#ppp1 {
	text-align: center;
}

.btn-head-play {
	margin-top: 10px;
	display: inline-block;
	color: rgb(238, 255, 144);
	font-size: 50px;
	font-weight: 900;
	border: solid 5px;
	border-color: #e9ff88;
	/* border-color: rgb(235, 255, 164);  */
	/* #ebffa4 */
	border-radius: 15px;
	text-align: center;
	width: 25%;
	cursor: pointer;
}

/* 내정보 스타일 시작 */
#myPage {
	font-family: Arial, Helvetica, sans-serif;
}

/* Full-width input fields */
.input[type=text], .input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

/* Set a style for all buttons */
.button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	/*width: 100%;*/
}

#myinfo {
	width: 30px;
	height: 20px;
	font-size: 7px;
	line-height: 0px;
	border-radius: 5%;
	margin-top: 15px;
	margin-left: 30px;
}

.button:hover {
	opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

.updateProfile {
	width: auto;
	padding: 10px 18px;
	background-color: #4CAF50;
}

/* Center the image and position the close button */
.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
	position: relative;
}

img.avatar {
	width: 300px;
	height: 300px;
	border-radius: 50%;
}

/* .container {
	padding: 16px;
} */

span.psw {
	float: right;
	padding-top: 16px;
}

/* The Modal (background) */
.modal {
	display: none;
	/* Hidden by default */
	position: fixed;
	/* Stay in place */
	z-index: 1;
	/* Sit on top */
	left: 0;
	top: 0;
	width: 100%;
	/* Full width */
	height: 100%;
	/* Full height */
	overflow: auto;
	/* Enable scroll if needed */
	background-color: rgb(0, 0, 0);
	/* Fallback color */
	background-color: rgba(0, 0, 0, 0.4);
	/* Black w/ opacity */
	padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 1px solid #888;
	width: 40%;
	/* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
	position: absolute;
	right: 25px;
	top: 0;
	color: #000;
	font-size: 35px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: red;
	cursor: pointer;
}

/* Add Zoom Animation */
.animate {
	-webkit-animation: animatezoom 0.6s;
	animation: animatezoom 0.6s
}

@
-webkit-keyframes animatezoom {from { -webkit-transform:scale(0)
	
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes animatezoom {from { transform:scale(0)
	
}

to {
	transform: scale(1)
}

}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	.cancelbtn, .updateProfile {
		width: 100%;
	}
}

.iframe-board {
	border: 0;
	width: 100%;
	height: 2500px;
}

/* 내정보 스타일 끝 */
</style>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script>
/* 내정보 스크립트 시작 */
//Get the modal
var modal = document.getElementById('id01');


//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
	if (event.target == modal) {
	modal.style.display = "none";
	}
}

window.onload = function(){
	var myinfo = document.getElementById('myinfo');
	myinfo.onclick = function(){
		var mno_js = document.getElementById('mno').value;
		
		if( mno_js == "" || mno_js == null || mno_js == 'null' ){
			alert("로그인을 해주세요.");
		}
		else{
			document.getElementById('id01').style.display='block';
		}
	}

} 

 
/* 내정보 스크립트 끝  */

$(function(){

	//$('#myinfo').hide();
	
	
	var loginText = "로그인"
	var mno = localStorage.getItem('mno');
	var nickname = localStorage.getItem('nickname');
	$('#admin').hide();

	if(mno == 'c_1'){
		$('#admin').show();
		
	}else{
		$('#admin').hide();
	}

	$.ajax({url:'/isMember?mno='+mno+'&nickname='+nickname,function(re){
		console.log(re);
	}})

	var s_mno = $('#mno').val()
	
	if( s_mno == "" || s_mno == null || s_mno == 'null'){
		//$('#myinfo').hide()
		$('#loginA').html(loginText)
			
	}
	else{
		//$('#myinfo').show()
		loginText = "로그아웃"
		$('#loginA').html(loginText)
		
	}

	
	//로그인, 로그아웃 <a> 태그 눌렀을때 
	$("#loginA").click(function(e){
		e.preventDefault();
		
		if(loginText == '로그아웃'){

			$.getJSON('/logout?mno='+mno, function(r){
				if(r == 1){
					alert("로그아웃 되었습니다.")
					
					/* $('#mno').value(""); */
					
					loginText = "로그인"
					$('#loginA').html(loginText)
					mno = null;
					nickname = null;
					localStorage.removeItem('mno');
					localStorage.removeItem('nickname');
					/* session.removeAttribute("mno");
					session.removeAttribute("nickname");
					session.removeAttribute("img"); */
					
					location.href = '/main';
					$('#admin').hide();
				}
			})
			
		} else if(loginText == '로그인'){
			location.href = '/index';
			
		}
		
	})
	$("#updateProfile").click(function(){
		alert("업데이트")

	})

	
})
</script>
</head>

<body>

	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="/main">캠프파이어 코리아</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<!-- 세션에 저장된 닉네임/ 회원번호 가져오기 -->
			<form>
				<input type='hidden' name='mno' id='mno' value='${mno}'> <input
					type='hidden' name='nickname' id='nickname' value='${nickname}'>
				<input type='hidden' name='img' id='img' value='${img}'>
			</form>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="/main" class="nav-link">메인</a></li>
					<li class="nav-item"><a href="/howto" class="nav-link">하우투</a></li>
					<li class="nav-item"><a href="/ranking" class="nav-link">랭킹</a></li>
					<li class="nav-item"><a href="/game" class="nav-link">토끼의모험</a></li>
					<li class="nav-item"><a href="/community" class="nav-link">커뮤니티</a></li>
					<li class="nav-item"><a href="/contact" class="nav-link">컨택트</a></li>
					<li class="nav-item"><a href="/admin" class="nav-link" id="admin">관리자</a></li>
					<li class="nav-item"><a href="#" class="nav-link" id="loginA">로그인</a></li>
					<!--내정보 추가  시작 -->
					<!-- <li class="nav-item">\n</li> -->
					<li class="nav-item">
						<div id='myPage'>
							<button id='myinfo' class="button" style="width: auto;">내
								정보</button>

							<div id="id01" class="modal">

								<form class="modal-content animate" action="/action_page.php"
									method="post">
									<div class="imgcontainer">
										<span
											onclick="document.getElementById('id01').style.display='none'"
											class="close" title="Close Modal">&times;</span> <img
											src="mimg/${img}" alt="Avatar" class="avatar"><br>
										<input type="file" class="input uplordImg" name="uplordImg">
									</div>

									<div class="container">
										<label for="uname"><b>Nickname</b></label> <input
											class="input" type="text" value="${nickname }" name="uname"
											required>
									</div>

									<div class="container" style="background-color: #f1f1f1">
										<button type="button" id="updateProfile"
											class="button updateProfile">저장</button>
										<button type="button"
											onclick="document.getElementById('id01').style.display='none'"
											class="button cancelbtn">취소</button>
									</div>
								</form>
							</div>
						</div>
					</li>
					<!--내정보 추가  끝 -->
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->

	<div class="hero-wrap ftco-degree-bg"
		data-stellar-background-ratio="0.5">
		<div style="height: 100px;"></div>
		<video id="videobcg" preload="auto" autoplay="true" loop="loop"
			muted="muted" volume="0">
			<source src="images/index01.mp4" type="video/mp4">
			<source src="images/index01.mp4" type="video/webm">
		</video>
		<div class="container">
			<div id="ppp1">
				<div id="ppp2" class="btn-head-play">Admin</div>
			</div>
		</div>
		<div class="mouse">
			<a href="#" class="mouse-icon">
				<div class="mouse-wheel">
					<span class="ion-ios-arrow-round-down"></span>
				</div>
			</a>
		</div>
	</div>
	<!--<section class="ftco-section">-->

	<section class="ftco-section ftco-property-details goto-here">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-12">
					<div class="property-details">
						<div class="text text-center">
							<span class="subheading">CAMPFIRE</span>
							<h2>관리자 페이지</h2>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 pills">
					<div class="bd-example bd-example-tabs">
						<div class="d-flex justify-content-center">
							<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">

								<li class="nav-item"><a class="nav-link active"
									id="pills-description-tab" data-toggle="pill"
									href="#pills-description" role="tab"
									aria-controls="pills-description" aria-expanded="true">회원관리</a>
								</li>
								<li class="nav-item"><a class="nav-link"
									id="pills-manufacturer-tab" data-toggle="pill"
									href="#pills-manufacturer" role="tab"
									aria-controls="pills-manufacturer" aria-expanded="true">게시판관리</a>
								</li>
								<li class="nav-item"><a class="nav-link"
									id="pills-review-tab" data-toggle="pill" href="#pills-review"
									role="tab" aria-controls="pills-review" aria-expanded="true">통계</a>
								</li>
							</ul>
						</div>

						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-description"
								role="tabpanel" aria-labelledby="pills-description-tab">
								<div class="row">
									<iframe id='iframe-notice' class="iframe-board" src="http://localhost:8088/adminMember"></iframe>
								</div>
							</div>

							<div class="tab-pane fade" id="pills-manufacturer"
								role="tabpanel" aria-labelledby="pills-manufacturer-tab">
								<div class="row">
									<iframe id='iframe-tip' class="iframe-board" src="http://localhost:8088/adminBoard"></iframe>
								</div>
							</div>

							<div class="tab-pane fade" id="pills-review" role="tabpanel" aria-labelledby="pills-review-tab">
								<div class="row">
									<iframe id='iframe-board' class="iframe-board"  src="http://localhost:8088/adminStats"></iframe>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<footer class="ftco-footer ftco-section">
		<div class="container">
			<div class="row mb-5">
				<div class="col-md">
					<div class="ftco-footer-widget mb-4">
						<h2 class="ftco-heading-2">CAMPFIRE KOREA</h2>
						<p>Far far away, behind the word mountains, far from the
							countries.</p>
						<ul class="ftco-footer-social list-unstyled mt-5">
							<li class="ftco-animate"><a href="#"><span
									class="icon-twitter"></span></a></li>
							<li class="ftco-animate"><a href="#"><span
									class="icon-facebook"></span></a></li>
							<li class="ftco-animate"><a href="#"><span
									class="icon-instagram"></span></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md">
					<div class="ftco-footer-widget mb-4 ml-md-4">
						<h2 class="ftco-heading-2">Community</h2>
						<ul class="list-unstyled">
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Search
									Properties</a></li>
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>For
									Agents</a></li>
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Reviews</a></li>
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>FAQs</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md">
					<div class="ftco-footer-widget mb-4 ml-md-4">
						<h2 class="ftco-heading-2">About Us</h2>
						<ul class="list-unstyled">
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Our
									Story</a></li>
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Meet
									the team</a></li>
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Careers</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md">
					<div class="ftco-footer-widget mb-4">
						<h2 class="ftco-heading-2">Company</h2>
						<ul class="list-unstyled">
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>About
									Us</a></li>
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Press</a></li>
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Contact</a></li>
							<li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Careers</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md">
					<div class="ftco-footer-widget mb-4">
						<h2 class="ftco-heading-2">Have a Questions?</h2>
						<div class="block-23 mb-3">
							<ul>
								<li><span class="icon icon-map-marker"></span><span
									class="text">203 Fake St. Mountain View, San Francisco,
										California, USA</span></li>
								<li><a href="#"><span class="icon icon-phone"></span><span
										class="text">+2 392 3929 210</span></a></li>
								<li><a href="#"><span class="icon icon-envelope pr-4"></span><span
										class="text">info@yourdomain.com</span></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">

					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0.
                        Copyright &copy;<script>
                            document.write(new Date().getFullYear());
                        </script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>
			</div>
		</div>
	</footer>



	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
            <circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
            <circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>


	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-migrate-3.0.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/jquery.waypoints.min.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/aos.js"></script>
	<script src="js/jquery.animateNumber.min.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>
	<script src="js/jquery.timepicker.min.js"></script>
	<script src="js/scrollax.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script src="js/google-map.js"></script>
	<script src="js/main.js"></script>

</body>
</html>