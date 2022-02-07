<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="../header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages':['bar']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChartTot);//토탈
google.charts.setOnLoadCallback(drawChartM);//남성
google.charts.setOnLoadCallback(drawChartW);//여성
google.charts.setOnLoadCallback(ageGraph);//나이

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
      
//전체 통계
function drawChartTot() {
// Create the data table.     
	var jsonData = $.ajax({
		url : "${path}/statistics/graphForm",
		dataType : "json",
		async : false //ajax의 비동기방식을 동기식으로 변환
		}).responseText; //서버의 응답테스트
        
		var arr = new Array(); //[,]형태로 가공하기위함
		var result = JSON.parse(jsonData);//json형태로변환
		
		for(var i=0;i < result.length;i++){
			arr[i]=[result[i].findvalue,result[i].cnt]
		};
		var data = new google.visualization.DataTable();
    	data.addColumn('string', 'KeyWords');
    	data.addColumn('number', 'Count');
    
    	data.addRows(arr);
        // Set chart options
        /* var options = {'title':'키워드검색그래프',
        			   'width':450,
                	   'height':450,
                	   'pieHole': 0.4}; */
        var options = {
        	title: '키워드그래프',
        	width: 400,
        	legend: { position: 'none' },
        	chart: { title: '키워드그래프',
        	subtitle: '전체키워드통계Top5' },
        	bars: 'horizontal', // Required for Material Bar Charts.
        	axes: {
        		x: {
        		0: { side: 'top', label: 'Percentage'} // Top x-axis.
        		}
        	},
        	bar: { groupWidth: "90%" }
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.charts.Bar(document.getElementById('totChart'));
        chart.draw(data, options);
      }
      
//남성별통계
function drawChartM() {
// Create the data table.
	var jsonData = $.ajax({
	url : "${path}/statistics/graphForm/m",
	dataType : "json",
	async : false //ajax의 비동기방식을 동기식으로 변환
	}).responseText; //서버의 응답텍스트
	
	var arr = new Array(); //[,]형태로 가공하기위함
	var result = JSON.parse(jsonData);//json형태로변환
	for(var i=0;i < result.length;i++){
	arr[i]=[result[i].findvalue,result[i].cnt]
	};

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'KeyWords');
	data.addColumn('number', 'Count');
	data.addRows(arr);
	// Set chart options
	var options = {
        	title: '키워드그래프',
        	width: 400,
        	legend: { position: 'none' },
        	chart: { title: '키워드그래프',
        	subtitle: '남성키워드통계Top5' },
        	bars: 'horizontal', // Required for Material Bar Charts.
        	axes: {
        		x: {
        		0: { side: 'top', label: 'Percentage'} // Top x-axis.
        		}
        	},
        	bar: { groupWidth: "90%" }
		};
        // Instantiate and draw our chart, passing in some options.
		 var chart = new google.charts.Bar(document.getElementById('MChart'));
		chart.draw(data, options);
	}
      
//여성별통계
function drawChartW() {
// Create the data table.
var jsonData = $.ajax({
	url : "${path}/statistics/graphForm/w",
	dataType : "json",  			
	async : false //ajax의 비동기방식을 동기식으로 변환
	}).responseText; //서버의 응답테스트	
	var arr = new Array(); //[,]형태로 가공하기위함
	var result = JSON.parse(jsonData);//json형태로변환
	for(var i=0;i < result.length;i++){
		arr[i]=[result[i].findvalue,result[i].cnt]
	};
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'KeyWords');
	data.addColumn('number', 'Count');
	data.addRows(arr);
// Set chart options
	var options = {
        	title: '키워드그래프',
        	width: 400,
        	legend: { position: 'none' },
        	chart: { title: '키워드그래프',
        	subtitle: '여성키워드통계Top5' },
        	bars: 'horizontal', // Required for Material Bar Charts.
        	axes: {
        		x: {
        		0: { side: 'top', label: 'Percentage'} // Top x-axis.
        		}
        	},
        	bar: { groupWidth: "90%" }
		};
// Instantiate and draw our chart, passing in some options.
	var chart = new google.charts.Bar(document.getElementById('WChart'));
	chart.draw(data, options);
	}
//나이별 통계그래프

function ageGraph() {
	var findkey = $('select[name=findkey] option:selected').val();
	console.log(findkey);
	$.ajax({
		url : "${path}/statistics/graphForm/age",
		data : {findkey},
		dataType : "json",
		success : function drawChartAge(resultCnt)
		{
			
			var arr = new Array(); //[,]형태로 가공하기위함
			
			for(var i=0;i < resultCnt.length;i++){
			arr[i]=[resultCnt[i].findvalue,resultCnt[i].cnt]
			};
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'KeyWords');
			data.addColumn('number', 'Count');
			data.addRows(arr);
			// Set chart options
			var options = {
        	title: '키워드그래프',
        	width: 400,
        	legend: { position: 'none' },
        	chart: { title: '키워드그래프',
        	subtitle: findkey +'대 키워드통계Top5' },
        	bars: 'horizontal', // Required for Material Bar Charts.
        	axes: {
        		x: {
        		0: { side: 'top', label: 'Percentage'} // Top x-axis.
        		}
        	},
        	bar: { groupWidth: "90%" }
		};

			// Instantiate and draw our chart, passing in some options.
			 var chart = new google.charts.Bar(document.getElementById('ageChart'));
			chart.draw(data, options);      
     	},
		error : function(){
			console.log('error');
		}
     	
	})
}
</script>
</head>
<body>
<main>
	<div class="container">
	<form  class="form-inline">
	<h2 id="graphTitle">통계그래프</h2>
		<select name="findkey">
			<option value="10"
				<c:out value="${findkey == '10'? 'selected':'' }"/>>10대</option>
			<option value="20"
				<c:out value="${findkey == '20'? 'selected':'' }"/>>20대</option>
			<option value="30"
				<c:out value="${findkey == '30'? 'selected':'' }"/>>30대</option>
			<option value="40"
				<c:out value="${findkey == '40'? 'selected':'' }"/>>40대</option>
			<option value="50"
				<c:out value="${findkey == '50'? 'selected':'' }"/>>50대</option>
			<option value="60"
				<c:out value="${findkey == '60'? 'selected':'' }"/>>60대</option>
		</select>
		<div class="form-group">
			<button type="button" id="ageBt1" class="form-control"
				onclick="ageGraph()">조회</button> *데이터가 부족할 경우 표시가 안됩니다.
		</div>
	<table class="columns">
		<tr>
			<td><div id="ageChart" style="border: 1px solid #ccc ;width: 600px; height: 300px;"></div></td>
			<td><div id="totChart" style="border: 1px solid #ccc ;width: 600px; height: 300px;"></div></td>	
		</tr>
	</table>
	<table class="columns">
		<tr>
			<td><div id="MChart" style="border: 1px solid #ccc;width: 600px; height: 300px;"></div></td>
			<td><div id="WChart" style="border: 1px solid #ccc;width: 600px; height: 300px;"></div></td>
		</tr>
	</table>
</form>
</div>
</main>
</body>
</html>
<%@ include file="../footer.jsp" %>
