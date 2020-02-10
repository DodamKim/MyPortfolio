<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="416717030310-5ogf2dc81ah096codaab2ikui8e9cplp.apps.googleusercontent.com">

<link
	href="https://fonts.googleapis.com/css?family=Nunito+Sans:200,300,400,600,700,800,900&display=swap"
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
<style type="text/css">
#wrap {
	padding-top: 5%;
	padding-left: 30%;
	padding-right: 30%;
}

#home {
	width: 100%;
	text-align: left;
}

.login {
	text-align: center;
	padding-top: 1%;
}

.g-signin2 {
	display: table;
	margin-left: auto;
	margin-right: auto;
	margin-top: 1%;
	margin-bottom: 1%;
}

#gmLogin {
	text-align: center;
	padding-top: 10%;
}
</style>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<script src="https://apis.google.com/js/platform.js" async defer></script>

<!-- 페이스북관련 스크립트  -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div id='wrap'>
		<a class="navbar-brand" href="main" id="home">CAMPFIRE KOREA</a>
		<hr>
		<br>

		<!-- 카카오 -->
		<div class='login'>
			<a id="kakao-login-btn"></a> <a
				href="http://developers.kakao.com/logout"></a>


			<!-- 네이버아이디로로그인 버튼 노출 영역 -->
			<div id="naver_id_login" class='login'></div>

			<!-- 구글 로그인 버튼 노출 영역 -->
			<div class="g-signin2" data-onsuccess="onSignIn" data-theme="dark"
				data-width="222" data-height="45"></div>

			<!-- 페북 로그인 버튼 노출 영역 -->
			<span id="fbName"></span> <img src="login/facebook.png"
				style="cursor: pointer" id="fbBtn" value="로그인">


		</div>

		<script type='text/javascript'>
			var nickname;
			var mno;

			//<![CDATA[
			// 사용할 앱의 JavaScript 키를 설정해 주세요.
			Kakao.init('9797dc90c57374948488d51150870dbc');
			// 카카오 로그인 버튼을 생성합니다.
			Kakao.Auth.createLoginButton({
				container : '#kakao-login-btn',
				success : function(authObj) {
					Kakao.API.request({
						url : '/v1/user/me',
						success : function(res) {
							//alert(JSON.stringify(res)); //<---- kakao.api.request 에서 불러온 결과값 json형태로 출력
							//alert(JSON.stringify(authObj)); //<----Kakao.Auth.createLoginButton에서 불러온 결과값 json형태로 출력
							//console.log(res.id);//<---- 콘솔 로그에 id 정보 출력(id는 res안에 있기 때문에  res.id 로 불러온다)
							//console.log(res.kaccount_email);//<---- 콘솔 로그에 email 정보 출력 (어딨는지 알겠죠?)
							//console.log(res.properties['nickname']);//<---- 콘솔 로그에 닉네임 출력(properties에 있는 nickname 접근 
							// res.properties.nickname으로도 접근 가능 )
							console.log(authObj.access_token);//<---- 콘솔 로그에 토큰값 출력
							mno = "K_" + res.id;
							console.log("k 합친버전 : " + mno);
							nickname = res.properties['nickname'];
							nickname = nickname;
							console.log("닉네임 : " + nickname);

							localStorage.setItem('mno', mno);
							localStorage.setItem('nickname', nickname);
							location.href = '/main';

						}
					})
				},
				fail : function(err) {
					alert(JSON.stringify(err));
				}
			});
			//]]>

			var naver_id_login = new naver_id_login("qp7Fp0Q9FXFJjq2IKfMi",
					"http://172.20.10.2:8088/test");
			var state = naver_id_login.getUniqState();
			naver_id_login.setButton("green", 3, 48);
			naver_id_login.setDomain("http://172.20.10.2:8088");
			naver_id_login.setState(state);
			naver_id_login.setPopup();
			naver_id_login.init_naver_id_login();

			function onSignIn(googleUser) {
				// Useful data for your client-side scripts:
				var profile = googleUser.getBasicProfile();
				//				console.log("ID: " + profile.getId()); // Don't send this directly to your server!
				//				console.log('Full Name: ' + profile.getName());
				//				console.log('Given Name: ' + profile.getGivenName());
				//				console.log('Family Name: ' + profile.getFamilyName());
				//				console.log("Image URL: " + profile.getImageUrl());
				//				console.log("Email: " + profile.getEmail());

				mno = "G_" + profile.getId();
				console.log("G 합친버전 : " + mno);
				nickname = profile.getName();
				console.log("닉네임 : " + nickname);

				localStorage.setItem('mno', mno);
				localStorage.setItem('nickname', nickname);
				location.href = '/main';

				// The ID token you need to pass to your backend:
				var id_token = googleUser.getAuthResponse().id_token;
				//console.log("ID Token: " + id_token);
			}

			/* 페이스북 */
			var checkFb = function(response) {
				console.log(response);
				//statusChangeCallback(response);
				if (response.status === 'connected') {
					document.querySelector('#fbBtn').value = '로그아웃';
					FB.api('/me', function(resp) {
						//document.querySelector('#fbName').innerHTML = '반갑습니다. '
						//		+ resp.name + '님';
						//console.log('이름' + resp.name);
						//console.log('아이디' + resp.id);

						mno = "F_" + resp.id
						console.log("F 합친버전 : " + mno);
						nickname = resp.name
						console.log("닉네임 : " + nickname);

						localStorage.setItem('mno', mno);
						localStorage.setItem('nickname', nickname);
						location.href = '/main';
					});
				} else {
					document.querySelector('#fbBtn').value = '로그인';
					document.querySelector('#fbName').innerHTML = ''
				}
			}

			window.fbAsyncInit = function() {
				FB.init({
					appId : '424414954887764',
					cookie : true,
					xfbml : true,
					version : 'v5.0'
				});

				FB.getLoginStatus(checkFb);

			};

			(function(d, s, id) {
				var js, fjs = d.getElementsByTagName(s)[0];
				if (d.getElementById(id)) {
					return;
				}
				js = d.createElement(s);
				js.id = id;
				js.src = "https://connect.facebook.net/en_US/sdk.js";
				fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));

			$(function() {

				$('#fbBtn').click(function() {
					if (this.value === '로그인') {
						FB.login(function(res) {
							console.log('로그인 => ', res);
							checkFb(res);

						});
					} else {
						FB.logout(function(res) {
							console.log('로그아웃 => ', res);
							checkFb(res);
						});
					}

				})

			})

			$(document)
					.bind(
							'keypress',
							function(event) {
								if (event.which === 71 && event.shiftKey) {
									//alert('you pressed SHIFT+G');
									$("#gmLogin").empty();

									var input1 = $('<input type="password" id="gmPw" name="gmPw" placeholder="관리자 비밀번호 입력">')
									var input2 = $(
											'<button id="gmLoginBtn"></button>')
											.html("로그인")
									$("#gmLogin").append(input1, input2)

									$("#gmLoginBtn")
											.click(
													function() {
														var gmPw = $("#gmPw")
																.val();

														$
																.ajax({
																	url : '/gmLogin?gmPw='
																			+ gmPw,
																	success : function(
																			r) {

																		if (r == 1) {
																			localStorage
																					.setItem(
																							'mno',
																							'c_1');
																			localStorage
																					.setItem(
																							'nickname',
																							'관리자');
																			location.href = '/main';
																		} else {
																			alert("비밀번호가 틀렸습니다.")
																		}
																	}
																})
													})

								}
							});
		</script>
		<div id="gmLogin"></div>
	</div>



</body>
</html>