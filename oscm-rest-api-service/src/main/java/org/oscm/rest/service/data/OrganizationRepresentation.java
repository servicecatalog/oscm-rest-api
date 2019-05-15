/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service.data;

import org.oscm.internal.vo.VOOrganization;
import org.oscm.rest.common.Representation;
import org.oscm.rest.common.RepresentationCollection;

import javax.ws.rs.WebApplicationException;
import java.util.List;

public class OrganizationRepresentation extends Representation {

  private transient VOOrganization voOrg = new VOOrganization();

  private String organizationId;

  public OrganizationRepresentation() {
    this(new VOOrganization());
  }

  public OrganizationRepresentation(VOOrganization org) {
    voOrg = org;
  }

  @Override
  public void validateContent() throws WebApplicationException {}

  @Override
  public void update() {
    voOrg.setKey(convertIdToKey());
    voOrg.setOrganizationId(getOrganizationId());
    voOrg.setVersion(convertETagToVersion());
  }

  @Override
  public void convert() {
    setId(Long.valueOf(voOrg.getKey()));
    setOrganizationId(voOrg.getOrganizationId());
    setETag(Long.valueOf(voOrg.getVersion()));
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  public VOOrganization getVO() {
    return voOrg;
  }

  public static RepresentationCollection<OrganizationRepresentation> toCollection(
      List<VOOrganization> list) {
    return null;
  }
}
