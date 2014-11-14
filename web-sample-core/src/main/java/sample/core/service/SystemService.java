package sample.core.service;

import java.util.List;

import sample.core.info.UserInfo;
import sample.core.model.SysArea;
import sample.core.model.SysDict;
import sample.core.model.SysFile;
import sample.core.model.SysMenu;
import sample.core.model.SysModule;
import sample.core.model.SysRole;
import sample.core.model.SysUser;

public interface SystemService {
	// Module
	public SysModule loadModule(Integer id);

	public SysModule saveModule(String name, Integer sequence, UserInfo userInfo);

	public SysModule updateModule(Integer id, String name, Integer sequence,
			UserInfo userInfo);

	// Menu
	public SysMenu loadMenu(Integer id);

	public SysMenu saveMenu(Integer moduleId, Integer parentId, String name,
			String url, Integer sequence, UserInfo userInfo);

	public SysMenu updateMenu(Integer id, Integer parentId, String name,
			String url, Integer sequence, UserInfo userInfo);

	// Role
	public SysRole loadRole(Integer id);

	public SysRole saveRole(String name, Integer sequence,
			List<Integer> menuIds, UserInfo userInfo);

	public SysRole updateRole(Integer id, String name, Integer sequence,
			List<Integer> menuIds, UserInfo userInfo);

	// User
	public SysUser loadUser(Integer id);

	// Dict
	public SysDict loadDict(Integer id);

	// Area
	public SysArea loadArea(Integer id);

	// File
	public SysFile loadFile(Integer id);
}
