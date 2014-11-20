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
import sample.core.utils.Datagrid;
import sample.core.utils.QueryBuilder;

public interface SystemService {
	// Module
	public SysModule loadModule(Integer id);

	public List<SysModule> findModule(QueryBuilder qb);

	public Datagrid<SysModule> datagridModule(QueryBuilder qb);

	public SysModule saveModule(String name, Integer sequence, UserInfo userInfo);

	public SysModule updateModule(Integer id, String name, Integer sequence,
			UserInfo userInfo);

	// Menu
	public SysMenu loadMenu(Integer id);

	public List<SysMenu> findMenu(QueryBuilder qb);

	public Datagrid<SysMenu> datagridMenu(QueryBuilder qb);

	public SysMenu saveMenu(Integer moduleId, Integer parentId, String name,
			String url, Integer sequence, UserInfo userInfo);

	public SysMenu updateMenu(Integer id, Integer parentId, String name,
			String url, Integer sequence, UserInfo userInfo);

	// Role
	public SysRole loadRole(Integer id);

	public List<SysRole> findRole(QueryBuilder qb);

	public Datagrid<SysRole> datagridRole(QueryBuilder qb);

	public SysRole saveRole(String name, Integer sequence,
			List<Integer> menuIds, UserInfo userInfo);

	public SysRole updateRole(Integer id, String name, Integer sequence,
			List<Integer> menuIds, UserInfo userInfo);

	// User
	public SysUser loadUser(Integer id);

	public List<SysUser> findUser(QueryBuilder qb);

	public Datagrid<SysUser> datagridUser(QueryBuilder qb);

	// Dict
	public SysDict loadDict(Integer id);

	public List<SysDict> findDict(QueryBuilder qb);

	public Datagrid<SysDict> datagridDict(QueryBuilder qb);

	// Area
	public SysArea loadArea(Integer id);

	public List<SysArea> findArea(QueryBuilder qb);

	public Datagrid<SysArea> datagridArea(QueryBuilder qb);

	// File
	public SysFile loadFile(Integer id);

	public List<SysFile> findFile(QueryBuilder qb);

	public Datagrid<SysFile> datagridFile(QueryBuilder qb);

	// other
	public UserInfo login(String username, String password);
}
