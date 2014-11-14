package sample.core.service.impl;

import java.util.List;

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
import sample.core.model.SysArea;
import sample.core.model.SysDict;
import sample.core.model.SysFile;
import sample.core.model.SysMenu;
import sample.core.model.SysModule;
import sample.core.model.SysRole;
import sample.core.model.SysUser;
import sample.core.service.SystemService;
import sample.core.utils.DictUtils;
import sample.core.utils.QueryBuilder;
import sample.core.utils.QueryUtils;
import sample.core.utils.Utilities;

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
	public SysModule saveModule(String name, Integer sequence, UserInfo userInfo) {
		SysModule sysModule = new SysModule();
		sysModule.setName(name);
		sysModule.setSequence(sequence);
		sysModule.setDeleted(DictUtils.NO);
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
	public SysMenu saveMenu(Integer moduleId, Integer parentId,
			String name, String url, Integer sequence, UserInfo userInfo) {
		SysMenu sysMenu = new SysMenu();
		sysMenu.setSysModule(sysModuleDao.load(moduleId));
		sysMenu.setParentId(parentId);
		sysMenu.setName(name);
		sysMenu.setUrl(url);
		sysMenu.setSequence(sequence);
		sysMenu.setDeleted(DictUtils.NO);
		sysMenu.setOperatorId(userInfo.getUserId());
		sysMenu.setOperateDate(userInfo.getOperateDate());
		return sysMenuDao.save(sysMenu);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SysMenu updateMenu(Integer id, Integer parentId,
			String name, String url, Integer sequence, UserInfo userInfo) {
		SysMenu sysMenu = sysMenuDao.load(id);
		sysMenu.setParentId(parentId);
		sysMenu.setName(name);
		sysMenu.setUrl(url);
		sysMenu.setSequence(sequence);
		sysMenu.setOperatorId(userInfo.getUserId());
		sysMenu.setOperateDate(userInfo.getOperateDate());
		return sysMenuDao.update(sysMenu);
	}

	// Role
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public SysRole loadRole(Integer id) {
		return sysRoleDao.load(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SysRole saveRole(String name, Integer sequence,
			List<Integer> menuIds, UserInfo userInfo) {
		SysRole sysRole = new SysRole();
		sysRole.setName(name);
		sysRole.setSequence(sequence);

		QueryBuilder qb = QueryUtils.addWhereNotDeleted(new QueryBuilder());
		qb.addWhere("and t.id in {0}", Utilities.ifEmpty(menuIds, -1));
		List<SysMenu> sysMenus = sysMenuDao.find(qb);
		sysRole.setSysMenus(sysMenus);

		sysRole.setDeleted(DictUtils.NO);
		sysRole.setOperatorId(userInfo.getUserId());
		sysRole.setOperateDate(userInfo.getOperateDate());
		return sysRoleDao.save(sysRole);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SysRole updateRole(Integer id, String name, Integer sequence,
			List<Integer> menuIds, UserInfo userInfo) {
		SysRole sysRole = sysRoleDao.load(id);
		sysRole.setName(name);
		sysRole.setSequence(sequence);

		QueryBuilder qb = QueryUtils.addWhereNotDeleted(new QueryBuilder());
		qb.addWhere("and t.id in {0}", Utilities.ifEmpty(menuIds, -1));
		List<SysMenu> sysMenus = sysMenuDao.find(qb);
		sysRole.setSysMenus(sysMenus);

		sysRole.setOperatorId(userInfo.getUserId());
		sysRole.setOperateDate(userInfo.getOperateDate());
		return sysRoleDao.save(sysRole);
	}

	// User
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public SysUser loadUser(Integer id) {
		return sysUserDao.load(id);
	}

	// Dict
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public SysDict loadDict(Integer id) {
		return sysDictDao.load(id);
	}

	// Area
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public SysArea loadArea(Integer id) {
		return sysAreaDao.load(id);
	}

	// File
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public SysFile loadFile(Integer id) {
		return sysFileDao.load(id);
	}
}
