package ptit.example.btlwebbook.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.UpdateUserDTO;
import ptit.example.btlwebbook.dto.request.UserRequestDTO;
import ptit.example.btlwebbook.dto.response.AddressResponse;
import ptit.example.btlwebbook.dto.response.ReviewResponse;
import ptit.example.btlwebbook.dto.response.UserResponse;
import ptit.example.btlwebbook.dto.response.VoucherResponse;
import ptit.example.btlwebbook.model.Address;
import ptit.example.btlwebbook.model.Review;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.model.Voucher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:34+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.email( userRequestDTO.getEmail() );
        user.fullName( userRequestDTO.getFullName() );
        user.phoneNumber( userRequestDTO.getPhoneNumber() );

        return user.build();
    }

    @Override
    public void updateUser(User user, UpdateUserDTO updateUserDTO) {
        if ( updateUserDTO == null ) {
            return;
        }

        user.setBirthday( updateUserDTO.getBirthday() );
        user.setFullName( updateUserDTO.getFullName() );
        user.setGender( updateUserDTO.getGender() );
        user.setPhoneNumber( updateUserDTO.getPhoneNumber() );
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.addresses( addressListToAddressResponseList( user.getAddresses() ) );
        userResponse.avatar( user.getAvatar() );
        userResponse.birthday( user.getBirthday() );
        userResponse.email( user.getEmail() );
        userResponse.fullName( user.getFullName() );
        userResponse.gender( user.getGender() );
        userResponse.id( user.getId() );
        userResponse.phoneNumber( user.getPhoneNumber() );
        userResponse.reviews( reviewListToReviewResponseList( user.getReviews() ) );
        userResponse.role( user.getRole() );
        userResponse.service( user.getService() );
        userResponse.serviceId( user.getServiceId() );
        userResponse.status( user.getStatus() );
        userResponse.vouchers( voucherListToVoucherResponseList( user.getVouchers() ) );

        return userResponse.build();
    }

    protected AddressResponse addressToAddressResponse(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponse.AddressResponseBuilder addressResponse = AddressResponse.builder();

        addressResponse.address( address.getAddress() );
        addressResponse.districtId( address.getDistrictId() );
        addressResponse.id( address.getId() );
        addressResponse.provinceId( address.getProvinceId() );
        addressResponse.wardId( address.getWardId() );

        return addressResponse.build();
    }

    protected List<AddressResponse> addressListToAddressResponseList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressResponse> list1 = new ArrayList<AddressResponse>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToAddressResponse( address ) );
        }

        return list1;
    }

    protected ReviewResponse reviewToReviewResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setContent( review.getContent() );
        if ( review.getCreatedAt() != null ) {
            reviewResponse.setCreatedAt( LocalDateTime.ofInstant( review.getCreatedAt().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        reviewResponse.setId( review.getId() );
        reviewResponse.setRating( review.getRating() );
        if ( review.getUpdatedAt() != null ) {
            reviewResponse.setUpdatedAt( LocalDateTime.ofInstant( review.getUpdatedAt().toInstant(), ZoneId.of( "UTC" ) ) );
        }

        return reviewResponse;
    }

    protected List<ReviewResponse> reviewListToReviewResponseList(List<Review> list) {
        if ( list == null ) {
            return null;
        }

        List<ReviewResponse> list1 = new ArrayList<ReviewResponse>( list.size() );
        for ( Review review : list ) {
            list1.add( reviewToReviewResponse( review ) );
        }

        return list1;
    }

    protected VoucherResponse voucherToVoucherResponse(Voucher voucher) {
        if ( voucher == null ) {
            return null;
        }

        VoucherResponse.VoucherResponseBuilder voucherResponse = VoucherResponse.builder();

        voucherResponse.code( voucher.getCode() );
        voucherResponse.createdBy( voucher.getCreatedBy() );
        voucherResponse.end( voucher.getEnd() );
        voucherResponse.id( voucher.getId() );
        voucherResponse.minimum( voucher.getMinimum() );
        voucherResponse.name( voucher.getName() );
        voucherResponse.start( voucher.getStart() );
        if ( voucher.getValue() != null ) {
            voucherResponse.value( voucher.getValue() );
        }

        return voucherResponse.build();
    }

    protected List<VoucherResponse> voucherListToVoucherResponseList(List<Voucher> list) {
        if ( list == null ) {
            return null;
        }

        List<VoucherResponse> list1 = new ArrayList<VoucherResponse>( list.size() );
        for ( Voucher voucher : list ) {
            list1.add( voucherToVoucherResponse( voucher ) );
        }

        return list1;
    }
}
