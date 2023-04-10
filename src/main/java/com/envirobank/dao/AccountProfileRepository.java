package com.envirobank.dao;     //h2 database

import com.envirobank.models.AccountProfile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountProfileRepository extends JpaRepository<AccountProfile, Integer> {      //extending jpa mkaes this interface into a table in our database

    public List<AccountProfile> findByAccountHolderName(String accountHolderName);
}
