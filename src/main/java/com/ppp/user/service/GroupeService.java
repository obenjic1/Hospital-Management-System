package com.ppp.user.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ppp.user.model.Groupe;
import com.ppp.user.model.dto.GroupDTO;


public interface GroupeService {
	
	List<Groupe> getAllGroupe();
	Groupe groupDetails( String name);
	String updateGroupe(GroupDTO groupDTO, String name);
	Page< Groupe > findPaginatedGroup(int pageNo, int pageSize);
	void disableGroup(long id);
	String addNewGroupe(GroupDTO groupDTO, String name);

}
