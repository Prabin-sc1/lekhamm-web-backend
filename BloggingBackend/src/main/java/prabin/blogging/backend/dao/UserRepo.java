package prabin.blogging.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import prabin.blogging.backend.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
