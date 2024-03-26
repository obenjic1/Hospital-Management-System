package com.ppp.user.details;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.ppp.user.model.Groupe;
import com.ppp.user.model.GroupeRole;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;

@Service
public class UserDetailServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsernameOrEmail(username, username);
        if (user == null) {
           return null;
        }
        
        if(user.isDeleted()) {
        	throw new DisabledException("Votre compte a été supprimé");
        }

        Groupe group = user.getGroupe();
        if (!group.isEnabled()) {
            throw new DisabledException("Le groupe est désactivé");
        }
        List<GroupeRole> roles = group.getGroupRoles();
        
        if (roles.isEmpty()) {
        	user.setActive(true);
           return new org.springframework.security.core.userdetails.User(
                    username, user.getPassword(), user.isEnabled(),
                    user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                    user.isAccountNonLocked(), new HashSet<GrantedAuthority>());
        }	 
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (GroupeRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
        }
        return new org.springframework.security.core.userdetails.User(
            username, user.getPassword(), user.isEnabled(),
            user.isAccountNonExpired(), user.isCredentialsNonExpired(),
            user.isAccountNonLocked(), authorities);
    }
   
}