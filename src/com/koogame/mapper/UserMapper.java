package com.koogame.mapper;

import java.util.List;

import com.koogame.bean.Ability;
import com.koogame.bean.Equipment;
import com.koogame.bean.Mail;
import com.koogame.bean.Solider;
import com.koogame.bean.User;
import com.koogame.bean.UserDetail;


public interface UserMapper {
	
	/**
	 * 通过imei查询用户UID
	 * @param imei
	 * @return
	 */
	public User findUserId(String imei);
	
	/**
	 * 通过imei查询用户UID
	 * @param imei
	 * @return
	 */
	public UserDetail findUserDetailByUid(int uid);
	
	
	/**
	 * 通过name查询用户 
	 * @param imei
	 * @return
	 */
	public UserDetail findUserByName(String name);
	
	/**
	 * 新注册用户
	 * @param user
	 */
	public int addUser(User user);
	
	 
	public void addNewTable(String name);
	
	/**
	 * 判断用户属性是否已经添加
	 * @param uid
	 * @return
	 */
	public UserDetail isUserAdded(int uid);
	
	/**
	 * 添加玩家属性
	 * @param userdetail
	 */
	public void addUserProperty(UserDetail userdetail);
	
	/**
	 * 更新玩家属性
	 * @param userdetail
	 */
	public void updateUser(UserDetail userdetail);
	
	/**
	 * 添加玩家技能
	 * @param ability
	 */
	public void addUserAbility(Ability ability);
 
	/**
	 * 删除玩家技能
	 * @param ability
	 */
	public void delUserAbility(int uid);
	
	/**
	 * 添加玩家装备
	 * @param equip
	 */
	public void addUserEquip(Equipment equip);
 
	/**
	 * 删除玩家装备
	 * @param equip
	 */
	public void delUserEquip(int uid);
	
	
	/**
	 * 添加玩家兵种
	 * @param equip
	 */
	public void addUserSolider(Solider solider);
 
	/**
	 * 删除玩家兵种
	 * @param equip
	 */
	public void delUserSolider(int uid);
	
	/**
	 * 查询用户邮件
	 * @param equip
	 */
	public List<Mail> findUserMail(int uid);
	 
	
	
	
 
}
