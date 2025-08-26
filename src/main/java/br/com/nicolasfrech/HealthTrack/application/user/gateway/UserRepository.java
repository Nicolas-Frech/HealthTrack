package br.com.nicolasfrech.HealthTrack.application.user.gateway;

import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import br.com.nicolasfrech.HealthTrack.domain.user.User;

public interface UserRepository {

    User save(User user);

    boolean existsByRole(Role role);
}
