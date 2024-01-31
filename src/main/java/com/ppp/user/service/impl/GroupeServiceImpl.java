package com.ppp.user.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.user.model.Groupe;
import com.ppp.user.model.GroupeRole;
import com.ppp.user.model.Role;
import com.ppp.user.model.dto.GroupDTO;
import com.ppp.user.repository.GroupRoleRepository;
import com.ppp.user.repository.GroupeRepository;
import com.ppp.user.repository.RoleRepository;
import com.ppp.user.service.GroupeService;

@Service("groupeService")
public class GroupeServiceImpl implements GroupeService {
	
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	public GroupRoleRepository groupRoleRepository;

	@Override
	public String addNewGroupe(GroupDTO groupDTO, String name) {
	    Groupe newGroup = new Groupe();
	    newGroup.setName(groupDTO.getName());
	    newGroup.setDescription(groupDTO.getDescription());
	    newGroup.setCreatedAt(LocalDateTime.now());
	    newGroup.setGroupRoles(newGroup.getGroupRoles());
	    List<String> selectedRoles = groupDTO.getIds();
	    List<Groupe> existingGroups = groupeRepository.findAll();		 
	    for (Groupe existingGroup : existingGroups) {
	        if (existingGroup.getName().equals(newGroup.getName())) {
	            return "error";
	        }
	    }
	    groupeRepository.save(newGroup);

	    for (String roleName : selectedRoles) {
	        Role role = roleRepository.findByName(roleName);
	        GroupeRole groupeRole = new GroupeRole();
	        groupeRole.setGroupe(newGroup);
	        groupeRole.setRole(role);
	        groupRoleRepository.save(groupeRole);
	    }
	    return "sucess";
	}
	

	@Override
	public List<Groupe> getAllGroupe() {
		return groupeRepository.findAll();
	}
	
	@Override
	public Page<Groupe> findPaginatedGroup(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return groupeRepository.findAll(pageable);
	}

	@Override
	public Groupe groupDetails(String name) {	 
		 return groupeRepository.findByName(name);
	}


	@Override
	public String updateGroupe(GroupDTO groupDTO, String name) {
		Groupe existinGroup = groupeRepository.findByName(name);
		if(existinGroup == null) {
			return "error";
		}
		existinGroup.setName(groupDTO.getName());
		existinGroup.setDescription(groupDTO.getDescription());
		existinGroup.setCreatedAt(LocalDateTime.now());
		    List<String> selectedRoles = groupDTO.getIds();
		    
		    groupeRepository.save(existinGroup);
		    
		    List<GroupeRole> existingRoles = groupRoleRepository.findByGroupe(existinGroup);
	        for( GroupeRole existingRoleName : existingRoles) {
	        	if(existingRoleName != null) {
	        		groupRoleRepository.delete(existingRoleName);
	        	}
	        }

		    for (String roleName : selectedRoles) {
		        Role role = roleRepository.findByName(roleName);
		        GroupeRole groupeRole = new GroupeRole();
		        groupeRole.setGroupe(existinGroup);
		        groupeRole.setRole(role);
		        groupRoleRepository.save(groupeRole);
		    }
		return null;
	}


	@Override
	public Groupe disableGroup(Long id) {
		Groupe optionalGroup = groupeRepository.findById(id).get();
      groupeRepository.delete(optionalGroup);
		return optionalGroup;
	}

}
