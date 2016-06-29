/**
 * Created by jh on 2016/4/26.
 */
var classIndex = 0;
var flsa = true;
//勾选
function MyCheck(obj){
    if(obj.checked){
        //选中
        var Box = obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode;
        var ThStr = Box.getElementsByTagName('label')[0].children[0].innerHTML;
        var TdStr = obj.parentNode.parentNode.parentNode.children[2].value;
        var ObjClass=obj.parentNode.parentNode.parentNode.getAttribute("data-class");

        var Th = '<th  class="sorting_disabled th_'+ObjClass+'" rowspan="1" colspan="1" >' + ThStr + '</th>'
        var Td = '<td class="td_'+ObjClass+'" ><div class="cl">' + TdStr + '</div></td>'
        console.log(Th)
        console.log(Td)


    } else {
        //没选中

    }
}


//包装属性点击取消
function BtnCancel(obj){
    var obj2 = obj.parent().parent().find(".distinguish").children();
    var oInput = obj2.find("input[type='text']");

    for(var i = 0; i < obj2.length; i++){
        if(obj2.eq(i).find("input[type='text']").attr("readonly") != "readonly"){
            obj2.eq(i).find("input[type='text']").parent().remove();

            SortClassAdd(obj);
        }
    }
}

//包装属性点击保存
function BtnPreservationl(obj){
    var obj2 = obj.parent().parent().find(".distinguish").children();
    var oInput = obj2.find("input[type='text']");

    for(var i = 0; i < obj2.length; i++){
        if(!obj2.eq(i).find("input[type='text']").val()){
            obj2.eq(i).find("input[type='text']").parent().remove();
            SortClassAdd(obj);
        } else {
            var oClass = obj2.eq(i).find("input[type='text']").parent().parent().parent().parent().attr("data-class");
            obj2.eq(i).find("input[type='text']").attr({"readonly" : "readonly"}).css("border", "none");

            SortClassAdd(obj);
        }
    }
}

//点击显示赋值给input
function setValue(obj){
    var str = obj.html();
    $('#myInput').find("input").val(str);
    $(".btn_off_html").click(function(){
        obj.html($('#myInput').find("input").val());
        return false;
    })
}

//排序class
function SortClassAdd(obj){
    var Objs = obj.parent().parent().parent();
    var ObjDataClass = Objs.attr("data-class");
    var ObjCom = Objs.find(".distinguish").children();

    if(ObjCom.length > 0){
        for(var i = 0; i < ObjCom.length; i++){
            ObjCom.eq(i).attr({"data-class" : ObjDataClass + '_' + (i + 1)}).addClass(ObjDataClass + '_' + (i + 1));
        }

    } else {
        ObjCom.eq(0).attr({"data-class" : ObjDataClass + '_' + 0}).addClass(ObjDataClass + '_' + 0);
    }
}

// 添加input
function addInput(obj){
    var obj3 = obj.parent().parent().find(".distinguish");
    var indexClass = obj.parent().parent().attr("data-class");
    var _this = obj.parent().parent().find(".distinguish");

    if(_this.html().length){
        var obj3children = obj3.children().length + 1;

        var str = ' <div class=" mar_b15 po_re mar_l25 mar_r25 fl" style="padding-left:0;">\
                    <div class="check-box po_ab"  style=" display:block;left:-25px; top:3px;">\
                           <input type="checkbox" onclick="MyCheck(this)"  name="checkbox2" datatype="*">\
                    </div>\
                     <span></span>\
                    <input type="text" class="input-text" style="width:146px;">\
                    <a class="btn_removetjzb po_ab" href="javascript:;" onclick="RemoveParent($(this),1)"></a>\
                </div>'

        _this.append(str);


        //单选和复选插件
        $(function(){
            $('.skin-minimal input').iCheck({
                checkboxClass : 'icheckbox-blue',
                radioClass : 'iradio-blue',
                increaseArea : '20%'
            });
        });

        //排序class
        SortClassAdd(obj)
    } else {

        var str = ' <div class=" mar_b15 po_re mar_l25 mar_r25 fl" style="padding-left:0;">\
                    <div class="check-box po_ab" style=" display:block;left:-25px; top:3px;">\
                           <input type="checkbox" onclick="MyCheck(this)"  name="checkbox2" datatype="*">\
                    </div>\
                     <span></span>\
                    <input type="text" class="input-text" style="width:146px;">\
                    <a class="btn_removetjzb po_ab" href="javascript:;" onclick="RemoveParent($(this),1)"></a>\
                </div>'

        _this.append(str);
        obj.parent().find("a").show();

        //单选和复选插件
        $(function(){
            $('.skin-minimal input').iCheck({
                checkboxClass : 'icheckbox-blue',
                radioClass : 'iradio-blue',
                increaseArea : '20%'
            });
        });

        //排序class
        SortClassAdd(obj)

    }
}

//显示添加框
function shouobj(obj){
    $(".temporary_weight").show();
}

//随机数
function random(n, m){
    return Math.floor(Math.random() * (m - n) + n);
}

//取消左侧属性名
function hideobj(){
    $(".temporary_weight").hide();
    $(".temporary_weight").find("input").val("");
}

//增加左侧属性名
function addPacking(obj){
    var n = random(1, 1000);
    var html = obj.parent().parent().find("input").val();
    if(!html){
        alert("请输入文字");
        return false
    }
    var str = '<div class="row cl add_' + n + '" data-class=add_' + n + '>\
                  <label class="form-label col-xs-2" data-class="add_' + n + '_0"><a data-toggle="modal" href="#myInput" onclick="setValue($(this))">' + html + '\
                  </a>：<a class="po_ab btn_removetjzb_2" onclick="RemoveParent($(this),2)" style="right: 15px;top: -8px;"></a></label>\
                  <div class="col-xs-10 skin-minimal cl">\
                    <div class="distinguish fl"></div>\
                    <div class="formControls fl"> \
                      <a class="btn btn-default radius size-S" href="javascript:;"  onclick="BtnCancel($(this))" style="display:none;">取消</a>\
                      <a class="btn btn-warning radius size-S" href="javascript:;" onclick="BtnPreservationl($(this))" style="display:none;">保存</a>\
                      <a class="mar_l10 add_input add_zj_input" onclick="addInput($(this));" href="javascript:;">+ 增加</a> \
                    </div>\
                  </div>\
              </div>'

    $(".Packing_weight").append(str);
    obj.parent().parent().find("input").val("");
}
