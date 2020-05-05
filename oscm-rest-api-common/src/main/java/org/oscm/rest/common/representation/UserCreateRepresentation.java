package org.oscm.rest.common.representation;

import org.oscm.internal.types.enumtypes.Salutation;
import org.oscm.internal.types.enumtypes.UserRoleType;
import org.oscm.internal.vo.VOUserDetails;
import org.oscm.rest.common.validator.RequiredFieldValidator;

import java.util.HashSet;
import java.util.Set;

public class UserCreateRepresentation extends Representation {

  private Salutation salutation;
  private String firstName;
  private String lastName;
  private String email;
  private String userId;
  private String organizationId;
  private Set<UserRoleType> userRoles = new HashSet<>();
  private String address;
  private String phone;
  private String locale;

  private transient VOUserDetails vo = new VOUserDetails();

  public Salutation getSalutation() {
    return salutation;
  }

  public void setSalutation(Salutation salutation) {
    this.salutation = salutation;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  public Set<UserRoleType> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Set<UserRoleType> userRoles) {
    this.userRoles = userRoles;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public VOUserDetails getVo() {
    return vo;
  }

  public void setVo(VOUserDetails vo) {
    this.vo = vo;
  }

  @Override
  public void update() {
    vo.setAddress(getAddress());
    vo.setEMail(getEmail());
    vo.setFirstName(getFirstName());
    vo.setKey(convertIdToKey());
    vo.setLastName(getLastName());
    vo.setLocale(getLocale());
    vo.setOrganizationId(getOrganizationId());
    vo.setPhone(getPhone());
    vo.setSalutation(getSalutation());
    vo.setUserId(getUserId());
    vo.setUserRoles(getUserRoles());
  }

  @Override
  public void validateContent() {
    RequiredFieldValidator validator = new RequiredFieldValidator();
    validator.validateNotBlank("userId", userId);
    validator.validateNotBlank("email", email);
    validator.validateNotBlank("organizationId", organizationId);
    validator.validateNotBlank("userRoles", userRoles);
  }
}
