package com.okwei.restful.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sun.misc.BASE64Encoder;

import com.okwei.restful.enums.ActionTypeEnum;
import com.okwei.restful.utils.AppSettingUtil;
import com.okwei.restful.utils.DateOperate;
import com.okwei.restful.utils.ImgDomain;
import com.okwei.restful.utils.ImgProcess;
import com.okwei.restful.utils.JsonUtil;
import com.okwei.restful.utils.ParseHelper;
import com.okwei.restful.vo.BackImgModle;
import com.okwei.restful.vo.BaseReturnModle;
import com.oreilly.servlet.MultipartRequest;

/**
 * ES Search搜索产品
 * 
 * @author xiehz
 *
 */
@RequestMapping("/uploadimage")
@RestController
public class UploadImageController {

	@ResponseBody
	@RequestMapping(value = "/upload", method = { RequestMethod.POST, RequestMethod.GET })
	public String upload(HttpServletRequest request) {
		BackImgModle rq = new BackImgModle();
		int cateInt = ParseHelper.toInt(request.getParameter("cate"));
		int typeInt = ParseHelper.toInt(request.getParameter("type"));
		String file = request.getParameter("file");
		if (cateInt<=0&&( file == null || "".equals(file))) {// 通过文件形式传输
			BaseReturnModle modle = imgReturnBase64(request);
			if (modle.getStatus() == 1) {
				file = modle.getStatusReason();
				Map<String, Object> map = (Map<String, Object>) modle.getBaseModel();
				if (map != null) {
					cateInt = ParseHelper.toInt(String.valueOf(map.get("cate")));
					typeInt = ParseHelper.toInt(String.valueOf(map.get("type")));
				}
			} else {
				rq.setStatus(2);
				rq.setStatusReason(modle.getStatusReason());
				return JsonUtil.objectToJsonStrWithUpCase(rq);
			}
		}
		if (cateInt == 0) {
			rq.setStatus(2);
			rq.setStatusReason("没有指定任务!");
			return JsonUtil.objectToJsonStrWithUpCase(rq);
		}
		try {
			if (cateInt == Integer.parseInt(ActionTypeEnum.UploadTempImg.toString())) {// 保存到临时文件
				rq = new ImgProcess().uploadImgToLocal(file, typeInt);
			} else if (cateInt == Integer.parseInt(ActionTypeEnum.DeleteTempFile.toString())) {// 删除临时文件
				rq.setStatus(1);
				rq.setStatusReason("删除成功");
			} else if (cateInt == Integer.parseInt(ActionTypeEnum.SaveToFastDFS.toString())) {// 将临时文件保存到FastDFS并删除临时文件
				if("".equals(file)||null==file){
					rq.setStatus(1);
					rq.setStatusReason("上传成功!");
				}else {
					rq = new ImgProcess().uploadImgToLocal(file, typeInt);
				}
			} else if (cateInt == Integer.parseInt(ActionTypeEnum.UploadMiniImg.toString())) {// 上传颜色的小图
				rq = new ImgProcess().uploadImgToLocal(file, typeInt);
			}else{
				rq = new ImgProcess().uploadImgToLocal(file, typeInt);
				if(rq!=null&&rq.getStatus()==1){
					rq.setImgUrl(ImgDomain.GetFullImgUrl(rq.getImgUrl()));
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			rq.setStatus(-1);
			rq.setStatusReason(e.getMessage());
		}
		return JsonUtil.objectToJsonStrWithUpCase(rq);
	}

	/**
	 * 图片临时保存 获取base64编码后 删除
	 * 
	 * @param request
	 * @return
	 */
	private BaseReturnModle imgReturnBase64(HttpServletRequest request) {
		BaseReturnModle resultMsg = new BaseReturnModle();
		MultipartRequest mr = null;
		// 用来限制用户上传文件大小的
		int maxPostSize = 1 * 100 * 1024 * 1024;
		try {
			String imgBasenew = AppSettingUtil.getSingleValue("imgPathTemp");
			String uploadPath =  DateOperate.getTimeString("yyyyMM") + "/";
			File f = new File(imgBasenew + uploadPath);
			if (!f.isDirectory()) {// 文件路劲是否存在
				String bathString = imgBasenew + "Images/";
				File f1 = new File(bathString);
				if (!f1.isDirectory()) {
					f1.mkdir();
				}
				f.mkdir();
			}
			// logger.error("filesPath:"+imgBasenew+uploadPath);
			mr = new MultipartRequest(request, imgBasenew + uploadPath, maxPostSize, "GBK");
			int cate = ParseHelper.toInt(mr.getParameter("cate"));
			int type = ParseHelper.toInt(mr.getParameter("type"));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cate", cate);
			map.put("type", type);
			Enumeration files = mr.getFileNames();
			// logger.error("files");
			String filename = "";
			String filePath = "";
			String pathFull = imgBasenew + uploadPath;
			while (files.hasMoreElements()) {
				filename = (String) files.nextElement();
				File ff = mr.getFile(filename);

				String fileSuffix = ff.getName().substring(ff.getName().lastIndexOf(".") + 1).toUpperCase();
				if (fileSuffix.equals("JPG") || fileSuffix.equals("JPEG") || fileSuffix.equals("GIF") || fileSuffix.equals("PNG") || fileSuffix.equals("BMP")) {
					filePath = mr.getFilesystemName(filename);
					InputStream in = null;
					byte[] data = null;
					// 读取图片字节数组
					try {
						in = new FileInputStream(pathFull + filePath);
						data = new byte[in.available()];
						in.read(data);
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 对字节数组Base64编码
					BASE64Encoder encoder = new BASE64Encoder();
					// return ;//返回Base64编码过的字节数组字符串
					resultMsg.setStatus(1);
					resultMsg.setStatusReason(encoder.encode(data));
					resultMsg.setBaseModel(map);
					// 删除临时文件
					// File ff = mr.getFile(filename);

					if (ff.isFile() && ff.exists()) {
						 ff.delete();
					}

				} else {
					resultMsg.setStatus(-1);
					resultMsg.setStatusReason("上传文件格式不正确！");
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			resultMsg.setStatus(-1);
			resultMsg.setStatusReason(e.getMessage());
		}
		return resultMsg;
	}


}
