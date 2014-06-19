<html>
	<head>
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/d3/3.4.8/d3.min.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
		<link rel="stylesheet" type="text/css" href="hadoopgantt.css"/>
		<script type="text/javascript">

			var jobStatistics = {
			    startTime: "2014-06-14 00:00:00",
			    endTime: "2014-06-14 00:05:00",
			    nodes: ["node1", "node2"],
			    mapSlots: 4,
			    reduceSlots: 2,
			    tasks: [
			        {
						type: "MAP",
						taskID: "m_1",
						attemptID: "m_attempt1",
						node: "node1",
						slot: 0,
						startTime: "2014-06-14 00:00:00",
						endTime: "2014-06-14 00:01:00"
			        },
			        {
			            type: "MAP",
			            taskID: "m_2",
			            attemptID: "m_attempt2",
			            node: "node1",
			            slot: 1,
			            startTime: "2014-06-14 00:00:00",
			            endTime: "2014-06-14 00:01:00"
			        },
			        {
			            type: "MAP",
			            taskID: "m_3",
			            attemptID: "m_attempt3",
			            node: "node2",
			            slot: 0,
			            startTime: "2014-06-14 00:00:00",
			            endTime: "2014-06-14 00:01:00"
			        },
			        {
			            type: "MAP",
			            taskID: "m_4",
			            attemptID: "m_attempt4",
			            node: "node2",
			            slot: 1,
			            startTime: "2014-06-14 00:00:00",
			            endTime: "2014-06-14 00:01:00"
			        },
			        {
			            type: "MAP",
			            taskID: "m_5",
			            attemptID: "m_attempt5",
			            node: "node1",
			            slot: 0,
			            startTime: "2014-06-14 00:01:10",
			            endTime: "2014-06-14 00:02:10"
			        },
			        {
			            type: "MAP",
			            taskID: "m_6",
			            attemptID: "m_attempt6",
			            node: "node2",
			            slot: 1,
			            startTime: "2014-06-14 00:01:10",
			            endTime: "2014-06-14 00:02:10"
			        },
			        {
			            type: "REDUCE",
			            taskID: "r_1",
			            attemptID: "r_attempt1",
			            node: "node1",
			            slot: 0,
			            startTime: "2014-06-14 00:02:30",
			            endTime: "2014-06-14 00:04:30"
			        },
			        {
			            type: "REDUCE",
			            taskID: "r_2",
			            attemptID: "r_attempt2",
			            node: "node2",
			            slot: 0,
			            startTime: "2014-06-14 00:02:30",
			            endTime: "2014-06-14 00:04:30"
			        },
			        {
			            type: "REDUCE",
			            taskID: "r_3",
			            attemptID: "r_attempt3",
			            node: "node2",
			            slot: 1,
			            startTime: "2014-06-14 00:02:30",
			            endTime: "2014-06-14 00:04:30"
			        }
			    ]
			};

			function request(){
				var jobTrackerURL = document.getElementById("jobTrackerURL").value;
				var requestedJobID = document.getElementById("requestedJobID").value;
				
				d3.json("hadoopCrawl.do?jobHistoryRoot=" + encodeURIComponent(jobTrackerURL) + "&jobID=" + encodeURIComponent(requestedJobID), function(error, json){
					if(error)
						return console.warn(error);
					var jobStatistics = eval(json);
					console.log(jobStatistics);
					illustrate(jobStatistics);
				});
			}
			
			function illustrate(jobStatistics){	
				d3.select("svg").remove();
				var barHeight = 20;
				var barGap = 4;
				var topAndBottomPadding = 10;
				var sidePadding = 300;
				var axisHeight = 30;
				var width = 1200;
				var height = calculateHeight(jobStatistics);
				var svg = d3.select("#canvas")
			        .append("svg")
			        .attr("class", "svg")
			        .attr("width", width)
			        .attr("height", height);
			    var timeFormat = d3.time.format("%Y-%m-%d %H:%M:%S");
			    var timeScale = d3.time.scale().domain([timeFormat.parse(jobStatistics.startTime),
			                 timeFormat.parse(jobStatistics.endTime)])
			        .range([0,width-150]);

				makeAxis(svg, timeScale, jobStatistics);
				drawTasks(jobStatistics, svg, timeScale, timeFormat);

			   	function calculateHeight(jobStatistics){
			   		var slotCount = jobStatistics.nodes.length * (jobStatistics.mapSlots + jobStatistics.reduceSlots);
			   		var slotsHeight = slotCount * barHeight + (slotCount - 1) * barGap;
			   		return slotsHeight + 2 * topAndBottomPadding + axisHeight;
			   	}

			   	function makeAxis(svg, timeScale, jobStatistics){
			   		drawXAxis(svg, timeScale);
			   		drawYAxis(svg, jobStatistics);
			   	}

			   	function drawXAxis(svg, timeScale){
			   		var xAxis = d3.svg.axis()
			   			.scale(timeScale)
			   			.orient('bottom')
			   			.ticks(10)
			   			.tickSize(-(height - topAndBottomPadding - 20), 0)
			   			.tickFormat(d3.time.format("%H:%M:%S"));

					var grid = svg.append('g')
					    .attr('class', 'grid')
					    .attr('transform', 'translate(' +sidePadding + ', ' + (height - axisHeight - topAndBottomPadding) + ')')
					    .call(xAxis)
					    .selectAll("text")  
						.style("text-anchor", "middle")
					    .attr("fill", "#000")
					    .attr("stroke", "none")
					    .attr("font-size", 10)
					    .attr("dy", "1em");
			   	}

			   	function drawYAxis(svg, jobStatistics){
			   		var slotNames = new Array();
			   		for(var i in jobStatistics.nodes){
			   			for(var j = 0; j < jobStatistics.mapSlots; j++){
			   				slotNames.push(jobStatistics.nodes[i] + ":MapSlot_" + j);
			   			}
			   		}
			   		for(var i in jobStatistics.nodes){
			   			for(var j = 0; j < jobStatistics.reduceSlots; j++){
			   				slotNames.push(jobStatistics.nodes[i] + ":ReduceSlot_" + j);
			   			}
			   		}
			   		console.log(slotNames);

					var axisText = svg.append("g")
						.selectAll("text")
						.data(slotNames)
						.enter()
						.append("text")
						.text(function(d){
							return d;
						})
						.attr("x", 10)
						.attr("y", function(d, i){
							return topAndBottomPadding + (i + 0.5) * (barHeight + barGap) + 3;
						})
						.attr("height", barHeight)
						.attr("font-size", 11)
						.attr("text-anchor", "start");
			   	}

			   	function drawTasks(jobStatistics, svg, timeScale, timeFormat){
			   		var machineCount = jobStatistics.nodes.length;
			   		var totalMapSlots = machineCount * jobStatistics.mapSlots;
			        var rectangles = svg.append("g")
			        	.selectAll("rect")
			        	.data(jobStatistics.tasks)
			        	.enter();

			        var taskRects = rectangles.append("rect")
			        	.attr("x", function(t){
			        		return timeScale(timeFormat.parse(t.startTime)) + sidePadding;
			        	})
			        	.attr("y", function(t){
			        		var slotIndex;
			        		var machineIndex = jobStatistics.nodes.indexOf(t.node);
			        		if(t.type == "MAP"){
								slotIndex = machineIndex * jobStatistics.mapSlots + t.slot;
			        		}else{
			        			slotIndex = machineIndex * jobStatistics.reduceSlots + totalMapSlots + t.slot;
			        		}
			        		y = topAndBottomPadding + slotIndex * (barHeight + barGap);
			        		return y;
			        	})
			        	.attr("height", barHeight)
			        	.attr("width", function(t){
			        		return timeScale(timeFormat.parse(t.endTime)) - timeScale(timeFormat.parse(t.startTime));
			        	})
			        	.attr("stroke", "none")
			        	.attr("class", function(t){
			        		if(t.type == "MAP"){
			        			return "mapperBar";
			        		}else{
			        			return "reducerBar";
			        		}
			        	});

			        var rectText = rectangles.append("text")
               			.text(function(t){
                			return t.attemptID;
               			})
               			.attr("x", function(d){
                		return (timeScale(timeFormat.parse(d.endTime))-timeScale(timeFormat.parse(d.startTime)))/2 + timeScale(timeFormat.parse(d.startTime)) + sidePadding;
                		})
               			.attr("y", function(t){
                  			var slotIndex;
			        		var machineIndex = jobStatistics.nodes.indexOf(t.node);
			        		if(t.type == "MAP"){
								slotIndex = machineIndex * jobStatistics.mapSlots + t.slot;
			        		}else{
			        			slotIndex = machineIndex * jobStatistics.reduceSlots + totalMapSlots + t.slot;
			        		}
			        		y = topAndBottomPadding + slotIndex * (barHeight + barGap);
			        		return y + 14;
              			})
						.attr("font-size", 11)
						.attr("text-anchor", "middle")
						.attr("text-height", barHeight)
						.attr("fill", "#fff");
					rectText.on("mouseover", function(e){
						var task = d3.select(this).data()[0];
						var tag = "";
						for(var key in task){
							tag += (key + ": " + task[key] + "<br/>");
						}
						var output = document.getElementById("tag");
						var x = this.x.animVal.getItem(this) + "px";
						var y = this.y.animVal.getItem(this) + 25 + topAndBottomPadding + 20 + "px";
						output.innerHTML = tag;
						output.style.top = y;
						output.style.left = x;
						output.style.display = "block";
					})
					.on("mouseout", hideTaskDetail);
					taskRects.on("mouseover", function(e){
						var task = d3.select(this).data()[0];
				        var tag = "";
						for(var key in task){
							tag += (key + ": " + task[key] + "<br/>");
						}
						var output = document.getElementById("tag");
						var x = (this.x.animVal.value + this.width.animVal.value/2) + "px";
						var y = this.y.animVal.value + 25 + topAndBottomPadding + 20 + "px";
						output.innerHTML = tag;
						output.style.top = y;
						output.style.left = x;
						output.style.display = "block";
					})
					.on("mouseout", hideTaskDetail);

					function hideTaskDetail(){
						var output = document.getElementById("tag");
						output.style.display = "none";
					}
			   	}
			}

		</script>
	</head>
	<body>
		<label>Job History URL:</label>
		<input type="text" id="jobTrackerURL" />
		<label>Job ID:</label>
		<input type="text" id="requestedJobID" />
		<input type="button" value="Illustrate" onclick="request()"/>
		<div id="canvas"></div>
		<div id="tag"></div>
	</body>
</html>