package com.crm.domain.mappers

import com.crm.domain.pojo.Student
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface StudentMapper {
    @Select("SELECT * FROM Student WHERE sId=#{sId}")
    fun findById(sId:Int): Student?


    fun find(s:Student):List<Student>

    @Select("SELECT * FROM Student")
    fun findAll():List<Student>

    @Delete("DELETE FROM Student WHERE sId=#{sId}")
    fun deleteById(sId: Int):Boolean

    @Insert("INSERT INTO Student(name,stuNum, gender, cLass, dorm, department, telephone) " +
            "VALUE (#{name},#{stuNum},#{gender},#{cLass},#{dorm},#{department},#{telephone})")
    fun add(s:Student):Boolean

    @Update("UPDATE Student SET name=#{name},gender=#{gender},cLass=#{cLass},dorm=#{dorm},department=#{department},telephone=#{telephone} WHERE sId=#{sId}")
    fun update(s:Student):Boolean
}