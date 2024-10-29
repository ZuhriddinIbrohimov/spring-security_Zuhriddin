package zuhriddinscode.repository;

import org.springframework.data.repository.CrudRepository;
import zuhriddinscode.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {

    Optional<ProfileEntity> findByPhoneAndVisibleTrue(String phone);
}