/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.service;

import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.service.data.OrganizationRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/technicalservices" + CommonParams.PATH_ID + "/suppliers")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class TSSupplierResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  TSSupplierBackend sb;

  @GET
  public Response getSuppliers(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addSupplier(
      @Context UriInfo uriInfo,
      OrganizationRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @DELETE
  @Path("/{orgId}")
  public Response removeSupplier(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }
}
