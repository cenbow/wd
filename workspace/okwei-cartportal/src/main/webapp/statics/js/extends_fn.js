plog = function(msg) {
    if (window.console && console.log) {
        console.log(msg);
    }
};
$(function() {
    part = $(document.body);
    win = $(window);
    window.navTab = $("#navTab");
    current = {};
    histy = new HistoryManage();
});

function initDomEvent() {
    plog("initDomEvent");
    $("#startDate").datepicker({
        defaultDate: "+1w",
        //numberOfMonths: 3,
        onClose: function(selectedDate) {
            $("#endDate").datepicker("option", "minDate", selectedDate);
        }
    });

    $("#endDate").datepicker({
        defaultDate: "+1w",
        //numberOfMonths: 3,
        onClose: function(selectedDate) {
            $("#startDate").datepicker("option", "maxDate", selectedDate);
        }
    });
    $(".hasDatepicker,.date").attr("readonly", "readonly");
}
var r20 = /%20/g,
    rsubmitterTypes = /^(?:submit|button|image|reset|file)$/i,
    manipulation_rcheckableType = /^(?:checkbox|radio)$/i,
    rbracket = /\[\]$/,
    rCRLF = /\r?\n/g,
    rsubmittable = /^(?:input|select|textarea|keygen)/i;
jQuery.paramNotN = function(a, traditional) {
    var prefix,

        s = [],
        add = function(key, value) {
            // If value is a function, invoke it and return its value
            value = jQuery.isFunction(value) ? value() : (value == null ? "" : value);
            s[s.length] = encodeURIComponent(key) + "=" + encodeURIComponent(value);
        };

    function buildParams(prefix, obj, traditional, add) {
        var name;

        if (jQuery.isArray(obj)) {
            // Serialize array item.
            jQuery.each(obj, function(i, v) {
                if (traditional || rbracket.test(prefix)) {
                    // Treat each array item as a scalar.
                    add(prefix, v);

                } else {
                    // Item is non-scalar (array or object), encode its numeric index.
                    buildParams(prefix + "[" + (typeof v === "object" ? i : "") + "]", v, traditional, add);
                }
            });

        } else if (!traditional && jQuery.type(obj) === "object") {
            // Serialize object item.
            for (name in obj) {
                buildParams(prefix + "[" + name + "]", obj[name], traditional, add);
            }

        } else {
            // Serialize scalar item.
            add(prefix, obj);
        }
    }

    // Set traditional to true for jQuery <= 1.3.2 behavior.
    if (traditional === undefined) {
        traditional = jQuery.ajaxSettings && jQuery.ajaxSettings.traditional;
    }

    // If an array was passed in, assume that it is an array of form elements.
    if (jQuery.isArray(a) || (a.jquery && !jQuery.isPlainObject(a))) {
        // Serialize the form elements
        jQuery.each(a, function() {
            add(this.name, this.value);
        });

    } else {
        // If traditional, encode the "old" way (the way 1.3.2 or older
        // did it), otherwise encode params recursively.
        for (prefix in a) {
            buildParams(prefix, a[prefix], traditional, add);
        }
    }

    // Return the resulting serialization
    return s.join("&").replace(r20, "+");
};
/*jQuery 继承的方法 */
$.fn.extend({
    setDefaultText: function(msg) {
        this.attr("placeholder", msg);
        if (isSupportPlaceholder) {
            return this;
        } //如果支持html5 的placeholder属性则不做后面的操作
        if (this.val) {
            this.val(msg);
        } else {
            return this;
        }
        this.css({
            opacity: 0.6
        });
        this.die('focusin').live('focusin', function() {
            if (this.val() == msg) {
                this.val('').css({
                    opacity: 1.0
                });
            }
        }).die('focusout').live('focusout', function() {
            if (this.val() == '' || this.val() == msg) {
                this.val(msg).css({
                    opacity: 0.6
                });
            }
        });
        this.die('keyup').live('keyup', function() {
            if (this.val() == msg) {
                this.css({
                    opacity: 0.6
                });
            } else {
                this.css({
                    opacity: 1.0
                });
            }
        })
        return this;
    },
    /* 序列化为表单对象 */
    serializeJson: function() {
        var serializeObj = {};
        $(this.serializeArray()).each(function() {
            if (this.value && this.value.length)
                serializeObj[this.name] = this.value;
        });
        return serializeObj;
    },
    serializeNotN: function() {
        return jQuery.paramNotN(this.serializeArrayNotN());
    },
    serializeArrayNotN: function() {
        return this.map(function() {
                // Can add propHook for "elements" to filter or add form elements
                var elements = jQuery.prop(this, "elements");
                return elements ? jQuery.makeArray(elements) : this;
            })
            .filter(function() {
                var type = this.type;
                // Use .is(":disabled") so that fieldset[disabled] works
                return this.name && this.value.length && !jQuery(this).is(":disabled") &&
                    rsubmittable.test(this.nodeName) && !rsubmitterTypes.test(type) &&
                    (this.checked || !manipulation_rcheckableType.test(type));
            })
            .map(function(i, elem) {
                var val = jQuery(this).val();

                return val == null ?
                    null :
                    jQuery.isArray(val) ?
                    jQuery.map(val, function(val) {
                        return {
                            name: elem.name,
                            value: val.replace(rCRLF, "\r\n")
                        };
                    }) : {
                        name: elem.name,
                        value: val.replace(rCRLF, "\r\n")
                    };
            }).get();
    },
    preventRepeatedSubmission: function(time, autoReset) { //To prevent  Repeated submission autoReset 表示自动重置
        var t = this;
        var fn;
        var t1;
        this.init = function() {
            if (t.is(":input")) { //如果是表单
                t.attr("disabled", "disabled");
            } else {
                t1 = t.clone(false); //复制一份
                t.fadeOut(1, function() {
                    t.after(t1);
                }); //加入克隆的dom元素
                t1.css({
                    "opacity": "0.5"
                }).click(function() {
                    return false;
                }); //去掉事件
            }
        }
        this.resetPRS = function() {
            if (t.is(":input")) { //如果是表单
                t.removeAttr("disabled");
            } else {
                t1.fadeOut(300, function() {
                    $(this).remove();
                    t.fadeIn(1);
                });
            }
        }

        this.init();

        !autoReset && time != 0 && setTimeout(this.resetPRS, time || 6000);
    },
    remoteTip: function(dataUrl) {
        var t = $(this);
        var id = "tip_" + t.attr("id") + "_" + t.attr("name");
        var ul = $("#" + id);
        if (!ul.length) {
            ul = $("<ul id='" + id + "' class='custom-input-tip hide'></ul>");
            t.after(ul);
            ul.html("");
        }
        var timer = undefined;
        t.keyup(function(e) {
            var focus = ul.find("li.focus");
            if (e.keyCode == 38) { //up
                if (!focus.length) {
                    ul.find("li").eq(0).addClass("focus");
                } else if (!focus.prev("li").length) {
                    focus.removeClass("focus");
                    ul.find("li").last().addClass("focus");
                } else {
                    focus.removeClass("focus").prev("li").addClass("focus");
                }
                focus = ul.find("li.focus");
                t.val(focus.text());
            } else if (e.keyCode == 40) { //down
                if (!focus.length) {
                    ul.find("li").eq(0).addClass("focus");
                } else if (!focus.next("li").length) {
                    ul.find("li").eq(0).addClass("focus");
                    focus.removeClass("focus");
                } else {
                    focus.removeClass("focus").next("li").addClass("focus");
                }
                focus = ul.find("li.focus");
                t.val(focus.text());
            } else {
                if (!!t.val().length) {
                    timer && clearTimeout(timer);
                    timer = setTimeout(function() {
                        $.get(dataUrl + t.val(), function(data) {
                            var li = "";
                            //ul.html("").slideUp(200);
                            for (var i in data) {
                                li += "<li class='tip-li'>" + data[i] + "</li>";
                            }
                            if (!!li.length) {
                                ul.html(li).slideDown(200);
                            } else {
                                ul.html("").hide(200);
                            }
                        }, "json");
                    }, 300);
                }
            }

        });
        ul.on("click", ".tip-li", function() {
            t.val($(this).text());
            ul.hide();
        });
        part.click(function() {
            ul.slideUp(300);
        })
    }
});



function triggerPLError(uploader, file, rjson) {
    uploader.trigger('Error', {
        code: uploader.HTTP_ERROR,
        message: rjson.msg,
        file: file,
        response: rjson.msg,
        status: 500
    });

    // uploader.splice();
    alert(rjson.msg);
}

function HistoryManage() {
    var _urlLength = 20;
    _urlQueue = new Array();
    var _mainTarget = $("#navTab");
    var i = 0;
    this.addUrl = function(url, data) {
        if (data && data.length) {
            var params = $.paramNotN(data);
            url = params.length ? url + "?" + params : url;
        }
        _urlQueue.unshift(url);
        i++;
        if (i > _urlLength) {
            _urlQueue.splice(10, 10);
            i = 10;
        }
        return true;
    }
    this.load = function(url, data, fn) {

        _mainTarget.load(url, data, fn);
        this.addUrl(url, data);
        initDomEvent();
    }
    this.refresh = function() {
        this.back(0);
        initDomEvent();
    }
    this.back = function(index) {
        var url = _urlQueue[index];
        _urlQueue.splice(0, index);
        i = i - index;
        //alert(url);
        plog(url)
        url && this.addUrl(url) && _mainTarget.load(url,function(){
             initDomEvent();
        });
    }
}


function Loading(targetId, opacity) {
    var opts = {
        lines: 12, // 画的线条数  
        length: 15, // 每条线的长度  
        width: 8, // 线宽  
        radius: 20, // 线的圆角半径  
        corners: 1, // Corner roundness (0..1)  
        rotate: 0, // 旋转偏移量  
        direction: 1, // 1: 顺时针, -1: 逆时针  
        color: '#000', // #rgb or #rrggbb or array of colors  
        speed: 1, // 转速/秒  
        trail: 100, // Afterglow percentage  
        shadow: false, // 是否显示阴影  
        hwaccel: false, // 是否使用硬件加速  
        className: 'spinner', // 绑定到spinner上的类名  
        zIndex: '3141592600', // 定位层 (默认 2000000000)  
        top: '50%', // 相对父元素上定位，单位px  
        left: '50%' // 相对父元素左定位，单位px 
    };
    var target = part[0]; // document.getElementById(targetId || 'navTab');

    var spinner = new Spinner(opts).spin(target);
    var backdorp = new Backdrop(opacity);
    var msg = "操作进行中";
    var loadingTimer;
    this.start = function() {

        //var spinner = new Spinner().spin();
        // target.appendChild(spinner.el);
        var span = $("<span style='position:absolute;margin:auto;left:0;right:0;top:0;bottom:0;width:180px;height:20px;margin-left:-50px;margin-top:60px;font-size:18px;z-index:31415926;color:#93FF93;'></span>");
        $(".spinner").append(span);
        var circle = " . ";
        var circles = circle;
        backdorp.show();
        loadingTimer = setInterval(function() {
            if (circles.length > 15) {
                circles = circle;
            }
            span.text(msg + circles);
            circles += " . ";
        }, 400);
        return this;
    }
    this.stop = function() {
        spinner.stop();
        backdorp.remove();
        delete loadingTimer;
    }

}

