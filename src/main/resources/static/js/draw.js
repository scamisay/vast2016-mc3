
    function draw(data, title) {
        var temp = new Array();
        $(eval(data)).map(function(){temp.push([this.date, this.value]); });
        data = temp;

        var numcharts = $('.chartMC3').length;
        var newId = 'chart_'+(numcharts+1);
        $('#chart_container').append('<div id="'+newId+'" class="chartMC3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>');

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
                name: 'USD to EUR',
                data: data
            }]
        });
    }