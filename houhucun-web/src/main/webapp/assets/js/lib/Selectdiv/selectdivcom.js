//*
//调用说明:1.大块元素  2.隐藏 显示元素  3.type ==1是单选搜索  type==2 是多选搜索  4.obj3  当type=2时候才必须传obj3   多选li的内容到obj3  5.回调fn  6.btn确定按钮关闭下拉
// $.divselect("obj1","obj2"，"1");
//
jQuery.divselect = function(divselectid,inputselectid,type,btn,obj3) {
    var inputselect = $(inputselectid);
    $(divselectid+" cite").click(function(){
        var width=$(divselectid).width()-2;
        $(divselectid+" .inp_search").css("width",width);

        var ul = $(divselectid+" .inp_search");
        if(ul.css("display")=="none"){
            ul.slideDown("fast");
        }else{
            ul.slideUp("fast");
        }
        return false;
    });
    $(divselectid+" ul li a").click(function(){
        if(type == 1){//type等于1 是单选   等于2 是多选
            var txt = $(this).text();
            $(divselectid+" cite").html(txt);
            var value = $(this).attr("data-value");
            inputselect.val(value);
            //$(divselectid+" .inp_search").slideUp("fast");
            $(btn).click(function(){
                $(divselectid+" .inp_search").slideUp("fast");
            });
        }else{
            if(type == 2 && $(this).find("i").attr("class") == "ico_checkbox_on" )
            {

                $(this).find("i").removeClass("ico_checkbox_on").addClass("ico_checkbox_off");
                var txt = $(this).text();
                var value = $(this).attr("data-value");
                var str='<div class="col-xs-2 font_nowrap mar_b10 po_re" data-value='+value+'>'+txt+'<a href="javascript:;" class="po_ab btn_removetjzb_1 hide" style="display: none;"></a></div>'
                $(obj3).append(str);
                return false;
            }else{
                return false;
            }
        }


    });
    //搜索
    $(divselectid+' .inp_sea_div').click(function(){
        return false;
    })
    var arr=[];
    $(divselectid+' .inp_sea_div input').keyup(function(){
        $(divselectid+" ul li").hide();
        var _this_val=$(this).val().toLowerCase();

        $(divselectid+" ul li").each(function(ev,index){
            var _this_html= $(this);
            var str = _this_html.find("a").html().toLowerCase();
            if( str.indexOf(_this_val) != -1)
            {
                var _index=_this_html.find('a').attr('data-value') - 1;
                $(divselectid+" ul li").eq(_index).show();
            }
        })

        if(_this_val == null || _this_val=="")
        {
            $(divselectid+" ul li").show();
        }

    })

    $(btn).click(function(){
        $(divselectid+" .inp_search").slideUp("fast");
    });
};