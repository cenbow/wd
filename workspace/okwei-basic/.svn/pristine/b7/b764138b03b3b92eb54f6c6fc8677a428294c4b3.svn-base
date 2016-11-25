package com.okwei.service;

import com.okwei.bean.domain.TFansApply;
import com.okwei.bean.dto.TFansApplyDTO;
import com.okwei.bean.vo.FansApplyVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.agent.BaseResultVO;
import com.okwei.bean.vo.agent.HalfProductVO;
import com.okwei.bean.vo.agent.HeadInfo;
import com.okwei.common.PageResult;



public interface IFansApplyCommonService {

	/**
	 * created by zlp at 2016/10/27
	 * @param weiid
	 * @return
	 */
	TFansApply getTFansApply(Long weiid);

	/**
	 * @author zlp
	 * 统计有多少城市的人加入铁杆朋友圈
	 * @return
	 */
	long count_TFansDistrict();

	/**
	 * 统计全部状态的铁杆朋友圈
	 * @return
	 */
	long count_TFansNumber();
	
	/**
	 *  统计申请通过的铁杆朋友圈
	 * @return
	 */
	long count_TFansPassNumber();
	/**
	 * 保存铁杆朋友圈申请资料
	 * @param ta
	 * @return
	 */
	ReturnModel saveTfansApply(TFansApplyDTO ta);

	/**
	 
	 * @param level
	 * 查询区域的级别, 2. 省 配合 parent =0 ，3.市，配合parent=上级区域 4.区
	 * 配合parent=上级区域 >4表示只查询一个行政区parent=该行政区代号
	 * @param level
	 * @param parent
	 * @param codeType
	 * @return
	 */
	ReturnModel getFansRegionalForPcode(int level, long parent, int codeType,int status);

	/**
	 * @author zlp created by 2016/10/27
	 * 根据省市区得到相应地区的铁杆朋友圈列表
	 * @param province
	 * @param city
	 * @param district
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageResult<FansApplyVO> findFansInfolistBydistrict(Integer province,
			Integer city, Integer district, int pageIndex, int pageSize,int status);

	
	
}
