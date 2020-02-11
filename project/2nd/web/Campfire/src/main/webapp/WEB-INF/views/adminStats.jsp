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

.admin-menu-dv {
	text-align: center;
}
.admin-menu-sp {
	font-size: 24px;
	padding: 20px;
	cursor: pointer;
}

/*  .admin-stats-div {
	display: none;
}

.admin-stats-active {
	display: block;
} */

.stats-title {
	padding-top: 30px;
}

#innercontentarea-th {
	margin-bottom: 20px;
}
</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	
	var type = ["k","n","g","f"];	
	$.ajax({url:"/getTotal", success:function(data){
		var total = data;
		var tt_total = $("<td></td>").html(total);
		var tt_rate = $("<td></td>").html(100);
		$("#innercontentarea-tb-tt").append(tt_total, tt_rate);

		$.ajax({url:"/getCountType", data:{type:type[0]}, success:function(re){
			var k_type_tt = $("<td></td>").html(re);	
			var k_rate = eval(re/total*100).toFixed(1);;
			var k_type_rate = $("<td></td>").html(k_rate);
 			$("#innercontentarea-tb-k").append(k_type_tt, k_type_rate);
		}});
			
		$.ajax({url:"/getCountType", data:{type:type[1]}, success:function(re){
			var n_type_tt = $("<td></td>").html(re);	
			var n_rate = eval(re/total*100).toFixed(1);;
			var n_type_rate = $("<td></td>").html(n_rate);
 			$("#innercontentarea-tb-n").append(n_type_tt, n_type_rate);
		}});
		
		$.ajax({url:"/getCountType", data:{type:type[2]}, success:function(re){
			var g_type_tt = $("<td></td>").html(re);	
			var g_rate = eval(re/total*100).toFixed(1);
			var g_type_rate = $("<td></td>").html(g_rate);
 			$("#innercontentarea-tb-g").append(g_type_tt, g_type_rate);
		}});
		
		$.ajax({url:"/getCountType", data:{type:type[3]}, success:function(re){
			var f_type_tt = $("<td></td>").html(re);	
			var f_rate = eval(re/total*100).toFixed(1);;
			var f_type_rate = $("<td></td>").html(f_rate);
 			$("#innercontentarea-tb-f").append(f_type_tt, f_type_rate);
		}});
	}});

	$("#btnStats").click(function(){
		var start = $("#monthFD").val();
		console.log(start);
		var data = {start:start};
		$("#innercontentarea-stats-tb").empty();

		var m_date = 0;
		var m_visit = 0;
		var m_play = 0;
		
		$.getJSON("/dataList", data, function(data){
			$.each(data, function(idx, d){
				var tr = $("<tr></tr>");
				var date = $("<td></td>").html(d.ddate.substring(0,10));
				m_date = d.ddate.substring(0,7);
				var visit = $("<td></td>").html(d.visit);
				m_visit += parseInt(d.visit);
				var play = $("<td></td>").html(d.play);
				m_play += parseInt(d.play);
				$(tr).append(date, visit, play);
				$("#innercontentarea-stats-tb").append(tr);
			});
			// console.log("총 방문자 : "+m_visit+", 총 플레이 : "+m_play)
			var tt_tr = $("<tr></tr>");
			var tt_date = $("<td></td>").html(m_date);
			var tt_visit = $("<td></td>").html("총 "+m_visit);
			var tt_play = $("<td></td>").html("총 "+m_play);
			$(tt_tr).append(tt_date, tt_visit, tt_play);
			$("#innercontentarea-stats-tb").prepend(tt_tr);
		});
	});

	$("#admin-stats-div-mb").hide();
	$("#admin-stats-div-st").hide();
	$("#admin-menu-mb").click(function(){
		console.log("mb 클릭");
		$("#admin-stats-div-st").hide();
		$("#admin-stats-div-mb").show();
	});
	$("#admin-menu-st").click(function(){
		console.log("st 클릭");
		$("#admin-stats-div-mb").hide();  
        $("#admin-stats-div-st").show();
	});
});
</script>
</head>
<body>
	<div id="contentarea">
		<div id="innercontentarea">
			<div class="admin-menu-dv">
				<span id="admin-menu-mb" class="admin-menu-sp">Member</span>
				<span id="admin-menu-st" class="admin-menu-sp">Stats</span>
			</div>
			<div id="admin-stats-div-mb" class="admin-stats-div">
				<div class="stats-title">
					<h2>Member</h2>
				</div>
				<table id="innercontentarea-th">
					<thead id="board-thead">
						<tr>
							<th>구분</th>
							<th>가입자 수 (명)</th>
							<th>비율 (%)</th>
						</tr>
					</thead>
					<tbody id="innercontentarea-tb">
						<tr id="innercontentarea-tb-tt">
							<td>전체</td>
						</tr>
						<tr id="innercontentarea-tb-k">
							<td>카카오</td>
						</tr>
						<tr id="innercontentarea-tb-n">
							<td>네이버</td>
						</tr>
						<tr id="innercontentarea-tb-g">
							<td>구글</td>
						</tr>
						<tr id="innercontentarea-tb-f">
							<td>페이스북</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="admin-stats-div-st" class="admin-stats-div">
				<div class="stats-title">
					<h2>Stats</h2>
				</div>
				<div>
					<select name="monthFD" id="monthFD">
	                	<option value="" selected disabled>[월별]</option>
						<option value="19/01/01">1월</option>
						<option value="19/02/01">2월</option>
						<option value="19/03/01">3월</option>
						<option value="19/04/01">4월</option>
						<option value="19/05/01">5월</option>
						<option value="19/06/01">6월</option>
						<option value="19/07/01">7월</option>
						<option value="19/08/01">8월</option>
						<option value="19/09/01">9월</option>
						<option value="19/10/01">10월</option>
						<option value="19/11/01">11월</option>
						<option value="19/12/01">12월</option>
					</select>
					<span id="btnSP">
	                    <button type="button" id="btnStats" class="btn">검색하기</button>    
	                </span>
				</div>
				<table id="innercontentarea-th">
				<thead id="board-thead">
					<tr>
						<th>날짜</th>
						<th>방문자 수 (명)</th>
						<th>플레이 수 (회)</th>
					</tr>
				</thead>
				<tbody id="innercontentarea-stats-tb"></tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>