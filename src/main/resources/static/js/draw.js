
    function draw(data, title, containerId) {
        var temp = new Array();
        $(eval(data)).map(function(){temp.push([this.date, this.value]); });
        data = temp;

        var numcharts = $('.chartMC3').length;
        var newId = 'chart_'+(numcharts+1);
        $(containerId).append('<div id="'+newId+'" class="chartMC3" style="float: left; width: 60%; height: 400px; margin: 0;"></div>');

        $('#'+newId).highcharts({
            chart: {
                zoomType: 'x'
            },
            title: {
                text: title
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {

            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },

            series: [{
                type: 'area',
                data: data
            }]
        });
    }


    function drawHistogram(rawData, title, containerRef){
        var maxValue = 0;
        eval(rawData).forEach(
            function(d){
                if(d.value > maxValue){
                    maxValue= d.value;
                }
            }
        );

        var data = new Array();
        eval(rawData).forEach(
            function(d){
                data.push(d.value/maxValue);
            }
        );

        var formatCount = d3.format(",.0f");

        var margin = {top: 10, right: 30, bottom: 30, left: 30},
            width = 400 - margin.left - margin.right,
            height = 250 - margin.top - margin.bottom;

        var x = d3.scaleLinear()
            .rangeRound([0, width]);

        var bins = d3.histogram()
            .domain(x.domain())
            .thresholds(x.ticks(20))
            (data);

        var y = d3.scaleLinear()
            .domain([0, d3.max(bins, function(d) { return d.length; })])
            .range([height, 0]);

        var div = d3.select(containerRef).append("div")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .attr("style", "float:left; margin-top: 70px;");

        var svg = div.append("svg")
            .attr("width", width + margin.left + margin.right)
            .attr("height", height + margin.top + margin.bottom)
            .append("g")
            .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        var bar = svg.selectAll(".bar")
            .data(bins)
            .enter().append("g")
            .attr("class", "bar")
            .attr("transform", function(d) { return "translate(" + x(d.x0) + "," + y(d.length) + ")"; });

        bar.append("rect")
            .attr("x", 1)
            .attr("width", x(bins[0].x1) - x(bins[0].x0) - 1)
            .attr("height", function(d) { return height - y(d.length); });

        bar.append("text")
            .attr("dy", ".75em")
            .attr("y", 6)
            .attr("x", (x(bins[0].x1) - x(bins[0].x0)) / 2)
            .attr("text-anchor", "middle")
            .text(function(d) { return formatCount(d.length); });

        svg.append("g")
            .attr("class", "axis axis--x")
            .attr("transform", "translate(0," + height + ")")
            .call(d3.axisBottom(x));

    }
