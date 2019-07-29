package com.ateamforce.coffeenow.dto;

import com.ateamforce.coffeenow.annotations.PasswordMatches;
import com.ateamforce.coffeenow.model.AppUser;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sakel
 */
@PasswordMatches
public class NewStoreDto extends AppUser {
    
    @NotNull(message = "{vat.notempty.restriction.message}")
    private long vat;
    
    @NotNull(message = "{storename.notempty.restriction.message}")
    @NotEmpty(message = "{storename.notempty.restriction.message}")
    @NotBlank(message = "{storename.notempty.restriction.message}")
    @Size(min = 2, max = 30, message = "{storename.size.restriction.message}")
    private String storename;
    
    @NotNull(message = "{contactname.notempty.restriction.message}")
    @NotEmpty(message = "{contactname.notempty.restriction.message}")
    @NotBlank(message = "{contactname.notempty.restriction.message}")
    @Size(min = 3, max = 30, message = "{contactname.size.restriction.message}")
    private String contactname;
    
    @NotNull(message = "{phone.notempty.restriction.message}")
    @NotEmpty(message = "{phone.notempty.restriction.message}")
    @NotBlank(message = "{phone.notempty.restriction.message}")
    @Size(min = 10, max = 10, message = "{phone.size.restriction.message}")
    private String phone;
    
    @NotNull(message = "{address.notempty.restriction.message}")
    @NotEmpty(message = "{address.notempty.restriction.message}")
    @NotBlank(message = "{address.notempty.restriction.message}")
    private String address;
    
    @NotNull(message = "{state.notempty.restriction.message}")
    @NotEmpty(message = "{state.notempty.restriction.message}")
    @NotBlank(message = "{state.notempty.restriction.message}")
    private String state;
    
    @NotNull(message = "{zip.notempty.restriction.message}")
    private int zip;

    public NewStoreDto() {
    }

    public long getVat() {
        return vat;
    }

    public void setVat(long vat) {
        this.vat = vat;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

}
