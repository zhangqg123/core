package com.enation.app.base.core.tag;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.enation.app.base.core.model.Member;
import com.enation.app.base.core.service.IMemberManager;
import com.enation.eop.resource.model.EopSite;
import com.enation.eop.sdk.HeaderConstants;
import com.enation.eop.sdk.context.EopContext;
import com.enation.eop.sdk.context.EopSetting;
import com.enation.eop.sdk.context.UserConext;
import com.enation.framework.context.webcontext.ThreadContextHolder;
import com.enation.framework.taglib.BaseFreeMarkerTag;
import com.enation.framework.util.StringUtil;

import freemarker.template.TemplateModelException;
/**
 * 网站头标签
 * @author lina
 * 2014-5-27
 */
@Component
@Scope("prototype")
public class HeaderTag extends BaseFreeMarkerTag {
	@Autowired
	private IMemberManager memberManager;

	@Override
	protected Object exec(Map params) throws TemplateModelException {
		String member_id = (String) params.get("member_id");
//		String uname = (String) params.get("uname");
		Member member = null;
		if(member_id!=null&&member_id.length()>0){
			member = UserConext.getCurrentMember();
			if(member==null){
				member = memberManager.get(Integer.valueOf(member_id));
//				member.setMember_id(Integer.valueOf(member_id));
//				member.setUname(uname);
				ThreadContextHolder.getSession().setAttribute(UserConext.CURRENT_MEMBER_KEY, member);
			}
		}
		Map siteHeader = new HashMap();
		String ctx = ThreadContextHolder.getHttpRequest().getContextPath();
		EopSite site  =EopSite.getInstance();
		siteHeader.put("title", StringUtil.isEmpty(site.getTitle()) ? site.getSitename() : site.getTitle());
		siteHeader.put("keywords", site.getKeywords());
		siteHeader.put("description", site.getDescript());
		siteHeader.put("ctx",ctx);
		siteHeader.put("sitename", site.getSitename());
		siteHeader.put("gift", EopSetting.GIFT);
		siteHeader.put("tableName","");
		if(UserConext.getCurrentMember()!=null){
			siteHeader.put("tableName",UserConext.getCurrentMember().getName());
		}
		return siteHeader;
	}

}
