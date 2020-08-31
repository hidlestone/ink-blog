package com.payn.ink.service;

import com.payn.ink.domain.User;
import com.payn.ink.vo.outvo.AdminIndexDataOutVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: payn
 * @date: 2020/8/24 16:37
 */
public interface AdminIndexService {

	AdminIndexDataOutVo getAdminIndexDataInfo(User user, HttpServletRequest request);
}
