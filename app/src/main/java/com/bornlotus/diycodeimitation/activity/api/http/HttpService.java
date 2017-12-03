package com.bornlotus.diycodeimitation.activity.api.http;

import com.bornlotus.diycodeimitation.activity.api.utils.Constant;

import java.io.File;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by BornLotus on 2017/12/3.
 */

public interface HttpService {

    //---------topic-----------------------------

    /**
     * 获取 topic 列表
     *
     * @param type    类型，默认 last_actived，可选["last_actived", "recent", "no_reply", "popular", "excellent"]
     * @param node_id 如果你需要只看某个节点的，请传此参数, 如果不传 则返回全部
     * @param offset  偏移数值，默认值 0
     * @param limit   数量极限，默认值 20，值范围 1..150
     * @return topic 列表
     */
    @GET("topics.json")
    <T> Observable<T>  getTopicList(@Query("type") String type, @Query("node_id") Integer node_id,
                               @Query("offset") Integer offset, @Query("limit") Integer limit);


    /**
     * 创建一个新的 topic
     *
     * @param title   话题标题
     * @param body    话题内容, Markdown 格式
     * @param node_id 节点编号
     * @return 新话题的内容详情
     */
    @POST("topics.json")
    @FormUrlEncoded
    <T> Observable<T> createTopic(@Field("title") String title, @Field("body") String body,
                                  @Field("node_id") Integer node_id);

    /**
     * 获取 topic 内容
     *
     * @param id topic 的 id
     * @return 内容详情
     */
    @GET("topics/{id}.json")
    <T> Observable<T> getTopic(@Path("id") int id);


    /**
     * 更新(修改) topic
     *
     * @param id      要修改的话题 id
     * @param title   话题标题
     * @param body    话题内容, Markdown 格式
     * @param node_id 节点编号
     * @return 更新后的话题内容详情
     */
    @POST("topics/{id}.json")
    @FormUrlEncoded
    <T> Observable<T> updateTopic(@Path("id") int id, @Field("title") String title,
                                  @Field("body") String body, @Field("node_id") Integer node_id);


    /**
     * 删除一个话题
     *
     * @param id 要删除的话题 id
     * @return 状态
     */
    @DELETE("topics/{id}.json")
    <T> Observable<T> deleteTopic(@Path("id") int id);


    /**
     * 收藏话题
     *
     * @param id 被收藏的话题 id
     * @return 状态信息
     */
    @POST("topics/{id}/favorite.json")
    @FormUrlEncoded
    <T> Observable<T> collectionTopic(@Path("id") int id);


    /**
     * 取消收藏话题
     *
     * @param id 被收藏的话题 id
     * @return 状态信息
     */
    @POST("topics/{id}/unfavorite.json")
    @FormUrlEncoded
    <T> Observable<T> unCollectionTopic(@Path("id") int id);


    /**
     * 关注话题
     * @param id
     * @param <T>
     * @return
     */
    @POST("topics/{id}/follow.json")
    @FormUrlEncoded
    <T> Observable<T> watchTopic(@Path("id") int id);

    /**
     * 取消关注话题
     * @param id
     * @param <T>
     * @return
     */
    @POST("topics/{id}/unfollow.json")
    @FormUrlEncoded
    <T> Observable<T> unWatchTopic(@Path("id") int id);


    /**
     * 获取topic恢复列表
     * @param id
     * @param offset
     * @param limit
     * @param <T>
     * @return
     */
    @GET("topics/{id}/replies.json")
    <T> Observable<T> getTopicRepliesList(@Path("id") int id, @Query("offset") Integer offset,
                                          @Query("limit") Integer limit);


    /**
     * 创建 topic 回帖(回复，评论)
     *
     * @param id   话题列表
     * @param body 回帖内容, Markdown 格式
     * @return 回复详情
     */
    @POST("topics/{id}/replies.json")
    @FormUrlEncoded
    <T> Observable<T> createTopicReply(@Path("id") int id, @Field("body") String body);


    /**
     * 获取回帖的详细内容
     * @param id
     * @param <T>
     * @return
     */
    @GET("replies/{id}.json")
    <T> Observable<T> getTopicReply(@Path("id") int id);


    /**
     * 更新回帖
     *
     * @param id   id
     * @param body 回帖详情
     * @return 回帖内容
     */
    @POST("replies/{id}.json")
    @FormUrlEncoded
    <T> Observable<T> updateTopicReply(@Path("id") int id, @Field("body") String body);


    /**
     * 删除回帖
     *
     * @param id id
     * @return 状态
     */
    @DELETE("replies/{id}.json")
    <T> Observable<T> deleteTopicReply(@Path("id") int id);


    /**
     * 屏蔽话题，移到 NoPoint 节点 (管理员限定)
     *
     * @param id 要屏蔽的话题 id
     * @return 状态
     */
    @POST("topics/{id}/ban.json")
    @FormUrlEncoded
    <T> Observable<T> banTopic(@Path("id") int id);


    //-----------News-----------------------------------------------

    /**
     * 获取 news 列表
     *
     * @param node_id 如果你需要只看某个节点的，请传此参数, 如果不传 则返回全部
     * @param offset  偏移数值，默认值 0
     * @param limit   数量极限，默认值 20，值范围 1..150
     * @return news 列表
     */
    @GET("news.json")
    <T> Observable<T> getNewsList(@Query("node_id") Integer node_id, @Query("offset") Integer offset,
                                  @Query("limit") Integer limit);

    /**
     * 创建一个 new (分享)
     *
     * @param title   标题
     * @param address 地址(网址链接)
     * @param node_id 节点 id
     * @return 结果
     */
    @POST("news.json")
    @FormUrlEncoded
    <T> Observable<T> createNews(@Field("title") Integer title, @Field("address") Integer address,
                                 @Field("node_id") Integer node_id);


    /**
     * 获取 news 回帖列表
     *
     * @param id     id
     * @param offset 偏移数值 默认 0
     * @param limit  数量极限，默认值 20，值范围 1...150
     * @return 回复列表
     */
    @GET("news/{id}/replies.json")
    <T> Observable<T> getNewsRepliesList(@Path("id") int id, @Query("offset") Integer offset,
                                         @Query("limit") Integer limit);


    /**
     * 创建 news 回帖 (暂不可用, 没有api)
     *
     * @param id   id
     * @param body 回帖内容， markdown格式
     * @return 回复
     */
    @Deprecated
    @POST("news/{id}/replies.json")
    <T> Observable<T> createNewsReply(@Path("id") int id, @Field("body") Integer body);


    /**
     * 获取 news 回帖详情
     *
     * @param id id
     * @return 详情
     */
    @GET("news_replies/{id}.json")
    <T> Observable<T> getNewsReply(@Path("id") int id);


    /**
     * 更新 news 回帖
     *
     * @param id   id
     * @param body 回帖内容
     * @return 回帖内容
     */
    @POST("news_replies/{id}.json")
    <T> Observable<T> updateNewsReply(@Path("id") int id, @Field("body") String body);


    /**
     * 删除 news 回帖
     *
     * @param id id
     * @return 状态
     */
    @DELETE("news_replies/{id}.json")
    <T> Observable deleteNewsReply(@Path("id") int id);


    /**
     * 获取 news 分类列表
     *
     * @return 分类列表
     */
    @GET("news/nodes.json")
    <T> Observable<T> getNewsNodeList();


    //------Sites---------------------------------------------


    /**
     * 获取 酷站 列表
     * @return 列表
     */
    @GET("sites.json")
    <T> Observable<T> getSites();


    //--- user info --------------------------------------------------------------------------------

    /**
     * 获取用户列表
     *
     * @param limit 数量极限，默认值 20，值范围 1..150
     * @return 用户列表
     */
    @GET("users.json")
    <T> Observable<T> getUserList(@Query("limit") Integer limit);


    /**
     * 获取用户详细资料
     *
     * @param login_name 登录用户名(非昵称)
     * @return 用户详情
     */
    @GET("users/{login}.json")
    <T> Observable<T> getUser(@Path("login") String login_name);


    /**
     * 获取当前登录者的详细资料
     *
     * @return 用户详情
     */
    @GET("users/me.json")
    <T> Observable<T> getMe();


    /**
     * 屏蔽用户
     *
     * @param login_name 登录用户名(非昵称)
     * @return 状态
     */
    @Deprecated
    @POST("users/{login}/block.json")
    <T> Observable<T> blockUser(@Path("login") String login_name);

    /**
     * 取消屏蔽用户
     *
     * @param login_name 登录用户名(非昵称)
     * @return 状态
     */
    @Deprecated
    @POST("users/{login}/unblock.json")
    <T> Observable<T> unBlockUser(@Path("login") String login_name);


    /**
     * 获取用户屏蔽的用户列表(只能获取自己的)
     *
     * @param login_name 登录用户名(非昵称)
     * @param offset     偏移数值，默认值 0
     * @param limit      数量极限，默认值 20，值范围 1..150
     * @return 被屏蔽的用户列表
     */
    @GET("users/{login}/blocked.json")
    <T> Observable<T> getUserBlockedList(@Path("login") String login_name,
                                         @Query("offset") Integer offset, @Query("limit") Integer limit);



    /**
     * 关注用户
     *
     * @param login_name 登录用户名(非昵称)
     * @return 状态
     */
    @Deprecated
    @POST("users/{login}/follow.json")
    <T> Observable<T> followUsers(@Path("login") String login_name);


    /**
     * 取消关注用户
     *
     * @param login_name 登录用户名(非昵称)
     * @return 状态
     */
    @Deprecated
    @POST("users/{login}/unfollow.json")
    <T> Observable<T> unFollowUser(@Path("login") String login_name);


    /**
     * 用户正在关注的人列表
     *
     * @param login_name 登录用户名(非昵称)
     * @param offset     偏移数值，默认值 0
     * @param limit      数量极限，默认值 20，值范围 1..150
     * @return 用户关注的人列表
     */
    @GET("users/{login}/following.json")
    <T> Observable<T> getUserFollowingList(@Path("login") String login_name,
                                           @Query("offset") Integer offset, @Query("limit") Integer limit);


    /**
     * 关注该用户的人列白哦
     *
     * @param login_name 登录用户名(非昵称)
     * @param offset     偏移数值，默认值 0
     * @param limit      数量极限，默认值 20，值范围 1..150
     * @return 关注该用户的人列表
     */
    @GET("users/{login}/followers.json")
    <T> Observable<T> getUserFollowerList(@Path("login") String login_name,
                                          @Query("offset") Integer offset, @Query("limit") Integer limit);


    /**
     * 用户收藏的话题列表
     *
     * @param login_name 登录用户名(非昵称)
     * @param offset     偏移数值，默认值 0
     * @param limit      数量极限，默认值 20，值范围 1..150
     * @return 话题列表
     */
    @GET("users/{login}/favorites.json")
    <T> Observable<T> getUserCollectionTopicList(@Path("login") String login_name,
                                                 @Query("offset") Integer offset, @Query("limit") Integer limit);


    /**
     * 获取用户创建的话题列表
     *
     * @param login_name 登录用户名(非昵称)
     * @param order      类型 默认 recent，可选["recent", "likes", "replies"]
     * @param offset     偏移数值，默认值 0
     * @param limit      数量极限，默认值 20，值范围 1..150
     * @return 话题列表
     */
    @GET("users/{login}/topics.json")
    <T> Observable<T> getUserCreateTopicList(@Path("login") String login_name, @Query("order") String order,
                                             @Query("offset") Integer offset, @Query("limit") Integer limit);


    /**
     * 用户回复过的话题列表
     *
     * @param login_name 登录用户名(非昵称)
     * @param order      类型 默认 recent，可选["recent"]
     * @param offset     偏移数值，默认值 0
     * @param limit      数量极限，默认值 20，值范围 1..150
     * @return 话题列表
     */
    @GET("users/{login}/replies.json")
    <T> Observable<T> getUserReplyTopicList(@Path("login") String login_name, @Query("order") String order,
                                            @Query("offset") Integer offset, @Query("limit") Integer limit);


    //---------Likes-----------------------------------------

    /**
     * 赞
     *
     * @param obj_type ["topic", "reply", "news"]
     * @param obj_id   id
     * @return 是否成功
     */
    @POST("likes.json")
    @FormUrlEncoded
    <T> Observable<T> like(@Field("obj_type") String obj_type, @Field("obj_id") Integer obj_id);


    /**
     * 取消赞
     *
     * @param obj_type ["topic", "reply", "news"]
     * @param obj_id   id
     * @return 是否成功
     */
    @DELETE("likes.json")
    <T> Observable<T> unLike(@Field("obj_type") String obj_type, @Field("obj_id") Integer obj_id);


    //--- Token ------------------------------------------------------------------------------------

    /**
     * 获取 Token (一般在登录时调用)
     *
     * @param client_id     客户端 id
     * @param client_secret 客户端私钥
     * @param grant_type    授权方式 - 密码
     * @param username      用户名
     * @param password      密码
     * @return Token 实体类
     */
    @POST(Constant.OAUTH_URL)
    @FormUrlEncoded
    <T> Observable<T> getToken(
            @Field("client_id") String client_id, @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type, @Field("username") String username,
            @Field("password") String password);


    /**
     * 刷新 token
     *
     * @param client_id     客户端 id
     * @param client_secret 客户端私钥
     * @param grant_type    授权方式 - Refresh Token
     * @param refresh_token token 信息
     * @return Token 实体类
     */
    @POST(Constant.OAUTH_URL)
    @FormUrlEncoded
    <T> Observable<T> refreshToken(
            @Field("client_id") String client_id, @Field("client_secret") String client_secret,
            @Field("grant_type") String grant_type, @Field("refresh_token") String refresh_token);


    //--- devices -------------------------------------------------------------------------------

    /**
     * 记录用户 Device 信息，用于 Push 通知。
     * 请在每次用户打开 App 的时候调用此 API 以便更新 Token 的 last_actived_at 让服务端知道这个设备还活着。
     * Push 将会忽略那些超过两周的未更新的设备。
     *
     * @param platform 平台 ["ios", "android"]
     * @param token    令牌 token
     * @return 是否成功
     */
    @POST("devices.json")
    @FormUrlEncoded
    <T> Observable<T> undateService(@Field("platform") String platform, @Field("token") String token);


    /**
     * 删除 Device 信息，请注意在用户登出或删除应用的时候调用，以便能确保清理掉
     *
     * @param platform 平台 ["ios", "android"]
     * @param token    令牌 token
     * @return 是否成功
     */
    @DELETE("devices.json")
    <T> Observable<T> deleteDevices(@Field("platform") String platform, @Field("token") String token);


    //------------notification------------------------------------------------------

    /**
     * 获取通知列表
     *
     * @param offset 偏移数值，默认值 0
     * @param limit  数量极限，默认值 20，值范围 1..150
     * @return 通知列表
     */
    @GET("notifications.json")
    <T> Observable<T> getNotificationsList(@Query("offset") Integer offset,
                                           @Query("limit") Integer limit);


    /**
     * 获得未读通知数量
     *
     * @return 未读通知数量
     */
    @GET("notifications/unread_count.json")
    <T> Observable<T> getNotificationUnReadCount();;


    /**
     * 将当前用户的一些通知设成已读状态
     *
     * @param ids id 集合
     * @return 状态
     */
    @Deprecated
    @POST("notifications/read.json")
    <T> Observable<T> markNotificationAsRead(@Field("ids") int[] ids);


    /**
     * 删除当前用户的某个通知
     *
     * @param id 通知 id
     * @return 状态
     */
    @DELETE("notifications/{id}.json")
    <T> Observable<T> deleteNotification(@Path("id") int id);


    /**
     * 删除当前用户的所有通知
     *
     * @return 状态
     */
    @DELETE("notifications/all.json")
    <T> Observable<T> deleteAllNotification();


    //--------upload photo------------------------------
    /**
     * 上传图片,请使用 Multipart 的方式提交图片文件
     *
     * @param img_file 图片文件
     * @return 图片地址
     */
    @POST("photos.json")
    <T> Observable<T> uploadPhoto(@Field("file") File img_file);


    //--- project  ---------------------------------------------------------------------------------

    /**
     * 获取 project 列表
     *
     * @param node_id 如果你需要只看某个节点的，请传此参数, 如果不传 则返回全部
     * @param offset  偏移数值，默认值 0
     * @param limit   数量极限，默认值 20，值范围 1..150
     * @return project 列表
     */
    @GET("projects.json")
    <T> Observable<T> getProjectsList(@Query("node_id") Integer node_id,
                                        @Query("offset") Integer offset, @Query("limit") Integer limit);

    //--- project reply ----------------------------------------------------------------------------

    /**
     * 获取 project 回复列表
     *
     * @param id     project 的 id
     * @param offset 偏移数值 默认 0
     * @param limit  数量极限，默认值 20，值范围 1...150
     * @return 回复列表
     */
    @GET("projects/{id}/replies.json")
    <T> Observable<T> getProjectRepliesList(@Path("id") int id, @Query("offset") Integer offset,
                                                   @Query("limit") Integer limit);

    /**
     * 创建 project 回帖(回复，评论)
     *
     * @param id   话题列表
     * @param body 回帖内容, Markdown 格式
     * @return
     */
    @POST("projects/{id}/replies.json")
    @FormUrlEncoded
    <T> Observable<T> createProjectReply(@Path("id") int id, @Field("body") String body);

    /**
     * 获取回帖的详细内容（一般用于编辑回帖的时候）
     *
     * @param id id
     * @return 回帖内容
     */
    @GET("project_replies/{id}.json")
    <T> Observable<T> getProjectReply(@Path("id") int id);


    /**
     * 更新回帖
     *
     * @param id   id
     * @param body 回帖详情
     * @return 回帖内容
     */
    @POST("project_replies/{id}.json")
    @FormUrlEncoded
    <T> Observable<T> updateProjectReply(@Path("id") int id, @Field("body") String body);

    /**
     * 删除回帖
     *
     * @param id id
     * @return 状态
     */
    @DELETE("project_replies/{id}.json")
    <T> Observable<T> deleteProjectReply(@Path("id") int id);

}
