<html>
	<head>
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/d3/3.4.8/d3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="yarngantt.css"/>
		<script type="text/javascript">

			//var jobStatistics = {"startTime":"2014-06-12 10:07:16.352","endTime":"2014-06-12 10:15:50.285","nodes":[{"nodeID":"/default-rack/bigant-dev-003:8042","maxParallel":8,"attempts":[{"attemptID":"attempt_1402538726852_0001_m_000002_0","slot":0,"startTime":"2014-06-12 10:07:16.619","endTime":"2014-06-12 10:11:45.222"},{"attemptID":"attempt_1402538726852_0001_m_000005_0","slot":1,"startTime":"2014-06-12 10:07:17.146","endTime":"2014-06-12 10:11:47.127"},{"attemptID":"attempt_1402538726852_0001_m_000008_0","slot":2,"startTime":"2014-06-12 10:07:18.158","endTime":"2014-06-12 10:11:34.571"},{"attemptID":"attempt_1402538726852_0001_m_000011_0","slot":3,"startTime":"2014-06-12 10:07:19.253","endTime":"2014-06-12 10:11:41.509"},{"attemptID":"attempt_1402538726852_0001_m_000014_0","slot":4,"startTime":"2014-06-12 10:07:20.268","endTime":"2014-06-12 10:12:30.737"},{"attemptID":"attempt_1402538726852_0001_m_000017_0","slot":5,"startTime":"2014-06-12 10:07:21.312","endTime":"2014-06-12 10:13:04.893"},{"attemptID":"attempt_1402538726852_0001_m_000020_0","slot":6,"startTime":"2014-06-12 10:07:22.301","endTime":"2014-06-12 10:12:06.015"},{"attemptID":"attempt_1402538726852_0001_m_000022_0","slot":7,"startTime":"2014-06-12 10:07:23.338","endTime":"2014-06-12 10:12:41.236"},{"attemptID":"attempt_1402538726852_0001_m_000023_0","slot":2,"startTime":"2014-06-12 10:11:35.984","endTime":"2014-06-12 10:15:21.206"},{"attemptID":"attempt_1402538726852_0001_m_000024_0","slot":3,"startTime":"2014-06-12 10:11:43.022","endTime":"2014-06-12 10:15:17.826"},{"attemptID":"attempt_1402538726852_0001_m_000025_0","slot":0,"startTime":"2014-06-12 10:11:47.133","endTime":"2014-06-12 10:15:19.148"},{"attemptID":"attempt_1402538726852_0001_m_000026_0","slot":1,"startTime":"2014-06-12 10:11:49.07","endTime":"2014-06-12 10:15:22.859"},{"attemptID":"attempt_1402538726852_0001_m_000027_0","slot":6,"startTime":"2014-06-12 10:12:07.195","endTime":"2014-06-12 10:15:22.816"},{"attemptID":"attempt_1402538726852_0001_m_000028_0","slot":4,"startTime":"2014-06-12 10:12:32.323","endTime":"2014-06-12 10:15:22.998"},{"attemptID":"attempt_1402538726852_0001_m_000029_0","slot":7,"startTime":"2014-06-12 10:12:42.39","endTime":"2014-06-12 10:15:27.27"},{"attemptID":"attempt_1402538726852_0001_m_000001_1","slot":5,"startTime":"2014-06-12 10:13:06.51","endTime":"2014-06-12 10:14:03.41"},{"attemptID":"attempt_1402538726852_0001_m_000010_1","slot":5,"startTime":"2014-06-12 10:14:30.123","endTime":"2014-06-12 10:14:36.002"},{"attemptID":"attempt_1402538726852_0001_m_000012_1","slot":5,"startTime":"2014-06-12 10:14:45.202","endTime":"2014-06-12 10:14:45.664"}]},{"nodeID":"/default-rack/bigant-dev-002:8042","maxParallel":8,"attempts":[{"attemptID":"attempt_1402538726852_0001_m_000000_0","slot":0,"startTime":"2014-06-12 10:07:16.656","endTime":"2014-06-12 10:13:27.804"},{"attemptID":"attempt_1402538726852_0001_m_000003_0","slot":1,"startTime":"2014-06-12 10:07:17.145","endTime":"2014-06-12 10:13:51.253"},{"attemptID":"attempt_1402538726852_0001_m_000007_0","slot":2,"startTime":"2014-06-12 10:07:18.168","endTime":"2014-06-12 10:14:14.784"},{"attemptID":"attempt_1402538726852_0001_m_000010_0","slot":3,"startTime":"2014-06-12 10:07:19.245","endTime":"2014-06-12 10:14:35.931"},{"attemptID":"attempt_1402538726852_0001_m_000013_0","slot":4,"startTime":"2014-06-12 10:07:20.261","endTime":"2014-06-12 10:14:18.688"},{"attemptID":"attempt_1402538726852_0001_m_000016_0","slot":5,"startTime":"2014-06-12 10:07:21.313","endTime":"2014-06-12 10:14:40.492"},{"attemptID":"attempt_1402538726852_0001_m_000019_0","slot":6,"startTime":"2014-06-12 10:07:22.289","endTime":"2014-06-12 10:13:57.47"},{"attemptID":"attempt_1402538726852_0001_m_000021_0","slot":7,"startTime":"2014-06-12 10:07:23.332","endTime":"2014-06-12 10:14:17.209"},{"attemptID":"attempt_1402538726852_0001_m_000003_1","slot":0,"startTime":"2014-06-12 10:13:30.48","endTime":"2014-06-12 10:13:51.315"},{"attemptID":"attempt_1402538726852_0001_m_000007_1","slot":0,"startTime":"2014-06-12 10:13:54.33","endTime":"2014-06-12 10:14:14.855"},{"attemptID":"attempt_1402538726852_0001_m_000004_1","slot":1,"startTime":"2014-06-12 10:13:59.93","endTime":"2014-06-12 10:14:43.873"}]},{"nodeID":"/bigant-dev-002:8042","maxParallel":0,"attempts":[]},{"nodeID":"/default-rack/bigant-dev-004:8042","maxParallel":7,"attempts":[{"attemptID":"attempt_1402538726852_0001_m_000001_0","slot":0,"startTime":"2014-06-12 10:07:16.352","endTime":"2014-06-12 10:14:03.329"},{"attemptID":"attempt_1402538726852_0001_m_000004_0","slot":1,"startTime":"2014-06-12 10:07:17.144","endTime":"2014-06-12 10:14:43.862"},{"attemptID":"attempt_1402538726852_0001_m_000006_0","slot":2,"startTime":"2014-06-12 10:07:18.157","endTime":"2014-06-12 10:14:39.711"},{"attemptID":"attempt_1402538726852_0001_m_000009_0","slot":3,"startTime":"2014-06-12 10:07:19.272","endTime":"2014-06-12 10:14:31.567"},{"attemptID":"attempt_1402538726852_0001_m_000012_0","slot":4,"startTime":"2014-06-12 10:07:20.258","endTime":"2014-06-12 10:14:45.652"},{"attemptID":"attempt_1402538726852_0001_m_000015_0","slot":5,"startTime":"2014-06-12 10:07:21.281","endTime":"2014-06-12 10:14:40.191"},{"attemptID":"attempt_1402538726852_0001_m_000018_0","slot":6,"startTime":"2014-06-12 10:07:22.287","endTime":"2014-06-12 10:14:33.996"},{"attemptID":"attempt_1402538726852_0001_m_000006_1","slot":0,"startTime":"2014-06-12 10:14:15.036","endTime":"2014-06-12 10:14:39.719"},{"attemptID":"attempt_1402538726852_0001_m_000023_1","slot":0,"startTime":"2014-06-12 10:14:59.302","endTime":"2014-06-12 10:15:50.285"},{"attemptID":"attempt_1402538726852_0001_m_000024_1","slot":1,"startTime":"2014-06-12 10:15:14.382","endTime":"2014-06-12 10:15:17.838"}]}]};

			function request(){
				var jobTrackerURL = document.getElementById("jobTrackerURL").value;
				var requestedJobID = document.getElementById("requestedJobID").value;
				
				d3.json("yarnCrawl.do?jobHistoryRoot=" + encodeURIComponent(jobTrackerURL) + "&jobID=" + encodeURIComponent(requestedJobID), function(error, json){
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
				var topAndBottomPadding = 20;
				var sidePadding = 200;
				var axisHeight = 20;
				var width = 1000;
				var slotCounts = countSlots(jobStatistics);
				var height = calculateHeight(slotCounts);
				
				var colorScale = d3.scale.linear()
					.domain([0, Object.keys(slotCounts).length])
					.range(["#00B9FA", "#F95002"])
					.interpolate(d3.interpolateHcl);

				var svg = d3.select("#canvas")
					.append("svg")
					.attr("class", "svg")
					.attr("width", width)
					.attr("height", height);
				
				var timeFormat = d3.time.format("%Y-%m-%d %H:%M:%S.%L");
				var timeScale = d3.time.scale()
					.domain([timeFormat.parse(jobStatistics.startTime), timeFormat.parse(jobStatistics.endTime)])
					.range([0, width - sidePadding]);

				makeAxis(svg, slotCounts);
				drawTasks(jobStatistics, slotCounts, svg, timeScale, timeFormat, colorScale);

				function countSlots(jobStatistics){
					var slotStartIndex = 0;
					var slotCounts = {};
					var nodeInformation = jobStatistics.nodes;
					for(var i in nodeInformation){
						var node = nodeInformation[i];
						var slotCount = node.maxParallel;
						if(slotCount == 0)
							slotCount = 1;
						slotCounts[node.nodeID] = {"slotCount": slotCount, "startIndex": slotStartIndex};
						slotStartIndex += slotCount;
					}
					return slotCounts;
				}

				function calculateHeight(slotCounts){
					var height = 0;
					height += topAndBottomPadding;
					for(var nodeID in slotCounts){
						var slotCount = slotCounts[nodeID].slotCount;
						height += ((barHeight + barGap) * slotCount);
					}
					height += topAndBottomPadding;
					return height;
				}

				function makeAxis(svg, slotCounts){
					drawXAxis(svg);
					drawYAxis(svg, slotCounts);
				}

				function drawXAxis(svg){
					var xAxis = d3.svg.axis()
						.scale(timeScale)
						.orient('bottom')
						.ticks(d3.time.seconds, 30)
						.tickSize(-height + topAndBottomPadding + axisHeight, 0, 0)
						.tickFormat(d3.time.format("%H:%M"));

					var grid = svg.append('g')
						.attr('class', 'grid')
						.attr('transform', 'translate(' + sidePadding + ', ' + (height - axisHeight - topAndBottomPadding) + ')')
						.call(xAxis)
						.selectAll("text")  
						.style("text-anchor", "middle")
						.attr("fill", "#000")
						.attr("stroke", "none")
						.attr("font-size", 10)
						.attr("dy", "1em");
				}

				function drawYAxis(svg, slotCounts){
					var nodeData = [];
					for(var nodeID in slotCounts){
						nodeData.push({"id" : nodeID, "slotCount": slotCounts[nodeID].slotCount, "startIndex": slotCounts[nodeID].startIndex});
					}

					var axisText = svg.append("g")
						.selectAll("text")
						.data(nodeData)
						.enter()
						.append("text")
						.text(function(node){
							return node.id;
						})
						.attr("x", 10)
						.attr("y", function(d){
							var regionHeight = d.slotCount * (barGap + barHeight);
							var regionStart = d.startIndex * (barGap + barHeight);
							return regionStart + regionHeight / 2 + 3;
						})
						.attr("font-size", 11)
						.attr("text-anchor", "start");
				}

				function drawTasks(jobStatistics, slotCounts, svg, timeScale, timeFormat, colorScale){
					drawNodeBars(slotCounts, svg, colorScale);
					var attempts = collectAttempts(jobStatistics);
					drawTaskRectangles(attempts, slotCounts, svg, timeScale, timeFormat);

					function drawNodeBars(slotCounts, svg, colorScale){
						var nodeData = [];
						for(var nodeID in slotCounts){
							nodeData.push({"id" : nodeID, "slotCount": slotCounts[nodeID].slotCount, "startIndex": slotCounts[nodeID].startIndex});
						}
						var nodeRects = svg.append("g")
							.selectAll("rect")
							.data(nodeData)
							.enter()
							.append("rect")
							.attr("x", 0)
							.attr("y", function(d){
								return d.startIndex * (barGap + barHeight);
							})
							.attr("width", width)
							.attr("height", function(d){
								return d.slotCount * (barGap + barHeight);
							})
							.attr("stroke", "none")
							.attr("fill", function(d, i){
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

					function drawTaskRectangles(attempts, slotCounts, svg, timeScale, timeFormat){
						var machineCount = Object.keys(slotCounts).length;
						var rectangles = svg.append("g")
							.selectAll("rect")
							.data(attempts)
							.enter();

						var taskRects = rectangles.append("rect")
							.attr("x", function(t){
								return timeScale(timeFormat.parse(t.attempt.startTime)) + sidePadding + 21;
							})
							.attr("y", function(t){
								var nodeID = t.nodeID;
								var slotIndex = slotCounts[nodeID].startIndex + t.attempt.slot;
								y = slotIndex * (barHeight + barGap);
								return y;
							})
							.attr("height", barHeight)
							.attr("width", function(t){
								/*
								console.log(timeScale);
								console.log(t.attempt);
								console.log(timeScale(timeFormat.parse(t.attempt.endTime)), timeScale(timeFormat.parse(t.attempt.startTime)));
								console.log(timeScale(timeFormat.parse(t.attempt.endTime)) - timeScale(timeFormat.parse(t.attempt.startTime)));
								*/
								return timeScale(timeFormat.parse(t.attempt.endTime)) - timeScale(timeFormat.parse(t.attempt.startTime));
							})
							.attr("stroke", "none")
							.attr("class", function(t){
								return "mapperBar";
								/*
								if(t.type == "MAP"){
									return "mapperBar";
								}else{
									return "reducerBar";
								}
								*/
							});

						// var rectText = rectangles.append("text")
						// 	.text(function(t){
						// 		return t.attempt.attemptID;
						// 	})
						// 	.attr("x", function(d){
						// 		return (timeScale(timeFormat.parse(d.attempt.endTime))-timeScale(timeFormat.parse(d.attempt.startTime)))/2 + timeScale(timeFormat.parse(d.attempt.startTime)) + sidePadding;
						// 	})
						// 	.attr("y", function(t){
						// 		var nodeID = t.nodeID;
						// 		var slotIndex = slotCounts[nodeID].startIndex + t.attempt.slot;
						// 		y = slotIndex * (barHeight + barGap);
						// 		return y;
						// 	})
						// 	.attr("font-size", 11)
						// 	.attr("text-anchor", "middle")
						// 	.attr("text-height", barHeight)
						// 	.attr("fill", "#fff");
						// rectText.on("mouseover", function(e){
						// 	var task = d3.select(this).data()[0];
						// 	var tag = "";
						// 	for(var key in task){
						// 		tag += (key + ": " + task[key] + "<br/>");
						// 	}
						// 	var output = document.getElementById("tag");
						// 	var x = this.x.animVal.getItem(this) + "px";
						// 	var y = this.y.animVal.getItem(this) + 25 + topAndBottomPadding + 20 + "px";
						// 	output.innerHTML = tag;
						// 	output.style.top = y;
						// 	output.style.left = x;
						// 	output.style.display = "block";
						// })
						// .on("mouseout", hideTaskDetail);
						taskRects.on("mouseover", function(e){
							var task = d3.select(this).data()[0];
							var tag = "";
							for(var key in task.attempt){
								if(key != "slot")
									tag += (key + ": " + task.attempt[key] + "<br/>");
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
		<label>Job History URL:</label>
		<input type="text" id="jobTrackerURL" />
		<label>Job ID:</label>
		<input type="text" id="requestedJobID" />
		<input type="button" value="Illustrate" onclick="request()"/>
		<div id="canvas"></div>
		<div id="tag"></div>
	</body>
</html>