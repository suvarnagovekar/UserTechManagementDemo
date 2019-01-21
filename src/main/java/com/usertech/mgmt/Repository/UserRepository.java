package com.usertech.mgmt.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.usertech.mgmt.entity.UserDetails;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserDetails,Integer> {


}
