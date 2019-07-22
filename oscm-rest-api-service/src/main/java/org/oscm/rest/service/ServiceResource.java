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
import org.oscm.rest.service.data.ServiceDetailsRepresentation;
import org.oscm.rest.service.data.StatusRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/services")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class ServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  ServiceBackend sb;

  @GET
  public Response getServices(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createService(
      @Context UriInfo uriInfo,
      ServiceDetailsRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @GET
  @Path(CommonParams.PATH_ID)
  public Response getService(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return get(uriInfo, sb.get(), params, true);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response updateService(
      @Context UriInfo uriInfo,
      ServiceDetailsRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return put(uriInfo, sb.put(), content, params);
  }

  @DELETE
  @Path(CommonParams.PATH_ID)
  public Response deleteService(@Context UriInfo uriInfo, @BeanParam ServiceParameters params)
      throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID + "/status")
  public Response setServiceState(
      @Context UriInfo uriInfo, StatusRepresentation content, @BeanParam ServiceParameters params)
      throws Exception {
    return put(uriInfo, sb.putStatus(), content, params);
  }
}
