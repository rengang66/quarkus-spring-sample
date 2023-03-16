package com.iiit.quarkus.sample.kubernetes.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.iiit.quarkus.sample.kubernetes.mybatis.domain.Project;

@Mapper
public interface ProjectMapper {
	
	@Insert("insert into iiit_projects(id,name) values (#{id},#{name})")	
	public int addProject( Project project );
	
	@Select("SELECT * FROM iiit_projects WHERE id = #{id}")	
	public Project getProjectById(@Param("id") Long id);
	
	@Select("select * from iiit_projects ")	
	public List<Project> getAllProject();
	
	@Update("update iiit_projects set id=#{id}, name=#{name} where id=#{id}")
	public int updateProject(Project project);
	
	@Delete("delete from iiit_projects where id=#{id} ")
	public boolean deleteProject(@Param("id") Long empId);
	

}
