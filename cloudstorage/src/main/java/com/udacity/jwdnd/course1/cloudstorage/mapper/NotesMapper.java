package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Notes> getNotes(Integer userId);

    @Select("SELECT COUNT(*) FROM NOTES WHERE noteId = #{noteId}")
    int checkExist(Integer noteId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Notes notes);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    int delete(Integer noteId);

    @Update("UPDATE NOTES SET notetitle =  #{noteTitle}, notedescription = #{noteDescription}," +
            "userid = #{userId} WHERE noteId = #{noteId}")
    int update(Notes notes);

}
