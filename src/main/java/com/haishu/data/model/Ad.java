package com.haishu.data.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Ad {

    private Long id;

    private Long adPlaceId;

    private Long goodsId;

    private String title;

    private String briefIntroduction;

    private String url;

    private String bigPictureUrl;

    private Timestamp validBeginTime;

    private Timestamp validEndTime;

    private Integer sort;

    private Integer state;

    private Timestamp createTime;
}
