﻿$(function(){
    $("[name=name_xxk]").each(function(i){
        $(this).click(function(){
            $("[name=name_xxk]").attr("class","")
            $(this).attr("class","now")
            $("[id^=id_xxk_]").hide();
            $("#id_xxk_"+i).show();
        })
    })

    $("[name=name_gys]").each(function(i){
        $(this).click(function(){
            $("[name=name_gys]").attr("class","wdgz_div_no")
            $(this).attr("class","wdgz_div_yes")
            $("[id^=id_gys_]").hide();
            $("#id_gys_"+i).show();
        })
    })
})