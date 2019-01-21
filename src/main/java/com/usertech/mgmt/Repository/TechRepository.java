package com.usertech.mgmt.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.usertech.mgmt.entity.Technology;

@Repository
public interface TechRepository extends PagingAndSortingRepository<Technology,Integer>{
}
