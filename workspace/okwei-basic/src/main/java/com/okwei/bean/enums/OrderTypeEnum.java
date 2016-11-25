package com.okwei.bean.enums;

public enum OrderTypeEnum {
	/**
	 * 零售 订单（云商通）
	 * */
	Pt(1),
	/**
	 * 供应商进驻
	 * */
	YunGys(2),
	/**
	 * 认证员保证金
	 * */
	YunRzy(3),
	/**
	 * 导航冲值
	 * */
	DaoHang(4),
	/**
	 * 抢票
	 * */
	Qp(5),
	/**
	 * 充值
	 * */
	ChongZhi(6),
	/**
	 * 点餐系统订单
	 * */
	DingCan(7),
	/**
	 * 零售 订单(批发号)
	 * */
	BatchOrder(8),
	/**
	 * 批发订单（ 批发号）
	 * */
	BatchWholesale(9),
	
	/**
	 * 预订单
	 * */
	BookOrder(12),
	/**
	 * 预订单（首款）
	 * */
	BookHeadOrder(10),
	/**
	 * 预定单（ 尾款）
	 * */
	BookTailOrder(11),
	/**
	 * 预订单( 全款)
	 */
	BookFullOrder(15),
	
	/*----------------------------------------*/
	/**
	 * 批发号供应商（进驻）
	 */
	BatchGys(13),
	/**
	 * 批发号认证员（进驻）
	 * */
	BatchRzy(14),
	/**
	 * 批发号和认证员一起(进驻)
	 */
	GysAndVerifier(16), 
	/**
	 *  批发号认证点 重新进驻 不需要缴纳服务费 只缴纳保证金
	 */
	BatchPortNoServiceFee(17),
    /**
     * 工厂号 重新进驻 不需要缴纳服务费 只缴纳保证金
     */
    YunGysNoServiceFee(18),
    /*------------------------------------------------*/
    /**
     * 进货单
     */
    Jinhuo(19),
    /**
     * 铺货单
     */
    Puhuo(20),
    /**
     * 铺货单（首款）
     */
    PuhuoHeader(21),
    /**
     * 铺货单（尾款）
     */
    PuhuoTail(22),
    /**
     * 铺货单（全款）
     */
    PuhuoFull(23),
    /**
     * 零售 订单（平台号）
     */
    RetailPTH(24),
    /**
     * 悬赏订单
     */
    Reward(25),
    /**
     * 平台、品牌号抽点订单
     */
    ChouDian(26),
    /*-------------------------------------*/
    /**
     * 代理区订单（零售）
     */
    RetailAgent(27),
    /**
     * 兑换订单
     */
    Convert(28),
	/**
     * 品牌代理缴费订单
     */
	AgentPayment(29) ,
	/**
	 * 半价购买订单
	 */
	HalfTaste(30)
    ;
	
	private final int step;

	private OrderTypeEnum(int step) {

		this.step = step;
	}

	@Override
	public String toString() {
		return String.valueOf(this.step);
	}
}
