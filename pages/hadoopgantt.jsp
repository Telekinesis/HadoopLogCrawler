<html>
	<head>
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/d3/3.4.8/d3.min.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
		<link rel="stylesheet" type="text/css" href="hadoopgantt.css"/>
		<script type="text/javascript">

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
				var barHeight = 10;
				var barGap = 10;
				var topAndBottomPadding = 0;
				var sidePadding = 300;
				var axisHeight = 20;
				var width = 1200;
				var height = calculateHeight(jobStatistics);
				var toolBarHeight = 22;
				var svg = d3.select("#canvas")
					.append("svg")
					.attr("class", "svg")
					.attr("width", width)
					.attr("height", height);
				var timeScale = d3.scale.linear()
					.domain([0, jobStatistics.durationInSecond])
					.range([0,width-sidePadding]);

				makeAxis(svg, timeScale, jobStatistics);
				drawTasks(jobStatistics, svg, timeScale);

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
						.tickSize(-height + topAndBottomPadding + axisHeight, 0)
						.tickFormat(function(t){
							return t + "s";
						});

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
							return topAndBottomPadding + (i + 0.5) * (barHeight + barGap);
						})
						.attr("height", barHeight)
						.attr("font-size", 11)
						.attr("text-anchor", "start");
				}

				function drawTasks(jobStatistics, svg, timeScale){
					var machineCount = jobStatistics.nodes.length;
					var totalMapSlots = machineCount * jobStatistics.mapSlots;
					var rectangles = svg.append("g")
					.selectAll("rect")
					.data(jobStatistics.tasks)
					.enter();

					var taskRects = rectangles.append("rect")
					.attr("x", function(t){
						return timeScale(t.startTimeInSecond) + sidePadding;
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
					.attr("rx", 5)
					.attr("ry", 5)
					.attr("height", barHeight)
					.attr("width", function(t){
						return timeScale(t.endTimeInSecond) - timeScale(t.startTimeInSecond);
					})
					.attr("stroke", "none")
					.attr("class", function(t){
						if(t.type == "MAP"){
							return "mapperBar";
						}else{
							return "reducerBar";
						}
					});
					taskRects.on("mouseover", function(e){
						var task = d3.select(this).data()[0];
						var tag = "";
						for(var key in task){
							tag += (key + ": " + task[key] + "<br/>");
						}
						var output = document.getElementById("tag");
						var x = (this.x.animVal.value + this.width.animVal.value/2 - 300 / 2 + 80) + "px";
						var y = this.y.animVal.value + barHeight + topAndBottomPadding + toolBarHeight + 15 + "px";
						output.innerHTML = tag;
						output.style.top = y;
						output.style.left = x;
						output.style.display = "block";
					})
					.on("mouseout", hideTaskDetail);
					
//					var rectText = rectangles.append("text")
//						.text(function(t){
//			 			return t.type;
//						})
//						.attr("x", function(d){
//			 		return (timeScale(d.endTimeInSecond)-timeScale(d.startTimeInSecond))/2 + timeScale(d.startTimeInSecond) + sidePadding;
//			 		})
//						.attr("y", function(t){
//							var slotIndex;
//						var machineIndex = jobStatistics.nodes.indexOf(t.node);
//						if(t.type == "MAP"){
//								slotIndex = machineIndex * jobStatistics.mapSlots + t.slot;
//						}else{
//							slotIndex = machineIndex * jobStatistics.reduceSlots + totalMapSlots + t.slot;
//						}
//						y = slotIndex * (barHeight + barGap) + barHeight;
//						return y;
//						})
//						.attr("font-size", 11)
//						.attr("text-anchor", "middle")
//						.attr("text-height", barHeight)
//						.attr("fill", "#fff");
//					rectText.on("mouseover", function(e){
//						var task = d3.select(this).data()[0];
//						var tag = "";
//						for(var key in task){
//							tag += (key + ": " + task[key] + "<br/>");
//						}
//						var output = document.getElementById("tag");
//						var x = this.x.animVal.getItem(this) + "px";
//						var y = this.y.animVal.getItem(this) + barHeight + topAndBottomPadding + toolBarHeight + 15 + "px";
//						output.innerHTML = tag;
//						output.style.top = y;
//						output.style.left = x;
//						output.style.display = "block";
//					})
//					.on("mouseout", hideTaskDetail);
					
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