// JavaScript Document
$(document).ready(function(){
    //ͷ����ַ
    $('.head_address_top').mouseenter(function(){
        $('.head_address').show();
    });
    $('.head_address_top').mouseleave(function(){
        $('.head_address').hide();
    });
    //���������б�
    $('.search_select').mouseenter(function(){
        $('.search_select_list').show();
    });
    $('.search_select').mouseleave(function(){
        $('.search_select_list').hide();
    });
    //�����б�
    $('.nav_left').mouseenter(function(){
        $('.nav_list').show();
    });
    $('.nav_left').mouseleave(function(){
        $('.nav_list').hide();
    });
    $('.nav_list ul li').mouseenter(function(){
        $(this).addClass('active_li');
        $(this).children('.nav_list_child').addClass('active_div');
        $(this).children('.right_pos').show();
    });
    $('.nav_list ul li').mouseleave(function(){
        $(this).removeClass('active_li');
        $(this).children('div').removeClass('active_div');
        $(this).children('.right_pos').hide();
    });
    //head_center nav
    $('.logo_right_nav li').each(function(){
        var _this = $(this);
        _this.click(function(){
            _this.addClass('active').siblings().removeClass('active');
        });
    });
});