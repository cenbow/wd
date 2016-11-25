package com.okwei.bean.enums;
/**
 * 用户身份值 标示 Identity
 * @author Administrator
 *
 */
public enum UserIdentityType {
	/**
	 * 普通用户
	 */
	commonUser(1),//0
	/**
	 *  云商通 供应商
	 */
	yunSupplier(2),//1
	/**
	 * 批发号 供应商
	 */
	batchSupplier(4),//2
	/**
	 * Erp供应商
	 */
	erpSupplier(8),//3
	/**
	 * 工厂号 见习认证员
	 */
	ordinary(16),//4
	/**
	 * 工厂号 正式认证员
	 */
	percent(32),//5
	/**
	 * 批发号认证员
	 */
	batchverifier(64),//6
	/**
	 * 认证点
	 */
	batchverifierport(128),//7
	/**
	 * 市场管理员
	 */
	market(256),//8
	/**
	 * 平台号供应商
	 */
	PlatformSupplier(512),//9
	/**
	 * 品牌号供应商
	 */
	BrandSupplier(1024),//10
	/**
	 * 平台号品牌号代理商
	 */
	Agent(2048),//11
	/**
	 * 落地店
	 */
	Ground(4096),//12
	/**
	 * 认证员代理商
	 */
	VerifierAgent(8192),//13
	
	/**
	 * 代理区品牌号供应商
	 */
	AgentBrandSupplier(16384),//14
	
	/**
	 * 代理区代理
	 */
	AgentBrandAgent(32768),//15
	/**
	 * 城主
	 */
	AgentDuke(65536),//16
	/**
	 * 副城主
	 */
	AgentDeputyDuke(131072),//17
	/**
	 * 队长
	 */
	AgentCaptain(262144);//18
	
	private final int step; 

    private UserIdentityType(int step) { 

         this.step = step; 
    }
    
    @Override
    public String toString()
    {
    	return String.valueOf(this.step);         	
    }
}
