package com.example.highload.services.impl;

import com.example.highload.model.inner.Profile;
import com.example.highload.model.network.ProfileDto;
import com.example.highload.repos.ProfileRepository;
import com.example.highload.services.ProfileService;
import com.example.highload.utils.DataTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final DataTransformer dataTransformer;

    @Override
    public Profile saveProfileForUser(ProfileDto profileDto) {
        return profileRepository.save(dataTransformer.profileFromDto(profileDto));
    }

    @Override
    public Profile editProfile(ProfileDto data, int id) {
        // TODO UPDATES IN REPOS
    }

    @Override
    public Profile findById(int id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Override
    public Profile findByUserId(int userId) {
        return profileRepository.findByUser_Id(userId).orElse(null);
    }

    @Override
    public Page<Profile> findAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }
}
