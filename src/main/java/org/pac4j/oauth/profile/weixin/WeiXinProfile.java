package org.pac4j.oauth.profile.weixin;

import org.pac4j.core.profile.Gender;
import org.pac4j.oauth.profile.OAuth20Profile;

/**
 * 用于添加返回用户信息
 */
public class WeiXinProfile extends OAuth20Profile {

	public String openid = "";
	// 用户在QQ空间的昵称。
	public String nickname = "";
	// 大小为30×30像素的QQ空间头像URL。
	public String figureurl = "";
	// 大小为50×50像素的QQ空间头像URL。
	public String figureurl_1 = "";
	// 大小为100×100像素的QQ空间头像URL。
	public String figureurl_2 = "";
	// 大小为40×40像素的QQ头像URL。
	public String figureurl_qq_1 = "";
	// 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
	public String figureurl_qq_2 = "";
	// 标识用户是否为黄钻用户（0：不是；1：是）
	public int vip = 0;
	// 标识用户是否为黄钻用户（0：不是；1：是）。
	public int isYellowVip = 0;
	// 黄钻等级
	public int yellowVipLevel = 0;
	// QQ等级
	public int level = 0;
	// 标识是否为年费黄钻用户（0：不是； 1：是）
	public int isYellowYearVip = 0;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFigureurl() {
		return figureurl;
	}

	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}

	public String getFigureurl_1() {
		return figureurl_1;
	}

	public void setFigureurl_1(String figureurl_1) {
		this.figureurl_1 = figureurl_1;
	}

	public String getFigureurl_2() {
		return figureurl_2;
	}

	public void setFigureurl_2(String figureurl_2) {
		this.figureurl_2 = figureurl_2;
	}

	public String getFigureurl_qq_1() {
		return figureurl_qq_1;
	}

	public void setFigureurl_qq_1(String figureurl_qq_1) {
		this.figureurl_qq_1 = figureurl_qq_1;
	}

	public String getFigureurl_qq_2() {
		return figureurl_qq_2;
	}

	public void setFigureurl_qq_2(String figureurl_qq_2) {
		this.figureurl_qq_2 = figureurl_qq_2;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public int getIsYellowVip() {
		return isYellowVip;
	}

	public void setIsYellowVip(int isYellowVip) {
		this.isYellowVip = isYellowVip;
	}

	public int getYellowVipLevel() {
		return yellowVipLevel;
	}

	public void setYellowVipLevel(int yellowVipLevel) {
		this.yellowVipLevel = yellowVipLevel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getIsYellowYearVip() {
		return isYellowYearVip;
	}

	public void setIsYellowYearVip(int isYellowYearVip) {
		this.isYellowYearVip = isYellowYearVip;
	}

}
