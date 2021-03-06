package is.citizen.sdk.resource.token;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import is.citizen.sdk.enums.*;
import is.citizen.sdk.resource.BaseEncryptedAsset;
import is.citizen.sdk.resource.CitizenView;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;

import java.util.Objects;

public class Token extends BaseEncryptedAsset {

    private static final long serialVersionUID = 4781750110446530509L;

    @JsonView({CitizenView.User.Login.class})
    private String id;

    private String name;

    private TokenType tokenType;

    private TokenStatus tokenStatus;

    private String hashedUserEmail;

    private String encryptedUserEmail;

    private String userUsername;

    private String userEmail;

    private int duration;

    private int access = 0;

    private byte[] message; // encrypted message

    @Deprecated
    private byte[] image; // unencrypted image for the token, often the profile pic

    @Deprecated
    private String imageId;

    private String requesterImageId;

    private String userImageId;

    private TokenDurationType durationType;

    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime expiryDate;

    private String hashedRequesterEmail;

    private String requesterUsername;

    @Deprecated
    private String requesterEmail;

    @JsonSerialize(using = DateTimeSerializer.class)
    private DateTime creationDate;

    private String issuingDomain;

    private String purpose;

    private CrmIntegrationType integrationType;

    private String hashedIntegrationIdentifier;

    private String encryptedIntegrationIdentifier;

    // TODO  avoid of using these constants
    public final static int
            REQUESTED_AMOUNT_1 = 1,
            REQUESTED_AMOUNT_2 = 2,
            REQUESTED_AMOUNT_3 = 3,
            REQUESTED_AMOUNT_4 = 4,
            REQUESTED_AMOUNT_5 = 5;

    public static int getMaxRequestedAmountOfDocuments() {
        return REQUESTED_AMOUNT_5;
    }

    /**
     * made it static, so we will able to reuse this logic for is.citizen.ums.domain.Token if needed
     */
    public static int getRequestedAmountOfPhotoIDs(int access) {
        if (AccessType.contains(access, AccessType.PHOTO_ID_1)) {
            return REQUESTED_AMOUNT_1;
        }
        if (AccessType.contains(access, AccessType.PHOTO_ID_2)) {
            return REQUESTED_AMOUNT_2;
        }
        if (AccessType.contains(access, AccessType.PHOTO_ID_3)) {
            return REQUESTED_AMOUNT_3;
        }
        if (AccessType.contains(access, AccessType.PHOTO_ID_4)) {
            return REQUESTED_AMOUNT_4;
        }
        if (AccessType.contains(access, AccessType.PHOTO_ID_5)) {
            return REQUESTED_AMOUNT_5;
        }
        return 0;
    }

    public static int getRequestedAmountOfAddresses(int access) {
        if (AccessType.contains(access, AccessType.ADDRESS_VALID1)) {
            return REQUESTED_AMOUNT_1;
        }
        if (AccessType.contains(access, AccessType.ADDRESS_VALID2)) {
            return REQUESTED_AMOUNT_2;
        }
        if (AccessType.contains(access, AccessType.ADDRESS_VALID3)) {
            return REQUESTED_AMOUNT_3;
        }
        if (AccessType.contains(access, AccessType.ADDRESS_VALID4)) {
            return REQUESTED_AMOUNT_4;
        }
        if (AccessType.contains(access, AccessType.ADDRESS_VALID5)) {
            return REQUESTED_AMOUNT_5;
        }
        return 0;
    }

    public static boolean hasAccessToAddressValidation(int access) {
        return
                AccessType.contains(access, AccessType.ADDRESS_VALID1) ||
                        AccessType.contains(access, AccessType.ADDRESS_VALID2) ||
                        AccessType.contains(access, AccessType.ADDRESS_VALID3) ||
                        AccessType.contains(access, AccessType.ADDRESS_VALID4) ||
                        AccessType.contains(access, AccessType.ADDRESS_VALID5);
    }

    public static boolean hasAccessToPhotoID(int access) {
        return
                AccessType.contains(access, AccessType.PHOTO_ID_1) ||
                        AccessType.contains(access, AccessType.PHOTO_ID_2) ||
                        AccessType.contains(access, AccessType.PHOTO_ID_3) ||
                        AccessType.contains(access, AccessType.PHOTO_ID_4) ||
                        AccessType.contains(access, AccessType.PHOTO_ID_5);
    }


    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int amountOfRequestedAddresses() {
        return getRequestedAmountOfAddresses(getAccess());
    }

    public int amountOfRequestedPhotoIDs() {
        return getRequestedAmountOfPhotoIDs(getAccess());
    }

    public boolean hasAccessToAddressValidation() {
        return hasAccessToAddressValidation(getAccess());
    }

    public boolean hasAccessToPhotoID() {
        return hasAccessToPhotoID(getAccess());
    }

    public String getHashedUserEmail() {
        return hashedUserEmail;
    }

    public void setHashedUserEmail(String hashedUserEmail) {
        this.hashedUserEmail = hashedUserEmail;
    }

    public String getEncryptedUserEmail() {
        return encryptedUserEmail;
    }

    public void setEncryptedUserEmail(String encryptedUserEmail) {
        this.encryptedUserEmail = encryptedUserEmail;
    }

    public String getHashedRequesterEmail() {
        return hashedRequesterEmail;
    }

    public void setHashedRequesterEmail(String hashedRequesterEmail) {
        this.hashedRequesterEmail = hashedRequesterEmail;
    }

    public String getRequesterUsername() {
        return requesterUsername;
    }

    public void setRequesterUsername(String requesterUsername) {
        this.requesterUsername = requesterUsername;
    }

    @Deprecated
    public String getRequesterEmail() {
        return requesterEmail;
    }

    @Deprecated
    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public DateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(DateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public void addAccess(AccessType accessType) {
        access = AccessType.add(access, accessType);
    }

    public void removeAccess(AccessType accessType) {
        access = AccessType.remove(access, accessType);
    }

    public void fullAccess() {
        access = AccessType.all();
    }

    public void clearAccess() {
        access = AccessType.none();
    }

    public TokenDurationType getDurationType() {
        return durationType;
    }

    public void setDurationType(TokenDurationType durationType) {
        this.durationType = durationType;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    /**
     * Used to get the plain text user that we are requesting information from
     * @return
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Used to set the plain text user that we are requesting information from
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(TokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Deprecated
    public byte[] getImage() {
        return image;
    }

    @Deprecated
    public void setImage(byte[] image) {
        this.image = image;
    }
    public String getRequesterImageId() {
        return requesterImageId;
    }

    public void setRequesterImageId(String requesterImageId) {
        this.requesterImageId = requesterImageId;
    }

    @Deprecated
    public String getImageId() {
        return imageId;
    }

    @Deprecated
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(String userImageId) {
        this.userImageId = userImageId;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public String getIssuingDomain() {
        return issuingDomain;
    }

    public void setIssuingDomain(String issuingDomain) {
        this.issuingDomain = issuingDomain;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public CrmIntegrationType getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(CrmIntegrationType integrationType) {
        this.integrationType = integrationType;
    }

    public String getHashedIntegrationIdentifier() {
        return hashedIntegrationIdentifier;
    }

    public void setHashedIntegrationIdentifier(String hashedIntegrationIdentifier) {
        this.hashedIntegrationIdentifier = hashedIntegrationIdentifier;
    }

    public String getEncryptedIntegrationIdentifier() {
        return encryptedIntegrationIdentifier;
    }

    public void setEncryptedIntegrationIdentifier(String encryptedIntegrationIdentifier) {
        this.encryptedIntegrationIdentifier = encryptedIntegrationIdentifier;
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(id, tokenType, tokenStatus, userUsername, encryptedUserEmail, duration, durationType, expiryDate, creationDate,
                        access, requesterUsername, hashedRequesterEmail, purpose);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        return Objects.equals(this.id, other.id) &&
                Objects.equals(this.userUsername, other.userUsername) &&
                Objects.equals(this.tokenType, other.tokenType) &&
                Objects.equals(this.tokenStatus, other.tokenStatus) &&
                Objects.equals(this.duration, other.duration) &&
                Objects.equals(this.access, other.access) &&
                Objects.equals(this.metaData, other.metaData) &&
                Objects.equals(this.expiryDate, other.expiryDate) &&
                Objects.equals(this.creationDate, other.creationDate) &&
                Objects.equals(this.requesterUsername, other.requesterUsername) &&
                Objects.equals(this.hashedRequesterEmail, other.hashedRequesterEmail) &&
                Objects.equals(this.hashedUserEmail, other.hashedUserEmail) &&
                Objects.equals(this.encryptedUserEmail, other.encryptedUserEmail) &&
                Objects.equals(this.purpose, other.purpose) &&
                Objects.equals(this.durationType, other.durationType);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
