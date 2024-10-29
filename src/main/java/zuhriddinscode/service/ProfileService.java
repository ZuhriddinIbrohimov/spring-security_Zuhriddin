package zuhriddinscode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuhriddinscode.ProfileDTO;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDTO registration(ProfileDTO profileDTO){
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profileDTO.getName());
        entity.setSurname(profileDTO.getSurname());
        entity.setPhone(profileDTO.getPhone());
        entity.setPassword(profileDTO.getPassword());
        entity.setRole(profileDTO.getRole());

        profileRepository.save(entity);
        profileDTO.setId(entity.getId());

        return profileDTO;
    }

}