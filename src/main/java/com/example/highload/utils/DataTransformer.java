package com.example.highload.utils;

import com.example.highload.model.inner.*;
import com.example.highload.model.network.*;
import com.example.highload.repos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dataTransformer")
@RequiredArgsConstructor
public class DataTransformer {

    RoleRepository roleRepository;
    ProfileRepository profileRepository;
    UserRepository userRepository;
    OrderRepository orderRepository;
    ImageRepository imageRepository;
    /* users */

    public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getHashPassword());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }

    public User userFromDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setHashPassword(userDto.getPassword());
        Role role = roleRepository.findByName(userDto.getRole().toString()).orElseThrow();
        user.setRole(role);
        return user;
    }

    /* tags */

    public TagDto tagToDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        return tagDto;
    }

    public Tag tagFromDto(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
        return tag;
    }

    public List<TagDto> tagListToDto(List<Tag> entities) {
        return entities.stream().map(this::tagToDto).toList();
    }


    /* reviews */

    public ReviewDto reviewToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setText(review.getText());
//        reviewDto.setUserName(review.getProfile().getName());
        reviewDto.setUserName(review.getProfile().getUser().getLogin());
        reviewDto.setProfileId(review.getProfile().getId());
        return reviewDto;
    }

    public Review reviewFromDto(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setText(reviewDto.getText());
        Profile profile = profileRepository.findById(reviewDto.getProfileId()).orElseThrow();
        review.setProfile(profile);
        return review;
    }

    public List<ReviewDto> reviewListToDto(List<Review> entities) {
        return entities.stream().map(this::reviewToDto).toList();
    }

    /* responses */

    public ResponseDto responseToDto(Response response) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setId(response.getId());
        responseDto.setText(response.getText());
        responseDto.setUserId(response.getUser().getId());
        responseDto.setApproved(response.getIsApproved());
        responseDto.setUserName(response.getUser().getLogin());
        responseDto.setOrderId(response.getOrder().getId());
        return responseDto;
    }

    public Response responseFromDto(ResponseDto responseDto) {
        Response response = new Response();
        response.setId(responseDto.getId());
        response.setText(responseDto.getText());
        response.setIsApproved(responseDto.isApproved());
        User user = userRepository.findById(responseDto.getUserId()).orElseThrow();
        response.setUser(user);
        Order order = orderRepository.findById(responseDto.getOrderId()).orElseThrow();
        response.setOrder(order);
        return response;
    }

    public List<ResponseDto> responseListToDto(List<Response> entities) {
        return entities.stream().map(this::responseToDto).toList();
    }

    /* user requests */

    public UserRequestDto userRequestToDto(UserRequest userRequest) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(userRequest.getId());
        userRequestDto.setLogin(userRequest.getLogin());
        userRequestDto.setPassword(userRequest.getHashPassword());
        userRequestDto.setRole(userRequest.getRole().getName());
        return userRequestDto;
    }

    public UserRequest userRequestFromDto(UserRequestDto userRequestDto) {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(userRequestDto.getId());
        userRequest.setLogin(userRequestDto.getLogin());
        userRequest.setHashPassword(userRequestDto.getPassword());
        Role role = roleRepository.findByName(userRequestDto.getRole().toString()).orElseThrow();
        userRequest.setRole(role);
        return userRequest;
    }

    public List<UserRequestDto> userRequestListToDto(List<UserRequest> entities) {
        return entities.stream().map(this::userRequestToDto).toList();
    }


    /* profiles */

    public ProfileDto profileToDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setName(profile.getName());
        profileDto.setAbout(profile.getAbout());
        profileDto.setImage(imageToDto(profile.getImage()));
        profileDto.setMail(profile.getMail());
        profileDto.setEducation(profile.getEducation());
        profileDto.setExperience(profile.getExperience());
        profileDto.setUserId(profile.getUser().getId());
        return profileDto;
    }

    public Profile profileFromDto(ProfileDto profileDto) {
        Profile profile = new Profile();
        profile.setId(profileDto.getId());
        profile.setName(profileDto.getName());
        profile.setAbout(profileDto.getAbout());
        Image image = imageRepository.findById(profileDto.getImage().getId()).orElseThrow();
        profile.setImage(image);
        profile.setMail(profileDto.getMail());
        profile.setEducation(profileDto.getEducation());
        profile.setExperience(profileDto.getExperience());
        User user = userRepository.findById(profileDto.getUserId()).orElseThrow();
        profile.setUser(user);
        return profile;
    }

    public List<ProfileDto> profileListToDto(List<Profile> entities) {
        return entities.stream().map(this::profileToDto).toList();
    }

    /* orders */

    public OrderDto orderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDescription(order.getDescription());
        orderDto.setCreated(order.getCreated());
        orderDto.setPrice(order.getPrice());
        orderDto.setStatus(order.getStatus());
        orderDto.setUserName(order.getUser().getLogin());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setTags(tagListToDto(order.getTags()));
        return orderDto;
    }

    public Order orderFromDto(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setDescription(orderDto.getDescription());
        order.setCreated(orderDto.getCreated());
        order.setPrice(orderDto.getPrice());
        order.setStatus(orderDto.getStatus());
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow();
        order.setUser(user);
        return order;
    }

    public List<OrderDto> orderListToDto(List<Order> entities) {
        return entities.stream().map(this::orderToDto).toList();
    }

    /* notifications */

    public NotificationDto notificationToDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setId(notification.getId());
        notificationDto.setRead(notification.getIsRead());
        notificationDto.setTime(notification.getTime());
        notificationDto.setReceiverId(notification.getReceiverProfile().getId());
        notificationDto.setSenderId(notification.getSenderProfile().getId());
        return notificationDto;
    }

    public Notification notificationFromDto(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setId(notificationDto.getId());
        Profile senderProfile = profileRepository.findById(notificationDto.getSenderId()).orElseThrow();
        notification.setSenderProfile(senderProfile);
        Profile receiverProfile = profileRepository.findById(notificationDto.getReceiverId()).orElseThrow();
        notification.setReceiverProfile(receiverProfile);
        notification.setTime(notificationDto.getTime());
        notification.setIsRead(notificationDto.isRead());
        return notification;
    }

    public List<NotificationDto> notificationListToDto(List<Notification> entities) {
        return entities.stream().map(this::notificationToDto).toList();
    }

    /* images */

    public ImageDto imageToDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setUrl(image.getUrl());
        return imageDto;
    }

    public Image imageFromDto(ImageDto imageDto) {
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setUrl(imageDto.getUrl());
        return image;
    }

    public List<ImageDto> imageListToDto(List<Image> entities) {
        return entities.stream().map(this::imageToDto).toList();
    }

}
