<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<body class="bg_f3">
<div style="border: 0px;background: none;" class="fr conter_right">
      <jsp:include page="/jsp/walletmgt/header.jsp"/>
      
      <div class="fl conter_right mt_20  bg_white bor_si">
        <div class="wdqb_xxk ndfxs_1-2_border">
            <div class="tab_pc f16">
            <ul>
                <li class="now"><a href="/manage/list?type=8">全部</a><i></i></li>
                <li><a href="#?type=1">收入</a><i></i></li>
                <li><a href="#?type=2">支出</a><i></i></li>
                </ul>
            </div>
        </div>
     
          <div class="rztd_cx">
            <div class="rztd_cx_div">时间：</div>
              <input type="text" class="btn_h24 dis_b fl ">
              <div style="margin: 0 5px;" class="rztd_cx_div">-</div>
              <input type="text" class="btn_h24 dis_b fl  mr_18">
              <div style="height: 27px; margin-left:10px; line-height: 27px;width: 70px;" class="jbzl_dl_qrdz">查询</div>
          </div>
        <!-- 明细 -->
        <table id="mzh_qb_0" style="display: none;" class="mt_20 wdqb_table">
          <tbody><tr class="wdqb_tr">
            <td><b class="ml_20">交易时间</b></td>
            <td><b>收入</b></td>
            <td><b>支出</b></td>
            <td><b>余额</b></td>
            <td><b>备注</b></td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red">+8899.00</span></td>
            <td><b class="ft_blue"></b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red">+8899.00</span></td>
            <td><b class="ft_blue"></b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red"></span></td>
            <td><b class="ft_blue">-1000.00</b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red">+8899.00</span></td>
            <td><b class="ft_blue"></b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10 </p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red">+8899.00</span></td>
            <td><b class="ft_blue"></b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
           <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red">+8899.00</span></td>
            <td><b class="ft_blue"></b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
           <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red">+8899.00</span></td>
            <td><b class="ft_blue"></b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red"></span></td>
            <td><b class="ft_blue">-8899.00</b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td><span class="ft_red"></span></td>
            <td><b class="ft_blue">-8899.00</b></td>
            <td>9988.00</td>
            <td>&nbsp;</td>
          </tr>
        </tbody></table>
        <!-- 收入 -->
        <table id="mzh_qb_1" style="display: none;" class="mt_20 wdqb_table">
          <tbody><tr class="wdqb_tr">
            <td><b class="ml_20">交易时间</b></td>
            <td><b>类型</b></td>
            <td><b>流水号</b></td>
            <td><b>收入</b></td>
            <td><b>余额</b></td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>交易佣金</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+8899.00</span></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>认证佣金</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+8899.00</span></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>提现</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+888.00</span></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>钱包充值</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+8899.00</span></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10 </p>
                <p>13:50</p>
              </div></td>
            <td>购物退款</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+8899.00</span></td>
            <td>9988.00</td>
          </tr>
           <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>任务佣金</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+8899.00</span></td>
            <td>9988.00</td>
          </tr>
           <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>返点</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+8899.00</span></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>扣税</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+213132.00</span></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>支出</td>
            <td>1801231008645263</td>
            <td><span class="ft_red">+231321.00</span></td>
            <td>9988.00</td>
          </tr>
        </tbody></table>
        <!-- 支出 -->
        <table id="mzh_qb_2" style="display: block;" class="mt_20 wdqb_table">
          <tbody><tr class="wdqb_tr">
            <td><b class="ml_20">交易时间</b></td>
            <td><b>类型</b></td>
            <td><b>流水号</b></td>
            <td><b>支出</b></td>
            <td><b>余额</b></td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>交易佣金</td>
            <td>1801231008645263</td>
            <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>认证佣金</td>
            <td>1801231008645263</td>
              <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>提现</td>
            <td>1801231008645263</td>
              <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>钱包充值</td>
            <td>1801231008645263</td>
              <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10 </p>
                <p>13:50</p>
              </div></td>
            <td>购物退款</td>
            <td>1801231008645263</td>
              <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
           <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>任务佣金</td>
            <td>1801231008645263</td>
               <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
           <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>返点</td>
            <td>1801231008645263</td>
               <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>扣税</td>
            <td>1801231008645263</td>
              <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
          <tr>
            <td><div class="wdqb_div ml_20">
                <p>2015/2/10</p>
                <p>13:50</p>
              </div></td>
            <td>支出</td>
            <td>1801231008645263</td>
              <td><b class="ft_blue">-2131.00</b></td>
            <td>9988.00</td>
          </tr>
        </tbody></table>



      </div>
    </div>
</body>
</html>
