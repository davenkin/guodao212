$(document).ready(function () {
    setHighChartTheme();
//    loadWeather();
    relocateMainContent();

    $("#hul").mouseenter(function () {
        $(".highcharts-container").css('z-index', -1);

    });

    $("#hul").mouseleave(function () {
        $(".highcharts-container").css('z-index', 0);

    });

    $(document).ajaxStart(function () {
        $("#wait").css("display", "block");
    });
    $(document).ajaxComplete(function () {
        $("#wait").css("display", "none");
    });

    var name = getParameterByName('name');
    var category = getParameterByName('category');

    if (name == "") {
        showBookRank("api/ranks?name=实现领域驱动设计&category=亚马逊全部图书");
    } else {
        showBookRank("api/ranks?name=" + name + "&category=" + category);
    }

    $(window).resize(relocateMainContent);

    $('#binfang').click(function () {
        randomDisplayImages();
        return false;
    });


    $('#rank-all').click(function () {
        showBookRank("api/ranks");
        return false;
    });

    $('#amazon-iddd').click(function () {
        showBookRank("api/ranks?name=实现领域驱动设计&category=亚马逊全部图书");
        return false;
    });

    $('#amazon-all').click(function () {
        showBookRank("api/ranks?category=亚马逊全部图书");
        return false;
    });

    $('#jd-iddd').click(function () {
        showBookRank("api/ranks?name=实现领域驱动设计&category=京东计算机和互联网");
        return false;
    });

    $('#jd-all').click(function () {
        showBookRank("api/ranks?category=京东计算机和互联网");
        return false;
    });

    $('#dangdang-iddd').click(function () {
        showBookRank("api/ranks?name=实现领域驱动设计&category=当当计算机与互联网");
        return false;
    });

    $('#dangdang-all').click(function () {
        showBookRank("api/ranks?category=当当计算机与互联网");
        return false;
    });

});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function showBookRank(uri) {
    $.get(uri, function (ranks, status) {
        showHighcharts(ranks);
    });
}

function showHighcharts(dataSeries) {
    $('#main-content').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: '图书销售排名走势'
        },
        xAxis: {
//            opposite:true,
            type: 'datetime',

            title: {
                text: '时间'
            },

        },

        yAxis: {
            reversed: true,
            title: {
                text: '名次'
            },
            min: 0
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br>',
            pointFormat: '{point.x:%Y-%b-%e-%H:%M}<br>第{point.y}名'
        },

        series: dataSeries
    });

}

function relocateMainContent() {
    var topHeight = document.getElementById("top-wrapper").clientHeight;
    var windowHeight = window.innerHeight

    document.getElementById("main-content-wrapper").style.paddingTop = topHeight + "px";
    document.getElementById("main-content").style.height = (windowHeight - topHeight) + "px";
    document.getElementById("side-bar").style.height = (windowHeight - topHeight) + "px";
}

function randomDisplayImages() {
    $.get("api/randomImages", function (items, status) {
        var mainContent = document.getElementById('main-content');

        mainContent.innerHTML = "";
        for (i = 0; i < items.length; i++) {
            var item = items[i];
            var newImage = '<img class="main-image" src="https://dl.dropboxusercontent.com/u/73613802/images/' + item + '" onclick="randomDisplayImages();"/>';
            mainContent.innerHTML = mainContent.innerHTML + newImage;
        }

        mainContent.innerHTML = mainContent.innerHTML + '<div id="footer">Created by Davenkin.</div>';

    });

}

function loadWeather() {
    var sideBar = document.getElementById('side-bar');
    sideBar.innerHTML = '<iframe id="cd" class="weather" allowtransparency="true" frameborder="0" width="140" height="184" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=1&t=1&v=1&d=2&bd=0&k=&f=&q=0&e=0&a=0&c=56294&w=140&h=184&align=center"></iframe><iframe id="nc" class="weather" allowtransparency="true" frameborder="0" width="140" height="184" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=1&t=1&v=1&d=2&bd=0&k=&f=&q=0&e=0&a=0&c=57411&w=140&h=184&align=center"></iframe><iframe id="hc" class="weather" allowtransparency="true" frameborder="0" width="140" height="184" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=1&t=1&v=1&d=2&bd=0&k=&f=&q=0&e=0&a=0&c=60433&w=140&h=184&align=center"></iframe>'
}

function setHighChartTheme() {

// Load the fonts
    Highcharts.createElement('link', {
        href: 'http://fonts.googleapis.com/css?family=Unica+One',
        rel: 'stylesheet',
        type: 'text/css'
    }, null, document.getElementsByTagName('head')[0]);

    Highcharts.theme = {
        colors: ["#2b908f", "#90ee7e", "#f45b5b", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
            "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
        chart: {
            backgroundColor: {
                linearGradient: {x1: 0, y1: 0, x2: 1, y2: 1},
                stops: [
                    [0, '#2a2a2b'],
                    [1, '#3e3e40']
                ]
            },
            style: {
                fontFamily: "'Unica One', sans-serif"
            },
            plotBorderColor: '#606063'
        },
        title: {
            style: {
                color: '#E0E0E3',
                textTransform: 'uppercase',
                fontSize: '20px'
            }
        },
        subtitle: {
            style: {
                color: '#E0E0E3',
                textTransform: 'uppercase'
            }
        },
        xAxis: {
            gridLineColor: '#707073',
            labels: {
                style: {
                    color: '#E0E0E3'
                }
            },
            lineColor: '#707073',
            minorGridLineColor: '#505053',
            tickColor: '#707073',
            title: {
                style: {
                    color: '#A0A0A3'

                }
            }
        },
        yAxis: {
            gridLineColor: '#707073',
            labels: {
                style: {
                    color: '#E0E0E3'
                }
            },
            lineColor: '#707073',
            minorGridLineColor: '#505053',
            tickColor: '#707073',
            tickWidth: 1,
            title: {
                style: {
                    color: '#A0A0A3'
                }
            }
        },
        tooltip: {
            backgroundColor: 'rgba(0, 0, 0, 0.85)',
            style: {
                color: '#F0F0F0'
            }
        },
        plotOptions: {
            series: {
                dataLabels: {
                    color: '#B0B0B3'
                },
                marker: {
                    lineColor: '#333'
                }
            },
            boxplot: {
                fillColor: '#505053'
            },
            candlestick: {
                lineColor: 'white'
            },
            errorbar: {
                color: 'white'
            }
        },
        legend: {
            itemStyle: {
                color: '#E0E0E3'
            },
            itemHoverStyle: {
                color: '#FFF'
            },
            itemHiddenStyle: {
                color: '#606063'
            }
        },
        credits: {
            style: {
                color: '#666'
            }
        },
        labels: {
            style: {
                color: '#707073'
            }
        },

        drilldown: {
            activeAxisLabelStyle: {
                color: '#F0F0F3'
            },
            activeDataLabelStyle: {
                color: '#F0F0F3'
            }
        },

        navigation: {
            buttonOptions: {
                symbolStroke: '#DDDDDD',
                theme: {
                    fill: '#505053'
                }
            }
        },

        // scroll charts
        rangeSelector: {
            buttonTheme: {
                fill: '#505053',
                stroke: '#000000',
                style: {
                    color: '#CCC'
                },
                states: {
                    hover: {
                        fill: '#707073',
                        stroke: '#000000',
                        style: {
                            color: 'white'
                        }
                    },
                    select: {
                        fill: '#000003',
                        stroke: '#000000',
                        style: {
                            color: 'white'
                        }
                    }
                }
            },
            inputBoxBorderColor: '#505053',
            inputStyle: {
                backgroundColor: '#333',
                color: 'silver'
            },
            labelStyle: {
                color: 'silver'
            }
        },

        navigator: {
            handles: {
                backgroundColor: '#666',
                borderColor: '#AAA'
            },
            outlineColor: '#CCC',
            maskFill: 'rgba(255,255,255,0.1)',
            series: {
                color: '#7798BF',
                lineColor: '#A6C7ED'
            },
            xAxis: {
                gridLineColor: '#505053'
            }
        },

        scrollbar: {
            barBackgroundColor: '#808083',
            barBorderColor: '#808083',
            buttonArrowColor: '#CCC',
            buttonBackgroundColor: '#606063',
            buttonBorderColor: '#606063',
            rifleColor: '#FFF',
            trackBackgroundColor: '#404043',
            trackBorderColor: '#404043'
        },

        // special colors for some of the
        legendBackgroundColor: 'rgba(0, 0, 0, 0.5)',
        background2: '#505053',
        dataLabelsColor: '#B0B0B3',
        textColor: '#C0C0C0',
        contrastTextColor: '#F0F0F3',
        maskColor: 'rgba(255,255,255,0.3)'
    };

// Apply the theme
    Highcharts.setOptions(Highcharts.theme);
}




