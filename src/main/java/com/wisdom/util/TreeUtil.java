package com.wisdom.util;

import java.lang.reflect.Method;

import com.wisdom.entity.TreeModel;

public class TreeUtil {
	/**
	 * 复制数据到树model
	 * 
	 * @param tm
	 *            树model
	 * @param o
	 *            待复制的对象
	 * @param s
	 *            变长参数，方法的名称字符串 约定第1个为id，第2个为text， 第3个为linkUrl，第4个为iconCls，
	 *            第5个为splitNum
	 */
	public static void copyModel(TreeModel tm, Object o, String... s) {
		Class<?> clazz = o.getClass();// 获取类包名
		int count = s.length;

		try {
			if (count - 0 > 0 && s[0] != "") {
				Method id = clazz.getMethod(s[0]);// 约定第1个为id
				tm.setId(String.valueOf(id.invoke(o)));
			}
			if (count - 1 > 0 && s[1] != "") {
				Method text = clazz.getMethod(s[1]);// 约定第2个为text
				tm.setText(String.valueOf(text.invoke(o)));
			}
			if (count - 2 > 0 && s[2] != "") {
				Method url = clazz.getMethod(s[2]);// 约定第3个为funcUrl
				tm.setLinkUrl(String.valueOf(url.invoke(o)));
			}
			if (count - 3 > 0 && s[3] != "") {
				Method cls = clazz.getMethod(s[3]);// 约定第4个为iconCls
				tm.setIconCls(String.valueOf(cls.invoke(o)));
			}
			
			if(count - 4 > 0 && s[4] != "") {
				Method cls = clazz.getMethod(s[4]);
				tm.setState(String.valueOf(cls.invoke(o)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}