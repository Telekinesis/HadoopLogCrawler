<html>
	<head>
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/d3/3.4.8/d3.min.js"></script>
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.0/js/jquery.dataTables.min.js"></script>
		<link rel="stylesheet" type="text/css" href="yarngantt.css"/>
		<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.0/css/jquery.dataTables.css"/>
		<script type="text/javascript">

			//var jobStatistics = {"durationInSecond":81,"nodes":[{"nodeID":"/default-rack/bigant-dev-003:8042","maxParallel":8,"attempts":[{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000001","attemptID":"attempt_1402538726852_0018_m_000001_0","type":"MAP","slot":0,"startSecond":0,"endSecond":11},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000004","attemptID":"attempt_1402538726852_0018_m_000004_0","type":"MAP","slot":1,"startSecond":0,"endSecond":13},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000007","attemptID":"attempt_1402538726852_0018_m_000007_0","type":"MAP","slot":2,"startSecond":1,"endSecond":17},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000010","attemptID":"attempt_1402538726852_0018_m_000010_0","type":"MAP","slot":3,"startSecond":2,"endSecond":18},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000013","attemptID":"attempt_1402538726852_0018_m_000013_0","type":"MAP","slot":4,"startSecond":3,"endSecond":18},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000016","attemptID":"attempt_1402538726852_0018_m_000016_0","type":"MAP","slot":5,"startSecond":4,"endSecond":22},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000019","attemptID":"attempt_1402538726852_0018_m_000019_0","type":"MAP","slot":6,"startSecond":5,"endSecond":24},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000021","attemptID":"attempt_1402538726852_0018_m_000021_0","type":"MAP","slot":7,"startSecond":6,"endSecond":25},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000025","attemptID":"attempt_1402538726852_0018_m_000025_0","type":"MAP","slot":0,"startSecond":13,"endSecond":31},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000027","attemptID":"attempt_1402538726852_0018_m_000027_0","type":"MAP","slot":1,"startSecond":15,"endSecond":33},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000031","attemptID":"attempt_1402538726852_0018_m_000031_0","type":"MAP","slot":2,"startSecond":19,"endSecond":38},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000033","attemptID":"attempt_1402538726852_0018_m_000033_0","type":"MAP","slot":3,"startSecond":19,"endSecond":37},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000035","attemptID":"attempt_1402538726852_0018_m_000035_0","type":"MAP","slot":4,"startSecond":20,"endSecond":39},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000039","attemptID":"attempt_1402538726852_0018_m_000039_0","type":"MAP","slot":5,"startSecond":24,"endSecond":41},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000043","attemptID":"attempt_1402538726852_0018_m_000043_0","type":"MAP","slot":6,"startSecond":26,"endSecond":42},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000044","attemptID":"attempt_1402538726852_0018_m_000044_0","type":"MAP","slot":7,"startSecond":27,"endSecond":46},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000051","attemptID":"attempt_1402538726852_0018_m_000051_0","type":"MAP","slot":0,"startSecond":32,"endSecond":52},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000054","attemptID":"attempt_1402538726852_0018_m_000054_0","type":"MAP","slot":1,"startSecond":35,"endSecond":51},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000057","attemptID":"attempt_1402538726852_0018_m_000057_0","type":"MAP","slot":2,"startSecond":39,"endSecond":55},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000059","attemptID":"attempt_1402538726852_0018_m_000059_0","type":"MAP","slot":3,"startSecond":40,"endSecond":57},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000060","attemptID":"attempt_1402538726852_0018_m_000060_0","type":"MAP","slot":4,"startSecond":41,"endSecond":58},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000061","attemptID":"attempt_1402538726852_0018_m_000061_0","type":"MAP","slot":5,"startSecond":43,"endSecond":62},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000064","attemptID":"attempt_1402538726852_0018_m_000064_0","type":"MAP","slot":6,"startSecond":44,"endSecond":61},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000069","attemptID":"attempt_1402538726852_0018_m_000069_0","type":"MAP","slot":7,"startSecond":47,"endSecond":64},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000076","attemptID":"attempt_1402538726852_0018_m_000076_0","type":"MAP","slot":0,"startSecond":53,"endSecond":73},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000077","attemptID":"attempt_1402538726852_0018_m_000077_0","type":"MAP","slot":1,"startSecond":54,"endSecond":72},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000080","attemptID":"attempt_1402538726852_0018_m_000080_0","type":"MAP","slot":2,"startSecond":57,"endSecond":75},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000081","attemptID":"attempt_1402538726852_0018_m_000081_0","type":"MAP","slot":3,"startSecond":58,"endSecond":76},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000084","attemptID":"attempt_1402538726852_0018_m_000084_0","type":"MAP","slot":4,"startSecond":59,"endSecond":77},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000088","attemptID":"attempt_1402538726852_0018_m_000088_0","type":"MAP","slot":5,"startSecond":62,"endSecond":79},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000089","attemptID":"attempt_1402538726852_0018_m_000089_0","type":"MAP","slot":6,"startSecond":63,"endSecond":79},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000092","attemptID":"attempt_1402538726852_0018_m_000092_0","type":"MAP","slot":7,"startSecond":66,"endSecond":79}]},{"nodeID":"/default-rack/bigant-dev-002:8042","maxParallel":7,"attempts":[{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000000","attemptID":"attempt_1402538726852_0018_m_000000_0","type":"MAP","slot":0,"startSecond":0,"endSecond":15},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000003","attemptID":"attempt_1402538726852_0018_m_000003_0","type":"MAP","slot":1,"startSecond":0,"endSecond":20},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000006","attemptID":"attempt_1402538726852_0018_m_000006_0","type":"MAP","slot":2,"startSecond":1,"endSecond":24},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000009","attemptID":"attempt_1402538726852_0018_m_000009_0","type":"MAP","slot":3,"startSecond":1,"endSecond":23},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000012","attemptID":"attempt_1402538726852_0018_m_000012_0","type":"MAP","slot":4,"startSecond":2,"endSecond":25},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000015","attemptID":"attempt_1402538726852_0018_m_000015_0","type":"MAP","slot":5,"startSecond":3,"endSecond":28},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000018","attemptID":"attempt_1402538726852_0018_m_000018_0","type":"MAP","slot":6,"startSecond":4,"endSecond":28},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000030","attemptID":"attempt_1402538726852_0018_m_000030_0","type":"MAP","slot":0,"startSecond":17,"endSecond":42},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000038","attemptID":"attempt_1402538726852_0018_m_000038_0","type":"MAP","slot":1,"startSecond":22,"endSecond":44},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000041","attemptID":"attempt_1402538726852_0018_m_000041_0","type":"MAP","slot":2,"startSecond":25,"endSecond":50},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000042","attemptID":"attempt_1402538726852_0018_m_000042_0","type":"MAP","slot":3,"startSecond":26,"endSecond":50},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000045","attemptID":"attempt_1402538726852_0018_m_000045_0","type":"MAP","slot":4,"startSecond":27,"endSecond":51},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000046","attemptID":"attempt_1402538726852_0018_m_000046_0","type":"MAP","slot":5,"startSecond":28,"endSecond":53},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000048","attemptID":"attempt_1402538726852_0018_m_000048_0","type":"MAP","slot":6,"startSecond":29,"endSecond":59},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000063","attemptID":"attempt_1402538726852_0018_m_000063_0","type":"MAP","slot":0,"startSecond":44,"endSecond":71},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000067","attemptID":"attempt_1402538726852_0018_m_000067_0","type":"MAP","slot":1,"startSecond":45,"endSecond":69},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000071","attemptID":"attempt_1402538726852_0018_m_000071_0","type":"MAP","slot":2,"startSecond":50,"endSecond":70},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000073","attemptID":"attempt_1402538726852_0018_m_000073_0","type":"MAP","slot":3,"startSecond":51,"endSecond":74},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000075","attemptID":"attempt_1402538726852_0018_m_000075_0","type":"MAP","slot":4,"startSecond":52,"endSecond":76},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000078","attemptID":"attempt_1402538726852_0018_m_000078_0","type":"MAP","slot":5,"startSecond":54,"endSecond":77},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000086","attemptID":"attempt_1402538726852_0018_m_000086_0","type":"MAP","slot":6,"startSecond":61,"endSecond":79},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000095","attemptID":"attempt_1402538726852_0018_m_000095_0","type":"MAP","slot":1,"startSecond":70,"endSecond":81}]},{"nodeID":"/default-rack/bigant-dev-004:8042","maxParallel":8,"attempts":[{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000002","attemptID":"attempt_1402538726852_0018_m_000002_0","type":"MAP","slot":0,"startSecond":0,"endSecond":6},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000005","attemptID":"attempt_1402538726852_0018_m_000005_0","type":"MAP","slot":1,"startSecond":0,"endSecond":8},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000008","attemptID":"attempt_1402538726852_0018_m_000008_0","type":"MAP","slot":2,"startSecond":1,"endSecond":11},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000011","attemptID":"attempt_1402538726852_0018_m_000011_0","type":"MAP","slot":3,"startSecond":2,"endSecond":15},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000014","attemptID":"attempt_1402538726852_0018_m_000014_0","type":"MAP","slot":4,"startSecond":3,"endSecond":14},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000017","attemptID":"attempt_1402538726852_0018_m_000017_0","type":"MAP","slot":5,"startSecond":4,"endSecond":17},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000020","attemptID":"attempt_1402538726852_0018_m_000020_0","type":"MAP","slot":6,"startSecond":5,"endSecond":18},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000022","attemptID":"attempt_1402538726852_0018_m_000022_0","type":"MAP","slot":0,"startSecond":6,"endSecond":20},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000023","attemptID":"attempt_1402538726852_0018_m_000023_0","type":"MAP","slot":7,"startSecond":7,"endSecond":20},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000024","attemptID":"attempt_1402538726852_0018_m_000024_0","type":"MAP","slot":1,"startSecond":10,"endSecond":22},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000026","attemptID":"attempt_1402538726852_0018_m_000026_0","type":"MAP","slot":2,"startSecond":13,"endSecond":27},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000028","attemptID":"attempt_1402538726852_0018_m_000028_0","type":"MAP","slot":3,"startSecond":16,"endSecond":30},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000029","attemptID":"attempt_1402538726852_0018_m_000029_0","type":"MAP","slot":4,"startSecond":17,"endSecond":29},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000032","attemptID":"attempt_1402538726852_0018_m_000032_0","type":"MAP","slot":5,"startSecond":19,"endSecond":31},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000034","attemptID":"attempt_1402538726852_0018_m_000034_0","type":"MAP","slot":6,"startSecond":20,"endSecond":34},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000036","attemptID":"attempt_1402538726852_0018_m_000036_0","type":"MAP","slot":0,"startSecond":21,"endSecond":34},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000037","attemptID":"attempt_1402538726852_0018_m_000037_0","type":"MAP","slot":7,"startSecond":22,"endSecond":34},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000040","attemptID":"attempt_1402538726852_0018_m_000040_0","type":"MAP","slot":1,"startSecond":24,"endSecond":38},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000047","attemptID":"attempt_1402538726852_0018_m_000047_0","type":"MAP","slot":2,"startSecond":29,"endSecond":42},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000049","attemptID":"attempt_1402538726852_0018_m_000049_0","type":"MAP","slot":3,"startSecond":30,"endSecond":43},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000050","attemptID":"attempt_1402538726852_0018_m_000050_0","type":"MAP","slot":4,"startSecond":31,"endSecond":43},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000052","attemptID":"attempt_1402538726852_0018_m_000052_0","type":"MAP","slot":5,"startSecond":32,"endSecond":46},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000055","attemptID":"attempt_1402538726852_0018_m_000055_0","type":"MAP","slot":0,"startSecond":35,"endSecond":49},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000053","attemptID":"attempt_1402538726852_0018_m_000053_0","type":"MAP","slot":6,"startSecond":35,"endSecond":49},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000056","attemptID":"attempt_1402538726852_0018_m_000056_0","type":"MAP","slot":7,"startSecond":36,"endSecond":50},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000058","attemptID":"attempt_1402538726852_0018_m_000058_0","type":"MAP","slot":1,"startSecond":40,"endSecond":53},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000062","attemptID":"attempt_1402538726852_0018_m_000062_0","type":"MAP","slot":2,"startSecond":44,"endSecond":58},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000065","attemptID":"attempt_1402538726852_0018_m_000065_0","type":"MAP","slot":3,"startSecond":44,"endSecond":58},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000066","attemptID":"attempt_1402538726852_0018_m_000066_0","type":"MAP","slot":4,"startSecond":45,"endSecond":57},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000068","attemptID":"attempt_1402538726852_0018_m_000068_0","type":"MAP","slot":5,"startSecond":47,"endSecond":61},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000070","attemptID":"attempt_1402538726852_0018_m_000070_0","type":"MAP","slot":0,"startSecond":49,"endSecond":64},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000072","attemptID":"attempt_1402538726852_0018_m_000072_0","type":"MAP","slot":6,"startSecond":50,"endSecond":63},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000074","attemptID":"attempt_1402538726852_0018_m_000074_0","type":"MAP","slot":7,"startSecond":51,"endSecond":65},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000079","attemptID":"attempt_1402538726852_0018_m_000079_0","type":"MAP","slot":1,"startSecond":54,"endSecond":68},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000082","attemptID":"attempt_1402538726852_0018_m_000082_0","type":"MAP","slot":3,"startSecond":58,"endSecond":71},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000083","attemptID":"attempt_1402538726852_0018_m_000083_0","type":"MAP","slot":2,"startSecond":59,"endSecond":73},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000085","attemptID":"attempt_1402538726852_0018_m_000085_0","type":"MAP","slot":4,"startSecond":60,"endSecond":73},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000087","attemptID":"attempt_1402538726852_0018_m_000087_0","type":"MAP","slot":5,"startSecond":62,"endSecond":74},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000090","attemptID":"attempt_1402538726852_0018_m_000090_0","type":"MAP","slot":0,"startSecond":64,"endSecond":75},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000091","attemptID":"attempt_1402538726852_0018_m_000091_0","type":"MAP","slot":6,"startSecond":65,"endSecond":76},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000093","attemptID":"attempt_1402538726852_0018_m_000093_0","type":"MAP","slot":7,"startSecond":66,"endSecond":76},{"jobID":"job_1402538726852_0018","taskID":"task_1402538726852_0018_m_000094","attemptID":"attempt_1402538726852_0018_m_000094_0","type":"MAP","slot":1,"startSecond":69,"endSecond":77}]}]};
			//var jobStatistics = {"durationInSecond":55,"nodes":[{"nodeID":"/default-rack/bigant-dev-003:8042","maxParallel":1,"attempts":[{"jobID":"job_1402980614342_0005","taskID":"task_1402980614342_0005_r_000000","attemptID":"attempt_1402980614342_0005_r_000000_0","type":"REDUCE","slot":0,"startSecond":0,"endSecond":3}]},{"nodeID":"/default-rack/bigant-dev-002:8042","maxParallel":1,"attempts":[{"jobID":"job_1402980614342_0004","taskID":"task_1402980614342_0004_m_000000","attemptID":"attempt_1402980614342_0004_m_000000_0","type":"MAP","slot":0,"startSecond":0,"endSecond":4},{"jobID":"job_1402980614342_0004","taskID":"task_1402980614342_0004_r_000000","attemptID":"attempt_1402980614342_0004_r_000000_0","type":"REDUCE","slot":0,"startSecond":6,"endSecond":10}]},{"nodeID":"/default-rack/bigant-dev-004:8042","maxParallel":1,"attempts":[{"jobID":"job_1402980614342_0005","taskID":"task_1402980614342_0005_m_000000","attemptID":"attempt_1402980614342_0005_m_000000_0","type":"MAP","slot":0,"startSecond":46,"endSecond":49}]}]};
			function listJobs(){
				var jobTrackerURL = document.getElementById("jobTrackerURL").value;
				
				d3.json("yarnJobList.do?jobHistoryRoot=" + encodeURIComponent(jobTrackerURL), function(error, json){
					if(error)
						return console.warn(error);
					var jobs = eval(json);
					$("#jobList").css("visibility", "visible");
					var table = $("#jobList").DataTable();
					for(var i in jobs){
						var newRow = table.row.add([jobs[i]]);
						newRow.draw();
					}
					$("#jobList tbody").on("click", "tr", function(){
						$(this).toggleClass("selected");
					});
					$("#illustrationButton").css("visibility", "visible");
				});
			}
			
			function request(){
				var jobTrackerURL = document.getElementById("jobTrackerURL").value;
				var jobs = $("#jobList").DataTable().rows(".selected").data();
				var selectedCount = $("#jobList").DataTable().rows(".selected").data().length;
				var jobList = [];
				for(var i = 0; i < selectedCount; i++){
					jobList.push(jobs[i][0]);
				}
				d3.json("yarnCrawl.do?jobHistoryRoot=" + encodeURIComponent(jobTrackerURL) + "&jobs=" + encodeURIComponent(JSON.stringify(jobList)), function(error, json){
					if(error)
						return console.warn(error);
					var jobStatistics = eval(json);
 					illustrate(jobStatistics);
				});
			}
			
			function illustrate(jobStatistics){
				d3.select("svg").remove();
				var barHeight = 10;
				var barGap = 10;
				var topAndBottomPadding = 0;
				var sidePadding = 250;
				var axisHeight = 20;
				var width = 1000;
				var tableRegionWidth = $("#tableRegion").css("width");
				console.log(tableRegionWidth);
				var slotCounts = countSlots(jobStatistics);
				var height = calculateHeight(slotCounts);
				
				var colorScale = d3.scale.linear()
					.domain([0, Object.keys(slotCounts).length])
					.range(["#00B9FA", "#F95002"])
					.interpolate(d3.interpolateHcl);

				var svg = d3.select("#chartRegion")
					.append("svg")
					.attr("class", "svg")
					.attr("width", width)
					.attr("height", height);
				d3.select("#chartRegion")
					.attr("width", width)
					.attr("height", height);	
				var timeScale = d3.scale.linear()
					.domain([0, jobStatistics.durationInSecond])
					.range([0, width - sidePadding]);

				makeAxis(svg, timeScale, slotCounts);
				drawTasks(jobStatistics, slotCounts, svg, timeScale, colorScale);

				function countSlots(jobStatistics){
					var slotStartHeight = 0;
					var slotCounts = [];
					var nodeInformation = jobStatistics.nodes;
					for(var i in nodeInformation){
						var node = nodeInformation[i];
						var slotCount = node.maxParallel;
						if(slotCount == 0)
							slotCount = 1;
						var slotHeight = slotCount * (barHeight + barGap) + barGap;
						var slot = {"nodeID": node.nodeID, "slotHeight": slotHeight, "slotStartHeight": slotStartHeight};
						slotCounts.push(slot);
						slotStartHeight += slotHeight;
					}
					return slotCounts;
				}

				function calculateHeight(slotCounts){
					var lastIndex = slotCounts.length - 1;
					var lastSlot = slotCounts[lastIndex];
					var height = topAndBottomPadding + lastSlot.slotHeight + lastSlot.slotStartHeight + topAndBottomPadding + axisHeight;
					return height;
				}

				function makeAxis(svg, timeScale, slotCounts){
					drawXAxis(svg, timeScale);
					drawYAxis(svg, slotCounts);
				}

				function drawXAxis(svg, timeScale){
					var xAxis = d3.svg.axis()
						.scale(timeScale)
						.orient('bottom')
						.ticks(10)
						.tickSize(-height + axisHeight + topAndBottomPadding, 0, 0)
						.tickFormat(function(t){
							return t + "s";
						});

					var grid = svg.append('g')
						.attr('class', 'grid')
						.attr('transform', 'translate(' + sidePadding + ', ' + (height - axisHeight- topAndBottomPadding) + ')')
						.call(xAxis)
						.selectAll("text")  
						.style("text-anchor", "middle")
						.attr("fill", "#000")
						.attr("stroke", "none")
						.attr("font-size", 10)
						.attr("dy", "1em");
				}

				function drawYAxis(svg, slotCounts){
					var axisText = svg.append("g")
						.selectAll("text")
						.data(slotCounts)
						.enter()
						.append("text")
						.text(function(s){
							return s.nodeID;
						})
						.attr("x", 10)
						.attr("y", function(s){
							var regionHeight = s.slotHeight;
							var regionStart = s.slotStartHeight;
							return regionStart + regionHeight / 2 + 5;
						})
						.attr("font-size", 11)
						.attr("text-anchor", "start");
				}

				function drawTasks(jobStatistics, slotCounts, svg, timeScale, colorScale){
					drawNodeBars(slotCounts, svg, colorScale);
					var attempts = collectAttempts(jobStatistics);
					drawTaskRectangles(attempts, slotCounts, svg, timeScale);

					function drawNodeBars(slotCounts, svg, colorScale){
						var nodeRects = svg.append("g")
							.selectAll("rect")
							.data(slotCounts)
							.enter()
							.append("rect")
							.attr("x", 0)
							.attr("y", function(s){
								return s.slotStartHeight;
							})
							.attr("width", width)
							.attr("height", function(s){
								return s.slotHeight;
							})
							.attr("stroke", "none")
							.attr("fill", function(s, i){
								return d3.rgb(colorScale(i));
							})
							.attr("opacity", 0.2);
					}

					function collectAttempts(jobStatistics){
						var nodeInformation = jobStatistics.nodes;
						var attempts = [];
						for(var i in nodeInformation){
							var node = nodeInformation[i];
							for(var j in node.attempts){
								attempts.push({nodeID: node.nodeID, attempt: node.attempts[j]});
							}
						}
						return attempts;
					}

					function drawTaskRectangles(attempts, slotCounts, svg, timeScale){
						var machineCount = slotCounts.length;
						var nodeMap = {};
						for(var i in slotCounts){
							var nodeID = slotCounts[i].nodeID;
							nodeMap[nodeID] = slotCounts[i];
						}

						var rectangles = svg.append("g")
							.selectAll("rect")
							.data(attempts)
							.enter();

						var taskRects = rectangles.append("rect")
							.attr("x", function(t){
								return timeScale(t.attempt.startSecond) + sidePadding;
							})
							.attr("y", function(t){
								var nodeID = t.nodeID;
								var slotPosition = nodeMap[nodeID];
								y = slotPosition.slotStartHeight + barGap + t.attempt.slot * (barGap + barHeight);
								return y;
							})
							.attr("rx", 5)
							.attr("ry", 5)
							.attr("height", barHeight)
							.attr("width", function(t){
								return timeScale(t.attempt.endSecond) - timeScale(t.attempt.startSecond);
							})
							.attr("stroke", "none")
							.attr("class", function(t){
								if(t.attempt.type == "MAP"){
									return "mapperBar";
								}else{
									return "reducerBar";
								}
							})
						taskRects.on("mouseover", function(e){
								var task = d3.select(this).data()[0];
								var tag = "";
								for(var key in task.attempt){
									if(key != "slot")
										tag += (key + ": " + task.attempt[key] + "<br/>");
								}
								var output = document.getElementById("tag");
								var x = (parseInt(tableRegionWidth, 10) + this.x.animVal.value + this.width.animVal.value / 2 - 300 / 2 + 80) + "px";
								var y = this.y.animVal.value + topAndBottomPadding + barHeight + 15 + "px";
								output.innerHTML = tag;
								output.style.top = y;
								output.style.left = x;
								output.style.display = "block";
							})
							.on("mouseout", hideTaskDetail);

						var rectText = rectangles.append("text")
							.text(function(t){
								return t.attempt.type;
							})
							.attr("x", function(d){
								return (timeScale(d.attempt.endSecond)-timeScale(d.attempt.startSecond))/2 + timeScale(d.attempt.startSecond) + sidePadding;
							})
							.attr("y", function(t){
								var nodeID = t.nodeID;
								var slotPosition = nodeMap[nodeID];
								y = slotPosition.slotStartHeight + barGap + t.attempt.slot * (barGap + barHeight) + barHeight;
								return y;
							})
							.attr("font-size", barHeight - 2)
							.attr("text-anchor", "middle")
							.attr("text-height", barHeight - 2)
							.attr("fill", "#fff");
						rectText.on("mouseover", function(e){
							var task = d3.select(this).data()[0];
							var tag = "";
							for(var key in task.attempt){
								if(key != "slot")
									tag += (key + ": " + task.attempt[key] + "<br/>");
							}
							var output = document.getElementById("tag");
							var x = parseInt(tableRegionWidth, 10) + this.x.animVal.getItem(this) + "px";
							var y = this.y.animVal.getItem(this) + topAndBottomPadding + barHeight + 15 + "px";
							output.innerHTML = tag;
							output.style.top = y;
							output.style.left = x;
							output.style.display = "block";
						})
						.on("mouseout", hideTaskDetail);
					}

					function hideTaskDetail(){
						var output = document.getElementById("tag");
						output.style.display = "none";
					}
				}
			}

		</script>
	</head>
	<body>
		<div id="tableRegion" style="float:left;width: 300px">
		<label>Job History URL:</label>
			<input type="text" id="jobTrackerURL" value="http://bigant-dev-001:19888"/>
			<input type="button" value="listJobs" onclick="listJobs()" />
			<table id = "jobList" class="display" style="width:300px; visibility: hidden">
				<thead>
					<tr>
						<th>Job ID</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			<input type="button" id="illustrationButton" value="Illustrate" onclick="request()" style="visibility: hidden"/>
		</div>
		<div id="chartRegion" style="float:left"></div>
		<div id="tag"></div>
	</body>
</html>