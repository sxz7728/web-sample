package sample.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.core.dao.SysDictDao;
import sample.core.dao.SysFileDao;
import sample.core.dao.SysMenuDao;
import sample.core.dao.SysModuleDao;
import sample.core.dao.SysRoleDao;
import sample.core.dao.SysUserDao;
import sample.core.service.SystemService;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private SysDictDao sysDictDao;

	@Autowired
	private SysFileDao sysFileDao;

	@Autowired
	private SysMenuDao sysMenuDao;

	@Autowired
	private SysModuleDao sysModuleDao;

	@Autowired
	private SysRoleDao sysRoleDao;

	@Autowired
	private SysUserDao sysUserDao;
}
