package digitalaxom.asm.Repository;


import digitalaxom.asm.db.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    //username should be unique across all organisations
    UserAccount findOneByUsername(String username);

}