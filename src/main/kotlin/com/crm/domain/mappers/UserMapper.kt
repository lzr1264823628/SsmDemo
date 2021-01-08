package com.crm.domain.mappers

import com.crm.domain.pojo.User
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

interface UserMapper {
    @Select("SELECT * FROM User WHERE uId=#{uId}")
    fun findById(uId: Int): User

    @Select("SELECT * FROM User")
    fun findAll(u: User): List<User>

    @Select("SELECT * FROM User WHERE username=#{username}")
    fun findByName(username:String):User

    fun find(u: User): List<User>
    @Insert("INSERT INTO User(username, password, email) VALUE " +
            "(#{username},#{password},#{email})")
    fun add(u: User):Boolean

    @Delete("DELETE FROM User WHERE uId=#{uId}")
    fun deleteById(uId: Int):Boolean
    @Update("UPDATE User SET username=#{username},password=#{password},email=#{email} WHERE uId=#{uId}")
    fun update(u: User):Boolean
}