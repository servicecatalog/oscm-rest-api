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

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.service.data.TechnicalServiceRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/technicalservices")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class TechnicalServiceResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  TechnicalServiceBackend tsb;

  @EJB ServiceProvisioningService sps;

  @GET
  public Response getTechnicalServices(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    return getCollection(uriInfo, tsb.getCollection(), params);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTechnicalService(
      @Context UriInfo uriInfo,
      TechnicalServiceRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return post(uriInfo, tsb.post(), content, params);
  }

  @DELETE
  @Path(CommonParams.PATH_ID)
  public Response deleteTechnicalService(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    return delete(uriInfo, tsb.delete(), params);
  }

  @GET
  @Path(CommonParams.PATH_ID)
  public Response exportTechnicalService(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    // FIXME: Implement this endpoint properly. Use get() from interface
    // key needed
    VOTechnicalService ts = new VOTechnicalService();
    ts.setKey(params.getId().longValue());
    byte[] export = sps.exportTechnicalServices(Collections.singletonList(ts));
    return Response.ok(export, MediaType.APPLICATION_XML_TYPE).build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response importTechnicalServices(
      @Context UriInfo uriInfo, byte[] input, @BeanParam ServiceParameters params)
      throws Exception {
    // FIXME: Implement this endpoint properly. Use put() from interface
    // FIXME: This endpoint should accept TSRepresentation instead of byte array (just like every
    // other  PUT endpoint)
    String msg = sps.importTechnicalServices(input);
    if (Strings.isNullOrEmpty(msg)) {
      return Response.noContent().build();
    }
    return Response.status(Status.BAD_REQUEST).entity(msg).build();
  }
}
