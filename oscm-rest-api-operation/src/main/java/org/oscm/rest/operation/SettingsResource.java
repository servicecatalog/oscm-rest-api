/**
 * *****************************************************************************
 *
 * <p>Copyright FUJITSU LIMITED 2019
 *
 * <p>Creation Date: 10-04-2019
 *
 * <p>*****************************************************************************
 */
package org.oscm.rest.operation;

import lombok.AccessLevel;
import lombok.Setter;
import org.oscm.rest.common.CommonParams;
import org.oscm.rest.common.RestResource;
import org.oscm.rest.common.Since;
import org.oscm.rest.operation.data.SettingRepresentation;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Since(CommonParams.VERSION_1)
@Path(CommonParams.PATH_VERSION + "/settings")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class SettingsResource extends RestResource {

  @EJB
  @Setter(value = AccessLevel.PROTECTED)
  SettingsBackend sb;


  @GET
  public Response getSettings(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return getCollection(uriInfo, sb.getCollection(), params);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createSetting(
      @Context UriInfo uriInfo,
      SettingRepresentation content,
      @BeanParam OperationParameters params)
      throws Exception {
    return post(uriInfo, sb.post(), content, params);
  }

  @GET
  @Path(CommonParams.PATH_ID)
  public Response getSetting(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return get(uriInfo, sb.get(), params, true);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(CommonParams.PATH_ID)
  public Response updateSetting(
      @Context UriInfo uriInfo,
      SettingRepresentation content,
      @BeanParam OperationParameters params)
      throws Exception {
    return put(uriInfo, sb.put(), content, params);
  }

  @DELETE
  @Path(CommonParams.PATH_ID)
  public Response deleteSetting(@Context UriInfo uriInfo, @BeanParam OperationParameters params)
      throws Exception {
    return delete(uriInfo, sb.delete(), params);
  }
}
