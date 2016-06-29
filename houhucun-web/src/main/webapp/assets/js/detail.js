$(function(){
//产品详情页左侧js效果
    // 图片上下滚动

    var count = $("#imageMenu .small-pic-box li").length - 2; /* 显示 3 个 li标签内容 */
    var interval = $("#imageMenu li:first").width()+20;
    var curIndex = 0;
    $('.scrollbutton').click(function(){
        if( $(this).hasClass('disabled') ) return false;
        
        if ($(this).hasClass('prev')) --curIndex;
        else ++curIndex;
        
        $('.scrollbutton').removeClass('disabled');
        if (curIndex == 0) $('.prev').addClass('disabled');
        if (curIndex == count-1) $('.next').addClass('disabled');
        
        $("#imageMenu ul").stop(false, true).animate({"marginLeft" : -curIndex*interval + "px"}, 600);
    });
    
    // 解决 ie6 select框 问题
    $.fn.decorateIframe = function(options) {
        if (!$.support.leadingWhitespace) {
            var opts = $.extend({}, $.fn.decorateIframe.defaults, options);
            $(this).each(function() {
                var $myThis = $(this);
                //创建一个IFRAME
                var divIframe = $("<iframe />");
                divIframe.attr("id", opts.iframeId);
                divIframe.css("position", "absolute");
                divIframe.css("display", "none");
                divIframe.css("display", "block");
                divIframe.css("z-index", opts.iframeZIndex);
                divIframe.css("border");
                divIframe.css("top", "0");
                divIframe.css("left", "0");
                if (opts.width == 0) {
                    divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");
                }
                if (opts.height == 0) {
                    divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");
                }
                divIframe.css("filter", "mask(color=#fff)");
                $myThis.append(divIframe);
            });
        }
    }
    $.fn.decorateIframe.defaults = {
        iframeId: "decorateIframe1",
        iframeZIndex: -1,
        width: 0,
        height: 0
    }
    //放大镜视窗
    $("#bigView").decorateIframe();

    //点击到中图
    var midChangeHandler = null;
    
    $("#imageMenu .small-pic-box li img").bind("click", function(){
        $("#imageMenu .small-pic-box li").removeClass('active');
        if ($(this).attr("id") != "onlickImg") {
            midChange($(this).attr("src").replace("small", "mid"));
            $("#imageMenu li").removeAttr("id");
            $(this).parent().addClass('active');
            $(this).parent().attr("id", "onlickImg");
        }
    }).bind("mouseover", function(){
        $("#imageMenu .small-pic-box li").removeClass('active');
        if ($(this).attr("id") != "onlickImg") {
            window.clearTimeout(midChangeHandler);
            midChange($(this).attr("src").replace("small", "mid"));
            $(this).parent().addClass('active');
        }
    });

    function midChange(src) {
        $("#midimg").attr("src", src).load(function() {
            changeViewImg();
        });
    }

    //大视窗看图
    function mouseover(e) {
        if ($("#winSelector").css("display") == "none") {
            $("#winSelector,#bigView").show();
        }

        $("#winSelector").css(fixedPosition(e));
        e.stopPropagation();
    }


    function mouseOut(e) {
        if ($("#winSelector").css("display") != "none") {
            $("#winSelector,#bigView").hide();
        }
        e.stopPropagation();
    }


    $("#midimg").mouseover(mouseover); //中图事件
    $("#midimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件

    var $divWidth = $("#winSelector").width(); //选择器宽度
    var $divHeight = $("#winSelector").height(); //选择器高度
    var $imgWidth = $("#midimg").width(); //中图宽度
    var $imgHeight = $("#midimg").height(); //中图高度
    var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度

    function changeViewImg() {
        $("#bigView img").attr("src", $("#midimg").attr("src").replace("mid", "big"));
    }

    changeViewImg();

    $("#bigView").scrollLeft(0).scrollTop(0);
    function fixedPosition(e) {
        if (e == null) {
            return;
        }
        var $imgLeft = $("#midimg").offset().left; //中图左边距
        var $imgTop = $("#midimg").offset().top; //中图上边距
        X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X
        Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y
        X = X < 0 ? 0 : X;
        Y = Y < 0 ? 0 : Y;
        X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;
        Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;

        if ($viewImgWidth == null) {
            $viewImgWidth = $("#bigView img").outerWidth();
            $viewImgHeight = $("#bigView img").height();
            if ($viewImgWidth < 200 || $viewImgHeight < 200) {
                $viewImgWidth = $viewImgHeight = 800;
            }
            $height = $divHeight * $viewImgHeight / $imgHeight;
            $("#bigView").width($divWidth * $viewImgWidth / $imgWidth);
            $("#bigView").height($height);
        }

        var scrollX = X * $viewImgWidth / $imgWidth;
        var scrollY = Y * $viewImgHeight / $imgHeight;
        $("#bigView img").css({ "left": scrollX * -1, "top": scrollY * -1 });
        $("#bigView").css({ "top": 0, "left": $(".pro-img").width()+50 });

        return { left: X, top: Y };
    }

    //关注商品点击效果
    $('.smallImg .attention-pro').click(function(){
        $(this).addClass('active');
    });

    //模拟select
    jQuery.fn.select = function(options){  
        return this.each(function(){  
            var $this = $(this);  
            var $shows = $this.find(".shows");  
            var $selectOption = $this.find(".selectOption");  
            var $el = $this.find("ul > li");  
                                      
            $this.click(function(e){  
                $(this).toggleClass("zIndex");  
                $(this).children("ul").toggleClass("dis");  
                e.stopPropagation();  
            });  
              
            $el.bind("click",function(){  
                var $this_ = $(this);  
                   
                $this_.parent().parent().find(".selectOption").text($this_.text());  
            });  
              
            $("body").bind("click",function(){  
                $this.removeClass("zIndex");  
                $this.find("ul").removeClass("dis");      
            })  
              
        //eahc End    
        });  
          
    } 
    $(".selectContainer").select();

    //型号规格列表单选
    $('.size-box .ac_span').each(function(){
        $(this).click(function(){
            $(this).addClass('active_span').siblings().removeClass('active_span');  
        }); 
    });

    //地址选择
    $(function () {
        $('.li_tab_input,.li_tab_i').click(function () {
            $('.tab_cont').toggle();
            $('#con_one_2 dd').removeClass('current');
            $('.tab li').removeClass('current');
            $('#one1').addClass('current');
            $('.list dl').hide();
            $('#con_one_1').show();        
        });
        
        // $('.list dl dd').each(function (ev, index) {
        //     var tack = $(this);
        //     var _index = $(this).index();
        //     tack.on('click', function () {
        //         $('.list dl dd').removeClass('current');
        //         $(this).addClass('current');
        //     });
        // }); 
    });   

    //增加、减少商品
    
    $(".buy-num .J_add").bind("click",function(){
        var oldValue=parseInt($(this).siblings(".J_input").attr("value"));
        oldValue++;
        $(this).siblings(".J_input").attr("value",oldValue);
        return false;
    })
    $(".buy-num .J_minus").bind("click",function(){
        var oldValue=parseInt($(this).siblings(".J_input").attr("value"));
        oldValue--;
        if(oldValue<1){
            oldValue=1;
            $(this).siblings(".J_input").attr("value","1");
        }else{
            $(this).siblings(".J_input").attr("value",oldValue);
        }  
        return false;     
    })

    //关联商品轮播
    $dragBln = false;
    $(".relate-pro-list").touchSlider({
        flexible: true,
        speed: 500,
        btn_prev: $("#btn_prev"),
        btn_next: $("#btn_next"),
        paging: $(".dot-btn a"),
        counter: function (e) {
            $(".dot-btn a").removeClass("on").eq(e.current - 1).addClass("on");
        }
    });
    $(".relate-pro-list").bind("mousedown", function () {
        $dragBln = false;
    });
    $(".relate-pro-list").bind("dragstart", function () {
        $dragBln = true;
    });
    $(".ctrolBtn").click(function () {
        if ($dragBln) {
            return false;
        }
    });
    timer = setInterval(function () {
        $("#btn_next").click();
    }, 3000);
    $(".main_visual").hover(function () {
        clearInterval(timer);
    }, function () {
        timer = setInterval(function () {
            $("#btn_next").click();
        }, 3000);
    });
    $(".main_visual").bind("touchstart", function () {
        clearInterval(timer);
    }).bind("touchend", function () {
        var timer = setInterval(function () {
            $("#btn_next").click();
        }, 3000);
    });

    //产品详情介绍tab
    $.Huitab("#pro-detail .tabBar span","#pro-detail .tabCon","active","click","0");

    //用户评价
    $.Huitab("#comment .tabBar span","#comment .tabCon","active","click","0");

    //右侧固定栏划过显示详情
    $('.right-sidebar').hover(function(){
        $('.right-sidebar').removeClass('active');
        $(this).addClass('active');
        if($('.shoppingCar')){
            $('.shoppingCar').eq($(this).index()).show();
        }
    },function(){
        if($('.shoppingCar')){
            $('.shoppingCar').eq($(this).index()).hide();
        }
    });

    //技术详情页修改
    $('.modify').click(function(){
        if($(this).html()=="修改")
        {
            $('.table-cont input').attr('readonly','readonly').css('border','none');
            $(this).parent().siblings().find('input[type="text"]').removeAttr('readonly').focus().css('border','1px solid #eaeaea');
            $(this).html('保存');
            if($(this).parent().siblings().find('textarea'))
            {
                $('.table-cont textarea').attr('readonly','readonly').css('border','none');
                $(this).parent().siblings().find('textarea').removeAttr('readonly').focus().css('border','1px solid #eaeaea');
            }
            if($(this).parent().siblings().find('select'))
            {
                $('.table-cont select').attr('disabled','disabled').css('border','none');
                $(this).parent().siblings().find('select').removeAttr('disabled').focus().css('border','1px solid #eaeaea');
            }
        }
        else
        {
            $('.table-cont input').attr('readonly','readonly').css('border','none');
            $(this).html('修改');
            if($(this).parent().siblings().find('textarea'))
            {
                $('.table-cont textarea').attr('readonly','readonly').css('border','none');
            }
            if($(this).parent().siblings().find('select'))
            {
                $('.table-cont select').attr('disabled','disabled').css('border','none');
            }
        } 
    });

    //套餐左右箭头显示与隐藏

    if($('#ns_scroll_box-1').children().length<=4)
    {
       $('#J-ns-collcation-con-1').find('.btn-con').hide(); 
    } 
    
    if($('#ns_scroll_box-2').children().length<=4)
    {
        $('#J-ns-collcation-con-2').find('.btn-con').hide(); 
    } 
    


});

    //推荐套餐tab切换
    function ns_tab(tabId,conId,n,l){
        for(var i = 1;i<=l;i++){
            $("#"+tabId+i).removeClass("active");    
            $("#"+conId+i).hide();  
        };
        $("#"+tabId+n).addClass("active");   
        $("#"+conId+n).show();      
    };
