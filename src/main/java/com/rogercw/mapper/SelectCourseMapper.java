package com.rogercw.mapper;

import com.rogercw.po.SelectCourse;
import com.rogercw.po.SelectCourseExample;
import com.rogercw.po.SelectCourseKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SelectCourseMapper {
    long countByExample(SelectCourseExample example);

    int deleteByExample(SelectCourseExample example);

    int deleteByPrimaryKey(SelectCourseKey key);

    int insert(SelectCourse record);

    int insertSelective(SelectCourse record);

    List<SelectCourse> selectByExample(SelectCourseExample example);

    SelectCourse selectByPrimaryKey(SelectCourseKey key);

    int updateByExampleSelective(@Param("record") SelectCourse record, @Param("example") SelectCourseExample example);

    int updateByExample(@Param("record") SelectCourse record, @Param("example") SelectCourseExample example);

    int updateByPrimaryKeySelective(SelectCourse record);

    int updateByPrimaryKey(SelectCourse record);
}