/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ucai.live.data.local;

import android.content.Context;

import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.domain.User;

import java.util.List;
import java.util.Map;

import cn.ucai.live.data.model.Gift;


public class UserDao {
	public static final String TABLE_NAME=	"uers";
	public static final String COLUMN_NAME_ID = "username";
	public static final String COLUMN_NAME_NICK = "nick";
	public static final String COLUMN_NAME_AVATAR = "avatar";
	
	public static final String PREF_TABLE_NAME = "pref";
	public static final String COLUMN_NAME_DISABLED_GROUPS = "disabled_groups";
	public static final String COLUMN_NAME_DISABLED_IDS = "disabled_ids";


	public static final String USER_TABLE_NAME=	"t_superwechat_user";
	public static final String USER_COLUMN_NAME = "m_user_name";					//用户账号
	public static final String USER_COLUMN_NAME_NICK = "m_user_nick";					//用户昵称
	public static final String USER_COLUMN_NAME_AVATAR_ID="m_avatar_id";					//主键
	public static final String USER_COLUMN_NAME_AVATAR_SUFFIX = "m_avatar_suffix";              //头像后缀名
	public static final String USER_COLUMN_NAME_AVATAR_PATH = "m_avatar_path";				//保存路径
	public static final String USER_COLUMN_NAME_AVATAR_TYPE= "m_avatar_type";				//头像类型：\n0:用户头像\n1:群组头像
	public static final String USER_COLUMN_NAME_UPDATE_TIME= "m_avatar_last_update_time";	//最后更新时间

	public static final String GIFT_TABLE_NAME="t_superwechat_gift";
	public static final String GIFT_COLUMN_ID="m_gift_id";
	public static final String GIFT_COLUMN_NAME="m_gift_name";
	public static final String GIFT_COLUMN_URL="m_gift_url";
	public static final String GIFT_COLUMN_PRICE="m_gift_price";
	public UserDao(Context context) {
	}

	/**
	 * save contact list
	 * 
	 * @param contactList
	 */
	public void saveContactList(List<EaseUser> contactList) {
	    LiveDBManager.getInstance().saveContactList(contactList);
	}

	/**
	 * get contact list
	 * 
	 * @return
	 */
	public Map<String, EaseUser> getContactList() {
		
	    return LiveDBManager.getInstance().getContactList();
	}
	
	/**
	 * delete a contact
	 * @param username
	 */
	public void deleteContact(String username){
	    LiveDBManager.getInstance().deleteContact(username);
	}
	
	/**
	 * save a contact
	 * @param user
	 */
	public void saveContact(EaseUser user){
	    LiveDBManager.getInstance().saveContact(user);
	}
	
	public void setDisabledGroups(List<String> groups){
	    LiveDBManager.getInstance().setDisabledGroups(groups);
    }
    
    public List<String>  getDisabledGroups(){       
        return LiveDBManager.getInstance().getDisabledGroups();
    }
    
    public void setDisabledIds(List<String> ids){
        LiveDBManager.getInstance().setDisabledIds(ids);
    }
    
    public List<String> getDisabledIds(){
        return LiveDBManager.getInstance().getDisabledIds();
    }
    
	/**
	 * save contact list
	 *
	 * @param contactList
	 */
	public void saveAppContactList(List<User> contactList) {
		LiveDBManager.getInstance().saveAppContactList(contactList);
	}

	/**
	 * get contact list
	 *
	 * @return
	 */
	public Map<String, User> getAppContactList() {

		return LiveDBManager.getInstance().getAppContactList();
	}

	/**
	 * delete a contact
	 * @param username
	 */
	public void deleteAppContact(String username){
		LiveDBManager.getInstance().deleteAppContact(username);
	}

	/**
	 * save a contact
	 * @param user
	 */
	public void saveAppContact(User user){
		LiveDBManager.getInstance().saveAppContact(user);
	}

	/**
	 * save contact list
	 *
	 * @param contactList
	 */
	public void saveAppGiftList(List<Gift> contactList) {
		LiveDBManager.getInstance().saveAppGiftList(contactList);
	}

	/**
	 * get contact list
	 *
	 * @return
	 */
	public Map<Integer, Gift> getAppGiftList() {

		return LiveDBManager.getInstance().getAppGiftList();
	}
}
