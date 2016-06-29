/**
 * Created日历插件 by jh on 2016/1/8.
 *
 *
 */

function Created(obj) {
    this.oInput = $(obj);
    this.index = 0;    // tab默认下标
    this.province = 0; //默认省
    this.city = 0;     // 默认市
    this.county = 0;   //默认县
    var _this = this;
    this.area = getArea();
    var str = '<div class="tab_cont po_ab" id="Mycalendar" style="padding: 10px;">\
                    <ul class="tab">\
                        <li class="current" id="one1"><a href="javascript:;">省份<i class="fa fa-caret-down"></i></a></li>\
                        <li id="one2"><a href="javascript:;">城市<i class="fa fa-caret-down"></i></a></li>\
                    </ul>\
                <div class="list">\
                    <dl id="con_one_1"  style="display:block;">\
                    </dl>\
                    <dl id="con_one_2" style="display:none;">\
                    </dl>\
                </div>\
             </div>'
    this.oInput.parent().append(str);
    $('#con_one_1').html('');


    $('#con_one_1').append('<dd class="current po_re"><a href="javascript:;"><span class="address-name">北京1</span><b class="pub_b show_inline po_ab"></b></a></dd><dd class="po_re"><a href="javascript:;"><span class="address-name">北京1</span><b class="pub_b show_inline po_ab"></b></a></dd>');//后台数据--省
    $('#con_one_2').append('<dd class="current po_re"><a href="javascript:;"><span class="address-name">北京2</span><b class="pub_b show_inline po_ab"></b></a></dd><dd class="po_re"><a href="javascript:;"><span class="address-name">北京2</span><b class="pub_b show_inline po_ab"></b></a></dd>');//后台数据--市


    this.MyTab(_this);
    this.MyActive($('#con_one_1'));
    this.MyActive($('#con_one_2'));
    this.setInpuHtml(_this);
}

//选项卡切换

Created.prototype.MyTab = function (_this) {
    var obj = $('#Mycalendar .tab li');
    for (var i = 0; i < obj.length; i++) {
        obj.eq(i).on('click', function (ev, index) {
            var index = $(this).index();
            if (index == 0) {
                _this.city = 0;     // 默认市
                _this.county = 0;   //默认县
            } else {
                if (index == 1) {
                    _this.county = 0;   //默认县
                }
            }

            obj.removeClass('current');
            $('.list').children().css({'display': "none"});
            $(this).addClass('current');
            $('.list').children().eq(index).css({'display': 'block'});
            _this.index = index;
        })

    }
}

//选项卡二
Created.prototype.MyActive = function(obj){
    var objChi=obj.children();
    objChi.each(function(_this){
        $(this).on('click', function (ev, index) {
            objChi.removeClass('current');
            $(this).addClass('current');
        })
    })
}

//点确定辅助给input
Created.prototype.setInpuHtml=function(_this){
    $('#con_one_1 dd').on('click',function(){
        $('.tab li').removeClass('current');
        $('#one2').addClass('current');
        $('.list dl').hide();
        $('#con_one_2').show();
    })
    $('#con_one_2 dd').on('click',function(){
        var str='';

        for(var i=0; i<$('.list dl').length;i++)
        {
            var str1=$('.list dl').eq(i).find('.current .address-name').html();
            str+=str1;
        }
        // var text=str+'-'+$('#con_one_4 .input_Style2').val();

        _this.oInput.val(str);

        $('#Mycalendar').hide();
    })

}