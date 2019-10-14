package com.haishu.data.mapper;

import com.haishu.data.model.Ad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdMapper {

    List<Ad> findAllByAdPlaceCode(@Param("code") String code);

}
