package sample.core.service.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sample.core.dao.SysAreaDao;
import sample.core.dao.SysDictDao;
import sample.core.dao.SysFileDao;
import sample.core.dao.SysMenuDao;
import sample.core.dao.SysModuleDao;
import sample.core.dao.SysRoleDao;
import sample.core.dao.SysUserDao;
import sample.core.info.UserInfo;
import sample.core.model.SysMenu;
import sample.core.model.SysModule;
import sample.core.service.SystemService;
import sample.core.utils.DictUtils;

@Service
public class SystemServiceImpl implements SystemService {
	@Autowired
	private SysModuleDao sysModuleDao;

	@Autowired
	private SysMenuDao sysMenuDao;

	@Autowired
	private SysRoleDao sysRoleDao;

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysDictDao sysDictDao;

	@Autowired
	private SysAreaDao sysAreaDao;

	@Autowired
	private SysFileDao sysFileDao;

	// Module
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public SysModule loadModule(Integer id) {
		return sysModuleDao.load(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SysModule createModule(String name, Integer sequence,
			UserInfo userInfo) {
		SysModule sysModule = new SysModule();
		sysModule.setName(name);
		sysModule.setSequence(sequence);
		sysModule.setDelFlag(DictUtils.NO);
		sysModule.setOperatorId(userInfo.getUserId());
		sysModule.setOperateDate(userInfo.getOperateDate());
		return sysModuleDao.save(sysModule);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SysModule updateModule(Integer id, String name, Integer sequence,
			UserInfo userInfo) {
		SysModule sysModule = sysModuleDao.load(id);
		sysModule.setName(name);
		sysModule.setSequence(sequence);
		sysModule.setOperatorId(userInfo.getUserId());
		sysModule.setOperateDate(userInfo.getOperateDate());
		return sysModuleDao.update(sysModule);
	}

	// Menu
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public SysMenu loadMenu(Integer id) {
		return sysMenuDao.load(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SysMenu createMenu(Integer moduleId, Integer parentId, String name,
			String url, Integer sequence, UserInfo userInfo, int index) {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setSysModule(sysModuleDao.load(moduleId));
		sysMenu.setParentId(parentId);
		sysMenu.setName(name);

		try {
			PropertyUtils.setProperty(sysMenu, "url" + index, url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		sysMenu.setSequence(sequence);
		sysMenu.setDelFlag(DictUtils.NO);
		sysMenu.setOperatorId(userInfo.getUserId());
		sysMenu.setOperateDate(userInfo.getOperateDate());
		return sysMenuDao.save(sysMenu);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SysMenu updateMenu(Integer id, Integer parentId, String name,
			String url, Integer sequence, UserInfo userInfo, int index) {
		SysMenu sysMenu = sysMenuDao.load(id);
		sysMenu.setParentId(parentId);
		sysMenu.setName(name);

		try {
			PropertyUtils.setProperty(sysMenu, "url" + index, url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		sysMenu.setSequence(sequence);
		sysMenu.setOperatorId(userInfo.getUserId());
		sysMenu.setOperateDate(userInfo.getOperateDate());
		return sysMenuDao.update(sysMenu);
	}
}
