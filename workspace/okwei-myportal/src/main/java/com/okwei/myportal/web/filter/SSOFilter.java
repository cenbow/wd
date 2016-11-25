package com.okwei.myportal.web.filter; 

import com.okwei.web.filter.BaseSSOFilter;

public class SSOFilter extends BaseSSOFilter
{ 
    public SSOFilter(){
        super.isEnabled = true;
        super.isSubEnabled = false;  
    }  

}
