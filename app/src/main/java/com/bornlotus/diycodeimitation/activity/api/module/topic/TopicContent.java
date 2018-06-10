package com.bornlotus.diycodeimitation.activity.api.module.topic;

public class TopicContent {

    private int id;                         // 唯一 id
    private String title;                   // 标题
    private String created_at;              // 创建时间
    private String updated_at;              // 更新时间
    private String replied_at;              // 最近一次回复时间
    private int replies_count;              // 回复总数量
    private String node_name;               // 节点名称
    private int node_id;                    // 节点 id
    private int last_reply_user_id;         // 最近一次回复的用户 id
    private String last_reply_user_login;   // 最近一次回复的用户登录名
    private User user;                      // 创建该话题的用户(信息)
    private boolean deleted;                // 是否是被删除的
    private boolean excellent;              // 是否是加精的
    private Abilities abilities;            // 当前用户对该话题拥有的权限
    private String body;                    // 话题详情(Markdown格式)
    private String body_html;               // 话题详情(HTML 格式)
    private int hits;                       // 浏览次数
    private int likes_count;                // 喜欢的人数
    private String suggested_at;            // 置顶(推荐)时间
    private Boolean followed;               // 是否关注
    private Boolean liked;                  // 是否喜欢
    private Boolean favorited;              // 是否收藏

}
