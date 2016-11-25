package com.okwei.pay.web.filter;

import com.okwei.web.filter.BaseSSOFilter;

public class SSOFilter extends BaseSSOFilter {

    public SSOFilter(){
        super.isEnabled = false;
        super.isSubEnabled = false; 
    } 
    
  

}
