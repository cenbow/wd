/**
 * 正则表达式 
 */
	var regx;
	function JudgePositiveInteger(num)
	{   
		if(num == null || num == "")
			return false;
		regx = /^[0-9]\d*$/;
		return regx.test(num);
	}
	/**
	 * 验证金额
	 */
	function JudgeAmount(amount)
	{
	   if(amount == null || amount == "")
			return false;
		regx = /^[0-9]*(\.[0-9]{1,2})?$/;
		return regx.test(amount);
	}
	/**
	 * 验证url
	 */
	function IsURL (str_url) {
		var strRegex = "[a-zA-z]+://[^\s]*"
		var re=new RegExp(strRegex);
		if (re.test(str_url)) {
		return (true);
		} else {
		return (false);
		}
	} 
	/**
	 * int的最大值
	 */
	function MaxInt(num)
	{   
		var result = true; 
		if(num > 2147483647)
		{
			result = false;
		}
		return result;
	}
	/**
	 * short的最大值 32767
	 */
	function MaxShort(num)
	{
		var result = true;
		if(num > 32767)
		{
			result = false;
		}
		return result;
	}
	//判断手机号码
	function JudgePhone(mobilePhone)
	{  
    	if(JudgePositiveInteger(mobilePhone) == false || mobilePhone == null || mobilePhone == ""
	    	|| mobilePhone.length != 11)
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
	}
	//判断是否为空
	function isNull(content)
	{
		if(content == null || content == "" || "undefined" == content || content == typeof(content))
		{
			return true;
		}
		return false;
	}
	//判断身份证号码
	function checkIDCard(idCard)
	{   
		if(isNull(idCard) == true)
		{
			return false;
		}
		var idCarRegex = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
		var re = new RegExp(idCarRegex);
		if (re.test(idCard)) {
			return true;
		} 
		else {
			return false;
		}
	}
	