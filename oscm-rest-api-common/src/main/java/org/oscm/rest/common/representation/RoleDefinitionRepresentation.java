/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.common.representation;

import org.oscm.internal.vo.VORoleDefinition;

import javax.ws.rs.WebApplicationException;

public class RoleDefinitionRepresentation extends Representation {

  private String roleId;
  private String name;
  private String description;

  private transient VORoleDefinition vo;

  public RoleDefinitionRepresentation() {
    this(new VORoleDefinition());
  }

  public RoleDefinitionRepresentation(VORoleDefinition r) {
    vo = r;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    vo.setDescription(getDescription());
    vo.setKey(convertIdToKey());
    vo.setName(getName());
    vo.setRoleId(getRoleId());
    vo.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setDescription(vo.getDescription());
    setId(Long.valueOf(vo.getKey()));
    setName(vo.getName());
    setRoleId(vo.getRoleId());
    setETag(Long.valueOf(vo.getVersion()));
  }

  public VORoleDefinition getVO() {
    return vo;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
