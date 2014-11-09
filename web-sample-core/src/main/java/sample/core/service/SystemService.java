package sample.core.service;

import sample.core.info.UserInfo;
import sample.core.model.SysMenu;
import sample.core.model.SysModule;

public interface SystemService {
	// Module
	public SysModule loadModule(Integer id);

	public SysModule createModule(String name, Integer sequence,
			UserInfo userInfo);

	public SysModule updateModule(Integer id, String name, Integer sequence,
			UserInfo userInfo);

	// Menu
	public SysMenu loadMenu(Integer id);

	public SysMenu createMenu(Integer moduleId, Integer parentId, String name,
			String url, Integer sequence, UserInfo userInfo, int index);

	public SysMenu updateMenu(Integer id, Integer parentId, String name,
			String url, Integer sequence, UserInfo userInfo, int index);

}
