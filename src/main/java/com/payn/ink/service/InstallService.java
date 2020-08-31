package com.payn.ink.service;

import com.payn.ink.response.ResponseResult;
import com.payn.ink.vo.invo.InstallInVo;

/**
 * 安装，保存设置
 *
 * @author: payn
 * @date: 2020/8/18 20:13
 */
public interface InstallService {

	/**
	 * 保存安装设置
	 * @param installLnVo
	 */
	ResponseResult saveInstallOpt(InstallInVo installLnVo);

}
