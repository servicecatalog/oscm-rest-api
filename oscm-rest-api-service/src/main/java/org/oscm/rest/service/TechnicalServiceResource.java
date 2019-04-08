package org.oscm.rest.service;

import org.oscm.internal.intf.ServiceProvisioningService;
import org.oscm.internal.vo.VOTechnicalService;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.service.data.TechnicalServiceRepresentation;
import org.oscm.string.Strings;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;

@Path(CommonParams.PATH_VERSION + "/technicalservices")
@Stateless
public class TechnicalServiceResource extends RestResource {

  @EJB TechnicalServiceBackend tsb;

  @EJB ServiceProvisioningService sps;

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTechnicalServices(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    return getCollection(uriInfo, tsb.getCollection(), params);
  }

  @Since(CommonParams.VERSION_1)
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTechnicalService(
      @Context UriInfo uriInfo,
      TechnicalServiceRepresentation content,
      @BeanParam ServiceParameters params)
      throws Exception {
    return post(uriInfo, tsb.post(), content, params);
  }

  @Since(CommonParams.VERSION_1)
  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response deleteTechnicalService(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    return delete(uriInfo, tsb.delete(), params);
  }

  @Since(CommonParams.VERSION_1)
  @GET
  @Produces(MediaType.APPLICATION_XML)
  @Path(CommonParams.PATH_ID)
  public Response exportTechnicalService(
      @Context UriInfo uriInfo, @BeanParam ServiceParameters params) throws Exception {
    // key needed
    VOTechnicalService ts = new VOTechnicalService();
    ts.setKey(params.getId().longValue());
    byte[] export = sps.exportTechnicalServices(Collections.singletonList(ts));
    return Response.ok(export, MediaType.APPLICATION_XML_TYPE).build();
  }

  @Since(CommonParams.VERSION_1)
  @PUT
  @Consumes(MediaType.APPLICATION_XML)
  public Response importTechnicalServices(
      @Context UriInfo uriInfo, byte[] input, @BeanParam ServiceParameters params)
      throws Exception {
    String msg = sps.importTechnicalServices(input);
    if (Strings.isEmpty(msg)) {
      return Response.noContent().build();
    }
    return Response.status(Status.BAD_REQUEST).entity(msg).build();
  }
}
