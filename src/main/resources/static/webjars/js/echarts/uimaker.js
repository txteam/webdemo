(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['exports', 'echarts'], factory);
    } else if (typeof exports === 'object' && typeof exports.nodeName !== 'string') {
        // CommonJS
        factory(exports, require('echarts'));
    } else {
        // Browser globals
        factory({}, root.echarts);
    }
}(this, function (exports, echarts) {
    var log = function (msg) {
        if (typeof console !== 'undefined') {
            console && console.error && console.error(msg);
        }
    };
    if (!echarts) {
        log('ECharts is not Loaded');
        return;
    }
    echarts.registerTheme('uimaker', {
        "color": [
            "#0076c8",
            "#d63737",
            "#6f61ae",
            "#008b72",
            "#f27501",
            "#35486b"
        ],
        "backgroundColor": "rgba(255,255,255,1)",
        "textStyle": {},
        "title": {
            "textStyle": {
                "color": "#000000"
            },
            "subtextStyle": {
                "color": "#4d5c7a"
            }
        },
        "line": {
            "itemStyle": {
                "normal": {
                    "borderWidth": "2"
                }
            },
            "lineStyle": {
                "normal": {
                    "width": "2"
                }
            },
            "symbolSize": "6",
            "symbol": "emptyCircle",
            "smooth": true
        },
        "radar": {
            "itemStyle": {
                "normal": {
                    "borderWidth": "2"
                }
            },
            "lineStyle": {
                "normal": {
                    "width": "2"
                }
            },
            "symbolSize": "6",
            "symbol": "emptyCircle",
            "smooth": true
        },
        "bar": {
            "itemStyle": {
                "normal": {
                    "barBorderWidth": 0,
                    "barBorderColor": "#ccc"
                },
                "emphasis": {
                    "barBorderWidth": 0,
                    "barBorderColor": "#ccc"
                }
            }
        },
        "pie": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "scatter": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "boxplot": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "parallel": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "sankey": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "funnel": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "gauge": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                },
                "emphasis": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            }
        },
        "candlestick": {
            "itemStyle": {
                "normal": {
                    "color": "#d63737",
                    "color0": "transparent",
                    "borderColor": "#d63737",
                    "borderColor0": "#0076c8",
                    "borderWidth": "1"
                }
            }
        },
        "graph": {
            "itemStyle": {
                "normal": {
                    "borderWidth": 0,
                    "borderColor": "#ccc"
                }
            },
            "lineStyle": {
                "normal": {
                    "width": 1,
                    "color": "#37496e"
                }
            },
            "symbolSize": "6",
            "symbol": "emptyCircle",
            "smooth": true,
            "color": [
                "#0076c8",
                "#d63737",
                "#6f61ae",
                "#008b72",
                "#f27501",
                "#35486b"
            ],
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#ffffff"
                    }
                }
            }
        },
        "map": {
            "itemStyle": {
                "normal": {
                    "areaColor": "#e3e3e3",
                    "borderColor": "#252e3f",
                    "borderWidth": 0.5
                },
                "emphasis": {
                    "areaColor": "rgba(0,131,204,1)",
                    "borderColor": "#516b91",
                    "borderWidth": "0"
                }
            },
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#000000"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "rgb(255,255,255)"
                    }
                }
            }
        },
        "geo": {
            "itemStyle": {
                "normal": {
                    "areaColor": "#e3e3e3",
                    "borderColor": "#252e3f",
                    "borderWidth": 0.5
                },
                "emphasis": {
                    "areaColor": "rgba(0,131,204,1)",
                    "borderColor": "#516b91",
                    "borderWidth": "0"
                }
            },
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#000000"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "rgb(255,255,255)"
                    }
                }
            }
        },
        "categoryAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#c4c4c4"
                }
            },
            "axisTick": {
                "show": true,
                "lineStyle": {
                    "color": "#5e5e5e"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#000000"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#bab4b4"
                    ]
                }
            },
            "splitArea": {
                "show": true,
                "areaStyle": {
                    "color": [
                        "rgba(255,255,255,0.05)",
                        "rgba(0,0,0,0.02)"
                    ]
                }
            }
        },
        "valueAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#c4c4c4"
                }
            },
            "axisTick": {
                "show": true,
                "lineStyle": {
                    "color": "#5e5e5e"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#000000"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#bab4b4"
                    ]
                }
            },
            "splitArea": {
                "show": true,
                "areaStyle": {
                    "color": [
                        "rgba(255,255,255,0.05)",
                        "rgba(0,0,0,0.02)"
                    ]
                }
            }
        },
        "logAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#c4c4c4"
                }
            },
            "axisTick": {
                "show": true,
                "lineStyle": {
                    "color": "#5e5e5e"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#000000"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#bab4b4"
                    ]
                }
            },
            "splitArea": {
                "show": true,
                "areaStyle": {
                    "color": [
                        "rgba(255,255,255,0.05)",
                        "rgba(0,0,0,0.02)"
                    ]
                }
            }
        },
        "timeAxis": {
            "axisLine": {
                "show": true,
                "lineStyle": {
                    "color": "#c4c4c4"
                }
            },
            "axisTick": {
                "show": true,
                "lineStyle": {
                    "color": "#5e5e5e"
                }
            },
            "axisLabel": {
                "show": true,
                "textStyle": {
                    "color": "#000000"
                }
            },
            "splitLine": {
                "show": true,
                "lineStyle": {
                    "color": [
                        "#bab4b4"
                    ]
                }
            },
            "splitArea": {
                "show": true,
                "areaStyle": {
                    "color": [
                        "rgba(255,255,255,0.05)",
                        "rgba(0,0,0,0.02)"
                    ]
                }
            }
        },
        "toolbox": {
            "iconStyle": {
                "normal": {
                    "borderColor": "#455b7d"
                },
                "emphasis": {
                    "borderColor": "#0594f7"
                }
            }
        },
        "legend": {
            "textStyle": {
                "color": "#566b86"
            }
        },
        "tooltip": {
            "axisPointer": {
                "lineStyle": {
                    "color": "#0594f7",
                    "width": "1"
                },
                "crossStyle": {
                    "color": "#0594f7",
                    "width": "1"
                }
            }
        },
        "timeline": {
            "lineStyle": {
                "color": "#2c3950",
                "width": "1"
            },
            "itemStyle": {
                "normal": {
                    "color": "#0076c8",
                    "borderWidth": "0"
                },
                "emphasis": {
                    "color": "#0076c8"
                }
            },
            "controlStyle": {
                "normal": {
                    "color": "#0076c8",
                    "borderColor": "#0076c8",
                    "borderWidth": "0"
                },
                "emphasis": {
                    "color": "#0076c8",
                    "borderColor": "#0076c8",
                    "borderWidth": "0"
                }
            },
            "checkpointStyle": {
                "color": "#0076c8",
                "borderColor": "rgba(29,115,199,0.37)"
            },
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#566b86"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "#566b86"
                    }
                }
            }
        },
        "visualMap": {
            "color": [
                "#2c3950",
                "#2c3950",
                "#0076c8"
            ]
        },
        "dataZoom": {
            "backgroundColor": "rgba(0,0,0,0)",
            "dataBackgroundColor": "rgba(78,100,125,0.3)",
            "fillerColor": "rgba(54,77,112,0.4)",
            "handleColor": "#0594f7",
            "handleSize": "100%",
            "textStyle": {
                "color": "#566b86"
            }
        },
        "markPoint": {
            "label": {
                "normal": {
                    "textStyle": {
                        "color": "#ffffff"
                    }
                },
                "emphasis": {
                    "textStyle": {
                        "color": "#ffffff"
                    }
                }
            }
        }
    });
}));
