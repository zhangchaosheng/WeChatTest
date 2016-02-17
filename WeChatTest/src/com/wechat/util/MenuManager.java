package com.wechat.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.model.AccessToken;
import com.wechat.model.Button;
import com.wechat.model.CommonButton;
import com.wechat.model.ComplexButton;
import com.wechat.model.Menu;

public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	private static Map<String, String> cfg = ConfigUtil.get_cfg_properties("wechat.properties");

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = cfg.get("appId");
		// 第三方用户唯一凭证密钥
		String appSecret = cfg.get("appSecret");

		// 调用接口获取access_token
		AccessToken at = CommonInterfaceUtil.getAccessToken(appId, appSecret);
		System.out.println("AccessToken=" + at.getToken());

		if (null != at) {
			// 调用接口创建菜单
			int result = CommonInterfaceUtil.createMenu(getMenu(),
					at.getToken());

			// 判断菜单创建结果
			if (0 == result) {
				log.info("菜单创建成功！");
				System.out.println("菜单创建成功！");
			} else {
				log.info("菜单创建失败，错误码：" + result);
				System.out.println("菜单创建失败，错误码：" + result);
			}
		}
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("收入提取");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("退款操作");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("账单明细");
		btn13.setType("click");
		btn13.setKey("13");

		// CommonButton btn14 = new CommonButton();
		// btn14.setName("历史上的今天");
		// btn14.setType("click");
		// btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("促销管理");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("店员管理");
		btn22.setType("click");
		btn22.setKey("22");

		// CommonButton btn23 = new CommonButton();
		// btn23.setName("美女电台");
		// btn23.setType("click");
		// btn23.setKey("23");
		//
		// CommonButton btn24 = new CommonButton();
		// btn24.setName("人脸识别");
		// btn24.setType("click");
		// btn24.setKey("24");
		//
		// CommonButton btn25 = new CommonButton();
		// btn25.setName("聊天唠嗑");
		// btn25.setType("click");
		// btn25.setKey("25");

		CommonButton btn31 = new CommonButton();
		btn31.setName("操作指南");
		btn31.setType("click");
		btn31.setKey("31");

		CommonButton btn32 = new CommonButton();
		btn32.setName("政策说明");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("联系客服");
		btn33.setType("click");
		btn33.setKey("33");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("我的收入");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("我的店铺");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("服务支持");
		mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33 });

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}
