package com.ppp.user.repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.user.model.Groupe;
import com.ppp.user.model.GroupeRole;
import com.ppp.user.model.Role;

public interface GroupRoleRepository extends JpaRepository<GroupeRole, Long> {

	Collection<Role> findByGroupeAndRoleIn(Groupe group, Set<Role> roles);
	List<Role> findByRole(Role role);
	List<GroupeRole> findByGroupe(Groupe group);
	Role findRoleByGroupe(Groupe group);
	void deleteByGroupe(Groupe group); 
	
}
