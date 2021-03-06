package is.citizen.sdk.resource;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public final class UserCheck implements Serializable {
    private static final long serialVersionUID = -291116567230844528L;

    /**
     * Says if a user exists and is claimed.
     */
    @JsonView(CitizenView.User.Login.class)
    private boolean userExists;

    /**
     * Says if a user exists and is a grey user.
     */
    @JsonView(CitizenView.User.Verify.class)
    private boolean greyUserExists;

    /**
     * Says if a email confirmation code is correct.
     */
    @JsonView(CitizenView.User.Verify.class)
    private boolean emailCodeCorrect;

    /**
     * Says if a claim code is correct.
     */
    @JsonView(CitizenView.User.Verify.class)
    private boolean claimCodeCorrect;

    /**
     * Says if the user has a secret.
     */
    @JsonView(CitizenView.User.Verify.class)
    private boolean hasSecret;

    /**
     * Says if the emailed checked is confirmed.
     */
    @JsonView(CitizenView.User.Verify.class)
    private boolean emailConfirmed;

    public void setUserExists(boolean userExists) {
        this.userExists = userExists;
    }

    public boolean getUserExists() {
        return userExists;
    }

    public void setGreyUserExists(boolean greyUserExists) {
        this.greyUserExists = greyUserExists;
    }

    public boolean getGreyUserExists() {
        return greyUserExists;
    }

    public boolean isEmailCodeCorrect() {
        return emailCodeCorrect;
    }

    public void setEmailCodeCorrect(boolean emailCodeCorrect) {
        this.emailCodeCorrect = emailCodeCorrect;
    }

    public boolean isClaimCodeCorrect() {
        return claimCodeCorrect;
    }

    public void setClaimCodeCorrect(boolean claimCodeCorrect) {
        this.claimCodeCorrect = claimCodeCorrect;
    }

    public boolean isHasSecret() {
        return hasSecret;
    }

    public void setHasSecret(boolean hasSecret) {
        this.hasSecret = hasSecret;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCheck userCheck = (UserCheck) o;

        if (userExists != userCheck.userExists) return false;
        if (greyUserExists != userCheck.greyUserExists) return false;
        if (emailCodeCorrect != userCheck.emailCodeCorrect) return false;
        if (claimCodeCorrect != userCheck.claimCodeCorrect) return false;
        return hasSecret == userCheck.hasSecret;
    }

    @Override
    public int hashCode() {
        int result = (userExists ? 1 : 0);
        result = 31 * result + (greyUserExists ? 1 : 0);
        result = 31 * result + (emailCodeCorrect ? 1 : 0);
        result = 31 * result + (claimCodeCorrect ? 1 : 0);
        result = 31 * result + (hasSecret ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
