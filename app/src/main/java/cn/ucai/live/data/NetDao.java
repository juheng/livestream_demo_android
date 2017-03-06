package cn.ucai.live.data;

import android.content.Context;

import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.domain.User;

import java.io.File;

import cn.ucai.live.I;
import cn.ucai.live.utils.MD5;
import cn.ucai.live.utils.OkHttpUtils;
import cn.ucai.live.utils.OnCompleteListener;


/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class NetDao {
    public static void register(Context context, String userName, String userNick, String password,
                                OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .post()
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.NICK, userNick)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(String.class)
                .execute(listener);
    }

    public static void unRegister(Context context, String userName, OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME, userName)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void login(Context context, String userName, String password,
                             OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(String.class)
                .execute(listener);
    }

    public static void getUserInfoByUsername(Context context, String userName,
                                             OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME, userName)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void updateNick(Context context, String userName, String userNick,
                                  OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME, userName)
                .addParam(I.User.NICK, userNick)
                .targetClass(String.class)
                .execute(listener);
    }

    public static void updateAvatar(Context context, String userName, File file,
                                    OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID, userName)
                .addParam(I.AVATAR_TYPE, I.AVATAR_TYPE_USER_PATH)
                .addFile2(file)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }

    public static void addContact(Context context, String userName, String cname,
                                    OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME, userName)
                .addParam(I.Contact.CU_NAME, cname)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void downloadContact(Context context, String userName,
                                    OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST)
                .addParam(I.Contact.USER_NAME, userName)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void removeContact(Context context, String userName, String cname,
                                  OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CONTACT)
                .addParam(I.Contact.USER_NAME, userName)
                .addParam(I.Contact.CU_NAME, cname)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void createGroup(Context context, EMGroup group, File file,
                                   OkHttpUtils.OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam(I.Group.HX_ID, group.getGroupId())
                .addParam(I.Group.NAME,group.getGroupName())
                .addParam(I.Group.DESCRIPTION,group.getDescription())
                .addParam(I.Group.OWNER,group.getOwner())
                .addParam(I.Group.IS_PUBLIC,String.valueOf(group.isPublic()))
                .addParam(I.Group.ALLOW_INVITES,String.valueOf(group.isAllowInvites()))
                .addFile2(file)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }
    public  static  void addGroupMembers(Context context, String memberName, String groupId,
                                        OkHttpUtils.OnCompleteListener<String>listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_GROUP_MEMBERS)
                .addParam(I.Member.USER_NAME,memberName)
                .addParam(I.Member.GROUP_HX_ID,groupId)
                .targetClass(String.class)
                .execute(listener);
    }
    public  static  void deleteGroupMembers(Context context, String memberName, String groupId,
                                        OkHttpUtils.OnCompleteListener<String>listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_GROUP_MEMBER)
                .addParam(I.Member.GROUP_ID,groupId)
                .addParam(I.Member.USER_NAME,memberName)
                .targetClass(String.class)
                .execute(listener);
    }
    public  static  void updateGroupName(Context context, String groupName, String groupId,
                                        OkHttpUtils.OnCompleteListener<String>listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_GROUP_NAME)
                .addParam(I.Group.NAME,groupName)
                .addParam(I.Group.GROUP_ID,groupId)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void deleteGroup(Context context, String groupId, OkHttpUtils.OnCompleteListener<String>listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_GROUP)
                .addParam(I.Group.GROUP_ID,groupId)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void createLive(Context context, User user, OnCompleteListener<String> listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_CHATROOM)
                .addParam("auth","1IFgE")
                .addParam("name",user.getMUserNick()+"的直播")
                .addParam("description",user.getMUserNick()+"的直播")
                .addParam("owner",user.getMUserName())
                .addParam("maxusers","300")
                .addParam("member",user.getMUserName())
                .targetClass(String.class)
                .execute(listener);
    }
    public static void removeLive(Context context, String chatRoomId, OnCompleteListener<String> listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CHATROOM)
                .addParam("auth","1IFgE")
                .addParam("chatRoomId",chatRoomId)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void getAllGifts(Context context, OnCompleteListener<String> listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_GET_GIFT)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void loadChange(Context context,String username, OnCompleteListener<String> listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOAD_CHANGE)
                .addParam("uname",username)
                .targetClass(String.class)
                .execute(listener);
    }
    public static void giveGift(Context context,String username,String anchor,int giftId,int count, OnCompleteListener<String> listener){
        OkHttpUtils<String>utils=new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_GIVING_GIFT)
                .addParam("uname",username)
                .addParam("anchor",anchor)
                .addParam("giftId",String.valueOf(giftId))
                .addParam("giftNum",String.valueOf(count))
                .targetClass(String.class)
                .execute(listener);
    }

}
