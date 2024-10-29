package zuhriddinscode.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zuhriddinscode.ProfileDTO;
import zuhriddinscode.service.ProfileService;

@RestController
@RequestMapping( "/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/registration")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO profileDTO){
        ProfileDTO result= profileService.registration(profileDTO);
        return ResponseEntity.ok(result);
    }


}