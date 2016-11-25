$(function(){
    $("body").on("click","[name=mzh_fenye]",function(){
        $("[name=mzh_fenye]").attr("class","ndfxs_1-2_fenye_no");
        $(this).attr("class","ndfxs_1-2_fenye_yes");
    })

    //选项卡
    var $this = $("[class=gygl_xxk_yes]").width();
    $("[class=gygl_xxk_yes]").find("span").css({width:$this});
    $("body").on("click","[name=mzh_xxk]",function(){
        var $this = $(this).width();
        $("[name=mzh_xxk]").attr("class","gygl_xxk_no");
        $(this).attr("class","gygl_xxk_yes");
        $(this).find("span").css({width:$this});
        if($(this).attr("idclick") == 0){
        	$("[id^=mzh_click_]").hide();
        	$("#mzh_click_0").show();
        } else if($(this).attr("idclick") == 1){
        	$("[id^=mzh_click_]").hide();
        	$("#mzh_click_1").show();
        } else if($(this).attr("idclick") == 2){
        	$("[id^=mzh_click_]").hide();
        	$("#mzh_click_2").show();
        } else if($(this).attr("idclick") == 3){
        	$("[id^=mzh_click_]").hide();
        	$("#mzh_click_3").show();
        } else if($(this).attr("idclick") == 4){
        	$("[id^=mzh_click_]").hide();
        	$("#mzh_click_4").show();
        }
    })

    /* 4-3-3（供应商管理）发布产品(yes-no) 
	 $("[name=mzh_lmbt_one_ck_no]").click(function(){
		$("[name=mzh_lmbt_one_ck_no]").attr("class","mzh_lmbt_one_ck_no")
		$(this).attr("class","mzh_lmbt_one_ck_yes")
	 })

	
    $("[name=mzh_lmbt_one_ck_no]").each(function(){
		$(this).click(function(){
			$("[name=mzh_lmbt_one_ck_no]").attr("class","mzh_lmbt_one_ck_no")
			$(this).attr("class","mzh_lmbt_one_ck_yes")
		})
	})
	$("[name=mzh_lmbt_one_ck_notwo]").each(function(){
		$(this).click(function(){
			$("[name=mzh_lmbt_one_ck_notwo]").attr("class","mzh_lmbt_one_ck_no")
			$(this).attr("class","mzh_lmbt_one_ck_yes")
		})
	})
	$("[name=mzh_lmbt_one_ck_nothr]").each(function(){
		$(this).click(function(){
			$("[name=mzh_lmbt_one_ck_nothr]").attr("class","mzh_lmbt_one_ck_no")
			$(this).attr("class","mzh_lmbt_one_ck_yes")
		})
	})
	$("[name=mzh_lmbt_one_ck_nofor]").each(function(){
		$(this).click(function(){
			$("[name=mzh_lmbt_one_ck_nofor]").attr("class","mzh_lmbt_one_ck_no")
			$(this).attr("class","mzh_lmbt_one_ck_yes")
		})
	})*/
	
	

    /* 3-3（供应商管理）发布产品-编辑 */
    $("body").on("click","[name=mzh_lmbt_one_ck_no_img2]",function(){
        $(".mzh_lmbt_one_ck_xinjian").show();
        $(".mzh_lmbt_one_ck_no_img3").live("click",function(){
            $(".mzh_lmbt_one_ck_xinjian").hide();
        })
    })

    /* 3-3（供应商管理）发布产品-删除 */
    $("[name=mzh_lmbt_one_ck_no_img1]").click(function(){
        $(this).parent("div").remove();
    })


	/****** 左侧选项卡 ********/
	$(".p10 ul li").each(function(){
		$(this).click(function(){
			$(".p10 ul li").attr("class","");
			$(this).attr("class","now");
		})
	})
	
	/* 1-3（首页）我上架的产品-上架中---改 */
	//状态yes
	$("body").on("click","[name=mzh_4_7_yes]",function(){
		$("[name=mzh_4_7_yes]").attr("class","");
		$(this).attr("class","yes_bgs");
	})
	
	//分类no
	$("body").on("click","[name=mzh_4_7_no]",function(){
		$("[name=mzh_4_7_no]").attr("class","");
		$(this).attr("class","yes_bgs");
	})
	
	/****** 1-4（首页）店铺分类--新增 ******/
	//删除
	$("body").on("click","[name=mzh_remove]",function(){
		$(this).parent().parent().remove();
	})
	
	/****** 6-1（供应商管理）客服QQ ******/
	//增加QQ
	$("body").on("click","[name=mzh_add_qq]",function(){
		$("#mzh_add_qq").append("<li><input type='text' class='btn_h30 fl w150' maxlength='12' /><i class='jian' name='mzh_remove_qq'></i></li>");
	})
	
	//删除QQ
	$("body").on("click","[name=mzh_remove_qq]",function(){
		$(this).parent().remove();
	})
	
	
	/****** 11-1我的钱包 ******/
	//鼠标放上-详情
	$("[class=wdqb_div_1_span]").hover(function(){
		$(this).next().show();
	},function(){
		$(this).next().hide();
	})
	
	/***** 3-1（首页）供应管理--改 *****/
	//下拉和按钮
	$("body").on("click","[name=mzh_xl]",function(){
		if($(this).attr("isclick") == 0){
			$(this).parent().attr("class","mzh_xl_yes");
			$(this).attr("isclick","1");
			$(this).attr("src","pic/mzh_4_8_xl.jpg")
		} else{
			$(this).parent().attr("class","mzh_xl");
			$(this).attr("isclick","0");
			$(this).attr("src","pic/mzh_4_8_xl_no.jpg")
		}
	})
	$("body").on("click","[name=mzh_span]",function(){
		$(this).parent().parent().attr("class","mzh_xl");
		$(this).parent().prev().attr("isclick","0");
		$(this).parent().prev().attr("src","pic/mzh_4_8_xl_no.jpg")
	})
	
	/****** 1-3（首页）我上架的产品-上架中---改 ******/
	$("body").on("click",".mzh_xlk_text",function(){
		if($(this).attr("isclick") == 0){
			$("[name=mzh_xlk]").attr("class","mzh_xlk");
			$(this).parent().attr("class","mzh_xlk_yes");
			$(this).attr("isclick","1");
		} else{
			$(this).parent().attr("class","mzh_xlk");
			$(this).attr("isclick","0");
		}
	})
	$("[name=mzh_span_4_9]").click(function(){
		var $val = $(this).text();
		$(this).parent().parent().attr("class","mzh_xlk");
		$(this).parent().prev().attr("isclick","0");
		$(this).parent().prev().html($val);
	})

	/****** 12-1（购物车）购物车 ******/
	//鼠标放上
	$("[name=mzh_hover]").hover(function(){
		if($(this).attr("ishover") ==0){
			$(this).find("div[name=mzh_fangshang]").attr("class","comm_bianjno");
			$(this).attr("ishover","1");
		}
	},function(){
		if($(this).attr("ishover") ==1){
			$(this).find("div[name=mzh_fangshang]").attr("class","comm_bianjno_mr");
			$(this).attr("ishover","0");
		}
	})
	
	$("[name=mzh_click]").mouseover(function(){
		$(this).parent().attr("class","comm_bianjyes");
	})
	$(".img_bianji").mouseout(function(){
		if($(this).parent().parent().parent().attr("ishover") == 1){
			$(this).parent().attr("class","comm_bianjno")
		}
	})

	//点击编辑-出弹出层
	$("body").on("click",".img_bianji",function(){
		$("[name=mzh_hover]").attr("ishover","0");
		$("[name=mzh_fangshang]").attr("class","comm_bianjno_mr");
		$(".elect_color").hide();
		$(this).parent().attr("class","comm_bianjyes");
		$(this).parent().parent().parent().attr("ishover","2");
		$(this).nextAll("div[class=elect_color]").show();
	})
	//弹出层-颜色-点击
	$("body").on("click","[name=mzh_color]",function(){
		$("[name=mzh_color]").attr("class","bor_heisel");
		$(this).attr("class","bor_redsel");
	})
	//弹出层-尺码-点击
	$("body").on("click","[name=mzh_size]",function(){
		$("[name=mzh_size]").attr("class","bor_heisel");
		$(this).attr("class","bor_redsel");
	})
	//弹出层-取消
	$("body").on("click","[name=mzh_close]",function(){
		$(".elect_color").hide();
		$("[name=mzh_fangshang]").attr("class","comm_bianjno_mr");
		$("[name=mzh_hover]").attr("ishover","0");
	})
	//减
	$("body").on("click","[name=mzh_jian]",function(){
		var $jian = parseInt($(this).next().find('input').val());
		if($jian >= 1){
			$jian = $jian - 1;
			$(this).next().find('input').val($jian);
		}
	})
	//加
	$("body").on("click","[name=mzh_jia]",function(){
		var $jia = parseInt($(this).prev().find('input').val());
		$jia = $jia + 1;
		$(this).prev().find('input').val($jia);
	})
	//全选
    $("[id=quanChe]").change(function(){
        $("[name=intName]").attr("checked",true);
        wirteFun();
    },function(){
	//反选
        $("[name=intName]").each(function(){
            if($(this).attr("checked")){
                $(this).attr("checked",false);
            } else {
                $(this).attr("checked",true);
            }
        })
        wirteFun();
    })
	function wirteFun(){
        $ingType = $("#zysaa").find("input:checked");    //[1]变量$ingType，zysaaID下的所有子元素平且是input里面为checked的
        $("#zysId").html("");                            //[4]清除以勾选的内容叠加
        $($ingType).each(function(i,n){         //[2]用变量循环带2个参数(i,n)第一个的意思是intName的个数,第二个是input里的属性
            $("#zysId").append("<li class=\"mfx2\">"+$ingType.eq(i).parent().text() +"<span class=\"a\" name=\"spName\" id=pa_"+ n.value+"> X</span></li>")
            //[3]zysId追加它下面的一句话让循环，+变量下的索引，查找(i),查找父元素的文本
    	})
	}
    $("[name=intName]").each(function(){
        $(this).click(function(){
            wirteFun();
        })
    })
	
	/**  我的钱包 **/
	$("#Addinput").click(function(){
		$("#AddCount").show();
		//$("#Bdbanks").attr("onclick","win('绑定银行卡','win_div_4','574px','600px')");
	})
	
	
	/*** 2-4 ***/
	//绑定倒计时
	$("#mzh_tijiao").click(function() {
        var yzm = $("#mzh_yzm").val();
        if ($.trim(yzm) == "") {
        }
    });
    //获取验证码
    $("#mzh_hqyzm").click(function(){
    	$(this).attr("value","已发送");
    })

    /****** 1-5（订单详情）已完成 --评分 ******/
        //放上
    $("[id^=mzh_pingfen_]").hover(function(){
        var $val = $(this).attr("value");
        if($(this).parent().attr("idclick") ==0){
            if($val == 1){
                $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
                $(this).attr("class","mzh_pjsd_pf_cha");
            } else if($val == 2){
                $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
                $(this).attr("class","mzh_pjsd_pf_cha");
                $(this).prevAll().attr("class","mzh_pjsd_pf_cha");
            } else if($val == 3){
                $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
                $(this).attr("class","mzh_pjsd_pf_hao");
                $(this).prevAll().attr("class","mzh_pjsd_pf_hao");
            } else if($val == 4){
                $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
                $(this).attr("class","mzh_pjsd_pf_hao");
                $(this).prevAll().attr("class","mzh_pjsd_pf_hao");
            } else if($val == 5){
                $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
                $(this).attr("class","mzh_pjsd_pf_henhao");
                $(this).prevAll().attr("class","mzh_pjsd_pf_henhao");
            }
        }
    },function(){
        if($("#mzh_dingyi").attr("idclick") == 0){
            $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
        }
    })
    //点击
    $("[id^=mzh_pingfen_]").click(function(){
        var $vala = $(this).attr("value");
        if($vala == 1){
            $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
            $(this).attr("class","mzh_pjsd_pf_cha");
        } else if($vala == 2){
            $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
            $(this).attr("class","mzh_pjsd_pf_cha");
            $(this).prevAll().attr("class","mzh_pjsd_pf_cha");
        } else if($vala == 3){
            $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
            $(this).attr("class","mzh_pjsd_pf_hao");
            $(this).prevAll().attr("class","mzh_pjsd_pf_hao");
        } else if($vala == 4){
            $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
            $(this).attr("class","mzh_pjsd_pf_hao");
            $(this).prevAll().attr("class","mzh_pjsd_pf_hao");
        } else if($vala == 5){
            $("[id^=mzh_pingfen_]").attr("class","mzh_pjsd_pf_moren");
            $(this).attr("class","mzh_pjsd_pf_henhao");
            $(this).prevAll().attr("class","mzh_pjsd_pf_henhao");
        }
        $(this).parent().attr("idclick","1");
        if($vala<=2){
            $("#mzh_fenshu").attr("class","mzh_pf_fs_cha");
        } else if($vala<=4 || $vala >=3){
            $("#mzh_fenshu").attr("class","mzh_pf_fs_hao");
        } else{
            $("#mzh_fenshu").attr("class","mzh_pf_fs_henhao");
        }
        $(this).siblings("#mzh_fenshu").show();
        $(this).siblings("#mzh_fenshu").html($vala+"分");
    })

    /********  *******/
    $("[name=mzh_xgcp]").click(function(){
        $("[name=mzh_xgcp]").attr("class","mzh_radio_xxk_2_no");
        $(this).attr("class","mzh_radio_xxk_2_yes");
    })

    /* 2-3-1（订单详情-申请退款）已发货 */
    $("#mzh_no").click(function(){
        $("#mzh_no_hide").show();
    })
    $("#mzh_yes").click(function(){
        $("#mzh_no_hide").hide();
    })

    /* 3-1（预定订单详情）处理预定订单 */
    $("#mzh_bxgcpjg").click(function(){
        $("[name=mzh_cpdj]").hide();
        $("[name=mzh_cpdj_no]").show();
        $("[name=mzh_zjg]").hide();
    })
    $("#mzh_xgcpjg").click(function(){
        $("[name=mzh_cpdj]").show();
        $("[name=mzh_cpdj_no]").hide();
        $("[name=mzh_zjg]").hide();
        $("[name=mzh_zjg_no]").show();
    })
    $("#mzh_xgcpzg").click(function(){
        $("[name=mzh_cpdj]").hide();
        $("[name=mzh_zjg_no]").hide();
        $("[name=mzh_cpdj_no]").show();
        $("[name=mzh_zjg]").show();
    })
	
	 /* 发布产品 搜索  5.20加  XH */
	
	$('.sel_jieguo ul li').each(function(){
		$(this).click(function(){
			$('.sel_jieguo ul li').attr('class','');
			$(this).attr('class','nows_sel');
		})
	})
	
})







