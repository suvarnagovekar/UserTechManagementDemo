package com.usertech.mgmt.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.usertech.mgmt.entity.UserTech;

@Repository
public interface UserTechRepository extends CrudRepository<UserTech,Integer> {

	public List<UserTech> findByTechId(int techId);

	public void deleteByUserId(int userId);

	public void deleteByTechId(int techId);
	
	@Query("select userId from UserTech where techId=:techId")
	public List<Object> findUserIdFromUserTech(@Param("techId") int techId);
}
