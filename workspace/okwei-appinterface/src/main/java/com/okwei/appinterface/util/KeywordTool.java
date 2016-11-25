package com.okwei.appinterface.util;

import java.util.Date;
import java.util.HashMap;

import com.okwei.util.RedisUtil;

public class KeywordTool {

	private volatile static KeywordTool instance;  
	private KeywordTool (){
		String[] keyWords = getWords();
		
		if(keyWords!=null)
		{
			/* 增加敏感词组 */
			addKeywords(keyWords);
			/* 设置匹配方式 */
			setMatchType(2);
		}
	}  
    public static KeywordTool getInstance() {  
	    if (instance == null) {  
	        synchronized (KeywordTool.class) {  
	        if (instance == null) {  
	        	instance = new KeywordTool();  
	        }  
	        }  
	    }  
	    return instance;  
    }  

	
	
	private HashMap keysMap = new HashMap();
	private int matchType = 1; // 1:最小长度匹配 2：最大长度匹配

	/**
	 * 构造关键字树状DFA图
	 */
	public void addKeywords(String[] keywords) {
		for (int i = 0; i < keywords.length; i++) {
			String key = keywords[i];
			HashMap nowhash = keysMap;
			for (int j = 0; j < key.length(); j++) {
				char word = key.charAt(j);
				Object wordMap = nowhash.get(word);
				if (wordMap != null) {
					nowhash = (HashMap) wordMap;
				} else {
					HashMap newWordHash = new HashMap();
					newWordHash.put("isEnd", "0");
					nowhash.put(word, newWordHash);
					nowhash = newWordHash;
				}
				if (j == key.length() - 1) {
					nowhash.put("isEnd", "1");
				}
			}
		}
	}

	public void resetKeywords(){
		keysMap = new HashMap();
		String[] keyWords = getWords();
		/* 增加敏感词组 */
		addKeywords(keyWords);
		/* 设置匹配方式 */
		setMatchType(2);
	}
	/**
	 * 重置关键词
	 */
	public void clearKeywords() {
		keysMap = new HashMap();
	}

	/**
	 * 检查一个字符串从begin位置起开始是否有keyword符合， 如果有符合的keyword值，返回值为匹配keyword的长度，否则返回零
	 * flag 1:最小长度匹配 2：最大长度匹配
	 */

	public int checkKeyWords(String txt, int begin, int flag) {
		HashMap nowhash = keysMap;
		int maxMatchRes = 0;
		int res = 0;
		for (int i = begin; i < txt.length(); i++) {
			char word = txt.charAt(i);
			Object wordMap = nowhash.get(word);
			if (wordMap != null) {
				res++;
				nowhash = (HashMap) wordMap;
				if (((String) nowhash.get("isEnd")).equals("1")) {
					if (flag == 1) {
						return res;
					} else {
						maxMatchRes = res;
					}
				}
			} else {
				return maxMatchRes;
			}
		}
		return maxMatchRes;
	}

	/**
	 * 返回txt中关键字的列表
	 */
	public HashMap getTxtKeyWords(String txt) {
		HashMap res = new HashMap();
		for (int i = 0; i < txt.length();) {
			int len = checkKeyWords(txt, i, matchType);
			if (len > 0) {
				Object obj = res.get(txt.substring(i, i + len));
				if (obj == null) {
					res.put(txt.substring(i, i + len), new Integer(1));
				} else {
					Integer count = new Integer(((Integer) obj).intValue() + 1);
					res.put(txt.substring(i, i + len), count);
				}
				i += len;
			} else {
				i++;
			}
		}
		return res;
	}

	/**
	 * 仅判断txt中是否有关键字
	 */
	public boolean isContentKeyWords(String txt) {
		for (int i = 0; i < txt.length(); i++) {
			int len = checkKeyWords(txt, i, 1);
			if (len > 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * flag: 1:将txt中的关键字替换成指定字符串 2：将txt中的关键字每个字都替换成指定的字符串
	 */
	public String getReplaceStrTxtKeyWords(String txt, String replacestr,
			int flag) {
		StringBuffer res = new StringBuffer();
		for (int i = 0; i < txt.length();) {
			int len = checkKeyWords(txt, i, matchType);
			if (len > 0) {
				if (flag == 2)
					for (int j = 0; j < len; j++) {
						res.append(replacestr);
					}
				if (flag == 1)
					res.append(replacestr);
				i += len;
			} else {
				res.append(txt.charAt(i));
				i++;
			}
		}
		return res.toString();
	}

	/**
	 * 敏感词过滤公共方法返回敏感过滤结果 此方法用于在action 中对于敏感文本的过滤 公共方法(方便调用)
	 * 
	 * @return
	 * @throws IOException
	 *             txt:文本
	 */
	public static String getFilterWords(String txt) {
		/* 实例化敏感词对象 */
		KeywordTool filter = new KeywordTool();
		/* 得到敏感词库 */
		String[] keyWords = getWords();
		/* 增加敏感词组 */
		filter.addKeywords(keyWords);
		/* 设置匹配方式 */
		filter.setMatchType(2);
		/* 返回关键字替换处理后的文本 */
		String result = filter.getReplaceStrTxtKeyWords(txt, "*", 2);
		return result;
	}

	/**
	 * 得到敏感词数组
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String[] getWords() {
		// 获取Redis缓存里面的内容
		String words = RedisUtil.getString("filterkeywordset");
		if(words==null)
			return null;
		String[] keyWords = words.split(",");
		return keyWords;
	}

	public HashMap getKeysMap() {
		return keysMap;
	}

	public void setKeysMap(HashMap keysMap) {
		this.keysMap = keysMap;
	}

	public int getMatchType() {
		return matchType;
	}

	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}

	// /**
	// * 测试程序写入properties
	// *
	// * @param filePath
	// * @param parameterName
	// * @param parameterValue
	// */
	// // 写入properties信息
	// public static void writeProperties(String filePath, String parameterName,
	// String parameterValue) {
	// Properties prop = new Properties();
	// try {
	// InputStream fis = new FileInputStream(filePath);
	// // 从输入流中读取属性列表（键和元素对）
	// prop.load(fis);
	// // 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
	// // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
	// OutputStream fos = new FileOutputStream(filePath);
	// prop.setProperty(parameterName, parameterValue);
	// // 以适合使用 load 方法加载到 Properties 表中的格式，
	// // 将此 Properties 表中的属性列表（键和元素对）写入输出流
	// prop.store(fos, "Update '" + parameterName + "' value");
	// } catch (IOException e) {
	// System.err.println("Visit " + filePath + " for updating "
	// + parameterName + " value error");
	// }
	// }

	// /**
	// * Description: 测试程序 1、在处理小数据量该关键词处理程序与传统遍历字符串效率等同
	// * 2、处理大数据量数据时，传统比较方式时间开销取决于关键字库长度与文本长度的乘积、而用本关键词法时间开销基本上只取决于文本长度
	// *
	// * @throws IOException
	// */
	// public static void main(String[] args) throws IOException {
	// /* 实例化敏感词对象 */
	// KeywordTool filter = new KeywordTool();
	// // /*路径读取(WEB-INF)目录下*/
	// String url = filter.getClass().getResource("").getPath().replaceAll(
	// "%20", " ");
	// String path = url.substring(0, url.indexOf("WEB-INF"))
	// + "WEB-INF/words.properties";
	// writeProperties(
	// path,
	// "words",
	// "爱女人,爱液,按摩棒,拔出来,爆草,包二奶,暴干,暴奸,暴乳,爆乳,暴淫,屄,被操,被插,被干,逼奸,仓井空,插暴,操逼,操黑,操烂,肏你,肏死,操死,操我,厕奴,插比,插b,插逼,插进,插你,插我,插阴,潮吹,潮喷,成人电影,成人论坛,成人色情,成人网站,成人文学,成人小说,艳情小说,成人游戏,吃精,赤裸,抽插,扌由插,抽一插,春药,大波,大力抽送,大乳,荡妇,荡女,盗撮,多人轮,发浪,放尿,肥逼,粉穴,封面女郎,风月大陆,干死你,干穴,肛交,肛门,龟头,裹本,国产av,好嫩,豪乳,黑逼,后庭,后穴,虎骑,花花公子,换妻俱乐部,黄片,几吧,鸡吧,鸡巴,鸡奸,寂寞男,寂寞女,妓女,激情,集体淫,奸情,叫床,脚交,金鳞岂是池中物,精液,就去日,巨屌,菊花洞,菊门,"
	// +
	// "巨奶,巨乳,菊穴,开苞,口爆,口活,口交,口射,口淫,裤袜,狂操,狂插,浪逼,浪妇,浪叫,浪女,狼友,聊性,流淫,铃木麻,凌辱,漏乳,露b,乱交,乱伦,轮暴,轮操,轮奸,裸陪,买春,美逼,美少妇,美乳,美腿,美穴,美幼,秘唇,迷奸,密穴,蜜穴,蜜液,摸奶,摸胸,母奸,奈美,奶子,男奴,内射,嫩逼,嫩女,嫩穴,捏弄,女优,炮友,砲友,喷精,屁眼,品香堂,前凸后翘,强jian,强暴,强奸处女,情趣用品,情色,拳交,全裸,群交,惹火身材,人妻,人兽,日逼,日烂,肉棒,肉逼,肉唇,肉洞,肉缝,肉棍,肉茎,肉具,揉乳,肉穴,肉欲,乳爆,乳房,乳沟,乳交,乳头,三级片,骚逼,骚比,骚女,骚水,骚穴,色逼,色界,色猫,色盟,色情网站,色区,色色,色诱,色欲,色b,少年阿宾,少修正,射爽,射颜,食精,释欲,兽奸,兽交,"
	// +
	// "手淫,兽欲,熟妇,熟母,熟女,爽片,爽死我了,双臀,死逼,丝袜,丝诱,松岛枫,酥痒,汤加丽,套弄,体奸,体位,舔脚,舔阴,调教,偷欢,偷拍,推油,脱内裤,文做,我就色,无码,舞女,无修正,吸精,夏川纯,相奸,小逼,校鸡,小穴,小xue,写真,性感妖娆,性感诱惑,性虎,性饥渴,性技巧,性交,性奴,性虐,性息,性欲,胸推,穴口,学生妹,穴图,亚情,颜射,阳具,杨思敏,要射了,夜勤病栋,一本道,一夜欢,一夜情,一ye情,阴部,淫虫,阴唇,淫荡,阴道,淫电影,阴阜,淫妇,淫河,阴核,阴户,淫贱,淫叫,淫教师,阴茎,阴精,淫浪,淫媚,淫糜,淫魔,淫母,淫女,淫虐,淫妻,淫情,淫色,淫声浪语,淫兽学园,淫书,淫术炼金士,淫水,淫娃,淫威,淫亵,淫样,淫液,淫照,阴b,应召,幼交,幼男,幼女,欲火,欲女,玉女心经,"
	// +
	// "玉蒲团,玉乳,欲仙欲死,玉穴,援交,原味内衣,援助交际,张筱雨,招鸡,招妓,中年美妇,抓胸,自拍,自慰,作爱,18禁,99bb,a4u,a4y,adult,amateur,anal,a片,fuck,gay片,g点,g片,hardcore,h动画,h动漫,incest,porn,secom,sexinsex,sm女王,xiao77,xing伴侣,tokyohot,yin荡,胡的接班人,钦定接班人,习近平,平近习,xjp,习太子,习明泽,老习,温家宝,温加宝,温x,温jia宝,温宝宝,温加饱,温加保,张培莉,温云松,温如春,温jb,胡温,胡x,胡jt,胡boss,胡总,胡王八,hujintao,胡jintao,胡j涛,胡惊涛,胡景涛,胡紧掏,湖紧掏,胡紧套,锦涛,hjt,胡派,胡主席,刘永清,胡海峰,胡海清,江泽民,民泽江,江胡,江哥,江主席,江书记,江浙闽,江沢民,江浙民,择民,则民,茳泽民,zemin,"
	// +
	// "ze民,老江,老j,江core,江x,江派,江zm,jzm,江戏子,江蛤蟆,江某某,江贼,江猪,江氏集团,江绵恒,江绵康,王冶坪,江泽慧,邓小平,平小邓,xiao平,邓xp,邓晓平,邓朴方,邓榕,邓质方,毛泽东,猫泽东,猫则东,chairmanmao,猫贼洞,毛zd,毛zx,z东,ze东,泽d,zedong,毛太祖,毛相,主席画像,改革历程,朱镕基,朱容基,朱镕鸡,朱容鸡,朱云来,李鹏,李peng,里鹏,李月月鸟,李小鹏,李小琳,华主席,华国,国锋,国峰,锋同志,白春礼,薄熙来,薄一波,蔡赴朝,蔡武,曹刚川,常万全,陈炳德,陈德铭,陈建国,陈良宇,陈绍基,陈同海,陈至立,戴秉国,丁一平,董建华,杜德印,杜世成,傅锐,郭伯雄,郭金龙,贺国强,胡春华,耀邦,华建敏,黄华华,黄丽满,黄兴国,回良玉,贾庆林,贾廷安,靖志远,李长春,"
	// +
	// "李春城,李建国,李克强,李岚清,李沛瑶,李荣融,李瑞环,李铁映,李先念,李学举,李源潮,栗智,梁光烈,廖锡龙,林树森,林炎志,林左鸣,令计划,柳斌杰,刘奇葆,刘少奇,刘延东,刘云山,刘志军,龙新民,路甬祥,罗箭,吕祖善,马飚,马恺,孟建柱,欧广源,强卫,沈跃跃,宋平顺,粟戎生,苏树林,孙家正,铁凝,屠光绍,王东明,汪东兴,王鸿举,王沪宁,王乐泉,王洛林,王岐山,王胜俊,王太华,王学军,王兆国,王振华,吴邦国,吴定富,吴官正,无官正,吴胜利,吴仪,奚国华,习仲勋,徐才厚,许其亮,徐绍史,杨洁篪,叶剑英,由喜贵,于幼军,俞正声,袁纯清,曾培炎,曾庆红,曾宪梓,曾荫权,张德江,张定发,张高丽,张立昌,张荣坤,张志国,赵洪祝,紫阳,周生贤,周永康,朱海仑,政治局常委,中纪委,主席像,"
	// +
	// "总书记,中南海,大陆当局,中国当局,北京当局,共产党,党产共,gcd,共贪党,gongchandang,阿共,共一产一党,产党共,公产党,工产党,共c党,共x党,共铲,供产,共惨,供铲党,供铲谠,供铲裆,共残党,共残主义,共产主义的幽灵,拱铲,老共,中共,中珙,中gong,gc党,贡挡,gong党,g产,狗产蛋,共残裆,恶党,邪党,共产专制,共产王朝,裆中央,土共,土g,共狗,g匪,共匪,仇共,communistparty,政府,症腐,政腐,政付,正府,政俯,政一府,政百度府,政f,zhengfu,政zhi,挡中央,档中央,中央领导,中国zf,中央zf,国wu院,中华帝国,gong和,大陆官方,北京政权,李愚蠢,中国猪,台湾猪,进化不完全的生命体,震死他们,贱人,装b,大sb,傻逼,傻b,煞逼,煞笔,刹笔,傻比,沙比,欠干,婊子养的,我日你,"
	// +
	// "我操,我草,卧艹,卧槽,爆你菊,艹你,cao你,他妈,它妈,她妈,他爸,它爸,她爸,他爹,它爹,她爹,他妈的,它妈的,她妈的,他爸的,它爸的,她爸的,他爹的,它爹的,她爹的,你他妈,真他妈,别他吗,草你吗,草你丫,操你妈,擦你妈,操你娘,操他妈,日你妈,干你妈,干你娘,娘西皮,狗操,狗草,狗杂种,狗日的,操你祖宗,操你全家,操你大爷,妈逼,你麻痹,麻痹的,妈了个逼,马勒,狗娘养,贱比,贱b,下贱,死全家,全家死光,全家不得好死,全家死绝,白痴,无耻,sb,杀b,你吗b,你妈的,婊子,贱货,人渣,混蛋,媚外,和弦,兼职,限量,铃声,性伴侣,男公关,火辣,精子,射精,诱奸,强奸,做爱,性爱,发生关系,按摩,快感,处男,猛男,少妇,屌,屁股,下体,a片,内裤,浑圆,咪咪,发情,刺激,白嫩,粉嫩,兽性,风骚,呻吟,sm,阉割,高潮,裸露,不穿,一丝不挂,脱光,干你,干死,我干,中日没有不友好的,木牛流马的污染比汽车飞机大,"
	// +
	// "他们嫌我挡了城市的道路,当官靠后台,警察我们是为人民服务的,中石化说亏损,做人不能太cctv了,领导干部吃王八,工商税务两条狼,公检法是流氓,公安把秩序搞乱,剖腹一刀五千几,读不起选个学校三万起,父母下岗儿下地,裙中性运动,安眠酮,代血浆,普萘洛尔,呋塞米,西布曲明,testosterone,胰岛素样生长因子,促红细胞生成素,地西泮,尼可刹米,甲睾酮,adrenaline,erythropoietin,地奈德,莫达非尼,氯噻嗪,苯巴比妥,促性腺激素,泼尼松,麻黄草,雄烯二醇,地塞米松,tamoxifen,strychnine,androst,新型毒品,杜冷丁,兴奋剂,mdma,海洛因,海luo因,heroin,diamorphine,diacetylmorphine,鸦片,阿芙蓉,咖啡因,cocain,三唑仑,美沙酮,麻古,k粉,凯他敏,ketamine,冰毒,"
	// +
	// "苯丙胺,cannabis,大麻,爱他死,氯胺酮,benzodiazepines,甲基安非他明,安非他命,吗啡,morphine,摇头丸,迷药,乖乖粉,narcotic,麻醉药,精神药品 ,福音会,中国教徒,统一教,观音法门,统一教,盘古,李洪志,志洪李,李宏志,轮功,法轮,轮法功,三去车仑,氵去车仑,发论工,法x功,法o功,法0功,法一轮一功,轮子功,车仑工力,法lun,fa轮,法lg,fl功,falungong,大法弟子,大纪元,dajiyuan,明慧网,明慧周报,正见网,新唐人,伪火,退党,tuidang,退dang,超越红墙,自fen,真善忍,九评,9评,9ping,九ping,jiuping,藏字石,集体自杀,自sha,zi杀,suicide,titor,逢8必灾,逢八必灾,逢9必乱,逢九必乱,朱瑟里诺,根达亚文明,诺查丹玛斯,人类灭亡进程表,按照马雅历法,推背图,推bei图,"
	// +
	// "济世灵文,诸世纪,钢珠狗,成人快播电影,买卖假证,欧美无码区,出售军警用品,有偿换肾,汽狗专销,麻果出售,雷管供应,引爆器供应,黑索金供应,无码性爱,有码性爱,迅雷日本无码,买卖大冰砖,久久情色,淫色导航,仿真枪改装,假币朝鲜版代理,代理假币,供应改号软件,午夜淫虎网,裸聊妹,军刀出售,出售猎刀,猎刀买卖,神风大乱交,色吧社区,专售咖啡因,专售氯胺酮,提供摇头丸,视频裸聊,投注取现,真钱下注,麻因出售,性爱漫画,批发军刀,网购汽枪,氧化汞专卖,批量出售银行卡,批量收购银行卡,军官证出售,警察证出售,上门做爱服务,出售车主信息,出售客户信息,淫色妹妹导航,悠悠成人社区,色狼友综合网,人皮专卖,丁香成人网,乱伦电影下载,色色电影网站,色情在线电影,真人激情裸聊,色播成人,真人裸聊服务,超高清成人电视棒,"
	// +
	// "色老汉导航,午夜激情片,一品色导航,色情影视基地,淫色影院,性爱动漫,乱伦影院,草榴成人导航,快播女优,淫播网,无码快播,淫色基地,色吧亚洲,性爱动画,猎枪网,性交电影,小姐威客,国产乱伦片,出售马古,批发马古,马古配方,汽狗网,提供开锁器,批发汽狗,狙击汽枪出售,连弩出售,专售可卡因,色播电影网,小姐出台服务,性爱动画片,网络赌博机,18禁成人游戏,成人游戏下载,激情电影种子,卖钢珠槍,成人高清BT,淫色电影,淫洞,淫间道,发票代售,汽枪原理图,猎枪出售qq,出售自制气枪qq,汽枪制造图纸,汽狗批发,PCP专卖,pcp配件,仿真气枪专卖店,诚信汽狗专卖,供应白黄牙签QQ,出售三唑仑片QQ,供应大小冰砖QQ,批发白黄牙签QQ,供应钻石冰糖QQ,供应K粉QQ,92手狗QQ,买卖身份证QQ,万能开锁器QQ,楼凤QQ,假发票QQ,雇杀手QQ,"
	// +
	// "提供十字开锁器QQ,专卖开锁翻转枪QQ,白牙签验货付款,黄牙签验货付款,奶油冰验货付款,制造意外QQ,职业打手QQ,出售高考考生报名信息,专卖麻黄素QQ,杀手雇佣网QQ,代理假发票,代理假发票QQ,假发票专卖QQ,假发票专卖,出售军品刀具QQ,专卖军品刀具,专卖军品刀具QQ,小姐包夜服务QQ,办理军官证QQ,小冰砖QQ,水果冰QQ,flexispy官方网站,出售信用卡用户资料,出售信用卡用户信息,出售纯冰QQ,出售银行卡用户资料,出售银行卡用户信息,批发GHB水,出售改号器,改号软件出售,批发三唑仑片,出售氯胺酮qq,老板数据QQ,出售植物冰QQ,出售化学冰QQ,成人视频下载,成人电影下载,电警棍厂家直销,警用手铐厂家直销,Flexispy官网,买卖高仿人皮面具qq,代办各种证件qq,销售各类银行卡,提供军用刀具,冰zhuan销售QQ,"
	// +
	// "野外军刀专卖,杀手替人报仇,考生名录,提供高仿真人皮面具,提供高仿真人皮面具QQ,供应高仿人皮面具,供应高仿人皮面具QQ,号码任意显示QQ,气gou专卖,气gou专卖QQ,专卖气狗,专卖气狗QQ,批发猎刀QQ,专卖猎刀,猎刀专卖QQ,成人动画下载,身份证代办QQ,手铐货到付款,警服货到付款,警用电棍专卖,警用手铐qq,警用电棍qq,军品刀具批发,批发k粉qq,出售54枪QQ,供應假人民幣QQ,冰毒供应QQ,提供股民数据QQ,兼职楼凤QQ,成人禁书,A片下载,专业删帖QQ,办理真毕业证,供应雷管QQ,批发黄白牙签,警用电击棒销售QQ,水果冰买卖QQ,户外军刀销售QQ,汽车开锁器销售QQ,一夜情服务QQ,3D成人电影,Qvod情色电影,国产AV片,替人报仇公司,成人动漫BT,AV激情视频,出售居民身份证,成人av电影,成人无码DVD,"
	// +
	// "真人娱乐城QQ,真人娱乐老虎机,真人娱乐老虎机QQ,代办假学历QQ,92气狗QQ,54气狗QQ,64气狗QQ,爆竹专卖QQ,销售烟花爆竹QQ,销售烟花QQ,供应氰化钠QQ,TNT买卖,供应TNT,高仿毕业证,代办假学历,替人报仇QQ,替人报仇电话,替人报仇QQ,性爱聊天室,代删负面贴子,最新亚洲无码合集,东京热合集,最新东京热全集,专业制作高仿硅胶面具,最新假币销售,台湾最新版假币,裸聊qq,成人色情网站,性爱视频,高仿真面具出售,警用品专卖网,spyera软件供应,spyera软件出售,spyera软件下载,spyera软件官方网站,代理台湾假币,代理朝鲜假币,销售楼盘业主资料QQ,销售车主资料QQ,销售车主资料,出售公司法人资料,销售公司法人资料,出售法人手机号码,出售车主名录QQ,出售车主名录,供应车主名录,供应车主名录QQ,销售车主名录,"
	// +
	// "销售车主名录QQ,出售高档客户资料,出售法人通讯录,出售老板个人资料,出售老板通讯录,销售老板通讯录,出售老板通讯录QQ,销售老板通讯录QQ,出售老板个人资料QQ,销售法人通讯录QQ,出售法人通讯录QQ,销售法人通讯录,销售公司法人资料QQ,出售公司法人资料QQ,出售楼盘业主资料QQ,销售楼盘业主资料,出售车主资料,出售车主资料QQ,出售车主资源QQ,出售车主资源,出售车主名单,出售车主名单QQ,出售业主名单,销售车主名单,销售业主名单,销售业主名单QQ,销售车主名单QQ,出售业主名单QQ,销售个人商务数据资料QQ,销售个人商务数据资料,出售个人商务数据资料QQ,出售个人商务数据资料,供应高档客户资料,供应高档客户资料QQ,出售企业名录资料,出售银行客户资料,出售企业名录资料QQ,销售高档客户资料QQ,"
	// +
	// "销售高档客户资料,出售银行客户资料QQ,供应银行客户资料,供应银行客户资料QQ,出售电话营销数据,出售电话营销数据QQ,销售电话营销数据,销售电话营销数据QQ,出售高档客户资料QQ,供应公司法人资料QQ,供应法人通讯录,供应个人商务数据资料QQ,供应车主名单,供应车主名单QQ,供应业主名单,供应业主名单QQ,供应老板通讯录,供应老板通讯录QQ,供应老板个人资料QQ,供应法人通讯录QQ,供应企业名录资料QQ,供应企业名录资料,供应电话营销数据,供应电话营销数据QQ,供应个人商务数据资料,供应老板个人资料,供应法人手机号码,供应公司法人资料,供应车主资料QQ,供应楼盘业主资料,供应楼盘业主资料QQ,供应车主资料,供应车主资源QQ,供应车主资源,提供股民数据,网络老虎机QQ,网络赌博机QQ,出售股民电话资源,爆竹出售QQ,"
	// +
	// "出售爆竹QQ,专卖军刀QQ,专卖军刀,美女裸聊QQ,成人AV无码,成人AV无码QQ,批发警服QQ,警服批发QQ,批发警棍QQ,出售网络电视棒QQ,供应麻古QQ,供应大麻,供应大麻QQ,买卖54枪QQ,气Gun专卖,欧洲无码AV下载,亚洲无码AV下载,办证服务QQ,办证服务电话,假警官证QQ,手机改号软件QQ,任意修改主叫号码,来电号码任意显示,供應假人民幣,钢珠弹专卖qq,出售自制连弩qq,出售匕首qq,猎刀销售qq,军刀专卖qq,高仿真人民币qq,高仿假鈔銷售,假鈔qq,假鈔出售qq,专业帮人打架报仇,专业帮人打架报仇QQ,假发票出售QQ,出售假钞票QQ,专业警棍出售QQ,专业警棍出售,手铐出售QQ,出售专业手铐,办证服务公司,出售专业手铐QQ,出售警用头盔,出售警用头盔QQ,专业开锁QQ,出售开锁工具QQ,专业开锁工具出售,"
	// +
	// "专业开锁工具出售QQ,专业网络删帖,冰DU出售,真钱网络赌场,手机号码修改软件,出售单管猎槍,汽槍出售,高清AV,网上真钱娱乐场,办理假毕业证,出售阻击枪,网络公关删贴,插BB成人电影,哇哇影院,假人民幣銷售,供应氰化钠,山奈钾出售,防身刀具专卖,亚洲A片,丁香花成人网站,快播A片网,无码AV,办假驾驶证QQ,办假驾照,办假学历,快播AV电影,动漫A片,黄色A片,冰ZHUAN出售,3P视频,多P视频,激情QQ视频,代办大本文凭,成人短片,乱交系列,办假身份证qq,办假毕业证qq,办假驾照QQ,办假学历QQ,找小姐上门服务QQ,找小姐上门QQ,找小姐包夜QQ,少妇包夜QQ,成人QVOD,警棍销售,成人av大片,办假证QQ,气狗qq,54狗qq,64狗qq,92狗qq,气狗销售qq,爆竹专卖,专卖开山刀,专卖开山刀QQ,出售高清成人电视棒,财税代理公司QQ,"
	// +
	// "出售雷管炸药QQ,草榴社區,FlexiSPY,销售烟花,成人高清电视棒,出售短狗QQ,出售公司电话资源,任意改号码,任意改号码QQ,提供K粉qq,警服批发,出售中考考生信息,成人AV网,成人AV,氰化汞出售,氰化氢出售,订做人皮面具,快播成人,氰化钾供应qq,氰化钠供应,氰化钾供应,氰化钙供应,氰化镉供应,spyera软件,办理大专文凭,冰毒配方qq,供应冰毒QQ,帮人报仇QQ,改号器QQ,白牙签销售QQ,出售白牙签QQ,冰毒供应,冰毒供应qq,批发麻果,批发缅果,批发缅古,色情武侠小说,硝酸汞供应,氯化汞供应,碘化汞供应,金属铊出售,砷化氢出售,砷化物供应,供应世界名刀,供应刺刀M9,卖角膜qq,找打手qq,提供打手,出气报仇,找职业杀手qq,警用手拷出售QQ,批发手铐QQ,解码器出售,解码器专卖qq,干扰器出售qq,专业制作文凭,无码光碟,"
	// +
	// "多人乱交,多人群交,进口双管枪qq,出售杜冷丁qq,砍刀专卖网,警服出售电话,专业警官证制作,日本武士刀批发,销售烟花爆竹,开山刀出售qq,蝴蝶甩刀出售qq,冷钢出售qq,诚信办理文凭,出售合成k粉,炸药制作的方法,狼客免费成人视频网,SEX性吧有你,出售业主资料,出售社区业主名单,日本无码DVD,最新日本无码,无码成人,冰钻石qq,代开正规發票,代开正规发票qq,代开正规發票qq,高清裸聊现场,代办文凭QQ,真实文凭QQ,开山刀专卖QQ,快速办理证件QQ,蝴蝶甩刀专卖QQ,冷钢专卖QQ,财税代理公司,出售仿真汽枪,警官证出售qq,出售金属枪模,出售专业警枪,快播成人视频,电警棍出售qq,警用手拷出售qq,警用装备出售qq,警服出售qq,出售朝鲜冰qq,出售麻果qq,小姐援交信息,进口汽狗专卖,小姐援交服务,原装汽狙狗,"
	// +
	// "原装cfx气枪,原装工字牌气枪,原装气枪,原装电动长狗,原装竞技气枪,原装广州三箭,原装锡峰气枪,原装仿真气枪,原装秃鹰气枪,原装比赛用枪,原装娥眉气枪,原装狩猎气枪,原装西班牙气枪,原装猎枪,原装双管枪,原装英国骚本,原装PCP气枪,裸聊美女qq,供应杜冷丁,东京无码下载,枪支零部件出售,冰毒出售qq,冰毒销售qq,冰毒配方销售,假1元硬币买卖,朝鲜版假钞买卖,假硬币买卖,Xwodi破解软件,购买xwodi,xwodi软件官方网,xwodi软件版下载,肾买卖QQ,包射视频,包爽包射,诚信办证电话,出售冰zhuan,供应肾qq,卖眼角膜qq,做爱聊天室,做爱视频,qvod性爱视频,真人色片,高仿真人皮面具qq,专卖高仿真人皮面具,刀具交易网,监听手机qq,办理假文凭qq,出售溜冰壶qq,溜冰妹联系qq,代办发票,麻古电话,出售K粉电话,"
	// +
	// "麻黄素出售qq,麻黄素qq,出售摇头丸qq,批发猎刀,植物冰出售qq,专销人皮面具,专销高仿人皮面具,专销高仿人皮面具qq,銷售假人民幣,成人DVD,白牙签电话,出售白牙签qq,出售麻谷qq,出售大冰砖qq,办证qq,假硬币出售qq,供应上海工字气枪,砍刀专卖店,发票qq,假文凭办理,麻谷销售QQ,销售黃牙簽QQ,大冰磚销售QQ,海洛因销售QQ,白牙簽销售QQ,白粉销售QQ,专业制作人皮面具,成人电视棒销售,出售军刀QQ,自动跳刀专卖QQ,手工猎刀专卖QQ,野营军刀出售QQ,QVOD援交片,销售冰砖,批发仿真枪,东京热视频下载,出售小果子qq,出售冰du,警用电击棒销售,水果冰买卖,足球下注,卖假发票,眼角膜价格,商丘单车网,专业办理各种证件,办理证件货到付款,真实有效文凭,办理自考本科文凭,供应手枪qq,出售气狗qq,出售狙击枪qq,"
	// +
	// "供应各式军用枪qq,出售手狗qq,供应步枪qq,1元假硬币,手槍买卖,发票买卖,东京热视频,办理真凭文,短狗狗粮QQ,销售短狗狗粮QQ,电警棍专卖,幼幼电影,供应气枪qq,出售步枪qq,供应猎枪qq,批发警用电击棍,网上赌博机,网上老虎机,网上赌博游戏,18禁情色电影,成人A片下载,成人A片在线观看,全套包夜服务,丝袜小姐按摩,六合彩网上开户,代理增值税发票,激情裸女聊天室,狙击枪专卖网,一本道视频,汽槍买卖,餐饮发票出售,酒店发票出售,QVOD激情电影,快播成人电影,快播av电影,qvod在线成人电影,QVOD幼交片,最新版假人民幣,本科文凭qq,军用手枪专卖网,卖水果冰,出售水果冰qq,批发小冰砖,批发水果冰,出售水果冰,批发K粉,批发冰毒qq,批发军用匕首,性爱电影,办警官证电话,成人游戏,钢珠枪买卖,专卖汽狗,"
	// +
	// "出售X卧底,假钱出售价格,美女出台服务,出售高考考生信息,仿真64手枪专卖,仿真54手枪专卖,出售高仿真枪支,双管猎枪专卖,高压气枪专卖网,骚本气枪出售,出售各种进口气枪,双管猎狗专卖,虎头牌猎狗专卖,一夜情性息,弓弩的制作方法,銀行卡復制器,真人美女视频聊天室,植物冰出售,真实身份证买卖,银行卡买卖,出售真枪,代开增值税醱票,色情游戏,长狗专卖,美女裸图,18禁色情电影,抽插呻吟,出售定制人皮面具,买真文凭QQ,办毕业证找QQ,视频裸聊QQ,美女激情视频,办理会计师证QQ,办理教师资格证QQ,出售高仿真人皮面具qq,成人裸聊聊天室,激情裸聊室,免费A片,夜店兼职女郎,学生妹妹兼职,连环百家乐,气枪专卖商城,太阳城网上娱乐,兼职男妓,在线下注,真人娱乐城,坐台小姐兼职,提供仿真枪,百家乐赌博,"
	// +
	// "真钱龙虎游戏,供应股民数据,卖眼角膜,提供收藏数据,供应电视购物数据,供应一手老人数据,出售开锁器,电购数据,一手老人数据,小姐全套信息,模特全套包夜,代開销售發嘌,代開發嘌,供应银行卡,兼职小姐qq,出售办公用品发票,出售广告费发票,汽车中控干扰器,中控拦截器,汽车拦截解码器,代开建筑发票,骚本气狗专卖,秃鹰气枪专卖网点,办英语六级证,办理英语四级证书,办理证件QQ,销售發票,销售醱票,大专文凭QQ,供应钢珠枪,本科文凭办理,狙击步枪专卖,办职称证,万能麻将机,真实文凭,代办武警驾驶证,办律师执业证,92手槍54式手槍,54式 64式 手枪,办真学历,证件办理,办本科文凭,出售气枪QQ,出售打猎枪,发票代办,出售秃鹰气枪,操,草,2B,cao,草泥马,草呢吗,草泥马,草你妈,cao泥马,cao呢吗,cao泥马,cao你妈,"
	// +"泥马,尼玛,艹");
	// // /*读取xml文件*/
	// // Properties prop = new Properties();//实例化peoperties对象
	// // FileInputStream fis=new FileInputStream(path);//读取内容
	// // prop.load(fis);//填充到实例
	// // String words = new
	// //
	// String(prop.getProperty("words").toString().getBytes("ISO-8859-1"),"UTF-8");
	// // String[] keyWords =words.split(",");
	// // //虚拟200000字符的文本
	// // String txt = "关键字(安建超),其他关键字(宋川)(共产党)";
	// // Date begin =new Date();
	// // /*增加敏感词组*/
	// // filter.addKeywords(keyWords);
	// // printCostTime("增减关键词组DFA时间(毫秒)：",begin);
	// // /*设置匹配方式*/
	// // filter.setMatchType(2);
	// // //判断文本是否含有关键字
	// // System.out.println(filter.isContentKeyWords(txt));
	// // printCostTime("判断时候含有关键字时间(毫秒)：",begin);
	// // /*返回关键字替换处理后的文本*/
	// // filter.getReplaceStrTxtKeyWords(txt, "*", 2);
	// // System.out.println("返回结果:====="+filter.getReplaceStrTxtKeyWords(txt,
	// // "*", 2));
	// // printCostTime("关键字替换处理后时间(毫秒)：",begin);
	// // //返回文本中关键词次数统计
	// // System.out.println("返回结果:+++++++"+filter.getTxtKeyWords(txt));
	// // printCostTime("关键词次数统计时间(毫秒)：",begin);
	// // System.out.println("返回结果：========"+getFilterWords(txt));
	// }
	//
	//
	// /**
	// * 时间统计
	// *
	// * @param name
	// * @param begin
	// */
	// public static void printCostTime(String name, Date begin) {
	// Date now = new Date();
	// System.out.println(name + (now.getTime() - begin.getTime()));
	// }

}
