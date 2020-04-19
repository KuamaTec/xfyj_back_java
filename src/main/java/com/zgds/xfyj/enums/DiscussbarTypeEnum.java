package com.zgds.xfyj.enums;

/**
 * Created by ASUS-LXP on 2019/4/24.
 */
public enum DiscussbarTypeEnum {

    HIGH_SCHOOL_DISCUSS_BAR(1, "tbl_high_schools_forum"),//高校
    AUTOMOUSELY_DISCUSS_BAR(2, "tbl_autonomously_recruit_forum"),//自主招生
    ART_DISCUSS_BAR(3, "tbl_art_recruit_forum"),//艺术类
    SPORT_DISCUSS_BAR(4, "tbl_sports_recruit_forum"),//体育类
    RECOMMEND_DISCUSS_BAR(5, "tbl_recommend_recruit_forum"),//保送生
    SPECIAL_DISCUSS_BAR(6, "tbl_special_recruit_forum");//专项

    private Integer index;
    private String tableName;


    DiscussbarTypeEnum(Integer index, String tableName) {
        this.index = index;
        this.tableName = tableName;
    }

    DiscussbarTypeEnum() {
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public static String getTableName(Integer discussType) {
        DiscussbarTypeEnum[] discussbarTypeEnums = DiscussbarTypeEnum.values();
        for (DiscussbarTypeEnum discussbarTypeEnum : discussbarTypeEnums) {
            if (discussbarTypeEnum.index.equals(discussType)) {
                return discussbarTypeEnum.getTableName();
            }
        }
        return null;
    }

    public static Integer getDiscussType(String levelName) {
        DiscussbarTypeEnum[] discussbarTypeEnums = DiscussbarTypeEnum.values();
        for (DiscussbarTypeEnum discussbarTypeEnum : discussbarTypeEnums) {
            if (discussbarTypeEnum.getTableName().equals(levelName)) {
                return discussbarTypeEnum.index;
            }
        }
        return null;
    }
}
