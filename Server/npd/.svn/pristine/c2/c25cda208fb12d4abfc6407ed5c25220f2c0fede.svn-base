	google.load('visualization', '1.0', {'packages':['corechart']});
  	function search(){
  		var pid=document.getElementById("pid").value;
  		var url = "http://localhost:8080/npd/GameServlet?gid=1&pid="+pid;
  		alert(url);
  		var arr = new Array();
  		var length = 0;
  		$.ajax({
  	      type: "get",
  	      async: false,
  	      url: url,
  	      data: "",
  	      contentType: "text/plain; charset=utf-8",
  	      dataType: "json",
  		  jsonp:"jsonpcallback",
  		  timeout:100000, 
  	      cache: false,
  	      success: function (data) {
  	    	length = data.length;
  	      	for(var i=0;i<length;i++){
  	      		switch(data[i].level){
  	      		case 1:
  	      			arr.push(data[i].time);
  	      			break;
  	      		case 2:
  	      			arr.push(data[i].time);
  	      			break;
  	      		case 3:
  	      			arr.push(data[i].time);
  	      			break;
  	      		/*case 4:
  	      			arr.push(data[i].time);
  	      			break;
  	      		case 5:
  	      			arr.push(data[i].time);
  	      			break;
  	      		case 6:
  	      			arr.push(data[i].time);
  	      			break;*/
  	      		}
  	      	}
  	      	},
  	  		error: function(XMLHttpRequest, textStatus, errorThrown) {
  	        alert(errorThrown);
  	    	}
  	  	});
  		google.load("visualization", "1", {packages:["corechart"]});
  	    google.setOnLoadCallback(drawChart);
  	}
	
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ['time ', 'time'],
        ['level 1', parseInt(arr[0])],
        ['level 2',parseInt(arr[1])],
        ['level 3', parseInt(arr[2])],
        /*['level 4', parseInt(arr[3])],
        ['level 5', parseInt(arr[4])],
        ['level 6', parseInt(arr[5])],*/
      ]);
      var options = {
    		    title: 'PipeGame Result',
    		    hAxis: {format:'###s',title: 'Time Consumption', titleTextStyle: {color: 'red'}}
    		  };

    		  var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

    		  chart.draw(data, options);

    		}